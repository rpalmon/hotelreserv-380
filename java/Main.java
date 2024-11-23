package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Nam and Roee
 * @version 11/22/2024
 * 
 * 
 * The {@code Main} class serves as the entry point for the JavaFX application.
 * It initializes and displays the primary stage with a login screen.
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene is set.
     * @throws Exception if there is an error during loading the FXML file.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file for the login screen.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Pane root = loader.load();

        // Create a new scene with the loaded FXML layout.
        Scene scene = new Scene(root);

        // Set the scene and title for the primary stage.
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Screen");

        // Display the primary stage.
        primaryStage.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args the command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
