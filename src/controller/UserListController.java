package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Users.User;
import model.Users.Users;

public class UserListController extends Controller<Users> {

    @FXML
    private ListView<User> userListView;

    @FXML
    public void initialize() {
        userListView.setItems(model.getUsers());
    }

    @FXML
    public void handleClose() {
        stage.close();
    }
}
