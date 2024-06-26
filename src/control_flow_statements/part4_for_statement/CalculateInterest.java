package control_flow_statements.part4_for_statement;
/*
 * Calculate Interest Challenge
 * ............................
 * - Using the for statement, call calculateInterest() with the amount of 10000, with an interest of 2,3,4,5,6,7 and 8
 * - Print the result to the console window
 *
 * Bonus
 * - If we wanted to calculate interest rates with percentages, then probably we won't be using an integer there but a
 *   double, and we can have a different way of incrementing
 *      - Like for example, we can increment with 0.5% or 1.5%,... along those lines
 *
 * - Note that sometimes, the precision of the double, will calculate like 700.000000001
 *      10,000 at 3.5% interest = 350.00000000000006
 *      10,000 at 7% interest = 700.0000000000001
 *
 * - it's something that we don't need to worry about, but it can be nice to actually get rid of that
 * - one thing we can do to fix that is to call format() on String that takes 2 parameters
 *      - format
 *      - value
 *      e.g.
 *          String.format("%.2f",calculateInterest(10_000d,i))
 * - we're passing the value we got ultimately from our calculateInterest() to the String.format()
 * - the "%.2f" means convert the number and output it with 2 decimal places
 *
 *
 * Mini-challenge
 * ..............
 * - modify the loop to do the same thing but start from 8% and work backwards to 2%
 *
 *
 */
public class CalculateInterest {

    public static void main(String[] args) {

        for (int i = 2; i < 9; i++) {
            System.out.println("10,000 at "+ i +"% interest = "+String.format("%.2f",calculateInterest(10_000d,i)));
        }

        System.out.println(".".repeat(50));

        //Variations
        for (double i = 2.0; i <= 8.0; i+=1.5) {
            System.out.println("10,000 at "+ i +"% interest = "+String.format("%.2f",calculateInterest(10_000d,i)));
        }

        System.out.println(".".repeat(50));

        // Mini-challenge solution
        for (double i = 8; i >= 2; i--) {
            System.out.println("10,000 at "+ i +"% interest = "+String.format("%.2f",calculateInterest(10_000d,i)));
        }
    }

    public static double calculateInterest(double amount , double interestRate){
        return (amount * (interestRate / 100));
    }
}
