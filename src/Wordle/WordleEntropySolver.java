package Wordle;

import java.util.*;
import java.util.stream.Collectors;

public class WordleEntropySolver {

    // Convert feedback to string like "22010"
    static String getFeedback(String guess, String answer) {
        int[] feedback = new int[5];
        char[] answerChars = answer.toCharArray();
        boolean[] used = new boolean[5];

        // Greens
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                feedback[i] = 2;
                used[i] = true;
            }
        }

        // Yellows
        for (int i = 0; i < 5; i++) {
            if (feedback[i] == 0) {
                for (int j = 0; j < 5; j++) {
                    if (!used[j] && guess.charAt(i) == answerChars[j]) {
                        feedback[i] = 1;
                        used[j] = true;
                        break;
                    }
                }
            }
        }

        // Convert feedback to string
        StringBuilder sb = new StringBuilder();
        for (int n : feedback) sb.append(n);
        return sb.toString();
    }

    // Filter candidate words based on feedback
    static List<String> filterWords(String guess, String feedback, List<String> possibleWords) {
        return possibleWords.stream()
                .filter(w -> getFeedback(guess, w).equals(feedback))
                .collect(Collectors.toList());
    }

    // Compute entropy for a given guess
    static double entropyOfGuess(String guess, List<String> possibleWords) {
        Map<String, Integer> patternCounts = new HashMap<>();

        for (String answer : possibleWords) {
            String pattern = getFeedback(guess, answer);
            patternCounts.put(pattern, patternCounts.getOrDefault(pattern, 0) + 1);
        }

        double total = possibleWords.size();
        double entropy = 0.0;

        for (int count : patternCounts.values()) {
            double p = count / total;
            entropy += -p * (Math.log(p) / Math.log(2));
        }

        return entropy;
    }

    // Pick best guess (max entropy)
    static String bestGuess(List<String> possibleWords, List<String> allWords) {
        double bestScore = -1.0;
        String bestWord = allWords.get(0);

        for (String guess : allWords) {
            double ent = entropyOfGuess(guess, possibleWords);
            if (ent > bestScore) {
                bestScore = ent;
                bestWord = guess;
            }
        }
        return bestWord;
    }

    // Solve the Wordle
    static void solveWordle(String trueAnswer, List<String> allWords, List<String> possibleWords) {
        int round = 1;
        while (round < 7) {
            String guess = bestGuess(possibleWords, allWords);
            String feedback = getFeedback(guess, trueAnswer);
            System.out.printf("Round %d | Guess: %s | Feedback: %s%n", round, guess, feedback);
            System.out.println(feedback);
            if (feedback.equals("22222")) {
                System.out.println("Solved!");
                break;
            }

            possibleWords = filterWords(guess, feedback, possibleWords);
            round++;

            if (possibleWords.isEmpty()) {
                System.out.println("No possible words left. Something went wrong.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Tiny word list for testing
        List<String> words = new LoadWordlist("src/data/valid-wordle-words.txt").getWordlist();

        solveWordle("focal", words, new ArrayList<>(words));
    }
}
