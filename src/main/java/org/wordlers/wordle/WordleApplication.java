package org.wordlers.wordle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.wordlers.wordle.scenes.IntroScene;
import org.wordlers.wordle.structure.Words;

import java.util.Timer;
import java.util.TimerTask;

public class WordleApplication extends Application {

    private static Stage stage;
    public static Image backButtonImage ;

    @Override
    public void start(Stage stage) {
        WordleApplication.stage = stage;
        stage.setTitle("Wordle");
        stage.setScene(IntroScene.load());

        backButtonImage = new Image(getClass().getResourceAsStream("back_arrow_button.png"));
        stage.show();

        Words.load();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Changes the scene of the application.
     * @param scene The new scene.
     * @author Santio
     * @since Feb. 25, 2023
     */
    public static void setScene(Scene scene) {
        Platform.runLater(() -> {
            stage.setScene(scene);
        });
    }

    /**
     * Schedules a task to be run later
     * @param delay The delay in ms
     * @param run The method to run after the delay has elapsed
     * @since Apr. 4, 2023
     * @author Santio
     */
    public static void schedule(int delay, Runnable run) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                run.run();
            }
        }, delay);
    }

    /**
     * Closes the application
     * @since Apr. 6, 2023
     * @author Santio
     */
    public static void close() {
        stage.close();
    }

}