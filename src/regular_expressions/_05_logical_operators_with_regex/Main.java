package regular_expressions._05_logical_operators_with_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Logical Operators with Regex
 * ............................
 *
 * - When writing regular expressions there are a few logical operators we can use
 *
 * - They include AND , OR ,and NOT
 *
 *
 * //////////////
 *  AND operator
 * //////////////
 *
 * - And it turns out that we've been using AND operator and that's because it's implicit
 *
 * - Like for example, when we write regular expressions like:
 *
 *      "abc"
 *      - it technically means "a" and "b" and "c"
 *
 *
 * //////////////
 *  OR (|) operator
 * //////////////
 *
 * - We looked at an example of a match, where we gave [Hh]arry , and that would match the case on Harry,
 *    whether it's uppercase H , or a lowercase h
 *
 *      System.out.println( "Harry".replaceAll("[Hh]arry" , "harry"));
        System.out.println( "harry".replaceAll("[Hh]arry" , "Harry"));
 *
 *      - prints "harry"
 *      - prints "Harry"
 *
 * - Basically the regular expression will match both cases
 * - We can also use the OR (|) operator to achieve the same thing
 *
 *      System.out.println("harry".replaceAll("[H|h]arry", "larry"));
 *      System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));
 *
 *      - prints "larry"
 *      - prints "Larry"
 * - What we're saying here is that, if the character is an uppercase H , or a lowercase h, that will be considered
 *    a match and it matched the regular expressions for both
 *
 *
 * ////////////////////////
 *  NOT - [^abc] OPERATOR
 * ///////////////////////
 *
 * - We have seen how to use NOT when working with a set of characters
 * - One version of NOT is using ^ inside the []
 * - For example:
 *      [^abc]
 *   - the caret character here means not when it's the first character within square braces
 *   - this regular expression matches all characters except a, b and c
 *
 * /////////////////////////
 * NOT ! - Exclamation mark
 * /////////////////////////
 *
 * - Another version of NOT OPERATOR is the exclamation mark character
 *
 * - But this character has to be used in what's called a negative look ahead expression
 *
 * - Let's take a look at an example of that:
 *
 * - Find t's that aren't followed by v in the following String
 *
 *      String tvTest = "tstvtkt";
 *
 * - our first attempt at a regular expression might be something like writing "t[^v]"
 *
 *      String tNotVRegExp = "t[^v]";

        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        int count = 0;
        while (tNotVMatcher.find()){
            count++;
            System.out.println("Occurrence: "+ count+ " : "+tNotVMatcher.start() + " to " +tNotVMatcher.end());
        }
 *
 * - There should be 3 occurrences : "ts" , "tk" and final "t" that isn't followed by anything
 * - However, if we run this, we get only 2 occurrences but we have 3
 * - The first 2 occurrences,  "ts" and "tk" were found but the last is not followed by other character and
 *   therefore no other character to match with
 * - And there's a reason for that, when we use caret ^ in a [^] regular expression, we actually match a character
 *   or we MUST match a character in order for the regular expression to match
 * - So, we're saying that "t" must be followed by any character other than "v"
 * - But in this case there's no character following the final "t" and so our ^v in [] doesn't actually match
 *   anything
 *
 * - Another way of saying this is that the [^] consumes a character, it requires a character to be there
 *
 *
 * /////////
 *
 * - Instead of using [^v] , we can use the NOT operator which is the ! in a negative look ahead expression
 * - A look ahead doesn't consume characters, it can actually match nothing
 * - So let's change our regex to
 *
 *      String tNotVRegExp = "t(?!v)";
 *
 *      - This means we want "t" that isn't followed by a "v"
 *      - The ? inside the parentheses is part of the look ahead syntax and look ahead always start with (?
 *      - The ! doesn't consume a character, so it won't match instances of T at the end of the string
 * - If we run this now , we should get 3 lines of output , from our occurrences and we should get the final
 *   matching "t" as well as a result
 * - And surely we do
 *
 *
 * //////////////////////////
 * (?! - Negative Look Ahead
 * //////////////////////////
 *
 * - Note that when we use a look ahead, the characters in the Look Ahead aren't part of the match
 * - And also note that the indices indicate that our matches are 1 character in length, and if you recall
 *   the end(), returns the index after the match
 * - They're no longer matching the t followed by another character
 * - Once again, look ahead, don't consume the characters they match, or in other words, they don't include the
 *    characters they match in the matched text
 * - So that's negative look ahead expressions
 *
 * //////////////////////////
 * (?= - Positive Look Ahead
 * //////////////////////////
 *
 * - But we can also use positive look ahead using the equal sign rather than using the ! operator which was
 *   the exclamation mark
 *
 * - If we wanted to find all matches of "t" followed by "v" , but we didn't want to include v in the match, we'd
 *   use a regular expression as
 *
 *      String tNotVRegExp = "t(?=v)";
 *
 *      - prints: "Occurrence: 1 : 2 to 3" in the String "tstvtkt"
 *
 *
 * //////////////////////////
 *  Validate US Phone number
 * //////////////////////////
 *
 *
 * Regular expressions are often used to validate user input
 *
 * Below is a regular expression that validates a sample US phone number :
 *
 *      String phone = "(800) 123-4567";
 *
 *      String regExp = "^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$";
 *
 *
 * ///// Explanation /////
 *
 * - Start by indicating that the line has to start with the entire pattern
 *       ^()
 *
 * - If anything occurs before this pattern in the String, then it's not a phone no
 *
 * - We expect area code to be within parentheses
 * - The first character we expect is the open parentheses :
 *
 *      [\\(]{1}
 *
 *      - We have to escape it because the parentheses is a character class used to indicate a group
 *      - But in this case we want to match the parentheses character literal
 *      - And when we want to match a character literal, that can also be used as a character class in a regex,
 *          we have to escape it in this way
 *      - Following the parentheses is the quantifier which indicates that there should only be 1 parenthesis
 *
 * - We expect 3 numbers, between 0 and 9 inclusive
 *
 *      [0-9]{3}[\\)]{1}
 *      - we're using a quantifier to specify how many numbers we expect for a match
 *      - following that we expect 1 closing parentheses, escaped and quantified
 *
 * - Then we want to see a blank space and only 1 blank *
 *      [ ]{1}
 *
 * - Then we want to see 3 numbers 0-9 inclusive
 *      [0-9]{3}
 *
 * - followed by a dash
 *      [\\-]{1}
 *
 * - followed by 4 numbers 0-9 inclusive
 *      [0-9]{4})$
 *
 * - finally, we expect the String to end with the entire pattern
 *      $
 * - which means that if anything follows the phone no, the reg expression won't match
 *
 *
 * /////////////
 *
 * - Work with some examples
 *
 *      String phone1 = "1234567890"; // shouldn't match
        String phone2 = "(123) 456-7890"; // should match
        String phone3 = "123 456-7890"; // shouldn't match
        String phone4 = "(123)456-7890"; // shouldn't match
 *
 *
 * - and validate with the regExp above
 *
 *      System.out.println("phone1 = "+phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // false
        System.out.println("phone2 = "+phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // true
        System.out.println("phone3 = "+phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // false
        System.out.println("phone4 = "+phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$")); // false
 *
 * - There are also many ways to write a regex that matches a phone number
 * - For example, we might not expect parentheses around the area code, in a real world app, you would probably
 *   remove punctuation and white space from the phone number before processing it
 * - And we might require the country code but these are details that would depend on your specific case
 *
 *
 * /////////////////////
 *  Validate Visa Card
 * /////////////////////
 *
 * - Visa credit card numbers starts with a 4
 * - New accounts have 16 numbers whereas older cards have 13
 * - The regular expression to validate a Visa number, would be as follows
 *
 *      String regExp = "^4[0-9]{12}([0-9]{3})?$"
 *
 *      - start with ^4 because all the Visa card numbers start with a 4
 *      - following the 4, we expect at least 12 numbers : [0-9]{12}
 *      - next OPTIONAL 3 characters in the newer cards : ([0-9]{3})? , which we've put in a group followed by
 *         a ? at the end, which will match 0 or more occurrences of the group as we'd want
 *      - There will be 0 occurrences of the final 3 numbers for older card numbers, and w occurrences for
 *         new account numbers and that's why we're using a group
 *      - Finally, the $ boundary matcher which means that if anything occurs after this pattern , then there's no
 *         match
 *
 * - Let's try this with some few Random numbers
 *
 *      String visa1 = "4444444444444";
        String visa2 = "5444444444444";
        String visa3 = "4444444444444444";
        String visa4 = "44444444444444444";
        String visa5 = "4444";

 * - Validate with the regExp above
 *
        System.out.println("visa1 = "+visa1.matches("^4[0-9]{12}([0-9]{3})?$")); // should match
        System.out.println("visa2 = "+visa2.matches("^4[0-9]{12}([0-9]{3})?$")); // shouldn't match
        System.out.println("visa3 = "+visa3.matches("^4[0-9]{12}([0-9]{3})?$")); // should match
        System.out.println("visa4 = "+visa4.matches("^4[0-9]{12}([0-9]{3})?$")); // shouldn't match
        System.out.println("visa5 = "+visa5.matches("^4[0-9]{12}([0-9]{3})?$")); // shouldn't match
 *
 *
 * ///////
 * - Please note that this isn't a comprehensive check for a Visa card number, some 13 and 16 digit Strings
 *   would match this regular expression
 */
public class Main {

    public static void main(String[] args) {

        // OR operator in action
        // [Hh]arry would match the case on Harry , whether it's uppercase H , or a lowercase h
        System.out.println( "Harry".replaceAll("[Hh]arry" , "harry"));
        System.out.println( "harry".replaceAll("[Hh]arry" , "Harry"));

        // OR operator in action - Consider H or h a match
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));


        String tvTest = "tstvtkt";
        //String tNotVRegExp = "t[^v]";
//        String tNotVRegExp = "t(?!v)";
        String tNotVRegExp = "t(?=v)";

        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        int count = 0;
        while (tNotVMatcher.find()){
            count++;
            System.out.println("Occurrence: "+ count+ " : "+tNotVMatcher.start() + " to " +tNotVMatcher.end());
        }

        /*
         * - Test with some sample strings against the regExp
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
        * Test and validate with some sample strings
        */

        String visa1 = "4444444444444";
        String visa2 = "5444444444444";
        String visa3 = "4444444444444444";
        String visa4 = "44444444444444444";
        String visa5 = "4444";

        System.out.println("visa1 = "+visa1.matches("^4[0-9]{12}([0-9]{3})?$")); // should match
        System.out.println("visa2 = "+visa2.matches("^4[0-9]{12}([0-9]{3})?$")); // shouldn't match
        System.out.println("visa3 = "+visa3.matches("^4[0-9]{12}([0-9]{3})?$")); // should match
        System.out.println("visa4 = "+visa4.matches("^4[0-9]{12}([0-9]{3})?$")); // shouldn't match
        System.out.println("visa5 = "+visa5.matches("^4[0-9]{12}([0-9]{3})?$")); // shouldn't match



    }
}
