package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BaseGhost extends BaseCollidable{
    private int speed;
    private int hp;

    public BaseGhost() {
        this.speed = 1;
        this.z = 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(x,y,40,40);
    }

    public void update() {
        // Calculate direction vector from BaseGhost to Player
        double dx = GameLogic.getPlayer().getX() - this.getX();
        double dy = GameLogic.getPlayer().getY() - this.getY();

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
        this.setX(this.getX() + speedX);
        this.setY(this.getY() + speedY);
    }
}