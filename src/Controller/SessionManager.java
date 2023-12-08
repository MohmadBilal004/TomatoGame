package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Models.User;

public class SessionManager {

    private static User currentUser;
    private static Connection conn;
    private static PreparedStatement pst;
    private static String userId;
    private static boolean isLoggedIn;

    public static void setUserId(String email) {
        userId = email;
        isLoggedIn = true;
    }

    public static String getUserId() {
        return userId;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void logout() {
        userId = "No Email"; // Set to a default value
        isLoggedIn = false;
        currentUser = null;
    }

    public static void setConnection(Connection connection) {
        conn = connection;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean login(String email, String password) {
        if (conn == null) {
            System.out.println("Database connection is not set.");
            return false;
        }

        try {
            String query = "SELECT * FROM `users` WHERE `email` = ? AND `password` = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Retrieve the userName from the ResultSet
                String userName = rs.getString("userName");

                currentUser = new User(email, password, userName);
                setUserId(email);
                return true;
            }
        } catch (Exception ex) {
            // Handle the exception appropriately
        }

        return false;
    }

    public static Connection getConnection() {
        return conn;
    }
}
