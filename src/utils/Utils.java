package utils;

import java.util.Scanner;

public class Utils {
    final static Scanner scanner = new Scanner(System.in);

    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Print Wrap Line: Prints a line before and after the string.
     */
    public static void printwln(String str) {
        System.out.println("\n" + str);
    }

    /**
     * Asks for confirmation by making the user copy a character.
     * <br><br>
     * Example: <br>
     * Type 'q' to quit <br>
     * > Quit  // will return true <br>
     * > anything not starting with 'q'  // will return false <br>
     * 
     * @param copyChar the character the user has to type to return true
     * @return if the user had typed the character
     */
    public static boolean askConfirmByCopyChar(char copyChar) {
        return Character.toUpperCase(
            scanner.nextLine().charAt(0)
        ) == Character.toUpperCase(copyChar);
    }

}