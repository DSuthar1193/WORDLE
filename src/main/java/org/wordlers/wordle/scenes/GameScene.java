package org.wordlers.wordle.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import org.wordlers.wordle.WordleApplication;
import org.wordlers.wordle.builders.TextBuilder;
import org.wordlers.wordle.panes.ContentPane;
import org.wordlers.wordle.panes.KeyBoardPane;
import org.wordlers.wordle.panes.LetterPane;
import org.wordlers.wordle.structure.Game;
import org.wordlers.wordle.structure.MusicPlayer;
import org.wordlers.wordle.structure.WindowSettings;
import org.wordlers.wordle.structure.Words;

import java.io.File;
import java.util.List;

public final class GameScene {
    
    private static int currentIndex = 0;
    private static int guessed = 0;
    private static String word = "";
    public static MusicPlayer soundEffects = new MusicPlayer().volume(20);
    public static MusicPlayer bgMusic = new MusicPlayer().loop(true);

    private GameScene() {}

    public static Scene load() {
        MainMenuScene.player.stop();

        currentIndex = 0;
        guessed = 0;

        Game game = new Game(Words.getWord("choices"));
        bgMusic.play(new File("sounds/game_music.mp3"));
        
        // Make the root pane
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WindowSettings.WIDTH, WindowSettings.HEIGHT);
    
        // Set the title
        root.setTop(new TextBuilder("Wordle Game", 36).build());
        BorderPane.setAlignment(root.getTop(), Pos.CENTER);
    
        // Add the letter grid
        ContentPane grid = new ContentPane(5,6);
        root.setCenter(grid);
        BorderPane.setAlignment(root.getCenter(), Pos.CENTER);
        
        // Add the keyboard
        KeyBoardPane keyboard = new KeyBoardPane();
        root.setBottom(keyboard);
        BorderPane.setAlignment(root.getBottom(), Pos.CENTER);
        
        // Listen to key presses
        root.setOnKeyPressed(e -> {
            // Get the key pressed
            KeyCode key = e.getCode();
            
            // If it's the backspace key, we remove the last letter
            if (key == KeyCode.BACK_SPACE && currentIndex > 0) {
                if (word.length() == 0) return;
                
                currentIndex--; // Go back one letter
                grid.getLetters().get(currentIndex).setLetter(""); // Remove the letter from the grid
                word = word.substring(0, word.length() - 1); // Remove the last letter
                return;
            }
            
            // If the key is the enter key, we send it to the game handler
            if (key == KeyCode.ENTER) {
                // Get the index of the beginning of the word
                int index = currentIndex - word.length();
                
                // Check if the word is valid
                System.out.println("Guessing: " + word);
                if (word.length() < grid.getGameWidth() || !Words.isWord(word)) {
                    soundEffects.play(new File("sounds/error.mp3"));
                    for (int i = 0; i < grid.getGameWidth(); i++) {
                        int letterIndex = index + i;
                        grid.shake(letterIndex);
    
                        // Flash red and then back to the original color
                        int finalI = i;
                        // Delay the task by 50 milliseconds for each letter
                        WordleApplication.schedule(25 * i, () -> {
                            // Get the color of the letter
                            Paint color = grid.getLetters().get(letterIndex).getColor();
                            // Set the color to red
                            grid.getLetters().get(letterIndex).setColor(Paint.valueOf("#cc2222"));
                            
                            // Delay the task by 100 milliseconds for each letter after it has all been set to red
                            WordleApplication.schedule(100 + (25 & grid.getGameWidth()) + (25 * finalI), () -> {
                                // Set the color back to the original color
                                grid.getLetters().get(letterIndex).setColor(color);
                            });
                        });
                    }
                    return;
                }
                
                // Get the result of the word
                guessed++;
                List<Integer> results = game.guess(word);
                
                // Color and animate each letter
                for (int i = 0; i < word.length(); i++) {
                    int finalI = i;
                    int letterIndex = index + i;

                    WordleApplication.schedule(400 * i, () -> {
                        // Update keyboard to reflect color
                        String letter = grid.getLetters().get(letterIndex).getLetter();
                        
                        // Get the color, if it's gray then use a darker gray
                        Paint color = results.get(finalI) == 0
                            ? Paint.valueOf("#555555")
                            : Game.getColor(results.get(finalI));
                        
                        // Set the color of the letter
                        keyboard.getLetters().get(letter).setColor(color);
                        
                        // Animate the letter
                        keyboard.animate(letter);
                        
                        // Keyboard done, moving on to the grid
                        // Play the animation then color it halfway through
                        grid.animateGuess(letterIndex);

                        WordleApplication.schedule(200, () -> {
                            grid.getLetters().get(letterIndex).setColor(
                                Game.getColor(results.get(finalI))
                            );
                        });
                    });
                }
                
                // All done, reset the word
                word = "";
                
                // Check if they won
                boolean won = true;
                for (int result : results) {
                    if (result != Game.CORRECT) {
                        won = false;
                        break;
                    }
                }
    
                boolean finalWon = won;
                WordleApplication.schedule(400 * grid.getGameWidth(), () -> {
                    if (finalWon) {
                        soundEffects.play(new File("sounds/right.mp3"));
                        WordleApplication.setScene(WinScene.load());
                        System.out.println("Game State: WIN");
                        return;
                    }
    
                    // Check if the game is over
                    if (guessed == grid.getGameHeight()) {
                        System.out.println("Game State: LOSE");
                        WordleApplication.setScene(LoseScene.load());
                    }
                });
                
                return;
            }
            
            // If it isn't a letter, we ignore it
            if (!key.isLetterKey()) return;
            
            // Make it they stop typing if they've typed the whole word
            if (word.length() >= grid.getGameWidth()) return;
            
            // Get the next available grid spot
            LetterPane letter = grid.getLetters().get(currentIndex);
            
            // Set the letter to the key pressed
            letter.setLetter(key.getChar());
            
            // Add the letter to the word
            word += key.getChar();
            
            // Animate the letter
            grid.animatePress(currentIndex);
            currentIndex++;
        });
        
        root.requestFocus();
        
        return scene;
    }
    
}
