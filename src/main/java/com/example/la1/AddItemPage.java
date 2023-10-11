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

@MultipartConfig
public class AddItemPage extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("additem.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String company_name = (String) session.getAttribute("Shopkeeper");
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

        ShopkeeperDatabase.AddItem(item_name, item_price, item_quantity, item_description, item_category, item_image_path, company_name);

        doGet(request, response);
    }

}
