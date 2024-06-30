package control_flow_statements.part9_read_user_input;

import java.util.Scanner;

/*
 * Min And Max Input Challenge
 * .....................
 *
 * - Read the numbers from the console entered by the user and print the minimum and maximum number the user has entered
 * - Before the user enters the number, print the message "Enter number:"
 * - If the user enters an invalid number, break out of the loop and print the minimum and maximum number
 *
 * Hint:
 *  - Use an endless while loop
 *
 *
 * Solutions - Tricky part
 *  1. Use a boolean flag that checks if the user is entering the first number and set min and max to that value
 *      - create "isInitialNumber" boolean flag and set it to true
 *      - after initial number is entered, set min and max to that number and update "isInitialNumber" flag to false
 *      - will only execute once when the user enters the first number and then never again
 *
 *  2. set min and max to extremely high and low int values
 *      - set max to the minimum value that an int can hold - user can only enter a number >= min value ;
 *          max = -2146473648
 *          - any number the user enters will always be greater than -2146473648
 *      - set min to the maximum value that an int can hold
 *          min = 2146473647
 */
public class MinAndMaxInputChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int max = 0 , min = 0; // track min and max numbers
        boolean isInitialNumber = true;

        while (true) {
            System.out.println("Enter number: ");

            boolean isAnInt = scanner.hasNextInt(); // checks if an in is entered first

            if (isAnInt) {
                int number = scanner.nextInt(); // 10 ; 11 ; 9 ; 7 ; 8

                if (isInitialNumber) {            // set min and max to the first number entered
                    max = number;                 // max=10;
                    min = number;                 // min=10;
                    isInitialNumber = false;      // set the flag to false
                }

                if (number > max) {             // 10>10; 11 > 10; 9 > 11; 7 > 11; 8 > 11;
                    max = number;              // false ; max=11; false; false; false;
                }
                if (number < min) {             // 10>10; 11 < 10; 9 < 10 ; 7 < 9; 8 < 7
                    min = number;              // false ; false; min=9; min=7; false;
                }
            } else {
                break;
            }
            scanner.nextLine(); // handle enter key
        }

        System.out.println("max = " + max);
        System.out.println("min = " + min);
        scanner.close();
    }
}
