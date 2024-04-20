package application.input;

import application.GameController;
import application.logic.GameLogic;
import application.logic.Life;
import application.logic.Player;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class InputUtility {
    private static final ArrayList<KeyCode> keyPressed = new ArrayList<>();

    public static void setKeyPressed(KeyCode keyCode, boolean pressed) {
        if (pressed) {
            if (!keyPressed.contains(keyCode)) {
                keyPressed.add(keyCode);
            }
        } else {
            keyPressed.remove(keyCode);
        }
        System.out.println(keyPressed);
    }

    public static boolean getKeyPressed(KeyCode keycode) {
        return keyPressed.contains(keycode);
    }
}
