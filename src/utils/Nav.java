package utils;

import pane.GamePane;
import pane.MenuPane;
import pane.RootPane;

public class Nav {
    private static RootPane rootPane;
    public static void homePage() {
        clear();
        rootPane.getChildren().add(new MenuPane());
    }
    public static void startGame() {
        clear();
        rootPane.getChildren().add(new GamePane());
    }

    private static void clear() {
        rootPane.getChildren().clear();
    }
    public static void setRootPane(RootPane rootPane) {
        Nav.rootPane = rootPane;
    }
}
