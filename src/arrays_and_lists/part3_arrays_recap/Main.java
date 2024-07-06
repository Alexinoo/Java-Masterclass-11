package arrays_and_lists.part3_arrays_recap;

import java.util.Arrays;

/*
 * Arrays Recap
 * ............
 * - An Array is a data structure that allows you to store multiple values of the same type into a single variable
 * - The default values of numeric array elements are set to 0
 * - The default values of a double array elements are set to 0.0
 * - The default values of an array of string elements are set to null
 * - The default values of an array of boolean elements are set to false
 * - For example:
 *      int[] myIntArray = new int[5];  creates  [ 0 , 0 , 0 , 0 , 0 ]
 *
 *      double[] myDoubleArray = new double[5];  creates  [ 0.0 , 0.0 , 0.0 , 0.0, 0.0 ]
 *
 *      double[] myCharArray = new char[5];  creates  [  ,  ,  , ,  ]
 *
 *      boolean[] myDoubleArray = new boolean[5];  creates  [ false, false , false , false , false ]
 *
 *      String[] myStringArray = new String[5]; creates [ null, null , null , null , null ]
 *
 * - Arrays are indexed based
 *      - An array with n elements is indexed from 0 to n-1
 *      - For example:
 *          - 10 elements index range is from 0 to 9
 * - If we try to access index that is out of range, Java will throw an ArrayIndexOutOfBoundsException
 *      - indicates that the index is "out of range" in other words "out of bounds"
 * - To access array elements, we use the square braces [ and ] , also known as array access operator
 *
 *
 *
 * Example:
 *  - Creating an array
 *
 *      int[] array = new int[5];  [0,0,0,0,0]
 *
 *      - This array contains the elements from array[0] to array[4]
 *      - It has 5 elements and index range 0 to 4
 *      - The new operator or keyword is used to create the array and initialize the array elements to default values
 *      - All the array elements are initialized to 0, since this is an int array
 *
 *  - For boolean array elements, they would be initialized to false
 *  - For string or other objects,  they would be initialized to null
 *
 * - Initialize an Array
 *      int[] myNumbers = { 5, 4, 3, 2, 1 };
 *
 *      - We can also initialize an array inline by using array initializer block { and }
 *          - Values defined are separated by a comma
 *      - This way of initializing an array is also known as an anonymous array
 *      - It has 5 elements and index range 0 to 4
 *      - The array elements are initialized to 5,4,3,2,1
 *
 * - Let's look at a common errors that we're likely to encounter while working with arrays
 *
 * 1st Common Error
 * ...............
 *
 *      int[] myArray = { 10, 35 , 20 , 17 , 18 };
 *
 *      myArray[5] = 55;    // updating an element at specified index that is out of bounds
 *      myArray[-1] = 5;    // out of bounds
 *
 *      - Accessing index out of range will cause error in other words ArrayIndexOutOfBoundsException
 *      - We have 5 elements and index range is 0 to 4
 *          - Elements at index -1 throws ArrayIndexOutOfBoundsException
 *              - arrays can't have a negative index but can hold a negative value
 *
 *          - Elements at index 5 throws ArrayIndexOutOfBoundsException
 *
 *
 * 2nd Common Error
 * ................
 * - Consider the array below
 *
 *      int[] myArray = {10 , 35, 20, 17 , 18 };
 *
 *      for(int i = 1; i < myArray.length; i++){
 *          System.out.println("value= "+ myArray[i]);
 *      }
 *
 * - Array index starts from 0, so if we work with 1, the first element will not be printed
 *
 * - Therefore the output for this loop will be
 *          value = 35
 *          value = 20
 *          value = 17
 *          value = 18
 * - Change the variable to start from 0
 *
 * 3rd Common Error
 * ................
 * - Consider the array below
 *
 *      int[] myArray = {10 , 35, 20, 17 , 18 };
 *
 *      for(int i = 0; i <= myArray.length; i++){
 *          System.out.println("value= "+ myArray[i]);
 *      }
 *
 * - Using i <= myArray.length
 *
 * - The output will be
 *      value = 10 // printed when i = 0  // condition 0 <= 5
 *      value = 35 // printed when i = 1  // condition 1 <= 5
 *      value = 20 // printed when i = 2  // condition 2 <= 5
 *      value = 17 // printed when i = 3  // condition 3 <= 5
 *      value = 18 // printed when i = 4  // condition 4 <= 5
 *      value = myArray[5] ArrayIndexOutOfBoundsException when i = 5  since condition 5 <= 5 is true
 *
 *     -  value = myArray[5] is out of range/bounds
 * - we need to use the less than operator (<) so that the last condition will be (4 < 5)
 * - therefore 5 < 5 will evaluate to false and the code block will not be evaluated
 *
 * - (5 <= 5) will evaluate tor true and the code block is executed, though we don't have an element with an index 5
 *   and therefore an ArrayIndexOutOfBoundsException is thrown
 *
 * - Solution
 *      - Either use
 *          i <= myArray.length - 1
 *      - Or
 *          i < myArray.length
 *
 * - The above will work just fine
 *
 */
public class Main {

    public static void main(String[] args) {
        int[] myIntArray = new int[5];
        double[] myDoubleArray = new double[5];
        char[] myCharArray = new char[5];
        boolean[] myBooleanArray = new boolean[5];
        String[] myStringArray = new String[5];

        System.out.println(Arrays.toString(myIntArray)); // [0,0,0,0,0]
        System.out.println(Arrays.toString(myDoubleArray)); // [0.0 , 0.0 , 0.0 , 0.0, 0.0]
        System.out.println(Arrays.toString(myCharArray)); // [ ,  , , , ]
        System.out.println(Arrays.toString(myBooleanArray)); // [ false, false , false , false , false ]
        System.out.println(Arrays.toString(myStringArray)); // [ null, null , null , null , null ]
    }
}


