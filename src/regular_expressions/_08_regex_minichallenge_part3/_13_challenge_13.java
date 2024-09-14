package regular_expressions._08_regex_minichallenge_part3;
/*
 *
 * Challenge #13
 * .............
 *
 * - US zip codes can be followed by a dash and another four numbers
 * - Write a regular expressions that will match those zip-codes
 *
 * - Use the String below for your tests
        String challenge13 = "11111-1111";
 *
 *
 *
 * //////////
 * Solution:
 * ///////////
 *
 *      String regEx = "^\\d{5}-\\d{4}$";
        System.out.println(challenge13.matches("^\\d{5}-\\d{4}$"));
 *
 *      - prints true
 *      - we're specifying the first set of digits need to be 5 digits and adding a dash (-)
 *          before the next set of 4 digits
 *      - same as beginning, all we have to do is change the number in the quantifier
 *
 *
 * ///////////
 * - If we're tyring to verify user input, we could check the length of the input and then decide which regular
 *    expression to use
 * - But it would be ideal to have 1 regular expression that would match both cases
 *
 */
public class _13_challenge_13 {

    public static void main(String[] args) {

        String challenge13 = "11111-1111";

        System.out.println(challenge13.matches("^\\d{5}-\\d{4}$")); // true
    }
}
