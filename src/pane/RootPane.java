package pane;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import utils.Nav;

public class RootPane extends StackPane {
    public static RootPane instance;

    public RootPane() {
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        Nav.setRootPane(this);
        Nav.homePage();
    }

    public static RootPane getRootPane() {
        if (instance == null) instance = new RootPane();
        return instance;
    }
}
