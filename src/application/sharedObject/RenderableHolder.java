package application.sharedObject;

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
    public static Image lifeSprite;
    public static Image explosionSprite;
    public static AudioClip explosionSound ;
    public static AudioClip shootSound ;
    public static Image victoryScene ;
    public static Image defeatScene;


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
        lifeSprite = new Image(ClassLoader.getSystemResource("Life.png").toString());
        explosionSprite = new Image(ClassLoader.getSystemResource("EnemyExplosion.png").toString()) ;
        explosionSound = new AudioClip(ClassLoader.getSystemResource("explosionSound.wav").toString()) ;
        victoryScene = new Image(ClassLoader.getSystemResource("Victory.png").toString()) ;
        defeatScene = new Image(ClassLoader.getSystemResource("Defeat.png").toString()) ;
        shootSound = new AudioClip(ClassLoader.getSystemResource("shootSound.wav").toString()) ;
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

    public static RenderableHolder getInstance() {
        return instance;
    }

    public List<IRenderable> getEntities() {
        return this.entities;
    }
}
