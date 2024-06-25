package control_flow_statements.part4_for_statement;
/*
 * The for statement
 * .................
 * - The for statement is used to loop some code
 *      - it allows us to execute some code, a certain no of times
 * - This is very common in programming & we'll look at an example of why we want to do that
 *
 * Example:
 *  - Add calculateInterest() that takes 2 parameters of type double and also returns a double
 *  - calculate interest based on the amount that is passed as a parameter
 *
 * - Alright,
 *  - This works, but suppose we wanted to calculate an interest for 3% , 4% and then 5%
 *  - We can do this in 1 way, and that's by copying and pasting below line several times and update the interest
 *
 *      System.out.println("10,000 at 2% interest = "+calculateInterest(10000.0,2.0));
 *      System.out.println("10,000 at 3% interest = "+calculateInterest(10000.0,3.0));
 *      System.out.println("10,000 at 4% interest = "+calculateInterest(10000.0,4.0));
 *      System.out.println("10,000 at 5% interest = "+calculateInterest(10000.0,5.0));
 *
 *  - But we can probably see right away, that this will really get tedious if we wanted to do that every time
 *    we wanted to calculate a different interest rate
 *  - Suppose we had 20 or even 100 interest rates calculations we wanted to do, there would be a lot of code &
 *    it's very inflexible because we have to continually have to go back and change things and can be a little bit
 *    messy
 *
 * - The good thing here is that Computers are made for automation
 * - There is a good solution to getting around this statement and that's by using a for-statement to automate this
 *   statement and to ultimately make it a lot easier
 *
 * for statement
 * .............
 *  - is commonly referred as the for loop
 *  - it repeatedly loop something until a condition is satisfied
 *  - loop here means, it's processing a given block of code, a given number of times or until a particular condition
 *    is satisfied
 *
 * Basic structure:
 *  for(init ; termination ; increment){
 *  }
 *  - where
 *      - init means
 *          - the code that is going to be initialized once at the start of the loop
 *          - a common thing to do here is to initialize a variable
 *              - normally, we would create a variable, i , j, or k
 *              - once the loop exits the variable won't exist anymore - it's really just for the for-loop
 *
 *      - termination
 *          - tell the loop how we want to exit
 *          - at what point do we exit from the loop
 *          - normally, it's a condition which has to evaluate to false at some point and if that happens, we exit
 *            from the loop
 *          - if true, we process whatever that is in the code block
 *          - add a condition for example : i < 5
 *              - will process until is greater than 5
 *
 *      - increment
 *          - is an expression that is invoked after each iteration
 *          - each time the loop goes around
 *          - a common thing is to put a number that is incremented each time
 *          - increment the value of the variable by say 1,2,3 etc
 *              - i++ - increments the value of i by 1 after each iteration
 *
 * Note:
 *  - We don't always have to start at 0, we can start at 1, 10, 20 , 100 and so on
 *      - But it's typically in these scenarios with for loops to start with 0, because a lot of Java, for example
 *        arrays, start at 0
 *      - it's a good practise to start with 0
 *  - Update to several values and test
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("10,000 at 2% interest = "+calculateInterest(10000.0,2.0));
        System.out.println("10,000 at 3% interest = "+calculateInterest(10000.0,3.0));
        System.out.println("10,000 at 4% interest = "+calculateInterest(10000.0,4.0));
        System.out.println("10,000 at 5% interest = "+calculateInterest(10000.0,5.0));

        for (int i = 1; i < 5; i++) {
            System.out.println("Loop "+i+ " hello!");
        }
    }
    public static double calculateInterest(double amount , double interestRate){
        return (amount * (interestRate/100));
    }
}
