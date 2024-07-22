package generics.part1_intro;

import java.util.ArrayList;

/*
 * Generics
 *
 * - When we provide a type parameter to a generic type, this is called a Parameterized type
 * - We specialize the type using <> diamond operator, or angle brackets
 *
 * - So , we'll specify an integer type when we declare an ArrayList and also on parameter on printDouble() method *
 * - In other words, we're specifying the type that the ArrayList is going to hold
 *      ArrayList<Integer> items = new ArrayList<>();
 *
 * - And immediately , we get an error and not a warning anymore for this line below
 *       items.add("alex");
 *  - Which tells us that the ArrayList is expecting an Integer & we're providing a String object
 *
 * - However, adding a type here
 *      new ArrayList<>();
 * - is unnecessary because Java has got enough info in the declaration
 *       ArrayList<Integer> items
 * - as shown above
 *
 * - So by telling ArrayList what types of object(s) we wanted it to hold, the program now fails to compile when we try to add the value "alex"
 *
 * - In the printDouble(), we can now get rid of the cast now, and we can also change the Object to an Integer now because we are damn sure that
 *   our ArrayList hold values of type int
 *      - we can either work with Integer
 *
 *         for (int item : n){
                System.out.println( item * 2);
           }
 *
 *      - or just int
 *
 *          for (int item : n){
                System.out.println( item * 2);
            }
 *
 * - Obviously, we don't need type Object, now because we already know what kind of data our ArrayList holds
 * - And Java is smart enough to know that ok, you're using an ArrayList<Integer> and it can now convert it back to an int and java will handle
 *   the Autoboxing and Unboxing automatically for us
 *
 * - And, if we run this code, we'll find that it actually works, and we get the same results
 */
public class WithGenerics {
    public static void main(String[] args) {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(5);

       // items.add("alex"); // error

        printDoubled(items);
    }

    private static void printDoubled(ArrayList<Integer> n){
        for (int item : n){
            System.out.println( item * 2);
        }
    }
}
