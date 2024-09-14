package regular_expressions._07_regex_minichallenge_part2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *
 * Challenge #11
 * .............
 *
 * - Suppose we have the following string containing points on a graph within curly braces.
 *
 *      String challenge11 = "{0, 2},{0, 5},{1, 3},{2, 4}";
 *
 * - Extract what's in the curly braces
 *
 *
 * ///////////
 *  Solution
 * ////////////
 *
 *      String regExp = "\\{(.+?)\\}";
 *
 *      Where:

 *      - \\{  \\} - escape both the left and right curly brace characters as they're used in regular expressions with
 *                   quantifiers
 *      - (.+?) - we want anything inside the curly braces to match
 *
 *      - remember to put the contents of the curly braces into group by using parentheses
 *      - we've used (.+?) to get the contents of the braces
 *          - dot (.) matches any character
 *          - + expects at least 1 character of any kind
 *          - ? turns the plus quantifier into a lazy quantifier and it prevents from matching more characters than we want
 *              essentially
 *          - will actually limit the match to the content of the curly braces
 *          - if we were to remove the question mark quantifier, there would only be 1 match because the plus quantifier would
 *                 gobble up everything between the first opening brace and the last closing brace
 *              e.g.
 *                      Occurrence: 0, 2},{0, 5},{1, 3},{2, 4
 *
 * ///////
 *
 * - That worked fine, if we know that our data only contains points within curly braces
 * - If we wanted to be more precise and only match a digit, followed by a comma and a space followed by a digit, we could do
 *   something like this
 *
 * - let's modify our String to
 *      challenge11 = "{0, 2},{0, 5},{1, 3},{2, 4},{x, y},{6, 34}, {11, 12}";
 *
 *      - added also {x, y} into the mix above
 *
 * - Then change or regular expression to
 *
 *      regExp =  "\\{(\\d+, \\d+)\\}"
 *
 *      - \\{ escape first curly brace
 *      - use \\d+
 *          - used \\d+ character class followed by the plus quantifier to indicate that we want more than 1 or more digits
 *          - followed by a comma, then a space
 *      - followed by 1 or more digits : \\d+
 *      - \\}escape last curly brace
 *
 * - We should only get the numerics when we run that are part of the curly braces
 *      - {x, y} pair is skipped,  doesn't match the regular expression above
 *
 *
 * /////
 * - If we wanted only to extract the digits only without the comma and the space, we'd actually put each set of digits into
 *    its own group
 * - For example:
 *
 *       pattern = Pattern.compile("\\{(\\d+)\\, (\\d+)\\}");
 */
public class _11_challenge_11 {
    public static void main(String[] args) {

        String challenge11 = "{0, 2},{0, 5},{1, 3},{2, 4}";

        Pattern pattern = Pattern.compile("\\{(.+?)\\}");
        Matcher matcher = pattern.matcher(challenge11);

        while (matcher.find()){
            System.out.println("Occurrence: "+matcher.group(1));
        }

        System.out.println("_".repeat(50));
        /////////////////////////////////////////////
        /////////////////////////////////////////////

        challenge11 = "{0, 2},{0, 5},{1, 3},{2, 4},{x, y},{6, 34}, {11, 12}";

        pattern = Pattern.compile("\\{(\\d+, \\d+)\\}");
        matcher = pattern.matcher(challenge11);

        while (matcher.find()){
            System.out.println("Occurrence: "+matcher.group(1));
        }

        System.out.println("_".repeat(50));

        pattern = Pattern.compile("\\{(\\d+)\\, (\\d+)\\}");
        matcher = pattern.matcher(challenge11);

        while (matcher.find()){
            System.out.println("Occurrence: "+matcher.group(1)+ " "+matcher.group(2));
        }

    }
}
