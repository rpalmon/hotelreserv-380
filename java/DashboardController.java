package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * The {@code DashboardController} class handles user interactions on the dashboard screen.
 * It provides navigation to different sections of the application such as guest management,
 * reservation management, and room management.
 */
public class DashboardController {

    /** Button to navigate to the guest management screen. */
    @FXML
    private Button guestButton;

    /** Button to navigate to the reservation management screen. */
    @FXML
    private Button resButton;

    /** Button to navigate to the room management screen. */
    @FXML
    private Button roomButton;

    /** Button to log out and return to the login screen. */
    @FXML
    private Button logoutButton;

    /**
     * Handles the "Guest" button click event.
     * Navigates the user to the guest management screen.
     */
    @FXML
    private void handlesGuestButton() {
        loadScene("guest.fxml");  // Load the guest management screen
    }

    /**
     * Handles the "Reservation" button click event.
     * Navigates the user to the reservation management screen.
     */
    @FXML
    private void handlesResButton() {
        loadScene("reservation.fxml");  // Load the reservation management screen
    }

    /**
     * Handles the "Rooms" button click event.
     * Navigates the user to the room management screen.
     */
    @FXML
    private void handlesRoomButton() {
        loadScene("room.fxml");  // Load the room management screen
    }

    /**
     * Handles the "Log Out" button click event.
     * Navigates the user back to the login screen.
     */
    @FXML
    public void handleLogOutButton() {
        loadScene("login.fxml");  // Load the login screen
    }

    /**
     * Loads a new screen based on the provided FXML file name.
     *
     * @param fxmlFile the name of the FXML file to load.
     */
    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();  // Load the FXML file for the new screen
            Scene newScene = new Scene(root);  // Create a new scene from the FXML

            // Get the current stage and set the new scene
            Stage stage = (Stage) guestButton.getScene().getWindow();
            stage.setScene(newScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Log the error or show an alert to the user
        }
    }
}
