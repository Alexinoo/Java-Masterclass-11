package lambda_expressions.part10_lambda_mini_challenges;

import java.util.function.Supplier;

/*
 * Challenge #7
 * ............
 *
 * - As with Function, the Supplier won't do anything until we use it
 * - Use this supplier to assign the string "I love Java!" to a variable called supplierResult.
 * - Then print the variable to the console
 */

public class Challenge7 {

    public static void main(String[] args) {
        Supplier<String> iLoveJava = () -> "I love Java!";

        String supplierResult = iLoveJava.get();

        System.out.println(supplierResult);
    }
}
