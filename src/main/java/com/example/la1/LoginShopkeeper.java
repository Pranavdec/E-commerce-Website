package com.example.la1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

public class LoginShopkeeper extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        request.setAttribute("error", "");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        String query = LoginShopkeeperDatabase.Query(email, password);
        if (query.equals("Incorrect password.")) {
            request.setAttribute("error", query);
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Shopkeeper_email", email);
            session.setAttribute("Shopkeeper", query);
            try {
                ShopkeeperPage.Home(request, response);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
