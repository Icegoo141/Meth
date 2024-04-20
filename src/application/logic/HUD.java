package application.logic;

import application.sharedObject.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HUD implements IRenderable {
    private double remainingTime;
    @Override
    public int getZ() {
        return -9998;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(800,0,200,800);

        gc.setStroke(Color.BLACK);
        gc.setFont(Font.font(30));
        gc.strokeText(String.valueOf(Math.round(remainingTime * 10.0) / 10.0),850,200);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }
}
