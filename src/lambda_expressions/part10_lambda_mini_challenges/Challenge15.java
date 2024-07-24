package lambda_expressions.part10_lambda_mini_challenges;

import java.util.Arrays;
import java.util.List;

/*
 * Challenge #15
 * .............
 *
 *  - Let's go back to the previous version of the code, when we were printing out the sorted names
 *  - Suppose we want to debug what's going on when the chain is executed
 *
 *  - Instead of printing out the names at the end of the chain, maybe we're not sure if the code that uppercase's the first letter is working
 *    correctly
 *
 *  - Let's use peek() to print out the names after the map() method has executed.
 *
 *  - What will the following code print to the console ?
 *
         topNames2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo);
 *
 *
 * The Answer:
 *  - Nothing
 *  - This chain doesn't contain a terminal operation and so, nothing is printed out
 *  - Remember that stream chains are evaluated lazily
 *      - Nothing happens until a terminal operation is added to the chain
 *      - At that point, the chain is executed
 */
public class Challenge15 {

    public static void main(String[] args) {
        List<String> topNames2015 = Arrays.asList("Amelia","Olivia","emily","Isla","Ava",
                "oliver","Jack","Charlie","harry","Jacob");

        topNames2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo);
    }
}
