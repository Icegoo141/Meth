package application.logic;

import application.input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends BaseCollidable {
    private int speed;

    public Player() {
        this.speed = 5;
        this.z = 1;
    }

    public void update() {
        if (InputUtility.getKeyPressed(KeyCode.W)) {
            this.y -= speed;
        }
        if (InputUtility.getKeyPressed(KeyCode.S)) {
            this.y += speed;
        }
        if (InputUtility.getKeyPressed(KeyCode.A)) {
            this.x -= speed;
        }
        if (InputUtility.getKeyPressed(KeyCode.D)) {
            this.x += speed;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, 40, 40);
    }
}
