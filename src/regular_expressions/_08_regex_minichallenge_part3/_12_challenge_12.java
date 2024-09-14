package regular_expressions._08_regex_minichallenge_part3;

/*
 *
 * Challenge #12
 * .............
 *
 * - Write a regular expression that will match a 5-digit US zip code
 *
 * - Use "11111" as your test string
 *
 *
 * //////////////
 * Solution : Tim - broader solution
 * //////////////
 *
 *      String regExp = "^\\d{5}$";
 *      System.out.println(challenge12.matches("^1{5}$")); // true
 *
 *      - prints true
 *      - we've used ^ and $ boundary matches since we want the entire string to match
 *      - we've used \\d character class to match any digit
 *      - we've used {5} quantifier to specify we want 5-digits
 *
 * //////////////////
 *  Another Solution - strictly 1's
 * //////////////////
 *
 *      regExp = "1{5}$";
 */
public class _12_challenge_12 {

    public static void main(String[] args) {

        String challenge12 = "11111";

        String regExp = "^1{5}$";

        System.out.println(challenge12.matches(regExp)); // true

        challenge12 = "11121";
        regExp = "^\\d{5}$";
        System.out.println(challenge12.matches(regExp)); // true
    }
}
