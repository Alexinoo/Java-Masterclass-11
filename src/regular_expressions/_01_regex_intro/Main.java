package regular_expressions._01_regex_intro;
/*
 * Regular Expressions Introduction
 *
 * - Regular expressions are a way to describe a string or a pattern
 * - We've probably used it without knowing
 * - Some methods in the String class accept a regular expression as a parameter
 *
 * - For example:
 *      - matches(String regex) : boolean
 *
 *      - split(String regex) : String[]
 *
 *      - replaceAll(String regex, String replacement) : String
 *
 * - All actually work with regular expressions
 * - Regular expressions are used to search strings for a specific pattern, or to validate that user input matches a specific
 *    pattern
 * - For example, we may want to verify that the email address provided by the user , by checking the input against a regular
 *   expression that describes how an email address string should look
 *
 *
 * ////////
 *
 * Example:
 *  - Create some string and print it to the console
 *
 *      String myString = "I am a String. Yes, I am.";
        System.out.println(myString);
 *
 *  - The simplest form of a regular expression is a string literal
 * - For example: the string "Hello" - in double quotes is a regular expression
 *
 * /////
 *
 * replaceAll(String regex , String replacement)
 * - Let's use replaceAll() , to replace all occurrences of the word "I" with the word "You" for below string
 *
 *      String myString = "I am a String. Yes, I am.";
 *      String yourString = myString.replaceAll("I","You");
        System.out.println(yourString);
 *
 *      - prints "You am a String. Yes, You am."
 *      - Our regular expression matched the source string in 2 places and each match or occurrence was replaced with the
 *          String "You"
 *
 * - The replaceAll() accepts a regular expression that describes the pattern we want to replace as the first parameter
 * - The second parameter is the actual replacement String
 *
 * - Now before we could do was match string literals , and in this way regular expressions wouldn't really be interesting
 * - But we can actually do more by using what is called character classes, and boundary matches
 *
 *
 *
 *
 * ////////////// Research from GPT ///////////////////////
 *
 * /// Character classes /////////
 * ///////////////////////////////
 *
 * - Character classes in Java are used to define a set of characters that you want to match in a search pattern
 * - They are enclosed in square brackets [] and allow you to specify ranges or individual characters.
 * - Here are some commonly used character classes in Java regex:
 *
 * ///// Basic Character Classes: ///////
 *
 *
 *  1. [abc] : Matches any single character a, b, or c
 *
 *  2. [^abc] : Matches any single character except a, b, or c
 *
 *  3. [a-z] : Matches any single lowercase letter from a to z
 *
 *  4. [A-Z] : Matches any single uppercase letter from A to Z
 *
 *  5. [0-9] : Matches any single digit from 0 to 9
 *
 *  6. [a-zA-Z] : Matches any single letter, regardless of case
 *
 *  7. [a-zA-Z0-9] : Matches any single alphanumeric character
 *
 *
 *
 * /////// Predefined Character Classes: //////
 *
 *  1. \d : Matches any digit, equivalent to [0-9]
 *
 *  2. \D : Matches any non-digit character, equivalent to [^0-9]
 *
 *  3. \w : Matches any word character (alphanumeric plus underscore), equivalent to [a-zA-Z0-9_]
 *
 *  4. \W : Matches any non-word character, equivalent to [^a-zA-Z0-9_]
 *
 *  5. \s : Matches any whitespace character (spaces, tabs, line breaks).
 *
 *  6. \S : Matches any non-whitespace character.
 *
 *
 * /////// Custom Character Classes: //////////
 *
 *  1. [aeiou] : Matches any lowercase vowel
 *
 *  2. [A-Fa-f0-9] : Matches any hexadecimal digit.
 *
 *  3. [.,!?] : Matches any one of the punctuation marks . , ! ?
 *
 *
 * /////// Character Class Ranges: ///////////
 *
 *  1. [a-zA-Z] : Matches any letter from a to z or A to Z
 *
 *  2. [0-9A-F] : Matches any digit from 0 to 9 or any letter from A to F
 *
 *
 * /////// Negation in Character Classes: ////////
 *
 *  1. [^aeiou] : Matches any character that is not a lowercase vowel
 *
 *  2. [^a-zA-Z0-9] : Matches any character that is not a letter or digit
 *
 *
 * ///// Escape Sequences in Character Classes: //////////
 *
 *  1. Use \\ to escape special characters within a character class, such as \\[, \\], \\-,
 *
 * /// Examples ////
 *
 *  1. To match a phone number pattern: \d{3}-\d{3}-\d{4} (matches patterns like 123-456-7890)
 *
 *  2. To match an email address: [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}.
 *
 * ///////////////////
 *
 * - Character classes are a powerful tool in regex for defining sets of characters in your patterns, allowing for flexible
 *   and precise matching.
 *
 * ////////////// End of Research from GPT ///////////////////////
 *
 * - A character class is like a wild card, and it represent a set or class of characters
 * - A boundary matcher looks for boundaries, such as the beginning and end of a string or a word
 *
 *
 * /////////////////////
 * "." - dot Character class
 * ///////////////////
 *
 * Let's take a look at an example
 *
 * - Declare a string
 *
 *       String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
 *
 * - Looking at character classes, the "." dot character class matches any character
 * - Let's try using it by using replaceAll()
 *
 *      System.out.println(alphanumeric.replaceAll(".","Y"));
 *
 *      - prints "YYYYYYYYYYYYYYYYYYYYYYY"
 *      - the entire string is replaced with Y's , basically a wild card for any character
 *
 *
 *
 * ////////////////////////////
 * "^" - caret Character class
 * ////////////////////////////
 *
 * - This is followed by pattern which could be a string literal , or something more complex
 * - When we use this caret boundary matcher, the regular expression must match the beginning of the string
 *
 * - Let's look at an example of this and suppose we want to replace the first part of the string "abcDee" with something else
 *
 *      "abcDeeeF12Ghhiiiijkl99z".replaceAll("^abcDeee","YYY");
 *
 *      - prints "YYYF12Ghhiiiijkl99z"
 *      - note that the length of a regex doesn't have to match the length of the replacement
 *      - we got a match and we got "abcDeee" was found at the start of the string and were replaced with 3 Y's
 *
 * - So, the regular expression match those characters that occurred at the beginning of the string
 * - And since we've specified the "^" caret boundary matcher and that's why it's only looking for the start of the string and
 *   replaced it
 *
 *
 * //////
 * - Just to show that this replacement is going to do replacement only at the start of a string, let's add another occurrence of
 *   "abcDeee" later in the String
 * - And since it doesn't occur at the beginning of the String, it shouldn't match the regular expression
 * - So, the second occurrence shouldn't be replaced
 *
 *      "abcDeeeF12GhhabcDeeeiiiijkl99z".replaceAll("^abcDeee","YYY");
 *
 *      - prints YYYF12GhhabcDeeeiiiijkl99z
 *      - the second occurrence of "abcDeee" wasn't replaced, because again we specified the caret boundary matcher, which indicates
 *         to only match at the start of the String
 *
 *
 *
 * /////// Another nifty feature of IntelliJ ///
 * - When we place our cursor inside a regular expression, notice we get a light bulb popup at the LHS of the screen, near the gutter
 * - We can click on Check RegExp, an abbreviation of regular expression
 * - We get a pop up and we can type different strings into the filed and intelliJ will actually tell us whether the String matches the
 *   regular expression
 * - The default entry would normally pop up as a Sample text
 * - We can change that to a different string and will only show green or a tick if our string literal matches the regEx
 * - This is a handy way to test out a regular expression without actually having to run your application all the time
 * - However, it requires that the String as a whole must match the expression , it doesn't actually match parts of a String
 *
 *
 *
 * //////// matches(String regex) : boolean
 * - matches() accepts a regular expression as a parameter, and returns true if the string matches and false if otherwise
 * - Let's use the alphanumeric String and pass the regular expression "^hello"
 *
 *      "abcDeeeF12Ghhiiiijkl99z".matches("^hello");
 *
 *      - prints false since the alphanumeric string doesn't start with the word "hello"
 *
 * - Let's pass "^abcDeee" as a regular expression instead
 *
 *      "abcDeeeF12Ghhiiiijkl99z".matches("^abcDeee");
 *
 *      - prints false
 *
 * - But Why is that ?
 * - It turns out that when using String.matches() , the string as a whole has to match the regex to evaluate to true
 * - It's not good enough for any part of the string to match
 * - It's the same behavior mentioned with the intelliJ quick check - and Tim won't be surprised to assume that IntelliJ
 *    internally is calling the matches() under the hood
 * - So, when using matches() with the caret boundary matcher, the entire string has to match the regular expression
 *
 * - The following will return true
 *
 *       System.out.println("abcDeeeF12Ghhiiiijkl99z".matches("^abcDeeeF12Ghhiiiijkl99z"));
 *
 *       - prints true
 *
 * - Below, will also return true
 *
 *       System.out.println("abcDeeeF12Ghhiiiijkl99z".matches("abcDeeeF12Ghhiiiijkl99z"));
 *
 * - So either way, with or without caret, should work
 *
 * - The documentation for the matches() doesn't really make it clear that the whole string must match for true to be returned
 * - This actually makes sense though, needing the full string to match in the context of verifying user input, where we want the
 *   entire string or input to match a given pattern
 *
 *
 * //////////////////////////////////
 * "$" - dollar sign Character class
 * /////////////////////////////////
 *
 * - It's always preceded by a pattern and it will match strings that end with the pattern
 * - It's the opposite of the caret boundary matcher
 *
 * - Let's look at an example for this:
 *
 * - Take the last few characters of the "abcDeeeF12Ghhiiiijkl99z" and append a $ sign to that starting from "i"
 * - Replace that with the String "THE END"
 *
 *      System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("ijkl99z$","THE END"));
 *
 *      - prints "abcDeeeF12GhhiiiTHE END"
 *
 * - We've used replaceAll() instead of the matches() , because of the matches requirement that the entire String matches the
 *   expression rather than just part of it
 *
 * ///////////////////////////////
 * [] -
 * ///////////////////////////////
 *
 * - When we want to match a specific letter or set of letters , we can actually put those letters within square brackets: []
 * - let's find and replace all occurrences of letters aei with X in our alphanumeric string
 *
 *      System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("[aei]","X"));
 *
 *      - prints "XbcDXXXF12GhhXXXXjkl99z"
 *      - replaces all the occurrences of letters aei with letter X respectively
 *
 * - The length of the replacement string doesn't always have to be 1 , let's use a replacement String "I replace a letter here"
 *
 *       System.out.println(abcDeeeF12Ghhiiiijkl99z.replaceAll("[aei]","I replaced a letter here"));
 *
 *      - prints some long text that I will have to truncate here,
 *      - each letter 'aei' has been replaced by "I replaced a letter here"
 *
 * - This regular expression isn't looking for the pattern [aei], instead each individual character is examined
 * - If it's an a , an e , or an i , then it matches this expression and the replacement actually happens
 *
 * - Suppose we only want to replace the letters a, e or i if they are followed by an F or a j
 * - replacement is going to happen if one of the 3 letters a, e, or i is actually followed by an F or a j
 *
 *      System.out.println(abcDeeeF12Ghhiiiijkl99z.replaceAll("[aei][Fj]", "X"));
 *
 *      - prints "abcDeeX12GhhiiiXkl99z"
 *
 *      - we have 2 matches and we can see that they got replaced with an X
 *          - X replaced eF
 *          - X replaced ij
 *      - basically both characters were replaced with an X
 */
public class Main {

    public static void main(String[] args) {
        String myString = "I am a String. Yes, I am.";
        System.out.println(myString);

        String yourString = myString.replaceAll("I","You");
        System.out.println(yourString); // You am a String. Yes, You am.

        // Character class (.) matches any character - wild card for any character
        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphanumeric.replaceAll(".","Y")); //YYYYYYYYYYYYYYYYYYYYYYY

        // ^ - caret boundary matcher
        System.out.println(alphanumeric.replaceAll("^abcDeee","YYY")); // YYYF12Ghhiiiijkl99z


        // only matches from the start of a String
        String secondString = "abcDeeeF12GhhabcDeeeiiiijkl99z";
        System.out.println(secondString.replaceAll("^abcDeee","YYY")); // YYYF12GhhabcDeeeiiiijkl99z


        // boolean String.matches(String regex)
        // The string as a whole has to match the regex to evaluate to true/false
        // Same behaviour with IntelliJ quick check
        System.out.println(alphanumeric.matches("^hello")); // false


        // When using matches with it begins with boundary matcher , ^ in other words, the entire string has to match the regex
        System.out.println(alphanumeric.matches("^abcDeee")); // false
        System.out.println(alphanumeric.matches("^abcDeeeF12Ghhiiiijkl99z")); // true

        // Either way, with or withour carret - works
        System.out.println(alphanumeric.matches("abcDeeeF12Ghhiiiijkl99z")); // true

        // More character classes and boundary matches
        // $ boundary matcher - is always preceded by a pattern , and matches strings that end with the pattern
        // It's the opposite of the ^ boundary matcher

        System.out.println(alphanumeric.replaceAll("ijkl99z$","THE END")); // abcDeeeF12GhhiiiTHE END


        // When we want to match a specific letter or set of letters, we cna actually put those letters within square brackets
        // let's find and replace all occurrences of aei with X

        System.out.println(alphanumeric.replaceAll("[aei]","X")); //XbcDXXXF12GhhXXXXjkl99z

        System.out.println(alphanumeric.replaceAll("[aei]","I replaced a letter here")); //XbcDXXXF12GhhXXXXjkl99z

        // Do the same again but only if we want to replace aei if they're followed by an F or a J
        // only do the replacement if one of the 3 letters aei is actually followed by an F or a J
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X")); // abcDeeX12GhhiiiXkl99z


    }
}
