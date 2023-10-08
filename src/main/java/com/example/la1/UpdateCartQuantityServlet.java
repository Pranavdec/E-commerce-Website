package com.example.la1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
public class UpdateCartQuantityServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("user");
        Integer cart_id = Integer.parseInt(request.getParameter("cartId"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        AddItemCartDatabase.UpdateCartQuantity(email, cart_id, quantity);

        request.getRequestDispatcher("cart").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
