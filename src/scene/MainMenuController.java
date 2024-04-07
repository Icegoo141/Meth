package scene;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import utils.SceneNav;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button setting;

    @FXML
    private void toSetting() {
        SceneNav.setFXMLScene("SettingWindow.fxml");
    }
}
