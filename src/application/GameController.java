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
    private final AnimationTimer gameLoop;

    private GameController() {
        soundValue = 100;
        gameScreen = new GameScreen(1000, 800);
        gameLogic = new GameLogic();
        gameLoop = new AnimationTimer() {
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
    }

    public void start() {
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

    public static GameController getInstance() {
        return instance;
    }
}
