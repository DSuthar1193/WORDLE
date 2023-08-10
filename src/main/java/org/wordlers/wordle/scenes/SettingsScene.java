package org.wordlers.wordle.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.wordlers.wordle.WordleApplication;
import org.wordlers.wordle.builders.TextBuilder;
import org.wordlers.wordle.structure.WindowSettings;

import static org.wordlers.wordle.WordleApplication.backButtonImage;

/**
 * @author Nashwan
 * @since Apr. 5, 2023
 */
@SuppressWarnings("ConstantConditions")
public class SettingsScene {
    public static Scene load(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WindowSettings.WIDTH, WindowSettings.HEIGHT);

        Text title = new TextBuilder("Settings",48).color(Color.RED).build();
        root.setTop(title);
        BorderPane.setAlignment(root.getTop(), Pos.TOP_CENTER);

        // toggle
        Image on = new Image(WordleApplication.class.getResourceAsStream("volume_on.png"));
        Image off = new Image(WordleApplication.class.getResourceAsStream("volume_off.png"));

        ImageView button = new ImageView(MainMenuScene.player.disabled() ? off : on);
        HBox musicToggle = new HBox(new TextBuilder("Music: ", 32).build(), button);

        musicToggle.setOnMouseClicked(event -> {
            if (!MainMenuScene.player.disabled()) {
                button.setImage(off);
                GameScene.bgMusic.disable(true);
                GameScene.soundEffects.disable(true);
                MainMenuScene.player.disable(true);
            } else {
                button.setImage(on);
                GameScene.bgMusic.disable(false);
                GameScene.soundEffects.disable(false);
                MainMenuScene.player.disable(false);
            }
        });

        ImageView backButton = new ImageView(backButtonImage);
        musicToggle.setAlignment(Pos.CENTER);

        VBox box = new VBox(musicToggle, backButton);
        box.setMinWidth(WindowSettings.WIDTH);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        backButton.setOnMouseClicked((e) -> {
            WordleApplication.setScene(MainMenuScene.load());
        });

        VBox vBox = new VBox(box);
        root.setCenter(vBox);

        vBox.setAlignment(Pos.CENTER);

        return scene;
    }
}
