package lambda_expressions.part10_lambda_mini_challenges;
/*
 * Challenge #2
 * ............
 *
 * Write the following method as a lambda expression.
 * Don't worry about assigning it to anything
 */
public class Challenge2 {

    public static void main(String[] args) {
        System.out.println(everySecondChar("Alex")); // lx
    }
    public static String everySecondChar(String source){
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1){
                returnValue.append(source.charAt(i));
            }
        }
        return returnValue.toString();
    }
}
