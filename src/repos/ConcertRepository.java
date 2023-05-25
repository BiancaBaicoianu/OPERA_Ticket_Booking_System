package repos;

import database.DatabaseConfiguration;
import models.Concert;
import java.util.Calendar;
import models.Hall;

import java.sql.*;

public class ConcertRepository {
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS CONCERT " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                "withSeat BOOL NOT NULL," +
                "FOREIGN KEY (ID) REFERENCES SPECTACLE(ID) ON DELETE CASCADE)" ;
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            //System.out.println("Tabela CONCERT a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //INSERT
    static public void insertConcert(int id, boolean withSeat){
        String query = "INSERT INTO CONCERT VALUES (?, ?)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, withSeat);
            preparedStatement.executeUpdate();
            System.out.println("Concertul a fost adaugat cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //READ
    static public Concert findConcert(int id){
        String query = "SELECT * FROM CONCERT C JOIN SPECTACLE S ON C.ID = S.ID WHERE ID = ?";
        Connection connection = DatabaseConfiguration.connection();
        Concert spectacle = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Date date = resultSet.getDate("date");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                Hall hall = HallRepository.getHallById(resultSet.getInt("hall_id"));

                spectacle = new Concert(resultSet.getInt("id"), hall, resultSet.getDouble("starting_price"),
                        resultSet.getString("name"), resultSet.getString("description"), day, month, year,
                        resultSet.getString("starting_hour"), resultSet.getString("ending_hour"),
                        resultSet.getBoolean("withSeat"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return spectacle;
    }
}
