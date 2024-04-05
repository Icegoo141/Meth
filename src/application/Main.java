package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pane.RootPane;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(RootPane.getRootPane(), 800, 800);
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
