package application;

public class GameLogic {
    private static final GameLogic instance = new GameLogic();
    private int soundValue;
//    private Player player;
//    private ArrayList<BaseEnemy> ememies;
//
//    private void initGame() {
//        player = new Player();
//
//    }

    private GameLogic() {
        soundValue = 100;
    }

    public int getSoundValue() {
        return soundValue;
    }

    public void setSoundValue(int soundValue) {
        this.soundValue = soundValue;
//        SoundController.setVolume(soundValue);
    }

    public static GameLogic getInstance() {
        return instance;
    }
}
