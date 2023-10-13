package com.example.la1.Servlet;

import com.example.la1.Database.BuyDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyItems extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("user");
        String homeUrl = "home";

        if (email == null) {
            response.sendRedirect("login_user");
            return;
        }

        try {
            HashMap<String, Object> result = BuyDatabase.Query(email);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> successful = (List<Map<String, Object>>) result.get("successful");
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> unsuccessful = (List<Map<String, Object>>) result.get("unsuccessful");

            double totalCost = 0;
            for (Map<String, Object> row : successful) {
                totalCost += (Integer)row.get("quantity") * (Double) row.get("price");
            }

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Buy Items</title>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("</head><body>");

            out.println("<div class='container'>");


            out.println("<a href='" + homeUrl + "' class='btn btn-primary mt-3'>Home</a>");
            out.println("<h1 class='mt-4'>Successful Items</h1>");


            out.println("<table class='table'><thead class='thead-dark'><tr><th>Item Name</th><th>Quantity</th><th>Price</th></tr></thead><tbody>");
            for (Map<String, Object> row : successful) {
                out.println("<tr>");
                out.println("<td>" + row.get("item_name") + "</td>");
                out.println("<td>" + row.get("quantity") + "</td>");
                out.println("<td>" + row.get("price") + "</td>");
                out.println("</tr>");
            }
            out.println("</tbody></table>");


            out.println("<h3 class='text-right'>Total Cost(Rupees): " + totalCost + "</h3>");


            out.println("<h1 class='mt-4'>Unsuccessful Items</h1>");
            out.println("<table class='table'><thead class='thead-dark'><tr><th>Item Name</th><th>Quantity</th></tr></thead><tbody>");
            for(Map<String, Object> row : unsuccessful){
                out.println("<tr>");
                out.println("<td>" + row.get("item_name") + "</td>");
                out.println("<td>" + row.get("quantity") + "</td>");
                out.println("</tr>");
            }
            out.println("</tbody></table>");

            out.println("</div>");

            out.println("</body></html>");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

