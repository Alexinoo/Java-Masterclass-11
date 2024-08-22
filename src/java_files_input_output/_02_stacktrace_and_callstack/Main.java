package java_files_input_output._02_stacktrace_and_callstack;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Stack Trace and Call Stack
 *
 * - A try block can contain as many lines of code
 * - And similarly, a catch block can contain as much code as you need to handle the exception, including attempts to recover from it , if appropriate
 *
 * What is an Exception ?
 *  - The Java docs, defines an exception as an event which occurs during the execution of a program that disrupts the normal flow of the program's
 *     instructions
 *      - means something went wrong somewhere and it leads naturally to 2 very important questions
 *          (a) - what went wrong ?
 *          (b) - what do we do about it ?
 *
 *  - Exception and RuntimeException are classes defined in java.lang, and the different type of exceptions that can be called are subclasses of one
 *    of these 2 exceptions
 *  - Normally when catching an exception, you'd specify which subclass of exception to catch
 *      - Refer to "_01_exceptions.Main.java" where we caught the InputMisMatchException with the valid integer
 *  - We could just catch Exception, but it's really a good idea to be more specific and catch a specific Exception rather than just generalizing and sort
 *    of doing a catch-all so to speak exception
 *      - In other words ,w e could have just catch Exception e and that would work fine, though it's very generic and it can be useful to be specific
 *       but we'll look at that more later on
 *
 *
 * /////
 *  - The Type of Exception thrown answers the question , "what went wrong" , and we're able to know at a glance that there's an InputMisMatchException
 *     because the exception has been named to make us understand the type of error encountered
 *      - we can then decide what to do about it
 *
 *  - We didn't do very much, as any invalid input just resulted in zero being returned
 *  - There could be some reasonable response in some situations but in others , the calling method, might need to know that there was a problem
 *    rather than effectively being told that the user entered the number 0 which is what we did in our previous examples
 *
 * //////
 *  - To let the calling code know that there was a problem, we could have thrown the exception , and that means passing the exception back to the
 *     calling method
 *
 *
 * Examples
 *  - We've looked at an example to perform a division and another one to get a number from the user
 *  - By putting them in the same program we can look at how exceptions propagate up the call stack
 *
 *  - We don't need the LYBL , and we can start by not handling any exceptions and see how Java handles things
 *  - The main() say is going to call divide(), which will then call getInt() twice to get the 2 numbers: x and y
 *
 * //////
 *  Define the following methods
 *      main()
 *          calls divide()
 *
 *      divide()
 *          calls getInt() and store to variable x
 *          calls getInt() and store to variable y
 *
 *      getInt()
 *          - reads int from keyboard using scanner
 *
 *      - Test the program,
 *          - Enter 2 values and confirm th the division is working correctly
 *
 * /////
 *  - There are 2 exceptions that could happen in this program
 *      - Divide by 0 - ArithmeticException
 *      - Invalid input - InputMismatchException
 *  - Java automatically prints a stack trace, which is showing the call stack
 *
 *
 * /////////////////////
 * Call Stack
 * /////////////////////
 *
 *  - Is a list of all the method calls at any particular point in a program's execution
 *      - In this case, it's all the method calls at the point where the program crashed
 *  - Each thread of execution has it's own call stack and the thread is shown as the first line of the stack trace and here it's our main thread
 *      Exception in thread "main" java.util.InputMismatchException
 *
 *  - Every time a new method is called, it's actually placed onto the stack and when a method returns, it's then removed from the stack
 *
 * ///
 *
 * Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:939)
	at java.base/java.util.Scanner.next(Scanner.java:1594)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
	at java_files_input_output._02_stacktrace_and_callstack.Main.getInt(Main.java:106)
	at java_files_input_output._02_stacktrace_and_callstack.Main.divide(Main.java:97)
	at java_files_input_output._02_stacktrace_and_callstack.Main.main(Main.java:92)
 *
 *
 * - In our example, we can see that the exception happened in line 939 in the java.util.Scanner.throwFor() in the Scanner class
 *      - But our code didn't call it and therefore it relly doesn't make much sense to us yet
 *
 * - The next item on the stack is java.util.Scanner.next(Scanner.java:1594)
 *      - We didn't also call this either directly
 * - The best approach to make sense of these traces is to start at the bottom and then work back up
 *
 * - Our first item that relates to our code is "java_files_input_output._02_stacktrace_and_callstack.Main.main(Main.java:92)"
 *       int result = divide();
 *
 *      - and is where the divide() was called and the trace shows that it happened in Main.java
 *
 *  - The next entry is line 97, in Main.divide and that's where getInt() was called
 *
 *      int x = getInt();
 *
 *  - And the next one after that is on line 106 on getInt() method :
 *      return scanner.nextInt();
 *
 *  - Normally, we'd stop there because all the entries above that are also out of our code in java.util.Scanner class
 *  - We know our attempt to get an integer from our keyboard failed and we should do something to prevent that error from crashing the program
 *
 *
 * ////
 *  - Carry on through the stack though and it's just useful to see how to trace the errors through different classes and packages
 *  - IntelliJ has created links to the line numbers where they appear in the code in the editor
 *      - And once you click them, they'll take you to that line of code
 *  - Once the trail, leaves our code, there may still be links but they're in gray
 *
 *      at java.base/java.util.Scanner.throwFor(Scanner.java:939)
        at java.base/java.util.Scanner.next(Scanner.java:1594)
        at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
        at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
 *
 *  - click on
        at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
 *
 *      - and have a look at that by ctrl+click
 *
 *  - So, the scanner's nextInt() has been overloaded
 *         public int nextInt() {
               return nextInt(defaultRadix);
            }
 *      - which is why there seems to be 2 calls in the Stack trace and our call is ultimately passed on to nextInt() with default radix
 *
 *      - if we ctrl+click on  nextInt(defaultRadix), itn then takes us to the overloaded nextInt, which then takes us to
 *
 *          String s = next(integerPattern());
 *
 *      - ctrl + click on
 *          java.base/java.util.Scanner.next(Scanner.java:1594)
 *
 *      - that directs us to the line below
 *           throwFor();
 *
 *      - ctrl + click on
 *          java.base/java.util.Scanner.next(Scanner.java:939)
 *
 *      - directs to the line below
 *         throw new InputMismatchException();
 *
 *      - where the actual error is thrown
 *
 *  - Quite often, the exception would be thrown from the problem that had the problem, but as we can see the Scanner class uses "throw for()" to
 *     throw either InputMismatchException or a NoSuchElementException
 *
 *  - exceptions are sub-classes of the Exception class and Exception itself extends Throwable
 *
 *  - At this point, we need to know that the Throwable constructor fills in the stack trace for the exception
 *
 *      throw new InputMismatchException();
 *      - the above creates a new InputMismatchException obj that contains the current stack trace
 *
 *  - So when the exception itself is thrown, the java runtime then looks to see what can handle it
 *      - It does this by working out back up the call stack
 *      - Each method on the stack gets the exception, if it doesn't handle it,
 *      - Then the exception is passed on to the previous method on the stack and so on.
 *
 *  - So the exception is said to propagate up the call stack and it keeps getting passed to the calling methods until there's one that actually
 *     can handle that exception
 *  - However looking at the way the stack trace is printed, it might actually make more sense to talk about propagating down the call stack, but stack
 *    tends to be implemented growing downward, and so we talk about going back up the stack
 *  - Eventually, if nothing handles, the exception then the main.java entry point just prints the stack trace and the java runtime terminates which
 *    is what happened here
 *
 * /////////////
 *  - We'll modify the program to catch the exception , but let's continue to see the Exception class hierarchy
 *
 *  - ctrl + click on "InputMismatchException" below
 *          throw new InputMismatchException()
 *
 *  - which takes us to InputMismatchException constructor which is a pretty basic class with just 2 constructors and each one just delegates
 *      immediately to the super
 *
 *      public InputMismatchException() {
            super();
        }
 *
 *  - If we ctrl+click on super()
 *      - we get to the base class NoSuchElementException which is just a parse
 *
 *         public NoSuchElementException() {
                super();
            }
 *  - In fact, exception objects are generally like this, the subclasses exist to provide more granularity so that code can check for different types
 *     by catching the subclasses
 *
 *  - We saw NoSuchElementException before, it's the other exception that the "throw for()" could throw
 *
 *  - So InputMismatchException extends NoSuchElementException and exists purely to allow the exception handler to differentiate between the Scanner
 *     running out of input and the input being invalid
 *  - So we can cause a NoSuchElementException in our program , but let's do that in our next video

 *
 *
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
        int x = getInt();
        int y = getInt();
        System.out.println("x is "+ x + " y is "+y);
        return x/y;
    }

    private static int getInt() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter an integer");
        return scanner.nextInt();
    }
}
