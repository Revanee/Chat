package Authentication;

import Database.UsersDB;
import HelpDesk.UserManager;
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
    
    public static String getUser(String token) {
        return TokenManager.getUserFromToken(token);
    }
    
    public static String getToken(String token) {
        return TokenManager.getTokenFromUser(token);
    }
    
    public static Boolean login(String user, String password) {
        if(match(user, password)){
            TokenManager.removeUser(user);
            TokenManager.addToken(user);
            if(UserManager.getUser(user) == null) UserManager.addUser(user);
            return true;
        } else return false;
    }
    
    public static void logout(String token) {
        TokenManager.removeToken(token);
    }
    
    public static Boolean match(String user, String password) {

        if (UsersDB.userExists(user)) {
            String userPassword = UsersDB.getUserColumn(user, "password");
            String userSalt = UsersDB.getUserColumn(user, "salt");

            String hash = encrypt(encrypt(password) + userSalt);

            return (userPassword.equals(hash));
        } else {
            return false;
        }
    }

    public static Boolean checkValidToken(String reqToken) {
        return (TokenManager.getUserFromToken(reqToken) != null);
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

        byte[] hash = null;
        try {
            MessageDigest mesDig;
            mesDig = MessageDigest.getInstance("SHA-256");
            hash = mesDig.digest(b);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error instantiating md: " + e);
        }

        return DatatypeConverter.printHexBinary(hash);
    }
}
