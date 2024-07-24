package lambda_expressions.part10_lambda_mini_challenges;

import java.util.function.Function;

/*
 * Challenge #4
 * ............
 *
 * - Instead of executing this function directly, suppose we want to pass it to a method
 * - Write a method called everySecondCharacter that accepts the function as a parameter and executes it with the argument "1234567890"
 *
 * - It should return the result of the function.
 * - For bonus points, don't hard-code the argument string within the method
 */
public class Challenge4 {
    public static void main(String[] args) {
        Function<String,String> lambdaFunction = source -> {
            StringBuilder returnValue = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1){
                    returnValue.append(source.charAt(i));
                }
            }
            return returnValue.toString();
        };

    }

    public static String everySecondChar(Function<String,String> function , String source){
        return function.apply(source);
    }
}
