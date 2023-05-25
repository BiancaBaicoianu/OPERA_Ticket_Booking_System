package database;

import repos.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManagement {
    private static DatabaseManagement databaseManagement;
    // singleton
    private DatabaseManagement() {
        initDatabase();
    }

    public static DatabaseManagement getDatabaseManagement() {
        if (databaseManagement == null) {
            databaseManagement = new DatabaseManagement();
        }
        return databaseManagement;
    }

    private void initDatabase() {
        //CREATE
        ClientRepository.createTable();
        AdminRepository.createTable();
        HallRepository.createTable();
        SpectacleRepository.createTable();
        TicketRepository.createTable();
        BaletRepository.createTable();
        MusicalRepository.createTable();
        ConcertRepository.createTable();

    }

    public static int lastIdTable(String tableName){
        int id = 0;
        String query = "SELECT MAX(ID) FROM " + tableName;
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
//    public void initCustomer(){
//        Set<Event> favs = FavoritesRepository.selectCustomerFavorites(Customer.getCustomer().getId());
//        Customer.getCustomer().setFavorites(favs);
//
//        TicketRepository.selectCustomerTickets(Customer.getCustomer().getId());
//    }
}
