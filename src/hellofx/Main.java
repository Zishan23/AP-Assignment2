package hellofx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        LoginUI loginUI = new LoginUI();
        loginUI.displayLoginUI(primaryStage);
    }

    public static void main(String[] args) {
        // Register the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        launch(args);
    }
}
