package hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CombinedDashboard extends Application {
    private static User currentUser; // Assuming you have a User class
    private String folderPath;
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
            LoginUI loginUI = new LoginUI(); // Create an instance of the inner class
            loginUI.displayLoginUI(primaryStage);
        });
        // Create instances of the inner classes
        EditProfileUI editProfileUI = new EditProfileUI();
        AddPostUI addPostUI = new AddPostUI();
        RetrievePostUI retrievePostUI = new RetrievePostUI();
        RemovePostUI removePostUI = new RemovePostUI();
        TopPostsUI topPostsUI = new TopPostsUI();
        ExportPostUI exportPostUI = new ExportPostUI();

        editProfileButton.setOnAction(event -> {
            // Call the non-static method on the instance
            editProfileUI.displayEditProfileUI(primaryStage, currentUser);
        });

        addPostButton.setOnAction(event -> {
            // Call the non-static method on the instance
            addPostUI.displayAddPostUI(primaryStage, currentUser);
        });

        retrievePostButton.setOnAction(event -> {
            // Call the non-static method on the instance
            retrievePostUI.displayRetrievePostUI(primaryStage, currentUser);
        });

        removePostButton.setOnAction(event -> {
            // Call the non-static method on the instance
            removePostUI.displayRemovePostUI(primaryStage, currentUser);
        });

        topPostsButton.setOnAction(event -> {
            // Call the non-static method on the instance
            topPostsUI.displayTopPostsUI(primaryStage, currentUser);
        });

        exportPostButton.setOnAction(event -> {
            // Call the non-static method on the instance
            exportPostUI.displayExportPostUI(primaryStage, currentUser);
        });

        primaryStage.show();
    }


    public static void displayUserDashboard(Stage primaryStage, User user) {
        CombinedDashboard dashboard = new CombinedDashboard();
        CombinedDashboard.currentUser = user;
        dashboard.start(primaryStage); // Start the dashboard
    }


    

    class EditProfileUI {
        public void displayEditProfileUI(Stage primaryStage, User user) {
            // Create the edit profile window components
            TextField firstNameField = new TextField(user.getFirstName());
            TextField lastNameField = new TextField(user.getLastName());
            TextField usernameField = new TextField(user.getUsername());
            PasswordField passwordField = new PasswordField();
            Button saveButton = new Button("Save");
            Label errorLabel = new Label("");

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
                saveButton,
                errorLabel
            );

            // Set up the scene
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            // Event handler for the save button
            saveButton.setOnAction(event -> {
                // Get the new profile information from the input fields
                String newFirstName = firstNameField.getText();
                String newLastName = lastNameField.getText();
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();

                // Check if the new profile information is valid
                if (isValidInput(newFirstName, newLastName, newUsername, newPassword)) {
                    // Update the user's profile information in the database
                    boolean updateSuccessful = UserRegistration.updateUserProfile(user.getUsername(), newFirstName, newLastName, newUsername, newPassword);

                    if (updateSuccessful) {
                        // Profile update was successful
                        // Update the currentUser object with the new information
                        user.setFirstName(newFirstName);
                        user.setLastName(newLastName);
                        user.setUsername(newUsername);
                        user.setPassword(newPassword);

                        // Close the edit profile window
                        primaryStage.close();
                    } else {
                        // Profile update failed, display an error message
                        errorLabel.setText("Profile update failed. Please try again.");
                    }
                } else {
                    // Invalid input, display an error message
                    errorLabel.setText("Invalid input. Please check your data.");
                }
            });

            primaryStage.show();
        }
    

        // Method to validate input
        private boolean isValidInput(String newFirstName, String newLastName, String newUsername, String newPassword) {
            // Add validation logic here (e.g., checking for empty fields, valid username, strong password, etc.)
            return !newFirstName.isEmpty() && !newLastName.isEmpty() && !newUsername.isEmpty() && !newPassword.isEmpty();
        }
    }

    public class AddPostUI {
        public void displayAddPostUI(Stage primaryStage, User user) {
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
                // Get the post details from the input fields
                String content = contentTextArea.getText();
                int likes = Integer.parseInt(likesField.getText());
                int shares = Integer.parseInt(sharesField.getText());

                // Call the addPost method to save the post in the database
                boolean postAdded = UserRegistration.addPost(user.getUserId(), content, likes, shares);

                if (postAdded) {
                    // Post added successfully
                    // You can display a success message or perform any other actions here

                    // For example, you can clear the input fields and display a success message
                    contentTextArea.clear();
                    likesField.clear();
                    sharesField.clear();

                    // Display a success message
                    displaySuccessMessage("Post added successfully");
                } else {
                    // Post addition failed, display an error message
                    // You can set an error label or show a dialog to inform the user

                    // For example, you can display an error message in a label
                    displayErrorMessage("Post addition failed. Please try again.");
                }

                // Close the add post window
                primaryStage.close();
            });
        }

        // Helper method to display a success message
        private void displaySuccessMessage(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        // Helper method to display an error message
        private void displayErrorMessage(String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }



    class RetrievePostUI {
    	private Posts posts; // Your list of posts

        public void setPosts(Posts posts) {
            this.posts = posts;
        }
        public void displayRetrievePostUI(Stage primaryStage, User user) {
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
                int postId;
                try {
                    postId = Integer.parseInt(postIdField.getText());
                } catch (NumberFormatException e) {
                    // Handle invalid input (you can add your custom handling logic here)
                    return;
                }

                // Call a method to retrieve and display the post details
                retrievePost(postId, postDetailsTextArea);
            });
        }

        // Method to retrieve and display post details
        private void retrievePost(int postId, TextArea postDetailsTextArea) {
            // Search for the post with the given postId in the list of posts
            for (Post post : posts.getPosts()) {
                if (post.getPostId() == postId) {
                    // Display post details in the text area
                    postDetailsTextArea.setText("Post ID: " + post.getPostId() + "\n"
                            + "Content: " + post.getContent() + "\n"
                            + "Likes: " + post.getLikes() + "\n"
                            + "Shares: " + post.getShares());
                    return;
                }
            }

            // Handle the case where the post is not found (you can add your custom logic)
        }
    }


       

        
    
    
    class RemovePostUI {
        public void displayRemovePostUI(Stage primaryStage, User user) {
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

                // Call the removePost method to remove the post from the database
                boolean postRemoved = UserRegistration.deletePost(postId);

                if (postRemoved) {
                    // Post removed successfully
                    // You can display a success message or perform any other actions here

                    // For example, you can clear the input field and display a success message
                    postIdField.clear();
                    displaySuccessMessage("Post removed successfully");
                } else {
                    // Post removal failed, display an error message
                    // You can set an error label or show a dialog to inform the user

                    // For example, you can display an error message in a label
                    displayErrorMessage("Post removal failed. Please try again.");
                }
            });
        }

        // Helper method to display a success message
        private void displaySuccessMessage(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        // Helper method to display an error message
        private void displayErrorMessage(String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }


    class TopPostsUI {
        public void displayTopPostsUI(Stage primaryStage, User user) {
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

                // Call the getTopPostsByLikesAndShares method to retrieve and display the top N posts
                List<Post> topPosts = UserRegistration.getTopPostsByLikesAndShares(topN);

                // Format the retrieved top posts as a String
                StringBuilder topPostsString = new StringBuilder();
                for (Post post : topPosts) {
                    topPostsString.append("Post ID: ").append(post.getPostId()).append("\n");
                    topPostsString.append("User ID: ").append(post.getUserId()).append("\n");
                    topPostsString.append("Content: ").append(post.getContent()).append("\n");
                    topPostsString.append("Likes: ").append(post.getLikes()).append("\n");
                    topPostsString.append("Shares: ").append(post.getShares()).append("\n");
                    topPostsString.append("\n");
                }

                // Display the retrieved top posts in the TextArea
                topPostsTextArea.setText(topPostsString.toString());
            });
        }
    }




    class ExportPostUI {
        public  void displayExportPostUI(Stage primaryStage, User user) {
            // Create the export post window components
            TextField postIdField = new TextField();
            Button exportButton = new Button("Export");
            Label folderLabel = new Label("Select a folder for export:");
            Button selectFolderButton = new Button("Select Folder");
            Label fileNameLabel = new Label("Enter a file name:");
            TextField fileNameField = new TextField();
            Label exportStatusLabel = new Label();

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
                String fileName = fileNameField.getText();

                // Check if folderPath is not null or empty
                if (folderPath == null || folderPath.isEmpty()) {
                    exportStatusLabel.setText("Please select a folder for export.");
                } else {
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
                }
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

