package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ErrorController extends Controller<Exception> {
    @FXML
    private Label errorType;
    @FXML
    private Label errorMessage;

    public ErrorController(Label errorType) {
        this.errorType = errorType;
    }
    private void updateErrorDisplay() {
        if (errorType != null && model != null) {
            errorType.setText(model.getClass().getSimpleName());
            errorMessage.setText(model.getMessage());
        }
    }

    @Override
    public void setModel(Exception model) {
        super.setModel(model);
        updateErrorDisplay();
    }



    @FXML
    private void handleClose(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }



}