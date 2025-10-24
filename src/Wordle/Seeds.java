package Wordle;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Seeds
{
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


    public static Random getConstantSeededRandom()
    {
        // Create a Random object with the current time in milliseconds
        return new Random(System.currentTimeMillis());
    }

    public static String getRandomWord(Random rand)
    {
        Scanner scan = null;
        int randNum = rand.nextInt(14854) + 1;
        int i = 1;
        String word = null;
        try
        {
            String line;
            scan = new Scanner(new File(wordListFileName));
            while(i < randNum && scan.hasNextLine())
            {
                scan.nextLine();
                i++;
            }
            System.out.println("Just Test: " + randNum);
            word = scan.nextLine();
        }
        catch (IOException e)
        {
            System.out.println("Error reading Word List file: " + e.getMessage());
        }
        finally { scan.close(); }

        if(word == null)
            return getRandomWord(rand);
        else
            return word;

    }

//    public Random getDailyRandomGenerator() {return dailyRandomGenerator;}
}
