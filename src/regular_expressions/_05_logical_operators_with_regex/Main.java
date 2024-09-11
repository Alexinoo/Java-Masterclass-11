package regular_expressions._05_logical_operators_with_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Logical Operators with Regex
 *
 * - Include and , or , not
 *
 * - when we write "abc" , means "a" and "b" and "c"
 */
public class Main {

    public static void main(String[] args) {

        // OR operator in action
        // [Hh]arry would match the case on Harry , whether it's uppercase H , or a lowercase h
        System.out.println( "Harry".replaceAll("[Hh]arry" , "Harry"));
        System.out.println( "harry".replaceAll("[Hh]arry" , "Harry"));

        // OR operator in action - Consider H or h a match
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));

        /* NOT OPERATOR
         * One version of NOT is using ^ inside the []
         * [^abc]
         * - means not when it's the first character within square braces
         * - this regex matches all characters except a, b and c
         *
         * Another version of NOT OPERATOR is the exclamation mark character
         * - Has to be used in a negative look ahead expression
         *
         * Example
         * - Find t's that aren't followed by v
         *
         * - Shows on 2 occurrences but we have 3
         * - The last is not followed by other character and therefore no other character to match with
         * - [^v] means we have to match t with another character, but not v
         * - the last t is not matched to anything - consumes a character, requires a character to be there
         *
         *
         *
         * - Instead of using [^v] , we can use the NOT operator which is the ! in a negative look ahead expression
         * - A look ahead doesn't consume characters, it can actually match nothing
         * - So let's change ou regex "t[^v]" to "t(?!v)"
         *      - We want t that isn't followed by a v
         *      - The ? inside the parentheses is part of the look ahead syntax
         *          - And look ahead always start with (?
         *      - The ! doesn't consume a character, so it will match instances of T at the end of the string
         *
         *
         * Positive look ahead
         * - Suppose we wanted only matches of t's that are followed by v
         * - In this case we use positive look ahead : "t(?=v)"
         */

        String tvTest = "tstvtkt";
        //String tNotVRegExp = "t[^v]";
        String tNotVRegExp = "t(?!v)";

        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        int count = 0;
        while (tNotVMatcher.find()){
            count++;
            System.out.println("Occurrence: "+ count+ " : "+tNotVMatcher.start() + " to " +tNotVMatcher.end());
        }

        /*
         * Look at a couple of examples
         * Regex are usually used to validate user input
         *
         * Validate US phone number : (800) 123-4567
         * Regex: ^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$
         *
         * -- Explanation --
         * - Start by indicating that the line has to start with the entire pattern
         *      - If anything occurs before this pattern in the String, then it's not a phone no
         * - We expect area code to be within parentheses
         *      - The first character we expect is the open parentheses
         *      - We have to escape it, as we have seen, the parentheses is a character class used to indicate a group
         *      - But in this case we want to match the parentheses character literal
         *      - And when we want to match a character literal, that can also be used as a character class in a regex, we have to escape it this way
         *
         *
         *
         *
         *
         *
         * - More explanation to follow
         *
         * - Test with some strings
         */

        String phone1 = "1234567890"; // shouldn't match
        String phone2 = "(123) 456-7890"; // should match
        String phone3 = "123 456-7890"; // shouldn't match
        String phone4 = "(123)456-7890"; // shouldn't match

        System.out.println("phone1 = "+phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // false
        System.out.println("phone2 = "+phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // true
        System.out.println("phone3 = "+phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // false
        System.out.println("phone4 = "+phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // false


        /*
         * There are also many ways to write a regex that matches a phone number
         * We might not expect parentheses around the area code in a real world app
         *
         *
         * Validate visa card numbers
         * - Start with 4
         *      - Newer cards 16 numbers
         *      - Older cards 16 numbers
         * - The regex to validate these numbers would be like so
         * Regex : ^4[0-9]{12}([0-9]{3})?$
         */

        String visa1 ="4444444444444";
        String visa2 ="544444444444";



    }
}
