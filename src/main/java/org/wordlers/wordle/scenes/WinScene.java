package org.wordlers.wordle.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.wordlers.wordle.WordleApplication;
import org.wordlers.wordle.builders.TextBuilder;
import org.wordlers.wordle.structure.WindowSettings;

import static org.wordlers.wordle.WordleApplication.backButtonImage;

/**
 * The win scene
 * @author Devanshu
 * @since Apr. 4, 2023
 */
public class WinScene {
    public static Scene load(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WindowSettings.WIDTH, WindowSettings.HEIGHT);


        Text title = new TextBuilder("YOU WIN!!",48).color(Color.GREEN).build();

        ImageView backButton = new ImageView(backButtonImage);

        HBox box = new HBox(backButton);
        box.setMinWidth(WindowSettings.WIDTH);
        box.setAlignment(Pos.CENTER);

        box.setOnMouseClicked((e) -> {
            WordleApplication.setScene(MainMenuScene.load());
        });

        VBox vBox = new VBox(title,box);
        root.setCenter(vBox);

        vBox.setAlignment(Pos.CENTER);

        return scene;
    }
}
