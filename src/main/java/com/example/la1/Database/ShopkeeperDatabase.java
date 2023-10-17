package com.example.la1.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ShopkeeperDatabase {

    public static List<String> GetItems(String company_name) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            List<String> items = new ArrayList<>();
            items.add("Select Item");

            String query = "SELECT item_id From Item WHERE company_name = ?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, company_name);

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String item_id = rs.getString("item_id");
                    items.add(item_id);
                }
                return items;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Dictionary<String,String> GetItemDetails(String item_id) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "SELECT * From Item WHERE item_id = ?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, item_id);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    Dictionary<String,String> item_details = new Hashtable<>();
                    item_details.put("item_id", rs.getString("item_id"));
                    item_details.put("item_name", rs.getString("item_name"));
                    item_details.put("price", rs.getString("price"));
                    item_details.put("quantity", rs.getString("quantity"));
                    item_details.put("description", rs.getString("description"));
                    item_details.put("category", rs.getString("category"));
                    item_details.put("image_path", rs.getString("image_path"));
                    return item_details;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void UpdateDetails(String ItemName,String Price, String Quantity, String Description, String Category, String item_id,String image_path) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "UPDATE Item SET item_name = ?, price = ?, quantity = ?, description = ?, category = ?,image_path = ? WHERE item_id = ?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, ItemName);
                st.setString(2, Price);
                st.setString(3, Quantity);
                st.setString(4, Description);
                st.setString(5, Category);
                st.setString(7, item_id);
                st.setString(6, image_path);

                System.out.println(st);

                st.executeUpdate();
                System.out.println("Item Updated");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void AddItem(String ItemName,String Price, String Quantity, String Description, String Category,String ImagePath, String company_name){

        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);


            String query = "INSERT INTO Item (item_name, price, quantity, description, category, company_name, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, ItemName);
                st.setString(2, Price);
                st.setString(3, Quantity);
                st.setString(4, Description);
                st.setString(5, Category);
                st.setString(6, company_name);
                st.setString(7, ImagePath);

                st.executeUpdate();
                System.out.println("Item Added");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String GetReport(String company_name, String StartDate, String EndDate){
        String query = """
                SELECT
                    i.item_name,
                    SUM(t.quantity) AS total_quantity_sold
                FROM
                    Transaction t
                JOIN
                    Item i ON t.item_id = i.item_id
                JOIN
                    shopkeeper s ON t.company_name = s.Companyname
                WHERE
                    t.company_name = ?
                AND
                    t.time BETWEEN ? AND ?
                GROUP BY
                    i.item_name;
                                
                """;

        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, company_name);
            st.setString(2, StartDate);
            st.setString(3, EndDate);

            ResultSet rs = st.executeQuery();
            StringBuilder content = new StringBuilder();
            content.append("<table class=\"table table-striped\">");
            content.append("<thead>");
            content.append("<tr>");
            content.append("<th scope=\"col\">Item Name</th>");
            content.append("<th scope=\"col\">Quantity Sold</th>");
            content.append("</tr>");
            content.append("</thead>");
            content.append("<tbody>");
            while (rs.next()) {
                content.append("<tr>");
                content.append("<td>");
                content.append(rs.getString("item_name"));
                content.append("</td>");
                content.append("<td>");
                content.append(rs.getString("total_quantity_sold"));
                content.append("</td>");
                content.append("</tr>");
            }
            content.append("</tbody>");
            content.append("</table>");

            return content.toString();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String GetReport1(String company_name, String user_email){
        System.out.println(company_name);
        String query = """
                SELECT
                    i.item_name,
                    t.quantity,
                    t.user_email,
                    t.time
                FROM
                    Transaction t
                JOIN
                    Item i ON t.item_id = i.item_id
                WHERE
                    t.company_name = ?
                    AND
                    t.user_email = ?;
                                
                """;

        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, company_name);
            st.setString(2, user_email);

            ResultSet rs = st.executeQuery();
            StringBuilder content = new StringBuilder();
            content.append("<table class=\"table table-striped\">");
            content.append("<thead>");
            content.append("<tr>");
            content.append("<th scope=\"col\">User Email</th>");
            content.append("<th scope=\"col\">Item Name</th>");
            content.append("<th scope=\"col\">Quantity Sold</th>");
            content.append("<th scope=\"col\">Time</th>");
            content.append("</tr>");
            content.append("</thead>");
            content.append("<tbody>");
            while (rs.next()) {
                content.append("<tr>");
                content.append("<td>");
                content.append(rs.getString("user_email"));
                content.append("</td>");
                content.append("<td>");
                content.append(rs.getString("item_name"));
                content.append("</td>");
                content.append("<td>");
                content.append(rs.getString("quantity"));
                content.append("</td>");
                content.append("<td>");
                content.append(rs.getString("time"));
                content.append("</td>");
                content.append("</tr>");
            }
            content.append("</tbody>");
            content.append("</table>");

            System.out.println(content);

            return content.toString();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static List<String> GetUsers(String company_name){
        String query = "Select DISTINCT (user_email) from Transaction where company_name = ?";
        Properties props = LoginUserDatabase.getDbProperties();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, company_name);
            ResultSet rs = st.executeQuery();
            List<String> users = new ArrayList<>();
            while (rs.next()) {
                users.add(rs.getString("user_email"));
            }
            return users;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
