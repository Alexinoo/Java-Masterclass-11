package java_tutorial.part8_coding_exercises;
/*
 * Speed Converter
 * ...............
 *
 * 1. Write a method called "toMilesPerHour" that has 1 parameter of type "double" with the name "kilometersPerHour"
 *      - The method needs to return the rounded value of the calculation of type "long"
 *      - If the parameter, "kilometersPerHour" is "less than 0", the method "toMilesPerHour" needs to "return -1"
 *      - Otherwise, if it is "positive", calculate the values of miles per hour, round it and return it.
 *
 *      For conversion and rounding, check the notes in the text below
 *
 *      Examples of input/output
 *      ........................
 *      toMilesPerHour(1.5); - should return value 1
 *      toMilesPerHour(10.25); - should return value 6
 *      toMilesPerHour(-5.6); - should return value -1
 *      toMilesPerHour(25.42); - should return value 16
 *      toMilesPerHour(75.114); - should return value 47
 *
 * 2. Write another method called printConversion with 1 parameter of type "double" with the name "kilometersPerHour"
 *      - This method should not return anything(void) and it needs to calculate "milesPerHour" from the "kilometersPerHour"
 *          parameter
 *      - Then it needs to print a message in the format "XX km/h = YY mi/h"
 *
 *      - Where
 *          XX represents the original value kilometersPerHour
 *          XX represents the rounded milesPerHour from the kilometersPerHour parameter
 *      - If the parameter kilometersPerHour is < 0, then print the text "Invalid Value"
 *
 *      Examples of input/output
 *      printConversion(1.5); should print "1.5 km/h = 1 mi/h"
 *      printConversion(10.25); should print "10.25 km/h = 6 mi/h"
 *      printConversion(-5.6); should print "Invalid Value"
 *      printConversion(25.42); should print "25.42 km/h = 16 mi/h"
 *      printConversion(75.114); should print "75.114 km/h = 47 mi/h"
 *
 *
 * N/B
 * ...
 * - Use Math.round to round the number of calculated miles per hour (double). The method round returns a long
 *
 * Math.round()
 * ............
 * - Math.round() is a built-in method which returns the closest long method to the argument
 * - The result is rounded to an integer by adding 1/2, taking the floor of the result after adding 1/2, and typecasting the result to type long
 * - The () returns the value of the argument rounded to the nearest "int" value
 * - returns a long datatype
 *
 * Examples
 *  1.5 will be rounded to 2
 *  3.9 will be rounded to 4
 *  4.5 will be rounded to 5
 *
 *
 * Tip
 * ...
 * - In the method printConversion, call the method toMilesPerHour instead of duplicating the code
 * - 1 mile per hour is 1.609 kilometers per hour
 *
 */
public class SpeedConverter {

    public static void main(String[] args) {

        printConversion(1.5);
        printConversion(10.25);
        printConversion(-5.6);
        printConversion(25.42);
        printConversion(75.114);
        printConversion(10.5);

        System.out.println(".".repeat(50));
    }

    public static long toMilesPerHour(double kilometersPerHour){
        if (kilometersPerHour < 0){
            return -1;
        }
        return Math.round(kilometersPerHour / 1.609344);
    }

    public static void printConversion(double kilometersPerHour){
        if (kilometersPerHour < 0){
            System.out.println("Invalid value");
            return;
        }
        long milesPerHour = toMilesPerHour(kilometersPerHour);
        System.out.println(kilometersPerHour+" km/h = " +milesPerHour + " mi/h");
    }
}
