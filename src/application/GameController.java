package application;

import javafx.animation.AnimationTimer;

public class GameController {
    private static final GameController instance = new GameController();
    private int soundValue;

    private GameController() {
        soundValue = 100;
    }

    private void start() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //draw
                //logic
                //objectDeletion
                System.out.println("hello");
            }
        };
    }

    public int getSoundValue() {
        return soundValue;
    }

    public void setSoundValue(int soundValue) {
        this.soundValue = soundValue;
//        SoundController.setVolume(soundValue);
    }

    public static GameController getInstance() {
        return instance;
    }
}
