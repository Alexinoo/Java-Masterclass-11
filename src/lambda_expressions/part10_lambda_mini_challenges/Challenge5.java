package lambda_expressions.part10_lambda_mini_challenges;
import java.util.function.Function;

/*
 * Challenge #5
 * ............
 *
 * - Using the bonus version, call the method with the lambdaFunction we created earlier and the string "1234567890"
 * - Print the result returned from the method
 */

public class Challenge5 {
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

        String result = everySecondChar(lambdaFunction,"1234567890");
        System.out.println(result);
    }

    public static String everySecondChar(Function<String,String> function , String source){
        return function.apply(source);
    }
}
