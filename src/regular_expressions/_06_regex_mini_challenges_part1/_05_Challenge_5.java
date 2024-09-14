package regular_expressions._06_regex_mini_challenges_part1;
/*
 * Challenge #5
 * ............
 *
 * - Write a regular expression that will match the following string in it's entirety
 *
        String challenge5 = "aaabccccccccdddefffg";
 *
 * - Use String.matches() to verify your answer
 *
 * ///////////////
 *  Solution : 1
 * ///////////////
 *
 *      String regExp = "[abcdefg]+";
        System.out.println(challenge5.matches(regExp));
 *
 *      - prints true
 *      - we're matching strings that contain one or more of the letters a through g inclusive
 *
 * ///////////////
 *  Solution : 2
 * ///////////////
 *
 *      regExp = "[a-g]+";
        System.out.println(challenge5.matches(regExp));
 *
 *      - prints true
 *      - use a through g inclusive via range , implicitly, instead of hardcoding
 *
 * //// NOTE
 * - Using the * quantifier would also work
 *
 *
 */
public class _05_Challenge_5 {

    public static void main(String[] args) {

        String challenge5 = "aaabccccccccdddefffg";

        String regExp = "[abcdefg]+";
        System.out.println(challenge5.matches(regExp));

        regExp = "[a-g]+";
        System.out.println(challenge5.matches(regExp));

    }
}
