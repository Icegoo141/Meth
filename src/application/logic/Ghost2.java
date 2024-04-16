package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ghost2 extends BaseGhost {
    public Ghost2(double x, double y) {
        super(x, y);
        this.speed = 1.5;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.LIGHTPINK);
        gc.fillOval(x - 20, y - 20, 40, 40);
    }
}
