package Procedures;

import Authentication.Authenticator;
import Database.UsersDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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

        String status;

        if (UsersDB.userExists(user)) {
            status = "existing user";
        } else if (password.length() < 4) {
            status = "short password";
        } else {
            Authenticator.createUser(user, password);
            status = ("success");
        }

        out.write("{\"status\": \"" + status + "\"}");
    }
}
