package com.example.la1.Servlet;

import com.example.la1.Database.AddItemCartDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DeleteItemCart extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email;
        synchronized (session) {
            email = (String) session.getAttribute("user");
            if (email == null) {
                request.getRequestDispatcher("login_user").forward(request, response);
            }
        }
        String cart_id = request.getParameter("cartId");
        AddItemCartDatabase.DeleteCart(cart_id);
        request.getRequestDispatcher("cart").forward(request, response);
    }
}
