package application.logic;

import application.GameController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BaseGhost extends BaseCollidable{
    protected double speed;
    private int hp;

    private final Image spite = new Image("/res/Samurai.PNG") ;

    protected boolean isChangeSprite ;

    private long previousChange = 0 ;

    public BaseGhost(double x, double y) {
        this.x = x;
        this.y = y;
        this.speed = .5;
        this.z = 0;
        this.collisionRadius = 20;
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage cropedSprite = new WritableImage(spite.getPixelReader(), (isChangeSprite?0:1)*40 ,0,40,40 ) ;
        gc.drawImage(cropedSprite,x-20,y-20);
    }

    public void update() {
        // Calculate direction vector from BaseGhost to Player
        double dx = GameController.getInstance().getGameLogic().getPlayer().x - this.x;
        double dy = GameController.getInstance().getGameLogic().getPlayer().y - this.y;

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
        this.x = (this.x + speedX);
        this.y = (this.y + speedY);

        //Update sprite
        long now = GameController.getInstance().getGameLogic().getCurrTime() ;
        if(now - previousChange >= 250000000){
            isChangeSprite = !isChangeSprite ;
            previousChange = now ;
        }
   }
}





