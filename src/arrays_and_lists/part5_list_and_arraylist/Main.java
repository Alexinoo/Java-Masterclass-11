package arrays_and_lists.part5_list_and_arraylist;

import java.util.Scanner;

/*
 * List and ArrayList
 * ..................
 *
 * - We've looked at the process of
 *      - creating arrays,
 *      - sorting elements in an array
 *      - accessing elements
 *      - reversing elements in an array
 *      - get the minimum element in an array
 *      - copy the contents of 1 array to another array using a couple of different methods
 *
 * - Let's look at resizing an array
 * - Suppose you have an array that you have defined that holds 10 elements and say you want to change it to say 10
 *   elements, but still preserving the data in the existing array
 * - We can create a new array that can hold 12 elements but that again wouldn't preserve the data from the existing
 *   array
 *
 * _ Let's look at the concept of changing an array while preserving the data in a situation where you want to change
 *   the size of that array
 *
 * Example:
 *  - Define the scanner for accepting console input
 *  - baseData array with a size of 10 elements
 *
 * - We have the following methods
 *  - getInput()
 *      - gets the data from the console and save it into the baseData array initialized above
 *  - printArray(int[] array)
 *      - prints the contents of the array passed to it
 *  - resizeArray()
 *      - takes a copy of the original array
 *      - creates a new array with a size of 12 elements
 *      - use a loop that get each element from the original array that we took a copy of and then stores it back
 *         into the new array that we've created again
 *
 * - That's how you would go about resizing an array
 *      - copy the array first into another variable which in our case we've called it original
 *      - de-reference the older to a new-sized array with 12 elements
 *      - use a loop and copy the original values from the original array into a new array
 *
 * - We've now got the 12 array elements stored overriding the previous values
 *
 * - The concept is that we need to
 *      - store the original array
 *              int original = baseData;
 *
 *      - and then create a new one
 *              baseData = new int[12];
 *      - finally copy the original elements into the new one again
 *
 * - what we did is that we accepted the input a second time and overwrote the values
 * - if we wanted to update the existing
 *      - then we need to comment out the input from the user the second time
 *
 *           System.out.println("Enter 12 integers");
             getInput();
 *
 *      - and replace with
 *          baseData[10] = 67;
            baseData[11] = 34;
 *
 * - Now we've got the original array with 10 elements, and then a new array which has an extra 2 elements that we're
 *   saving
 *
 * - This is one concept of resizing an array , which as we can see, it can be quite messy, if you have to continually
 *   resize that array, which can be somehow unproductive to do that
 *
 * - This means there has to be a better way of actually accessing arrays using a List and an ArrayList
 *
 *
 *
 * List and ArrayList
 * ..................
 *
 * List
 *
 * - We can create lists, which is another way of creating an array,
 * - An array is a list in that, it's a sequence of numbers *
 * - A List is an ordered collection (also known as a sequence) and the user of this Interface has precise control
 *   over where in the list each element is inserted
 * - The user can access elements by their integer index (position in the list), and also search elements in the list
 *
 * - Unlike sets, lists typically allow duplicate elements
 *
 * - The List interface provides a special iterator, called a ListIterator, that allows insertion and replacement, and
 *   bidirectional access in addition to the normal operations that the Iterator interface provides
 *
 * - An ArrayList inherits from the List, and the List interface is pretty much like the list or arrays if you will and
 *   it's an ordered collection and allows you to have items in their respective positions
 *
 * - The List interface extends the Collection interface and Collection extends Iterable
 *
 * ArrayList
 *
 * - ArrayList inherits from an AbstractList which implements the List Interface
 * - An ArrayList is a resizeable array, handles the resizing automatically, handles the resize internally, not only
 *   does it maintain the size of how many elements, but also the capacity, the amount of memory that is reserved
 * - As elements are added to the ArrayList, its capacity grows automatically
 *
 * Let's look at an application that creates a grocery list for us using the ArrayList
 *  - We'll add some methods to
 *      - add items to the list
 *      - modify an item on the list
 *      - remove items
 *
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static int[] baseData = new int[10];
    public static void main(String[] args) {
        System.out.println("Enter 10 integers");
        getInput();
        printArray(baseData);
        resizeArray();

        //overwriting from user input
        //System.out.println("Enter 12 integers");
        //getInput();
        baseData[10] = 67;
        baseData[11] = 34;
        printArray(baseData);
    }

    private static void resizeArray() {
        int[] original = baseData;

        baseData = new int[12];
        for (int i = 0; i < original.length; i++) {
            baseData[i] = original[i];
        }
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void getInput() {
        for (int i = 0; i < baseData.length; i++)
            baseData[i] = scanner.nextInt();
    }
}
