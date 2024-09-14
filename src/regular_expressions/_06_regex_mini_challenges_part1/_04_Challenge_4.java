package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #4
 * ............
 *
 * - Replace all occurrences of blank with an underscore for the following string *
 * - Print out the resulting string
 *
        String challenge4 = "Replace all blanks with underscores.";
 *
 *
 * ///////////////
 * Solution : 1
 * ///////////////
 *
 *      String regExp = " ";
 *      System.out.println(challenge4.replaceAll(regExp, "_"));
 *
 *      - prints "Replace_all_blanks_with_underscores."
 *      - used a space as a regular expression
 *
 * ///////////////
 * Solution : 2
 * ///////////////
 *
 *      String regExp = "\\s";
 *      System.out.println(challenge4.replaceAll(regExp, "_"));
 *
 *      - prints "Replace_all_blanks_with_underscores."
 *      - use character class "\\s" which will match all whitespace
 *
 * - This is a broader solution that would work well if we wanted to replace all whitespace with underscores and
 *   not just blanks
 */
public class _04_Challenge_4 {

    public static void main(String[] args) {

        String challenge4 = "Replace all blanks with underscores.";

        String regExp = " ";
        System.out.println(challenge4.replaceAll(regExp, "_")); //Replace_all_blanks_with_underscores.

        regExp = "\\s";
        System.out.println(challenge4.replaceAll(regExp, "_")); //Replace_all_blanks_with_underscores.

    }
}
