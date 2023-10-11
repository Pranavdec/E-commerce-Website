package com.example.la1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.*;


public class UpdateItemPage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String selectedItem = request.getParameter("selectedItem");
        if (selectedItem != null && !selectedItem.isEmpty()) {
            Dictionary<String, String> itemDetails = ShopkeeperDatabase.GetItemDetails(selectedItem);
            request.setAttribute("itemDetails", itemDetails);
        }

        HttpSession session = request.getSession();
        String company_name = (String) session.getAttribute("Shopkeeper");
        List<String> itemNames = ShopkeeperDatabase.GetItems(company_name);
        request.setAttribute("itemNames", itemNames);

        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateItem.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String item_id = request.getParameter("item_id");
        String item_name = request.getParameter("item_name");
        String item_price = request.getParameter("item_price");
        String item_quantity = request.getParameter("item_quantity");
        String item_description = request.getParameter("item_description");
        String item_category = request.getParameter("item_category");

        ShopkeeperDatabase.UpdateDetails(item_name, item_price, item_quantity, item_description, item_category, item_id);

        doGet(request, response);

    }
}
