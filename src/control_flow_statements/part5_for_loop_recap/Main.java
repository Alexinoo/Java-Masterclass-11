package control_flow_statements.part5_for_loop_recap;

/*
 * for Loop Recap
 * ..............
 *  - for loop structure
 *
 *      for(init; condition ; increment){
 *          // statements
 *      }
 *
 * - we have 3 parts
 *
 * - init
 *      - initialize variables or variable in this case
 *
 * - condition
 *      - determines when the loop ends
 *
 * - increment
 *      - also known as iterator or iterator step
 *      - 1 execution of the loop statement is known as the 1 iteration
 *      - we can increment with 1 or 5 or also decrement by any number, depends on the situation
 *
 * - in short, we say that the loop iterates, or executes a block of code when the condition is true, a number of
 *   times/steps and the loop stops when the condition becomes false
 *
 * - the block of code is defined by curly braces, in the loop body, we can have multiple statements
 * - those steps will execute every step, or we can say they'll execute every iteration
 *
 * #Example-1
 * ........
 * - In the example below, we have a for loop that prints out the value of some numbers
 *
 * - the init part is
 *      - "int number = 1"
 *      - this means we are starting at 1
 *
 * - the condition is
 *      - "number < 7"
 *
 * - the increment is
 *      - "number += 2"
 *      - we are incrementing the number variable by 2
 *      - this for loop will print every 2nd number
 *
 * Steps for execution
 * ...................
 *  - Step 1
 *      - Initialize number variable to 1
 *
 *  - Step 2
 *      - execute line (code block)
 *      - Check condition 1 < 7 is true
 *
 *  - Step 3
 *      - Since the condition is true
 *          System.out.println("number = "+number);
 *      - the line of code above will be executed which prints "number = 1" to the console
 *
 * - Step 4
 *      - execute iteration step and the number 1 is incremented by 2
 *          1 + 2 = 3
 *          number = 3
 *
 * - So at the end of the fourth step, we've added 2 to the number, and now the number is 3, or the value of the number
 *   is now 3 & we start the next iteration by going back to Step 2
 *      number = 3
 *
 * - Step 1:
 *      - check condition 3 < 7 which evaluates to true
 *
 * - Step 2:
 *      - execute line (code block)
 *      - prints "number = 3" to the console
 *
 * - Step 3:
 *      - execute iteration step
 *          3 + 2 = 5
 *          number = 5
 *      - the value of the number is now 5
 *
 * - Then we got to the next iteration of the for loop
 * - the number is now 5
 *
 *  - Step 1
 *      - check condition 5 < 7 which evaluates to true
 *
 *  - Step 2
 *      - execute line (code block)
 *      - prints "number = 5" to the console
 *
 *  - Step 3:
 *      - execute iteration step
 *          5 + 2 = 7
 *      - "number = 7"
 *      - so the value of the number variable after the increment is now 7
 *
 * - Then we move on to the next iteration
 *
 *  - Step 1
 *      - check condition 7 < 7 which evaluates to false
 *      - the loop exits at this point and the code at the code block is not executed at this point
 *      - the code will continue after the closing curly braces
 *
 * - Therefore the final output will be
 *      number = 1
 *      number = 3
 *      number = 5
 *
 *
 * #Example-2
 * ..........
 *  - This for loop will never execute because
 *      - the number is initialized to 1
 *      - the condition 1 < 0 evaluates to false
 *      - the code block will never execute and will be bypassed
 *      - the loop exits and continue executing after the closing curly brace
 *
 * - So you really have to be careful with the conditions in the for loop, since as you can see if the condition
 *   is wrong, it'll never execute at all
 *
 * - To fix this loop and execute the code block, we have to change the condition
 *      - change the condition from
 *          number < 0
 *      - to
 *          number < 5
 *
 *      - refer to #Example-3
 *
 * Example-3
 * - By doing this, it will then print
 *      number = 1
 *      number = 3
 * - before closing down and exiting
 *
 * Example-4
 *  - the loop will basically loop forever
 *  - the condition 100 > 0 , but we're also incrementing the number by 10
 *  - every number above 100 is also > 0
 *  - this kind of loop will never end and is called endless loop
 *  - the program will stop once it gets to a maximum value of an integer - 2147483647
 *  - in many cases with endless loops programs might block forever or crash when it runs out of memory
 *
 * Example-5
 *  - to fix the above problem, we can change the condition
 *      number < 130
 *  - before it was number > 0
 *  - so this way the output will be printed in the console
 *      number = 100
 *      number = 110
 *      number = 120
 *  - and that's because we have changed the condition to < 130
 *
 * - There is also another variation of the for loop, known as foreach which we'll look at when we cover arrays and
 *   objects
 *
 */
public class Main {
    public static void main(String[] args) {

        //Example-1:
        for (int number = 1; number < 7; number += 2) {
            System.out.println("number = " + number);
        }

        //Example-2:
        for (int number = 1; number < 0;number += 2) {
            System.out.println("number = " + number);
        }

        //Example-3:
        for (int number = 1; number < 5; number += 2) {
            System.out.println("number = " + number);
        }


        //Example-4:
        for (int number = 100; number > 0; number += 10) {
            System.out.println("number = " + number);
        }

        //Example-4:
        for (int number = 100; number < 130; number += 10) {
            System.out.println("number = " + number);
        }

    }
}
