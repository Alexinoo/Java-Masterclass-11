package first_steps.part10_float_double_primitive_types;

/*
 * Float, and Double Primitive Types
 * .................................
 *
 * Floating Point Numbers
 * ......................
 * - Unlike whole numbers, floating point numbers have fractional parts that we express with a decimal point
 *    - e.g. 3.14159 is an example
 *
 * - Floating point numbers are also known as real numbers
 * - We use a floating point number when we need more precision in calculations
 *
 * - There are 2 primitive types for expressing floating point numbers
 *  - float
 *  - double
 *
 * - float is a single precision number while a double is double precision number
 *
 *
 * Single and Double Precision
 * ............................
 * - Precision refers to the format and amount of space occupied by the type
 * - Single precision occupies 32 bits ( so has a width of 32) and a Double precision occupies 64 bits ( and thus has a width of 64)
 *
 * float
 * ......
 *  - Size
 *      - A float typically occupies 4 bytes (32 bits) in memory in most programming languages, such as C, C++, and Java.
 *
 *  - Range and precision
 *      - The range of values that a float can represent is approximately 1.4E-45 to 3.4028235E38
 *      - The precision (the number of significant digits) is typically about 7 decimal digits.
 *
 *  - Floating-Point Representation
 *      - Floats are usually represented in a format specified by the IEEE 754 standard for floating-point arithmetic.
 *      - The 32-bit representation of a float consists of three main parts:
 *          - Sign Bit (1 bit) : Indicates the sign of the number (0 for positive, 1 for negative).
 *          - Exponent (8 bits): Represents the exponent of the number in a biased format.
 *          - Mantissa or Significand (23 bits): Represents the significant digits of the number.
 *
 *      Bit:  0  |  1  2  3  4  5  6  7  8     |  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                 |     Exponent (8 bits)       |                   Mantissa (23 bits)
                 |-----------------------------|--------------------------------------------------------|
           Sign: | Exponent:                   | Mantissa:
 *
 *
 * double
 * ......
 *  - Size
 *      - A double typically occupies 8 bytes (64 bits) in memory in most programming languages such as C, C++, and Java.
 *
 *  - Range
 *      - The range of values that a double can represent is approximately 4.9E-324 to 1.7976931348623157E308
 *      - The precision (the number of significant digits) is typically about 15-17 decimal digits.
 *
 *
 * - This means that the double has a much larger range and more precision than a float
 *
 * - We can see that double can work with a much bigger range of numbers, and it's a lot more precise than the float
 * - On the other hand, it needs twice amount of memory, 64-bits or 8 bytes to store that number compared to the float, which stores/requires 32 bits or
 *   4 bytes to store its number
 *
 * Next,
 * - Define 3 variables - int , float and double
 *
 *       int myIntValue = 5;
 *       float myFloatValue = 5;
 *       double myDoubleValue = 5;
 *
 * - In general, double data type is accepted much in the same way that the integer data type is accepted by default for whole numbers
 * - We can append an (f) for a float and a (d) for double & it's god practice to do that in general, if you're typing in literal numbers to make it
 *   abundantly clear of the meaning
 *
 *       int myIntValue = 5;
 *       float myFloatValue = 5f;
 *       double myDoubleValue = 5d;
 *
 * - The above works fine for a whole number, but you'll find that if we add a number .25 and if we remove (f) from myFloatValue, we'll actually get an
 *   error for the line below
 *
 *      float myFloatValue = 5.25;
 *
 * - The reason is that double is assumed to be the default floating point number, so we've got a similar problem we got with other data types
 * - If we hover the line above, we get "Required type float, provided double" - means that it's requiring a float, in this case a literal value , that
 *   we've typed in and it found a double
 * - So a floating number is assumed to be a double by Java
 *
 * Challenge
 * .........
 * - Thinking back to our Casting concept, which we used to convert both a byte and a short to the int equivalent,
 * - How do you think you would do the same for the float to remove the error ?
 *
 * Solution - use a cast
 *      float myFloatValue =(float) 5.25;
 * - The error now disappears because we've told Java that , yeah this is a double provided but treat it as a float
 *      - generally, not recommended to do that for 2 reasons
 *          - float aren't usually recommended to be used much these days
 *          - it's a lot clearer to rep float as 5.25f rather than (float)5.25 which is generally the most approach that most programmers would use
 *
 * - double is the preferred floating point data type to use
 *
 */
public class Main {

    public static void main(String[] args) {
        float myMinFloatValue = Float.MIN_VALUE;
        float myMaxFloatValue = Float.MAX_VALUE;

        System.out.println("Float Minimum value = "+ myMinFloatValue);
        System.out.println("Float Maximum value = "+ myMaxFloatValue);


        double myMinDoubleValue = Double.MIN_VALUE;
        double myMaxDoubleValue = Double.MAX_VALUE;

        System.out.println("Double Minimum value = "+ myMinDoubleValue);
        System.out.println("Double Maximum value = "+ myMaxDoubleValue);


        int myIntValue = 5;
        //float myFloatValue = 5.25; //Throws Casting error
        float myFloatValue =(float) 5.25; // solution
        double myDoubleValue = 5d;
    }
}
