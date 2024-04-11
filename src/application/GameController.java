package application;

import application.drawing.GameScreen;
import application.logic.GameLogic;
import application.sharedObject.RenderableHolder;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class GameController {
    private static final GameController instance = new GameController();
    private GameScreen gameScreen;
    private GameLogic gameLogic;
    private int soundValue;

    private GameController() {
        soundValue = 100;
        gameScreen = new GameScreen(1000,800);
        gameLogic = new GameLogic();
    }

    public void start() {
        StackPane root = new StackPane();
        root.getChildren().add(gameScreen);
        Scene scene = new Scene(root,1000,800);
        gameScreen.requestFocus();
        Main.getStage().setScene(scene);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //draw
                gameScreen.paintComponent();
                //logic
                gameLogic.update();
                //objectDeletion
                RenderableHolder.getInstance().update();
            }
        };
        gameLoop.start();
    }

    public int getSoundValue() {
        return soundValue;
    }

    public void setSoundValue(int soundValue) {
        this.soundValue = soundValue;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public static GameController getInstance() {
        return instance;
    }
}
