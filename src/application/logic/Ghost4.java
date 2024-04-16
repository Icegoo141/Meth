package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ghost4 extends BaseGhost {
    public Ghost4(double x, double y) {
        super(x, y);
        this.speed = 1.5;
        this.hp = 2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.SADDLEBROWN);
        gc.strokeOval(x - 20, y - 20, 40, 40);
    }
}
