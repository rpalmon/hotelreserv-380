package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * The {@code ResController} class handles user interactions with the reservation management screen.
 * It provides functionalities to add, update, delete, and search for reservations.
 */
public class ResController {

    /** Field for entering reservation ID. */
    @FXML
    private TextField resIDField;

    /** Field for entering guest ID. */
    @FXML
    private TextField guestIDField;

    /** Field for entering room ID. */
    @FXML
    private TextField roomIDField;

    /** Date picker for selecting check-in date. */
    @FXML
    private DatePicker checkInDatePicker;

    /** Date picker for selecting check-out date. */
    @FXML
    private DatePicker checkOutDatePicker;

    /** Field for entering total cost. */
    @FXML
    private TextField totalCostField;

    /** Choice box for selecting reservation status. */
    @FXML
    private ChoiceBox<Reservation.Status> statusChoiceBox;

    /** Checkbox for indicating payment status. */
    @FXML
    private CheckBox paymentCheckbox;

    /** Button to add a new reservation. */
    @FXML
    private Button addButton;

    /** Button to update an existing reservation. */
    @FXML
    private Button updateButton;

    /** Button to delete a reservation. */
    @FXML
    private Button deleteButton;

    /** Button to search for a reservation. */
    @FXML
    private Button searchButton;

    /** Button to navigate back to the dashboard. */
    @FXML
    private Button backToDashboardButton;

    /** Instance of {@code ReservationData} for managing reservation data operations. */
    private ReservationData reservationData;

    /**
     * Constructor for {@code ResController}.
     * Initializes the {@code ReservationData} instance.
     */
    public ResController() {
        reservationData = new ReservationData();
    }

    /**
     * Initializes the choice box with reservation status values.
     */
    @FXML
    public void initialize() {
        statusChoiceBox.getItems().setAll(Reservation.Status.values());
    }

    /**
     * Adds a new reservation to the system.
     * Validates input fields before adding.
     */
    @FXML
    public void addReservation() {
        if (resIDField.getText().isEmpty() ||
            guestIDField.getText().isEmpty() ||
            roomIDField.getText().isEmpty() ||
            totalCostField.getText().isEmpty() ||
            checkInDatePicker.getValue() == null ||
            checkOutDatePicker.getValue() == null ||
            statusChoiceBox.getValue() == null) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        int guestID = Integer.parseInt(guestIDField.getText());
        int roomID = Integer.parseInt(roomIDField.getText());
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        double totalCost = Double.parseDouble(totalCostField.getText());
        Reservation.Status status = statusChoiceBox.getValue();
        boolean payment = paymentCheckbox.isSelected();

        Reservation reservation = new Reservation(0, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment);

        try {
            reservationData.addRes(reservation);
            showAlert("Success", "Reservation added successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            System.err.println("Failed to add reservation. " + e.getMessage());
            showAlert("Error", "Failed to add reservation.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Updates an existing reservation in the system.
     * Validates input fields before updating.
     */
    @FXML
    public void updateReservation() {
        if (resIDField.getText().isEmpty() ||
            guestIDField.getText().isEmpty() ||
            roomIDField.getText().isEmpty() ||
            totalCostField.getText().isEmpty() ||
            checkInDatePicker.getValue() == null ||
            checkOutDatePicker.getValue() == null ||
            statusChoiceBox.getValue() == null) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }
        int resID = Integer.parseInt(resIDField.getText());
        int guestID = Integer.parseInt(guestIDField.getText());
        int roomID = Integer.parseInt(roomIDField.getText());
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        double totalCost = Double.parseDouble(totalCostField.getText());
        Reservation.Status status = statusChoiceBox.getValue();
        boolean payment = paymentCheckbox.isSelected();

        Reservation reservation = new Reservation(resID, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment);

        try {
            reservationData.updateRes(reservation);
            showAlert("Success", "Reservation updated successfully!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            System.err.println("Failed to update reservation. " + e.getMessage());
            showAlert("Error", "Failed to update reservation.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Deletes a reservation from the system.
     * Validates the reservation ID before deletion.
     */
    @FXML
    public void deleteReservation() {
        if (resIDField.getText().isEmpty()) {
            showAlert("Error", "Invalid reservation ID.", Alert.AlertType.ERROR);
            return;
        }

        int resID = Integer.parseInt(resIDField.getText());
        try {
            reservationData.delRes(resID);
            showAlert("Success", "Reservation deleted successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            System.err.println("Failed to delete reservation. " + e.getMessage());
            showAlert("Error", "Failed to delete reservation.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Searches for a reservation by its ID.
     * Populates the fields with reservation details if found.
     */
    @FXML
    public void searchReservation() {
        if (resIDField.getText().isEmpty()) {
            showAlert("Error", "Invalid reservation ID.", Alert.AlertType.ERROR);
            return;
        }
        int resID = Integer.parseInt(resIDField.getText());

        Reservation reservation = reservationData.getRes(resID);

        if (reservation != null) {
            guestIDField.setText(String.valueOf(reservation.getGuestId()));
            roomIDField.setText(String.valueOf(reservation.getRoomId()));
            checkInDatePicker.setValue(reservation.getCheckInDate());
            checkOutDatePicker.setValue(reservation.getCheckOutDate());
            totalCostField.setText(String.valueOf(reservation.getTotalCost()));
            statusChoiceBox.setValue(reservation.getStatus());
            paymentCheckbox.setSelected(reservation.getPayment());
        } else {
            showAlert("Error", "Reservation not found.", Alert.AlertType.ERROR);
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
        resIDField.clear();
        guestIDField.clear();
        roomIDField.clear();
        checkInDatePicker.setValue(null);
        checkOutDatePicker.setValue(null);
        totalCostField.clear();
        statusChoiceBox.setValue(null);
        paymentCheckbox.setSelected(false);
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
