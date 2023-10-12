package com.example.la1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Reports extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("reports.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        synchronized (session){
            String company_name = (String) session.getAttribute("Shopkeeper");
            String StartDate = request.getParameter("startDate");
            String EndDate = request.getParameter("endDate");

            String content = ShopkeeperDatabase.GetReport(company_name, StartDate, EndDate);
            request.setAttribute("content", content);

            RequestDispatcher dispatcher = request.getRequestDispatcher("reports.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
