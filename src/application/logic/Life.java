package application.logic;

import application.sharedObject.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Life implements IRenderable {

    private int life;

    private Image image = new Image(ClassLoader.getSystemResource("Life.PNG").toString()) ;

    public Life(){
       this.life = 3 ;
    }

    @Override
    public int getZ() {
        return 1000;
    }

    @Override
    public void draw(GraphicsContext gc) {
        for ( int i = life ; i > 0 ; i--){
            gc.drawImage(image,870,(i-1)*70+ 20);
        }
    }

    @Override
    public boolean isDestroyed() { return false;}

    @Override
    public boolean isVisible() {return true;}

    public int getLife(){
        return life ;
    }

    public void setLife(int life){
        this.life = life ;
    }
}
