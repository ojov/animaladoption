package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Animals.Animal;
import model.Application.AdoptionCentre;
import model.Exceptions.InvalidOperationException;
import model.Users.Customer;

public class CustomerDashboardController extends Controller<AdoptionCentre> {
    @FXML
    private ListView<Animal> animalListView;
    @FXML private Label welcomeLabel;
    @FXML private Button adoptButton;

    @FXML
    public void initialize() {
        adoptButton.disableProperty().bind(
                animalListView.getSelectionModel().selectedItemProperty().isNull()
        );
        welcomeLabel.setText("Welcome " + AdoptionCentre.getLoggedInUser().getName());
        animalListView.setItems(model.getAnimals().getAnimals());
    }

    @FXML
    private void showDetails(){
        try {
            // Open error in new modal window
            Stage errorStage = new Stage();
            errorStage.initOwner(stage);

            ViewLoader.showStage(
                    AdoptionCentre.getLoggedInUser() ,
                    "/view/DetailsView.fxml",
                    "My Details",
                    errorStage
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleAdopt() {
        // Your logout logic here
        Animal animal = animalListView.getSelectionModel().getSelectedItem();
        Customer customer = (Customer) AdoptionCentre.getLoggedInUser();
        if(customer.canAdopt(animal)){
            customer.adoptedAnimals().add(animal);
            model.getAnimals().remove(animal);
        } else{
            showErrorWindow(new InvalidOperationException("Cannot adopt " + animal.getName()+ " adoption limit for " +animal.typeProperty().get()+ "'s reached"));
        }
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
    private void handleClose(ActionEvent event) {
        // Your logout logic here
        stage.close();
    }



}
