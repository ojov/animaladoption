package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ErrorController extends Controller<String> {
    @FXML
    private Label errorMessage;

    public void setError(String message) {
        errorMessage.setText(message);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
