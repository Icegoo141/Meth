package scene;

import application.GameController;
import application.Main;
import application.sharedObject.RenderableHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import utils.SceneNav;

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
    private ImageView backgroundImage;
    public void initialize() {
        if(!RenderableHolder.mainBGM.isPlaying()){
            RenderableHolder.mainBGM.setCycleCount(-1);
            RenderableHolder.mainBGM.play() ;
        }
        backgroundImage.setImage(RenderableHolder.mainBGTextSprite);
    }
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
        RenderableHolder.mainBGM.stop();
        GameController.getInstance().setStage(1);
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
