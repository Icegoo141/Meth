package pane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.Nav;

public class MenuPane extends VBox {
    public MenuPane() {
        setPadding(new Insets(0, 100, 0, 100)); // Adjust left and right padding
        setSpacing(20);
        setAlignment(Pos.CENTER); // Aligns children in the center horizontally

        Text title = new Text("Simple shooting game");
        Button start = navButton("Start game");
        start.setOnMouseClicked(mouseEvent -> Nav.startGame());
        Button option = navButton("Option");
        Button credit = navButton("Credit");
        Button quit = navButton("Quit");
        quit.setOnMouseClicked(mouseEvent -> Platform.exit());

        getChildren().addAll(title, start, option, credit, quit);
    }

    private Button navButton(String target) {
        Button button = new Button(target);
        button.setPrefWidth(200);
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        button.setPrefHeight(75);
        return button;
    }
}
