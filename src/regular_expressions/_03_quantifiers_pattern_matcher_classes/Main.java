package regular_expressions._03_quantifiers_pattern_matcher_classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Quantifiers , Pattern and Matcher classes
 */
public class Main {

    public static void main(String[] args) {

        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";

        // - abcDeee has been replaced with the letters YYY
        System.out.println(alphanumeric.replaceAll("^abcDeee","YYY")); // YYYF12Ghhiiiijkl99z

        // Do the same as above, but use a Quantifier
        // Quantifiers specify how often an element in a regex can occur
        // In our example there are 3 e's occurring in a row
        // Instead of writing them out , we can actually use a quantifier as follows
        // The 3 in {} indicates the no of the preceding character that must occur in order for there to be a match - in this case 3e's
        // quantifiers always comes after the character that it applies to

        System.out.println(alphanumeric.replaceAll("^abcDe{3}","YYY")); // YYYF12Ghhiiiijkl99z

        // Suppose we don't actually care how many e's are there, we want to match Strings that match with abcD followed by 1 or more e's
        // In this case we would use a different quantifiers which is the plus (+) as follows
        System.out.println(alphanumeric.replaceAll("^abcDe+","YYY")); // YYYF12Ghhiiiijkl99z

        // With + , we don't need the curly braces , and the reason for that is because we aren't providing any value
        // We're actually saying 1 or more e's without specifying the actual number

        /////
        // Suppose we don't care if there any e's, the string can begin with abcD and then it's followed by any e's we want to match
        // But we also want to match if it's not followed by any e's
        // We want to match strings that begin with abcD, followed by 0 or more e's
        // In this case we'd use * or the asterisk quantifier
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
