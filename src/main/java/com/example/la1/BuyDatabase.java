package com.example.la1;

import java.sql.*;
import java.util.*;

public class BuyDatabase {
    public static HashMap<String, Object> Query(String email) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        Connection con = null;

        List<Map<String, Integer>> successful = new ArrayList<>();
        List<Map<String, Integer>> unsuccessful = new ArrayList<>();

        HashMap<String, Object> result = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);

            String query1 = "SELECT * FROM Cart WHERE user_email = ?";
            PreparedStatement st1 = con.prepareStatement(query1);
            st1.setString(1, email);
            ResultSet rs = st1.executeQuery();

            while (rs.next()) {
                int cart_id = rs.getInt("cart_id");
                int item_id = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");

                String query2 = "SELECT quantity, company_name FROM Item WHERE item_id = ?";
                PreparedStatement st2 = con.prepareStatement(query2);
                st2.setInt(1, item_id);
                ResultSet rs2 = st2.executeQuery();

                if (rs2.next()) {
                    int available_quantity = rs2.getInt("quantity");
                    if (available_quantity >= quantity) {
                        String company_name = rs2.getString("company_name");
                        String query3 = "INSERT INTO Transaction(user_email, item_id, quantity, company_name) VALUES(?, ?, ?, ?)";
                        PreparedStatement st3 = con.prepareStatement(query3);
                        st3.setString(1, email);
                        st3.setInt(2, item_id);
                        st3.setInt(3, quantity);
                        st3.setString(4, company_name);
                        st3.executeUpdate();

                        String query4 = "DELETE FROM Cart WHERE cart_id = ?";
                        PreparedStatement st4 = con.prepareStatement(query4);
                        st4.setInt(1, cart_id);
                        st4.executeUpdate();

                        String query5 = "Update Item SET quantity = quantity - ? WHERE item_id = ?";
                        PreparedStatement st5 = con.prepareStatement(query5);
                        st5.setInt(1, quantity);
                        st5.setInt(2, item_id);
                        st5.executeUpdate();

                        Map<String, Integer> successEntry = new HashMap<>();
                        successEntry.put("item_id", item_id);
                        successEntry.put("quantity", quantity);
                        successful.add(successEntry);
                    } else {
                        Map<String, Integer> unsuccessEntry = new HashMap<>();
                        unsuccessEntry.put("item_id", item_id);
                        unsuccessEntry.put("quantity", quantity);
                        unsuccessful.add(unsuccessEntry);
                    }
                }
            }

            con.commit();
            result.put("successful", successful);
            result.put("unsuccessful", unsuccessful);

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
            System.out.println(e.getMessage());
        }

        return result;
    }
}
