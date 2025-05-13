package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Animals.Animal;
import model.Animals.Animals;

public class ManagerDashboardController extends Controller<Animals> {
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
        animalTableView.setItems(model.getAnimals());

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


}
