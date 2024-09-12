package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #1
 *
 * - Write the String literal regular expression that will match the following String
 *
 * - Use the String.matches() to verify your answer
 */
public class _01_Challenge_1 {

    public static void main(String[] args) {
        String challenge1 = "I want a bike.";
        System.out.println(challenge1.matches("I want a bike."));
    }
}
