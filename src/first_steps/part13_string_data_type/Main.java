package first_steps.part13_string_data_type;
/*
 * Primitive Types Recap and the String Data Type
 * ..............................................
 *
 * - The 8 primitive types that we've looked at in Java are
 *    - byte  - holds a range of (-128 to 127)
 *    - short - holds a range of (-32768 to 32767 )
 *    - int - holds a range of (-2147483648 to 2147473647)
 *    - float
 *    - double
 *    - long
 *    - char
 *    - boolean
 *
 * - The most common types you'll use will be an int, double , boolean and probably from time to time you'll use a long
 *   and a char but not as often
 * - byte, short and float, these are types that you'll rarely use at all
 *
 * - Now as we deep-dive further into the course, you'll that there's a way to create your own data types, which in Java
 *   are called Classes
 * - What's interesting about Classes is that you can combine data types like perhaps 1 or more ints, double , booleans
 *   e.t.c and sort of create sort of like a super data type
 *
 * - For now, let's talk about another data type, called a String
 *
 *
 * String
 * ......
 * - The String is a data type in Java, which is not a primitive type
 * - It's actually a Class, but it enjoys a bit of favoritism in Java to make it easier to use than a regular class
 * - In other words, it's treated a little bit differently, and you can treat it differently, than you'd treat a normal
 *   class
 *
 * What is a String ?
 * ..................
 * - A String is a sequence of characters.
 * - A large number of characters and technically, it's limited by memory, or the MAX_VALUE of an int which is
 *   2147483647
 * - That's a lot of characters that could potentially fit in a string
 *
 * Definition
 * ..........
 * - To define one, we use String variablename (capital S) and enclose the value with double quotes
 *      String myString = "This is a string";
 * - You can see it's quite similar as to a primitive type in terms of how we use it
 *
 * - We can append to that string automatically by using the plus (+) operator
 * - e.g
 *      myString = myString + ", and this is more";
 *
 * Next,
 * - We can also use Unicode characters
 *      myString = myString + "\u00A9 2019";
 *
 * - So, the String has got some versatility, we can use Unicode characters and regular characters, and also unlike
 *   the char, we can actually add a significant amount of characters as we'd like
 *
 * Next,
 * - Let's try something different now
 * - Suppose we add "250.55" + "49.95"
 *    - In fact here, we get "250.5549.95"
 * - In other words, 49.95 has been appended to the end of 250.55
 * - The reason for that is that we're not using the numeric type, such as an int or a long , or a double for that
 *    matter, but rather a text data type, String in this case
 * - And a String treats the text or digits that you type as text only
 *
 * A String deals with textual data only & it doesn't treat even numeric numbers that we've keyed there as numbers,
 *  but they're also treated as the textual representations
 *
 * Next,
 * - Let's add a String to a numeric data of type integer
 *  - Define a string lastString and initialize it to "10"
 *  - Define an integer myInt and initialize it to 50
 *  - Add lastString to myInt
 *  - Print the result
 *
 * - We don't get any errors at all, and the lastString is now "1050"
 * - So, even if we've used an integer data type as the second part, in other words appended, the integer to the
 *   string , the entire string lastString is still configured and set up as a String
 *
 * - Java is smart enough and it converts, or effectively looks at the contents of myInt, 50 , the value of it and
 *   converts the value 50, in this case to a String, then appends that to the 10
 * - And that's why we're seeing 1050 as the value of our lastString
 *
 * - Let's look at another example but this time use a double
 *  - Define a double variable and initialize it to 120.47d
 *  - Add it to the lastString
 * - Again here, 120.47 is appended to the contents of the lastString variable
 * - Has done exactly the same as the integer, it has treated the value as text and appended that as text to our
 *   string variable
 *
 *
 *
 * - We are doing a very elementary way of dealing with Strings, and adding to Strings
 * - In later lessons, we'll look at some more advanced features of a string, because we can do all sorts of things
 *   with it
 *    - We can delete characters out of a string
 *    - We can append characters, which we've seen
 *    - We can insert characters at specific positions
 * - There's lots of flexibility with strings, but we need to know more about classes before we get into too much
 *   detail
 *
 * Strings in Java are Immutable
 * ..............................
 * - When we say, that we can delete characters in a String, that's not strictly true.
 * - This is because Strings in Java are immutable - You can't change the contents of a String once it's created
 * - Instead, what happens behind the scene is a new String is created for us by java
 *
 * - So in the case, of our code, lastString doesn't get appended the value "120.47", instead a new String is created
 *   which consists of previous value plus a text representation of the double value 120.47
 *
 * - The net result is the same, lastString has the right values, however, a new String is created and the old one
 *   is discarded from memory automatically by Java
 *
 * The code we used to Append Strings was quite Inefficient
 * .........................................................
 * - As a result of a string being created, appending values like this is inefficient and not recommended
 * - We'll look ata better way in the future where we'll discuss something called StringBuffer which unlike
 *    the String can be changed
 *
 *
 * - From the point of view, you can treat a String as sort of like a 9th primitive type, however, to be completely
 *   clear, it's not a primitive type, it's a class
 * - But the java language has set the String to be used in an easy-to-use way compared to classes traditionally
 *   which we haven't covered yet, and we'll be using a lot of Strings as we progress through the course and in
 *   general when we're working life as a Java programmer
 *
 */
public class Main {

    public static void main(String[] args) {

        String myString = "This is a string";
        System.out.println("myString is equal to "+myString);

        myString = myString + ", and this is more.";
        System.out.println("myString is equal to "+myString);

        myString = myString + "\u00A9 2019";
        System.out.println("myString is equal to "+myString);

        String numberString = "250.55";
        numberString = numberString + "49.95";
        System.out.println(numberString ); //250.5549.95

        String lastString = "10";
        int myInt = 50;
        lastString = lastString + myInt;
        System.out.println("LastString is equal to "+lastString); //1050

        double doubleNumber = 120.47d;
        lastString = lastString + doubleNumber;
        System.out.println("LastString is equal to "+lastString); //1050120.47



    }
}
