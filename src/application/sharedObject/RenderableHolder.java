package application.sharedObject;

import application.GameController;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();

    private final ArrayList<IRenderable> entities;

    private final Comparator<IRenderable> comparator;

    public static Image bulletSprite;
    public static Image playerSprite;
    public static Image baseGhostSprite;
    public static Image ghost2Sprite;
    public static Image ghost3Sprite;
    public static Image ghost4Sprite;
    public static Image mainBGSprite;
    public static Image mainBGTextSprite;
    public static Image level1BGSprite;
    public static Image level2BGSprite;
    public static Image level3BGSprite;
    public static Image lifeSprite;
    public static Image explosionSprite;
    public static Image victoryScene;
    public static Image defeatScene;
    public static AudioClip explosionSound;
    public static AudioClip shootSound;
    public static AudioClip victorySound;
    public static AudioClip defeatSound;
    public static AudioClip level1BGM;
    public static AudioClip level2BGM;
    public static AudioClip level3BGM;
    public static AudioClip mainBGM;


    static {
        loadResources();
    }

    public static void loadResources() {
        bulletSprite = new Image(ClassLoader.getSystemResource("Bullet.png").toString());
        playerSprite = new Image(ClassLoader.getSystemResource("Player.png").toString());
        baseGhostSprite = new Image(ClassLoader.getSystemResource("Samurai.png").toString());
        ghost2Sprite = new Image(ClassLoader.getSystemResource("Ninja.png").toString());
        ghost3Sprite = new Image(ClassLoader.getSystemResource("Oni.png").toString());
        ghost4Sprite = new Image(ClassLoader.getSystemResource("Monk.png").toString());
        mainBGSprite = new Image(ClassLoader.getSystemResource("backGround.png").toString());
        mainBGTextSprite = new Image(ClassLoader.getSystemResource("mainMenuBackGround.png").toString());
        level1BGSprite = new Image(ClassLoader.getSystemResource("Stage1BG.png").toString());
        level2BGSprite = new Image(ClassLoader.getSystemResource("Stage2BG.png").toString());
        level3BGSprite = new Image(ClassLoader.getSystemResource("Stage3BG.png").toString());
        lifeSprite = new Image(ClassLoader.getSystemResource("Life.png").toString());
        explosionSprite = new Image(ClassLoader.getSystemResource("EnemyExplosion.png").toString());
        victoryScene = new Image(ClassLoader.getSystemResource("Victory.png").toString());
        defeatScene = new Image(ClassLoader.getSystemResource("Defeat.png").toString());
        explosionSound = new AudioClip(ClassLoader.getSystemResource("explosionSound.wav").toString());
        shootSound = new AudioClip(ClassLoader.getSystemResource("shootSound.wav").toString());
        victorySound = new AudioClip(ClassLoader.getSystemResource("VictorySound.mp3").toString());
        defeatSound = new AudioClip(ClassLoader.getSystemResource("DefeatSound.wav").toString());
        level1BGM = new AudioClip(ClassLoader.getSystemResource("Stage1BGM.mp3").toString());
        level2BGM = new AudioClip(ClassLoader.getSystemResource("Stage2BGM.mp3").toString());
        level3BGM = new AudioClip(ClassLoader.getSystemResource("Stage3BGM.mp3").toString());
        mainBGM = new AudioClip(ClassLoader.getSystemResource("MainMenuBGM.mp3").toString());
    }

    private RenderableHolder() {
        entities = new ArrayList<>();
        comparator = Comparator.comparingInt(IRenderable::getZ);
    }

    public void add(IRenderable entity) {
        entities.add(entity);
        entities.sort(comparator);
    }

    public void update() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
    }

    public void setSoundVolume() {
        double volume = (double) GameController.getInstance().getSoundValue() / 100;

        explosionSound.setVolume(volume);
        shootSound.setVolume(volume);
        victorySound.setVolume(volume);
        defeatSound.setVolume(volume);
        level1BGM.setVolume(volume);
        level2BGM.setVolume(volume);
        level3BGM.setVolume(volume);
        mainBGM.setVolume(volume);
    }

    public static RenderableHolder getInstance() {
        return instance;
    }

    public List<IRenderable> getEntities() {
        return this.entities;
    }
}
