package application.logic.entities;

import application.GameController;
import application.logic.entities.BaseCollidable;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import utils.Stats;

import java.util.ArrayList;

public class Samurai extends BaseCollidable {
    protected double speed;
    protected int hp;

    protected boolean isChangeSprite;

    protected ArrayList<Image> sprites;

    private long previousChange = 0;

    public Samurai(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = Stats.ENEMIES_Z_INDEX;
        this.collisionRadius = Stats.ENEMIES_COLRADIUS;

        this.speed = Stats.SAMURAI_SPD;
        this.hp = Stats.SAMURAI_HP;

        sprites = new ArrayList<>();
        sprites.add(new WritableImage(RenderableHolder.baseGhostSprite.getPixelReader(), 0, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.baseGhostSprite.getPixelReader(), 40, 0, 40, 40));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(isChangeSprite ? sprites.get(0) : sprites.get(1), x - 20, y - 20);
    }

    public void update() {
        // Calculate direction vector from Samurai to Player
        double dx = GameController.getInstance().getGameLogic().getPlayer().getX() - this.x;
        double dy = GameController.getInstance().getGameLogic().getPlayer().getY() - this.y;

        // Calculate the distance between the two entities
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Normalize direction vector (make its length 1)
        if (distance != 0) {
            dx /= distance;
            dy /= distance;
        }

        // Scale direction vector by speed
        double speedX = dx * speed;
        double speedY = dy * speed;

        // Update position of second entity
        this.x = (this.x + speedX);
        this.y = (this.y + speedY);

        //Update sprite
        long now = GameController.getInstance().getGameLogic().getCurrTime();
        if (now - previousChange >= 250000000) {
            isChangeSprite = !isChangeSprite;
            previousChange = now;
        }
    }

    public int getHp() {
        return hp;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isDestroyed() {
        return hp <= 0;
    }
}
