package application.logic.entities;

import application.GameController;
import application.input.InputUtility;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import utils.Stats;


public class Player extends BaseCollidable {
    private boolean isAlternateSprite;

    private long previousSpriteChange = 0;
    private final double speed;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = Stats.PLAYER_Z_INDEX;
        this.collisionRadius = Stats.PLAYER_COLRADIUS;

        this.speed = Stats.PLAYER_SPD;
    }

    public void update() {
        handleMovement();
        handleShooting();

        // Update sprite
        long now = GameController.getInstance().getGameLogic().getCurrTime();
        if (now - previousSpriteChange >= 250000000) {
            isAlternateSprite = !isAlternateSprite;
            previousSpriteChange = now;
        }
    }

    private void handleMovement() {
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

        // Handle out of bounds
        x = Math.min(740, Math.max(60, x));
        y = Math.min(740, Math.max(60, y));
    }

    private void handleShooting() {
        // Shooting
        int dirX = 0;
        int dirY = 0;

        // Check for directional shooting
        if (InputUtility.getKeyPressed(KeyCode.UP)) dirY = -3;
        else if (InputUtility.getKeyPressed(KeyCode.DOWN)) dirY = 3;
        if (InputUtility.getKeyPressed(KeyCode.LEFT)) dirX = -3;
        else if (InputUtility.getKeyPressed(KeyCode.RIGHT)) dirX = 3;

        // Handle shooting
        if (dirY != 0 || dirX != 0) {
            GameController.getInstance().getGameLogic().handleShoot(dirX, dirY);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage croppedSprite = new WritableImage(RenderableHolder.playerSprite.getPixelReader(), (isAlternateSprite ? 0 : 1) * 40, 0, 40, 40);
        gc.drawImage(croppedSprite, x - 20, y - 20);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
