package java_files_input_output._01_exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * Exceptions
 *
 * - We'll start this section by looking an at introduction to Exceptions because many of the methods that we'll be using to read and write files
 *   requires that exceptions are handled
 *
 * - There are 2 main approaches of handling errors in programming
 *      - LBYL (Look Before You Leap)
 *      - EAFP (Easy to Ask for Forgiveness than Permission)
 *
 * LBYL
 *  - In Java, it's more common to LBYL, and we've seen thia a lot when testing an obj is not null before attempting to use it
 *
 * EAFP
 *  - Perform the operation and respond to an exception if something goes wrong
 *
 * - Trapping and handling exceptions is simple and the code that may cause problem is wrapped in a try block and then catch is used to deal with
 *    the exception
 * - Though there is more to that, like deciding which exceptions to catch things of that nature
 *
 * - We'll take examples and go through both approaches to dealing with errors to understand how exception handling is used
 *
 * ///////////
 * Example 1 - Perform Integer division
 *
 *  - Add 2 methods
 *      - divideLBYL(x,y)
 *          - test the value we're dividing by isn't zero valid
 *          - check if y is not 0 before dividing , because we can't divide something by zero
 *
 *
 *      - divideEAFP(x,y)
 *          - use try catch
 *          - perform division in the try block
 *          - handle ArithmeticException in the catch block
 *
 * ///////
 *  - Call both in the main()
 *      - pass the values of x and y to both of them
 *          - Test with value of y being 0
 *      - print the result
 *
 *  - Test and verify that both functions can cope with an attempt to divide by zero
 *
 * //////
 *  - Add divide(x, y)
 *      - perform the same division without checking for anything, or adding any try-catch block
 *      - pass the same parameters and confirm that it actually crash
 *
 *
 * ///////////////
 *  - There's still a debate as to which method is better, but the simple answer is that they're both better
 *  - Each approach has got it's own advantages that are more suited to certain situations
 *
 *  - When using Java, it's more common to check first
 *
 * /////////////////////
 *  Example 2 - Use a Scanner instead to get the number from the keyboard
 *      - Ensure that the values passed are actually valid
 *
 *  - Our program should attempt to read a value from the keyboard and store it in an int variable
 *
 * Add the following methods
 *  - getInt() : int
 *      - use a scanner variable
 *      - read the value from the scanner and return the integer
 *      - no checks, throws an InputMismatchException Exception if non-numeric/non integer input is entered
 *      - attempt to use nextInt() on invalid input results
 *
 *  - getIntLYBL()
 *      - read input from the scanner into a String
 *      - check each character in the String to make sure they're all digits : Character.isDigit()
 *      - create a boolean value "isValid" and set it to true
 *      - if valid
 *          - parse the input to an int and return it
 *      - Otherwise
 *          - return -1
 *
 *  - getIntEAFP()
 *      - use a try-catch block
 *      - read the value from the try block
 *      - if valid
 *          - return value read from scanner.nextInt()
 *      - otherwise , catch InputMismatchException
 *          - return -1
 *
 * //////
 * try catch
 *
 *  - try block is executed if there isn't an error, will continue executing if there's any many lines of code if any
 *      - will bypass the catch and fall through to the next line
 *
 *  - If there's any error, the Exception is raised and processing stops immediately and executes whichever the code is in the catch block
 *      - in this case, it'll just return -1
 *
 *
 *
 *
 * ////////
 *  - getIntEAFP() has less code compared to getIntLYBL() and a little bit more elegant as well because you can look at that more quickly and once you
 *    understand what exceptions are, it's easier to know what you want to return in case one is thrown
 *
 *  - It really depends with what you're trying to do, because it's not always black and white to always use EAFP or LYBL approach
 *  - Reverse can also be true as well
 *      - Like for example, it probably wouldn't make sense to use a try-catch block when checking that a key exists in a map , in that scenario, it
 *        would probably going to be a lot quicker to use the LBYL approach
 *      - You could obviously use it with exceptions , but checking for the existence of a key first or using map.get() and checking the value being
 *        returned is a null before attempting to use it, will result in more readable and less code
 *
 *
 *
 *
 *
 *
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int x = 98;
        int y = 0;
        System.out.println( divideLBYL(x,y));
        System.out.println( divideEAFP(x,y));
        //System.out.println( divide(x,y)); // throws an Arithmetic exception

        int number = getInt();
        System.out.println("number is "+number); // throws an InputMismatchException if non-integer is entered

       // int number = getIntLBYL();
       // System.out.println("number is "+number); //

        //int number = getIntEAFP();
        //System.out.println("number is "+number);
    }

    private static int getInt() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter your integer : ");
        return scanner.nextInt();
    }

    private static int getIntLBYL() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter an integer : ");
        boolean isValid = true;
        String input = scanner.next();
        for (int i = 0; i < input.length() ; i++) {
            if (!Character.isDigit(input.charAt(i))){
                isValid = false;
                break;
            }
        }
        if (isValid)
            return Integer.parseInt(input);
        return -1;
    }

    private static int getIntEAFP(){
        scanner = new Scanner(System.in);
        System.out.println("Please enter your integer : ");
        try{
            return scanner.nextInt();
        }catch (InputMismatchException e){
            return -1;
        }
    }

    private static int divideLBYL(int x , int y){
        if ( y != 0)
            return x/y;
        return 0;
    }

    private static int divideEAFP(int x , int y){
       try{
           return x/y;
       }catch (ArithmeticException e){
           return 0;
       }
    }

    private static int divide(int x , int y){
            return x/y;
    }
}
