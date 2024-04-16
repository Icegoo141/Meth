package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class Ghost2 extends BaseGhost {

    private final Image spite = new Image("/res/Ninja.PNG");

    public Ghost2(double x, double y) {
        super(x, y);
        this.speed = 1.5;
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage croppedSprite = new WritableImage(spite.getPixelReader(), (isChangeSprite ? 0 : 1) * 40, 0, 40, 40);
        gc.drawImage(croppedSprite, x - 20, y - 20);
    }
}
