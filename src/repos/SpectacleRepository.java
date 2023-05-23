package repos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConfiguration;
import models.Spectacle;

public class SpectacleRepository {
    //CREATE
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS SPECTACLE " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                "EVENT_TYPE VARCHAR(20) NOT NULL CHECK (EVENT_TYPE = 'balet' or EVENT_TYPE ='musical' or EVENT_TYPE ='concert'), " +
                "ID_HALL INT NOT NULL," +
                "NAME VARCHAR(100) NOT NULL, " +
                "DESCRIPTION VARCHAR(200) NOT NULL," +
                "AVAILABLE_SEATS INT NOT NULL," +
                "DATE DATE NOT NULL," +
                "STARTING_HOUR VARCHAR(10) NOT NULL," +
                "ENDING_HOUR VARCHAR(10) NOT NULL," +
                "FOREIGN KEY (ID_HALL) REFERENCES HALL(ID) ON DELETE CASCADE)" ;
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tabela SPECTACLE a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // INSERT
    static public void insertSpectacle(Spectacle spectacle){
        String query = "INSERT INTO SPECTACLE(ID, EVENT_TYPE, ID_HALL, NAME, DESCRIPTION, AVAILABLE_SEATS, DATE,  STARTING_HOUR, ENDING_HOUR) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.connection();

        try {
            Date date = Date.valueOf(spectacle.getYear()+"-"+spectacle.getMonth()+"-"+spectacle.getDay());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, spectacle.getSpectacleId());
            preparedStatement.setString(2, spectacle.getType());
            preparedStatement.setInt(3, spectacle.getHall().getId());
            preparedStatement.setString(4, spectacle.getNameSpectacle());
            preparedStatement.setString(5, spectacle.getDescription());
            preparedStatement.setInt(6, spectacle.getNoAvailableSeats());
            preparedStatement.setDate(7, date);
            preparedStatement.setString(8, spectacle.getStartingHour());
            preparedStatement.setString(9, spectacle.getEndingHour());

            preparedStatement.executeUpdate();
            System.out.println("Spectacolul a fost adaugat cu succes!");

//            if (spectacle.getType().equals("balet")){
//                BalletRepository.insertBallet(spectacle);
//            }
//            else if (spectacle.getType().equals("musical")){
//                MusicalRepository.insertMusical(spectacle);
//            }
//            else if (spectacle.getType().equals("concert")){
//                ConcertRepository.insertConcert(spectacle);
//            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //DELETE
    static public void deleteSpectacleById(int id){
        String query = "DELETE FROM SPECTACLE WGERE ID = ?";
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // SELECT
    static public boolean findSpectacle(int id){
        String query = "SELECT * FROM SPECTACLE WHERE ID = ?";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public static List<Spectacle> selectSpectacle(){
        String query = "SELECT id, event_type FROM SPECTACLE";
        Connection connection = DatabaseConfiguration.connection();
        List<Spectacle> spectacles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Spectacle spectacle;
//                if (resultSet.getString(2).equals("balet")){
//                    spectacle = BalletRepository.selectBalletById(resultSet.getInt(1));
//                }
//                else if (resultSet.getString(2).equals("musical")){
//                    spectacle = MusicalRepository.selectMusicalById(resultSet.getInt(1));
//                }
//                else {
//                    spectacle = ConcertRepository.selectConcertById(resultSet.getInt(1));
//                }
//                spectacles.add(spectacle);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return spectacles;
    }
}
