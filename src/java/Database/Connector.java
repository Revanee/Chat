package Database;

import java.sql.*;

public class Connector {

    public static Connection getConnection() {

        //Register MySQL Driver for JDBC
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        //Establish connection to database
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_room?"
                    + "user=root&password=1925");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
            return null;
        }
    }
}
