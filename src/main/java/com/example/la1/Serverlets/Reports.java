package com.example.la1.Serverlets;

import com.example.la1.Database.ShopkeeperDatabase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class Reports extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("reports.jsp");
        HttpSession session = request.getSession();
        synchronized (session) {
            String company_name = (String) session.getAttribute("Shopkeeper");
            try {
                List<String> users = ShopkeeperDatabase.GetUsers(company_name);
                request.setAttribute("users", users);
                dispatcher.forward(request, response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        synchronized (session){
            String company_name = (String) session.getAttribute("Shopkeeper");
            String reportType = request.getParameter("reportType");
            List<String> users = ShopkeeperDatabase.GetUsers(company_name);
            request.setAttribute("users", users);

            if (reportType.equals("1")){
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
            else{
                String user_email = request.getParameter("userSelect");
                String content = ShopkeeperDatabase.GetReport1(company_name, user_email);
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
}
