package application;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.SceneNav;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        stage.setTitle("Shooting Game");
        stage.setResizable(false);
        SceneNav.setFXMLScene("MainMenu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }
}
