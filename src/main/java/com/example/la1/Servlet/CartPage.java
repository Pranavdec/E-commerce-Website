package com.example.la1.Servlet;

import com.example.la1.Database.AddItemCartDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CartPage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        synchronized (session) {
            String email = (String) session.getAttribute("user");
            if (email == null) {
                request.getRequestDispatcher("login_user").forward(request, response);
            }
        }
        CreatePage(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    private static void CreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("user");
        List<Map<String, Object>> dataList = AddItemCartDatabase.GetData(email);
        StringBuilder cartHtml = new StringBuilder();
        assert dataList != null;
        for (Map<String, Object> row : dataList) {
            String itemName = (String) row.get("item_name");
            Integer cartId = (Integer) row.get("cart_id");
            String imagePath = (String) row.get("image_path");
            String description = (String) row.get("description");
            BigDecimal price = (BigDecimal) row.get("price");
            Integer quantity = (Integer) row.get("quantity");


            cartHtml.append("<div class=\"col-lg-4 col-md-6 col-sm-12\" style=\"margin-bottom: 20px;\">");
            cartHtml.append("<div class=\"card\">");
            cartHtml.append("<div class=\"image-wrapper\">");
            cartHtml.append("<img class=\"card-img-top my-image \" src=\"").append(imagePath).append("\" alt=\"Card image cap\" width=\"90%\" height=\"90%\">");
            cartHtml.append("</div>");
            cartHtml.append("<div class=\"card-body\">");
            cartHtml.append("<h5 class=\"card-title\">").append(itemName).append("</h5>");
            cartHtml.append("<p class=\"card-text\">").append(description).append("</p>");
            cartHtml.append("<p class=\"card-text\">Rupees: ").append(price).append("</p>");

            cartHtml.append("<form action=\"UpdateCartQuantityServlet\" method=\"get\">");
            cartHtml.append("<input type=\"hidden\" name=\"cartId\" value=\"").append(cartId).append("\">");
            cartHtml.append("<label for=\"quantity-").append(cartId).append("\"  style=\"margin-right: 10px\">Quantity: </label>");
            cartHtml.append("<input id=\"quantity-").append(cartId).append("\" type=\"number\" name=\"quantity\" min=\"1\" value=\"").append(quantity).append("\">");
            cartHtml.append("<input type=\"submit\" value=\"Save\" class=\"btn btn-primary\">");
            cartHtml.append("</form><br>");
            cartHtml.append("<form action=\"DeleteItemServlet\" method=\"post\" id=\"deleteForm-").append(cartId).append("\">");
            cartHtml.append("<input type=\"hidden\" name=\"cartId\" value=\"").append(cartId).append("\">");
            cartHtml.append("<input type=\"submit\" value=\"Delete\" class=\"btn btn-danger\">");
            cartHtml.append("</form>");

            cartHtml.append("</div></div>");
            cartHtml.append("</div>");
        }

        request.setAttribute("cartHtml", cartHtml.toString());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }


}
