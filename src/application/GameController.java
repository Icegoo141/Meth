package application;

import application.drawing.GameScreen;
import application.input.InputUtility;
import application.logic.GameLogic;
import application.sharedObject.RenderableHolder;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import utils.SceneNav;

import java.util.ArrayList;

public class GameController {
    private static final GameController instance = new GameController();
    private GameScreen gameScreen;
    private GameLogic gameLogic;
    private int soundValue;
    private final AnimationTimer gameLoop;
    private int stage;

    public ArrayList<Integer> arrayList ;

    private  AudioClip bgm ;

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
        arrayList = new ArrayList<>() ;
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
        RenderableHolder.getInstance().setSoundVolume();
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

    public void handleQuit(String nav) {
        RenderableHolder.level1BGM.stop();
        RenderableHolder.level2BGM.stop();
        RenderableHolder.level3BGM.stop();
        gameLoop.stop();
        InputUtility.clear();
        RenderableHolder.getInstance().getEntities().clear();
        SceneNav.setFXMLScene(nav);
    }

    public void changeStage(){
        if( stage < 3 ){
            setStage(++stage);
            getGameLogic().setStartTime(-1);
        }
        else {
            handleQuit("VictoryScene");
        }
    }

    public void setStage(int stage) {
        this.stage = stage;
        setBGM();
    }

    public int getStage(){
        return stage ;
    }

    public void setBGM(){
        switch (stage){
            case 1 :
                RenderableHolder.level1BGM.setCycleCount(-1);
                RenderableHolder.level1BGM.play() ;
                break ;
            case 2 :
                RenderableHolder.level1BGM.stop();
                RenderableHolder.level2BGM.setCycleCount(-1);
                RenderableHolder.level2BGM.play() ;
                break ;
            case 3 :
                RenderableHolder.level2BGM.stop();
                RenderableHolder.level3BGM.setCycleCount(-1);
                RenderableHolder.level3BGM.play() ;
                break ;
        }
    }
}
