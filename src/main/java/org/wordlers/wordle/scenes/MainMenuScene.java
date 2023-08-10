package org.wordlers.wordle.scenes;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.wordlers.wordle.WordleApplication;
import org.wordlers.wordle.builders.ButtonBuilder;
import org.wordlers.wordle.builders.TextBuilder;
import org.wordlers.wordle.structure.MusicPlayer;
import org.wordlers.wordle.structure.WindowSettings;

import java.util.List;

public final class MainMenuScene {

    private MainMenuScene() {}

    public static MusicPlayer player = new MusicPlayer().loop(true);

    public static Scene load() {
        GameScene.soundEffects.stop();
        GameScene.bgMusic.stop();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WindowSettings.WIDTH, WindowSettings.HEIGHT);

        HBox title = new HBox();
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(40, 0, 0, 0));

        title.getChildren().add(new TextBuilder("W", 75).color(Color.DARKGOLDENROD).family("Impact").build());
        title.getChildren().add(new TextBuilder("O", 75).family("Impact").build());
        title.getChildren().add(new TextBuilder("R", 75).color(Color.GREEN).family("Impact").build());
        title.getChildren().add(new TextBuilder("D", 75).family("Impact").build());
        title.getChildren().add(new TextBuilder("L", 75).color(Color.RED).family("Impact").build());
        title.getChildren().add(new TextBuilder("E", 75).family("Impact").build());

        Animation animation = runAnimation(title.getChildren());
        animation.setOnFinished(e -> animation.play());
        animation.play();

        root.setTop(title);
        BorderPane.setAlignment(root.getTop(), Pos.CENTER);

        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        root.setCenter(buttons);

        buttons.getChildren().addAll(
            new ButtonBuilder(new TextBuilder("PLAY", 24).build())
                .setOnClick(event -> WordleApplication.setScene(GameScene.load()))
                .build(),
//            new ButtonBuilder(new TextBuilder("HIGH SCORES", 24).build())
//                .setOnClick(event -> WordleApplication.setScene(GameScene.load()))
//                .build(),
            new ButtonBuilder(new TextBuilder("SETTINGS", 24).build())
                .setOnClick(event -> WordleApplication.setScene(SettingsScene.load()))
                .build(),
            new ButtonBuilder(new TextBuilder("CREDITS", 24).build())
                .setOnClick(event -> WordleApplication.setScene(CreditsScene.load()))
                .build(),
            new ButtonBuilder(new TextBuilder("EXIT GAME", 24).build())
                .setOnClick(event -> WordleApplication.close())
                .build()
        );

        return scene;
    }

    private static Transition runAnimation(List<Node> nodes) {
        SequentialTransition animation = new SequentialTransition();
        animation.setDelay(Duration.seconds(2));

        ParallelTransition scales = new ParallelTransition();
        animation.getChildren().add(scales);

        ParallelTransition shines = new ParallelTransition();
        animation.getChildren().add(shines);

        for (int i = 0; i<nodes.size(); i++) {
            Node node = nodes.get(i);

            ScaleTransition grow = new ScaleTransition(Duration.seconds(0.2), node);
            grow.setToX(1.2);
            grow.setToY(1.2);

            ScaleTransition shrink = new ScaleTransition(Duration.seconds(0.2), node);
            shrink.setToX(1);
            shrink.setToY(1);

            SequentialTransition scale = new SequentialTransition();
            scale.getChildren().addAll(grow, shrink);
            scale.setDelay(Duration.seconds(i*0.2));

            Color color = getColor(((Text) node).getFill());
            FillTransition white = new FillTransition(Duration.seconds(0.1), (Shape) node);
            white.setToValue(Color.LIGHTYELLOW);

            FillTransition original = new FillTransition(Duration.seconds(0.1), (Shape) node);
            original.setToValue(color);

            SequentialTransition shine = new SequentialTransition();
            shine.getChildren().addAll(white, original);
            shine.setDelay(Duration.seconds(i*0.05));

            scales.getChildren().addAll(scale);
            shines.getChildren().addAll(shine);
        }

        return animation;
    }

    private static Color getColor(Paint paint) {
        return Color.valueOf(paint.toString());
    }

}
