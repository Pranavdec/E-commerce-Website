package com.example.la1.Servlet;

import com.example.la1.IndexPage;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Home extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        IndexPage.CreateCards(request, response,"");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String nItems = request.getParameter("itemsPerPage");
        String currentPage = request.getParameter("currentPage");
        String search = request.getParameter("searchQuery");
        System.out.println(search);
        HttpSession session = request.getSession();
        session.setAttribute("itemsPerPage", nItems);
        session.setAttribute("currentPage", currentPage);
        IndexPage.CreateCards(request, response, search);
    }
}
