package scene;

import application.GameController;
import application.sharedObject.RenderableHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import utils.SceneNav;

public class DefeatSceneController {
    @FXML
    private ImageView defeatSceneImage;
    public void initialize(){
        defeatSceneImage.setImage(RenderableHolder.DefeatScene);
    }

    @FXML
    private void goToMainMenu() {
        SceneNav.setFXMLScene("MainMenu");
    }

    @FXML
    private void startGame() {
        GameController.getInstance().setStage(1);
        GameController.getInstance().start();
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
