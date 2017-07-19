package Procedures;

import Authentication.TokenManager;
import Database.MessagesDB;
import Utility.CookieGetter;
import Utility.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

@WebServlet(name = "Access", urlPatterns = {"/Access"})
public class Access extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String req = request.getParameter("type");
        
        Gson gson = new Gson();
        
        if (req != null) {
            if (req.equals("user")) {
                out.print(TokenManager.getUserFromToken(CookieGetter.getCookieValue("token", request)));
                System.out.println("user request");
            }
            
            if (req.equals("messages")) {
                response.setContentType("application/json");
                ArrayList<Message> messages = MessagesDB.getMessages();
                out.write(gson.toJson(messages, new TypeToken<ArrayList<Message>>(){}.getType()));
            }
            
            if (req.equals("messageAmmount")) {
                out.print(MessagesDB.getLastMessageID() + 1);
            }
            
            if (req.equals("send")){
                String user = TokenManager.getUserFromToken(CookieGetter.getCookieValue("token", request));
                MessagesDB.addMessage(user, request.getParameter("message"));
            }
        } else System.out.println("Bad type");
    }

}
