package application;

public class GameController {
    private static final GameController instance = new GameController();
    private int soundValue;
//    private Player player;
//    private ArrayList<BaseEnemy> ememies;
//
//    private void initGame() {
//        player = new Player();
//
//    }

    private GameController() {
        soundValue = 100;
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
