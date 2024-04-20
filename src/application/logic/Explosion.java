package application.logic;

import application.GameController;
import application.sharedObject.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Explosion implements IRenderable {
    private double x ,y ;
    private int changedSprite = 0 ;
    private long previousChange ;
    private Image sprite = new Image(ClassLoader.getSystemResource("EnemyExplosion.png").toString()) ;

    protected boolean destroyed = false ;

    public Explosion(double x, double y) {
        this.x = x;
        this.y = y;
        this.previousChange = GameController.getInstance().getGameLogic().getCurrTime();
    }
    @Override
    public int getZ() {
        return 0;
    }

    public void update(){
        long now = GameController.getInstance().getGameLogic().getCurrTime();
        if (now - previousChange >= 12e7) {
            changedSprite++ ;
            if (changedSprite >= 5) {
                this.destroyed = true;
            }
            previousChange = now;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        int spriteX = changedSprite * 40;

        WritableImage cropedSprite = new WritableImage(
                sprite.getPixelReader(), // PixelReader
                spriteX, // x coordinate of the top-left corner of the rectangular region to be copied
                0, // y coordinate of the top-left corner of the rectangular region to be copied
                40, // width of the rectangular region to be copied
                40 // height of the rectangular region to be copied
        );
        gc.drawImage(cropedSprite, x - 20, y - 20);
    }

    @Override
    public boolean isDestroyed() { return this.destroyed;}

    @Override
    public boolean isVisible() {return true;}
}
