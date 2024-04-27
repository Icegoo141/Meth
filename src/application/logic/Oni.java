package application.logic;

import application.sharedObject.RenderableHolder;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;

public class Oni extends BaseGhost {
    public Oni(double x, double y) {
        super(x, y);
        this.speed = 0.6;
        this.hp = 2;
        images=new ArrayList<>();
        images.add(new WritableImage(RenderableHolder.ghost3Sprite.getPixelReader(), 0, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.ghost3Sprite.getPixelReader(), 40, 0, 40, 40));
    }
}
