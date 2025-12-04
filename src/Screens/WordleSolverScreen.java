package Screens;

import Wordle.*;

import java.util.ArrayList;

public class WordleSolverScreen extends Screen{

    public static void showMainMenu() {
        clearScreen();
        displayHeader("Wordle Game", "");

        System.out.printf("%-40s\n", "===========================================");
        System.out.printf("%-40s\n", "\t\t\t\tNormal Game");
        System.out.printf("%-40s\n\n", "===========================================");
        ArrayList<String> thing = (ArrayList<String>) new LoadWordlist().getWords();
//        Algorithm a = new EntropyAlg(thing, thing);
//        Wordle_Game.playGame(a);
        //MainScreen.showMainMenu();
    }

}
