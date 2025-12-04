package Wordle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/** This is the base algorithm class that the solving algorithm will inherient from
 *
 */
public abstract class Algorithm {
    // DEFINITION: the "value" is whatever the child class determins the value to be
    // for example the entropy algorithm says that the entropy (amount of information) is the value of the word.

    public List<String> words; // the list of words

    // meant to be used to store the guessed words
    // I don't believe it's used, but it was planned on being used
    public ArrayList<String> feedback;

    // stores the "value" of every word, so that it's easy to get the best word.
    public ArrayList<Double> infoValue;

    // basic constructors
    public Algorithm(List<String> words){
        this.words = words;
        feedback = new ArrayList<String>();
        infoValue = new ArrayList<Double>();
    }

    // applies the guess to thin the wordlist so that it doesn't have to go through ALL words everytime.
    public abstract void applyPattern(String guess, String pattern);

    // Calculates the "value" of the string s
    public abstract double calculate(String s);

    // automatically applies the calculate function to all of the words and stores them in infoValue ArrayList
    public void calculateValue(){
        for(int i = 0; i < words.size(); i++) infoValue.add(calculate(words.get(i)));
    }

    // gets the best guess, aka the word with the most "value"
    public abstract String bestGuess();
}
