import Screens.MainScreen;
import Screens.SignInScreen;
import Wordle.Wordle_Game;
import util.User;

public class Main {
    public static void main(String[] args) {

        //Wordle_Game.playGame();

        //User user = new User("test2", "1234");
        SignInScreen.showSignIn();
        //MainScreen.showMainMenu(user);


//        String redB = "\u001B[41m";
//        String greenB = "\u001B[42m";
//        String yellowB = "\u001B[43m";
//
//        String resetB = "\u001B[0m";
//
//        System.out.printf("%sA%s", redB, resetB);

        //System.out.println("\u001B[42m-A-\u001B[0m");
    }
}