package com.example.songxuanyi.GenShinBBS.servlet;

import com.example.songxuanyi.GenShinBBS.model.User;
import com.example.songxuanyi.GenShinBBS.util.Database;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/Login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Database db = new Database();
        JsonObject jsonObject = new JsonObject();
        User user = db.login(username, password);
        if (user != null) {
            jsonObject.addProperty("status", "Success");
            session.setAttribute("user", user);
        } else {
            jsonObject.addProperty("status", "Failed");
        }
        out.print(jsonObject);
    }
}
