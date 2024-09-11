package regular_expressions._02_character_classes_and_boundary_matches;
/*
 * Character classes and Boundary matchers
 *
 *
 * - Except for the most trivial cases , most regular expressions will consist of literals boundary matches and character classes strung
 *   together
 * - There will usually be quantifiers as well and we'll look into them later
 *
 * - One example will be using the square brackets in a regular expression would be to find a word in a string , that may be in upper or
 *    lower case
 * - We could actually upper or lower the string first and then use something like String.indexOf
 * - Let's say we wanted to find a name "Harry" with a capital H, or harry with a lowercase h, without upper or lower casing the string
 * - We could do something like this
 *
 *      - Whether it's uppercase or lowercase, it gets converted to uppercase, because of what we have put in the replacement string
 *
 *
 * ////////
 *
 * - Suppose we wanted to replace every letter in the newAlphanumeric string except e and j
 * - When we use the caret as the 1st character inside [] square brackets, it's actually a character class and not a boundary
 *   matcher - eliminate confusion
 * - Since we're using it inside the [] , it negates the pattern that follows it
 * - Instead of matching all the occurrences of e and j , the regular expression [^ej], will match all occurrences that are not e and j
 * - And consequently, all the characters except e and j have been replaced by the the specified character
 *
 * - To get this behavior, the caret ^ , has to be inside square brackets, when it's not inside the square brackets , a caret ^ is used
 *    to denote the beginning of a String or line
 *
 *
 * /////
 * - Suppose we want to replace all occurrences of a through f and 3 through 8 inclusive
 * - Here is one way we can do this
 *
 *      System.out.println(newAlphanumeric.replaceAll("[abcdef345678]", "X")); // XXXDXXXF12Ghhiiiijkl99z
 *
 * - note that the D and F weren't replaced , because regex are case-sensitive , and in this case they weren't replaced because
 *   they're uppercase letters
 *
 * - Here is another shorter way to do that
        System.out.println(newAlphanumeric.replaceAll("[a-f3-8]", "X")); // XXXDXXXF12Ghhiiiijkl99z
 *
 * - And we should get exactly the same results
 *
 * - The dash character we've used in this case specifies a range,
 * - When we have multiple ranges as we do here, there's no need to separate them with a comma, we actually go on to the next range
 *    without adding a space between the ranges
 *
 * - Since regex are case-sensitive, if we wanted to actually match the capital A-F as well, we can revise the expression slightly and
 *   write it as follows
        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]", "X")); // XXXDXXXF12Ghhiiiijkl99z
 *
 * - and this time we get capital D and capital F replaced, because we've specified the uppercase versions as well as lowercase version
 *
 * - There's still another way we can do this , it's actually possible to turn the case sensitivity using the following special construct
 *
        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]", "X")); // XXXXXXXX12Ghhiiiijkl99z

 *      - And if we do that, we're now ignoring case sensitivity , and you can see that we never specified A-F as we did
 *      - This is because we're using special construct (?i)
 *
 * - And this works with ASCII strings , If the string is Unicode, then we'd actually need to use something like (?iu), if its a Unicode
 *
 *
 * ////
 * - Suppose we wanted to replace all the numbers in the string with "X"
 *
 * - One way of doing that will be
 *
        System.out.println(newAlphanumeric.replaceAll("[0-9]", "X")); // abcDeeeFXXGhhiiiijklXXz
 *
 *      - and this replaces any digits between 0 and 9 with an X
 *
 * - There's also a shorthand way to do the same thing
 * - When we want to match any digit , we can actually use \d character class
 *
        System.out.println(newAlphanumeric.replaceAll("\\d", "X")); // abcDeeeFXXGhhiiiijklXXz
 *
 *      - and this also replaces any digits between 0 and 9 with an X
 *      - note that you have to escape the slash with , we had to put a backslash, and put \d in the extra string to make it valid string
 *      - If we don't put a second string there, we'll actually get an error, illegal escape character in string literal
 *
 * - There's also a shortcut to replace all non-digits as well
        System.out.println(newAlphanumeric.replaceAll("\\D", "X")); // XXXXXXXX12XXXXXXXXXX99X
 *
 *      - and you can see we've replaced non-digits as well with "X"
 *
 * /////
 * - The String class has a trim() that removes whitespace from the beginning and at the end of the string
 *
 * - But suppose we want all white space remove no matter where it occurs in the String
 * - Declare a new string
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Harry".replaceAll("[Hh]arry","Harry")); // Harry
        System.out.println("harry".replaceAll("[Hh]arry","Harry")); // Harry

        String newAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";

        //replace every letter in the alphanumeric string except e and j
        System.out.println(newAlphanumeric.replaceAll("[^ej]","X")); // XXXXeeeXXXXXXXXXXjXXXXX


        System.out.println(newAlphanumeric.replaceAll("[abcdef345678]", "X")); // XXXDXXXF12Ghhiiiijkl99z
        System.out.println(newAlphanumeric.replaceAll("[a-f3-8]", "X")); // XXXDXXXF12Ghhiiiijkl99z

        // and this time we get capital D and capital F replaced, because we've specified the uppercase versions as well as lowercase version
        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]", "X")); // XXXXXXXX12Ghhiiiijkl99z


        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]", "X")); // XXXXXXXX12Ghhiiiijkl99z


        System.out.println(newAlphanumeric.replaceAll("[0-9]", "X")); // abcDeeeFXXGhhiiiijklXXz

        System.out.println(newAlphanumeric.replaceAll("\\d", "X")); // abcDeeeFXXGhhiiiijklXXz

        System.out.println(newAlphanumeric.replaceAll("\\D", "X")); // XXXXXXXX12XXXXXXXXXX99X


        ///////

        String hasWhitespace = "I have blanks and\t a tab, and also a new line\n";
        System.out.println(hasWhitespace);

       // use regex to remove all whitespace from the string
        System.out.println(hasWhitespace.replaceAll("\\s","")); // Ihaveblanksandatab,andalsoanewline

        // if we only wanted to remove specific whitespace characters, we could specify those in regex
        // just as we would for any other string: \t , \n etc
        System.out.println(hasWhitespace.replaceAll("\t","X")); // I have blanks andX a tab, and also a new line

        // we can use \\S to remove non whitespace characters
        System.out.println(hasWhitespace.replaceAll("\\S","X")); // X XXXX XXXXXX XXX	 X XXXX XXX XXXX X XXX XXXX


        // we can use \\w to match a-z , A-Z, 0-9 and also the underscore characters
        System.out.println(newAlphanumeric.replaceAll("\\w","X")); // XXXXXXXXXXXXXXXXXXXXXXX
        // \\w works similarly to "." that we saw in an earlier video
        System.out.println(newAlphanumeric.replaceAll(".","X")); // XXXXXXXXXXXXXXXXXXXXXXX

        // also running that on white space - note white space characters are not replaced , but everything else is
        System.out.println(hasWhitespace.replaceAll("\\w","U")); // U UUUU UUUUUU UUU	 U UUU, UUU UUUU U UUU UUUU


        // also note that \\W , does the opposite , will match everything except: a-z , A-Z, 0-9 and also the underscore
        System.out.println(hasWhitespace.replaceAll("\\W","U")); // IUhaveUblanksUandUUaUtabUUandUalsoUaUnewUlineU

        // \\b boundary matcher is an interesting one
        // it matches word boundaries , assumes words are separated by whitespace
        // Each word is surrounded by the replacement string , and so we get an M at the start and at the end of each word
        System.out.println(hasWhitespace.replaceAll("\\b", "<>")); // <>I<> <>have<> <>blanks<> <>and<>	 <>a<> <>tab<>, <>and<> <>also<> <>a<> <>new<> <>line<>

        // There are situations where the above can be useful, we could use it when we want to surround each word with a tags of some code
        // Perhaps you're creating some HTML code or needing some sort of other tags
        // cna be a quick way to replace that and put a tag at the start and the end of every word



    }
}
