package org.wordlers.wordle.builders;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * A shorter and nicer way to construct Text objects.
 * Example:
 * <pre>
 *     BorderPane root = new BorderPane();
 *     root.setTop(
 *         new TextBuilder("Hello World!", 20)
 *             .family("Impact")
 *             .build()
 *     )</pre>
 * @author Santio
 * @since Feb. 25, 2023
 */
@SuppressWarnings("unused")
public class TextBuilder {
    
    private final Text text;
    private Font font = null;
    private Color color = null;
    
    public TextBuilder() {
        this.text = new Text();
    }
    
    public TextBuilder(String text) {
        this.text = new Text(text);
    }
    
    public TextBuilder(String text, int size) {
        this.text = new Text(text);
        this.font = new Font(size);
    }
    
    /**
     * Sets the text for this object
     * @param text The value to set the text to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public TextBuilder setText(String text) {
        this.text.setText(text);
        return this;
    }
    
    /**
     * Sets the font size for this object
     * @param size The value to set the font size to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public TextBuilder size(int size) {
        this.font = Font.font(family(), style(), size);
        return this;
    }
    
    /**
     * Sets the font family for this object
     * @param family The value to set the font family to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public TextBuilder family(String family) {
        this.font = Font.font(family, style(), size());
        return this;
    }
    
    /**
     * Sets the font style for this object
     * @param style The value to set the font style to
     * @return This object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public TextBuilder style(FontPosture style) {
        this.font = Font.font(family(), style, size());
        return this;
    }
    
    /**
     * Sets the color of the text
     * @param color The color to set
     * @return This object
     * @author Santio
     * @since Feb. 26, 2023
     */
    public TextBuilder color(Color color) {
        this.color = color;
        return this;
    }
    
    /**
     * Used for getting a font internally, you do not need to use this function
     * @return The current font size, or a new font if one doesn't exist.
     * @author Santio
     * @since Feb. 25, 2023
     */
    private Font getFontOrDefault() {
        return font == null ? Font.getDefault() : font;
    }
    
    /**
     * Gets the font size that is currently being used
     * @return The font's size
     * @author Santio
     * @since Feb. 25, 2023
     */
    public double size() {
        return getFontOrDefault().getSize();
    }
    
    /**
     * Gets the family of the font that is currently being used.
     * @return The font's font family
     * @author Santio
     * @since Feb. 25, 2023
     */
    public String family() {
        return getFontOrDefault().getFamily();
    }
    
    /**
     * Gets the style of the font that is currently being used.
     * @return The font's style as a FontPosture object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public FontPosture style() {
        return FontPosture.findByName(getFontOrDefault().getStyle());
    }
    
    /**
     * Gets the color of the text
     * @return The color of the text as a Color object
     * @author Santio
     * @since Feb. 26, 2023
     */
    public Color color() {
        return color;
    }
    
    /**
     * Builds the text object with the current font size. Use this when you are done building the text.
     * @return Text object
     * @author Santio
     * @since Feb. 25, 2023
     */
    public Text build() {
        if (font != null) text.setFont(font);
        if (color != null) text.setFill(color);
        return text;
    }
    
}
