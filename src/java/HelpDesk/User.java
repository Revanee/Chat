package HelpDesk;

import Utility.Message;
import java.util.ArrayList;

class User {

    String name;
    Boolean busy = false;
    String status = "idle";
    String queue;
    Chat chat = null;
    int order;

    User(String name) {
        this.name = name;
    }
    
    public ArrayList<Message> getMessages() {
        if (chat == null) System.out.println("Bad chat");
        return chat != null ? chat.messages : new ArrayList<Message>();
    }
    
    public void send(String message) {
        chat.addMessage(new Message(name, message));
    }
}
