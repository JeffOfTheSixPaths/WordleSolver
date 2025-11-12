package edu.utsa.cs3443.wordsolverjavafx.model.Wordle;

import edu.utsa.cs3443.wordsolverjavafx.model.utilities.InputValidator;

public class Validator
{

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

    public static String[] checkGuess(String currWord, String userWord)
    {
        String[] result = new String[userWord.length() + 1];
        char[] userWordArr = userWord.toCharArray();
        boolean[] used = new boolean[currWord.length()];
        boolean[] green = new boolean[userWord.length()];

        // 1. Mark correct letters (greens)
        for (int i = 0; i < userWord.length(); i++) {
            if (userWord.charAt(i) == currWord.charAt(i)) {
                result[i] = "\u001B[42m" + userWordArr[i];
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
            result[i] = found ? ("\u001B[43m" + userWordArr[i]) : ("\u001B[41m" + userWordArr[i]);
        }

        return result;
    }

}
