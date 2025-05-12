package au.edu.uts.ap.javafx;

import javafx.stage.*;

public abstract class Controller<M> {
    protected M model;
    protected Stage stage;

    public void setModel(M model) {
        this.model = model;
    }
}