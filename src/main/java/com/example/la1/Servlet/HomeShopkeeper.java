package com.example.la1.Servlet;

import com.example.la1.ShopkeeperPage;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeShopkeeper extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) {
            try {
                ShopkeeperPage.Home(request, response);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
}
