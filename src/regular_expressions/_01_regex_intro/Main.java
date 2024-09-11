package regular_expressions._01_regex_intro;

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
        // always followed by a pattern which could be a String literal or something more complex
        // The regular expression must match the beginning of the string
        // Use replaceAll and suppose we want to replace the first part of the string "abcDee" with something else
        // note that the length of a regex doesn't have to match the length of the replacement

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
