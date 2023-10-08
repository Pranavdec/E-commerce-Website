package com.example.la1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.Properties;

public class IndexPage {
    public static void CreateCards(HttpServletRequest request, HttpServletResponse response){
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM Item\n" +
                    "ORDER BY RAND()\n" +
                    "LIMIT 10;\n";

            try (Statement st = con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                StringBuilder cardsHtml = new StringBuilder();
                while (rs.next()) {
                    cardsHtml.append("<div class=\"col-lg-4 col-md-6 col-sm-12\" style=\"margin-bottom: 20px;\">");
                    cardsHtml.append("<div class=\"card\">");
                    cardsHtml.append("<img class=\"card-img-top\" src=\"").append(rs.getString("image_path")).append("\" alt=\"Card image cap\">");
                    cardsHtml.append("<div class=\"card-body\">");
                    cardsHtml.append("<h5 class=\"card-title\">").append(rs.getString("item_name")).append("</h5>");
                    cardsHtml.append("<p class=\"card-text\">").append(rs.getString("description")).append("</p>");
                    cardsHtml.append("<a href=\"#\" class=\"btn btn-primary\">Add to Cart</a>");
                    cardsHtml.append("</div></div>");
                    cardsHtml.append("</div>");
                }
                request.setAttribute("cardsHtml", cardsHtml.toString());

                request.getRequestDispatcher("index.jsp").forward(request, response);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
