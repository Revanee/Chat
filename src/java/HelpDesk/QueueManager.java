package HelpDesk;

import java.util.ArrayList;

public class QueueManager {

    static ArrayList<User> helpers = new ArrayList<User>();
    static ArrayList<User> users = new ArrayList<User>();

    public static void addUser(String id, String queue) {
        User user = new User(id);
        user.status = "waiting";
        if (queue.equals("helpers")) {
            user.queue = "helpers";
            helpers.add(user);
        } else if (queue.equals("users")) {
            user.queue = "users";
            users.add(user);
        }

        update();
    }

    private static void update() {
        for (User user : users) {
            if (user.busy == false) {
                for (User helper : helpers) {
                    if (helper.busy == false) {
                        user.busy = true;
                        helper.busy = true;
                        user.status = "chatting";
                        helper.status = "chatting";
                        ChatManager.addChat(user.id, helper.id);
                        break;
                    }
                }
            }
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
            if (user.id.equals(id)) {
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
