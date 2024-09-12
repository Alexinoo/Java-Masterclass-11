package regular_expressions._06_regex_mini_challenges_part1;
/*
 * Challenge #5
 *
 * - Write a regular expression that will match the following string in it's entirety
 *
 * - Use String.matches() to verify your answer
 *
 *
 * Solution:
 *  - Option-1:
 *      - Use "[abcdefg]+"
 *          - we're matching strings that contain one or more of the letters a through g
 *
 *  - Option-2
 *      - Use range also "[a-g]+"
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
