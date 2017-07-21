package Database;

import Utility.Message;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MessagesDB {

    public static void addMessage(String user, String text) {

        //Generate dateTime
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        //Try inserting in db
        try {
            Connection conn = Connector.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("insert into messages (id, user, text, time) "
                    + "values (" + (getLastMessageID() + 1) + ", \"" + user + "\", \"" + text + "\", \"" + currentTime + "\")");
        } catch (SQLException e) {
            System.out.println("Error adding message to database: " + e);
        }
    }

    public static int getLastMessageID() {

        try {
            int id;
            Connection conn = Connector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select id from messages order by id desc limit 1");
            res.next();
            id = res.getInt("id");
            return id;
        } catch (SQLException e) {
            System.out.println("Error getting message id from database: " + e);
            return -1;
        }
    }

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();

        try {
            Connection conn = Connector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from messages");
            while (res.next()) {
                messages.add(new Message(res.getInt("id"), res.getString("user"), res.getString("text"), new Date(res.getTimestamp("time").getTime())));
            }
        } catch (SQLException e) {
            //System.out.println("Error getting messages from database: " + e);
        }

        return messages;
    }
}
