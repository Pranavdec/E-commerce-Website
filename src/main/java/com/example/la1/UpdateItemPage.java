package com.example.la1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Dictionary;
import java.util.List;

@MultipartConfig
public class UpdateItemPage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String selectedItem = request.getParameter("selectedItem");
        if (selectedItem != null && !selectedItem.isEmpty()) {
            Dictionary<String, String> itemDetails = ShopkeeperDatabase.GetItemDetails(selectedItem);
            request.setAttribute("itemDetails", itemDetails);
        }

        HttpSession session = request.getSession();
        synchronized (session){
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

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String item_id = request.getParameter("itemId");
        String item_name = request.getParameter("itemName");
        String item_price = request.getParameter("itemPrice");
        String item_quantity = request.getParameter("itemQuantity");
        String item_description = request.getParameter("itemDescription");
        String item_category = request.getParameter("itemCategory");

        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + "images" + File.separator + fileName;

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, Paths.get(savePath), StandardCopyOption.REPLACE_EXISTING);
        }

        String item_image_path = "images" + File.separator + fileName;

        ShopkeeperDatabase.UpdateDetails(item_name, item_price, item_quantity, item_description, item_category, item_id,item_image_path);

        doGet(request, response);

    }
}
