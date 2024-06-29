package control_flow_statements.part9_read_user_input;

import java.time.LocalDate;
import java.util.Scanner;

/*
 * Reading User Input
 * ..................
 * - make an interactive application where a user will enter his/her name and a year of birth and the application
 *   will calculate the current age of the user
 *
 * - we learnt how to parse, convert a string type into a number by using the methods such as Integer.parseInt() and
 *   Double.parseDouble()
 *
 * - we'll cover another useful method nextLine() as well as a few others
 *
 * - We'll use a class called a Scanner which is a simple text scanner that can parse both Primitive types and Strings
 * - In short, scanner use methods such as parseInt internally
 *
 * - the application, will request the user to enter their name and store it as a variable
 *
 * Scanner class
 * .............
 * - Scanner is one of the built-in Java classes which allows us to read user input
 * - We can parse primitive types and strings using methods from the scanner class
 *  - we create a new scanner obj that takes System.in as a parameter
 *      - System.out writes or outputs information to the console
 *      - System.in allows you to type input into the console which is then read back to the program
 *  - so that's what we're doing here when we're defining variable of type scanner
 *
 * - after using the scanner, we need to close it manually
 *      - after closing the scanner, calling ()s such as nextLine() or nextInt() will cause errors and therefore,
 *        we need to make sure we're done with the scanner before closing it
 * - this make sure the scanner releases the underlying memory that scanner was using internally
 *
 * Summary
 * - we have created a variable scanner, and then we're calling a method nextLine() to read a line of input from the
 *   console
 * - then we added an output to print out what was typed
 *
 * - By using the nextLine() in conjunction with scanner, we were able to retrieve some input from the console, or in
 *   other words, from the keyboard and stored it in name variable
 *
 * Next,
 * - Ask the user he's year of birth and use it calculate the current user's age based on what the current year is
 * - retrieve an int from the scanner to calculate his age from the birth year
 *      - use scanner.nextInt()
 * - we can as well use nextLine() as we've done with user's name and then convert it into an integer
 *      - scanner has a method that will parse the string to an integer for us which is scanner.nextInt()
 *      - what the user type is automatically converted into an integer and saved into a variable called yearOfBirth
 *         which is of type int
 * - display the message to the user so that they know what data to type before they enter their name
 *
 * Calculate age
 *  - Subtract the current year with the year of birth for the user
 *
 * Running this:
 *  - we didn't get a chance to enter the name, even though the message, "enter your name" was printed
 *
 * Solution
 *  - this is one of the mistakes that beginners usually make, which is why we've ordered the birth year prompt before
 *    the name
 *  - whenever we read a number from a scanner, be it an int or float or double or some other type, we press Enter key
 *    on the keyboard as we just did with the age
 *      - doing that ends the line
 *  - it's recommended after nextInt or call to nextInt(), to call nextLine() again without assigning it to a variable
 *  - In other words, to handle, the Enter key issue, we have to call nextLine, so that the scanner works as expected
 *  - Internally, the scanner is checking for a line separator, and when we hit Enter, we're typing a line separator so
 *    that it's interpreted as an input
 *  - when we reach the nextLine(), that retrieves the name, the input for a name becomes Enter, so is essentially
 *    skipped
 *
 * NOTICE
 *  - Keep this in mind for the future, after you read in a number with a scanner, there must be a next line method
 *    call to handle the Enter Key
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter your year of birth:");
        int yearOfBirth = scanner.nextInt();

        scanner.nextLine(); // Handle next line character - Enter key

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        int age = LocalDate.now().getYear() - yearOfBirth;

        System.out.println("Your name is : "+ name+ " and you are "+ age+ " years old.");
        scanner.close();
    }
}
