package application.drawing;

import application.input.InputUtility;
import application.sharedObject.IRenderable;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;


public class GameScreen extends Canvas {
    public GameScreen(double width,double height) {
        super(width,height);
        this.setVisible(true);
        addListener();
    }

    public void addListener() {
        this.setOnKeyPressed((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), true);
        });

        this.setOnKeyReleased((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), false);
        });
    }

    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            // System.out.println(entity.getZ());
            if (entity.isVisible() && !entity.isDestroyed()) {
                entity.draw(gc);
            }
        }
    }
}