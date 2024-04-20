package application.logic;

import application.sharedObject.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion implements IRenderable {
    private double x ,y ;
    private int changedSprite ;
    private Image sprite = new Image(ClassLoader.getSystemResource("EnemyExplosion.png").toString()) ;

    @Override
    public int getZ() {
        return 0;
    }

    public void update(){

    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public boolean isDestroyed() { return false;}

    @Override
    public boolean isVisible() {return true;}
}
