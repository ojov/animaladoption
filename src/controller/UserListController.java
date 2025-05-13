package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Users.User;
import model.Users.Users;

public class UserListController extends Controller<Users> {
    @FXML
    private ListView<User> userListView;

    private void initialize() {
        userListView.setItems(model.getUsers());
    }

}
