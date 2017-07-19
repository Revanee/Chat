package Authentication;

import Database.UsersDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;


public class Authenticator {
    
    static MessageDigest md;
    
    public static void init() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Unable to initialize authenticatior: " + e);
        }
    }
    
    public static Boolean match(String user, String password) {
        
        if (UsersDB.userExists(user)) {
            String userPassword = UsersDB.getUserColumn(user, "password");
            String userSalt = UsersDB.getUserColumn(user, "salt");

            String hash = encrypt(encrypt(password) + userSalt);

            System.out.println("User hash: " + userPassword);
            System.out.println("This hash: " + hash);
            return (userPassword.equals(hash));
        } else return false;
    }
    
    public static Boolean checkValidToken(String reqToken) {
        return (TokenManager.getUserFromToken(reqToken) != null && !TokenManager.getUserFromToken(reqToken).isEmpty());
    }
    
    public static void createUser(String user, String password) {
        System.out.println("Creating user...");
        
        byte[] b = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(b);
        String salt = DatatypeConverter.printHexBinary(b);
        String hash = encrypt(encrypt(password) + salt);
        
        UsersDB.addUser(user, hash, salt);
    }
    
    public static String encrypt(String word) {
        byte[] b;
        b = word.getBytes();
        
        MessageDigest mesDig = null;
        try {
            mesDig = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error instantiating md: " + e);
        }
        
        byte[] hash;
        hash = mesDig.digest(b);
        
        return DatatypeConverter.printHexBinary(hash);
    }
}
