package first_steps.part12_char_boolean_primitive_types;
/*
 * The char and boolean Primitive Data Types
 * .........................................
 *
 * - Time to look at the last 2 Primitive Types - char and boolean
 *
 * - Define our first character and assign that to a letter enclosed by a single quote
 *
 *      char myChar = 'D';
 *
 *      - This could be any character, letter or number, and other characters, like exclamation mark, a hash, an @ sign
 *        and so on
 * - A char is different to a string
 *
 * - In the previous videos, we;ve used literal strings , where we've typed some texts in double quotes
 * - We haven't used a string variable yet but we'll do that in upcoming sections
 *
 * - So char is similar to a string in one sense , in that it can store characters,
 * - However, in this case, you can only store a single character
 *
 * - This means, that if we try to add another character
 *      char myChar = 'DD'; // Error
 * - We will get an error - If we hover it, we get "Too many characters in character literal"
 * - It is literally only allowing us to save 1 character
 *
 * You might be wondering why would you want to use a variable that only allows you to store only 1 character ?
 * ............................................................................................................
 * - One example might be to store the last key pressed by a user in a game
 * - Or the last digit used in a menu option along those lines
 * - Press (Y or N ) or (0 or 1 ) to exit cases
 *
 * - char were much more relevant when Java was released in the late 1990's compared to today, with much faster
 *   computers that have got lots more memory
 * - In these days, you don't need to focus so much on saving memory
 *
 * - Cause another good use for char(s) would be to store data in arrays
 *
 * - For now, let's just know that we can only store a single character in a single char variable
 *
 * Char Data type
 * ..............
 * - A char primitive type in Java occupies 2 bytes - (or 16 bits has a width of 16)
 * - The reason it's not just a single byte is that it allows you to store Unicode characters
 *
 * Unicode
 * .......
 * - Unicode is an international encoding standard for use with different languages and scripts, by which each letter
 *   , digit, or symbol is assigned a unique numeric value that applies across different platforms and programs
 * - In the English Alphabet, we've got letters A through Z, Meaning only 26 characters are needed in total to represent
 *   the entire English Alphabet
 * - But other languages need more characters , and often a lot more
 * - Unicode allows us to represent these languages and the way it works is that by using a combination of the 2 bytes
 *   that a char takes up in memory, it can represent any one of the 65535 different types of char(s)
 * - So it's quite flexible with those 2 bytes
 *
 * - Visit https://www.ssec.wisc.edu/~tomw/java/unicode.html for unicode chart
 *    - Each character is represented with a specific unicode and a numeric value-decimal value it represents
 *    - For example:
 *          "0040" rep @
 *          "0041" rep A
 *          "0042" rep B
 *          "0043" rep C
 *          "0044" rep D
 * - Unicode character comprises a code that we key in into IntelliJ
 * - We need to append a backslash u and prepend the 4 digits
 *
 *      char myUnicodeChar = '\u0044';
 *
 * Next
 * - Let's print out the values
 *      - And sure enough, we've got D printing out twice in the output, in the console
 *
 * Next,
 * - Lets print the copyright symbol  rep by 00A9
 *
 *
 * Boolean Primitive Type
 * ......................
 * - A boolean value allows for 2 choices:
 *      - True or False
 *      - Yes or No
 *      - 1 or 0
 * - In Java terms, we've a boolean primitive type & it can be set to 2 values only (ture or false)
 * - They're pretty useful & you'll use them a lot when programming
 *
 * Next,
 * - Define a number of boolean values
 *      boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;
 *
 * So, what would be a typical example of using a boolean in a program ?
 *  - Well, let's say you wanted to know whether a particular customer was either the age of 21 or not
 *  - You might type the boolean like this
 *      boolean isCustomerOverTwentyOne = true;
 *
 * - The variable name is more of a question "isCustomerOverTwentyOne" and we've assigned a value of true to it and
 *   of course in this particular case, the customer is over 21
 *
 * - You'll see that very often with boolean, they'll use "is" and then a question to make it very intuitive as to
 *    what that variable is actually tracking
 * - We'll see practical applications of the boolean when we start tackling conditional logic
 *
 *
 */

public class Main {
    public static void main(String[] args){
        char myChar = 'D';
        char myUnicodeChar = '\u0044';

        System.out.println(myChar);
        System.out.println(myUnicodeChar);

        char myCopyrightChar = '\u00A9';
        System.out.println(myCopyrightChar);


        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;
    }
}
