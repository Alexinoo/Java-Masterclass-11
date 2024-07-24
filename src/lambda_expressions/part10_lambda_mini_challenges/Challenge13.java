package lambda_expressions.part10_lambda_mini_challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Challenge #13
 * .............
 *
 * - Using Challenge #12, do the same thing (Uppercase first letter, then sort and print the list) using a stream and a chain of stream operations
 *
 *
 * Solution
 *  - We didn't have to create a new list to hold the names with uppercase first letters
 *  - The map() returns a stream containing all the transformed names
 *
 * - This code is a little bit more concise than the previous versions and easy to read
 *
 * - So, we went from Java code that used lambda expressions to more concise code that used method references to streams
 */
public class Challenge13 {

    public static void main(String[] args) {

        List<String> topNames2015 = Arrays.asList("Amelia","Olivia","emily","Isla","Ava",
                                                "oliver","Jack","Charlie","harry","Jacob");

        //List<String> firstUpperCaseList = new ArrayList<>();
        //topNames2015.forEach(name -> firstUpperCaseList.add( name.substring(0,1).toUpperCase() + name.substring(1)));
        //firstUpperCaseList.sort(String::compareTo);
        //firstUpperCaseList.forEach(System.out::println);

        topNames2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .sorted(String::compareTo)
                .forEach(System.out::println);

    }
}
