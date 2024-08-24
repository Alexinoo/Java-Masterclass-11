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
 *  - If we ctrl+click on "NoSuchElementException" , notice how it extends RuntimeException and if we ctrl+click on
 *      super()
 *
 *      - we're now in the RuntimeException class
 *      - And notice how super() is being called again and if we click on it again
 *
 *          super()
 *      - we get to the Exception class which is the Base class and extends Throwable class
 *      - if we click again on
 *          super()
 *
 *      - we get to Throwable constructor which calls fillInStackTrace()
 *
 *  - As a result as long as a subclass exceptions constructor the stack trace is automatically going to be available
 *    on an output
 *  - There are other constructors for most of these exceptions which allow methods to add more info to the exception
 *    before throwing it to it's calling method
 *
 * //////////
 *  - Let's modify our program to start catching the exceptions and look at the options we have for dealing with them
 *
 *
 * /////////
 *  - Looking at our getInt(), we can get an exception when there's no input available or when the input doesn't
 *    represent a valid integer
 *      - We could catch the exception, and if we did, what would we do with it ?
 *      - Generally speaking, there's no point of catching an exception unless your code does something sensible with it
 *
 *  - And here there's really no action that can be taken if there's more input available
 *      - So catching the NoSuchElementException is just a waste of time in this method
 *
 *  - The only sensible thing to do with it is to allow it to propagate to the calling and that happens automatically
 *    if we don't catch it
 *
 *  - Our exceptions here are unchecked and we can just allow them to propagate automatically if we can't handle them
 *    sensibly in a particular method
 *
 * ////////
 *  - There is a possible action we could take if we get an InputMisMatchException, we could request another integer
 *    from the user
 *  - Add a way to continually ask for an input until we get a valid integer
 *      - Use a while(true) loop
 *          - add a try-catch block
 *              - if input is valid, return from the scanner.nextInt() from the try block
 *
 *              - Otherwise, throw an InputMisMatchException , and read past the eol so that we
 *                  can start afresh again
 *                      scanner.nextLine();
 *
 *                  - Add a message the following msg to the user
 *                      "print enter a number using the only digits 0-9"
 *
 *  - Test with both valid/invalid values
 *
 *  - It's very difficult to be able to guarantee an exception, dividing by 0 and the scanner.nextInt() are 2
 *    ways we can be sure of generating an error
 *  - But getInt() is very wrong for several reasons, and don't use it as an example of how to get input from a user
 *    in a real program
 *  - We've seen what happened with valid and invalid input, but if we do Ctrl+D , we get a NoSuchElementException and
 *    notice how that wasn't trapped here we're only catching InputMisMatchException
 *      - Hence NoSuchElementException wasn't caught and was propagated back up the chain
 *      - And the reason for that is we decided to let the calling code to handle it, rather than handling it in getInt()
 *
 * ////////
 * - Now the calling method is divide() and as well as receiving an exception from getting it, it can also throw one
 *   by attempting division by zero
 * - Again, there's no sensible thing that this method could do with either exception
 * - Because if a method can't perform the function that it's supposed to perform, then the only thing it can realistically
 *   do is inform the caller about the error
 * - So the first earlier attempt was return 0 or -1 ,but let's really annoy the user here by catching the
 *      NoSuchElementException , and call getInt() in the catch block
 *
 *      - Notice that it still crashed, and that's because although we caught the first exception, our exception
 *        handler then attempts to retry the operation that caused the problem by calling getInt()
 *      - This results in a second exception which isn't actually caught, so that's why it crashed for the 2nd time
 *
 *  - So it's valid for an exception handler to try and recover from an error condition, but it's good to note that
 *    any code in the exception handler, the catch block doesn't actually cause exception itself
 *  - So Ctrl+D is closed the program's input stream
 *      - So even though getInt() creates a new Scanner, any attempt to read from it will continue to raise NoSuchElementException
 *      - that's just a feature of scanner essentially
 *  - So the upshot is that we shouldn't have tried to do that in the catch block, and of course we had decided it was a
 *    bad idea for other reasons
 *      - but we did it anyway just to demo the importance of ensuring that exceptions are not raised in your
 *        exception handler code itself
 *      - a catch block is there to catch 1 or more exceptions, and should guard against them in case they do.
 *
 *      - keep them simple to try and make sure that they don't go on to cause more exception
 *
 *  - It is possible to nest a try catch block inside a catch block and sometimes it can be useful to do so, but the
 *    instructor the best advice to us is keep our catch blocks as simple as possible
 *  - The less code there's in the catch block, the less chance there is of another exception being thrown
 *
 * /////
 *  - It's now obvious that we can't do anything sensible inside the divide() with NoSuchElementException from getInt()
 *  - And we've also decided there's nothing we can do with an attempt to divide by zero either
 *  - So we could let both the NoSuchElementException and ArithmeticException exceptions automatically propagate up
 *    the call stack and let the main() handle them both
 *      - But what we can do is make life easier for main by throwing a single exception in both cases
 *      - And this is a good opportunity to see how to throw exceptions anyway
 *
 * /////
 *  - To throw an exception, you create a new exception object and use the throw statement
 *  - You should try to be specific as possible and use an appropriate subclass of an exception rather than throwing
 *    Exception itself
 *  - The divide() performs integer division, throwing ArithmeticException, and it seems like a good choice
 *  - We can also use the overloaded constructor that takes a String for the exception and provide a bit more info
 *    on what actually happened
 *      - This is really not intended for the calling method but it's useful in log files when a human has to work
 *         out what happened
 *
 * ///
 *  - Add both
 *      x = getInt()'
 *      y = getInt();
 *  - Because both can raise NoSuchElementException as well
 *  - Next, we can remove/comment out from the catch block
 *      x = getInt();
 *  - And
 *      throw new NoSuchElementException("no suitable input");
 *
 *  - instead, and that's going to be printed on the screen
 *
 *
 * /////
 *  - Next, add another try block for returning division of x and y
 *      - Then catch ArithmeticException in case of a division by zero and
 *
 *      - throw it as follows
 *          throw new ArithmeticException("attempt to divide by 0");
 *
 * ////
 *  - Test with both "Ctrl+D" and division by zero verify both exceptions are thrown
 *
 * /////
 *  - So, when the program's is run and either the 2 exceptions are triggered as we saw, the stack trace is then much
 *    simpler, not sure, we noticed it, try again
 *      - the stack trace is a lot simpler now it's really only referring to our code and that's because the exception
 *         originated in the divide()
 *      - so Java doesn't need to show any other methods that were being invoked
 *
 * //////
 *  - However, the code we've written look a bit clumpsy & we'll look at fixing that in the next video
 *
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
            //x = getInt();
            // y = getInt();
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
