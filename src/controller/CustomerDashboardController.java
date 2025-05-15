package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Animals.Animal;
import model.Application.AdoptionCentre;

public class CustomerDashboardController extends Controller<AdoptionCentre> {
    @FXML
    private ListView<Animal> animalListView;
    @FXML private Label welcomeLabel;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome " + AdoptionCentre.getLoggedInUser().getName());
        animalListView.setItems(model.getAnimals().getAnimals());
    }

    @FXML
    private void showDetails(){
        try {
            // Open error in new modal window
            Stage errorStage = new Stage();
            errorStage.initModality(Modality.APPLICATION_MODAL);
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
    private void handleAdopt(ActionEvent event) {
        // Your logout logic here
        stage.close();
    }
    @FXML
    private void handleClose(ActionEvent event) {
        // Your logout logic here
        stage.close();
    }

}
