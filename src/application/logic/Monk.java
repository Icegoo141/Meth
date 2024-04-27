package application.logic;

import application.sharedObject.RenderableHolder;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;

public class Monk extends BaseGhost {
    public Monk(double x, double y) {
        super(x, y);
        this.speed = 0.2;
        this.hp = 2;
        images=new ArrayList<>();
        images.add(new WritableImage(RenderableHolder.ghost4Sprite.getPixelReader(), 0, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.ghost4Sprite.getPixelReader(), 40, 0, 40, 40));
    }
}
