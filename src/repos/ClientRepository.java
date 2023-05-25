package repos;
import java.sql.*;
import database.DatabaseConfiguration;
public class ClientRepository {
    //CREATE
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS CLIENT " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                "USERNAME VARCHAR(100) NOT NULL, " +
                "PASSWORD VARCHAR(100) NOT NULL)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            //System.out.println("Tabela CLIENT a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // INSERT
    static public void ADDClient(String username, String password){
        String query = "INSERT INTO CLIENT(username,password) VALUES( ?, ?)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            System.out.println("Clientul a fost adaugat cu succes!");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // DELETE
    static public void DeleteClient(String username){
        String query = "DELETE FROM CLIENT WHERE USERNAME = ?";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            System.out.println("Clientul a fost sters cu succes!");
            // aici vom adauga in audit ca s-a sters un client

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // SELECT
    static public int checkClient(String username, String password) {
        String query = "SELECT * FROM CLIENT WHERE USERNAME = ? AND PASSWORD = ?";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //System.out.println("Clientul a fost gasit!");
                return 1;
            } else {
                //System.out.println("Clientul nu a fost gasit!");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
}
