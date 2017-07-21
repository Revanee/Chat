package Database;

import java.sql.*;
import java.util.ArrayList;

public class UsersDB {

    public static ArrayList<String> getUsers() {
        ArrayList<String> users = new ArrayList<String>();

        try {
            Connection conn = Connector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select user from users");
            while (res.next()) {
                users.add(res.getString("user"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting users from database: " + e);
        }

        return users;
    }

    public static Boolean userExists(String checkingUser) {
        ArrayList<String> users = getUsers();
        for (String user : users) {
            if (user.equals(checkingUser)) {
                return true;
            }
        }
        return false;
    }

    public static String getUserColumn(String user, String column) {

        try {
            Connection conn = Connector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select " + column + " from users where user='" + user + "'");
            res.next();
            return res.getString(column);
        } catch (SQLException e) {
            System.out.println("Error getting user data from database: " + e);
            return null;
        }
    }

    public static void addUser(String user, String password, String salt) {
        try {
            Connection conn = Connector.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("insert into users (user, password, salt) "
                    + "values (\"" + user + "\", \"" + password + "\", \"" + salt + "\")");
        } catch (SQLException e) {
            System.out.println("Error adding user to database: " + e);
        }
    }
}
