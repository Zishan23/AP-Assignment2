package hellofx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class LoginUI {
    public static void displayLoginUI(Stage primaryStage) {
        // Create the login form
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Label errorLabel = new Label("");

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(new Label("Login"), usernameField, passwordField, loginButton, errorLabel);

        // Set up the scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handler for the login button
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            boolean loginSuccessful = UserAuthentication.login(username, password);

            if (loginSuccessful) {
                UserDashboard.displayUserDashboard(primaryStage, username);
            } else {
                errorLabel.setText("Login failed. Please try again.");
            }
        });
    }
}