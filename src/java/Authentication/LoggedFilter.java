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
@WebFilter(filterName = "LoggedFilter", urlPatterns = {"/*"})
public class LoggedFilter implements Filter {
    
    @Override
    public void init(FilterConfig c){
        
    }
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        String path = ((HttpServletRequest) request).getRequestURI();
        
        if (!(path.startsWith("/Chat/scr/") ||
        path.startsWith("/Chat/dep/") ||
        path.equals("/Chat/") ||
        path.equals("/Chat/favicon.png") ||
        path.equals("/Chat/login.html") ||
        path.equals("/Chat/Login") ||
        path.equals("/Chat/registration.html") ||
        path.equals("/Chat/Register"))) {
            try {
                if (!Authenticator.checkValidToken(CookieGetter.getCookieValue("token", (HttpServletRequest)request))) {
                    System.out.println("User blocked");
                    ((HttpServletResponse)response).setStatus(403);
                    ((HttpServletResponse)response).sendRedirect("login.html");
                } else chain.doFilter(request, response);
            } catch (NullPointerException e) {
                System.out.println("Missing token");
                System.out.println("User blocked");
                ((HttpServletResponse)response).setStatus(403);
                ((HttpServletResponse)response).sendRedirect("login.html");
            }
        } else chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
}
