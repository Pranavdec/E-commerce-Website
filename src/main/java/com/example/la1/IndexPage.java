package com.example.la1;

import com.example.la1.Database.LoginUserDatabase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.Properties;

public class IndexPage {
    public static void CreateCards(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String items_per_page;
        synchronized (session) {
            items_per_page = (String) session.getAttribute("itemsPerPage");
        }
        if(items_per_page == null){
            items_per_page = "10";
        }
        Properties props = LoginUserDatabase.getDbProperties();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = """
                    SELECT * FROM Item
                    ORDER BY RAND()
                    LIMIT ?;
                    """;

            int items_per_page_int = Integer.parseInt(items_per_page);
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, items_per_page_int);
                ResultSet rs = pst.executeQuery();

                StringBuilder cardsHtml = new StringBuilder();
                while (rs.next()) {
                    String item_id = rs.getString("item_id");
                    cardsHtml.append("<div class=\"col-lg-4 col-md-6 col-sm-12\" style=\"margin-bottom: 20px;\">");
                    cardsHtml.append("<div class=\"card\">");
                    cardsHtml.append("<div class=\"image-wrapper\">");
                    cardsHtml.append("<img class=\"card-img-top my-image \" src=\"").append(rs.getString("image_path")).append("\" alt=\"Card image cap\" width=\"90%\" height\"90%\">");
                    cardsHtml.append("</div>");
                    cardsHtml.append("<div class=\"card-body\">");
                    cardsHtml.append("<h5 class=\"card-title\">").append(rs.getString("item_name")).append("</h5>");
                    cardsHtml.append("<p class=\"card-text\">").append(rs.getString("description")).append("</p>");
                    cardsHtml.append("<p class=\"card-text\">").append(rs.getString("price")).append("</p>");
                    cardsHtml.append("<form action=\"AddItemCartServlet\" method=\"post\">");
                    cardsHtml.append("<input type=\"hidden\" name=\"item_id\" value=\"").append(item_id).append("\">");
                    cardsHtml.append("<input type=\"submit\" value=\"Add to Cart\" class=\"btn btn-primary\">");
                    cardsHtml.append("</form>");
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
