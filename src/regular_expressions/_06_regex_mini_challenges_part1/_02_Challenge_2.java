package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #2
 * ............
 *
 * - Write a regular expression that will match "I want a bike." and "I want a ball."
 * - Verify your answer using the matches()
 *
 *
 * - Match 2 strings beginning with "I want a"
 *
 *      String challenge1 = "I want a bike.";
        String challenge2 = "I want a ball.";
 *
 * //////////////
 *  Solution 1:
 * //////////////
 *
 *      String regExp = "I want a \\w+.";
 *
 *      System.out.println(challenge1.matches(regExp));
        System.out.println(challenge2.matches(regExp));
 *
 *      - prints true in both cases
 *
 *      - We are using a character class \\w which matches characters A-Z in upper case , a-z in lower case , 0-9 and the underscore
 *      - It will actually match 1 character, and we need to use that with a quantifier
 *      - we use + quantifier which indicates that we want to match 1 or more characters before the full stop/period
 *      - Notice also that we have to escape the character class in the string - hence the 2 backslashes
 *
 *
 *
 * ///////////////////////////
 *  Solution 2: Using a group
 * ///////////////////////////
 *
 * - We can do something like
 *
 *      regExp = "I want a (bike|ball)."
 *
 *      System.out.println(challenge1.matches(regExp));
        System.out.println(challenge2.matches(regExp));
 *
 *      - prints true in both case
 *
 * - We've used a group that is going to match a bike or a ball with the use of a logical OR operator,
 *   with a pipe character
 *
 */
public class _02_Challenge_2 {
    public static void main(String[] args) {
        String challenge1 = "I want a bike.";
        String challenge2 = "I want a ball.";

        String regExp = "I want a \\w+.";

        System.out.println(challenge1.matches(regExp));
        System.out.println(challenge2.matches(regExp));

        regExp = "I want a (bike|ball).";

        System.out.println(challenge1.matches(regExp));
        System.out.println(challenge2.matches(regExp));
    }
}
