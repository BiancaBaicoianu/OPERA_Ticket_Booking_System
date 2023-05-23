package repos;

import database.DatabaseConfiguration;

import java.sql.*;

public class AuditRepository {
    //CREATE
    static public void createTable(){
        String query = "CREATE TABLE IF NOT EXISTS AUDIT " +
                "(ID SERIAL PRIMARY KEY NOT NULL ," +
                "ACTION VARCHAR(100) NOT NULL," +
                "DATE DATE NOT NULL," +
                "TIME VARCHAR(10) NOT NULL," +
                "USER_ID INT NOT NULL," +
                "FOREIGN KEY (USER_ID) REFERENCES CLIENT(ID))" ;
        Connection connection = DatabaseConfiguration.connection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabela AUDIT a fost creata cu succes!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
//    static public void addToAudit(){
//
//    }
}
