package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ErrorController extends Controller<Exception> {
    @FXML
    private Label errorType;
    @FXML
    private Label errorMessage;

    private final StringProperty errorTypeText = new SimpleStringProperty("");
    private final StringProperty errorMessageText = new SimpleStringProperty("");


    public ErrorController() {
    }

    @FXML
    private void initialize() {
        // Bind the UI elements to the properties
        if (errorType != null && errorMessage != null) {
            errorTypeText.set(model.getClass().getSimpleName());
            errorMessageText.set(model.getMessage());
            errorType.textProperty().bind(errorTypeText);
            errorMessage.textProperty().bind(errorMessageText);
        }

    }




    @FXML
    private void handleClose(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }



}