package Screens;

import util.User;

public class WordleNormalScreen extends Screen
{

    public static void showMainMenu(User currUser) {
        clearScreen();
        displayHeader("Wordle Game", "");

        System.out.printf("%-40s\n", "===========================================");
        System.out.printf("%-40s\n", "\t\t\t\tNormal Game");
        System.out.printf("%-40s\n\n", "===========================================");

        System.out.print("Enter your Guess word: \n");


    }

}
