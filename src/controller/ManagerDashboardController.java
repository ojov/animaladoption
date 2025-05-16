package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;

import javafx.collections.transformation.FilteredList;
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
    @FXML private TableView<Animal> animalTableView;
    @FXML private TableColumn<Animal, String> nameColumn;
    @FXML private TableColumn<Animal, Integer> ageColumn;
    @FXML private TableColumn<Animal, String> typeColumn;
    @FXML private TableColumn<Animal, String> isAdoptedColumn;
    @FXML private Button removeButton;
    @FXML private Button all;
    @FXML private Button catF;
    @FXML private Button dogF;
    @FXML private Button rabbitF;


    public void initialize() {
        removeButton.disableProperty().bind(
                animalTableView.getSelectionModel().selectedItemProperty().isNull()
        );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        isAdoptedColumn.setCellValueFactory(new PropertyValueFactory<>("isAdopted"));
        // Wrap the original list in a FilteredList
        FilteredList<Animal> filteredAnimals = new FilteredList<>(model.getAnimals().getAnimals(), animal -> true);

        // Set filtered list to the TableView
        animalTableView.setItems(filteredAnimals);

        // Filter buttons
        all.setOnAction(e -> filteredAnimals.setPredicate(animal -> true));
        catF.setOnAction(e -> filteredAnimals.setPredicate(animal -> "Cat".equalsIgnoreCase(animal.typeProperty().get())));
        dogF.setOnAction(e -> filteredAnimals.setPredicate(animal -> "Dog".equalsIgnoreCase(animal.typeProperty().get())));
        rabbitF.setOnAction(e -> filteredAnimals.setPredicate(animal -> "Rabbit".equalsIgnoreCase(animal.typeProperty().get())));
    }

    @FXML
    private void handleRemoveAnimal() {
        Animal selected = animalTableView.getSelectionModel().getSelectedItem();
        if(selected.isAdopted()){
            showErrorWindow(new InvalidOperationException(selected.getName()+" is already adopted"));
            return;
        }
        model.getAnimals().remove(selected);
        animalTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void openUserList() {
        openModal(model.getUsers(), "/view/UserListView.fxml", "User List");
    }
    @FXML
    private void openAddAnimal(){
        openModal(model.getAnimals(), "/view/AddAnimalView.fxml", "Add Animal");
    }
    @FXML
    private void handleClose() {
        // Your logout logic here
        stage.close();
    }

    private void showErrorWindow(Exception exception) {
        openModal(exception, "/view/ErrorView.fxml", "Error");
    }

    private void openModal(Object model, String fxmlPath, String title) {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(stage);
            ViewLoader.showStage(model, fxmlPath, title, modalStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
