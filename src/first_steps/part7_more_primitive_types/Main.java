package first_steps.part7_more_primitive_types;

/*
 * byte, short, long and width
 * ...........................
 *
 * - Let's look at some additional whole number primitive types
 *
 * byte
 * .........
 * - Call MAX_VALUE on Byte Wrapper class to determine the Maximum value - (127)
 * - Call MIN_VALUE on Byte Wrapper class to determine the Minimum value - (-128)
 *
 * - We can see that for byte, the range is quite small
 * - The minimum value you can go down to is - 128 and the maximum value is 127
 * - The range is quite small and frankly, ypu probably won't be using the byte data type a lot
 *
 * short
 * .....
 * - Call MAX_VALUE on Short Wrapper class to determine the Maximum value - (-32768)
 * - Call MIN_VALUE on Short Wrapper class to determine the Minimum value - (32767)
 * - We've printed the minimum and maximum values that a short data type can contain
 * - We can see that the short minimum value is -32768
 * - And the maximum value for a short value is 32767
 *
 * - Both the byte and the short have the same overflow and underflow issue, with their own range of numbers
 *
 * Let's have a talk of how much space each of the data types we've talked  to take up in memory
 *
 * Size of Primitive Types and Width
 * .................................
 *
 * ///////////////////////////////////////////////////////////////////////////////////////////////
 * byte
 * ///////////////////////////////////////////////////////////////////////////////////////////////
 * - A byte is composed of 8 bits
 * - A bit is not directly represented in a primitive type
 * - A byte occupies 8 bits
 *   - We say that a byte has a width of 8
 *
 *      | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
 *      ---------------------------------
 *      | 1 | 0 | 1 | 0 | 1 | 1 | 0 | 1 |
 *
 * /////////////////////////////////////////////////////////////////////////////////////////////////
 * short
 * /////////////////////////////////////////////////////////////////////////////////////////////////
 * - Size
 *      - A short typically occupies 2 bytes (16 bits) in memory
 *      - Has a width of 16
 *
 * - Range
 *      - For a signed short (which can represent both positive and negative numbers), the range is
 *      - i.e. -2 to the power of 15 up to 2 to the power of 15
 *      - from -32,768 to 32,767
 *
 * - Graphical representation
 *
 *      Bit:  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 *            |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
 *            1  0  1  0  1  1  0  1  0  1  1  0  1  0  1  1
 *
 * ///////////////////////////////////////////////////////////////////////////////////////////
 * int
 * ///////////////////////////////////////////////////////////////////////////////////////////
 * - Size
 *      - An int is usually 4 bytes (32 bits) in size
 *      - Has a width of 32
 *
 * - Range
 *      - the range is from ( -2^31 to 2^31)
 *      - i.e. -2,147,483,648 to 2,147,483,647
 *
 * - Graphical representation
 *
 *   Bit:  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
 *         |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
 *         1  0  1  0  1  1  0  1  0  1  1  0  1  0  1  1  0  1  0  1  1  0  1  0  1  1  0  1  0  1  1  0
 *
 *
 * - The point here is that each primitive type occupies a different amount of memory
 *      - we can see that an int needs 4 times the amount of space than a byte for example
 *
 * - It's not particularly relevant for us to know these no's but they could come up as an interview question & it is useful to know that certain
 *   data types take up more space than others
 *
 * - There's one more data type and that's used where you've got a number that you want to process that's larger than the amount for an int
 * - We call it long variable
 *
 *
 * ///////////////////////////
 * long
 * ///////////////////////////
 *
 * - When assigning a long value, you need to put a letter l at the end of it, and that tells the computer that it's a long value
 *      long myLongValue = 100;
 *
 * - However the lower case l can be confusing as it looks like a 1
 *      long myLongValue = 100l;
 *
 * - It's therefore recommended we use capital Ll (L) , to make it clearly stand out for that matter
 *      long myLongValue = 100L;
 *
 * - Size
 *      - A long occupies 8 bytes (64 bits)
 *      - This is double the size of an int which has a width of 32 bits
 *
 * - Range
 *      - For a signed long: (-2^63 to 2^63)
 *      - i.e. from ( -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807)
 *
 * - The actual number we can store is huge, though in a long primitive type
 *   - It's actually 2 to the power of 63
 *
 * - It's a pretty big range of numbers, and it still has got the same type of overflow and underflow as we've seen for the other primitive types
 *
 * - By default, Java considers a number you type as an integer, unless you add a letter on the end as we did above on line 102
 * - This forces Java to treat that numbers as a long
 *
 * - You might be wondering why a declaration worked without an L, when we first started, we didn't put an L there & we never got an error after
 *   removing the L
 * - Well, the reason is that the in fits fully into a long, because a long is twice the width of an int
 * - So Java is smart enough to know that & it just converts an int to a long for us instead of giving us an error
 *
 * - However, if we try and type in a literal value, for a long without the L, we will get an error
 * - e.g.
 *       long bigLongLiteralValue = 2_147_483_647
 * - The above works, we know that's the maximum integer number, and so IntelliJ is quite happy to accept that
 *
 * - But if we change that, and put an underscore 234 , we get an error
 * - e.g.
 *       long bigLongLiteralValue = 2_147_483_647;  // OK
 *       long bigLongLiteralValue = 2_147_483_647_234; // Error
 *       long bigLongLiteralValue = 2_147_483_647_234L; // OK
 *
 * - Java still treats long bigLongLiteralValue = 2_147_483_647_234; as an integer
 * - If we put an L there to treat is as a long , this works and the error disappears
 *
 * Next,
 * - Java is also smart when it comes to converting numbers from int to say, a short,
 * - So it knows if the literal value we're going to use is not going to fit into a short
 * - e.g.
 *      short bigShortLiteralValue = 32768;
 * - This clearly gives us an error since 32767 is the maximum value for a short and it's saying incompatible types required, a short, found an int
 * - But if we change it to below
 *      short bigShortLiteralValue = 32767
 * - This now becomes valid, and we don't have the error - the number can now directly fit into a short
 *
 *
 *
 *
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


        /////////////////////////////////////////////////////////////////
        //// byte
        ////////////////////////////////////////////////////////////////

        byte myMinByteValue = Byte.MIN_VALUE;
        byte myMaxByteValue = Byte.MAX_VALUE;
        System.out.println("Byte Minimum Value = "+ myMinByteValue); // -128
        System.out.println("Byte Maximum Value = "+ myMaxByteValue); // 127

        /////////////////////////////////////////////////////////////////
        //// short
        ////////////////////////////////////////////////////////////////

        short myMinShortValue = Short.MIN_VALUE;
        short myMaxShortValue = Short.MAX_VALUE;
        System.out.println("Short Minimum Value = "+ myMinShortValue); // -32768
        System.out.println("Short Maximum Value = "+ myMaxShortValue); // 32767





        ///////////////////////////////////////////////////////////////
        //// long
        //////////////////////////////////////////////////////////////

        long myLongValue = 100L;

        long myMinLongValue = Long.MIN_VALUE;
        long myMaxLongValue = Long.MAX_VALUE;
        System.out.println("Long Minimum Value = "+ myMinLongValue); // -9223372036854775808
        System.out.println("Long Maximum Value = "+ myMaxLongValue); // 9223372036854775807

        long bigLongValue = 2_147_483_647; // Ok - Fits
       // long bigLongLiteralValue = 2_147_483_647_234; // Error - Integer number too large
        long bigLongLiteralValue = 2_147_483_647_234L; // Works

        System.out.println(bigLongLiteralValue);
    }
}