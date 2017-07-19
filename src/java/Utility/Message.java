package Utility;

import java.util.Date;

public class Message {
    public int id;
    public String user;
    public String text;
    public Date time;
    
    public Message(int id, String user, String text, Date time) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.time = time;
    }
}
