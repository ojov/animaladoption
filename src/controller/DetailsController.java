package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Animals.Animal;
import model.Application.AdoptionCentre;
import model.Users.Customer;
import model.Users.User;

public class DetailsController extends Controller<User> {
    @FXML
    private ListView<Animal> adoptedAnimals;
    @FXML private Label username;
    @FXML
    public void initialize() {
        Label placeholder = new Label("No items available");
        placeholder.setStyle("-fx-text-fill: black;");
        adoptedAnimals.setPlaceholder(placeholder);
        Customer customer = (Customer) model;
        username.setText(AdoptionCentre.getLoggedInUser().getName());
        adoptedAnimals.setItems(customer.adoptedAnimals().getAnimals());
    }
    @FXML
    private void handleClose(ActionEvent event) {
        // Your logout logic here
        stage.close();
    }
}
