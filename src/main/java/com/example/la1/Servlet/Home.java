package com.example.la1.Servlet;

import com.example.la1.IndexPage;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Home extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        IndexPage.CreateCards(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String nItems = request.getParameter("itemsPerPage");
        HttpSession session = request.getSession();
        session.setAttribute("itemsPerPage", nItems);
        IndexPage.CreateCards(request, response);
    }
}
