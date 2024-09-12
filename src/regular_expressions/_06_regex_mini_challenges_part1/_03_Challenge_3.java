package regular_expressions._06_regex_mini_challenges_part1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Challenge #3
 *
 *  - In Challenge#2 , we used the same regular expression twice
 *  - Use the matcher.matches() to check for matches , instead of String.matches() for the regExp that uses \\w+.
 *
 * Hint:
 *  - You'll have to compile a pattern
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
