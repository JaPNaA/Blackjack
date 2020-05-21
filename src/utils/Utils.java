package utils;

public class Utils {

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

}