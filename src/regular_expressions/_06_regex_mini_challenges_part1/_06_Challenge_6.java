package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #6
 * ............
 *
 * - Write a regular expression that will ONLY MATCH THE FOLLOWING STRING IN IT'S ENTIRETY
 *
        String challenge5 = "aaabccccccccdddefffg";
 *
 *
 *
 * //////////////
 * Solution : 1
 * /////////////
 *
 *      String regExp = "^a{3}[b]{1}[c]{8}[d]{3}[e]{1}[f]{3}[g]{1}$";
 *      System.out.println(challenge5.matches(regExp));
 *
 *      - prints true - doing it the long way *
 *
 * //////////////
 * Solution : 2
 * /////////////
 *
 *      regExp = "^a{3}bc{8}d{3}ef{3}g$";
 *
 *      - A shorthand
 *      - we're using quantifiers to spell out exactly what we want to see
 *          - 3 a's followed by a single b
 *          - followed by 8 c's
 *          - followed by 3 d's
 *          - followed by a single e
 *          - followed by 3 f's and a g
 *
 *      - we've also used the caret ^ at the beginning of string and $ at the end - boundary matches - to match the entire string
 *        and not just part of the string
 *
 * /////////////
 * Verify Answer
 * //////////////
 *
 * - We can also verify the answer here by using replace all
 *
 *      System.out.println(challenge5.replaceAll(regExp , "REPLACED"));
 *
 *      - prints the word "REPLACED" which means it's working
 *      - can be a good way to verify your answers using replaceAll
 *
 * - And just to confirm that it does work, let us modify the original string by placing a z at the beginning,
 *
 *      challenge5 = "zaaabccccccccdddefffg";
        System.out.println(challenge5.replaceAll(regExp , "REPLACED"));
 *
 *      - prints "zaaabccccccccdddefffg" and the original string is never replaced
 *      - was left untouched because the complete regular expression wasn't matched because we have modified it
 *        without updating the regular expression
 */
public class _06_Challenge_6 {

    public static void main(String[] args) {

        String challenge5 = "aaabccccccccdddefffg";

        String regExp = "^a{3}[b]{1}[c]{8}[d]{3}[e]{1}[f]{3}[g]{1}$";
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
