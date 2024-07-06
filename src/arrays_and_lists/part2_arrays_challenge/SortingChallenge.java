package arrays_and_lists.part2_arrays_challenge;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Sort Array in Descending order
 * ..............................
 * - Create a program using arrays that sorts a list of integers in descending order
 * - For example:
 *      - if the array had the values : [106 , 26, 81, 5 , 15]
 *      - the program should sort the array to : [106 , 81 , 26, 15 , 5]
 * - Set up the program so that the numbers to sort are read in from the keyboard
 * - Implement the following methods:
 *      - getIntegers(int number) - returns an array of entered integers from keyboard
 *      - printArray(int[] array) - prints out the contents of the array
 *      - sortIntegers(int[] array) - sort the array and return a new array containing the sorted numbers
 * - Figure out how to copy the array elements from the passed array into a new array and sort them and return the new
 *   sorted array
 *
 * Solution
 * ........
 *
 * Sorting
 *  - copy the contents of the array passed into copiedArray using 2 ways
 *      - use Array,copyOf(int[] originalArray, int length)
 *
 *          int[] copiedArray = Arrays.copyOf(array,array.length);
 *
 *      - define a new array of the same length and use a for loop to initialize the contents
 *         of the passed array into the new array
 *              int[] copiedArray = new int[array.length];
 *              for(int i =0; i < array.length; i++){
 *                  copiedArray[i] = array[i];
 *              }
 *
 *  - initialize a boolean flag and set it to true
 *  - create a while loop and then nest a for loop
 *      - set boolean flag to false
 *          - if the for loop hasn't sorted all the numbers, set it to true, so that the while continues looping
 *          - while loop will continue processing until the flag is false
 *      - we're setting it to false, so that if we don't reset flag to true, we're gonna automatically end the while loop
 *      - inside for loop
 *          - compare the first element with the second element in the array
 *              - if condition is true,
 *                  - swap - create a temporary temp variable
 *                  - set flag to true
 *  - return sorted array
 *
 * Example:  [ 50, 160, 40 ]
 * - flag set to true
 *   - while loop runs
 *      - flag set to false
 *      - inside for loop
 *         i = 0
            if( 50 < 160 )
                swap(50,160)      [160 , 50 , 40]
                flag= true
           i = 1
            if(50 < 40 )          [160 , 50, 40 ]
                false
        - flag is true

     - while loop run again      [160 , 50 , 40 ]
        - flag set to false
        - inside for loop
            i = 0
              if(160 < 50)       [160 50 ,40 ]
                false
            i = 1
              if (50 < 40)       [160 50 ,40 ]
                false
        - flag is false

     - while loop exits
 */
public class SortingChallenge {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int[] myIntArray = getIntegers(5);

        printArray(myIntArray);

        int[] sortedArray = sortIntegers(myIntArray);

        System.out.println();
        System.out.println(Arrays.toString(sortedArray));

    }

    public static int[] getIntegers(int number){
        System.out.println("Enter "+ number+ " numbers \r");
        int[] numbersArray = new int[number];
        for (int i=0; i < numbersArray.length; i+=1){
            numbersArray[i] = scanner.nextInt();
        }
        return numbersArray;
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static int[] sortIntegers(int[] array){
       int[] copiedArray = Arrays.copyOf(array,array.length);

       boolean swapped = true;
       while (swapped){
           swapped = false;
           for (int i = 0; i < copiedArray.length - 1; i++) {
               if (copiedArray[i] < copiedArray[i+1]){
                   int temp = copiedArray[i];
                   copiedArray[i] = copiedArray[i+1];
                   copiedArray[i+1] = temp;
                   swapped = true;
               }
           }
       }
       return copiedArray;
    }
}
