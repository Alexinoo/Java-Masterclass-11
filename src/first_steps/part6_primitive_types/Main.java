package first_steps.part6_primitive_types;
/*
 * Primitive Types
 * ................
 * - We've been working with int or Integer data type
 *      - Technically, that's known as a primitive type
 * - Let's look at some other primitive types available in Java
 * - In Java, primitive types are the most basic data types.
 * - The int is one of the eight primitive types
 * - The eight primitive data types in Java are:
 *      - boolean
 *      - byte
 *      - char
 *      - short
 *      - long
 *      - float
 *      - double
 * - Consider these types as the building blocks of data manipulation
 * - Let's explore the eight primitive types in Java
 *
 * Java Packages
 * .............
 * - A package is a way to organize your java projects
 * - For now, consider them as folders with learnprogramming in our example being a sub-folder of academy
 * - Company use their domain names reversed as a package
 * - So learnprogramming.academy becomes academy.learnprogramming
 *
 * Next,
 * - Lets create a variable of tye integer with an initial value of 10000
 * - The computer has allocated enough space to store an integer, and it has assigned a name for that variable of myValue
 *
 * But what actually is an integer ?
 * ................................
 * - Well, we know it's a whole number, but what can we store in an integer ?
 * - is the range infinite ?
 * - Well, it turns out there is a range, which is usually the case for most data types
 * - There is a minimum and maximum number that can be expressed or stored in an int
 *
 * Next,
 * - Let's write some code to determine the range of a primitive type
 *
 * N/B
 * - In terms of the double quotes, and the value is that we've entered the text that we want to display on the screen and then added a plus (+)
 * - What happens is when you start using a literal string, as used here, then we use the plus operator, whatever follows the plus operator is
 *   converted to a string and gets outputted
 * - It's a handy way to combine text, and in this case, numeric output
 * - It's not the best way to do things, but at this point in the course, it's perfectly acceptable
 *
 * Next,
 * We're using Integer instead of int.
 * That's because int is a primitive type that really gives us the option to set the variable's value
 * Integer on the other hand is what's called a Wrapper class
 *
 * Wrapper classes
 * ...............
 * - Java uses the concept of a Wrapper class for all 8 primitive types
 *      - In the case of an int, we can use Integer and by doing that it gives us ways to perform operations on an int
 *      - In this case we're using the MIN_VALUE and MAX_VALUE to get Java to tell us the minimum and maximum ranges of numbers that can be stored
 *
 * - So, MIN_VALUE, -2147483648, is the smallest value that we can set an int to
 * - An MAX_VALUE, 2147483647, is the biggest
 *
 * Next,
 * - Just to confirm that, let's try adding plus 1 to one of these value
 *   - Technically, this shouldn't be possible because we've already established that "2147483647" is the maximum value of an integer & we're in fact
 *     trying to add one it
 *
 * - We've assigned the maximum value plus one and what's happened is that the number "Busted MAX value = -2147483648" has turned to a minus number, to
 *   a negative number
 *      - This is called an Overflow, we tried to put a large number into the space allocated by the computer for an integer
 * - Now it didn't fit, so the computer tried to fit it anyway and by doing that it overflowed
 * - Now, the reverse is also true , by trying to deduct 1 off the minimum number i.e. (-2147483648 - 1), we've sort of cycled around to the maximum
 *   value
 *      - This concept is called an Underflow this time and not Overflow
 *
 * Overflow and Underflow
 * ......................
 * - If you try and put a value larger than the maximum value in Java, or a value smaller than the minimum value in Java, then you'll get an Overflow
 *   in the case of the maximum value or an Underflow in the case of the minimum
 * - The computer just skips back to the minimum number or the maximum number, which is usually not what we'd want
 * - This is an important concept that we need to be aware of
 *
 * - So clearly here, Overflow and Underflow is a bad thing
 * - We don't want that happening with our code, and there's a data type that holds much bigger numbers if we need them
 * - But ultimately, as a programmer, it's our responsibility to use the appropriate data type and ensure the range of numbers we're trying to store
 *   in that data type is within the range
 *   - in this case the min and max value
 *
 * - However, we're not able to initialize, an integer with a number that is bigger that the maximum value
 *  - This results to a compilation error, that the integer number is too large
 * - Because we've typed a literal value for an integer, & IntelliJ can figure out and know that , okay, that number is clearly out of the range of the
 *   maximum number that can be stored & we can flag it immediately
 * - Unlike what happened in line code 106 and 107 which really didn't tell us anything until we ran it
 *
 * - So any literal number typed without any other character at the end is assumed to be of type int
 *
 * N/B
 * ...
 * - We can also use underscore to make the number more readable
 *  - e.g. 2147483647 to 2_147_483_647
 * - We can enter it in this format, and this makes it easier to understand and read it at a glance
 * - Either way is acceptable
 *
 * - That said, you won't see a lot of code out there using the underscore approach but it is valid from Java 7 or higher
 * - So any version of the JDK, from version 7 or higher will work
 *
 */
public class Main {

    public static void main(String[] args) {
        int myValue = 10000;

        int myMinValue = Integer.MIN_VALUE;
        int myMaxValue = Integer.MAX_VALUE;
        System.out.println("Integer Minimum Value = "+myMinValue); // -2147483648
        System.out.println("Integer Maximum Value = "+myMaxValue); // 2147483647

        System.out.println("Busted MAX value = "+ (myMaxValue + 1)); //-2147483648  (Overflow)
        System.out.println("Busted MIN value = "+ (myMinValue - 1)); // 2147483648  (Underflow)

        //int myMaxIntTest = 2147483648;
        int myMaxIntTest = 2_147_483_647;
    }
}
