package Screens;

import util.InputValidator;
import util.User;
import util.UserManager;

import java.util.Scanner;

public class  SignUpScreen extends Screen
{
    private static UserManager manager;
    private static String username;
    private static String password;

    private enum enSignUpOptions
    {
        eSkip,
        eCreateAccount,
        eBack,
        eExit
    }

    public static void showSignIn(UserManager mng) {

        manager = mng;


        clearScreen();
        displayHeader("Sign Up Screen", "");

        System.out.printf( "%-40s\n",  "===========================================");
        System.out.printf( "%-40s\n",  "\t\t\t\tSign Up");
        System.out.printf( "%-40s\n",  "===========================================");
        System.out.printf("%-40s\n",  "\t[1] Create an Account");
        System.out.printf("%-40s\n",  "\t[2] Back to Sign In Menu");
        System.out.printf("%-40s\n",  "\t[3] Exit");
        System.out.printf("%-40s\n",  "===========================================");
        performSignUpOption(readSignUpOption());
    }


    private static void performSignUpOption(enSignUpOptions signUpOption) {
        switch (signUpOption) {
            case eCreateAccount:
                clearScreen();
                performUsernameVerification(askUserName());
                break;
            case eBack:
                clearScreen();
                SignInScreen.backToSignIn();
                break;

            case eExit:
                clearScreen();
                logout();
                break;
        }
    }

    private static enSignUpOptions readSignUpOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%-40s\n", "Choose what you want to do? [1 to 3]? ");
        int choice = InputValidator.readIntNumberBetween(1, 3, "Invalid option. Enter a valid option");
        for (enSignUpOptions e : enSignUpOptions.values()) {
            if (choice == e.ordinal()) {
                return e;
            }
        }
        return enSignUpOptions.eSkip;
    }


    private static String askUserName()
    {
        System.out.println("Please create a username: ");
        return InputValidator.readString();
    }

    private static void performUsernameVerification(String userName)
    {
        if(manager.isUserExists(userName))
        {
            System.out.println("This username is already in use, please enter a new username.");
            performUsernameVerification(askUserName());
        }
        else
        {
            username = userName;
            askPassword();
        }
    }

    private static void askPassword()
    {
        String p1;
        String p2;

        System.out.println("Please create a password: (It must start with a uppercase letter, be at least 6 characters long and have at least 1 number)");
        p1 = passwordValidation();
        System.out.println("Please enter the password again: ");
        p2 = InputValidator.readString();
        performPasswordVerification(p1, p2);
    }

    private static void performPasswordVerification(String p1, String p2)
    {
        if(!p1.equals(p2))
        {
            System.out.println("The 2 passwords doesn't match please enter them again.");
            askPassword();

        }
        else
        {
            password = p1;
            manager.getInstance().addUser(username, password);

            if(manager.isUserExists(username))
            {
                manager.saveDataToFile();
                System.out.println("New account created successfully");
                System.out.println("Please Log In to use the app.");
                LogInScreen.showLogIn(manager);

            }
        }
    }

    private static String passwordValidation()
    {
        String password = InputValidator.readString();
        char[] passWordArr = password.toCharArray();
        if(passWordArr.length >= 6)
        {
            if (Character.isUpperCase(passWordArr[0]))
            {
                for (int i = 1; i < passWordArr.length; i++)
                {
                    if (Character.isDigit(passWordArr[i]))
                        return password;
                }
                System.out.println("The password must contain at least 1 number.");
                askPassword();
            }
            else
            {
                System.out.println("The password must start with a uppercase letter.");
                askPassword();
            }
        }
        else
        {
            System.out.println("The password must be at least 6 characters long.");
            askPassword();
        }

        return null;
    }

    private static User createAccount(String userName, String password)
    {
        return new User(userName, password);
    }


    private static void logout() { }

}
