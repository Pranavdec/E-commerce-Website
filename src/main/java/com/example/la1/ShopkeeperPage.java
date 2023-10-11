package com.example.la1;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Properties;
import java.sql.*;

public class ShopkeeperPage {

    public static void Home(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            HttpSession session = request.getSession();
            String company_name = (String) session.getAttribute("Shopkeeper");

            String query = "SELECT * From Item WHERE company_name = ?";
            try(PreparedStatement st = con.prepareStatement(query)){
                System.out.println(company_name);
                st.setString(1, company_name);
                ResultSet rs = st.executeQuery();
                StringBuilder itemstable = new StringBuilder();
                itemstable.append("<table border=\"1\">");
                itemstable.append("<tr><th>Item ID></th><th>Item Name</th><th>Item Price</th><th>Item Quantity</th><th>Item Company</th><th>Description</th><th>category</th></tr>");
                while(rs.next()){
                    itemstable.append("<tr>");
                    itemstable.append("<td>").append(rs.getString("item_id")).append("</td>");
                    itemstable.append("<td>").append(rs.getString("item_name")).append("</td>");
                    itemstable.append("<td>").append(rs.getString("price")).append("</td>");
                    itemstable.append("<td>").append(rs.getString("quantity")).append("</td>");
                    itemstable.append("<td>").append(rs.getString("company_name")).append("</td>");
                    itemstable.append("<td>").append(rs.getString("description")).append("</td>");
                    itemstable.append("<td>").append(rs.getString("category")).append("</td>");
                    itemstable.append("</tr>");
                }
                request.setAttribute("items_table", itemstable.toString());
                request.getRequestDispatcher("shopkeeper.jsp").forward(request, response);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
