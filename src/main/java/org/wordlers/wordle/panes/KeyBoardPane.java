package org.wordlers.wordle.panes;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.HashMap;
/**
 * Main Content of the game.
 * @author Devanshu
 * @since Mar. 31, 2023
 */
public class KeyBoardPane extends BorderPane {
    private final HashMap<String , LetterPane> letters = new HashMap<>();

    public KeyBoardPane() {

        HBox line1 = new HBox(
            pane("Q"),
            pane("W"),
            pane("E"),
            pane("R"),
            pane("T"),
            pane("Y"),
            pane("U"),
            pane("I"),
            pane("O"),
            pane("P")
        );
        HBox line2 = new HBox(
            pane("A"),
            pane("S"),
            pane("D"),
            pane("F"),
            pane("G"),
            pane("H"),
            pane("J"),
            pane("K"),
            pane("L")
        );
        HBox line3 = new HBox(
            pane("Z"),
            pane("X"),
            pane("C"),
            pane("V"),
            pane("B"),
            pane("N"),
            pane("M")
        );
        VBox vBox = new VBox(line1,line2,line3);
        
        line1.setAlignment(Pos.CENTER);
        line2.setAlignment(Pos.CENTER);
        line3.setAlignment(Pos.CENTER);
        
        line1.setSpacing(3);
        line2.setSpacing(3);
        line3.setSpacing(3);
        vBox.setSpacing(3);
        
        this.setCenter(vBox);

        vBox.setAlignment(Pos.CENTER);
        setAlignment(vBox, Pos.CENTER);
    }

    private LetterPane pane(String letter) {
        LetterPane pane = new LetterPane();
        pane.setLetter(letter);
        pane.setColor(Color.GRAY);
        pane.setMinSize(35,50);
        letters.put(letter,pane);
        return pane;
    }

    public HashMap<String, LetterPane> getLetters() {
        return letters;
    }
    
    public void animate(String letter) {
        LetterPane pane = letters.get(letter);
    
        ScaleTransition scale = new ScaleTransition(
            Duration.millis(100),
            pane
        );
        
        scale.setByX(0.1);
        scale.setByY(0.1);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        scale.play();
    }
    
}
