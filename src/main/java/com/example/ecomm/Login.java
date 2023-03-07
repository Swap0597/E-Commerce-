package com.example.ecomm;

import java.math.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static byte[] getSha(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getEncryptedPassword(String password) {
        try {
            BigInteger num = new BigInteger(getSha(password));
            StringBuilder haxString = new StringBuilder(num.toString(16));
            return haxString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer customerLogin(String userEmail, String password) {
        //select * from customer where email = 'angad@gmail.com' and password = 'abc';
        String encryptedPass = getEncryptedPassword(password);
        String query = "SELECT * FROM customer WHERE email = '" + userEmail + "' and password = '" + encryptedPass + "'";
        DatabaseConnection dbConn = new DatabaseConnection();
        try {
            ResultSet rs = dbConn.getQueryTable(query);

            if (rs != null && rs.next()) {
                return new Customer(
                        rs.getInt("cid"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
