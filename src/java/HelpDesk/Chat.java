package HelpDesk;

import Utility.Message;
import java.util.ArrayList;

class Chat {
    
    Boolean ended = false;
    
    ArrayList<Message> messages = new ArrayList<Message>();

    public void end() {
        ended = true;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}