package first_steps.part8_casting;

/*
 * Casting In Java
 * ...............
 * - Let's look at Arithmetic with these data types and then a concept known as Casting
 *
 * Example
 * - Assign Integer.MIN_VALUE to "myMinIntValue"
 * - Divide "myMinIntValue" by 2 and assign to "myTotal"
 *      - No problems with that
 *
 *
 * - Assign Byte.MIN_VALUE to "myMinByteValue"
 * - Divide Byte.MIN_VALUE by 2 and assign the result to "myNewByteValue"
 *      - Again no problem with that
 *
 * - But if we divide "myMinByteValue" by 2 and assign the result to "anotherNewByteValue"
 *  - Suddenly we get an error
 *  - So why do we get an error ?
 *
 * - We know that if we use a literal value and the number fits, then there's no problem , and clearly here now we know that we're using the
 *   MINIMUM byte value divided by 2 which results to -64
 *
 * - This would clearly fit normally in a byte data type
 * - But here we've got an expression that uses a variable that's been divided by 2 - and that's the difference compared to what we've done previously
 *   when we've used a literal value
 * - Well, the problems comes about because the default whole number used by Java is an int, and that's why we've got an error here
 * - And to see that , if we hover it, that really gives it away
 * - It says, required byte, so it's looking for a byte to store the value into our byte variable
 * - So basically, what's in parentheses is treated as an int by the computer and that's why we're getting this error
 *      - In this case (- 64)
 * - We know (-64) is a number that will fit into the byte range.
 *
 * So how do we tell Java that ?
 * .............................
 * - Well, we do that by a concept called Casting
 *
 *
 *
 *
 * Casting in Java
 * ...............
 * - Casting means to treat or convert a number from one type to another.
 * - We put the type we want the number to be in parentheses as below
 * - e.g.
 *      (byte)(myMinByteValue / 2);
 * - So we put (byte) in front of our expression (myMinByteValue / 2)
 *
 * N/B
 * - Also not that other programming languages have casting, this is not a Java thing
 *
 * - In short, we're telling Java to treat this value as a byte, instead of a default which is an integer and the error now disappears
 *
 *
 * - We can also do the exact thing for a short
 *
 * - Generally speaking, an integer is the whole number you'll probably want to use, and you can probably guess that because Java is using an int by
 *   default, and that's more or less telling us that that's what it's expecting to use most of the time
 *
 * - So, if you don't specify a type, that you actually want to convert to, then the integer is assumed automatically by Java, and in that case, we got
 *   an error because of that
 *
 * - In below case
 *      int myTotal = (myMinIntValue / 2);
 * - we didn't have an issue in the above because the result is also an int
 *
 * In general, always use an integer, unless you've got a really good reason not to do that
 *
 */
public class Main {

    public static void main(String[] args) {

        int myMinIntValue = Integer.MIN_VALUE; // -2147483648
        int myTotal = (myMinIntValue / 2) ; // (-2147483648 / 2)

        byte myMinByteValue = Byte.MIN_VALUE; // -128
        byte myNewByteValue = (Byte.MIN_VALUE / 2); //(-128 / 2 = -64)

        //byte anotherNewByteValue = (myMinByteValue / 2); //(-128 / 2 = -64) : -64 is an int
        byte anotherNewByteValue = (byte) (myMinByteValue / 2); // byte(-64)

        System.out.println(myTotal);
        System.out.println(myNewByteValue);
        System.out.println(anotherNewByteValue);

        short myMinShortValue = Short.MIN_VALUE; // -32768
        short newShortValue = (Short.MIN_VALUE / 2); // - 16384 (short)
        short myNewShortValue = (short) (myMinShortValue / 2); //(short) -16384

        System.out.println(myNewShortValue);

    }
}
