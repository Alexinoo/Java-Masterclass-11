package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #4
 *
 * - Replace all occurrences of blank with an underscore for the following string
 *
 * - Print out the resulting string
 *
 * Solution
 *  - Option-1
 *      - use String.replaceAll(" ","_");
 *
 *  - Option-2
 *      - use character class "\\s" which will match all white spaces
 */
public class _04_Challenge_4 {

    public static void main(String[] args) {

        String challenge4 = "Replace all blanks with underscores.";

        System.out.println(challenge4.replaceAll(" ", "_")); //Replace_all_blanks_with_underscores.
        System.out.println(challenge4.replaceAll("\\s", "_")); //Replace_all_blanks_with_underscores.

    }
}
