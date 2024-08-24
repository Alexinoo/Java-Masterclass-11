package java_files_input_output._04_multicatch_exceptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Multi Catch Exceptions
 *
 *  - The divide() code looks clumsy right now, because we've now got to try-catch blocks
 *      - One that catches NoSuchElementException
 *      - And the other one that catches ArithmeticException
 *  - There's no reason why we can't combine both into a single try catch block and in fact that's the recommended way
 *    of doing things
 *  - To change this is quite very simple and we only need to add another catch block right after the first one
 *
 *  - Next, copy the code that was in the 2nd try and include it in the first try-block
 *      - then delete the empty try-catch block
 *
 *  - This means, our code is much cleaner, as we've now included the code to be executed in the try-block and we're
 *     now catching multiple exceptions,
 *      - this makes it clear what we're trying to actually catch the potential errors that are likely to happen
 *  - When an exception happens, each of the catch blocks is checked in order to see if one of them handles the
 *     particular exception
 *  - So as soon as a catch that handles the exception is found, that code is actually executed and any remaining catch
 *    blocks are ignored
 *
 *  ///////
 *  - Starting with Java 7, it's actually possible to handle multiple exceptions with a single catch block and we'll
 *    look at that later in the main()
 *      - But here it wouldn't have been appropriate because we want to place additional info in the exceptions before
 *         throwing them
 *  - So the program should now behave as it should, with the final decision on what to do about the exceptions where
 *    it belongs which is in the main()
 *  - We can now decide how we're going to actually cope with a particular error, bearing in mind that we're now
 *     throwing the errors and not dealing with them as such in the divide()
 *
 * ///////
 *  - Now we can add a try in the main(), and print the result of the divide()
 *  - Then in the catch block, use bit-wise inclusive OR (|) to combine multiple exceptions
 *      - and use e.toString() - which sort of dumps off the error exception
 *      - and then print out some message to the console as well
 *
 *  - Note: We use the bit-wise inclusive OR and not the standard OR that we use with Conditional OR with 2 pipe
 *     characters, and we're just using 1 which is a bit-wise inclusive OR
 *      - And that's the way to actually check for multiple exceptions
 *
 * /////
 *  - Test the program with Ctrl+D and division by 0 to see whether it is doing what is required
 *
 * ///
 *  - Normally, in a real world program, it's easier to catch the exceptions from the main method as we have done
 *    in the main() rather than in the divide
 *      - So we can optionally, collapse the try and catch blocks in the divide and handle the exceptions in the main()
 *      - comment out on the try and catch blocks in the divide
 *
 * ////
 *  - Test with errors and verify the output
 *      - attempt to divide by zero
 *      - Ctrl+D on both, either getting input on x or y
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
       // try{
            x = getInt();
            y = getInt();

       //     System.out.println("x is "+ x + " y is "+y);

            return x/y;
       // }catch (NoSuchElementException e){
       //     throw new NoSuchElementException("no suitable input");
       // }catch (ArithmeticException e){
       //     throw new ArithmeticException("attempt to divide by 0");
       // }
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
