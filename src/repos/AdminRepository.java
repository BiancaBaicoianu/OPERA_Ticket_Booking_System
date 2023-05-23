package repos;
import java.sql.*;
import database.DatabaseConfiguration;
public class AdminRepository {
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS ADMIN " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                "USERNAME VARCHAR(100) NOT NULL, " +
                "PASSWORD VARCHAR(100) NOT NULL)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tabela ADMIN a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // INSERT
    static public void AddAdmin(String username, String password){
        String query = "INSERT INTO ADMIN VALUES( ?, ?)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            System.out.println("Adminul a fost adaugat cu succes!");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // DELETE
    static public void DeleteAdmin(String username){
        String query = "DELETE FROM ADMIN WHERE USERNAME = ?";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            System.out.println("Adminul a fost sters cu succes!");
            // aici vom adauga in audit ca s-a sters un admin

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // SELECT
    static public boolean checkAdmin(String username, String password) {
        String query = "SELECT * FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Adminul a fost gasit!");
                return true;
            } else {
                System.out.println("Adminul nu a fost gasit!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}

