package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import au.edu.uts.ap.javafx.ViewLoader;
import model.Application.AdoptionCentre;
import model.Users.User;

public class LoginController {
    @FXML
    private TextField usernameTf;
    @FXML
    private PasswordField passwordPf;
    @FXML
    private TextField managerIdTf;

    private AdoptionCentre adoptionCentre;

    public void initialize() {
        // Initialize any necessary components
    }

    public void setAdoptionCentre(AdoptionCentre adoptionCentre) {
        this.adoptionCentre = adoptionCentre;
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameTf.getText();
        String password = passwordPf.getText();
        
        if (!username.isEmpty() && !password.isEmpty()) {
            // Attempt login with username and password
            User user = adoptionCentre.getUsers().validateUser(username, password);
            if (user != null) {
                loadUserDashboard(user);
            } else {
                ViewLoader.showErrorAlert("Login Failed", "Invalid username or password");
            }
        }
    }

    @FXML
    private void handleManagerLogin(ActionEvent event) {
        String managerId = managerIdTf.getText();
        
        if (!managerId.isEmpty()) {
            // Attempt login with manager ID
            User manager = adoptionCentre.getUsers().validateManager(managerId);
            if (manager != null) {
                loadManagerDashboard(manager);
            } else {
                ViewLoader.showErrorAlert("Login Failed", "Invalid Manager ID");
            }
        }
    }

    private void loadUserDashboard(User user) {
        try {
            ViewLoader.showStage(adoptionCentre, "/view/UserDashboardView.fxml", 
                "User Dashboard - " + user.getUsername(), (Stage)usernameTf.getScene().getWindow());
        } catch (Exception e) {
            ViewLoader.showErrorAlert("Error", "Could not load dashboard: " + e.getMessage());
        }
    }

    private void loadManagerDashboard(User manager) {
        try {
            ViewLoader.showStage(adoptionCentre, "/view/ManagerDashboardView.fxml", 
                "Manager Dashboard", (Stage)managerIdTf.getScene().getWindow());
        } catch (Exception e) {
            ViewLoader.showErrorAlert("Error", "Could not load dashboard: " + e.getMessage());
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        ((Stage)usernameTf.getScene().getWindow()).close();
    }
}