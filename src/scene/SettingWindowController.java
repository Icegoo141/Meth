package scene;

import application.GameLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import utils.SceneNav;

public class SettingWindowController {
    private GameLogic gameInstance;
    @FXML
    private Slider soundSlider;

    @FXML
    private Text soundText;

    @FXML
    private Button toMenuButton;

    public void initialize() {
        gameInstance = GameLogic.getInstance();
        int gameSoundValue = gameInstance.getSoundValue();
        soundSlider.setValue(gameSoundValue);
        soundText.setText(String.valueOf(gameSoundValue));
        soundSlider.valueProperty().addListener(((observableValue, number, t1) -> {
            int soundValue = t1.intValue();
            soundText.setText(String.valueOf(soundValue));
            gameInstance.setSoundValue(soundValue);
        }));
    }

    @FXML
    private void goToMainMenu() {
        SceneNav.setFXMLScene("MainMenu");
    }
}
