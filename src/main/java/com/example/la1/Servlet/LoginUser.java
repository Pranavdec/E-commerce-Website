package com.example.la1.Servlet;

import com.example.la1.IndexPage;
import com.example.la1.Database.LoginUserDatabase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

public class LoginUser extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        request.setAttribute("error", "");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        String query = LoginUserDatabase.Query(email, password);
        if (query.equals("Success")) {
            HttpSession session = request.getSession();
            synchronized(session) {
                session.setAttribute("user", email);
                response.sendRedirect("home");
            }

        } else {
            request.setAttribute("error", query);
            dispatcher.forward(request, response);
        }

    }
}
