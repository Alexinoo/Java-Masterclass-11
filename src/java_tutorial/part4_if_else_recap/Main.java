package java_tutorial.part4_if_else_recap;
/*
 * If - Then - Else Recap
 * ......................
 *
 * - The if statement identifies which statement or code block to run based on the value of an expression
 *      - In other words, based on a specific condition
 *
 * - Inside the code block defined by curly braces { // statement(s) } , we can have 1 or multiple statements
 *
 * - We can use the else statement after the if.
 *      - In that case, when the condition is false the else block will be executed
 *
 * - We can also add else-if to test multiple conditions
 *
 * - The following is the basic structure of the if then else statement
 *
 *      if(condition){
 *          // if statement (block)
 *      }else{
 *          // else statement (block)
 *      }
 *
 * - When the condition is true, the block inside the if statement will be executed
 *      - This block can be 1 statement or multiple statements
 *
 * - When the condition is false, the block inside the else statement will be executed
 *
 * - In other words,
 *      - Both blocks will never execute at the same time, only 1 will, based on the condition being true or false
 *
 * - A block is defined by curly braces and there is no semicolon that should be added after the if statement
 *      - Adding a semicolon will cause an unwanted behavior
 *
 *
 * More Example
 * ............
 *
 * 1st Example
 * score = 6000
 *  - Test expression "score >= 5000", in this case the expression result is true
 *  - Execute block, prints the message "Your score was >= to 5000"
 *  - The code then jumps to the first line after the last curly brace and continue with the rest of the program
 *
 * 2nd Example
 * score = 800
 *  - Test condition, in this case it's false since 800 >= 5000 is false
 *      - if block is ignored
 *  - Test condition in this case, we have true and true : 800 < 1000 is true and 800 >= 500 is also true
 *      - The 2 && requires both the conditions to be true which is true
 *      - Execute block and print "Your score was < 1000 but >= 500"
 *  - Code will jump to the first line after the last curly brace and continue with the rest of the program
 *
 * 3rd Example
 * score = 200
 *  - Test condition, in this case, is false
 *  - Test condition, in this case, is false
 *  - No more conditions and as a result the else block gets executed
 *      - "Your score was < than 500" will be printed
 *  - The code will then jump to first line after the last curly braces and continue with rest of program
 *
 */
public class Main {

    public static void main(String[] args) {

        int score = 6000;
        score = 800;
        score = 200;

        if (score >= 5000){
            System.out.println("Your score was >= to 5000");
        } else if (score < 1000 && score >= 500) {
            System.out.println("Your score was < 1000 but >= 500");
        }else {
            System.out.println("Your score was < 500");
        }
    }
}
