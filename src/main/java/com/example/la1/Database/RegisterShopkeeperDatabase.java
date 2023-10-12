package com.example.la1.Database;

import com.example.la1.Database.LoginUserDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class RegisterShopkeeperDatabase {

    public static String QueryEmail(String email) {
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
                    return "Email already exists in the database.";
                } else {
                    return "Email";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Error: Something went wrong with the query.";
    }

    public static String InsertShopkeeper(String email, String password, String contact, String address, String company_name) {
        Properties props = LoginUserDatabase.getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String passwd = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, passwd);

            String query = "INSERT INTO shopkeeper VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, password);
                st.setString(2, email);
                st.setString(3, contact);
                st.setString(4, address);
                st.setString(5, company_name);

                st.executeUpdate();
                return  "Success";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
