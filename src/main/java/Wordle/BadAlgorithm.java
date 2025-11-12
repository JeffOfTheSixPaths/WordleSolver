package Wordle;

import java.util.ArrayList;

public class BadAlgorithm extends Algorithm{
    public BadAlgorithm(ArrayList<String> words){
        super(words);
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
