package arrays_and_lists.part4_reference_vs_value_types;

import java.util.Arrays;

/*
 * Reference Types vs Value Types
 * ..............................
 *
 * - Primitive Types
 *   - We've seen primitive types like int, double and boolean
 *   - all the primitive types are value types, they hold a value
 *
 * - Non-primitive types
 *   - an array is a reference type
 *   - a string is also a reference type
 *
 * - Let's look at some examples
 *
 * Value Types
 *      - Define 2 variables of type int ( myIntValue = 10, anotherIntValue = myIntValue )
 *      - initialize the second variable with the value for the first variable
 *          - in other words, both values have got the value of 10
 *      - print both values ( myIntValue = 10, anotherIntValue = 10 )
 *
 *      - increment anotherIntValue by 1
 *      - print both the variables again ( myIntValue = 10, anotherIntValue = 11 )
 *          - value of anotherIntValue is now 11
 *          - value of myIntValue is unchanged and still has the value of 10
 *
 *  - when we create an int variable, this value type, a single space in memory is allocated to store the value and that
 *    variable directly holds the value
 *  - if we assign it to another variable, the value is copied directly and both variable work independently
 *  - they've each got their own copy of a specific value, in this myIntValue = 10 , and anotherIntValue = 11
 *
 * Reference Types
 *  - Reference types such as arrays or classes work differently
 *  - We've seen what a reference is and how it works with the new operator
 *  - So whenever we see the new keyword, this means we're creating a new object
 *
 * - Lets look at an example with an array
 *
 *      int[] myIntArray = new int[5];
 *
 *      - this array has got 5 elements & we have a variable or in other words a reference
 *      - if we try to print this array as shown below , we'll get different references everytime we run this
 *          System.out.println(myIntArray); // [I@35f983a6
 *          System.out.println(myIntArray); // [I@7f690630
 *
 * - Arrays are reference types
 *   - a reference holds a reference or an address to the object but not the object itself
 *   - in other words "myIntArray" , holds a reference or an address to an array in memory
 *   - with reference types, we're using a reference to control the object in memory
 *   - therefore, we can't access the object directly
 * - A reference is like an address of some object in memory , "myIntArray" is actually a reference to the
 *   array in memory
 *
 * - Next create another array "anotherIntArray" and initialize it to myIntArray
 *
 *      int[] anotherArray = new int[5];
 *
 * - we've declared another reference to the same array in memory
 * - this means we've got 2 references pointing to the same array in memory
 * - both "myIntArray" and "anotherArray" hold the same address
 *
 * - So 1 way to know it's a reference type is by the use of a new operator or keyword , because that creates a new
 *   object in memory
 *
 *       int[] myIntArray = new int[5];
 *       int[] anotherArray = myIntArray;
 *
 *      - new here means new object
 *      - the second line , we haven't used a new keyword, we've just used the equal sign
 *
 * - To print them out, we need to use a useful static method from the Arrays class , which is built in to Java,
 *   called toString()
 * - Arrays.toString(int[] array) is a method built into Java, that prints out the contents of an entire array
 *
 * Arrays.toString()
 *  - joins multiple strings or objects into a string using a comma as a separator
 *  - we have an array of integers, and the toString() is going to convert every element in the array to a String
 *    and then return a new string where every element is separated nicely and we can print the whole array
 *  - you'll see how nicely it prints out an array
 *
 * Next,
 *  - Let's see what happens, if we make a change to the second array "anotherArray"
 *      anotherArray[0] = 1;
 *  - And print both arrays again
 *
 *  - What happens is that both were actually changed, both variables in other words are references to an array , or
 *    they're pointing to the same memory
 *  - And since we've updated the first element of "anotherArray" to 1, it automatically updated "myIntArray" and
 *    that's because both variables are referencing the same array in memory
 *  - So there's only 1 copy of the array that both variables are pointing to
 *
 * - Reference types represent the address of the variable rather than the data itself
 * - In our case, both variables represent the address pointing to the same array in memory
 * - So we've only gt 1 array in memory that both are pointing to
 *
 *
 * Next,
 * - The same behavior happens when we pass reference types to methods
 * - Create modifyArray(int[] array)
 *      - update array element 0 to value of 2
 * - Call it from the main() and pass "myIntArray"
 *      - modifyArray(myIntArray);
 * - So we're passing a reference type to modifyArray()
 *
 * - So what happens when we pass a reference type to a method ?
 *      - the address is copied to a parameter
 *      - now we got 3 references to the same array
 *          - the parameter "int[] array" is the 3rd reference to the array
 * - Let's print both references after the modifyArray() call
 *
 *       modifyArray(myIntArray);
 *       printArrayReferenceValuesChange(myIntArray , anotherArray);
 *
 * - The first element in both cases now holds the value of 2
 *      - This is because all the references point to the same array, we've just had 1 array in memory in this
 *        scenario
 *
 * - We may think a method can modify a reference by itself, but it can't
 *      - we can only de-reference it
 *
 * - Let's see what happens if we de-reference, or re-initialize the array that we received via a parameter to
 *   our method
 *
 *       //de-reference
        array = new int[]{1,2,3,4,5,};
 * - In this case, we're creating a new array, and so we have to use "new int[] {1,2,3,4,5}" , with the contents
 *   inside the { and } curly braces
 *
 * - You might think that the references in the main() , "myIntArray" and "anotherArray" will be able to see this
 *   change
 * - But they'll not be able to see it because we're de-referencing the reference, in other words, the parameter is
 *   a reference that now points to a different array since we used the new keyword
 *      - now it points to a new array with elements [1,2,3,4,5]
 *
 * - Nothing changes if we run this because "myIntArray" and "anotherArray" are still pointing to the original array
 *
 * - Let's de-reference another array before calling modifyArray
 *      anotherArray = new int[]{4,5,6,7,8};
 *
 *      - this now creates a new object in memory , and "myIntArray" and "anotherArray" point now to a different object
 *        in memory
 *      - now we've got 2 references and 2 different arrays in memory
 *          - we now got 2 different results because "anotherArray" no longer gets the change from the modifyArray()
 *            call because before calling the modifyArray(), we're de-referencing "anotherArray" reference and it now
 *            points to a different array in memory
 *          - it's because we've used the new keyword
 *                  anotherArray = new int[]{4,5,6,7,8};
 *
 * - We'll use references in the course more often & it will become easier as we continue to work with it
 *
 */
public class Main {

    public static void main(String[] args) {
        int myIntValue = 10;
        int anotherIntValue = myIntValue;

        printValues(myIntValue , anotherIntValue);

        anotherIntValue++;

        printValues(myIntValue , anotherIntValue);


        int[] myIntArray = new int[5];
        System.out.println(myIntArray); // [I@35f983a6 ; [I@7f690630

        int[] anotherArray = myIntArray;
        System.out.println(anotherArray); //[I@35f983a6 ; [I@7f690630

        printArrayReferenceValues(myIntArray , anotherArray);

        anotherArray[0] = 1;
        printArrayReferenceValuesChange(myIntArray , anotherArray);

        anotherArray = new int[]{4,5,6,7,8};
        modifyArray(myIntArray);
        printArrayReferenceValuesModify(myIntArray , anotherArray);

    }

    public static void printValues(int myIntValue , int anotherIntValue){
        System.out.println("myIntValue = "+myIntValue);
        System.out.println("anotherIntValue = "+anotherIntValue);
    }

    public static void printArrayReferenceValues(int[] myArray , int[] anotherArray){
        System.out.println("myIntArray = "+ Arrays.toString(myArray));
        System.out.println("anotherArray = "+Arrays.toString(anotherArray));
    }

    public static void printArrayReferenceValuesChange(int[] myArray , int[] anotherArray){
        System.out.println("after change - myIntArray = "+ Arrays.toString(myArray));
        System.out.println("after change - anotherArray = "+Arrays.toString(anotherArray));
    }

    public static void printArrayReferenceValuesModify(int[] myArray , int[] anotherArray){
        System.out.println("after modify - myIntArray = "+ Arrays.toString(myArray));
        System.out.println("after modify - anotherArray = "+Arrays.toString(anotherArray));
    }

    private static void modifyArray(int[] array){
        array[0] = 2;

        //de-reference
        array = new int[]{1,2,3,4,5,};
    }
}
