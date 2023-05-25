package repos;

import database.DatabaseConfiguration;
import models.Hall;
import models.Musical;

import java.sql.*;
import java.util.Calendar;

public class MusicalRepository {
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS MUSICAL " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                "NoACTS INT NOT NULL," +
                "FOREIGN KEY (ID) REFERENCES SPECTACLE(ID) ON DELETE CASCADE)" ;
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabela MUSICAL a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //INSERT
    static public void insertMusical(int id, int noActs){
        String query = "INSERT INTO MUSICAL VALUES (?, ?)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, noActs);
            preparedStatement.executeUpdate();
            System.out.println("Musical-ul a fost adaugat cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //READ
    static public Musical findMusical(int id){
        String query = "SELECT * FROM MUSICAL M JOIN SPECTACLE S ON M.ID = S.ID WHERE M.ID = ?";
        Connection connection = DatabaseConfiguration.connection();
        Musical spectacle = null;
        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
//            pstmt.setInt(2, noActs);
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

                spectacle = new Musical(rs.getInt("id"), hall, rs.getDouble("starting_price"),
                        rs.getString("name"), rs.getString("description"), day, month, year,
                        rs.getString("starting_hour"), rs.getString("ending_hour"), rs.getInt("noActs"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return spectacle;
    }

}
