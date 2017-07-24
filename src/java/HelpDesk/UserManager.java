package HelpDesk;

import java.util.ArrayList;

public class UserManager {

    private static ArrayList<User> users = new ArrayList<User>();

    public static void addUser(String name) {
        System.out.println("Adding user: " + name);
        users.add(new User(name));
    }

    public static void removeUser(String name) {
        if (getUser(name) != null) {
            getUser(name).chat.end();
            users.remove(getUser(name));
        }
    }

    public static User getUser(String name) {
        for (User user : users) {
            System.out.println(user.name);
            if (user.name.equals(name)) {
                if (user.chat != null) if (user.chat.ended) user.status = "idle";
                return user;
            }
        }
        System.out.println("User " + name + " not found in UserManager");
        return null;
    }

    public static void sendToQueue(String name, String queue) {
        getUser(name).status = "waiting";
        QueueManager.addUser(getUser(name), queue);
        QueueManager.update();
    }
}
