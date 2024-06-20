package java_tutorial.part10_method_overloading_challenge;
/*
 * Method Overloading Challenge
 * ............................
 * - Create a method named calcFeetAndInchesInCentimeters
 *      - Needs 2 parameters of type double
 *          - feet is the 1st parameter
 *          - inches is the 2nd parameter
 *
 *      - Validate both parameters
 *          - 1st parameter needs to be >= 0
 *          - 2nd parameter needs to be >= 0 and <= 12
 *          - return -1 if either of the above is true
 *
 * - If valid,
 *      - then calculate how many centimeters comprise the feet and inches passed to this method and return
 *          a value
 *
 * - Create a 2nd method of the same name
 *      - Needs only 1 parameter
 *          - inches is the parameter
 *      - Validate this parameter
 *          - needs to be >= 0
 *          - return -1 if it is not true
 *
 * - If it's valid,
 *      - then calculate how many feet are in the inches and then here is the tricky part
 *
 * - call the other overloaded method passing the correct feet and inches calculated so that it can calculate correctly
 *
 *
 * Hints:
 *  - use double for your number data types
 *  - 1 inch = 2.54cm and 1 foot = 12 inches
 *
 *  - us the link in the resource section to confirm your code is calculating correctly
 *  - calling another overloaded method just requires you to use the right number of parameters
 *
 */
public class Main {

    public static void main(String[] args) {
         calcFeetAndInchesInCentimeters(7,5);
         calcFeetAndInchesInCentimeters(-10,5);
         calcFeetAndInchesInCentimeters(0,1);
         calcFeetAndInchesInCentimeters(6,-10);
         calcFeetAndInchesInCentimeters(6,13);
         calcFeetAndInchesInCentimeters(6,0);

         calcFeetAndInchesInCentimeters(500);
    }

    public static double calcFeetAndInchesInCentimeters(double feet, double inches){
        if (feet < 0 || (inches < 0 || inches > 12)){
            System.out.println("Invalid feet or inches parameters");
            return -1;
        }
        double totalInches = (feet * 12) + inches;
        double centimeters = totalInches * 2.54;
        System.out.println(feet + " feet, " + inches+ " inches = " +centimeters + " cm");
        return centimeters;
    }

    public static double calcFeetAndInchesInCentimeters(double inches){
        if (inches < 0){
            return -1;
        }

        long feetInInches = (long) (inches / 12);
        double remainingInches = (inches % 12);
        return calcFeetAndInchesInCentimeters(feetInInches , remainingInches);
    }
}
