package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Animals.Animal;
import model.Application.AdoptionCentre;
import model.Exceptions.InvalidOperationException;

public class ManagerDashboardController extends Controller<AdoptionCentre> {
    public TableView<Animal> animalTableView;
    public TableColumn<Animal, String> nameColumn;
    public TableColumn<Animal, Integer> ageColumn;
    public TableColumn<Animal, String> typeColumn;
    public TableColumn<Animal, String> isAdoptedColumn;
    public Button removeButton;

    public void initialize() {
        removeButton.disableProperty().bind(
                animalTableView.getSelectionModel().selectedItemProperty().isNull()
        );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        isAdoptedColumn.setCellValueFactory(new PropertyValueFactory<>("isAdopted"));
        animalTableView.setItems(model.getAnimals().getAnimals());
    }

    @FXML
    private void handleRemoveAnimal() {
        Animal selected = animalTableView.getSelectionModel().getSelectedItem();
        if(selected.isAdopted()) showErrorWindow(new InvalidOperationException(selected.getName()+" is laready adopted"));
        model.getAnimals().remove(selected);
        animalTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void openUserList() {
        try {
            // Open error in new modal window
            Stage errorStage = new Stage();
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.initOwner(stage);

            ViewLoader.showStage(
                    model.getUsers(),
                    "/view/UserListView.fxml",
                    "User List",
                    errorStage
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openAddAnimal(){
        try {
            // Open error in new modal window
            Stage errorStage = new Stage();
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.initOwner(stage);

            ViewLoader.showStage(
                    model.getAnimals(),
                    "/view/AddAnimalView.fxml",
                    "Add Animal",
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


}
