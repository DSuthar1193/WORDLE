package org.wordlers.wordle.builders;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.function.Consumer;

/**
 * A shorter and nicer way to construct Button objects.
 * Example:
 * <pre>
 *     BorderPane root = new BorderPane();
 *     root.setCenter(new ButtonBuilder(
 *         new TextBuilder("Hello World!", 20)
 *     ).setOnClick(e -> {
 *        System.out.println("Hello World!");
 *     }))</pre>
 * @author Santio
 * @since Feb. 25, 2023
 */
@SuppressWarnings("unused")
public class ButtonBuilder {
    
    private final Button button;
    private String backgroundColor = "#e7d1ff";
    private String borderColor = "#1b0d29";
    
    public ButtonBuilder() {
        this.button = new Button();
    }
    
    public ButtonBuilder(String text) {
        this.button = new Button(text);
    }
    
    public ButtonBuilder(Text text) {
        this.button = new Button();
        this.button.setGraphic(text);
    }
    
    /**
     * Sets the text for this object
     * @param text The value to set the text to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public ButtonBuilder setText(String text) {
        this.button.setText(text);
        return this;
    }
    
    /**
     * Sets the text for this object
     * @param text The value to set the text to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public ButtonBuilder setText(Text text) {
        this.button.setGraphic(text);
        return this;
    }
    
    /**
     * Adds an on click event to this object
     * @param runnable The runnable to run when the button is clicked
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public ButtonBuilder setOnClick(Consumer<ActionEvent> runnable) {
        this.button.setOnAction(runnable::accept);
        return this;
    }
    
    /**
     * Gets the background color as a Background object
     * @return The background color as a Background object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }
    
    /**
     * Sets the background color for this object
     * @param color The value to set the background color to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public ButtonBuilder setBackgroundColor(String color) {
        this.backgroundColor = color;
        return this;
    }
    
    /**
     * Gets the border color
     * @return The border color
     * @author Santio
     * @since Feb. 26, 2023
     */
    public String getBorderColor() {
        return borderColor;
    }
    
    /**
     * Sets the hover color for this object
     * @param color The value to set the hover color to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public ButtonBuilder setBorderColor(String color) {
        this.borderColor = color;
        return this;
    }
    
    /**
     * Builds the button
     * @return The button object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public Button build() {
        this.button.setStyle(
            "-fx-background-radius: 5em;\n" +
            "-fx-border-radius: 5em;\n" +
            "-fx-background-color: transparent;\n" +
            "-fx-border-color: transparent;\n" +
            "-fx-border-width: 2px;\n" +
            "-fx-padding: 0.2em 0;\n"+
            "-fx-max-width: 20em;"
        );
        
        this.button.setOnMouseEntered(event -> this.button.setStyle(
            "-fx-background-radius: 5em;\n" +
            "-fx-border-radius: 5em;\n" +
            "-fx-background-color: " + getBackgroundColor() + ";\n" +
            "-fx-border-color: " + getBorderColor() + ";\n" +
            "-fx-border-width: 2px;\n" +
            "-fx-padding: 0.2em 0;\n"+
            "-fx-max-width: 20em;"
        ));
        
        this.button.setOnMouseExited(event -> this.button.setStyle(
            "-fx-background-radius: 5em;\n" +
            "-fx-border-radius: 5em;\n" +
            "-fx-background-color: transparent;\n" +
            "-fx-border-color: transparent;\n" +
            "-fx-border-width: 2px;\n" +
            "-fx-padding: 0.2em 0;\n"+
            "-fx-max-width: 20em;"
        ));
        
        return this.button;
    }
}
