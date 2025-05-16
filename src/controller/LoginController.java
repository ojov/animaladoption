package controller;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Application.AdoptionCentre;
import model.Users.Customer;
import model.Users.Manager;
import model.Users.Users;

public class LoginController extends Controller<AdoptionCentre> {
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField managerId;
    @FXML
    private Button loginButton;
    @FXML
    public void initialize() {
        username.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                managerId.clear();
                managerId.setDisable(true);
            }else {
                managerId.setDisable(false);
            }
            checkFields();
        });

        email.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                managerId.clear();
                managerId.setDisable(true);
            }else {managerId.setDisable(false);}
            checkFields();
        });

        // 🔁 When typing in managerId, clear username and email
        managerId.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                username.clear();
                email.clear();
                username.setDisable(true);
                email.setDisable(true);
            } else {
                username.setDisable(false);
                email.setDisable(false);
            }
            checkFields();
        });

        // Initial check
        checkFields();
    }


    private Users getUsers() {
        return model.getUsers();
    }


    private void openCustomerView() throws IOException {
        ViewLoader.showStage(model, "/view/CustomerDashboard.fxml", "Customer Dashboard", stage);
    }

    private void openManagerView() throws IOException {

        ViewLoader.showStage(model, "/view/ManagerDashboard.fxml", "Manager Dashboard", stage);
    }

    private void showErrorWindow(Exception exception) {
        try {
            // Open error in new modal window
            Stage errorStage = new Stage();
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.initOwner(stage);

            ViewLoader.showStage(
                    exception, // Pass error message as model
                    "/view/ErrorView.fxml",
                    "Error",
                    errorStage
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFields() {
        boolean managerIdFilled = !managerId.getText().trim().isEmpty();
        boolean emailAndUsernameFilled = !email.getText().trim().isEmpty()
                && !username.getText().trim().isEmpty();

        boolean shouldEnable = managerIdFilled || emailAndUsernameFilled;

        loginButton.setDisable(!shouldEnable);
    }

    @FXML
    public void handleLogin() {
        try {
            if (managerId.getText().isEmpty()) {
                // Customer login
                Customer customer = model.getUsers().validateCustomer(
                        username.getText(),
                        email.getText()
                );
                if(customer!=null) openCustomerView();
            } else {
                // Manager login
                Manager manager = model.getUsers().validateManager(
                        managerId.getText()
                );
                if(manager!=null) openManagerView();
            }
        } catch (Exception e) {
            showErrorWindow(e);
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Your logout logic here
        stage.close();
    }

}