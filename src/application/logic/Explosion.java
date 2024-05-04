package application.logic;

import application.GameController;
import application.sharedObject.IRenderable;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;

public class Explosion implements IRenderable {
    private final double x, y;
    private int spritePhase;
    private long previousSpriteChange;

    protected ArrayList<Image> images;

    public Explosion(double x, double y) {
        this.x = x;
        this.y = y;
        this.previousSpriteChange = GameController.getInstance().getGameLogic().getCurrTime();
        this.spritePhase = 0;
        images = new ArrayList<>();
        images.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 0, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 40, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 80, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 120, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 160, 0, 40, 40));
    }

    @Override
    public int getZ() {
        return 0;
    }

    public void update() {
        long now = GameController.getInstance().getGameLogic().getCurrTime();
        if (now - previousSpriteChange >= 12e7) {
            spritePhase++;
            previousSpriteChange = now;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(images.get(spritePhase), x - 20, y - 20);
    }

    @Override
    public boolean isDestroyed() {
        return spritePhase >= 5;
    }


}