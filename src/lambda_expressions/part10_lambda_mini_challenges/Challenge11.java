package lambda_expressions.part10_lambda_mini_challenges;

import java.util.*;

/*
 * Challenge #11
 * .............
 *
 * - Suppose we have the following list of the top 5 male and female names for 2015:
 *
 * - Write the code to print the items in the list in sorted order and with the first letter in each name upper-cased
 *       List<String> topNames2015 = Arrays.asList(
 *                                  "Amelia",
 *                                  "Olivia",
 *                                  "emily",
 *                                  "Isla",
 *                                  "Ava",
 *                                  "oliver",
 *                                  "Jack",
 *                                  "Charlie",
 *                                  "harry",
 *                                  "Jacob"
 *                              );
 * - For example:
 *      - The name "harry" should be printed as "Harry" and should be printed after "Emily" and before "Isla"
 * - Use lambda expressions wherever possible
 *
 * ////// Solution 2 ////////////////
 * - Uses lambda in 3 places
 *      - With the forEach()
 *      - With the sort( Comparator<? super String > c)
 *      - With the System.out,println(s)
 */
public class Challenge11 {

    public static void main(String[] args) {
        List<String> topNames2015 = Arrays.asList("Amelia","Olivia","emily","Isla","Ava","oliver","Jack","Charlie","harry","Jacob");

        //Solution #1
        Collections.sort(topNames2015);
        topNames2015.forEach( name -> {
            System.out.println(name.substring(0,1).toUpperCase() + name.substring(1));
        });

        System.out.println("==========================================");

        // Solution #2
        List<String> firstUpperCaseList = new ArrayList<>();
        topNames2015.forEach(name -> firstUpperCaseList.add( name.substring(0,1).toUpperCase() + name.substring(1)));

        // sort - firstUpperCaseList.sort((s1,s2)->s1.compareTo(s2));
        Collections.sort(firstUpperCaseList); // sort with static method

        // print
        firstUpperCaseList.forEach(s -> System.out.println(s));

    }
}
