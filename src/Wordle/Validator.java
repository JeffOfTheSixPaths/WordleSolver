package Wordle;

import util.InputValidator;

public class Validator
{
    // this validator helps make sure the inputted guess fits within our and wordle's rules.

    // gets a word from the command and checks to see if it's 5 letters.
    // I'm fairly sure this is only used in the terminal version.
    public static String wordValidator()
    {
        System.out.println("Enter your guess: ");
        String inWord = InputValidator.readString();
        char[] inWordArr = inWord.toCharArray();
        if(inWordArr.length != 5)
        {
            System.out.println("The word must contain 5 letters");
            return wordValidator();
        }
        return inWord;
    }


    // gets the color pattern for the guess
    // only used in the terminal. There is a different version for the GUI version.
    // currWord is the answer

    // just goes through and markes greens yellows and reds / greys.
    public static String[] checkGuess(String currWord, String userWord)
    {
        String[] result = new String[userWord.length() + 1];
        char[] userWordArr = userWord.toCharArray();
        boolean[] used = new boolean[currWord.length()];
        boolean[] green = new boolean[userWord.length()];

        // 1. Mark correct letters (greens)
        for (int i = 0; i < userWord.length(); i++) {
            if (userWord.charAt(i) == currWord.charAt(i)) {
                result[i] = "\u001B[42m" + userWordArr[i]; // the backslash u thing is just terminal color
                used[i] = true;
                green[i] = true;
            }
        }

        // 2. Mark present letters (yellows) or absents (grays)
        for (int i = 0; i < userWord.length(); i++) {
            if (green[i]) continue; // already handled

            boolean found = false;
            for (int j = 0; j < currWord.length(); j++) {
                if (!used[j] && userWord.charAt(i) == currWord.charAt(j)) {
                    found = true;
                    used[j] = true;
                    break;
                }
            }
            result[i] = found ? ("\u001B[43m" + userWordArr[i]) : ("\u001B[41m" + userWordArr[i]); // the backslash u thing is just terminal color
        }

        return result;
    }


    // This is the REAL version for sending the user the pattern of the guess
    // This uses letter_color to send the color to the GUI
    // overall works the same way where it marks greens then marks yellows.
    public static letter_color[] checkGuessColors(String word, String guess) {
        letter_color[] result = new letter_color[5];
        boolean[] used = new boolean[word.length()];

        // First pass: Mark greens
        for (int i = 0; i < word.length(); i++) {
            if (guess.charAt(i) == word.charAt(i)) {
                result[i] = letter_color.GREEN;
                used[i] = true;
            }
        }

        // Second pass: Mark yellows or reds
        for (int i = 0; i < word.length(); i++) {
            if (result[i] == letter_color.GREEN) continue;

            boolean foundYellow = false;
            for (int j = 0; j < word.length(); j++) {
                if (!used[j] && guess.charAt(i) == word.charAt(j)) {
                    foundYellow = true;
                    used[j] = true;
                    break;
                }
            }

            result[i] = foundYellow ? letter_color.YELLOW : letter_color.RED;
        }

        return result;
    }

}
