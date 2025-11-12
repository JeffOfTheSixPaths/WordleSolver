package Screens;

import util.InputValidator;
import util.User;
import util.UserManager;

import java.util.Scanner;

public class LogInScreen extends Screen
{
    private static UserManager manager;

    private enum enLogInOptions
    {
        eSkip,
        eLogIn,
        eCreateAccount,
        eExit
    }

    public static void showLogIn(UserManager mng)
    {
        manager = mng;


        clearScreen();
        displayHeader("LogIn Screen", "");

        System.out.printf("%-40s\n",  "===========================================");
        System.out.printf("%-40s\n",  "\t\t\t\tLog In");
        System.out.printf("%-40s\n",  "===========================================");
        System.out.printf("%-40s\n",  "\t[1] Log In");
        System.out.printf("%-40s\n",  "\t[2] Create an Account");
        System.out.printf("%-40s\n",  "\t[3] Exit");
        System.out.printf("%-40s\n",  "===========================================");
        performLogInOption(readLogInOption());
    }


    private static void performLogInOption(enLogInOptions logInOption) {
        switch (logInOption) {
            case eLogIn:
                clearScreen();
                performUsernameVerification(askUserName());
                break;

            case eCreateAccount:
                clearScreen();
                SignUpScreen.showSignIn(manager);
                break;

            case eExit:
                clearScreen();
                logout();
                break;
        }
    }

    private static enLogInOptions readLogInOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%-40s\n", "Choose what you want to do? [1 to 3]? ");
        int choice = InputValidator.readIntNumberBetween(1, 3, "Invalid option. Enter a valid option");
        for (enLogInOptions e : enLogInOptions.values()) {
            if (choice == e.ordinal()) {
                return e;
            }
        }
        return enLogInOptions.eSkip;
    }




    private static String askUserName()
    {
        System.out.println("Please enter your username: ");
        return InputValidator.readString();
    }

    private static void performUsernameVerification(String userName)
    {
        if(!manager.isUserExists(userName))
        {
            System.out.println("This username is not assigned with any existing account please try again or create a new account.");
            showLogIn(manager);
        }
        else
        {
            askPassword(manager.findUser(userName));
        }
    }

    private static void askPassword(User user)
    {
        String password;
        System.out.println("Please enter your password: ");
        password = InputValidator.readString();
        if(password.equals(user.getPassword()))
        {
            System.out.println("Logging In ...");
            MainScreen.showMainMenu(user);
        }
        else
        {
            System.out.println("Wrong password, please try again.");
            askPassword(user);
        }
    }

    private static void logout() { }
}
