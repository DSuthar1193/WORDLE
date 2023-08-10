package org.wordlers.wordle.structure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Words {
    
    private Words() {}
    
    private static final List<String> words = new ArrayList<>();
    private static final Random random = new Random();
    
    /**
     * Loads the words from the words.txt file.
     * This should only be ran once at the start of the program.
     * @author Santio
     * @since Mar. 4, 2023
     */
    public static void load() {
        try {
            // Clear the list just in case it's not empty
            words.clear();
            
            // Read the words from the file
            BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
            // Add each word to the list
            reader.lines().forEach(words::add);
            // Close the reader
            reader.close();
    
            System.out.println("Loaded " + words.size() + " words.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if the given word is a real 5-letter word.
     * @param word The word to check
     * @return If the word is a real 5-letter word
     * @author Santio
     * @since Mar. 4, 2023
     */
    public static boolean isWord(String word) {
        return words.contains(word.toLowerCase());
    }
    
    /**
     * Gets a random word, mostly used for debugging and testing.
     * @return A random 5-letter word from the words.txt file
     * @author Santio
     * @since Mar. 4, 2023
     */
    public static String randomWord() {
        return words.get(random.nextInt(words.size()));
    }
    
    /**
     * Gets a random word from the given file.
     * @param file The file to get the word from (.txt is automatically added)
     * @return A random word from the given file
     */
    public static String getWord(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file + ".txt"));
            List<String> words = reader.lines().toList();
            reader.close();
            
            return words.get(random.nextInt(words.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
