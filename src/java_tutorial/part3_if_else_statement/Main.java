package java_tutorial.part3_if_else_statement;

/*
 * Code Blocks and the if then Else Control Statements
 * ...................................................
 *
 * - Define variables to work with
 *      - boolean gameOver and set it to true
 *      - score of type int with a value of 5000
 *      - levelCompleted of type int with value of 5
 *      - bonus of type int with the value of 100
 *
 * - Test whether the score is equal to 5000 and if so
 *      - print the player scored 5000
 *
 * - We can also remove the braces {} , get rid of thh code block, if we've only got line/statement to print out or executed after the test
 * - Also , note that, if we omit the curly braces, any statement that we add after that will be executed regardless of whether our condition evaluates
 *   to true/false
 *
 * - If we add the braces, then it's either all or none
 *
 * - The use of code blocks are optional if you've got only 1 statement to process
 *
 * - However, Tim recommends that even if we only have 1 line, you should probably use a code block anyway because it makes the code clearer and
 *   easy to understand
 *
 * Next,
 * - Test for less than 5000 and if true
 *      - "Your score was less than 5000"
 * - add an else block that prints when the condition is false
 *      - "Got here"
 *
 * - Test for less than or equal to 5000
 *      - "Your score was less than 5000" is executed
 *
 * Next
 * - Test for less than 5000 and greater than 1000
 *      - print "Your score was less than 5000 but greater than 1000"
 *
 * - add an else if that test for less than 1000
 *      - print "Your score was less than 1000"
 * - add an else block
 *      - prints "Got here" if none of the conditions is true
 *
 * - update score to different values and re-run
 *
 * Next,
 * - Test if gameOver is true
 *      - create a variable in the code block - final score and calculate final score and assign the result
 *      - print it out
 *
 * - Talked a lit bit more on the scope of variable
 *      - finalScore whose scope is local to the code block it has been declared in
 *      - cannot be accessed outside the code block it has been defined
 *
 * - score, levelCompleted and bonus are global variable and that's the reason we can access them inside the code block
 *
 * Challenge
 * .........
 * - Print out a second score on the screen with the following
 *      - set score to 10000
 *      - set levelCompleted to 8
 *      - set bonus to 200
 * - Ensure the first printout above still displays as well
 *
 * Solution
 * ........
 * - We can have different approaches to solve this
 *   - Create a complete new set of variables (copy and paste the old variables and rename and update the values)
 *      - The limitation of this approach is that we're using excess memory, by creating new variables that we don't potentially need
 *
 *   - Re-use those variables and re-assign the values
 *      - The advantage is that it's quicker & we're not creating new variables
 *      - we use the memory more efficiently
 *      - The limitation, is that we don't have a permanent record of these variables
 *          - if we wanted to keep the fact that this first score was 800, and the level completed was 5 and bonus 100
 *          - we can't do that because we've redefined the variables
 *          - secondly, we've used gameOver twice, if we were to add a 2nd player, this would really be difficult to determine what
 *            player we're referring to
 *
 *      - Another limitation is we're copy-pasting code, or rather duplicating, this means that if we need to make a change, we'll need
 *        to change it in 1 or more than 1 place
 *          - If we forget tht our code is duplicated, you may forget to change in 1 place which means that your scores may not be calculated
 *            correctly
 *      - For example,
 *          - If we change the formula for calculating the final score, and add a bonus of 1000
 *              e.g. finalScore += 1000;
 *      - we also need to make the change to the second calculation, to get the other score correctly
 *
 * - In short, duplicating the code means that we need to go back and remember all the places where the code is duplicated and make a change
 * - We're really asking for errors in the long term because let's face it, we're all humans, we make mistakes, we forget things and it is very
 *   common in programming, particularly as the code you work on gets complicated
 *
 * - Solution : use the concept of methods
 *      - allows us to reuse code - type the code once and reuse it multiple times
 *
 *
 */
public class Main {
    public static void main(String[] args) {

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

       // if (score == 5000) {
       //     System.out.println("Your score was 5000");
       //      System.out.println("This will be executed no matter what the condition is..");
       // }

       //  if (score < 5000) {
       //      System.out.println("Your score was less than 5000");
       //  }else {
       //      System.out.println("Got here");
       //  }

       // if (score < 5000 && score > 1000) {
       //      System.out.println("Your score was less than 5000");
       //  } else if (score < 1000) {
       //       System.out.println("Your score was less than 1000");
       //   } else {
       //      System.out.println("Got here");
       //  }

        if (gameOver){
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 1000;
            System.out.println("Your final score was "+finalScore);
        }

        score = 10000;
        levelCompleted = 8;
        bonus = 200;

        if (gameOver){
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score was "+finalScore);
        }


    }
}
