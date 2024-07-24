package lambda_expressions.part10_lambda_mini_challenges;

import java.util.function.Supplier;

/*
 * Challenge #6
 * ............
 *
 * - Write a a lambda expression that maps to the java.util.Supplier interface
 * - This lambda should return this string "I love Java!"
 * - Assign it to a variable called iLoveJava
 *
 * Solution
 *  - Recall that supplier produces object(s)
 *  - Supplier Interface doesn't accept any arguments
 *  - And since the lambda body here is a single statement, we don't have to include the return keyword because it is implied
 *
 *  - But if we wanted to include it for clarity, we could change this slightly to do that, but we'll need to
 *      add a code block
 *  - Again, if we use a return statement, we have to use curly braces to open the code block and then terminate with a semicolon at the end of
 *    the body statement as well as at the end of the code block to complete the line
 */
public class Challenge6 {
    public static void main(String[] args) {

        Supplier<String> iLoveJava = () -> "I love Java!";
    }
}
