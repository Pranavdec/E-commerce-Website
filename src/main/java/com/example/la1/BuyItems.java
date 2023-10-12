package com.example.la1;

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
            return;
        }

        try {
            HashMap<String, Object> result = BuyDatabase.Query(email);

            List<Map<String, Integer>> successful = (List<Map<String, Integer>>) result.get("successful");
            List<Map<String, Integer>> unsuccessful = (List<Map<String, Integer>>) result.get("unsuccessful");


            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<a href=\"" + homeUrl + "\">Home</a>");
            out.println("<h1>Successful Items</h1>");
            out.println("<table border=\"1\"><tr><th>Item ID</th><th>Quantity</th></tr>");
            for (Map<String, Integer> row : successful) {
                out.println("<tr>");
                out.println("<td>" + row.get("item_id") + "</td>");
                out.println("<td>" + row.get("quantity") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("<h1>Unsuccessful Items</h1>");
            out.println("<table border=\"1\"><tr><th>Item ID</th><th>Quantity</th></tr>");
            for(Map<String, Integer> row : unsuccessful){
                out.println("<tr>");
                out.println("<td>" + row.get("item_id") + "</td>");
                out.println("<td>" + row.get("quantity") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("</body></html>");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
