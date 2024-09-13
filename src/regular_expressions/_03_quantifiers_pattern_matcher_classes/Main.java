package regular_expressions._03_quantifiers_pattern_matcher_classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Quantifiers , Pattern and Matcher classes
 *
 *
 * /////////////
 *  Quantifiers
 * /////////////
 *
 * - Here is the first example that we looked at and "^abcDeee" was our regular pattern that matched the alphanumeric String and
 *   as a result, the characters "abcDeee" were replaced with the letters YYY since they matched with the regex
 *
 *      System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("^abcDeee","YYY"));
 *
 *      - prints "YYYF12Ghhiiiijkl99z"
 *
 *
 * ///////////////////////
 *  {n} - Exactly n times
 * ///////////////////////
 *
 * - There's another way to write this expression but use a quantifier
 *
 * - Quantifiers specify how often an element in a regular expression can occur
 *
 * - In our example there are 3 e's occurring in a row
 *
 * - Instead of writing them out , we can actually use a quantifier as follows
 *      - We can leave abcD as well as the first letter but then delete the last 2 e's and add {3}
 *
 *          "^abcDe{3}"
 *
 *      - {3} indicates the no of the preceding character that must occur in order for there to be a match
 *      - in this case, we can read this as 3e's
 *      - Also note that quantifiers always comes after the character that it applies to
 *
        System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("^abcDe{3}","YYY"));
 *
 *      - prints "YYYF12Ghhiiiijkl99z" which is the same results we got without using the quantifiers
 *
 *
 * ////////////////////
 * plus (+) quantifier
 * ////////////////////
 *
 * Suppose we don't actually care how many e's are there, we want to match Strings that match with abcD followed by 1 or more e's
 * - In this case we'd use a different quantifier which is the (+) plus quantifier
 *
       System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("^abcDe+","YYY"));
 *
 *     - prints "YYYF12Ghhiiiijkl99z"
 *
 * - The good thing with plus (+) quantifier is that we don't need the curly braces,
 * - And the reason for that is because we aren't providing any value
 * - We're actually specifying 1 or more e's without specifying the actual number
 *
 *
 *
 * //////////////////////
 * asterisk/wildcard (*)
 * //////////////////////
 *
 * - Suppose we don't care if there are any e's, the string can begin with abcD and then it's followed by any e's we want to match
 * - But we also want to match if it's not followed by any e's
 * - In other words, we want to match strings that begin with abcD, followed by 0 or more e's
 * - In this case we'd use * or the asterisk quantifier
 *
 *      System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("^abcDe*","YYY"));
 *
 *      - prints "YYYF12Ghhiiiijkl99z" which is the same result that we got with the other versions above
 *
 * - Let us remove the string e's from alphanumeric and re-run
 *
 *      System.out.println("abcDF12Ghhiiiijkl99z".replaceAll("^abcDe*","YYY"));
 *
 *      - prints "YYYF12Ghhiiiijkl99z"
 *      - proceeds with replacement whether there's an e or not
 *      - this is because the regular expression is going to look for abcD and whether it has got an e after that or not
 *         just still proceed with replacement
 *
 *
 * ///////
 *  What would this quantifier be useful for ?
 *
 *  - one use case might be when you're doing something like verifying user input, and part of what we've asked for is OPTIONAL
 *  - You might want to look for that if it's there or not there and still get some valid input
 *
 *
 *
 * ///////////////////////////////////
 *  {n , m} - (Between n and m times)
 * ///////////////////////////////////
 *
 * - Matches between n and m (inclusive) occurrences of the preceding element.
 *
 * - Suppose we want to match strings that begin with abcD followed by 2-5 e's

 *      System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("^abcDe{2,5}","YYY"));
 *
 *      - prints "YYYF12Ghhiiiijkl99z" and we got a match there
 *
 * - But if we change our alphanumeric string to only have 1 e now,
 *
 *      System.out.println("abcDeF12Ghhiiiijkl99z".replaceAll("^abcDe{2,5}","YYY"));
 *
 *      - prints "abcDeF12Ghhiiiijkl99z" and there's no replacement because there was no match
 *      - this doesn't succeed because we're looking at at least 2 to 5 e's
 *
 *
 * //////////////
 * Combination
 * ///////////////
 *
 * - We also don't have to match from beginning or at the end, we can match parts in the middle or the end of the String
 *   replace from anywhere
 * - Let's try to replace all the occurrences of h, followed by any number of i's , followed by at least 1 j with a Y
 *
 *      System.out.println("abcDeeeF12Ghhiiiijkl99z".replaceAll("h+i*j","Y"));
 *
 *      - prints "abcDeeeF12GYkl99z"
 *      - we've used h+ , + rep 1 or more h
 *      - then i* , * rep 0 or more i's
 *      - followed by j
 *
 * ///////////////
 *
 * - There are other character classes and boundary matches that we can use in regular expressions
 * - Check for documentation at Oracle's website
 *      - Check them out and experiment since now we have the basics of regular expressions
 *
 *
 *
 *
 * ///////////////
 * Pattern Class
 * ///////////////
 *
 * - We've been using String.matches() and String.replaceAll() to demonstrate the basics of writing regular expressions
 *
 * - We can also use Pattern class, to manipulate Strings using regular expressions
 *
 * - Some Java API's want to work with patterns , rather than a String that represents a regular expression and we can use
 *   Pattern.compile() to compile a regular expression into a pattern
 *
 * - This is often done when we want to work with the methods in the Matcher class
 *
 * - Matchers work with classes that implement the CharSequence interface and that means we can use matches with Strings , StringBuffer
 *   StringBuilder and other classes that implement that interface
 *
 * - Generally we use Matcher, when we want to find multiple occurrences of a pattern, or when we want to use the same pattern
 *   with multiple sequences
 *
 * - If we're only going to use a regular expression once to check 1 occurrence in a String, then we'd probably don't need to use a
 *   Matcher
 *
 * - Let's take a look at an example:
 *
 * - Suppose we want to find all the occurrences of the h2 tag in a char sequence of HTML text
 * - Create some string
 *
 *      StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>My Heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");
 *
 *
 * - We've used a StringBuilder, because we're creating a long String
 * - Assume that the HTML text is part of a large part of a lrge page within a HTML body perhaps that is been extracted by some
 *   other process
 * - Use a Matcher class to find out if there's any h2 tags within this text
 *
 * - Create the patter that we're going to look for
 *
 *      String h2Pattern = "<h2>";
 *
 * - Then create Compile the pattern which creates Pattern instance/object
 * - Create a Matcher obj that calls matcher() on htmlText against our pattern obj
 *
 *      Pattern pattern = Pattern.compile(h2Pattern);
 *      Matcher matcher = pattern.matcher(htmlText);
 *
 * - Then call matches() on Matcher instance and checks whether the pattern matches the regular expression
 *
 *      System.out.println(matcher.matches());
 *
 *      - prints false
 *
 * - We might be thinking that this won't match tags that are in UPPERCASE and we're actually right
 *
 * - We have 2 choices to fix this
 *      - Modify regular expression to also look for h2 with an uppercase H,
 *      - Or when we compile the pattern , we could pass the Pattern.CASE_INSENSITIVE class as second parameter to the compile()
 *
 *          e.g. Pattern pattern = Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE);
 *
 *      - Or if you're using unicode, could write this along the line of
 *
 *          e.g. Pattern pattern = Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
 *
 *      - But in this case, we're going to assume that the tags in the HTML are in lowercase
 *
 * ////
 *
 * - Let's run this and see what happens :
 *      - And interestingly enough we get false
 * - So why is this not working
 *      - It turns out that the matches() wants to match the String as a whole , just like the String.matches() did
 * - So we need to write a regular expression that's going to match the entire text
 *
 * - We can do this quite easily with a regular expression as follows:
 *
 *      String h2Pattern = ".*<h2>.*";
 *
 *      - we have combined the dot (.) character class with * quantifier
 *      - If you recall, the . dot, will match every character and * means 0 or more
 *      - Effectively, what we're saying here is that there can be anything before and anything after the h2
 *      - And we'll actually have a match
 *
 * - And after running this, we get true because the entire String or character sequences now matches the pattern
 *
 *
 * ////////
 * - Well, that's fine and dandy, but how can we find out how many occurrences there are and where they occur
 * - Let's talk about doing that in the next video
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";

        System.out.println(alphanumeric.replaceAll("^abcDeee","YYY")); // YYYF12Ghhiiiijkl99z
        // above can also be written as - we get the same result
        // {n} quantifier
        System.out.println(alphanumeric.replaceAll("^abcDe{3}","YYY")); // YYYF12Ghhiiiijkl99z

        // + quantifier
        System.out.println(alphanumeric.replaceAll("^abcDe+","YYY")); // YYYF12Ghhiiiijkl99z

        // * quantifier
        System.out.println(alphanumeric.replaceAll("^abcDe*","YYY")); // YYYF12Ghhiiiijkl99z

        // Remove the string e's from alphanumeric and re-run
        //replace whether there's an e or not, proceed with replacement
        System.out.println("abcDF12Ghhiiiijkl99z".replaceAll("^abcDe*","YYY")); // YYYF12Ghhiiiijkl99z

        // specify min or max times a character can occur
        // Suppose we want to match strings that begin with abcDfollowed by 2 to 5 e's
        System.out.println(alphanumeric.replaceAll("^abcDe{2,5}","YYY")); // YYYF12Ghhiiiijkl99z

        // IF we change to 1 e now
        // Replacement not done because, we only have 1 e, requirement, 2 upto 5 e's required to work
        System.out.println("abcDeF12Ghhiiiijkl99z".replaceAll("^abcDe{2,5}","YYY")); //abcDeF12Ghhiiiijkl99z

        // we don't have to match from beginning or at the end, we can replace from anywhere
        // replace all the occurrences of h, followed by any number of i's , followed by at least 1 j with a Y
        // we want 1 h , hence +
        // followed by 0 or more i's , hence *
        // followed by j
        System.out.println(alphanumeric.replaceAll("h+i*j","Y")); //abcDeeeF12GYkl99z


        /// Pattern class

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>My Heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

        // Use a Matcher class to find out if there's any h2 tags within this text
        //String h2Pattern = "<h2>"; -- will return false
        String h2Pattern = ".*<h2>.*"; // returns true
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());


    }
}
