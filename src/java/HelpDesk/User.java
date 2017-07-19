package HelpDesk;

public class User {
    String id;
    Boolean busy = false;
    String status;
    String queue;
    
    User(String id) {
        this.id = id;
    }
}
