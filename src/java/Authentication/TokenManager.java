package Authentication;

import java.util.ArrayList;

public class TokenManager {

    private static ArrayList<Token> tokens = new ArrayList<Token>();

    public static void addToken(String user) {

        if (!user.isEmpty()) {
            tokens.add(new TokenManager().new Token(user));
        }
    }

    public static String getUserFromToken(String reqToken) {
        String user = null;
        for (Token itToken : tokens) {
            if (reqToken.equals(itToken.token)) {
                user = itToken.user;
            }
        }
        return user;
    }

    public static String getTokenFromUser(String reqUser) {
        String token = null;

        for (Token itToken : tokens) {
            if (reqUser.equals(itToken.user)) {
                token = itToken.token;
            }
        }

        return token;
    }

    public static void removeToken(String token_str) {
        Token deleteCandidate = null;
        for (Token token : tokens) {
            if (token.token.equals(token_str)) {
                deleteCandidate = token;
            }
        }
        if (deleteCandidate != null) {
            tokens.remove(deleteCandidate);
        }
    }

    public static void removeUser(String user) {
        for (Token token : tokens) {
            if (token.user.equals(user)) {
                tokens.remove(token);
            }
        }
    }

    public static int getCurrentUsers() {
        return tokens.size();
    }

    private class Token {

        String user;
        String token;

        Token(String user) {
            this.user = user;
            this.token = Double.toString(Math.random());
        }
    }
}
