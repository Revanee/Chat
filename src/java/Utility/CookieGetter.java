package Utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieGetter {

    public static String getCookieValue(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
