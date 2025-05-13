package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Animals.Animal;
import model.Animals.Animals;
import model.Application.AdoptionCentre;
import model.Users.Users;

public class ManagerDashboardController extends Controller<AdoptionCentre> {
    public TableView<Animal> animalTableView;
    public TableColumn<Animal, String> nameColumn;
    public TableColumn<Animal, Integer> ageColumn;
    public TableColumn<Animal, String> typeColumn;
    public TableColumn<Animal, String> isAdoptedColumn;

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        isAdoptedColumn.setCellValueFactory(new PropertyValueFactory<>("isAdopted"));
        animalTableView.setItems(model.getAnimals().getAnimals());
    }
    @FXML
    private void handleAddAnimal() {
        // Implementation for adding a new animal
    }

    @FXML
    private void handleRemoveAnimal() {
        // Implementation for removing selected animal
    }

    @FXML
    private void handleUpdateAnimal() {
        // Implementation for updating selected animal
    }
    @FXML
    private void openUserList() throws IOException {
        try {
            // Open error in new modal window
            Stage errorStage = new Stage();
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.initOwner(stage);

            ViewLoader.showStage(
                    model.getUsers(),
                    "/view/UserListView.fxml",
                    "Error",
                    errorStage
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
