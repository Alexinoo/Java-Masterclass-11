package java_files_input_output._04_multicatch_exceptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Multi Catch Exceptions
 *
 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            int result = divide();
            System.out.println(result);
        }catch (ArithmeticException  | NoSuchElementException e){
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    private static int divide() {
        int x , y;
        try{
            x = getInt();
            y = getInt();

            System.out.println("x is "+ x + " y is "+y);

            return x/y;
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("no suitable input");
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
