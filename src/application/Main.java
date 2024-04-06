package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Shooting Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
