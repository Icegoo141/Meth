package application.logic;

import application.GameController;
import application.input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;


public class Player extends BaseCollidable {

    private final Image spite = new Image("/res/Player.PNG") ;
    private final double speed;

    private boolean isChangeSprite ;

    private long previousChange = 0 ;

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
        x = Math.min(1000, Math.max(0, x));
        y = Math.min(800, Math.max(0, y));

        //Update sprite
        long now = GameController.getInstance().getGameLogic().getCurrTime() ;
        if(now - previousChange >= 250000000){
            isChangeSprite = !isChangeSprite;
            previousChange = now ;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage croppedSprite = new WritableImage(spite.getPixelReader(), (isChangeSprite?0:1)*40 ,0,40,40 ) ;
        gc.drawImage(croppedSprite,x-20,y-20);
    }

}
