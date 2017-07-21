package Procedures;

import Authentication.Authenticator;
import Database.UsersDB;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Gives a login token and status in JSON to the requesting page

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    static final Gson GSON = new Gson();
    
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
        
        JsonObject res = new JsonObject();
        res.addProperty("user", user);
        if(UsersDB.userExists(user)) {
            if (Authenticator.login(user, password)) {
                res.addProperty("token", Authenticator.getToken(user));
                res.addProperty("status", "success");
            } else {
                res.addProperty("status", "wrong password");
            }
        } else {
            res.addProperty("status", "wrong user");
        }
        
        out.write(GSON.toJson(res));
        
    }

}
