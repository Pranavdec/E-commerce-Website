package com.example.la1.Database;

import java.sql.*;
import java.util.*;

public class BuyDatabase {
    public static HashMap<String, Object> Query(String email) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");

        Connection con = null;
        PreparedStatement st1 = null, st2 = null, st3 = null, st4 = null, st5 = null;
        ResultSet rs = null, rs2 = null;

        List<Map<String, Object>> successful = new ArrayList<>();
        List<Map<String, Object>> unsuccessful = new ArrayList<>();

        HashMap<String, Object> result = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);

            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String query1 = "SELECT * FROM Cart WHERE user_email = ? FOR UPDATE";
            st1 = con.prepareStatement(query1);
            st1.setString(1, email);
            rs = st1.executeQuery();

            while (rs.next()) {
                int cart_id = rs.getInt("cart_id");
                int item_id = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");

                String query2 = "SELECT quantity, company_name, price, item_name FROM Item WHERE item_id = ? FOR UPDATE";
                st2 = con.prepareStatement(query2);
                st2.setInt(1, item_id);
                rs2 = st2.executeQuery();

                if (rs2.next()) {
                    int available_quantity = rs2.getInt("quantity");
                    if (available_quantity >= quantity) {
                        String company_name = rs2.getString("company_name");
                        String item_name = rs2.getString("item_name");
                        double price = rs2.getDouble("price");

                        String query3 = "INSERT INTO Transaction(user_email, item_id, quantity, company_name) VALUES(?, ?, ?, ?)";
                        st3 = con.prepareStatement(query3);
                        st3.setString(1, email);
                        st3.setInt(2, item_id);
                        st3.setInt(3, quantity);
                        st3.setString(4, company_name);
                        st3.executeUpdate();

                        String query4 = "DELETE FROM Cart WHERE cart_id = ?";
                        st4 = con.prepareStatement(query4);
                        st4.setInt(1, cart_id);
                        st4.executeUpdate();

                        String query5 = "Update Item SET quantity = quantity - ? WHERE item_id = ?";
                        st5 = con.prepareStatement(query5);
                        st5.setInt(1, quantity);
                        st5.setInt(2, item_id);
                        st5.executeUpdate();

                        Map<String, Object> successEntry = new HashMap<>();
                        successEntry.put("item_id", item_id);
                        successEntry.put("item_name", item_name);
                        successEntry.put("quantity", quantity);
                        successEntry.put("price", price);
                        successful.add(successEntry);
                    } else {
                        Map<String, Object> unsuccessfulEntry = new HashMap<>();
                        unsuccessfulEntry.put("item_id", item_id);
                        unsuccessfulEntry.put("item_name", rs2.getString("item_name"));
                        unsuccessfulEntry.put("quantity", quantity);
                        unsuccessful.add(unsuccessfulEntry);
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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (st1 != null) {
                    st1.close();
                }
                if (st2 != null) {
                    st2.close();
                }
                if (st3 != null) {
                    st3.close();
                }
                if (st4 != null) {
                    st4.close();
                }
                if (st5 != null) {
                    st5.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return result;
    }
}
