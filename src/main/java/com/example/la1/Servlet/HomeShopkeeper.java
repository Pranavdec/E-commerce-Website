package com.example.la1.Servlet;

import com.example.la1.ShopkeeperPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class HomeShopkeeper extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String email;
            synchronized (session) {
                email = (String) session.getAttribute("Shopkeeper");
                if (email == null) {
                    request.getRequestDispatcher("login_user").forward(request, response);
                }
            }
            try {
                ShopkeeperPage.Home(request, response);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
}
