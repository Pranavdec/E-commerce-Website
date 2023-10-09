package com.example.la1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class LoginShopkeeperDatabase {

    public static String Query(String email, String user_password) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM shopkeeper WHERE email = ?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, email);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    String password_database = rs.getString("password");
                    if (password_database.equals(user_password)) {
                        return rs.getString("Companyname");
                    } else {
                        return "Incorrect password.";
                    }
                } else {
                    return "Email does not exist in the database.";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Error: Something went wrong with the query.";
    }
}
