package lambda_expressions.part10_lambda_mini_challenges;

import java.util.function.Function;

/*
 * Challenge #2
 * ............
 *
 * - Write the following method as a lambda expression.
 * - Don't worry about assigning it to anything
 *
 *   public static String everySecondChar(String source){
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1){
                returnValue.append(source.charAt(i));
            }
        }
        return returnValue.toString();
    }
 *
 * - So does this lambda map to any of the interfaces in the Java.util.function package ?
 *   - In this case it does, it takes a parameter and returns a value
 *   - So it maps to the
 *      Function<String , String >

 * - So, to actually get this working, we can add the reference here
 *       Function<String,String> everySecondChar
 *
 * - And now we don't need (String source) because the prior type is implied because of the function definition we have set up
 */
public class Challenge2 {

    public static void main(String[] args) {

       Function<String,String> everySecondChar = source->{
            StringBuilder returnValue = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1){
                    returnValue.append(source.charAt(i));
                }
            }
            return returnValue.toString();
        };

    }

}
