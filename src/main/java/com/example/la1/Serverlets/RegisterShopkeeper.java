package com.example.la1.Serverlets;

import com.example.la1.Database.RegisterShopkeeperDatabase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Objects;

public class RegisterShopkeeper extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        request.setAttribute("error", "");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String company_name = request.getParameter("company_name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password_confirm");

        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        if (password.equals(password2)) {
            String query = RegisterShopkeeperDatabase.QueryEmail(email);
            System.out.println(query);

            if (query.equals("Email")) {
                String error = RegisterShopkeeperDatabase.InsertShopkeeper(email, password, contact, address, company_name);
                if (!Objects.equals(error, "Success")){
                    request.setAttribute("error", "Company name already exists in the database.");
                    dispatcher.forward(request, response);
                }
                else{
                    RequestDispatcher dispatcher2 = request.getRequestDispatcher("login.jsp");
                    request.setAttribute("error", "");
                    dispatcher2.forward(request, response);
                }

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
