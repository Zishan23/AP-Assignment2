package hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRegistration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JDBC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Zishan@2320";

    // User Registration
    public static boolean registerUser(String firstName, String lastName, String username, String password) {
    	String insertUserSql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(insertUserSql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // User Login
    public static User loginUser(String username, String password) {
    	String selectUserSql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(selectUserSql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("UserID");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    return new User(userId, firstName, lastName, username, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Post Management
    public static boolean addPost(int userId, String content, int likes, int shares) {
        String insertPostSql = "INSERT INTO Posts (UserID, Content, Likes, Shares) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(insertPostSql)) {
            statement.setInt(1, userId);
            statement.setString(2, content);
            statement.setInt(3, likes);
            statement.setInt(4, shares);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Post> getPostsByUserId(int userId) {
        String selectPostsSql = "SELECT * FROM Posts WHERE UserID = ?";
        List<Post> posts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(selectPostsSql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int postId = resultSet.getInt("PostID");
                    String content = resultSet.getString("Content");
                    int likes = resultSet.getInt("Likes");
                    int shares = resultSet.getInt("Shares");
                    posts.add(new Post(postId, userId, content, likes, shares));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static boolean deletePost(int postId) {
        String deletePostSql = "DELETE FROM Posts WHERE PostID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(deletePostSql)) {
            statement.setInt(1, postId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateUserProfile(String username, String newFirstName, String newLastName, String newUsername, String newPassword) {
        String selectUserIdSql = "SELECT UserID FROM Users WHERE username = ?";
        String updateUserProfileSql = "UPDATE Users SET first_name = ?, last_name = ?, username = ?, password = ? WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement selectStatement = conn.prepareStatement(selectUserIdSql);
             PreparedStatement updateStatement = conn.prepareStatement(updateUserProfileSql)) {

            // First, retrieve the UserID based on the username
            selectStatement.setString(1, username);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("UserID");

                    // Now, update the user's profile using the retrieved UserID
                    updateStatement.setString(1, newFirstName);
                    updateStatement.setString(2, newLastName);
                    updateStatement.setString(3, newUsername);
                    updateStatement.setString(4, newPassword);
                    updateStatement.setInt(5, userId);

                    int rowsUpdated = updateStatement.executeUpdate();
                    return rowsUpdated > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
