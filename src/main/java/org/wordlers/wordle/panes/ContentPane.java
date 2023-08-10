package org.wordlers.wordle.panes;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.wordlers.wordle.structure.Game;

import java.util.ArrayList;

/**
 * Main Content of the game.
 * @author Devanshu
 * @since Mar. 31, 2023
 */
public class ContentPane extends GridPane {

    private final int width;
    private final int height;
    private final ArrayList<LetterPane> letters = new ArrayList<>();
    
    public ContentPane(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.setVgap(5);
        this.setHgap(5);
        
        // Center everything
        this.setAlignment(Pos.CENTER);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                LetterPane letter = new LetterPane();
                letters.add(letter);
                this.add(letter,x,y);
            }
        }
    }

    public ArrayList<LetterPane> getLetters() {
        return letters;
    }
    
    public int getGameWidth() {
        return width;
    }
    
    public int getGameHeight() {
        return height;
    }
    
    public void animatePress(int index) {
        LetterPane letter = letters.get(index);
        
        ScaleTransition scale = new ScaleTransition(
            Duration.millis(100),
            letter
        );
        
        scale.setByX(0.1);
        scale.setByY(0.1);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        
        scale.play();
    }
    
    public void animateGuess(int index) {
        LetterPane letter = letters.get(index);
        
        ScaleTransition scale = new ScaleTransition(
            Duration.millis(250),
            letter
        );
        
        scale.setFromY(1);
        scale.setByY(-1);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        
        scale.play();
    }
    
    public void shake(int index) {
        LetterPane letter = letters.get(index);
        
        TranslateTransition translate = new TranslateTransition(
            Duration.millis(80),
            letter
        );
        
        translate.setFromX(0);
        translate.setFromY(0);
        translate.setByX(Game.random.nextInt(1, 7) - 3d);
        translate.setByY(Game.random.nextInt(1, 7) - 3d);
        translate.setCycleCount(4);
        translate.setAutoReverse(true);
        
        translate.play();
    }

    public void set(String text) {
        for (int i = 0; i < text.length(); i++) {
            letters.get(i).setLetter("" + text.charAt(i));
        }
    }
    
}
