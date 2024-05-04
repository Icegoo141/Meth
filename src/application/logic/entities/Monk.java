package application.logic.entities;

import application.sharedObject.RenderableHolder;
import javafx.scene.image.WritableImage;
import utils.Stats;

import java.util.ArrayList;

public class Monk extends Samurai {
    public Monk(double x, double y) {
        super(x, y);
        this.speed = Stats.MONK_SPD;
        this.hp = Stats.MONK_HP;

        sprites = new ArrayList<>();
        sprites.add(new WritableImage(RenderableHolder.ghost4Sprite.getPixelReader(), 0, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.ghost4Sprite.getPixelReader(), 40, 0, 40, 40));
    }
}
