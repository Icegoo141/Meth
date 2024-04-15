package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends BaseCollidable {
    private final int speed = 3;
    private int dirX;
    private int dirY;

    public Bullet(double x, double y, int dirX, int dirY) {
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.collisionRadius = 10;
    }

    public void update() {
        this.x = this.x + (dirX * speed);
        this.y = this.y + (dirY * speed);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        int radius = 10;
        gc.fillOval(x - 10, y - 10, radius * 2, radius * 2);
    }
}
