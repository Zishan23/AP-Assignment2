package hellofx;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class UserDashboard {
    private static User currentUser; // Assuming you have a User class

    public static void displayUserDashboard(Stage primaryStage, User user) {
        currentUser = user;

        // Create the user dashboard
        primaryStage.setTitle("Welcome, " + user.getFullName());

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
            new Label("Full Name: " + user.getFullName()), // Display user's full name
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
            LoginUI.displayLoginUI(primaryStage);
        });

        editProfileButton.setOnAction(event -> {
            // Open a new window for editing the user's profile
            Stage editProfileStage = new Stage();
            editProfileStage.setTitle("Edit Profile");

            // Create UI components for editing the profile
            TextField firstNameField = new TextField(currentUser.getFirstName());
            TextField lastNameField = new TextField(currentUser.getLastName());
            TextField usernameField = new TextField(currentUser.getUsername());
            PasswordField passwordField = new PasswordField(currentUser.getPassword());
            Button saveButton = new Button("Save");

            // Layout for the edit profile window
            VBox editLayout = new VBox(10);
            editLayout.getChildren().addAll(
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

            // Event handler for the save button
            saveButton.setOnAction(saveEvent -> {
                // Update the user's profile information in the database
                String newFirstName = firstNameField.getText();
                String newLastName = lastNameField.getText();
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();

                // You can call a method to update the user's profile in the database here
                // For example, UserRegistration.updateUserProfile(currentUser, newFirstName, newLastName, newUsername, newPassword);

                // Close the edit profile window
                editProfileStage.close();
            });

            // Set up the scene for the edit profile window
            Scene editProfileScene = new Scene(editLayout, 400, 300);
            editProfileStage.setScene(editProfileScene);
            editProfileStage.show();
        });

        addPostButton.setOnAction(event -> {
            // Open a new window for adding a post
            Stage addPostStage = new Stage();
            addPostStage.setTitle("Add Post");

            // Create UI components for adding a post
            TextArea contentTextArea = new TextArea();
            contentTextArea.setWrapText(true);
            TextField likesField = new TextField("0");
            TextField sharesField = new TextField("0");
            Button saveButton = new Button("Save");

            // Layout for the add post window
            VBox addPostLayout = new VBox(10);
            addPostLayout.getChildren().addAll(
                new Label("Add New Post"),
                new Label("Content:"),
                contentTextArea,
                new Label("# Likes:"),
                likesField,
                new Label("# Shares:"),
                sharesField,
                saveButton
            );

            // Event handler for the save button
            saveButton.setOnAction(saveEvent -> {
                // Get the post details
                String content = contentTextArea.getText();
                int likes = Integer.parseInt(likesField.getText());
                int shares = Integer.parseInt(sharesField.getText());

                // You can call a method to add the post to the database here
                // For example, PostManagement.addPost(currentUser, content, likes, shares);

                // Close the add post window
                addPostStage.close();
            });

            // Set up the scene for the add post window
            Scene addPostScene = new Scene(addPostLayout, 400, 300);
            addPostStage.setScene(addPostScene);
            addPostStage.show();
        });


        retrievePostButton.setOnAction(event -> {
            // Open a new window for retrieving a post by ID
            Stage retrievePostStage = new Stage();
            retrievePostStage.setTitle("Retrieve Post");

            // Create UI components for retrieving a post
            TextField postIdField = new TextField();
            Button retrieveButton = new Button("Retrieve");
            TextArea postDetailsTextArea = new TextArea();
            postDetailsTextArea.setEditable(false);

            // Layout for the retrieve post window
            VBox retrievePostLayout = new VBox(10);
            retrievePostLayout.getChildren().addAll(
                new Label("Retrieve Post by ID"),
                new Label("Post ID:"),
                postIdField,
                retrieveButton,
                postDetailsTextArea
            );

            // Event handler for the retrieve button
            retrieveButton.setOnAction(retrieveEvent -> {
                // Retrieve the post by ID and display the details
                int postId = Integer.parseInt(postIdField.getText());

                // You can call a method to retrieve and display the post details from the database here
                // For example, PostManagement.retrievePost(currentUser, postId, postDetailsTextArea);

                // Close the retrieve post window
                retrievePostStage.close();
            });

            // Set up the scene for the retrieve post window
            Scene retrievePostScene = new Scene(retrievePostLayout, 400, 300);
            retrievePostStage.setScene(retrievePostScene);
            retrievePostStage.show();
        });

        removePostButton.setOnAction(event -> {
            // Open a new window for removing a post by ID
            Stage removePostStage = new Stage();
            removePostStage.setTitle("Remove Post");

            // Create UI components for removing a post
            TextField postIdField = new TextField();
            Button removeButton = new Button("Remove");

            // Layout for the remove post window
            VBox removePostLayout = new VBox(10);
            removePostLayout.getChildren().addAll(
                new Label("Remove Post by ID"),
                new Label("Post ID:"),
                postIdField,
                removeButton
            );

            // Event handler for the remove button
            removeButton.setOnAction(removeEvent -> {
                // Remove the post by ID
                int postId = Integer.parseInt(postIdField.getText());

                // You can call a method to remove the post from the database here
                // For example, PostManagement.removePost(currentUser, postId);

                // Close the remove post window
                removePostStage.close();
            });

            // Set up the scene for the remove post window
            Scene removePostScene = new Scene(removePostLayout, 400, 200);
            removePostStage.setScene(removePostScene);
            removePostStage.show();
        });

        topPostsButton.setOnAction(event -> {
            // Open a new window for viewing top posts
            Stage topPostsStage = new Stage();
            topPostsStage.setTitle("Top Posts");

            // Create UI components for displaying top posts
            TextField topNField = new TextField("10"); // Default to top 10
            Button showTopPostsButton = new Button("Show Top Posts");
            TextArea topPostsTextArea = new TextArea();
            topPostsTextArea.setEditable(false);

            // Layout for the top posts window
            VBox topPostsLayout = new VBox(10);
            topPostsLayout.getChildren().addAll(
                new Label("View Top Posts"),
                new Label("Show the Top N Posts (e.g., 10):"),
                topNField,
                showTopPostsButton,
                topPostsTextArea
            );

            // Event handler for the show top posts button
            showTopPostsButton.setOnAction(showEvent -> {
                // Get the value of N (number of top posts to display)
                int topN = Integer.parseInt(topNField.getText());

                // You can call a method to retrieve and display the top N posts from the database here
                // For example, PostManagement.showTopPosts(currentUser, topN, topPostsTextArea);

                // Close the top posts window
                topPostsStage.close();
            });

            // Set up the scene for the top posts window
            Scene topPostsScene = new Scene(topPostsLayout, 400, 300);
            topPostsStage.setScene(topPostsScene);
            topPostsStage.show();
        });

        exportPostButton.setOnAction(event -> {
            // Open a new window for exporting a post to CSV
            Stage exportPostStage = new Stage();
            exportPostStage.setTitle("Export Post to CSV");

            // Create UI components for exporting a post
            TextField postIdField = new TextField();
            Button exportButton = new Button("Export");
            Label folderLabel = new Label("Select a folder for export:");
            Button selectFolderButton = new Button("Select Folder");
            Label fileNameLabel = new Label("Enter a file name:");
            TextField fileNameField = new TextField();
            Label exportStatusLabel = new Label("");

            // Layout for the export post window
            VBox exportPostLayout = new VBox(10);
            exportPostLayout.getChildren().addAll(
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

            // Event handler for the export button
            exportButton.setOnAction(exportEvent -> {
                // Export the post by ID to a CSV file
                int postId = Integer.parseInt(postIdField.getText());
                String folderPath = ""; // Get the selected folder path
                String fileName = fileNameField.getText();

                // You can call a method to export the post to a CSV file here
                // For example, PostManagement.exportPostToCSV(currentUser, postId, folderPath, fileName);

                // Display export status
                exportStatusLabel.setText("Post exported to CSV successfully.");

                // Close the export post window after a delay (e.g., 2 seconds)
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> exportPostStage.close());
                delay.play();
            });

            // Event handler for the select folder button
            selectFolderButton.setOnAction(selectFolderEvent -> {
                // Implement folder selection logic here and set the selected folder path
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(exportPostStage);

                if (selectedDirectory != null) {
                    folderPath = selectedDirectory.getAbsolutePath();
                }
            });

            // Set up the scene for the export post window
            Scene exportPostScene = new Scene(exportPostLayout, 400, 300);
            exportPostStage.setScene(exportPostScene);
            exportPostStage.show();
        });
    }
}
