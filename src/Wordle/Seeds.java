package Wordle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Seeds {
    // helps randomly get a random word.
    private static final String wordListFileName = "/data/valid-wordle-words.txt";


    public static Random getConstantSeededRandom() {
        // Create a Random object with the current time in milliseconds
        return new Random(System.currentTimeMillis());
    }

    public static String getRandomWord(Random rand) {

        List<String> words = new LoadWordlist().getWords();
        int randNum = rand.nextInt(words.size()) + 1;
        String word = words.get(randNum); // gets random word using the rand.nextInt() function

        if (word == null) { // makes sure to see if the word bugged out or not.
            System.out.println("word is null trying to call func getRandWord");
            return getRandomWord(rand);
        } else {
            return word;

        }

    }
}
