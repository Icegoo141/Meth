package application.logic;

import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Ghost3 extends BaseGhost {
    public Ghost3(double x, double y) {
        super(x, y);
        this.speed = 0.6;
        this.hp = 2;
        images=new ArrayList<>();
        images.add(new WritableImage(RenderableHolder.ghost3Sprite.getPixelReader(), 0, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.ghost3Sprite.getPixelReader(), 40, 0, 40, 40));
    }
}
