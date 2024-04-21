package application.logic;

import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Ghost3 extends BaseGhost {
    public Ghost3(double x, double y) {
        super(x, y);
        this.hp = 2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage croppedSprite = new WritableImage(RenderableHolder.ghost3Sprite.getPixelReader(), (isChangeSprite ? 0 : 1) * 40, 0, 40, 40);
        gc.drawImage(croppedSprite, x - 20, y - 20);
    }
}
