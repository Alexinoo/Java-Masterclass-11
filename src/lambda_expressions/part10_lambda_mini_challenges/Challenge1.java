package lambda_expressions.part10_lambda_mini_challenges;

/*
 * Challenge #1
 * ............
 *
 * Write the following anonymous class as a lambda expression
 *
 * Anonymous Runnable

 Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts ) {
                System.out.println(part);
            }
        }
    };
 *
 *
 * - So does this lambda map to any of the interfaces in the java.util.function package ?
 *     - Well, actually no, it doesn't
 *     - It doesn't take any arguments and it doesn't return any value
 *     - All the interfaces in the java.util.function package, either take an argument or arguments or return a value, or do both
 */
public class Challenge1 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts ) {
                System.out.println(part);
            }
        };

        new Thread(runnable).start();
    }
}

