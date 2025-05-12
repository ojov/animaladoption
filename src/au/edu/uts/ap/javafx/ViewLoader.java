package au.edu.uts.ap.javafx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewLoader {
    private static final String STYLESHEET = "/view/style.css";

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
        Parent content = loader.load();
        // 2. Create the header image
        ImageView banner = new ImageView(new Image("/image/cat_banner.jpg"));
        banner.setPreserveRatio(true);
        banner.setFitWidth(500);

        VBox root = new VBox(banner, content);
        stage.setTitle(title);
        Scene scene = new Scene(root, 500, 600, Color.WHITE);
        scene.getStylesheets().add(ViewLoader.class.getResource(STYLESHEET).toExternalForm());
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static <T> void showStage(T model, String fxml, String title, Stage stage) throws IOException {
        showStage(model, fxml, title, stage, () -> {
        });
    }
}