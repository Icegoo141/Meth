package application.drawing;

import application.GameController;
import application.input.InputUtility;
import application.sharedObject.IRenderable;
import application.sharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import utils.SceneNav;


public class GameScreen extends Canvas {
    public GameScreen(double width, double height) {
        super(width, height);
        addListener();
    }

    public void addListener() {
        this.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                GameController.getInstance().handleQuit("MainMenu");
            }
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
            if (!entity.isDestroyed()) {
                entity.draw(gc);
            }
        }
    }
}