package Wordle;

import java.util.ArrayList;
import java.util.Random;

public class Seeds {
    private static final String wordListFileName = "data/valid-wordle-words.txt";


//    private final Random dailyRandomGenerator = getDailySeededRandom();
//    private Random getDailySeededRandom()
//    {
//        LocalDate today = LocalDate.now();
//
//        // This ensures the same seed for any time within the same day
//        long seed = today.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//        // Create a Random object with the daily seed
//        return new Random(seed);
//    }


    public static Random getConstantSeededRandom() {
        // Create a Random object with the current time in milliseconds
        return new Random(System.currentTimeMillis());
    }

    public static String getRandomWord(Random rand) {

        ArrayList<String> words = new LoadWordlist(wordListFileName).getWordlist();
        int randNum = rand.nextInt(words.size()) + 1;
        String word = words.get(randNum);

        if (word == null) {
            System.out.println("word is null trying to call func getRandWord");
            return getRandomWord(rand);
        } else {
            return word;

        }

//    public Random getDailyRandomGenerator() {return dailyRandomGenerator;}
    }
}
