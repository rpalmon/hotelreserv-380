package main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The {@code LoginController} class handles user interactions with the login screen.
 * It validates the user's credentials and transitions to the appropriate screen on successful login.
 */
public class LoginController {

    /** The text field for entering the username. */
    @FXML
    private TextField usernameField;

    /** The password field for entering the password. */
    @FXML
    private PasswordField passwordField;

    /** The label for displaying login-related messages. */
    @FXML
    private Label messageLabel;

    /** The {@code UserData} instance used for user validation. */
    private UserData user;

    /**
     * Constructs a {@code LoginController} instance and initializes the {@code UserData} object.
     */
    public LoginController() {
        user = new UserData();
    }

    /**
     * Handles the login button action.
     * Validates the username and password and displays an appropriate message.
     * If the login is successful, transitions to the dashboard screen.
     */
    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate the user credentials
        if (user.validUser(username, password)) { // Example login logic
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
            loadNewScreen("dashboard.fxml");
        } else {
            messageLabel.setText("Invalid username or password.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Loads a new screen using the specified FXML file.
     *
     * @param fxmlFile the name of the FXML file to load.
     */
    private void loadNewScreen(String fxmlFile) {
        try {
            // Load the specified FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane root = loader.load();

            // Set the new scene on the current stage
            Scene newScene = new Scene(root);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading the screen: " + e.getMessage());
        }
    }
}
