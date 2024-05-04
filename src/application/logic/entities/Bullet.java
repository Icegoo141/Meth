package application.logic.entities;

import application.GameController;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import utils.Stats;

import java.util.ArrayList;

public class Bullet extends BaseCollidable {
    private final int dirX, dirY;
    private final double speed;
    private final int damage;
    private boolean isAlternateSprite;
    private final ArrayList<Image> sprites;
    private long previousSpriteChange = -1;

    public Bullet(double x, double y, int dirX, int dirY) {
        this.x = x;
        this.y = y;
        this.collisionRadius = Stats.BULLET_COLRADIUS;
        this.z = Stats.BULLET_Z_INDEX;
        this.dirX = dirX;
        this.dirY = dirY;

        this.speed = Stats.BULLET_SPD;
        this.damage = Stats.BULLET_DMG;


        sprites = new ArrayList<>();
        sprites.add(new WritableImage(RenderableHolder.bulletSprite.getPixelReader(), 0, 0, 20, 20));
        sprites.add(new WritableImage(RenderableHolder.bulletSprite.getPixelReader(), 20, 0, 20, 20));
    }

    public void update() {
        this.x = this.x + (dirX * speed);
        this.y = this.y + (dirY * speed);

        //out of bound
        if (x < 50 || x > 750) destroyed = true;
        if (y < 50 || y > 750) destroyed = true;

        //update sprite
        long now = GameController.getInstance().getGameLogic().getCurrTime();
        if (now - previousSpriteChange >= 5e7) {
            isAlternateSprite = !isAlternateSprite;
            previousSpriteChange = now;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(isAlternateSprite ? sprites.get(0) : sprites.get(1), x - 10, y - 10);
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public int getDamage() {
        return damage;
    }
}
