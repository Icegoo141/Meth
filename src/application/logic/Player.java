package application.logic;

import application.GameController;
import application.input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends BaseCollidable {
    private int speed;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.speed = 2;
        this.z = 1;
    }

    public void update() {
        //movement
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

        int dirX = 0;
        int dirY = 0;
        //shooting
        if (InputUtility.getKeyPressed(KeyCode.UP)) {
            dirY = dirY - 1;
        }
        if (InputUtility.getKeyPressed(KeyCode.DOWN)) {
            dirY = dirY + 1;
        }
        if (InputUtility.getKeyPressed(KeyCode.LEFT)) {
            dirX = dirX - 1;
        }
        if (InputUtility.getKeyPressed(KeyCode.RIGHT)) {
            dirX = dirX + 1;
        }
        if (dirY != 0 || dirX != 0) GameController.getInstance().getGameLogic().handleShoot(x, y, dirX, dirY);
        //handle out of bound
        x = Math.min(1000,Math.max(0,x));
        y = Math.min(800,Math.max(0,y));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, 40, 40);
    }
}
