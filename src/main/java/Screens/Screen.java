package Screens;

public class Screen {

    protected static void displayHeader(String header, String subHeader){
        System.out.println("-------------------------------------------");
        System.out.printf("|\t\t\t\t%s\t\t\t\t|\n", header);

        if (!subHeader.equals(""))
            System.out.printf("\n%32s\n", subHeader);

        System.out.println("-------------------------------------------");
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println(String.format("%n").repeat(50));
        }
    }
}

