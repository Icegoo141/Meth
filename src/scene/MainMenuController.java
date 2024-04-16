package scene;

import application.GameController;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import utils.SceneNav;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button start;
    @FXML
    private Button credits;
    @FXML
    private Button setting;
    @FXML
    private Button quit;

    @FXML
    private void goToCredit() {
        SceneNav.setFXMLScene("Credit");
    }


    @FXML
    private void goToSetting() {
        SceneNav.setFXMLScene("SettingWindow");
    }

    @FXML
    private void startGame() {
        GameController.getInstance().start();
    }

    @FXML
    private void quitGame() {
        Main.getStage().close();
    }

    @FXML
    private void onMouseEnter(javafx.scene.input.MouseEvent event) {
        Button hoverButton = (Button) event.getSource();
        hoverButton.setTextFill(Color.rgb(177, 128, 168));
    }

    @FXML
    private void onMouseExit(javafx.scene.input.MouseEvent event) {
        Button hoverButton = (Button) event.getSource();
        hoverButton.setTextFill(Color.rgb(79, 66, 134));
    }


}
