package regular_expressions._04_matcher_find_group_methods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Matcher , find and Group methods
 *.................................
 *
 * - We started working with Pattern and Matcher classes and we saw some results when the entire String or character sequence
 *   matches the pattern
 *
 * - But how can we find the occurrences and where they actually occur ?
 *
 * - We need to use find() , the start() and the end() methods
 *
 * - We need to create a loop
 *
 *      int count = 0;
        while (matcher.find()){
            count++;
            System.out.println("Occurrence "+count + " : "+matcher.start()+ " to "+matcher.end());
        }
 *
 * - find()
 *      - looks for occurrences of a pattern, used to create the matcher and when it finds one, the internal state of the
 *          matcher instance is updated and we can then call the start and end methods
 *
 * - start()
 *      -  returns index of the first character in the match
 *
 * - end()
 *      - returns index of the character that occurs right after the last character in the match
 *
 * - So we have 2 occurrences of h2 in our pattern  and we should expect to see 2 occurrences printed to the console
 *
 *
 *
 * ///
 * IF we run this:
 * - nothing shows - the loop actually didn't find anything
 * - There is a reason for that because we can only use a Matcher once
 * - matches have an internal state that's updated whenever we use it
 * - When we used the find() , the matcher was updated , and then we can use the start and end methods
 * - But when we call the matcher.matches() , so that we can print to the console, the internal state of the matcher was updated in
 *    such a way that we have to reset the matcher before we can use it
 * - We do that by calling the reset()
 *
 *       matcher.reset();
 *
 *      - resets the internal state so that we can use it again
 *
 * ////
 * IF we now run this again
 * - we got below as output
 *
 *      Occurrence 1 : 0 to 179
 * - but obviously not what we expected to see
 *
 * - So what's actually going on ?
 *
 * - The regular expression we use to determine if h2 occurs anywhere in the text isn't specific enough when it comes to pin-pointing
 *    how many occurrences there are, and also where they are
 *
 * - We had to use a broad expression with a matches() because the entire text had to match
 *
 *      String h2Pattern = ".*<h2>.*"
 *
 * - Since we used such a broad expression, there's only 1 occurrence of a match in the entire string , and the start
 *   and end indices are for the entire string
 *
 * - But now we're looking to find the occurrences within a String and therefore we don't need the entire string to match but only care
 *     about matching parts of the text
 *
 * - We can go back to using h2 as the regular expression and hence the usage of "<h2>" instead of ".*<h2>.*"
 *
 *
 * - And now if we run this
 *    - we get the right results
 *
 *      Occurrence 1 : 19 to 23
 *      Occurrence 2 : 136 to 140
 *
 * - Keep in mind that the index returned by the end() is actually the index for the 1st character that comes after the match
 * - This obviously works, but when you're using a matcher, to find multiple occurrences of a pattern is an easy way to do it
 * - We can actually use a group
 *
 * ///////////
 *
 * - To use a group in a regular expression, we use parentheses
 * - Therefore, we change our pattern into a group as below
 *
 *       String h2Pattern = "(<h2>)";
 *
 * - But keep in mind that parentheses aren't part of the pattern that has to be matched
 * - when working with groups we access them by using matcher.group() in the matcher class

 * - one version of the group(int group) takes an int parameter, which is the group number
 *
 * - when working with groups, the entire character sequences is group(0)

 * - so when we want to access a specific group, we start counting from the number 1

 * /////////////////
 *
 * - use an example below and create another group
 *
 *       String h2GroupPattern = "(<h2>)";
 *
 *       Pattern groupPattern = Pattern.compile(h2GroupPattern);
         Matcher groupMatcher = groupPattern.matcher(htmlText);
         System.out.println(groupMatcher.matches()); // false

         groupMatcher.reset();

         while (groupMatcher.find()){
            System.out.println("Occurrence: "+groupMatcher.group(1));
         }

 *      - We have updated the regular expression with a parentheses now *
 *      - also note that the loop is now different to what it was when we were using previous matcher.find()
 *      - for each occurrence, we call the group(1) with a value 1,
 *      - and we have more than 1 occurrence but we only have 1 group in our regular expression
 *      - group(0) is the entire character sequence , and group(1) is the (<h2>) in parentheses
 *
 *
 * ///////////
 *
 * - If We run this:
 *      - we get a false for the match and 2 occurrences of <h2>
 *          false
 *          Occurrence : <h2>
 *          Occurrence : <h2>
 *
 * - Ok, we now know that there are 2 occurrences of the <h2> but then so what, it would be nice to extract every h2 from
        the text in its entirety which would include the opening tag, text and the closing tag

 * - All we have to do is change our regex to match the opening tag , h2 text and the closing tag
 *
 *      String h2Pattern = "(<h2>.*</h2>)"
 *
 *      - surrounding the entire, with parentheses with a group is what we've done
 *      - this means we want the opening tag , the closing tag and everything in between
 *      - the dot (.) means any character, and then the star quantifier means 0 or more
 *      - so .* will match 1 or more of any character
 *
 *
 * ///////////
 *
 * - If We run this:
 * - It's starting to look like we want, but clearly, not exactly what we want
 * - What is happening is that the regular expression has grabbed everything form the opening tag of h2 to the closing tag of
 *   the second h2 as below
 *
 *      <h2>My Heading</h2><p>This is a paragraph about something.</p><p>This is another paragraph about something else.</p><h2>Summary</h2>
 *
 * - Now does this match our regular expression ?
 *      - returns text between <h2> and </h2> but definitely not what we were expecting
 *
 * ////////
 *
 * The reason why this is happening is because of greedy quantifiers vs reluctant or lazy quantifiers
 * - * quantifier is what's called a greedy quantifier - will grab as much text as it can
 * - and so it's matching and doesn't stop at </h2> and keeps going to see if more of the character sequence can result in a match
 * - In our example, grabbing more text can still result in a match as we've seen here
 *
 * ///////

 * - We want to use a lazy quantifier, one that does the minimum amount of work, once it finds a match, it doesn't keep looking
 *   to see if it can include more characters in the match
 *
 * - It may still find more matches in later parts of a character sequence but each match will contain minimum no of characters
 *   required for a match
 *
 * - The ? quantifier (means 0 or occurrences) is a lazy quantifier & we can use it to turn a greedy quantifier into a lazy quantifier
 *   by adding it after the quantifier
 *
 * - Since it wants no or only 1 occurrence, the moment the greedy quantifier that it's modifying finds a match, it will stop grabbing
 *   text , and obviously that's what we want here , so that we can actually our h2 occurrences
 * - And the reason it will stop at that point is because the ? quantifier will be satisfied at that point
 *
 *
 * ///////////
 *
 * - Change pattern to
 *
 *      String h2Pattern = "(<h2>.*?</h2>)";
 *
 * - By adding ?, we've turned the star quantifier into a lazy quantifier
 *
 * - And now once we run this, we get only occurrences of h2 tags
 *
 *      <h2>Sub Heading</h2>
 *      <h2>Summary</h2>
 * - And the text in between for the summaries as well
 *
 *
 *
 * /////
 * - We've seen how we can use matches to parse HTML code which is a little bit more exciting than what we've done so far
 * - If we weren't interested in empty h2 tags, and their tags with no text between the opening and closing tag, we'd use the
 *   regular expression
 *
 *      String h2Pattern = "(<h2>.+?</h2>)";
 *
 * - So the plus (+) as opposed to the star (*) wants 1 or more occurrences, whereas the star will accept 0m or more occurrences
 * - And we can also use multiple groups
 *
 * - For example, if we wanted the text between the tags and not actually the tags themselves, we could change that a little bit
 *   and add a multiple tag to our text group
 *
 *      String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while (h2TextMatcher.find()){
            System.out.println("Occurrence: "+ h2TextMatcher.group(2));
        }
 *
        - create another group pattern
 *      - defined 3 groups
 *      - Get the text between the h2 tags
        - group(0) rep the entire htmlText
        - group(1) rep (<h2>)
        - group(2) rep (.+?)
        - group(3) rep (</h2>)

        - check the matcher documentation
*/

public class Main {
    public static void main(String[] args) {
        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");              //19
        htmlText.append("<h2>Sub Heading</h2>"); //23
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");     //136
        htmlText.append("<h2>Summary</h2>");                                           // 140
        htmlText.append("<p>Here is the summary.</p>");

        //String h2Pattern = ".*<h2>.*"; // returns true
        String h2Pattern = "<h2>";
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        matcher.reset();

        int count = 0;
        while (matcher.find()){
            count++;
            System.out.println("Occurence "+count + " : "+matcher.start()+ " to "+matcher.end());
        }


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
