package arrays_and_lists.part1_intro_to_arrays;
/*
 * Introduction to Arrays
 * ......................
 *
 * - Let's look at how we can store multiple values of the same type using Arrays
 *
 * - Arrays are data structures that allows you to store a sequence of values , that are all of the same type
 * - we can create :
 *      - an array of integers
 *      - an array of strings
 *      - an array of characters
 * - works for all primitive types and even objects like strings
 *
 * - we declare an array same way as a variable
 *      int myVariable = 50
 * - but slightly different since we need to add [] before the variable name as below
 *      int[] myIntArray;
 *
 * - the above is a standard definition, but we need to also determine the number of elements we want to assign to this
 *   array
 *      myIntArray = new int[10];
 * - if we wanted to assign 10 elements to this array, we pass the size
 *
 * - we can also combine the above into 1 line variable
 *      int[] myIntArray = new int[10];
 *
 * - we have defined an array that can hold 10 elements of type int
 *
 * Initializing an Array
 * .....................
 * - all elements in an array are indexed with values from 0, 1,2 3,... length - 1
 *
 * - the first element is indexed with 0 , if we want to assign a value to the first element or the last element,
 *   we w'd do something like this
 *      myIntArray[0] = 10;
 *      myIntArray[1] = 20;
 *      myIntArray[2] = 30;
 *      myIntArray[9] = 90;
 *
 * - The above can become tedious and there's a shortcut that Java gives us if we know ahead of time, the values we'd
 *   want to save in the array
 *
 * - we could do something like this:
 *      int[] myIntegersArray = {10,20,30,40,50,60,70,80,90,100};
 *
 * - we're telling Java to assign the numbers into myIntArray , and how many of them
 *      - java will automatically allocate 10 elements into that array and initialize them to the values passed to that
 *        array
 * - we can access different elements from this array using an index as below
 *       System.out.println(myIntegersArray[0]); - 10
 *       System.out.println(myIntegersArray[6]); - 70
 *       System.out.println(myIntegersArray[8]); - 90
 *
 * - We can also use a for loop to initialize an array as well
 *      int[] anotherIntArray = new int[10];
 *
 * - then use a for loop to access individual elements
 *
 * array.length
 * ............
 * - array.length property returns the size of the array specified & we can use it while looping instead of hard
 *   coding the size as we did with the for loops
 *
 * - it's recommended that we use array.length property because we can get into errors when we try to access element
 *   that are out of bound of the array [ ArrayIndexOutOfBoundsException ] Exception
 *      - results from referencing an element with an index that does not exist
 *
 * - also hard coding might also get us into errors because , if we change the size of the array, we'd have to do it
 *   in other places as well
 *
 * Accessing the elements stored in array
 * ......................................
 *
 * - if we want to access the first and the last element, we'd do something like below
 *      int firstElement = myIntArray[0];
 *      int lastElement = myIntArray[myIntArray.length - 1];
 *
 * - the concept is exactly the same for defining arrays for the other data types
 *      double[] myDoubleArray = new double[10];
 *      char[] myCharArray = new char[10];
 *      String[] myStringArray = new String[10];
 *
 * - we can also pass arrays to methods as parameters as well
 *  - refer to #Example-4
 *  - used a method to print the contents of the "anotherIntArray" array
 *  - call printArray(anotherIntArray);
 *

 *
 */
public class Main {

    public static void main(String[] args) {

        //Example-1
        int[] myIntArray = new int[10];
        myIntArray[0] = 10;
        myIntArray[5] = 60;
        myIntArray[9] = 100;

        //Example-2
        int[] myIntegersArray = {10,20,30,40,50,60,70,80,90,100};
        System.out.println(myIntegersArray[0]);
        System.out.println(myIntegersArray[6]);
        System.out.println(myIntegersArray[8]);
        System.out.println(myIntegersArray[9]);

        // Example-3
        int[] anotherIntArray = new int[10];
        for (int i = 0; i < anotherIntArray.length; i++) {
            anotherIntArray[i] = i * 10;
        }

       // for (int i = 0; i < anotherIntArray.length ; i++) {
       //     System.out.println("Element "+ i + " , value is "+anotherIntArray[i]);
       // }
        printArray(anotherIntArray);
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length ; i++) {
            System.out.println("Element "+ i + " , value is "+array[i]);
        }
    }
}
