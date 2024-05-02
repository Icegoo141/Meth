package application.logic;

import application.sharedObject.RenderableHolder;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;


public class Ninja extends Samurai {
    public Ninja(double x, double y) {
        super(x, y);
        this.speed = 1.5;
        images=new ArrayList<>();
        images.add(new WritableImage(RenderableHolder.ghost2Sprite.getPixelReader(), 0, 0, 40, 40));
        images.add(new WritableImage(RenderableHolder.ghost2Sprite.getPixelReader(), 40, 0, 40, 40));
    }
}
