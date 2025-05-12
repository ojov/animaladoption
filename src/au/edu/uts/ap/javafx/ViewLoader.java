package au.edu.uts.ap.javafx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewLoader {

    public static <T> void showStage(T model, String fxml, String title, Stage stage, Runnable onStageClosed) throws IOException {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(fxml), null, null,
                type -> {
                    try {
                        @SuppressWarnings("unchecked")
                        Controller<T> controller = (Controller<T>) type.newInstance();
                        controller.model = model;
                        controller.stage = stage;
                        return controller;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        Parent root = loader.load();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();
    }

    public static <T> void showStage(T model, String fxml, String title, Stage stage) throws IOException {
        showStage(model, fxml, title, stage, () -> {
        });
    }
}