package generics.part1_intro;

import java.util.ArrayList;

public class WithoutGenerics {

    /*'
     * Generics Introduction
     * ....................
     *
     * - Just as we can create method(s) that take arguments in Java, and we can replace the parameters that we declared from the method
     * - Generics allows us to create classes , interfaces and method(s) that take types as parameters called unsurprisingly Type Parameters
     *
     * - Let's start with an ArrayList example that doesn't use Generics
     * - Create an ArrayList populated with integers and a method that prints the contents of the arrayList , doubled
     *
     *
     * - add integers 1,2,3,4,5 to the ArrayList
     *
     * - This is using autoboxing automatically, i.e. converting primitive type to an Integer
     *
     * printDoubled
     *  - Note we're using Object in the foreach loop because we didn't specify what the object was in the ArrayList
     *  - Then , we needed to cast it as an Integer and if we don't we'll actually get an error
     *  - We have to sort of tell Java what type of Object is actually in that ArrayList
     *
     *
     * - Technically, the ArrayList we've created can actually contain anything
     * - Any type of Object, not just an integer
     *
     * - So this is actually more than a minor inconvenience, because it also completely breaks the compiler's type checking
     * - Let's see the impact of how this will affect us by adding below
     *      items.add("alex");
     *
     * - And the above is quite valid with no indication that we've a problem with our code or red underlining in our code and as far as Java is
     *   concerned, this is valid and it actually is valid
     *
     * - But the problem is when we run it, we actually get an exception : "ClassCastException"
     *
     * - The reason is that the String object "alex" is tried to be converted into an Integer and it cannot be actually converted
     *      - is because we added a different type of data , in this case a String to our arrayList
     *
     * - The real problem wasn't so much it crashed although that's bad enough, but the real problem is that it isn't showing up until run-time
     * - In other words, the program compiles fine, we can build, or rebuild the project and as far as Java is concerned there are literally no
     *   problems, only potential warnings
     *      - "Unchecked call to 'add(E)' as a member of raw type 'java.util.ArrayList'
     *
     * - So, the Java compiler and IntelliJ by default knows how to provide these warnings because an ArrayList is a generic type, though we're using
     *  it without specifying a type parameter , in other words, using it without generics
     *
     * - We haven't specified with the ArrayList what type of Objects we're going to be storing in the ArrayList
     *
     * - We've used the ArrayList as a raw type
     *
     * - Prior to Java 1.5, this was the only way to code, there were no generics and when Java 1.5 came out, they actually introduced generics
     *
     * - raw types were left for backwards compatibility, with code that's written from previous versions,
     *      - in other words, if they didn't do that, that code would no longer run anymore
     *
     * - The old code continues to run, but when we're writing new code, we are highly discouraged to use the raw types and we can see why because in
     *   this instance we're able to crash it very quite easily by making a simple little change
     *
     * - It's certainly not recommended to use raw types, and because type safety is so easy to implement these days in the current versions of Java
     *
     *
     */

    public static void main(String[] args) {
        ArrayList items = new ArrayList();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(5);

        items.add("alex");

        printDoubled(items);
    }

    private static void printDoubled(ArrayList n){
       for (Object item : n){
           System.out.println( (Integer)item * 2);
       }
    }
}
