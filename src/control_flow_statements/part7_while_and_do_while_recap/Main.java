package control_flow_statements.part7_while_and_do_while_recap;
/*
 * while and do while recap
 * ........................
 *
 * - Let's recap on what we have learned about the while, do while loops , continue and break keywords
 *
 * while loop syntax:
 *   init;
 *   while(condition) {
 *      // statements
 *      // increment
 *  }
 *
 * while loop
 *  - initialization is OPTIONAL , since we can use while (true)
 *  - we have the while keyword and then a condition in the parenthesis
 *  - then we have a code block , which executes if the condition is true,
 *      - NOTICE, there is no semicolon
 *  - If we have to increment a variable, then we'll have to do manually inside the loop
 *  - If we want to use a variable in the condition, we'll have to declare that variable outside the while loop
 *
 * for-loop
 *  - we have the initialization, condition and iteration in the same line
 *
 *
 * do while loop
 * - syntax
 *      initialize;
 *      do {
 *          // statements
 *          // increment
 *      }while(condition)
 *
 * - we have the do keyword at the top and right after the keyword, we have a code block in curly braces
 *      - NOTICE, there's no semicolon after the do keyword
 * - after the code block, i.e. after the right curly brace , there's a while keyword then a condition in paretheses
 *   and a semicolon at the end
 *      - NOTICE, the semicolon is required in a do while
 * - keep in mind that the do while will EXECUTE AT LEAST ONCE and the condition is at the bottom
 * - the code in the code block will execute once, and then the condition is checked
 * - UNLIKE the while loop where the condition was checked at the top
 *
 * Example-1:
 *  - initialization
 *      int count = 1;
 *
 *  - condition
 *      while(count <= 5) {
 *              count++; // increment
 *      }
 *
 *
 *
 * - we have initialization of our variable outside the while loop, and iteration/increment in the while loop
 *      - initialization, condition and iteration are on different lines which is in contrast to the for loop
 *
 * Example-2:
 *  - initialization, condition and iteration are all on the same line separated by a semicolon
 *  - however, the iteration part is not terminated by a semicolon in the for loop
 *
 *
 * Example-3 : while loop with continue and break
 *  - prints numbers from 5 to 10
 *  - though this can be simplified with different conditions without a break and continue, but it's a good example
 *    to see how the continue and break keywords work
 *  - refer to the slides
 *
 * - This is how we can interrupt the loop and depending on the condition, we can skip part of the code or break out
 *   of the loop
 *
 * Summary
 * .......
 * - The while loop checks the condition at the start before executing the code block
 * - With the do-while loop, the code block is executed at least once and then the condition is checked
 *      - It's always guaranteed that the code in the code block for a do-while loop is executed at least once
 * - Be careful with conditions , it is easy to end up with endless loop
 *      - a loop that never ends
 *      - or to end up with a loop that never executes
 *
 * Tip:
 *  - always check you conditions/expressions
 *
 * - we can interrupt the loop by using "continue" and/or a "break" statement
 *
 * continue
 *  - this keyword bypasses the part of the code that is below the continue keyword and continues with the next
 *    iteration
 *
 * break
 *  - use to exit the loop depending on the condition we are checking
 *
 *
 *
 */

public class Main {

    public static void main(String[] args) {

        //Example-1 : while-loop
        System.out.println("Example-1 : while loop");
        int count = 1;
        while (count <= 5) {
            System.out.println("count = " + count);
            count++;
        }

        //Example-2 : for-loop
        System.out.println("Example-2 : for loop");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }


        //Example-3 : while loop with continue and break
        System.out.println("Example-3 : while loop continue and break");
        int number = 0;
        while (number < 15){
            number++;

            if (number <= 5){
                System.out.println("Skipping number "+number);
                continue;
            }

            System.out.println("number = "+number);

            if (number >= 10){
                System.out.println("Breaking at "+number);
                break;
            }
        }
    }
}
