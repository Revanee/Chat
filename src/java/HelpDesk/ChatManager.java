package HelpDesk;

import Utility.Message;
import java.util.ArrayList;

class ChatManager {

    static ArrayList<Chat> chats = new ArrayList<Chat>();

    public static void addChat(User u1, User u2) {
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
    
    public static void removeChats(String id) {
        ArrayList<Chat> deleteCandidates = new ArrayList<Chat>();
        for (Chat chat : chats) {
            if (chat.userIsPresent(id)) {
                deleteCandidates.add(chat);
            }
        }
        for (Chat candidate : deleteCandidates) {
            candidate.end();
            chats.remove(candidate);
        }
    }    
    
    private static class Chat {

        ArrayList<Message> messages = new ArrayList<Message>();
        ArrayList<User> users = new ArrayList<User>();

        public void end() {
            for (User user : users) {
                QueueManager.removeUser(user.id);
            }
        }
        
        public void addUser(User user) {
            users.add(user);
        }

        public void addMessage(Message message) {
            messages.add(message);
        }

        public Boolean userIsPresent(String user) {
            Boolean res = false;
            for (User usr : users) {
                if (usr.id.equals(user)) res = true;
            }
            return res;
        }
    }
}
