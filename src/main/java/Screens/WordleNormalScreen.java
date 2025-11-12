package Screens;

import Wordle.Wordle_Game;

public class WordleNormalScreen extends Screen
{

    public static void showMainMenu() {
        clearScreen();
        displayHeader("Wordle Game", "");

        System.out.printf("%-40s\n", "===========================================");
        System.out.printf("%-40s\n", "\t\t\t\tNormal Game");
        System.out.printf("%-40s\n\n", "===========================================");


        Wordle_Game.playGame(null);
        //MainScreen.showMainMenu();



    }

}
