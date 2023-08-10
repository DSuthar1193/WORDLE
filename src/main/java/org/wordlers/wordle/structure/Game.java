package org.wordlers.wordle.structure;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    
    // POV: you wish you could use enums
    public static final int INVALID = 0;
    public static final int MISPLACED = 1;
    public static final int CORRECT = 2;
    
    public static final Random random = new Random();
    
    // Game data
    public List<Character> entered = new ArrayList<>();
    private final String currentWord;
    public int guesses;
    
    public Game(String currentWord, int guesses) {
        this.currentWord = currentWord.toLowerCase();
        this.guesses = guesses;
    }
    
    public Game(String currentWord) {
        this(currentWord, 6);
    }
    
    public Game() {
        this(Words.randomWord(), 6);
    }
    
    public int checkCharacter(char c, int index) {
        if (index >= currentWord.length()) return INVALID;
        if (currentWord.charAt(index) == c) return CORRECT;
        if (currentWord.contains(""+c)) return MISPLACED;
        return INVALID;
    }
    
    public static Paint getColor(int status) {
        return switch (status) {
            case INVALID -> Color.web("#777777");
            case MISPLACED -> Color.web("#ff9933");
            case CORRECT -> Color.web("#339933");
            default -> Color.web("#000000");
        };
    }
    
    public List<Integer> guess(String word) {
        List<Integer> statuses = new ArrayList<>();
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.toLowerCase().charAt(i);
            int status = checkCharacter(c, i);
            statuses.add(status);
            if (!entered.contains(c)) entered.add(c);
        }
        
        return statuses;
    }
    
}
