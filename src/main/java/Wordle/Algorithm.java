package Wordle;

import java.util.ArrayList;

/** This is the base algorithm class that the solving algorithm will inherient from
 *
 */
public abstract class Algorithm {
    ArrayList<String> words;
    ArrayList<String> feedback;
    ArrayList<Double> infoValue;

    public Algorithm(ArrayList<String> words){
        this.words = words;
        feedback = new ArrayList<String>();
        infoValue = new ArrayList<Double>();
    }

    public abstract double calculate(String s);

    public void calculateValue(){
        for(int i = 0; i < words.size(); i++) infoValue.add(calculate(words.get(i)));
    }

    public abstract String bestGuess();
}
