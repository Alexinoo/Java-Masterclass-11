package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #7
 * ............
 *
 * - Write a regular expression that will match a string that starts with a series of letters
 * - The letters must be followed by a period
 * - After the period, there must be a series of digits
 *
 * - The String "kjisl.22" is an example of a String that would match
 *
 * - The String "f5.12a" is an example of a String that would not match
 *
 * - Use this string below to test your regular expression
 *
 *      String challenge7 = "abcd.135";
 *
 *
 * ///////////
 * Solution:
 * ///////////
 *
 *      String regExp = "^[A-Z][a-z]+\\.\\d+$"
 *
 *      System.out.println(challenge7.matches(regExp));
 *
 *      - prints true
 *      - we start by using beginning of the string boundary matcher , the caret ^ to ensure nothing occurs before the pattern
 *
 *      - we then look for 1 or more letters using a range of characters, and the plus quantifier
 *          - You could have used the case-insensitive modifier and only specified 1 range
 *
 *          e.g.
 *              regExp = "^(?i)[a-z]+\\.\\d+$";
 *              or
 *              regExp = "^(?i)[A-Z]+\\.\\d+$";
 *
 *      - after the letters, we look for a period, since the period character is a character class that matches all characters
 *          we have to escape it , hence \\.
 *
 *      - and then after that we expect one or more digits , hence \\d followed by a plus (+) quantifier to match that part
 *        of the string
 *         Note : The * quantifier would be wrong here because that would match strings that look like abc. , the strings we want to match
 *                 must have at least 1 digit after the period
 *      - include $ boundary matcher at the end of the string to ensure that nothing occurs after this pattern
 */
public class _07_Challenge_7 {

    public static void main(String[] args) {

        String challenge7 = "abcd.135";

        //String regExp = "[a-z]+.[0-9]+";
        String regExp = "^[A-z][a-z]+\\.\\d+$";
        //String regExp = "^(?i)[A-Z]+\\.\\d+$"; // case -insensitive modifier - work with 1 range

        System.out.println(challenge7.matches(regExp)); // true

        challenge7 = "kjisl.22";
        System.out.println(challenge7.matches(regExp)); // true

        challenge7 = "f5.12a";
        System.out.println(challenge7.matches(regExp)); // false


    }
}
