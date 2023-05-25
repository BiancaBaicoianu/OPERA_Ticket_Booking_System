package repos;
import java.sql.*;
import java.util.ArrayList;

import database.DatabaseConfiguration;
import models.Opera;
import models.Ticket;

public class TicketRepository {
    //CREATE
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS TICKET " +
                "(ID_SPECTACLE INT NOT NULL," +
                "ID_CLIENT INT NOT NULL," +
                "SEAT VARCHAR(10) NOT NULL," +
                "PRICE DOUBLE PRECISION NOT NULL," +
                "FOREIGN KEY (ID_SPECTACLE) REFERENCES SPECTACLE(ID) ON DELETE CASCADE," +
                "PRIMARY KEY (ID_CLIENT, ID_SPECTACLE, SEAT, PRICE))";
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            //System.out.println("Tabela TICKET a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // INSERT
    static public void ADDTicket(int idClient, Ticket ticket){
        String query = "INSERT INTO TICKET VALUES(?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, ticket.getSpectacle().getSpectacleId());
            preparedStatement.setString(3, ticket.getSeat());
            preparedStatement.setDouble(4, ticket.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("Biletul a fost adaugat cu succes!");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // SELECT
//    static public void getTickets(){
//        String query = "SELECT * FROM TICKET";
//        Connection connection = DatabaseConfiguration.connection();
//
//        List<Ticket> futureTickets = new ArrayList<>();
//        List<Ticket> oldTickets = new ArrayList<>();
//        Event event;
//        Ticket ticket;
//
//        try{
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while(rs.next())
//            {
//                // read all tickets to mark seats
//                int eventId = rs.getInt("event_id");
//                String event_type = SpectacleRepository.findEventType(eventId);
//                event = Opera.getOpera().findFutureEvent(eventId);
//                if (event != null)
//                    event.markSeat(rs.getString("seat"));
//
//                // add customer's tickets into the corresponding lists
//                if (rs.getInt("customer_id") == customerId) {
//                    if (event_type.equals("concert"))
//                    {
//                        event = ConcertRepository.findConcert(eventId);
//                        ticket = new ConcertTicket(rs.getString("seat"),rs.getDouble("price"), event);
//                    }
//                    else {
//                        event = TheatrePlayRepository.findTheatrePlay(eventId);
//                        ticket = new TheatrePlayTicket(rs.getString("seat"),rs.getDouble("price"), event);
//                    }
//
//                    if (Theatre.getTheatre().findPastEvent(eventId) != null) //is a past event
//                        oldTickets.add(ticket);
//                    else if (Theatre.getTheatre().findFutureEvent(eventId) != null) //is a future event
//                        futureTickets.add(ticket);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
}
