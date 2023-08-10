package org.wordlers.wordle.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.wordlers.wordle.WordleApplication;
import org.wordlers.wordle.panes.ContentPane;
import org.wordlers.wordle.structure.Game;
import org.wordlers.wordle.structure.WindowSettings;

import java.io.File;

/**
 * The intro scene
 * @author Santio
 * @since Apr. 6, 2023
 */
public class IntroScene {
    public static Scene load(){
        MainMenuScene.player.play(new File("sounds/bg_music.mp3"));

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WindowSettings.WIDTH, WindowSettings.HEIGHT);

        ContentPane pane = new ContentPane(6, 1);
        pane.set("WORDLE");
        root.setCenter(pane);

        WordleApplication.schedule(200, () -> {
            for (int i = 0; i < pane.getGameWidth(); i++) {
                int finalI = i;
                WordleApplication.schedule(500 * i, () -> {
                    pane.animateGuess(finalI);

                    WordleApplication.schedule(250, () -> {
                        pane.getLetters().get(finalI).setColor(
                            Game.getColor((int) Math.floor(Math.random() * 3))
                        );
                    });
                });
            }
        });

        WordleApplication.schedule(200 + 200 + (500 * pane.getGameWidth()), () -> {
            WordleApplication.setScene(MainMenuScene.load());
        });

        BorderPane.setAlignment(root.getCenter(), Pos.CENTER);
        return scene;
    }
}
