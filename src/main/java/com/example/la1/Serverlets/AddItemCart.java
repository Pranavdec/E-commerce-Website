package com.example.la1.Serverlets;

import com.example.la1.Database.AddItemCartDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddItemCart extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        synchronized (session) {
            String email = (String) session.getAttribute("user");
            Integer item_id = Integer.parseInt(request.getParameter("item_id"));
            Integer quantity = 1;
            if (!AddItemCartDatabase.IsItemPresent(email, item_id)){
                AddItemCartDatabase.Query(email, item_id, quantity);
            }
            request.getRequestDispatcher("redirect.jsp").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
