package application.logic;

import application.GameController;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Bullet extends BaseCollidable {
    private final int speed = 3;
    private int dirX;
    private int dirY;
    private int damage;
    protected boolean isChangeSprite;
    protected ArrayList<Image> images;
    private long previousChange = 0;

    public Bullet(double x, double y, int dirX, int dirY, int damage) {
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.damage = damage;
        this.collisionRadius = 10;
        images = new ArrayList<>();
        images.add(new WritableImage(RenderableHolder.bulletSprite.getPixelReader(), 0, 0, 20, 20));
        images.add(new WritableImage(RenderableHolder.bulletSprite.getPixelReader(), 20, 0, 20, 20));
    }

    public void update() {
        this.x = this.x + (dirX * speed);
        this.y = this.y + (dirY * speed);

        //out of bound
        if (x < 50 || x > 750) destroyed = true;
        if (y < 50 || y > 750) destroyed = true;

        //update sprite
        long now = GameController.getInstance().getGameLogic().getCurrTime();
        if (now - previousChange >= 5e7) {
            isChangeSprite = !isChangeSprite;
            previousChange = now;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(isChangeSprite ? images.get(0) : images.get(1), x - 10, y - 10);
    }

    public int getDamage() {
        return damage;
    }
}
