package Wordle;

import java.util.ArrayList;

public class BadAlgorithm extends Algorithm{
    // This is a testing Algorithm that is no longer in use

    // just has dummy functions that kind of work.
    public BadAlgorithm(ArrayList<String> words){
        super(words);
    }

    @Override
    public void applyPattern(String guess, String pattern) {
        // do NOTHING!!
        // just here to fill space and be "implemented"
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
