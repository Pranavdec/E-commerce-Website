package com.example.la1;

import java.sql.*;
import java.util.*;

public class AddItemCartDatabase {
    public static void Query(String email, Integer item_id, Integer quantity) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "INSERT INTO Cart (user_email, item_id, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, email);
                st.setInt(2, item_id);
                st.setInt(3, quantity);

                st.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Map<String, Object>> GetData(String email){
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        List<Map<String, Object>> dataList = new ArrayList<>();
        String query = "SELECT I.description,I.company_name,I.item_name,I.price,C.quantity,C.cart_id " +
                "FROM item AS I, cart AS C " +
                "WHERE I.item_id = C.item_id AND C.user_email = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement st = con.prepareStatement(query)) {

                st.setString(1, email);
                ResultSet rs = st.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>(columns);
                    for (int i = 1; i <= columns; ++i) {
                        row.put(md.getColumnName(i), rs.getObject(i));
                    }
                    dataList.add(row);
                }
            }
            return dataList;
        } catch (Exception e) {
            // Better error handling here
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void UpdateCartQuantity(String email, Integer cart_id, Integer quantity) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "UPDATE cart SET quantity = ? WHERE cart_id = ? AND user_email = ?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setInt(1, quantity);
                st.setInt(2, cart_id);
                st.setString(3, email);

                st.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
