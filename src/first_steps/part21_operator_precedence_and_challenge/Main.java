package first_steps.part21_operator_precedence_and_challenge;
/*
 * Operator Precedence and Operator challenge
 * ..........................................
 *
 * - Look for Java Operator Precedence table
 * - Gives us a handy list of precedence table in Java
 *    - shows us how Java decides the priority of evaluating things in an expression
 *
 * - Note that the highest operator () , [] , and . has a precedence of 15
 * - Multiplication, Division and modulus operators have a precedence of 12
 * - Addition and Subtraction operators have a precedence of 11
 *
 * - Keep in mind, you can use parentheses to override other things like multiplication, sort of like to force the
 *   condition to be evaluated in the order that you think it would be
 * - It's often a good practice to put parentheses around conditions
 *
 * Operator Challenge
 * ..................
 * 1- Create a double variable with a value of 20.00
 * 2- Create a second variable of type double with a value of 80.00
 * 3- Add both numbers together and multiply by 100.00
 * 4- Use the remainder operator to figure out what the remainder from the result of the operation in Step-3 and 40.00
 * 5- Create a boolean variable that assigns the value true, if the remainder in Step-4 is 0 , or false if it's not zero
 * 6- Output the boolean variable
 * 7- Write an if-then statement that displays a message "Got some remainder" if the boolean in Step-5 is not true
 *
 * - Don't be surprised if you see output for this step, where you might expect it not to show
 *
 *
 * ////// Explanation ////////////////
 * - Note that multiplication has a higher precedence than addition
 * - in the line below
 *       double myValuesTotal = myFirstValue + mySecondValue * 100.00d;
 *
 * - mySecondValue is multiplied by 100 first, to 8000
 * - and then added myFirstValue 20 , which results to 8020
 * - that's the reason, we did not get the solution we thought we might get
 *
 * - What we wanted is 80 plus 20 and then multiply by 100 to get 10000
 * - The 1000 % 40 is 0 , meaning the is nor remainder and the if-then statement would not be executed
 *
 * - Therefore, this where, we need to use the parentheses, to clarify the meaning, since we know that parentheses has
 *   a higher precedence than multiplication
 *      double myValuesTotal = (myFirstValue + mySecondValue) * 100.00d;
 * - (myFirstValue + mySecondValue) is going to be evaluated first now and the result will then be multiplied by 100
 *
 * - And if we run this again, we now get the expected output
 */
public class Main {

    public static void main(String[] args) {
        double myFirstValue = 20.00d;
        double mySecondValue = 80.00d;

       // double myValuesTotal = myFirstValue + mySecondValue * 100.00d; // 8020 - Operator precedence
        double myValuesTotal = (myFirstValue + mySecondValue) * 100.00d; // 10000
        System.out.println("MyValuesTotal = "+myValuesTotal);

        double theRemainder = myValuesTotal % 40.00d;
        System.out.println("theRemainder = "+theRemainder);

        boolean isNoRemainder = (theRemainder == 0) ? true : false;
        System.out.println("isNoRemainder = "+isNoRemainder);

        if (!isNoRemainder){
            System.out.println("Got some remainder");
        }
    }
}
