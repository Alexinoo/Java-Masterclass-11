package lambda_expressions.part10_lambda_mini_challenges;

import java.util.Arrays;
import java.util.List;

/*
 * Challenge #14
 * .............
 *
 * - Using Challenge #13, instead of printing out the sorted names, print out how many names start with the letter 'A' instead
 *
 * Hints:
 *  - You'll have to modify the stream chain
 *  - Add another statement to print the number of items
 *
 * Solution
 *  - Use filter() to remove all items from the stream that don't begin with "A"
 *  - Use count() to get the number of items in the resulting stream
 */
public class Challenge14 {

    public static void main(String[] args) {

        List<String> topNames2015 = Arrays.asList("Amelia","Olivia","emily","Isla","Ava",
                "oliver","Jack","Charlie","harry","Jacob");

        long namesBeginningWithA = topNames2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println("Number of names beginning with A is : " +namesBeginningWithA);
    }
}
