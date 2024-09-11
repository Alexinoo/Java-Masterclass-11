package regular_expressions._04_matcher_find_group_methods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>My Heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

        //String h2Pattern = ".*<h2>.*"; // returns true
        String h2Pattern = "<h2>";
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        // Count occurrence of <h2>
        // find() - looks for occurrences of a pattern, used to create the matcher
        // it one is found, the internal state of the matcher instance is updated and we can call the start and end methods
        // start() , returns index of the first character in the match
        // end() , returns index of the character that occurs right after the last character in the match
        // we have 2 occurrences of h2 in our pattern - and we should expect to see 2 occurrences printed to the console

        // First run - nothing shows
        // We can only use a Matcher once
        // matches have an internal state that's updated whenever we use it
        // When we used the find() , the matcher was updated , and then we can use the start and end methods
        // when we call the matcher.matches() , so that we can print to the console to the console, the internal state of the matcher
        //  was updated in such a way that we have to reset the matcher before we can use it
        // we do that by calling the reset()

        matcher.reset();

        // we've reset the matcher above so that we can use it again

        int count = 0;
        while (matcher.find()){
            count++;
            System.out.println("Occurence "+count + " : "+matcher.start()+ " to "+matcher.end());
        }

        // using String h2Pattern = ".*<h2>.*"; won't work - used it with matches() to match the entire string
        // And ultimately, there's only 1 occurrence of a match in the entire string , and the start and end indices are for the entire string

        // change to String h2Pattern = "<h2>"; - we only need the occurrences and not the entire string but only parts of
        // the text and hence the usage of "<h2>" instead of ".*<h2>.*"
        // and now we get the right results


        /* group - we use parentheses
         * - parentheses aren't part of the pattern that has to be matched
         * - when working with groups we access them by using group() in the matcher class
         * - one version of the group() takes an int parameter, which is the group number
         * - when working with groups, the entire character sequences group zero
         * - so when we want to access a specific group, we start counting from the number 1
         *
         * - use an example below
         *
         * - loop is now different to what it was when we were using previous matcher.find()
         * - for each occurrence, we call the group(1) with a value 1,
         * - and we have more than 1 occurrence but we only have 1 group in our regex
         * - group(0) is the entire character sequence , and group(1) is the (<h2>) in parentheses group
         * - print 2 occurrences of <h2>

         * - Ok, we now know that there are 2 occurrences of the <h2> but then so what, it would be nice to extract h2 from
         *   the text in its entirety which would include the opening tag, text and the closing tag
         * - All we have to do is change our regex to match the opening tag , h2 text and the closing tag
         *
         * - Need to surround the entire "(<h2>)" to "(<h2>.*</h2>)"
         *      - means we want the opening tag , the closing tag and everything in between
         *      - the dot (.) means any character, and then the star quantifier means 0 or more
         *      - so .* will match 1 or more of any character
         *
         *
         * greedy quantifiers vs reluctant or lazy quantifiers
         * - * quantifier is what's called a greedy quantifier - will grab as much text as it can
         *      - it's matching and doesn't stop and keeps going to see if more of the character sequence can result in a match
         * - We want to use a lazy quantifier, one that does the minimum amount of work, once it finds a match, it doesn't keep looking
         *   to see if it can include more characters in the match
         *      - may still find more matches in later parts of a character sequence but each match will contain minimum no of characters
         *        required for a match
         *      - the ? quantifier means 1 or 0 occurrences is a lazy quantifier and we can use it to turn a greedy quantifier into a
         *         lazy quantifier by adding it after the quantifier
         *
         * - change "(<h2>.*</h2>)" to "(<h2>.*?</h2>)"
         * - and now we get only occurrences of h2 tags
         */
        //String h2GroupPattern = "(<h2>)";
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches()); // false

        groupMatcher.reset();

        while (groupMatcher.find()){
            System.out.println("Occurrence: "+groupMatcher.group(1));
        }

        /*
         * Get the text between the h2 tags
         * - create another group pattern
         * - group(0) rep the entire htmlText
         * - group(1) rep (<h2>)
         * - group(2) rep (.+?)
         * - group(3) rep (</h2>)
         *
         * - check the matcher documentation
         */

        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while (h2TextMatcher.find()){
            System.out.println("Occurrence: "+ h2TextMatcher.group(2));
        }

    }
}
