package hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

class RegistrationUI {
    public static void displayRegistrationUI(Stage primaryStage) {
        // Create the registration form
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Register");
        Label errorLabel = new Label("");

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            new Label("Registration"),
            firstNameField,
            lastNameField,
            usernameField,
            passwordField,
            registerButton,
            errorLabel
        );

        // Set up the scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);

        // Event handler for the register button
        registerButton.setOnAction(event -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Validate and save user data to the database
            if (isValidInput(firstName, lastName, username, password)) {
                // Create a User object for the registered user
                User registeredUser = new User(0, firstName, lastName, username, password);

                boolean registrationSuccessful = UserRegistration.registerUser(registeredUser);

                if (registrationSuccessful) {
                    // Pass the registered user object to the UserDashboard
                    UserDashboard.displayUserDashboard(primaryStage, registeredUser);
                } else {
                    errorLabel.setText("Registration failed. Please try again.");
                }
            } else {
                errorLabel.setText("Invalid input. Please check your data.");
            }
        });
    }

    private static boolean isValidInput(String firstName, String lastName, String username, String password) {
        // Add validation logic here (e.g., checking for empty fields, valid username, strong password, etc.)
        return !firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty();
    }
}
