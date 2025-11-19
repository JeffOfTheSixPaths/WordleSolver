package Screens;

import util.InputValidator;
import util.UserManager;

import java.io.IOException;
import java.util.Scanner;

public class SignInScreen extends Screen
{
    private static UserManager manager;


    private enum enSignInOptions
    {
        eSkip,
        eSignUp,
        eLogIn,
        eExit
    }

    public static void showSignIn()
    {
        manager = new UserManager();
        manager.loadUsers();

        clearScreen();
        displayHeader("SignIn Screen", "");

        System.out.printf( "%-40s\n",  "===========================================");
        System.out.printf( "%-40s\n",  "\t\t\t\tSignIn Menu");
        System.out.printf( "%-40s\n",  "===========================================");
        System.out.printf( "%-40s\n",  "\t[1] Sign Up");
        System.out.printf( "%-40s\n",  "\t[2] Log In");
        System.out.printf( "%-40s\n",  "\t[3] Exit");
        System.out.printf( "%-40s\n",  "===========================================");

        performSignInOption(readSignInOption());
    }

    private static void performSignInOption(enSignInOptions mainMenuOption)
    {
        switch (mainMenuOption) {
            case eSignUp:
                clearScreen();
                showSignUpScreen();
                break;

            case eLogIn:
                clearScreen();
                showLogInScreen();
                break;

            case eExit:
                clearScreen();
                logout();
                break;
        }
    }

    private static enSignInOptions readSignInOption()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%-40s\n",  "Choose what you want to do? [1 to 3]? ");
        int choice = InputValidator.readIntNumberBetween(1, 3, "Invalid option. Enter a valid option");
        for(enSignInOptions e : enSignInOptions.values())
        {
            if (choice == e.ordinal())
            {
                return e;
            }
        }
        return enSignInOptions.eSkip;
    }

    public static void backToSignIn()
    {
        System.out.printf( "\n\t%-40s\n", "Press enter to go back to SignIn Menu...");
        InputValidator.readString();
        showSignIn();
    }

    private static void showSignUpScreen()
    {
        SignUpScreen.showSignIn(manager);
    }

    private static void showLogInScreen()
    {
        LogInScreen.showLogIn(manager);
    }

    private static void logout(){ }


}
