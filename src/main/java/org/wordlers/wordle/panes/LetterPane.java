package org.wordlers.wordle.panes;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.wordlers.wordle.structure.Game;

/**
 * Holds the letter in a box.
 * @author Devanshu
 * @since Mar. 31, 2023
 */

public class LetterPane extends StackPane {

    private String letter = "";
    private Paint color;

    private Text text = new Text();

    public LetterPane() {

        this.setMinSize(50,50);

        if (color == null) {
            this.setBorder(new Border(new BorderStroke(
                    letter.equals("") ? Color.GRAY : Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    null,
                    new BorderWidths(1)
            )));
        } else {
            this.setBackground(new Background(new BackgroundFill(color,null,null)));
        }
        text.setFont(Font.font("Arial",FontWeight.BOLD,24));
        text.setFill(color == null ? Color.BLACK : Color.WHITE);
       this.getChildren().add(text);

    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
        text.setText(letter);
        this.setBorder(new Border(new BorderStroke(
                letter.equals("") ? Color.GRAY : Color.BLACK,
                BorderStrokeStyle.SOLID,
                null,
                new BorderWidths(1)
        )));
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        // Ignore if the color is already correct
        if (this.color == Game.getColor(Game.CORRECT)) return;
        
        if (color == null) {
            this.setBorder(new Border(new BorderStroke(
                letter.equals("") ? Color.GRAY : Color.BLACK,
                BorderStrokeStyle.SOLID,
                null,
                new BorderWidths(1)
            )));
            this.setBackground(null);
        } else {
            this.setBackground(new Background(new BackgroundFill(color,null,null)));
            this.setBorder(null);
        }
        
        this.color = color;
        text.setFill(color == null ? Color.BLACK : Color.WHITE);
    }
}
