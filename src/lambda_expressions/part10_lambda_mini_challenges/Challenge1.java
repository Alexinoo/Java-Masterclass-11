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

