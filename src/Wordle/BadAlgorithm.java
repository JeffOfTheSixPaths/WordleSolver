package Wordle;

import java.util.ArrayList;

public class BadAlgorithm extends Algorithm{
    public BadAlgorithm(ArrayList<String> words){
        super(words);
    }

    @Override
    public void applyPattern(String guess, String pattern) {
        // do NOTHING!!
    }

    @Override
    public double calculate(String s) {
        return 0;
    }

    @Override
    public String bestGuess() {
        return "TESTT";
    }


}
