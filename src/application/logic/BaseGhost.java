package application.logic;

import application.GameController;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BaseGhost extends BaseCollidable {
    protected double speed;
    protected int hp;

    protected boolean isChangeSprite;

    protected ArrayList<Image> images ;

    private long previousChange = 0;

    public BaseGhost(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.collisionRadius = 20;
        this.speed = .5;
        this.hp = 1;
        images=new ArrayList<>();
        images.add(new WritableImage(RenderableHolder.baseGhostSprite.getPixelReader(), 0, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.baseGhostSprite.getPixelReader(), 40, 0, 40, 40));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(isChangeSprite?images.get(0):images.get(1), x - 20, y - 20);
    }

    public void update() {
        // Calculate direction vector from BaseGhost to Player
        double dx = GameController.getInstance().getGameLogic().getPlayer().x - this.x;
        double dy = GameController.getInstance().getGameLogic().getPlayer().y - this.y;

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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isDestroyed() {
        return hp <= 0;
    }
}
