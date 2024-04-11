package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends BaseCollidable{
    private int speed;

    public Player() {
        this.speed = 5;
        this.z = 1;
    }

    public void update() {

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,40,40);
    }
}
