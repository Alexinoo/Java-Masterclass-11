package regular_expressions._06_regex_mini_challenges_part1;

/*
 * Challenge #1
 * ............
 *
 * - It's now time to practice what we have learnt about writing regular expressions
 *
 * - There's often more than 1 way to write a regular expression that will match what we want to match
 *
 * - The answers provided by the instructor may not only be the only answer
 *
 * - We should verify our answers using String.matches() , String.replaceAll() and Matcher.matches() which
 *   we've covered in this section
 *
 * - Essentially, what Tim is saying is that if my answer doesn't match the one he provides, that doesn't mean
 *   I'm wrong, and that's because Tim can't really provide every possible solution
 *
 * - Because in programming there are multiple solutions with programming especially with regular expressions, there
 *   can be many different answers
 *
 * - Let's proceed and start looking at these challenges...
 *
 *
 * //// Challenge#1
 *
 * - Write the String literal regular expression that will match the following String
 *
 *      String challenge1 = "I want a bike.";
 *
 * - Use the String.matches() to verify your answer
 *
 *
 * ///// Solution:
 * - So, obviously, we're using the same String and that would match
 *
        System.out.println(challenge1.matches("I want a bike."));
 *
 *      - and of course, we get a true indicating that there was a match
 */
public class _01_Challenge_1 {

    public static void main(String[] args) {
        String challenge1 = "I want a bike.";
        System.out.println(challenge1.matches("I want a bike."));
    }
}
