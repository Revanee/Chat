package Procedures;

//Creates a new user in database

import Authentication.Authenticator;
import Database.UsersDB;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    static final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registration.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        JsonObject res = new JsonObject();
        
        if (UsersDB.userExists(user)) {
            res.addProperty("status", "existing user");
        } else if (password.length() < 4) {
            res.addProperty("status", "short password");
        } else {
            Authenticator.createUser(user, password);
            res.addProperty("status", "success");
        }

        out.write(GSON.toJson(res));
    }
}
