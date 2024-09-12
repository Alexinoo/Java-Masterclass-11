package regular_expressions._07_regex_minichallenge_part2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Challenge #10
 *
 * - Instead of printing out the number themselves, print out their start and end indices
 * - Use the same string we used for challenge#9
 * - Make those indices inclusive
 *
 * - For example:
 *      - the start index printed for 135 should be 5
 *      - the end index should be 7
 *
 * - Hint
 *      - check for the documentation for Matcher.start() and Matcher.end() methods
 *      - There's more than 1 version of these methods
 */
public class _10_challenge_10 {

    public static void main(String[] args) {

        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";

        Pattern pattern = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
        Matcher matcher = pattern.matcher(challenge9);

        while (matcher.find()){
            System.out.println("Occurrence : start = "+ matcher.start(1) + " end = "+ (matcher.end(1) - 1));
        }
    }
}
