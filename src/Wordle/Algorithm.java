package Wordle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/** This is the base algorithm class that the solving algorithm will inherient from
 *
 */
public abstract class Algorithm {
    public List<String> words;
    public ArrayList<String> feedback;
    public ArrayList<Double> infoValue;

    public Algorithm(List<String> words){
        this.words = words;
        feedback = new ArrayList<String>();
        infoValue = new ArrayList<Double>();
    }
    public abstract void applyPattern(String guess, String pattern);
    public abstract double calculate(String s);

    public void calculateValue(){
        for(int i = 0; i < words.size(); i++) infoValue.add(calculate(words.get(i)));
    }

    public abstract String bestGuess();
}
