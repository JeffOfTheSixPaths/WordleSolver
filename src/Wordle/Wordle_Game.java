package Wordle;
public class Wordle_Game
{
    // the actual wordle game
    // uses a bunch of the other defined methods.
    //This is only the terminal version


    public static String currWord; // answer
    private static String userWord; // guess
    private static final int maxTries = 6; // max tries. easily adjustable for difficulty. should not be put lower than 4 otherwise not always possible to solve

    // plays the game
    public static void playGame(Algorithm a)
    {
        currWord = Seeds.getRandomWord(Seeds.getConstantSeededRandom()).toUpperCase(); // gets a random word


        System.out.println("Welcome to Wordle!");
        System.out.println("You have " + maxTries + " tries to guess the " + currWord.length() + "-letter word.");
        if(a != null) System.out.println("The most informative guess is " + a.bestGuess()); // if there's a passed algorithm, use it.
        System.out.println();

        //for every attempt get the word and then check pattern / correctness
        for (int attempt = 1; attempt <= maxTries; attempt++) {
            System.out.print("Attempt " + attempt + ": ");

            userWord = Validator.wordValidator().toUpperCase(); // makes sure to process it

            String[] feedback = Validator.checkGuess(currWord, userWord); // checks pattern
            feedback[5] = "\u001B[0m"; // appends a color
            for (String feed : feedback) {
                System.out.print(feed); // prints it out
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
