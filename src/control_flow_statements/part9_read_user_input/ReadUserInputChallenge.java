package control_flow_statements.part9_read_user_input;

import java.util.Scanner;

/*
 * Reading User Input Challenge
 * ............................
 * - Read 10 numbers from the console entered by the user and print the sum of those numbers
 * - Create a Scanner class as we did in the previous video
 * - Use the hasNextInt method from the scanner to check if the user has entered an int value
 * - if hasNextInt() returns false, print the message "Invalid value" - Continue reading until you've read 10 numbers
 * - Use the nextInt() to get the number and add it to the sum
 * - Before the user enters each number, print the message "Enter number #x:" where x represents the count i.e. 1,2 3,4..
 * - For example:
 *      - the first message printed to the user would be"
 *          - "Enter number #1:"
 *      - the next
 *          - "Enter number #2:"
 *      - and so on
 * Hint:
 *  - Use a while loop
 *  - Use a counter variable for counting valid numbers
 *  - Close the scanner after you don't need it anymore
 */
public class ReadUserInputChallenge {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int sum = 0, counter = 0;

        while (true ){
            int order = counter + 1;
            System.out.println("Enter number #"+order+ ":");

            boolean isAnInt = scanner.hasNextInt();

            if (isAnInt){
                int number = scanner.nextInt();
                sum += number;
                counter++;
                if (counter == 5)
                    break;
            }else {
                System.out.println("Invalid value");
            }
            scanner.nextLine(); // handle end of line (enter key)
        }

        System.out.println("The sum = "+sum);

        scanner.close();

    }
}
