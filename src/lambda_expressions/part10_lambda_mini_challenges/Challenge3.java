package lambda_expressions.part10_lambda_mini_challenges;

import java.util.function.Function;

/*
 * Challenge #3
 * ............
 *
 * - Right now the function in Challenge #2, doesn't do anything
 * - Write the code that will execute the function with an argument of "1234567890"
 *
   Function<String,String> everySecondChar = source->{
            StringBuilder returnValue = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1){
                    returnValue.append(source.charAt(i));
                }
            }
            return returnValue.toString();
        };
 *
 *
 */
public class Challenge3 {

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


        System.out.println(everySecondChar.apply("1234567890"));
    }
}
