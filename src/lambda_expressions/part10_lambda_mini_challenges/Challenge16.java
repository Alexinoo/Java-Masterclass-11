package lambda_expressions.part10_lambda_mini_challenges;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Challenge #16
 * .............
 *
 * - Add a terminal operation to this chain so that the peek call will execute
 *
 * - Since the peek() call is printing every item, try to do something else with the terminal operation
 * - Don't print out the items again
 *
 * Solution
 *  - use an empty forEach
 *  - It's printing out the names at the stage, which they aren't sorted yet , because the stream for sorting is after the peek()
 *
 * - We can also use .collect(Collectors.toList()) , also other than .forEach(name -> {})
 *
 * - We've used .collect(Collectors.toList()) as a terminal operation to store the result of our change into a list
 *
 * - We may want to pass the results to a method or do something else with the list at that point
 *
 * - Get used to working with lambdas and streams by practising using them
 *      - At some point you'll think of lambda expression first rather than using an anonymous class
 */
public class Challenge16 {
    public static void main(String[] args) {
        List<String> topNames2015 = Arrays.asList("Amelia","Olivia","emily","Isla","Ava",
                "oliver","Jack","Charlie","harry","Jacob");

        topNames2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                //.forEach(name -> {}); // Using an empty forEach to trigger the processing
                .collect(Collectors.toList()); //
    }
}
