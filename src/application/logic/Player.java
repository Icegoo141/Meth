package application.logic;

import application.GameController;
import application.input.InputUtility;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;


public class Player extends BaseCollidable {
    private boolean isChangeSprite;

    private long previousChange = 0;
    private double speed;
    private int damage;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 1;
        this.collisionRadius = 20;

        this.speed = 1;
        this.damage = 1;
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
        if (InputUtility.getKeyPressed(KeyCode.UP)) dirY = -1;
        else if (InputUtility.getKeyPressed(KeyCode.DOWN)) dirY = 1;
        if (InputUtility.getKeyPressed(KeyCode.LEFT)) dirX = -1;
        else if (InputUtility.getKeyPressed(KeyCode.RIGHT)) dirX = 1;

        // Handle shooting
        if (dirY != 0 || dirX != 0) {
            GameController.getInstance().getGameLogic().handleShoot(dirX, dirY);
        }

        // Handle out of bounds
        x = Math.min(740, Math.max(60, x));
        y = Math.min(740, Math.max(60, y));

        //Update sprite
        long now = GameController.getInstance().getGameLogic().getCurrTime();
        if (now - previousChange >= 250000000) {
            isChangeSprite = !isChangeSprite;
            previousChange = now;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage croppedSprite = new WritableImage(RenderableHolder.playerSprite.getPixelReader(), (isChangeSprite ? 0 : 1) * 40, 0, 40, 40);
        gc.drawImage(croppedSprite, x - 20, y - 20);
    }

    public int getDamage() {
        return damage;
    }
}
