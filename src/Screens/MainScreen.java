package Screens;
import util.InputValidator;
import util.User;


import java.util.Scanner;

public class MainScreen extends Screen
{


    private enum enMainMenuOptions
    {
        eSkip,
        eSolver,
        eNormal,
        eWordList,
        eExit
    }

    public static void showMainMenu(User currUser)
    {
        clearScreen();
        displayHeader("Main Screen", "");

        System.out.printf("%-40s\n", "===========================================");
        System.out.printf("%-40s\n", "\t\t\t\tMain Menu");
        System.out.printf("%-40s\n", "===========================================");
        System.out.printf("\t%s\n", currUser.getUserName());
        System.out.printf("\tAccount Age: %d days\n", currUser.getAccAge());
        System.out.printf("\tSolves: %d\n", currUser.getSolvedWordles());
        System.out.printf("\tCheated Solves: %d (%.02f%%)\n", currUser.getHelpedWordles(), currUser.getCheatedWordles());
        System.out.printf("%-40s\n", "\tWordlistPath: ");
        //System.out.printf( "
        System.out.printf("%-40s\n", "===========================================");
        System.out.printf("%-40s\n", "\t[1] Use Solver Tool");
        System.out.printf("%-40s\n", "\t[2] Play Normal Game");
        System.out.printf("%-40s\n", "\t[3] Wordlist Path");
        System.out.printf("%-40s\n", "\t[4] Log Out");
        System.out.printf("%-40s\n", "===========================================");

        performMainMenuOption(readMainMenuOption());
    }

    private static void performMainMenuOption(enMainMenuOptions mainMenuOption) {
        switch (mainMenuOption) {
            case eSolver:
                clearScreen();
                showSolverGameScreen();
                break;

            case eNormal:
                clearScreen();
                showNormalGameScreen();
                break;

            case eWordList:
                clearScreen();
                showWordListScreen();
                break;

            case eExit:
                clearScreen();
                logout();
                break;
        }
    }

    private static enMainMenuOptions readMainMenuOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%-40s\n", "Choose what you want to do? [1 to 4]? ");
        int choice = InputValidator.readIntNumberBetween(1, 4, "Invalid option. Enter a valid option");
        for (enMainMenuOptions e : enMainMenuOptions.values()) {
            if (choice == e.ordinal()) {
                return e;
            }
        }
        return enMainMenuOptions.eSkip;
    }

    private static void backToMainMenu(User currUser) {
        System.out.printf("\n\t%-40s\n", "Press enter to go back to Main Menu...");
        InputValidator.readString();
        showMainMenu(currUser);
    }

    private static void showSolverGameScreen() {
        System.out.println("Solver Game Will Show Here ...");
    }

    private static void showNormalGameScreen() {
        WordleNormalScreen.showMainMenu();
    }

    private static void showWordListScreen() {
        System.out.println("Word List Screen Will Show Here ...");
    }


    private static void logout() {
        SignInScreen.backToSignIn();
    }
}


