package com.example.la1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class RegisterUserDatabase {
    private static Properties getDbProperties(){
        Properties prop = new Properties();

        ClassLoader classLoader = RegisterUserDatabase.class.getClassLoader();

        try(InputStream inputStream = classLoader.getResourceAsStream("db.properties")) {
            prop.load(inputStream);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return  prop;
    }

    public static String QueryEmail(String email) {
        Properties props = getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM User WHERE email = ?";

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

    public static void InsertUser(String email, String password, String contact, String address) {
        Properties props = getDbProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String passwd = props.getProperty("db.passwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, passwd);

            String query = "INSERT INTO User VALUES (?, ?, ?, ?)";

            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, password);
                st.setString(2, email);
                st.setString(3, contact);
                st.setString(4, address);

                st.executeUpdate();
                System.out.println("User inserted.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
