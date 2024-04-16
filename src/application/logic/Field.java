package application.logic;

import application.sharedObject.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Field implements IRenderable {

    private final Image spite = new Image("/res/Stage1BG.PNG") ;
    @Override
    public int getZ() {
        return -9999;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(spite,0,0);
        gc.setFill(Color.BLACK);
        gc.fillRect(800,0,200,800);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
