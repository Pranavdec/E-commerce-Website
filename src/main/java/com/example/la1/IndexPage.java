package com.example.la1;

import com.example.la1.Database.LoginUserDatabase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.Properties;

public class IndexPage {
    public static void CreateCards(HttpServletRequest request, HttpServletResponse response, String search) {
        HttpSession session = request.getSession();
        String items_per_page;
        String currentPageString;
        synchronized (session) {
            items_per_page = (String) session.getAttribute("itemsPerPage");
            currentPageString = (String) session.getAttribute("currentPage");
        }
        if(items_per_page == null){
            items_per_page = "10";
        }
        if(currentPageString == null){
            currentPageString = "1";
        }
        Properties props = LoginUserDatabase.getDbProperties();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        int items_per_page_int = Integer.parseInt(items_per_page);
        int currentPageInt = Integer.parseInt(currentPageString);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String countQuery;
            String query;
            if (search != null && !search.isEmpty()) {
                countQuery = "SELECT COUNT(*) FROM Item WHERE item_name LIKE ?;";
                query = """
                        SELECT * FROM Item
                        WHERE item_name LIKE ?
                        LIMIT ? OFFSET ?;
                        """;

            } else {
                countQuery = "SELECT COUNT(*) FROM Item;";
                query = """
                        SELECT * FROM Item
                        LIMIT ? OFFSET ?;
                        """;
            }

            int offset = (currentPageInt - 1) * items_per_page_int;
            try (PreparedStatement pstCount = con.prepareStatement(countQuery);
                 PreparedStatement pst = con.prepareStatement(query)) {

                if (search != null && !search.isEmpty()) {
                    String searchPattern = "%" + search + "%";
                    pstCount.setString(1, searchPattern);
                    pst.setString(1, searchPattern);
                    pst.setInt(2, items_per_page_int);
                    pst.setInt(3, offset);
                } else {
                    pst.setInt(1, items_per_page_int);
                    pst.setInt(2, offset);
                }

                try (ResultSet rsCount = pstCount.executeQuery()) {
                    if (rsCount.next()) {
                        int totalCount = rsCount.getInt(1);
                        int totalPages = (int) Math.ceil((double) totalCount / items_per_page_int);
                        request.setAttribute("totalPages", totalPages);
                    }
                }

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
                    cardsHtml.append("<p class=\"card-text\">Rupees: ").append(rs.getString("price")).append("</p>");
                    cardsHtml.append("<form action=\"AddItemCartServlet\" method=\"post\">");
                    cardsHtml.append("<input type=\"hidden\" name=\"item_id\" value=\"").append(item_id).append("\">");
                    cardsHtml.append("<label for=\"quantity-").append(item_id).append("\">Quantity: </label>");
                    cardsHtml.append("<input id=\"quantity-").append(item_id).append("\" type=\"number\" name=\"quantity\" min=\"1\" value=\"1\" required>");
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
