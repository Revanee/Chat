/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import Utility.CookieGetter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Utente
 */
@WebFilter(filterName = "LoggedFilter", urlPatterns = {"/room.html", "/Access"})
public class LoggedFilter implements Filter {
    
    @Override
    public void init(FilterConfig c){
        
    }
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        try {
            if (!Authenticator.checkValidToken(CookieGetter.getCookieValue("token", (HttpServletRequest)request))) {
                System.out.println("User blocked");
            ((HttpServletResponse)response).setStatus(403);
            }
        } catch (NullPointerException e) {
            System.out.println("Missing token");
            System.out.println("User blocked");
            ((HttpServletResponse)response).setStatus(403);
        }
        
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {
        
    }
}
