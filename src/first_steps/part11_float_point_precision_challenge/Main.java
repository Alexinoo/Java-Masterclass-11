package first_steps.part11_float_point_precision_challenge;
/*
 * Floating Point Precision and a Challenge
 * ........................................
 *
 * - Define 3 variables - whole numbers, int, float and double with
 * - Print their values
 *      - int value prints a whole number
 *      - float and double print 5.0 and 5.0 values
 * - And that's because they're floating point numbers
 *
 * Next,
 * - Let's try to do some division to see what happens
 * - Divide by 2 for all the 3 variables
 *
 * - If we run it,
 *      - the int type has a got a value of 2
 *      - both float and double have got a value of 2.5
 * - Because the integer is a whole number, the remainder of 5/2 is ignored , and we're left with the number of times 2 goes into 5, which is 2
 * - But in the case of the float and double, we've got a much more precise answer, because they can handle the decimal point
 *
 * - And that's the reason why you would want to use a floating point number to be more precise with operations like divisions
 *
 * Next,
 * - Let's try to divide all the variable by 3, this time
 *
 * - When we run the code again
 *  - Again no surprises for the integer value, we get 1 and the remainder is ignored
 *  - But note for the float and double value
 *      - For the float we've got 7 digits after the decimal
 *      - FOr the double, we've got up to 16 digits with a last digit being a 7
 *
 * - Hopefully, it's clear now that the double is a lot more precise and has more precision than the float
 * - It's highly recommended to use double and we won't be using the float anymore in this course
 *
 * - Any floating point number code that we use, will be using a double going forward
 *
 * Challenge
 * .........
 * - Convert a given number of pounds to kilograms
 *
 * STEPS:
 *  - Create a variable with the appropriate type to store the number of pounds to be converted to kilograms
 *  - Calculate the result .i.e. the number of kilograms based on the contents of the variable above and store the result in a 2nd appropriate
 *     variable
 *  - Print out the result of your calculation
 *
 * Hint:
 *  - 1 pound is equal to 0.45359237 of a kilogram (This should help you perform the calculation)
 *
 */
public class Main {

    public static void main(String[] args) {

       // int myIntValue = 5;
       // float myFloatValue = 5f;
       // double myDoubleValue = 5d;

       // int myIntValue = 5 / 2;           // 2
       // float myFloatValue = 5f / 2f;     // 2.5
       // double myDoubleValue = 5d / 2d;   // 2.5

        int myIntValue = 5 / 3;             //1
        float myFloatValue = 5f / 3f;       // 1.6666666
        double myDoubleValue = 5d / 3d;     //1.6666666666666667

        System.out.println("MyIntValue = "+myIntValue);
        System.out.println("MyFloatValue = "+myFloatValue);
        System.out.println("MyDoubleValue = "+myDoubleValue);

        //double noOfPounds =

    }
}
