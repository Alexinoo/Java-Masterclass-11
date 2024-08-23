package java_files_input_output._03_catching_and_throwing_exceptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Catching and Throwing Exceptions
 *
 * - Let's see if we can get the NoSuchElementException, to basically be invoked as an exception in our program
 *
 * - Currently our program is throwing InputMisMatchException but that's not the error we're looking for
 *      - Type Ctrl+D
 *      - Sends the ASCII EOT, End Of Transmission character to the program and that abruptly terminates any input
 * - So by subclassing the NoSuchElementException, the creators of the Scanner class allowed programs finer control over how they respond to the
 *   exception
 *
 *  - If we ctr+click on "NoSuchElementException" , notice how it extends RuntimeException and if we ctrl+click on "super()"
 *
 *
 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }

    private static int divide() {
        int x , y;
        try{
            x = getInt();
            y = getInt();
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("no suitable input");
        }
        System.out.println("x is "+ x + " y is "+y);
        try {
            return x/y;
        }catch (ArithmeticException e){
            throw new ArithmeticException("attempt to divide by 0");
        }
    }

    private static int getInt() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter an integer");
        while (true){
            try {
                return scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Please enter a number using only the digits 0 to 9 ");
            }
        }
    }
}
