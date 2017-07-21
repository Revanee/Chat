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
    
    
    private static class Chat {

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
}
