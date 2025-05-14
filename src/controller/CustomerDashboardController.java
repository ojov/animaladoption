package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Animals.Animal;
import model.Users.User;

public class CustomerDashboardController extends Controller<Animal> {
    @FXML
    private ListView<Animal> animalListView;

}
