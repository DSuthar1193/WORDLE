package org.wordlers.wordle.scenes;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.wordlers.wordle.WordleApplication;
import org.wordlers.wordle.builders.TextBuilder;
import org.wordlers.wordle.structure.WindowSettings;

public final class  CreditsScene {
    
    private CreditsScene() {}
    
    public static Scene load() {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
            Color.DIMGRAY,
            null,
            null
        )));
        Scene scene = new Scene(root, WindowSettings.WIDTH, WindowSettings.HEIGHT);
    
        VBox credits = new VBox();
        credits.setAlignment(Pos.CENTER);
        credits.getChildren().add(new TextBuilder("Wordle", 36).build());
        
        credits.getChildren().add(new TextBuilder("Group Members:", 36).build());
        credits.getChildren().add(new TextBuilder("Devanshu", 36).build());
        credits.getChildren().add(new TextBuilder("Nashwan", 36).build());
    
        credits.getChildren().add(new TextBuilder("Image Assets:", 36).build());
        credits.getChildren().add(new TextBuilder("From fonts.google.com/icons", 36).build());
    
        // Hide credits
        for (Node node : credits.getChildren()) {
            node.setTranslateY(WindowSettings.HEIGHT);
        }
        
        // Transitions
        SequentialTransition timeline = new SequentialTransition();
        
        ParallelTransition mainInfo = new ParallelTransition();
        timeline.getChildren().add(mainInfo);
        mainInfo.getChildren().addAll(
            up(credits.getChildren().get(0), true)
        );
    
        ParallelTransition members = new ParallelTransition();
        timeline.getChildren().add(members);
        members.getChildren().addAll(
            up(credits.getChildren().get(1)),
            up(credits.getChildren().get(2)),
            up(credits.getChildren().get(3))
        );
        
        ParallelTransition resources = new ParallelTransition();
        timeline.getChildren().add(resources);
        resources.getChildren().addAll(
            up(credits.getChildren().get(4)),
            up(credits.getChildren().get(5))
        );

        timeline.play();
        timeline.setOnFinished(e -> WordleApplication.setScene(MainMenuScene.load()));
        
        // Add to scene
        root.setCenter(credits);
        BorderPane.setAlignment(root.getCenter(), Pos.CENTER);
        return scene;
    }
    
    private static ParallelTransition up(Node node, boolean special) {
        ParallelTransition mix = new ParallelTransition();
        
        TranslateTransition transition = new TranslateTransition(
            Duration.seconds(4),
            node
        );
        transition.setToY(-100 - node.getTranslateY());
        mix.getChildren().add(transition);
        
        // Scale to be big
        if (special) {
            ScaleTransition scale = new ScaleTransition(
                Duration.seconds(1.6),
                node
            );
            scale.setToX(1.6);
            scale.setToY(1.6);
            mix.getChildren().add(scale);
        }
        
        return mix;
    }
    
    
    private static ParallelTransition up(Node node) {
        return up(node, false);
    }
    
}
