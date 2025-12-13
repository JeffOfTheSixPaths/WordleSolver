package Wordle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntropyAlg extends Algorithm {

    // This assumes a BASIC understanding of Wordle already.


    // The Entropy algorithm
    // this algorithm goes through each word and calculates its "entropy"
    // in information theory entropy is akin to just how much information the word gives
    // the entropy has the unit of bits so that's what it displays


    /*
    the way it calculates the entropy is that for every word AND for every pattern of that word
    it find the porportion of words that would fit into that pattern AND how likely that pattern is
    it then "averages" (oversimplification) them together.
     */


    // uses the same constructor as its parrent class which just assigns the wordlist
    // the second ArrayList is an artifact from a different way we were coding this. it is now not used.
    public EntropyAlg(ArrayList<String> words, ArrayList<String> nothing){
        super(words);
    }

    // this thins the wordlist to being words that only fit the given pattern
    // the `pattern` will be given from the game
    public void applyPattern(String guess, String pattern) {
        ArrayList<String> newList = new ArrayList<>();

        for (String answer : this.words) {
            // `pattern()` just sees if the pattern for that word would be the same with this guess's pattern.
            if (pattern(guess, answer).equals(pattern)) {
                newList.add(answer); // adds it to a newlist before assigning that list to the words.
            }
        }

        this.words = newList;  // shrink remaining possibilities
    }


    // gets the pattern from the guess and the answer
    // the pattern is how "good" the guess is.
    // It's just how wordle gives Yellow, Green, and Grey letters
    // This uses a different mapping for 2 - Green  1 - Yello and   0 - Grey.
    public String pattern(String guess, String answer) {
        int n = guess.length();
        int[] result = new int[n];
        boolean[] used = new boolean[n];

        // First pass: mark greens
        for (int i = 0; i < n; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                result[i] = 2;
                used[i] = true;
            }
        }

        // Second pass: mark yellows
        for (int i = 0; i < n; i++) {
            if (result[i] == 0) { // not green (not at the same place)
                for (int j = 0; j < n; j++) {
                    if (!used[j] && guess.charAt(i) == answer.charAt(j)) {
                        result[i] = 1;
                        used[j] = true;
                        break;
                    }
                }
            }
        }

        // Convert result to pattern string
        StringBuilder sb = new StringBuilder();
        for (int v : result) sb.append(v);
        return sb.toString();
    }


    // checks whether a word would be possible with a specific guess
    // I think this has a lot of overlap with `pattern()` because we were unsure about how this would break our game
    // and didn't want to risk it.
    public String possible(String guess, String answer) {
        // Simple example pattern: same letter in same position = '1', else '0'
        // Replace with your actual game's pattern logic.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            sb.append(guess.charAt(i) == answer.charAt(i) ? '1' : '0');
        }
        return sb.toString();
    }


    // This is how it calculates the entropy for a specific word
    // uses a hashmap to keep track of the amount of words that are possible for each available pattern.
    // It then uses this information to formally calculate entropy, which is just an Information Theory definition.
    @Override
    public double calculate(String s) {
        Map<String, Integer> patternCounts = new HashMap<>();

        for (String answer : this.words) {
            String p = possible(s, answer); // if the guess, `answer`, is possible with the given word `s`.
            patternCounts.put(p, patternCounts.getOrDefault(p, 0) + 1); // then add to the amount of words this pattern gives
        }

        double total = this.words.size(); // gets total size
        double entropy = 0.0;


        // literally just the formal definition for entropy
        // nothing to explain, this is actually just the formula.
        // I don't know the derivation because I don't know information theory
        // I just know this IS the formula for entropy.
        for (int count : patternCounts.values()) {
            double p = count / total;
            entropy += -p * (Math.log(p) / Math.log(2));  // log2
        }

        return entropy;
    }


    //gets the highest entropy word.
    @Override
    public String bestGuess() {
        String bestGuess = null; // default values, super useful when debugging earlier
        double bestEntropy = -1.0;


        // Finds max entropy.
        for (String guess : words) {
            double h = calculate(guess);
            if (h > bestEntropy) {
                bestEntropy = h;
                bestGuess = guess;
            }
        }

        System.out.println("Best entropy = " + bestEntropy);
        return bestGuess;
    }
}
