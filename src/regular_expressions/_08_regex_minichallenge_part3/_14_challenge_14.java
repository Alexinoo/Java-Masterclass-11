package regular_expressions._08_regex_minichallenge_part3;
/*
 *
 * Challenge #14
 *
 * Write a regular expression that will match 5-digit US zip codes and zip codes that contain the optional 4-digits preceded by a dash
 *
 * Solution:
 *
 * - Use "^\\d{5}(-\\d{4})?$"
 *   - We've modified the expression by enclosing the OPTIONAL part, the dash and 4 numbers within a group and following the group with
 *      the ? mark quantifier
 *   - Means that there can be 0 or 1 occurrences of the group
 *   - We wouldn't want to use the + plus or * quantifiers,  because we'd then match strings like 1111-1111-1111
 *
 * //////
 *
 * - Whenever you want to write a regular expression that matches a piece of data like a zip code, telephone number or email address etc
 *   - We can usually find regular expressions that solves these problems on the web
 *   - Need to be careful though because there's a lot of variations and in some cases, much debate about which one is correct
 *   - Also the regex can be wrong or incomplete
 */
public class _14_challenge_14 {

    public static void main(String[] args) {

        String challenge14   = "11111-1111";
        System.out.println(challenge14.matches("^\\d{5}(-\\d{4})?$"));

        challenge14 = "11111";
        System.out.println(challenge14.matches("^\\d{5}(-\\d{4})?$"));

    }
}
