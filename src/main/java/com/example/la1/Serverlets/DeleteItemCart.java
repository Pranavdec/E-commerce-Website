package com.example.la1.Serverlets;

import com.example.la1.Database.AddItemCartDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteItemCart extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DeleteItemCart");
        String cart_id = request.getParameter("cartId");
        AddItemCartDatabase.DeleteCart(cart_id);
        //send a get request to cart servlet
        request.getRequestDispatcher("cart").forward(request, response);
    }
}
