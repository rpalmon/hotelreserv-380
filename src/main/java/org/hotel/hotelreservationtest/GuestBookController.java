package org.hotel.hotelreservationtest;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;

public class GuestBookController extends BaseController {
    @FXML
    private Button back; // Assuming fx:id of the Button is "back"



    //function to return to control panel
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        backToControlPanel(event, back);

    }
    
}