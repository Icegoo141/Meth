package application.logic.entities;

import application.sharedObject.RenderableHolder;
import javafx.scene.image.WritableImage;
import utils.Stats;

import java.util.ArrayList;

public class Oni extends Samurai {
    public Oni(double x, double y) {
        super(x, y);
        this.speed = Stats.ONI_SPD;
        this.hp = Stats.ONI_HP;

        sprites = new ArrayList<>();
        sprites.add(new WritableImage(RenderableHolder.oniSprite.getPixelReader(), 0, 0, 40, 40));
        sprites.add(new WritableImage(RenderableHolder.oniSprite.getPixelReader(), 40, 0, 40, 40));
    }
}
