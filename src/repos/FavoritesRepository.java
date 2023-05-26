package repos;

import database.DatabaseConfiguration;
import models.Spectacle;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class FavoritesRepository {

    static public void createTable(){

        String query = "CREATE TABLE IF NOT EXISTS favorites"+
                "(id_client INT," +
                "id_show INT," +
                "PRIMARY KEY (id_client, id_show)," +
                "FOREIGN KEY (id_client) REFERENCES client(id) ON DELETE CASCADE," +
                "FOREIGN KEY (id_show) REFERENCES spectacle(id) ON DELETE CASCADE)";

        Connection connection = DatabaseConfiguration.connection();
        try{
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    static public void addFavoriteSpectacle(int clientId, int spectacleId){
        String query = "INSERT INTO favorites VALUES(?,?)";
        Connection connection = DatabaseConfiguration.connection();

        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, clientId);
            pstmt.setInt(2, spectacleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void deleteFavoriteSpectacle(int idClient, int idSpectacle){
        String insertSql = "DELETE FROM favorites WHERE id_customer = ? AND id_show = ?";
        Connection connection = DatabaseConfiguration.connection();

        try{
            PreparedStatement pstmt = connection.prepareStatement(insertSql);
            pstmt.setInt(1, idClient);
            pstmt.setInt(2, idSpectacle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void showUserFavoriteSpectacles(int clientId) {
        String query = "SELECT spectacle.id Id, spectacle.name Name  FROM favorites " +
                "JOIN client ON favorites.id_client = client.id " +
                "JOIN Spectacle ON Spectacle.id = favorites.id_show " +
                "WHERE client.id = ?";
        Connection connection = DatabaseConfiguration.connection();
        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
                System.out.println(rs.getInt("Id") + ". " + rs.getString("Name"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Set<Spectacle> selectClientFavorites(int clientId){
        String query = "SELECT id_show FROM favorites WHERE id_client = ?";
        Set<Spectacle> favs = new HashSet<>();
        Connection connection = DatabaseConfiguration.connection();
        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                //Spectacle Spectacle = SpectacleRepository.findSpectacle(rs.getInt("id_show"));
                //favs.add(Spectacle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favs;
    }
}
