package com.example.la1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class LoginUserDatabase {
    public static Properties getDbProperties(){
        Properties prop = new Properties();

        ClassLoader classLoader = RegisterUserDatabase.class.getClassLoader();

        try(InputStream inputStream = classLoader.getResourceAsStream("db.properties")) {
            prop.load(inputStream);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return  prop;
    }

    public static String Query(String email, String user_password) {
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
                    String password_database = rs.getString("password");
                    if (password_database.equals(user_password)) {
                        return "Success";
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
