package scene;

import application.sharedObject.RenderableHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import utils.SceneNav;

public class VictorySceneController {
    @FXML
    private ImageView victorySceneImage;
    public void initialize(){
        RenderableHolder.victorySound.play();
        victorySceneImage.setImage(RenderableHolder.victoryScene);
    }

    @FXML
    private void goToMainMenu() {
        SceneNav.setFXMLScene("MainMenu");
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
