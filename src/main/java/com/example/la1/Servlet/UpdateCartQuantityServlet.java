package com.example.la1.Servlet;

import com.example.la1.Database.AddItemCartDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
public class UpdateCartQuantityServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        synchronized (session) {
            String email = (String) session.getAttribute("user");

            if (email == null) {
                request.getRequestDispatcher("login_user").forward(request, response);
            }
            Integer cart_id = Integer.parseInt(request.getParameter("cartId"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            AddItemCartDatabase.UpdateCartQuantity(email, cart_id, quantity);

            request.getRequestDispatcher("cart").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
