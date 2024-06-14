package first_steps.part9_primitive_types_challenge;

/*
 * Primitive Types Challenge
 * .........................
 *
 * - Create a byte variable and set it to any valid byte number - it doesn't matter, which one
 * - Create a short variable and set it to a valid short number
 * - Create an int variable and set it to a valid int number
 * - Create a long variable and make it equal to 50000 plus 10 times the sum of (byte + short + int) values
 *
 *
 * - Here, we don't need to do any casting because an integer value which these results  are assumed, to the right hand side
 *   (byteValue + shortValue + intValue), this is assumed to be an int value, but we've established that longs work quite nicely with int(s) and we
 *   don't need to do any casting
 *
 * - But, if we wanted to do something similar with a short, though not part of a challenge, we w'd do something like this
 *   e.g.   short shortTotal = (1000 + 10 * (byteValue + shortValue + intValue));
 *
 * - clearly we've got an error doing that
 *      - and the reason is we do have to cast because if we hover it, we get "Required short but found an int" and therefore, we need to be specific
 *        here and put a short at the front of the expression
 *
 *
 * - The point of this challenge is to suggest that integer is the best primitive data type for whole numbers to use generally
 * - But even if, you're using type long, we saw that Java handles a lot of complexity for us, and we don't have to do the casting with a long,
 *   because a long will happily accept an integer, because of course an integer , maximum integer value will always fit within a long value
 *
 */
public class Main {

    public static void main(String[] args){

        byte byteValue = 10;
        short shortValue = 20;
        int intValue = 50;
        long myLongValue = 50_000L + 10L * (byteValue + shortValue + intValue);

        System.out.println(myLongValue);

        short shortTotal = (short) (1000 + 10 * (byteValue + shortValue + intValue));



    }
}
