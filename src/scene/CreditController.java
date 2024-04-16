package scene;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import utils.SceneNav;

public class CreditController {
    @FXML
    private Button toMenu;

    @FXML
    private void backToMainMenu() {
        SceneNav.setFXMLScene("MainMenu");
    }
}
