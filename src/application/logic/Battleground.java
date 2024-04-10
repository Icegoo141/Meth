package application.logic;

import application.sharedObject.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Battleground implements IRenderable {
    @Override
    public int getZ() {
        return -9999;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,1000,800);
        System.out.println("hi");
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
