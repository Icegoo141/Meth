package application.logic;

import application.sharedObject.IRenderable;
import javafx.scene.canvas.GraphicsContext;

public class Field implements IRenderable {
    @Override
    public int getZ() {
        return -9999;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
