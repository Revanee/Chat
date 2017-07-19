package Procedures;

import Authentication.Authenticator;
import Authentication.TokenManager;
import Database.UsersDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        
        String token = "";
        String status;
        
        if(UsersDB.userExists(user)) {
            if (Authenticator.match(user, password)) {
                TokenManager.addToken(user);
                token = TokenManager.getTokenFromUser(user);
                status = "success";
            } else {
                status = "wrong password";
            }
        } else {
            status = "wrong user";
        }
        
        out.write("{\"status\": \"" + status + "\", \"token\": \"" + token + "\", \"user\": \"" + user + "\"}");

    }

}
