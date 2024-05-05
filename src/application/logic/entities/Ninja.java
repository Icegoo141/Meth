package application.logic.entities;

import application.sharedObject.RenderableHolder;
import javafx.scene.image.WritableImage;
import utils.Stats;

import java.util.ArrayList;


public class Ninja extends Samurai {
    public Ninja(double x, double y) {
        super(x, y);
        this.speed = Stats.NINJA_SPD;
        this.hp = Stats.NINJA_HP;

        sprites = new ArrayList<>();
        sprites.add(new WritableImage(RenderableHolder.ninjaSprite.getPixelReader(), 0, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.ninjaSprite.getPixelReader(), 40, 0, 40, 40));
    }
}
