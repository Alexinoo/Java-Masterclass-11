package arrays_and_lists.part4_reference_vs_value_types;

import java.util.Arrays;

/*
 * Reverse Array Challenge
 * .......................
 * - Write a method called reverse() with an int array as a parameter
 * - The method should not return any value
 *      - In other words, the method is allowed to modify the array parameter
 * - In main(), test the reverse() method and print the array both reversed and non-reversed
 * - To reverse the array, you have to swap the elements, so that the first element is swapped with the last element
 *    and so on
 * - For example:
 *      - If the array is {1,2,3,4,5} ,  then the revered array is {5,4,3,2,1}
 *
 * Solution
 * ........
 * - Write reverse()
 * - concept
 *      - swap the first element with the last element
 *      - swap the 2 element with the second last and so on..
 *
 * - Initialize the lastIndex to array.length - 1
 * - Initialize halfLength which will rep the half of the array length
 *    - Use a for loop
 *      - loop through the array and continue swapping until we get to the halfway point of the array
 *          - once we reach halfway, the second half has already been swapped with the first and the array
 *              is fully reversed at that point
 *          - swap 2 elements by the help of a temporary variable
 *
 */
public class ReverseArrayChallenge {

    public static void main(String[] args) {
        int[] myIntArray = {1,2,3,4,5,6,7};

        System.out.println(Arrays.toString(myIntArray));
        reverse(myIntArray);

        System.out.println(Arrays.toString(myIntArray));
    }

    private static void reverse(int[] array) {
        int halfLength = array.length / 2;
        int lastIndex = array.length - 1;

        for (int i = 0; i < halfLength; i++) {
            int temp = array[i];
            array[i] = array[lastIndex - i];
            array[lastIndex - i] = temp;
        }
    }

}
