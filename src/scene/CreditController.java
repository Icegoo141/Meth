package scene;

import application.sharedObject.RenderableHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import utils.SceneNav;

public class CreditController {
    @FXML
    private Button toMenu;
    @FXML
    private ImageView backgroundImage;

    public void initialize() {
        backgroundImage.setImage(RenderableHolder.mainBGTextSprite);
    }

    @FXML
    private void backToMainMenu() {
        SceneNav.setFXMLScene("MainMenu");
    }
}
