package hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JDBC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Zishan@2320";

    public static boolean registerUser(String firstName, String lastName, String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Check if the username is already in use
            if (isUsernameTaken(conn, username)) {
                return false; // Registration failed due to username conflict
            }

            // If the username is available, insert the new user into the database
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, username);
                stmt.setString(4, password);

                int rowsInserted = stmt.executeUpdate();

                return rowsInserted > 0; // Registration is successful if at least one row is inserted
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false for any errors
        }
    }

    private static boolean isUsernameTaken(Connection conn, String username) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT username FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet result = stmt.executeQuery()) {
                return result.next(); // If a result is found, the username is taken
            }
        }
    }
}
