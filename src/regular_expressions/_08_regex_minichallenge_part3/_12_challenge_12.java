package regular_expressions._08_regex_minichallenge_part3;

/*
 *
 * Challenge #12
 *
 * - Write a regular expression that will match a 5-digit US zip code
 *
 * - Use "11111" as your test string
 *
 * - Solution
 *
 *      - Tim's solution : "^\\d{5}$"
 *          - we've used ^ and $ boundary matches since we want the entire string to match
 *          - we've used \\d character class to match any digit
 *          - we've used {5} quantifier to specify we want 5-digits
 */
public class _12_challenge_12 {

    public static void main(String[] args) {

        String challenge12 = "11111";

        System.out.println(challenge12.matches("^1{5}$")); // true

        challenge12 = "11121";
        System.out.println(challenge12.matches("^\\d{5}$")); // true
    }
}
