package application.logic;

import application.GameController;
import application.sharedObject.IRenderable;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Field implements IRenderable {
    @Override
    public int getZ() {
        return -9999;
    }

    @Override
    public void draw(GraphicsContext gc) {
        switch (GameController.getInstance().getStage()) {
            case 1:
                gc.drawImage(RenderableHolder.level1BGSprite, 0, 0);
                break;
            case 2:
                gc.drawImage(RenderableHolder.level2BGSprite, 0, 0);
                break;
            case 3:
                gc.drawImage(RenderableHolder.level3BGSprite, 0, 0);
                break;
        }
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
