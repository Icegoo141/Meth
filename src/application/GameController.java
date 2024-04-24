package application;

import application.drawing.GameScreen;
import application.input.InputUtility;
import application.logic.GameLogic;
import application.sharedObject.RenderableHolder;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import utils.SceneNav;

public class GameController {
    private static final GameController instance = new GameController();
    private GameScreen gameScreen;
    private GameLogic gameLogic;
    private int soundValue;
    private final AnimationTimer gameLoop;
    private int stage;

    private GameController() {
        soundValue = 100;
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //draw
                gameScreen.paintComponent();
                //logic
                gameLogic.update(l);
                //objectDeletion
                RenderableHolder.getInstance().update();
            }
        };
    }

    public void initGame() {
        gameScreen = new GameScreen(1000, 800);
        gameLogic = new GameLogic(stage);
    }

    public void start() {
        initGame();
        StackPane root = new StackPane();
        root.getChildren().add(gameScreen);
        Scene scene = new Scene(root, 1000, 800);
        gameScreen.requestFocus();
        Main.getStage().setScene(scene);
        gameLoop.start();
    }

    public int getSoundValue() {
        return soundValue;
    }

    public void setSoundValue(int soundValue) {
        this.soundValue = soundValue;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public AnimationTimer getGameLoop() {
        return gameLoop;
    }

    public static GameController getInstance() {
        return instance;
    }

    public void handleQuit() {
        gameLoop.stop();
        InputUtility.clear();
        RenderableHolder.getInstance().getEntities().clear();
        SceneNav.setFXMLScene("MainMenu");
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
