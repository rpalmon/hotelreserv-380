package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * The {@code RoomController} class handles user interactions with the room management screen.
 * It provides functionalities to add, update, delete, and search for room records.
 */
public class RoomController {

    /** Field for entering the room ID. */
    @FXML
    private TextField roomIDField;

    /** Field for entering the room number. */
    @FXML
    private TextField roomNumField;

    /** Dropdown for selecting the room type. */
    @FXML
    private ComboBox<Room.RoomType> roomTypeField;

    /** Field for entering the room price. */
    @FXML
    private TextField priceField;

    /** Checkbox to indicate the availability of the room. */
    @FXML
    private CheckBox availCheckBox;

    /** Button to add a new room. */
    @FXML
    private Button addButton;

    /** Button to delete an existing room. */
    @FXML
    private Button delButton;

    /** Button to update room details. */
    @FXML
    private Button updateButton;

    /** Button to search for a room. */
    @FXML
    private Button searchButton;

    /** Button to navigate back to the dashboard. */
    @FXML
    private Button backToDashboardButton;

    /** Instance of {@code RoomData} for managing room data operations. */
    private RoomData roomData;

    /**
     * Constructor for {@code RoomController}.
     * Initializes the {@code RoomData} instance.
     */
    public RoomController() {
        roomData = new RoomData();
    }

    /**
     * Initializes the {@code ComboBox} with room type values.
     */
    @FXML
    public void initialize() {
        roomTypeField.getItems().setAll(Room.RoomType.values());
    }

    /**
     * Adds a new room to the system.
     * Validates input fields before adding.
     */
    @FXML
    public void addRoom() {
        if (roomIDField.getText().isEmpty() ||
            roomNumField.getText().isEmpty() ||
            roomTypeField.getValue() == null ||
            priceField.getText().isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        String roomNum = roomNumField.getText();
        Room.RoomType roomType = roomTypeField.getValue();
        double price = Double.parseDouble(priceField.getText());
        boolean avail = availCheckBox.isSelected();

        Room room = new Room(0, roomNum, roomType, price, avail);

        try {
            roomData.addRoom(room);
            showAlert("Success", "Room was added.", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            System.err.println("Failed to add room. " + e.getMessage());
            showAlert("Error", "Failed to add room.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Updates an existing room in the system.
     * Validates input fields before updating.
     */
    @FXML
    public void updateRoom() {
        if (roomIDField.getText().isEmpty() ||
            roomNumField.getText().isEmpty() ||
            roomTypeField.getValue() == null ||
            priceField.getText().isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        int id = Integer.parseInt(roomIDField.getText());
        String roomNum = roomNumField.getText();
        Room.RoomType roomType = roomTypeField.getValue();
        double price = Double.parseDouble(priceField.getText());
        boolean avail = availCheckBox.isSelected();

        Room room = new Room(id, roomNum, roomType, price, avail);

        try {
            roomData.updateRoom(room);
            showAlert("Success", "Room was updated.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to update room. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Deletes a room from the system by its ID.
     */
    @FXML
    public void deleteRoom() {
        try {
            int id = Integer.parseInt(roomIDField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid room ID.", Alert.AlertType.ERROR);
                return;
            }

            roomData.delRoom(id);
            showAlert("Success", "Room deleted successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid room ID.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Failed to delete room. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Searches for a room by its ID and populates the fields with its details.
     */
    @FXML
    private void searchRoom() {
        try {
            int id = Integer.parseInt(roomIDField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid room ID.", Alert.AlertType.ERROR);
                return;
            }

            Room room = roomData.getRoomID(id);
            if (room != null) {
                roomNumField.setText(room.getRoomNum());
                roomTypeField.setValue(room.getRoomType());
                priceField.setText(String.valueOf(room.getPrice()));
                availCheckBox.setSelected(room.isAvail());
            } else {
                showAlert("Info", "Room not found.", Alert.AlertType.INFORMATION);
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Room ID is not valid.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Displays an alert dialog with the specified title, message, and type.
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
     * Clears all input fields and resets controls.
     */
    private void clearFields() {
        roomIDField.clear();
        roomNumField.clear();
        roomTypeField.setValue(null);
        priceField.clear();
        availCheckBox.setSelected(false);
    }

    /**
     * Handles the "Back to Dashboard" button click event.
     * Navigates back to the dashboard screen.
     */
    @FXML
    public void handleBackToDashboardButton() {
        loadScene("dashboard.fxml", "Dashboard");
    }

    /**
     * Loads a new screen based on the provided FXML file name and title.
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
