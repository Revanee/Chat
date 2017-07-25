package HelpDesk;

import java.util.ArrayList;
import java.util.Iterator;

class QueueManager {

    static ArrayList<User> helpers = new ArrayList<User>();
    static ArrayList<User> users = new ArrayList<User>();

    public static void addUser(User user, String queue) {
        user.status = "waiting";
        for (User usr : users) {
            if (usr.name.equals(user.name)) {
                user.status = "rejected";
            }
        }
        
        if (!user.status.equals("rejected")) {
            if (queue.equals("helpers")) {
                user.queue = "helpers";
                helpers.add(user);
            } else if (queue.equals("users")) {
                user.queue = "users";
                users.add(user);
            }
        }
    }

    public static void update() {
        setOrder();
        
        Iterator<User> user_i = users.iterator();
        Iterator<User> helper_i = helpers.iterator();
        
        while(user_i.hasNext()) {
            User user = user_i.next();
            if (!user.busy) {
                while(helper_i.hasNext()) {
                    User helper = helper_i.next();
                    if (!helper.busy) {
                        user.busy = true;
                        helper.busy = true;
                        user.status = "chatting";
                        helper.status = "chatting";
                        Chat chat = new Chat();
                        user.chat = chat;
                        helper.chat = chat;
                        setOrder();
                        break;
                    }
                }
            }
        }
    }
    
    private static void setOrder() {
        int i = 0;
        for (User user : users) {
            if (!user.busy){
                System.out.println("User: " + user.name);
                user.order = i;
                i++;
            }
        }
    }
    
    public static void removeUser (String id) {
        ArrayList<User> deleteCandidates = new ArrayList<User>();
        for (User user : users) {
            if (user.name.equals(id)) {
                deleteCandidates.add(user);
            }
        }
        for (User user : helpers) {
            if (user.name.equals(id)) {
                deleteCandidates.add(user);
            }
        }
        for (User candidate : deleteCandidates) {
            users.remove(candidate);
            helpers.remove(candidate);
        }
    }

    public static String checkStatus(String id, String queue) {
        if (queue.equals("helpers")) {
            return getUser(id, helpers).status;
        }
        if (queue.equals("users")) {
            return getUser(id, users).status;
        }
        return null;
    }

    private static User getUser(String id, ArrayList<User> queue) {
        for (User user : queue) {
            if (user.name.equals(id)) {
                return user;
            }
        }
        return null;
    }

    public static User getUser(String id) {
        User temp = getUser(id, helpers);
        if (temp != null) {
            return temp;
        } else {
            temp = getUser(id, users);
        }
        if (temp != null) {
            return temp;
        }

        return null;
    }
}
