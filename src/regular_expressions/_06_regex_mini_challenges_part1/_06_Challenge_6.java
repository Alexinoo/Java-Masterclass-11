package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #6
 *
 * - Write a regular expression that will only match the following string in it's entirety
 *
 * Solution
 * - Option-1
 *      - The long way
 *          regExp = "[a]{3}[b]{1}[c]{8}[d]{3}[e]{1}[f]{3}[g]{1}$";
 *
 * - Option-2
 *      - A shorthand
 *          regExp = "^a{3}bc{8}d{3}ef{3}g$";
 *
 *      - we're using quantifiers to spell out what we want to see
 *          - 3 a's followed by a single b
 *          - 8 c's followed
 *          - 3 d's followed
 *          - followed by a single e
 *          - 3 f's followed by a g
 *      - we've also used the caret ^ at the beginning of string and $ at the end - boundary matches - to match the entire string
 *        and not just part of the string
 *
 */
public class _06_Challenge_6 {

    public static void main(String[] args) {

        String challenge5 = "aaabccccccccdddefffg";

        String regExp = "[a]{3}[b]{1}[c]{8}[d]{3}[e]{1}[f]{3}[g]{1}$";
        System.out.println(challenge5.matches(regExp));

        regExp = "^a{3}bc{8}d{3}ef{3}g$";
        System.out.println(challenge5.matches(regExp));

        // verify regExp works with replaceAll()
        System.out.println(challenge5.replaceAll(regExp , "REPLACED"));

        // IF we modify the original string by placing a z at the beginning, the string is never replaced
        // as it doesn't match the regExp
        challenge5 = "zaaabccccccccdddefffg";
        System.out.println(challenge5.replaceAll(regExp , "REPLACED"));

    }
}
