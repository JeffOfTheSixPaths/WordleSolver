package Wordle;
public class Wordle_Game
{
    private static String currWord;
    private static String userWord;

    private static final int maxTries = 6;


    public static void playGame()
    {
        currWord = Seeds.getRandomWord(Seeds.getConstantSeededRandom()).toUpperCase();


        System.out.println("Welcome to Wordle!");
        System.out.println("You have " + maxTries + " tries to guess the " + currWord.length() + "-letter word.");
        System.out.println();

        for (int attempt = 1; attempt <= maxTries; attempt++) {
            System.out.print("Attempt " + attempt + ": ");
            userWord = Validator.wordValidator().toUpperCase();
            String[] feedback = Validator.checkGuess(currWord, userWord);
            feedback[5] = "\u001B[0m";
            for (String feed : feedback) {
                System.out.print(feed);
            }
            System.out.println();

            if (userWord.equals(currWord)) {
                System.out.println("Correct! You guessed the word in " + attempt + " tries!");
                //user.incrementSolvedWordles();
                return;
            }
            if(attempt == maxTries) {
                System.out.println("💀 You’ve run out of tries. The word was: " + currWord);
                return;
            }
        }


    }




}
