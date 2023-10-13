package com.example.la1.Servlet;

import com.example.la1.Database.RegisterUserDatabase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

public class RegisterUser extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        request.setAttribute("error", "");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password_confirm");

        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        if (password.equals(password2)) {
            String query = RegisterUserDatabase.QueryEmail(email);
            System.out.println(query);

            if (query.equals("Email")) {
                RegisterUserDatabase.InsertUser(email, password, contact, address);
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("login.jsp");
                request.setAttribute("error", "");
                dispatcher2.forward(request, response);
            } else {
                request.setAttribute("error", query);
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("error", "Passwords do not match.");
            dispatcher.forward(request, response);
        }
    }

}
