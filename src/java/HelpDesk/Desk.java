package HelpDesk;

import Authentication.Authenticator;
import Utility.CookieGetter;
import Utility.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Desk", urlPatterns = {"/Desk"})
public class Desk extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String type = request.getParameter("type");
        String id = Authenticator.getUser(CookieGetter.getCookieValue("token", request));

        if (type.equals("enter queue")) {
            String queueName = request.getParameter("queueName");
            QueueManager.addUser(id, queueName);
            out.write(gson.toJson(QueueManager.getUser(id)));
        }

        if (type.equals("check status")) {
            out.write(gson.toJson(QueueManager.getUser(id)));
        }

        if (type.equals("get messages")) {
            ArrayList<Message> messages = ChatManager.getMessages(id);
            out.write(gson.toJson(messages, new TypeToken<ArrayList<Message>>() {
            }.getType()));
        }

        if (type.equals("send message")) {
            ChatManager.addMessage(id, request.getParameter("message"));
        }

        if (type.equals("message ammount")) {
            ArrayList<Message> messages = ChatManager.getMessages(id);
            out.print(messages.size());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
