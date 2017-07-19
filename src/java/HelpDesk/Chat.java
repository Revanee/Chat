package HelpDesk;

import Utility.Message;
import java.util.ArrayList;

public class Chat {
    ArrayList<Message> messages = new ArrayList<Message>();
    ArrayList<String> users = new ArrayList<String>();
    
    public void addUser(String user) {
        users.add(user);
    }
    
    public void addMessage(Message message) {
        messages.add(message);
    }
    
    public Boolean userIsPresent(String user) {
        return users.contains(user);
    }
}
