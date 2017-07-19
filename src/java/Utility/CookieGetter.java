/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Utente
 */
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
