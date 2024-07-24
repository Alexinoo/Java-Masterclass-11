package lambda_expressions.part10_lambda_mini_challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Challenge #12
 * .............
 *
 * - Change the code written in Challenge#11 so that it uses method references.
 * - Remember that a method reference looks like Class::MethodName
 */
public class Challenge12 {

    public static void main(String[] args) {
        List<String> topNames2015 = Arrays.asList("Amelia","Olivia","emily","Isla","Ava","oliver","Jack","Charlie","harry","Jacob");

        List<String> firstUpperCaseList = new ArrayList<>();
        topNames2015.forEach(name -> firstUpperCaseList.add( name.substring(0,1).toUpperCase() + name.substring(1)));

        firstUpperCaseList.sort(String::compareTo);

        firstUpperCaseList.forEach(System.out::println);
    }
}
