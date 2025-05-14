package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Animals.Animal;
import model.Animals.Animals;
import model.Animals.Cat;
import model.Animals.Dog;
import model.Animals.Rabbit;
import model.Exceptions.InvalidOperationException;

public class AddAnimalController extends Controller<Animals> {

    @FXML private ComboBox<String> typeBox;

    @FXML private TextField nameField;

    @FXML private TextField ageField;

    @FXML
    public void initialize() {

        System.out.println("Initializing ComboBox...");
        typeBox.setItems(model.getAvailableTypes());
        System.out.println("typeBox is null? " + (typeBox == null));
    }

    @FXML
    public void handleAddAnimal() {
        String type = typeBox.getValue();
        String name = nameField.getText();
        String ageText = ageField.getText();

        if (type == null || name == null || ageText == null ||
                name.trim().isEmpty() || ageText.trim().isEmpty()) {
            showErrorWindow(new InvalidOperationException("All fields are required"));
            return; // Added return statement to prevent further execution
        }
        // Check if animal with same name already exists
        if(model.animal(name) != null){
            showErrorWindow(new InvalidOperationException(name + " already exists in the adoption centre"));
        }


        try {
            int age = Integer.parseInt(ageText);
            Animal animal = switch (type) {
                case "Cat" -> new Cat(name, age);
                case "Dog" -> new Dog(name, age);
                case "Rabbit" -> new Rabbit(name, age);
                default -> null;
            };

            if (animal != null) {
                model.add(animal);
                nameField.clear();
                ageField.clear();
            }
        } catch (NumberFormatException e) {
            showErrorWindow(
             new InvalidOperationException("Age must be an integer"));
        }catch (Exception e){
            showErrorWindow(e);
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
    public void handleClose() {
        stage.close();
    }

}
