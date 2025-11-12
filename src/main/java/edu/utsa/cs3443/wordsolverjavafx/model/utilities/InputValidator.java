package edu.utsa.cs3443.wordsolverjavafx.model.utilities;

import java.util.Scanner;

/**
 * This class will validate that any given input will be of the proper type and print an error message if not.
 */
public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean isNumberBetween(short number, short from, short to) {
        return (number >= from && number <= to);
    }

    public static boolean isNumberBetween(int number , int from, int to) {
        return (number >= from && number <= to);
    }

    public static boolean isNumberBetween(float number, float from, float to) {
        return (number >= from && number <= to);
    }

    public static boolean isNumberBetween(double number, double from, double to) {
        return (number >= from && number <= to);
    }


    public static char readChar(String errorMessage) {
        return scanner.nextLine().charAt(0);
    }

    public static int readIntNumber(String errorMessage) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ignore) {}
            System.out.println(errorMessage); // why not in catch?
        }
    }

    public static int readIntNumberBetween(int from, int to, String errorMessage) {
        int number = readIntNumber(errorMessage);

        while (!isNumberBetween(number, from, to))
        {
            System.out.println(errorMessage);
            number = readIntNumber(errorMessage);
        }
        return number;
    }

    public static double readDoubleNumber(String errorMessage) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException ignore) {}
            System.out.println(errorMessage); // why not in catch?
        }
    }

    public static double readDoubleNumberBetween(double from, double to, String errorMessage) {
        double number = readDoubleNumber(errorMessage);

        while (!isNumberBetween(number, from, to)) {
            System.out.println(errorMessage);
            number = readDoubleNumber(errorMessage);
        }
        return number;
    }

    public static short readShortNumber(String errorMessage) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Short.parseShort(line);
            } catch (NumberFormatException ignore) {}
            System.out.println(errorMessage); // why not in catch?
        }
    }

    public static float readFloatNumber(String errorMessage) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Float.parseFloat(line);
            } catch (NumberFormatException ignore) {}
            System.out.println(errorMessage); // why not in catch?
        }
    }

    public static short readShortNumberBetween(short from, short to, String errorMessage) {
        short number = readShortNumber(errorMessage);

        while (!isNumberBetween(number, from, to))
        {
            System.out.println(errorMessage);
            number = readShortNumber(errorMessage);
        }
        return number;
    }

    public static float readFloatNumberBetween(float from, float to, String errorMessage) {
        float number = readFloatNumber(errorMessage);

        while (!isNumberBetween(number, from, to))
        {
            System.out.println(errorMessage);
            number = readShortNumber(errorMessage);
        }
        return number;
    }

    public static boolean readBooleanValue()
    {

        String line = scanner.nextLine().trim();
        if(line.equalsIgnoreCase("False"))
        {
            return false;
        }
        else if(line.equalsIgnoreCase("True"))
        {
            return true;
        }
        else
        {
            System.out.println("Invalid input. Enter True or False: ");
            return readBooleanValue();
        }
    }

    public static String readString() {
        return scanner.nextLine().trim();
    }


}
