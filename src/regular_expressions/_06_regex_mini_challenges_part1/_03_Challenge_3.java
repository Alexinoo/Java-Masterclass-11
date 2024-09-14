package regular_expressions._06_regex_mini_challenges_part1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Challenge #3
 * ............
 *
 *  - In Challenge#2 , we used the same regular expression twice
 *
 *  - Use the Matcher.matches() to check for matches , instead of String.matches() for the regExp that uses \\w+.
 *
 * Hint:
 *  - You'll have to compile a pattern
 *
 *
 * /////////////
 *  Solution
 * /////////////
 *
 *      String challenge1 = "I want a bike.";
        String challenge2 = "I want a ball.";

        String regExp = "I want a \\w+.";
 *          OR
        String regExp = "I want a (bike|ball).";
 *
 *
 *      Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(challenge1);
        System.out.println(matcher.matches());

        matcher = pattern.matcher(challenge2);
        System.out.println(matcher.matches());
 *
 *      - prints true in both cases
 *      - we've compiled our regular expression into a Pattern
 *      - then we've created a matcher with a challenge1 String
 *      - then called matcher.matches()
 *
 *      -/////
 *      - Did the same thing challenge2 String
 *      - But at that point, we don't need to compile our pattern again
 *      - We can get a new Matcher instance using the same Pattern obj and our challenge2 String
 *
 * - Note that we don't have to reset the matcher here because we're using different matcher instances with each
 *   challenge
 */
public class _03_Challenge_3 {

    public static void main(String[] args) {

        String challenge1 = "I want a bike.";
        String challenge2 = "I want a ball.";

        String regExp = "I want a \\w+.";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(challenge1);
        System.out.println(matcher.matches());

        matcher = pattern.matcher(challenge2);
        System.out.println(matcher.matches());

    }
}
