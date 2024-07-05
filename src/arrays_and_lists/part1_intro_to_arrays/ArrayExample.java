package arrays_and_lists.part1_intro_to_arrays;

import java.util.Scanner;

/*
 * Use case Example:
 * .................
 *  - Accept some input from the user ( 5 numbers)
 *  - some up the total numbers and get the average of the numbers that were typed in
 *
 * Solution
 *  - Define scanner variable as a private static variable - to be shared
 *
 *  - Create an array of integers variable "myIntegers"
 *
 *  - Create getIntegers(int number)
 *      - that allows us to type in 5 numbers and return them in a form of an array of integers
 *      - initialize values[] array to the size passed to this method
 *      - use a for loop to get input from the user and store the numbers into values[i] using scanner.nextInt()
 *      - return the values[]
 *
 *  - Create getAverage(int[] array)
 *      - pass the array of the integers you want to calculate average from
 *      - define a sum variable and loop through the array adding each element to the sum variable
 *      - calculate the average by dividing the sum by the total size of the array
 *          - Cast both the sum and array.length to double
 *
 * - main()
 *      - loop through myIntegers[]
 *      - call getAverage(myIntegers) and pass the arrays of integer returned from getIntegers(int number)
 *
 */
public class ArrayExample {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int[] myIntegers = getIntegers(5);

        for (int i = 0; i < myIntegers.length; i++) {
            System.out.println("Element "+ i + ", typed value was "+ myIntegers[i]);
        }

        System.out.println("The average is "+getAverage(myIntegers));
    }

    public static int[] getIntegers(int number){
        System.out.println("Enter "+ number + " integer values. \r");
        int[] values = new int[number];
        for (int i = 0; i < values.length; i++) {
            values[i] = scanner.nextInt();
        }
        return values;
    }

    public static double getAverage(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return (double) sum /  (double) array.length;
    }
}
