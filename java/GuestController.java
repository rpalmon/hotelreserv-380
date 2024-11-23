package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The {@code GuestController} class handles the interaction between the user interface
 * and the guest data operations. It provides functionalities to add, update, delete,
 * and search for guest records.
 */
public class GuestController {

    /** Field for entering guest ID. */
    @FXML
    private TextField idField;

    /** Field for entering guest name. */
    @FXML
    private TextField nameField;

    /** Field for entering guest email. */
    @FXML
    private TextField emailField;

    /** Field for entering guest phone number. */
    @FXML
    private TextField phoneField;

    /** Field for entering guest address. */
    @FXML
    private TextArea addressField;

    /** Button to add a new guest. */
    @FXML
    private Button addButton;

    /** Button to delete an existing guest. */
    @FXML
    private Button delButton;

    /** Button to update guest details. */
    @FXML
    private Button updateButton;

    /** Button to search for a guest. */
    @FXML
    private Button searchButton;

    /** Button to navigate back to the dashboard. */
    @FXML
    private Button backToDashboardButton;

    /** Instance of {@code GuestData} for managing guest data operations. */
    private GuestData guestData;

    /**
     * Constructor for {@code GuestController}. Initializes the {@code GuestData} instance.
     */
    public GuestController() {
        guestData = new GuestData();
    }

    /**
     * Adds a new guest to the database. Validates input fields before adding.
     */
    @FXML
    public void addGuest() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        Guest guest = new Guest(0, name, email, phone, address);

        try {
            guestData.addGuest(guest);
            showAlert("Success", "Guest added successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            showAlert("Error", "Failed to add guest. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Updates an existing guest's details in the database. Validates input fields before updating.
     */
    @FXML
    public void updateGuest() {
        if (idField.getText() == null ||
            nameField.getText().isEmpty() ||
            emailField.getText().isEmpty() ||
            phoneField.getText().isEmpty() ||
            addressField.getText().isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        Guest guest = new Guest(id, name, email, phone, address);

        try {
            guestData.updateGuest(guest);
            showAlert("Success", "Guest updated successfully!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to update guest. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Deletes a guest record from the database. Validates the guest ID before deletion.
     */
    @FXML
    public void deleteGuest() {
        try {
            int id = Integer.parseInt(idField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid guest ID.", Alert.AlertType.ERROR);
                return;
            }

            guestData.delGuest(id);
            showAlert("Success", "Guest deleted successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid guest ID.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Failed to delete guest. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Searches for a guest by their ID and populates the fields with their details if found.
     */
    @FXML
    private void searchGuest() {
        try {
            int id = Integer.parseInt(idField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid guest ID.", Alert.AlertType.ERROR);
                return;
            }

            Guest guest = guestData.getGuestID(id);
            if (guest != null) {
                nameField.setText(guest.getName());
                emailField.setText(guest.getEmail());
                phoneField.setText(guest.getPhone());
                addressField.setText(guest.getAddress());
            } else {
                showAlert("Info", "Guest not found.", Alert.AlertType.INFORMATION);
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid guest ID.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Displays an alert dialog with the specified details.
     *
     * @param title the title of the alert.
     * @param message the message to display in the alert.
     * @param alertType the type of the alert (e.g., INFORMATION, WARNING, ERROR).
     */
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Clears all input fields in the form.
     */
    private void clearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
    }

    /**
     * Handles the "Back to Dashboard" button action. Loads the dashboard view.
     */
    @FXML
    public void handleBackToDashboardButton() {
        loadScene("dashboard.fxml", "Dashboard");
    }

    /**
     * Loads a new scene from the specified FXML file.
     *
     * @param fxmlFile the name of the FXML file to load.
     * @param title the title of the new window.
     */
    private void loadScene(String fxmlFile, String title) {
        try {
            Stage stage = (Stage) backToDashboardButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (Exception e) {
            showAlert("Error", "Unable to load " + title + " view. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
