package Screens;

import Wordle.Algorithm;
import Wordle.BadAlgorithm;
import Wordle.LoadWordlist;
import Wordle.Wordle_Game;

import java.util.ArrayList;

public class WordleSolverScreen extends Screen{

    public static void showMainMenu() {
        clearScreen();
        displayHeader("Wordle Game", "");

        System.out.printf("%-40s\n", "===========================================");
        System.out.printf("%-40s\n", "\t\t\t\tNormal Game");
        System.out.printf("%-40s\n\n", "===========================================");

        Algorithm a = new BadAlgorithm((ArrayList<String>) new LoadWordlist().getWords());
        Wordle_Game.playGame(a);
        //MainScreen.showMainMenu();
    }

}
