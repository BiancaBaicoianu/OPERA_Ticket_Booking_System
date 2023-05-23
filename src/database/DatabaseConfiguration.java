package database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String DRIVER_CLASS = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/opera";
    private static final String username = "postgres";
    private static final String password = "bianca17";
    // stabilirea conexiunii cu bd
    public static Connection connection()  {
        Connection connection = null;
        try {
            Class.forName(DRIVER_CLASS); // inregistreaza drive-ul jdbc
            // asigura comunicarea cu baza de date

            connection = java.sql.DriverManager.getConnection(DB_URL, username, password); // deschiderea unei conexiuni cu bd
            if (connection != null) {
                System.out.println("Conexiunea cu baza de date a fost realizata cu succes!");
            } else {
                System.out.println("Nu s-a putut realiza conexiunea cu baza de date!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

//    public static void closeConnection() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
