package utils;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneNav {
    public static void setFXMLScene(String target) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNav.class.getResource("/scene/" + target + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 800);
            Main.getStage().setScene(scene);
        } catch (Exception e) {
            System.out.println("An error occurred : " + e);
        }
    }
}
