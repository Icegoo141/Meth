package application.logic;

import application.GameController;
import application.input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends BaseCollidable {
    private double speed;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.speed = 1;
        this.z = 1;
        this.collisionRadius = 20;
    }

    public void update() {
        // Movement
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

        // Shooting
        int dirX = 0;
        int dirY = 0;

        // Check for directional shooting
        if (InputUtility.getKeyPressed(KeyCode.UP))
            dirY = -1;
        else if (InputUtility.getKeyPressed(KeyCode.DOWN))
            dirY = 1;
        if (InputUtility.getKeyPressed(KeyCode.LEFT))
            dirX = -1;
        else if (InputUtility.getKeyPressed(KeyCode.RIGHT))
            dirX = 1;

        // Handle shooting
        if (dirY != 0 || dirX != 0) {
            GameController.getInstance().getGameLogic().handleShoot(x, y, dirX, dirY);
        }

        // Handle out of bounds
        x = Math.min(740, Math.max(60, x));
        y = Math.min(740, Math.max(60, y));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x-20, y-20, 40, 40);
    }
}
