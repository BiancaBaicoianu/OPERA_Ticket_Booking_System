package repos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConfiguration;
import models.Hall;

public class HallRepository {
    //CREATE
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS HALL " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                " NAME VARCHAR(100) NOT NULL, " +
                "FLOOR INT NOT NULL," +
                "AVAILABLE BOOL DEFAULT TRUE," +
                "NO_ROWS INT NOT NULL," +
                "NO_COLUMNS INT NOT NULL)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tabela HALL a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // INSERT
    public static void addHall(Hall hall){
        String query = "INSERT INTO HALL(NAME, FLOOR, AVAILABLE, NO_ROWS, NO_COLUMNS) VALUES(?,?,?,?,?)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, hall.getName());
            preparedStatement.setInt(2, hall.getFloor());
            preparedStatement.setBoolean(3, hall.getAvailability());
            preparedStatement.setInt(4, hall.getRows());
            preparedStatement.setInt(5, hall.getColumns());
            preparedStatement.executeUpdate();
            System.out.println("Sala a fost adaugata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // DELETE
    public static void deleteHall(int id){
        String query = "DELETE FROM HALL WHERE ID = ?";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Sala a fost stearsa cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // SELECT
    public static Hall getHallById(int id){
        String query = "SELECT * FROM HALL WHERE ID = ?";
        Connection connection = DatabaseConfiguration.connection();
        Hall hall = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                hall = new Hall(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("floor"), resultSet.getBoolean("available"), resultSet.getInt("no_rows"), resultSet.getInt("no_columns"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return hall;
    }
    //SELECT
    public static void showHalls(){
        String query = "SELECT * FROM HALL";
        Connection connection = DatabaseConfiguration.connection();
        Hall hall = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                hall = new Hall(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("floor"), resultSet.getBoolean("available"), resultSet.getInt("no_rows"), resultSet.getInt("no_columns"));
                hall.showInfos();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // SELECT ALL
    public static void showAllHalls(){
        List<Hall> halls = new ArrayList<>();
        String query = "SELECT * FROM HALL";
        Connection connection = DatabaseConfiguration.connection();
        Hall hall = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                hall = new Hall(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("floor"), resultSet.getBoolean("available"), resultSet.getInt("no_rows"), resultSet.getInt("no_columns"));
                halls.add(hall);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
