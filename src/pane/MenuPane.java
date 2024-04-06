package pane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.Nav;

public class MenuPane extends VBox {

    public MenuPane() {

        setWidth(1000);
        setHeight(800);
        setMaxHeight(800);
        setMaxWidth(1000);
        setPadding(new Insets(0, 100, 0, 100));
        setSpacing(20);
        setAlignment(Pos.CENTER_LEFT);

        setBackground();
        setMenuOptions();
    }

    private void setBackground() {
        Image backgroundImage = new Image("file:../../res/mainMenuBackGround.png", 1000, 800, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(background));
    }

    private void setMenuOptions() {
        Text title = new Text("Simple shooting game");
        Button start = navButton("Start game");
        start.setOnAction(event -> Nav.startGame());
        Button option = navButton("Option");
        Button credit = navButton("Credit");
        Button quit = navButton("Quit");
        quit.setOnAction(event -> Platform.exit());

        getChildren().addAll(title, start, option, credit, quit);
    }

    private Button navButton(String target) {
        Button button = new Button(target);
        button.setId("menuButton");
        button.setPrefWidth(300);
        //button.getStyleClass().add("button") ;
        //button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        button.setPrefHeight(60);
        return button;
    }
}
