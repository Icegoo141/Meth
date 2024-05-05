package application.logic.entities;

import application.GameController;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;

public class Explosion extends BaseEntity {
    private int spritePhase;
    private long previousSpriteChange;

    private final ArrayList<Image> sprites;

    public Explosion(double x, double y) {
        this.x = x;
        this.y = y;
        this.previousSpriteChange = GameController.getInstance().getGameLogic().getCurrTime();
        this.spritePhase = 0;
        sprites = new ArrayList<>();
        sprites.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 0, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 40, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 80, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 120, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.explosionSprite.getPixelReader(), 160, 0, 40, 40));
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
        gc.drawImage(sprites.get(spritePhase), x - 20, y - 20);
    }

    @Override
    public boolean isDestroyed() {
        return spritePhase >= 5;
    }
}