package regular_expressions._08_regex_minichallenge_part3;
/*
 *
 * Challenge #14
 * .............
 *
 * - Write a regular expression that will match 5-digit US zip codes and zip codes that contain the OPTIONAL 4-digits
 *  preceded by a dash
 *
 * - Use the String below as an example
 *
 *      String challenge14   = "11111-1111";
 *
 * ///////////
 * Solution:
 * ///////////
 *
 *
 *      String regEx = "^\\d{5}(-\\d{4})?$";
 *      System.out.println(challenge14.matches("^\\d{5}(-\\d{4})?$"));
 *
 *      - prints true
 *      - We've modified the expression by enclosing the OPTIONAL part, the dash and 4 numbers within a group and
 *         following the group with the ? mark quantifier
 *      - This therefore means that there can only be 0 or 1 occurrences of the group
 *      - We wouldn't want to use the + plus or * quantifiers,  because we'd then match strings like 1111-1111-1111
 *
 * - Update original String to
 *
 *      challenge14   = "11111";
 *      System.out.println(challenge14.matches("^\\d{5}(-\\d{4})?$"));
 *
 *      - prints true, since the 2nd part is OPTIONAL
 *
 * //////
 *
 * - Whenever you want to write a regular expression that matches a piece of data like a zip code, telephone number or email address etc
 *   - We can usually find regular expressions that solves these problems on the web
 *   - Need to be careful though because there's a lot of variations and in some cases, much debate about which one is correct
 *   - Also the regex can be wrong or incomplete
 *
 * //////
 * - Regular Expression for Canadian post code
 *
 *      regExp = "^[A-Za-z]\d[A-Za-z][ -]?\d[A-Za-z]\d$";
 *
 * - This regular expression expects
 *      - a letter
 *      - followed by a digit
 *      - followed by letter
 *      - then they will optionally be a blank or a dash
 *      - then a number
 *      - followed by a letter
 *      - and finally a number
 *
 * - this matches post codes such as: "M1A 1M1
 * - But here the regular expression is wrong, because Canadian Post codes can't contain certain letters
 * - And they're generally not written using a dash, although that's not strictly wrong
 *
 * /////
 * - The point here is that not everything you see on internet is true especially with regular expressions
 * - Regular expressions can be challenging and write into cypher , need to practice , practice, practice
 */
public class _14_challenge_14 {

    public static void main(String[] args) {

        String challenge14   = "11111-1111";
        System.out.println(challenge14.matches("^\\d{5}(-\\d{4})?$")); // true

        challenge14 = "11111";
        System.out.println(challenge14.matches("^\\d{5}(-\\d{4})?$")); // true

    }
}
