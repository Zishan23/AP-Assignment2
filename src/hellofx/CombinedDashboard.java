package hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;

public class CombinedDashboard extends Application {
    private static User currentUser; // Assuming you have a User class

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the user dashboard
        primaryStage.setTitle("Welcome, " + currentUser.getFullName());

        // Create UI components
        Button logoutButton = new Button("Logout");
        Label dashboardLabel = new Label("User Dashboard");
        Button editProfileButton = new Button("Edit Profile");
        Button addPostButton = new Button("Add Post");
        Button retrievePostButton = new Button("Retrieve Post");
        Button removePostButton = new Button("Remove Post");
        Button topPostsButton = new Button("Top Posts");
        Button exportPostButton = new Button("Export Post");

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            dashboardLabel,
            new Label("Full Name: " + currentUser.getFullName()), // Display user's full name
            logoutButton,
            editProfileButton,
            addPostButton,
            retrievePostButton,
            removePostButton,
            topPostsButton,
            exportPostButton
        );

        // Set up the scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);

        // Event handlers for the buttons
        logoutButton.setOnAction(event -> {
            // Implement logout functionality here
            LoginUI.displayLoginUI(primaryStage);
        });

        editProfileButton.setOnAction(event -> {
            // Open a new window for editing the user's profile
            EditProfileUI.displayEditProfileUI(primaryStage, currentUser);
        });

        addPostButton.setOnAction(event -> {
            AddPostUI.displayAddPostUI(primaryStage, currentUser);
        });

        retrievePostButton.setOnAction(event -> {
            RetrievePostUI.displayRetrievePostUI(primaryStage, currentUser);
        });

        removePostButton.setOnAction(event -> {
            RemovePostUI.displayRemovePostUI(primaryStage, currentUser);
        });

        topPostsButton.setOnAction(event -> {
            TopPostsUI.displayTopPostsUI(primaryStage, currentUser);
        });

        exportPostButton.setOnAction(event -> {
            ExportPostUI.displayExportPostUI(primaryStage, currentUser);
        });

        primaryStage.show();
    }

    public static void displayCombinedDashboard(Stage primaryStage, User user) {
        currentUser = user;
        launch(primaryStage);
    }

    class LoginUI {
        public static void displayLoginUI(Stage primaryStage) {
            // Implement the login UI as needed
        }
    }

    class EditProfileUI {
        public static void displayEditProfileUI(Stage primaryStage, User user) {
            // Create the edit profile window components
            TextField firstNameField = new TextField(user.getFirstName());
            TextField lastNameField = a TextField(user.getLastName());
            TextField usernameField = new TextField(user.getUsername());
            TextField passwordField = new TextField(user.getPassword());
            Button saveButton = new Button("Save");

            // Layout
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("Edit Profile"),
                new Label("First Name:"),
                firstNameField,
                new Label("Last Name:"),
                lastNameField,
                new Label("Username:"),
                usernameField,
                new Label("Password:"),
                passwordField,
                saveButton
            );

            // Set up the scene
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            // Event handler for the save button
            saveButton.setOnAction(event -> {
                // Update the user's profile information in the database
                String newFirstName = firstNameField.getText();
                String newLastName = lastNameField.getText();
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();

                // You can call a method to update the user's profile in the database here
                // For example, UserRegistration.updateUserProfile(user, newFirstName, newLastName, newUsername, newPassword);

                // Close the edit profile window
                primaryStage.close();
            });
        }
    }

    class AddPostUI {
        public static void displayAddPostUI(Stage primaryStage, User user) {
            // Create the add post window components
            TextArea contentTextArea = new TextArea();
            contentTextArea.setWrapText(true);
            TextField likesField = new TextField("0");
            TextField sharesField = new TextField("0");
            Button saveButton = new Button("Save");

            // Layout
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("Add New Post"),
                new Label("Content:"),
                contentTextArea,
                new Label("# Likes:"),
                likesField,
                new Label("# Shares:"),
                sharesField,
                saveButton
            );

            // Set up the scene
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            // Event handler for the save button
            saveButton.setOnAction(event -> {
                // Get the post details
                String content = contentTextArea.getText();
                int likes = Integer.parseInt(likesField.getText());
                int shares = Integer.parseInt(sharesField.getText());

                // You can call a method to add the post to the database here
                // For example, PostManagement.addPost(user, content, likes, shares);

                // Close the add post window
                primaryStage.close();
            });
        }
    }

    class RetrievePostUI {
        public static void displayRetrievePostUI(Stage primaryStage, User user) {
            // Create the retrieve post window components
            TextField postIdField = new TextField();
            Button retrieveButton = new Button("Retrieve");
            TextArea postDetailsTextArea = new TextArea();
            postDetailsTextArea.setEditable(false);

            // Layout
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("Retrieve Post by ID"),
                new Label("Post ID:"),
                postIdField,
                retrieveButton,
                postDetailsTextArea
            );

            // Set up the scene
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            // Event handler for the retrieve button
            retrieveButton.setOnAction(event -> {
                // Retrieve the post by ID and display the details
                int postId = Integer.parseInt(postIdField.getText());

                // You can call a method to retrieve and display the post details from the database here
                // For example, PostManagement.retrievePost(user, postId, postDetailsTextArea);
            });
        }
    }

    class RemovePostUI {
        public static void displayRemovePostUI(Stage primaryStage, User user) {
            // Create the remove post window components
            TextField postIdField = new TextField();
            Button removeButton = new Button("Remove");

            // Layout
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("Remove Post by ID"),
                new Label("Post ID:"),
                postIdField,
                removeButton
            );

            // Set up the scene
            Scene scene = new Scene(layout, 400, 200);
            primaryStage.setScene(scene);

            // Event handler for the remove button
            removeButton.setOnAction(event -> {
                // Remove the post by ID
                int postId = Integer.parseInt(postIdField.getText());

                // You can call a method to remove the post from the database here
                // For example, PostManagement.removePost(user, postId);
            });
        }
    }

    class TopPostsUI {
        public static void displayTopPostsUI(Stage primaryStage, User user) {
            // Create the top posts window components
            TextField topNField = new TextField("10"); // Default to top 10
            Button showTopPostsButton = new Button("Show Top Posts");
            TextArea topPostsTextArea = new TextArea();
            topPostsTextArea.setEditable(false);

            // Layout
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("View Top Posts"),
                new Label("Show the Top N Posts (e.g., 10):"),
                topNField,
                showTopPostsButton,
                topPostsTextArea
            );

            // Set up the scene
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            // Event handler for the show top posts button
            showTopPostsButton.setOnAction(event -> {
                // Get the value of N (number of top posts to display)
                int topN = Integer.parseInt(topNField.getText());

                // You can call a method to retrieve and display the top N posts from the database here
                // For example, PostManagement.showTopPosts(user, topN, topPostsTextArea);
            });
        }
    }

    class ExportPostUI {
        public static void displayExportPostUI(Stage primaryStage, User user) {
            // Create the export post window components
            TextField postIdField = new TextField();
            Button exportButton = new Button("Export");
            Label folderLabel = new Label("Select a folder for export:");
            Button selectFolderButton = new Button("Select Folder");
            Label fileNameLabel = new Label("Enter a file name:");
            TextField fileNameField = new TextField();
            Label exportStatusLabel = new Label("");

            // Layout
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("Export Post to CSV"),
                new Label("Post ID:"),
                postIdField,
                exportButton,
                folderLabel,
                selectFolderButton,
                fileNameLabel,
                fileNameField,
                exportStatusLabel
            );

            // Set up the scene
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            // Event handler for the export button
            exportButton.setOnAction(event -> {
                // Export the post by ID to a CSV file
                int postId = Integer.parseInt(postIdField.getText());
                String folderPath = ""; // Get the selected folder path
                String fileName = fileNameField.getText();

                // You can call a method to export the post to a CSV file here
                // For example, PostManagement.exportPostToCSV(user, postId, folderPath, fileName);

                // Display export status
                exportStatusLabel.setText("Post exported to CSV successfully.");

                // Close the export post window after a delay (e.g., 2 seconds)
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(closeEvent -> {
                    primaryStage.close();
                });
                delay.play();
            });

            // Event handler for the select folder button
            selectFolderButton.setOnAction(selectFolderEvent -> {
                // Implement folder selection logic here and set the selected folder path
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
                File selectedFile = fileChooser.showSaveDialog(primaryStage);

                if (selectedFile != null) {
                    folderPath = selectedFile.getParent(); // Get the parent folder of the selected file
                    fileNameField.setText(selectedFile.getName());
                }
            });
        }
    }
}
