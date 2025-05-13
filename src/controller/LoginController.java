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
import model.Exceptions.InvalidOperationException;
import model.Exceptions.UnauthorizedAccessException;
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
    private Button exitButton;
    @FXML
    private Button loginButton;


    private Users getUsers() {
        return model.getUsers();
    }
    private void openMainView(Customer customer) throws IOException {
        AdoptionCentre.setLoggedInUser(customer);
        ViewLoader.showStage(customer, "/view/CustomerDashboard.fxml", "Customer Dashboard", stage);
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

    @FXML
    public void handleLogin() {
        try {
            if (managerId.getText().isEmpty()) {
                // Customer login
                Customer customer = getUsers().validateCustomer(
                        username.getText(),
                        email.getText()
                );
                openMainView(customer);
            } else {
                // Manager login
                Manager manager = getUsers().validateManager(
                        managerId.getText()
                );
                openManagerView();
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