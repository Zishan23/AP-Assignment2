package hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JDBC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Zishan@2320";

    public static boolean login(User user, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, user.getUsername()); // Use the username from the User object
            stmt.setString(2, password);

            try (ResultSet result = stmt.executeQuery()) {
                return result.next(); // User is authenticated if there is a match
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false for any errors
        }
    }
}
