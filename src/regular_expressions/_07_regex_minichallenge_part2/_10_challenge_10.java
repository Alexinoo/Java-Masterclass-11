package regular_expressions._07_regex_minichallenge_part2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Challenge #10
 * .............
 *
 * - Instead of printing out the number themselves, print out their start and end indices
 * - Use the same string we used for challenge#9
        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";
 *
 * - Make those indices inclusive
 *
 * - For example:
 *      - the start index printed for 135 should be 5 and the end index should be 7
 *
 * - Hint
 *      - check for the documentation for Matcher.start() and Matcher.end() methods
 *      - follow the link below for more info
 *          https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html
 *      - There's more than 1 version of these methods
 *
 *
 * //////////////
 *  Solution
 * //////////////
 *
 * - This is a tricky challenge for a couple of reasons
 *      - We have to use the versions of the start and end methods that accept a group number since we're only
 *          interested with the indices of the group matches, not the pattern matches as a whole
 *      - The end() returns the first index after the match ends
 *          - To get the index of the final matching character, we have to subtract 1 from the end() return value
 *
 *      Pattern pattern = Pattern.compile("(?i)[A-Z]+\\.(\\d+)\\s");
        Matcher matcher = pattern.matcher(challenge9);

        while (matcher.find()){
            System.out.println("Occurrence : start = "+ matcher.start(1) + " end = "+ (matcher.end(1) - 1));
        }
 *
 *      - prints below, representing indices of the numbers
 *          Occurrence : start = 5 end = 7
            Occurrence : start = 14 end = 14
            Occurrence : start = 21 end = 23
 */
public class _10_challenge_10 {

    public static void main(String[] args) {

        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";

        Pattern pattern = Pattern.compile("(?i)[A-Z]+\\.(\\d+)\\s");
        Matcher matcher = pattern.matcher(challenge9);

        while (matcher.find()){
            System.out.println("Occurrence : start = "+ matcher.start(1) + " end = "+ (matcher.end(1) - 1));
        }
    }
}
