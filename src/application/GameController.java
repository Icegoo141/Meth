package application;

public class GameController {
    private static GameController instance;
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
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }
}
