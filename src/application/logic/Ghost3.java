package application.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ghost3 extends BaseGhost{
    public Ghost3(double x, double y) {
        super(x, y);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BROWN);
        gc.strokeRect(x-20,y-20,40,40);
    }
}
