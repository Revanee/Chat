package HelpDesk;

import Utility.Message;
import java.util.ArrayList;

public class ChatManager {
    static ArrayList<Chat> chats = new ArrayList<Chat>();
    
    public static void addChat(String u1, String u2) {
        Chat chat = new Chat();
        chat.addUser(u1);
        chat.addUser(u2);
        chats.add(chat);
    }
    
    public static ArrayList<Message> getMessages(String id) {
        for (Chat chat : chats) {
            if (chat.userIsPresent(id)) {
                System.out.println("Chat found!");
                return chat.messages;
            }
        }
        
        System.out.println("Chat not found!");
        return null;
    }
    
    public static void addMessage(String id, String message) {
        for (Chat chat : chats) {
            if (chat.userIsPresent(id)) {
                chat.addMessage(new Message(id, message));
            }
        }
    }
}
