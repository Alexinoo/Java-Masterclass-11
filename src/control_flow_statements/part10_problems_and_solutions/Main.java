package control_flow_statements.part10_problems_and_solutions;

import java.time.LocalDate;
import java.util.Scanner;

/*
 * Reading User input - Problems and Solutions
 * ...........................................
 *
 * - There are potential problems that we're likely to face and how to solve them
 *      - what if the user enters a negative number for the year of birth
 *          - we need to add some condition that would check if the birth year is positive or if better check a valid range
 *              - test a range of 0 - 100
 *              - if the age is not in that range, print an error message to the user
 *      - what if the user enters letters instead of numbers
 *          - the program throws an InputMismatchException and exits which is similar to NumberFormatException
 *          - the program crashed because nextInt cannot parse a text to a number
 *
 * - At this point, we've got 2 problems.
 *      - 1. negative numbers as years
 *      - 2. Entering non-numeric letters where it's asking for the age
 *
 * Solution to problem 1 : negative yob
 *  - add an if statement that tests if age > 0 or less than a 100 and if so, we know that's a valid age
 *  - otherwise, prints to the user that the uer entered invalid year of birth
 *
 * Solution to problem 2 - enter non-numeric characters e.g. letters
 *  - we need to call a method hasNextInt() which returns a boolean
 *
 * hasNextInt()
 *  - checks if the input entered is a number or an integer, and if it qualifies to be an int, the method returns true
 *    otherwise, it returns false
 *  - allows us to avoid generating typo errors when using nextInt
 *  - validate
 *      - if "isAnInt is true and if so proceed with calculations
 *      - otherwise, inform the user that the year of birth is invalid
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your year of birth:");

        boolean isAnInt = scanner.hasNextInt();

        if (isAnInt) {

            int yearOfBirth = scanner.nextInt();
            scanner.nextLine(); // Handle next line character - Enter key

            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            int age = LocalDate.now().getYear() - yearOfBirth;

            if (age >= 0 && age <= 100) {
                System.out.println("Your name is " + name + " and you are " + age + " years old.");
            } else {
                System.out.println("Invalid year of birth");
            }
        }else{
            System.out.println("Unable to parse year of birth");
        }


        scanner.close();
    }
}
