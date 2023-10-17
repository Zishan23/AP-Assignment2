package hellofx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class UserDashboard {
    public static void displayUserDashboard(Stage primaryStage, String username) {
        // Create the user dashboard
        primaryStage.setTitle("Welcome, " + username);

        // Create UI components
        Button logoutButton = new Button("Logout");
        Label dashboardLabel = new Label("User Dashboard");

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(dashboardLabel, logoutButton);

        // Set up the scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);

        // Event handler for the logout button
        logoutButton.setOnAction(event -> {
            LoginUI.displayLoginUI(primaryStage);
        });
    }
}