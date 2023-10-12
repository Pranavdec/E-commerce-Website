package com.example.la1;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Home extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        IndexPage.CreateCards(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        IndexPage.CreateCards(request, response);
    }
}
