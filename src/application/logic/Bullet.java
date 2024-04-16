package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet extends BaseCollidable {

    private final Image spite = new Image("/res/Bullet.PNG") ;
    private final int speed = 3;
    private int dirX;
    private int dirY;
    private int damage;

    public Bullet(double x, double y, int dirX, int dirY, int damage) {
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.damage = damage;
        this.collisionRadius = 10;
    }

    public void update() {
        this.x = this.x + (dirX * speed);
        this.y = this.y + (dirY * speed);

        //out of bound
        if (x < 50 || x > 750) destroyed = true;
        if (y < 50 || y > 750) destroyed = true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(spite,x-10,y-10);
    }

    public int getDamage() {
        return damage;
    }
}
