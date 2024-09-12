package regular_expressions._07_regex_minichallenge_part2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Challenge #9
 *
 * - Let's suppose we're reading strings that match the patterns we used in challenges 7 and 8 from a file
 *
 * - Tabs are used to separate the matches, with 1 exception
 *
 * - The last match is followed by a newline
 *
 * - Revise the regular expression accordingly and extract all the numbers , as we did in challenge 8
 *
 */
public class _09_challenge_9 {

    public static void main(String[] args) {
        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";

        Pattern pattern = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
        Matcher matcher = pattern.matcher(challenge9);

        while (matcher.find()){
            System.out.println("Occurrence : "+ matcher.group(1));
        }


    }
}
