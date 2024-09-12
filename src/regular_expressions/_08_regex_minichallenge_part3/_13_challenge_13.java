package regular_expressions._08_regex_minichallenge_part3;
/*
 *
 * Challenge #13
 *
 * - US zip codes can be followed by a dash and another four numbers
 * - Write a regular expressions that will match those zip-codes
 *
 * - Use "11111-1111" as your string
 *
 * Solution:
 *  - Use "^\\d{5}-\\d{4}$"
 *
 */
public class _13_challenge_13 {

    public static void main(String[] args) {

        String challenge13 = "11111-1111";

        System.out.println(challenge13.matches("^\\d{5}-\\d{4}$"));
    }
}
