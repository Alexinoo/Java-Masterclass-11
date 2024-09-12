package regular_expressions._07_regex_minichallenge_part2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Challenge #8
 *
 * - Modify the regular expression in challenge#7 to use a group so that we can print all the digits that occur in a
 *   string that contains multiple occurrences of the pattern
 * - Write all the code required to accomplish ( create a pattern and matcher, etc)
 *
 * - Use the following string to test your code
 */
public class _08_challenge_8 {

    public static void main(String[] args) {
        String regExp = "^[A-z][a-z]+\\.\\d+$"; // regExp in challenge#7

        regExp = "[A-Za-z]+\\.(\\d+)";

        String challenge8 = "abcd.135uvqz.7tzik.999";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(challenge8);


        while (matcher.find()){
            System.out.println("Occurrences : "+ matcher.group(1));
        }

    }
}
