package com.example.la1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
                    return item_details;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void UpdateDetails(String ItemName,String Price, String Quantity, String Description, String Category, String item_id) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "UPDATE Item SET item_name = ?, price = ?, quantity = ?, description = ?, category = ? WHERE item_id = ?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, ItemName);
                st.setString(2, Price);
                st.setString(3, Quantity);
                st.setString(4, Description);
                st.setString(5, Category);
                st.setString(7, item_id);

                st.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String AddItem(String ItemName,String Price, String Quantity, String Description, String Category,String ImagePath, HttpServletRequest request){

        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            HttpSession session = request.getSession();
            String company_name = (String) session.getAttribute("Shopkeeper");

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
                return "Success";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
