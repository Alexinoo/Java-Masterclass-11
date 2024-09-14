package regular_expressions._07_regex_minichallenge_part2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Challenge #8
 * ............
 *
 * - Modify the regular expression in challenge#7 to use a group, so that we can print all the digits
 *     that occur in a String that contains multiple occurrences of the pattern
 *
 * - Write all the code required to accomplish ( create a pattern and matcher, etc)
 *
 * - Use the following string to test your code
 *
        String challenge8 = "abcd.135uvqz.7tzik.999";
 *
 * - There are 3 occurrences of the pattern we looked for in challenge#7
 * - When you run your code, you should see 135 , 7 and 999 printed to the console
 *
 *
 * //////////
 *  Solution
 * //////////
 *
 *
        String challenge8 = "abcd.135uvqz.7tzik.999";
        String regExp = "(?i)[a-z]+\\.(\\d+)";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(challenge8);

        while (matcher.find()){
            System.out.println("Occurrences : "+ matcher.group(1));
        }
 *
 *      - we're now interested in matching parts of the String rather than the string as a whole
 *      - notice that we have removed ^ and $ boundary matches that we used in challenge#7
 *      - This is because we're only interested in printing the numbers part of the pattern, and we've put
 *         the part of the pattern that matches the digits into a group
 *      - we've done this by surrounding that part of the regular expression with parentheses
 *
 * - Below will also work
 *
        regExp = "(\\d+)";
 */
public class _08_challenge_8 {

    public static void main(String[] args) {
        String regExp = "^[A-z][a-z]+\\.\\d+$"; // regExp in challenge#7

        regExp = "(?i)[a-z]+\\.(\\d+)";

        String challenge8 = "abcd.135uvqz.7tzik.999";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(challenge8);

        while (matcher.find()){
            System.out.println("Occurrences : "+ matcher.group(1));
        }

    }
}
