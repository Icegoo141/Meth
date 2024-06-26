package scene;

import application.GameController;
import application.sharedObject.RenderableHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utils.SceneNav;

public class SettingWindowController {
    private GameController gameInstance;
    @FXML
    private Slider soundSlider;
    @FXML
    private Text soundText;
    @FXML
    private Button toMenuButton;
    @FXML
    private ImageView backgroundImage;

    public void initialize() {
        backgroundImage.setImage(RenderableHolder.mainBGTextSprite);
        gameInstance = GameController.getInstance();
        int gameSoundValue = gameInstance.getSoundValue();
        soundSlider.setValue(gameSoundValue);
        soundText.setText(String.valueOf(gameSoundValue));
        soundSlider.valueProperty().addListener(((observableValue, number, t1) -> {
            int soundValue = t1.intValue();
            soundText.setText(String.valueOf(soundValue));
            gameInstance.setSoundValue(soundValue);
            RenderableHolder.mainBGM.stop();
            RenderableHolder.mainBGM.play();
        }));
    }

    @FXML
    private void goToMainMenu() {
        SceneNav.setFXMLScene("MainMenu");
    }
}
