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
    private static User currentUser; 
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
            new Label("Full Name: " + currentUser.getFullName()), 
            logoutButton,
            editProfileButton,
            addPostButton,
            retrievePostButton,
            removePostButton,
            topPostsButton,
            exportPostButton
        );

        
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);

       
        logoutButton.setOnAction(event -> {
            
            LoginUI loginUI = new LoginUI(); 
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

            
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            // Event handler for the save button
            saveButton.setOnAction(event -> {
               
                String newFirstName = firstNameField.getText();
                String newLastName = lastNameField.getText();
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();

                
                if (isValidInput(newFirstName, newLastName, newUsername, newPassword)) {
                   
                    boolean updateSuccessful = UserRegistration.updateUserProfile(user.getUsername(), newFirstName, newLastName, newUsername, newPassword);

                    if (updateSuccessful) {
                        
                        user.setFirstName(newFirstName);
                        user.setLastName(newLastName);
                        user.setUsername(newUsername);
                        user.setPassword(newPassword);

                        
                        primaryStage.close();
                    } else {
                        
                        errorLabel.setText("Profile update failed. Please try again.");
                    }
                } else {
                    
                    errorLabel.setText("Invalid input. Please check your data.");
                }
            });

            primaryStage.show();
        }
    

       
        private boolean isValidInput(String newFirstName, String newLastName, String newUsername, String newPassword) {
            
            return !newFirstName.isEmpty() && !newLastName.isEmpty() && !newUsername.isEmpty() && !newPassword.isEmpty();
        }
    }

    public class AddPostUI {
        public void displayAddPostUI(Stage primaryStage, User user) {
            
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

            
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

           
            saveButton.setOnAction(event -> {
               
                String content = contentTextArea.getText();
                int likes = Integer.parseInt(likesField.getText());
                int shares = Integer.parseInt(sharesField.getText());

                
                boolean postAdded = UserRegistration.addPost(user.getUserId(), content, likes, shares);

                if (postAdded) {
                    
                    contentTextArea.clear();
                    likesField.clear();
                    sharesField.clear();

                
                    displaySuccessMessage("Post added successfully");
                } else {
                   
                    displayErrorMessage("Post addition failed. Please try again.");
                }

               
                primaryStage.close();
            });
        }

        
        private void displaySuccessMessage(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

       
        private void displayErrorMessage(String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }



    class RetrievePostUI {
    	private Posts posts; 

        public void setPosts(Posts posts) {
            this.posts = posts;
        }
        public void displayRetrievePostUI(Stage primaryStage, User user) {
           
            TextField postIdField = new TextField();
            Button retrieveButton = new Button("Retrieve");
            TextArea postDetailsTextArea = new TextArea();
            postDetailsTextArea.setEditable(false);

           
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("Retrieve Post by ID"),
                new Label("Post ID:"),
                postIdField,
                retrieveButton,
                postDetailsTextArea
            );

            
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

           
            retrieveButton.setOnAction(event -> {
           
                int postId;
                try {
                    postId = Integer.parseInt(postIdField.getText());
                } catch (NumberFormatException e) {
                    
                    return;
                }

               
                retrievePost(postId, postDetailsTextArea);
            });
        }

        
        private void retrievePost(int postId, TextArea postDetailsTextArea) {
            
            for (Post post : posts.getPosts()) {
                if (post.getPostId() == postId) {
                    
                    postDetailsTextArea.setText("Post ID: " + post.getPostId() + "\n"
                            + "Content: " + post.getContent() + "\n"
                            + "Likes: " + post.getLikes() + "\n"
                            + "Shares: " + post.getShares());
                    return;
                }
            }

            
        }
    }


       

        
    
    
    class RemovePostUI {
        public void displayRemovePostUI(Stage primaryStage, User user) {
            
            TextField postIdField = new TextField();
            Button removeButton = new Button("Remove");

            
            VBox layout = new VBox(10);
            layout.getChildren().addAll(
                new Label("Remove Post by ID"),
                new Label("Post ID:"),
                postIdField,
                removeButton
            );

            
            Scene scene = new Scene(layout, 400, 200);
            primaryStage.setScene(scene);

            
            removeButton.setOnAction(event -> {
                
                int postId = Integer.parseInt(postIdField.getText());

                
                boolean postRemoved = UserRegistration.deletePost(postId);

                if (postRemoved) {
                    
                    postIdField.clear();
                    displaySuccessMessage("Post removed successfully");
                } else {
                    
                    displayErrorMessage("Post removal failed. Please try again.");
                }
            });
        }

        
        private void displaySuccessMessage(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

       
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
           
            TextField topNField = new TextField("10"); 
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

          
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            
            showTopPostsButton.setOnAction(event -> {
                
                int topN = Integer.parseInt(topNField.getText());

                
                List<Post> topPosts = UserRegistration.getTopPostsByLikesAndShares(topN);

                
                StringBuilder topPostsString = new StringBuilder();
                for (Post post : topPosts) {
                    topPostsString.append("Post ID: ").append(post.getPostId()).append("\n");
                    topPostsString.append("User ID: ").append(post.getUserId()).append("\n");
                    topPostsString.append("Content: ").append(post.getContent()).append("\n");
                    topPostsString.append("Likes: ").append(post.getLikes()).append("\n");
                    topPostsString.append("Shares: ").append(post.getShares()).append("\n");
                    topPostsString.append("\n");
                }

                
                topPostsTextArea.setText(topPostsString.toString());
            });
        }
    }




    class ExportPostUI {
        public  void displayExportPostUI(Stage primaryStage, User user) {
           
            TextField postIdField = new TextField();
            Button exportButton = new Button("Export");
            Label folderLabel = new Label("Select a folder for export:");
            Button selectFolderButton = new Button("Select Folder");
            Label fileNameLabel = new Label("Enter a file name:");
            TextField fileNameField = new TextField();
            Label exportStatusLabel = new Label();

          
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

           
            Scene scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);

            
            exportButton.setOnAction(event -> {
                // Export the post by ID to a CSV file
                int postId = Integer.parseInt(postIdField.getText());
                String fileName = fileNameField.getText();

                
                if (folderPath == null || folderPath.isEmpty()) {
                    exportStatusLabel.setText("Please select a folder for export.");
                } else {
                   
                    exportStatusLabel.setText("Post exported to CSV successfully.");

                    
                    PauseTransition delay = new PauseTransition(Duration.seconds(2));
                    delay.setOnFinished(closeEvent -> {
                        primaryStage.close();
                    });
                    delay.play();
                }
            });

            
            selectFolderButton.setOnAction(selectFolderEvent -> {
                
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
                File selectedFile = fileChooser.showSaveDialog(primaryStage);

                if (selectedFile != null) {
                    folderPath = selectedFile.getParent(); 
                    fileNameField.setText(selectedFile.getName());
                }
            });
        }
    }
}

