package control_flow_statements.part4_for_statement;

/*
 * Sum 3 and 5 Challenge - Sums up numbers divisible by both 3 and 5 between 1 -1000
 * ..................................................................................
 *
 * - Create a for statement using a range of numbers from 1 to 1000 inclusive
 * - Sum all the numbers that can be divided with both 3 and also with 5
 * - For those numbers that meet the above condition, print out the number
 * - break out of the loop once you find 5 numbers that met the conditions above
 * - After breaking out of the loop, print the sum of the numbers that met the above conditions
 *
 * Solution
 * - Define a count variable that represents the counter found numbers
 *      - we need this because we need to break out of the loop, once we find 5 numbers that meet the conditions
 *      - initialize it to 0
 *
 * - Define a variable for sum, to record the sum of those numbers
 *      - initialize to 0
 *
 * - use a for loop that starts from 1 and ends when number <= 1000
 *      - check if number is divisible by 3 and 5 at the same time
 *          - use modulus operator, needs to be equal to 0 in both cases
 *      - if so,
 *          - print the number
 *          - increment the count
 *          - add the number to the sum
 *      - check the count variable if it's value is equal to 5 and if so,
 *          - use break keyword to exit the for loop
 *
 * - print the value of the sum variable after for loop
 *
 */
public class SumThreeAndFiveChallenge {

    public static void main(String[] args) {
        int sum = 0, count = 0;

        for (int i = 1; i <= 1000; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.println("Found number = " + i);
                sum += i;
                count++;
            }
            if (count == 5) {
                break;
            }
        }
        System.out.println("The sum of the numbers = " + sum);


    }
}
