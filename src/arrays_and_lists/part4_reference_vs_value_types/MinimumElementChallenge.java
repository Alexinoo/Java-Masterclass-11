package arrays_and_lists.part4_reference_vs_value_types;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Minimum Element Challenge
 * .........................
 * - Write a method called readIntegers() with a parameter called count that represents how many integers the user needs
 *   to enter
 * - The method needs to read from the console until all the numbers are entered, and then return an array containing
 *    the numbers entered
 * - Write a method findMin() with the array as a parameter. The method needs to return the minimum value in the array
 *
 * - In the main(), read the count from the console and call readIntegers() with the count parameter
 * - Then call the findMin() passing the array returned from the call to readIntegers()
 *
 * - Print the minimum element in the array returned from the findMin()
 *
 * Tips:
 *  - Assume that the user will enter numbers, never letters
 *  - Create a Scanner as a static field to help with data input
 */
public class MinimumElementChallenge {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter the number representing how many elements yu want to work with: \r");
        int count = scanner.nextInt();

        int[] returnedArray = readIntegers(count);
        System.out.println(Arrays.toString(returnedArray));

        int min = findMin(returnedArray);
        System.out.println("minimum value = "+min);
    }

    private static int findMin(int[] myNumbers) {
        // Solution 1
        int min = Integer.MAX_VALUE;                     // min = 2147483648
        for (int i = 0; i < myNumbers.length; i++) {     //[10,20,30,40,50]
            int value = myNumbers[i];                // nextValue = 10 ; nextValue = 20; nextValue=30;
            if ( value < min){                       // 10 < 2147483647; 20 < 10 false ; 30 < 10 false
                min = value;                         // min = 10       ; min = 10      ; min =
            }
        }

        // Solution 2
//        int min = 0;
//        boolean isFirst = true;
//        for (int i = 0; i < myNumbers.length-1; i++) {
//            if (isFirst){
//                min = myNumbers[i];
//                isFirst = false;
//            }
//            if ( myNumbers[i+1] < min ){    //20 < 10
//                min = myNumbers[i+1];
//            }
//        }

        return min;
    }

    private static int[] readIntegers(int count){
        System.out.println("Enter "+count+ " numbers \r");
        int[] myArray = new int[count];
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = scanner.nextInt();
        }
        return myArray;
    }
}







