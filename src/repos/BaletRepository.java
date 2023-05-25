package repos;

import database.DatabaseConfiguration;
import models.Hall;
import models.Ballet;
import java.sql.*;
import java.util.Calendar;

public class BaletRepository {
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS BALET " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                "LOGESEAT BOOL NOT NULL," +
                "FOREIGN KEY (ID) REFERENCES SPECTACLE(ID) ON DELETE CASCADE)" ;
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabela BALET a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //INSERT
    static public void insertBalet(int id, boolean logeSeat){
        String query = "INSERT INTO BALET VALUES (?, ?)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, logeSeat);
            preparedStatement.executeUpdate();
            System.out.println("Baletul a fost adaugat cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //READ
    static public Ballet findBalet(int id){
        String query = "SELECT * FROM BALET B JOIN SPECTACLE S ON B.ID = S.ID WHERE B.ID = ?";
        Connection connection = DatabaseConfiguration.connection();
        Ballet spectacle = null;
        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                Date date = rs.getDate("date");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                Hall hall = HallRepository.getHallById(rs.getInt("hall_id"));

                spectacle = new Ballet(rs.getInt("id"), hall, rs.getDouble("starting_price"),
                        rs.getString("name"), rs.getString("description"), day, month, year,
                        rs.getString("starting_hour"), rs.getString("ending_hour"), rs.getBoolean("logeseat"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return spectacle;
    }

}
