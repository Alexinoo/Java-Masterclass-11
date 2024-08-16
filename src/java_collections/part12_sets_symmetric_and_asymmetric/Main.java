package java_collections.part12_sets_symmetric_and_asymmetric;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Sets - Symmetric and Asymmetric
 *
 * - We'll have a look at some of the Mathematical operations that are usually performed on Sets
 *
 * //// addAll()
 * - We've already seen how to create a union of 2 sets using the addAll()
 * - This is one of what oracle docs calls bulk operations that can be performed on Set<E>
 * - Check the link below from resource section
 *      https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html
 * - We're going to use some examples to look at how to perform Set<E> intersection and difference as well as checking
 *   if
 *      - 1 Set is a super set of another
 *      - 2 Set is a subset of the first
 *
 * /// Examples
 * Create a Set of all squares
 *
 * Create a Set of all cubes from 1 to 20
 *
 * Implement using a for loop
 *
 * Print the number of elements each set hold
 *
 * //////
 * - We've already seen how to get the Union when we created the Set<HeavenlyBody> moons in the Solar system
 * - Because checking nearly 200 numbers that will be printed in chaotic order we'll just print the size of the
 *   resulting Set to confirm things are working
 *
 *
 * First
 *  - Create a Set<Integers> and initialize it with a Set<Integer> squares values
 *  - use addAll() and add the cubes as well
 *
 *  - Print the size of the Set<Integer> union
 *      - prints 38 elements
 *      - 1, 64 appear on both sets, only 1 copy was added to the Set<E> union
 *
 * Next
 *  - Because these bulky operations are destructive , which means they modify the Set they're called upon, the program
 *    that we typed in created a new Set containing the member of squares
        Set<Integer> union = new HashSet<>(squares);
 *  - The we used addAll() to add the cube entries
 *      union.addAll(cubes);
 *
 * //////
 *  - Nearly all the collection classes have constructors that take another collection and use the items in that to
 *    populate the new collection
 *  - We created a new variable below
 *
 *      Set<Integer> union = new HashSet<>(squares);
 *
 *      - because bulk operations are destructive and modify the set they are called upon
 *      - by creating a new HashSet, we're making sure that we're not affecting any existing Sets that we initially created
 *  - We use that to create and pass in the Set<Integer> squares to the constructor for the HashSet which added those
 *     entries & then we're using addAll() to add the entries of cubes as well
 *
 * //////
 *  - Notice we didn't get 200 elements, but only 196 elements
 *  - And we already know that the intersection MUST contain 4 elements as a result
 *  - So let's create the intersection to check that and then ultimately sum value of their size and that should equal
 *    to the total no of elements
 *
 * Next
 *  - Print square root and cube root of each element out of the 4 elements in the intersection
 *
 *      for (double i : intersection) {
            System.out.println(i + " is the square of "+ Math.sqrt(i)+ " and the cube of "+Math.cbrt(i));
        }
 * //////////// Symmetric and Asymmetric difference
 *
 * - So in Set, 2 theories are defined, symmetric and asymmetric difference
 * - The Java Set interface defines a way to obtain the asymmetric difference of 2 sets using the removeAll() bulk
 *   operation
 *      - And that will remove all elements from 1 Set from another
 * - Our current example has an intersection of 4 elements in a Set size() of a 100 , and checking the result will be
 *   extremely tedious
 * - We'll switch to smaller sets just to see the removeAll() in action
 * - This is a good time to actually look at the interaction between the collections framework and Java Arrays so that
 *   we can use that to create our Sets
 *
 * ///////
 *  - The Collection interface suggests that all classes that implement Collection should provide 2 standard
 *    constructors
 *      - A no args constructor to create an empty collection
 *      - Takes a Collection argument to initialize the new collection with all the items in the Collection that is
 *         passed to the constructor
 *  - But this is only a suggestion because interfaces do not have a constructors - this behavior can't be enforced for
 *    that reason
 *
 *  - Tha Arrays Class provides Arrays.asList() that's used to return a List View of the elements in the array
 *      - In fact it uses an ArrayList
 *
 * ///////
 *  - Create an empty Set of type String
 *      Set<String> words = new HashSet<>();
 *
 *  - Create an Array of Strings
 *      String[] arrayWords = "One day in the year of the fox".split(" ");
 *
 *  - call arrayWords on Arrays.asList() and use addAll to add it to words Set<String> words set
 *      words.addAll(Arrays.asList(arrayWords));
 *
 *  - loop and print the words and of course we don't expect them to be printed in sorted order
 *
 *
 * ///// Arrays.asList()
 *  - This method act as a bridge between array-based and collection-based APIs
 *  - It provides a convenient way of initializing a collection with a list of values as there is still no Set or Map
 *    literals in Java
 *
 * ////
 *  - To see what removeAll does and why it's called asymmetric difference , our example create 2 sets and calculates
 *    the asymmetric difference both ways around
 *  - So it's called asymmetric difference, because Set 1 takes Set 2 , is not the same as Set 2 take Set 1
 *
 * //// Venn Diagram to understand Sets
 *  - The circle to the left in green represent Set<String> nature
 *  - The circle to the right in blue represent Set<String> divine
 *
 *  - When they overlap,
 *      - it's the intersection of the 2 Sets
 *      - and the Set of everything in both is their union
 *
 *  - The asymmetric difference
 *      - nature - divine will contain all the green words
 *      - divine - nature will contain all the blue words
 *
 * //// Example
 *  - Create a Set<String> natureSet
 *      - Create an array of strings "natureWords" and add to natureSet
 *
 *  - Create a Set<String> divineSet
 *      - Create an array of strings "divineWords" and add to divineSet
 *
 *  - use Arrays.asList() which provides a convenient way to initialize the collection with a list of values as there
 *    is still no Set or Map literals in Java
 *      - We're sort of simulating that by having an array of strings and then using the addAll()
 *      - Then pass Arrays.asList(String[] array) to convert it to ultimately to this Set in a convenient way
 *
 * /////////// removeAll()
 *
 * - Transforms Set1 into the (asymmetric) set difference of S1 and S2.
 * - For example, the set difference of s1 minus s2 is the set containing all of the elements found in s1 but not in s2
 *      - Nature take out divine
 *  - Let's figure out the asymmetric difference,
 *      - nature take divine
 *           Set<String> diff1 = new HashSet<>(natureSet);
 *           diff1.removeAll(divineSet);
 *           printSet(diff1);            [all, but, art, thee, nature, unknown]
 *
 *      - then divine take nature
            Set<String> diff2 = new HashSet<>(divineSet);
            diff2.removeAll(natureSet);
            printSet(diff2);            [err, forgive, divine, human]
 *
 * - The asymmetric difference "nature take divine" will contain all the green words : [all, but, art, thee, nature, unknown]
 * - The asymmetric difference "divine take nature" will contain all the blue words :  [err, forgive, divine, human]
 *
 * - Java doesn't have a method for calculating the asymmetric difference because it's used less often when dealing with Sets
 *
 * //// Symmetric difference
 * - The Symmetric difference is the elements that appear in 1 set or the other but not both
 *      - This can be therefore be defined as the Union Minus Intersection ( distinct in both sets)
 *      - This will give us both the green and blue words
 * - Because we know how to get the union and the intersection, we can get the symmetric difference by removing intersection from the union
 *
 * - Create a union of both natureSet and divineSet - using addAll()
 *      Set<String> unionTest = new HashSet<>(natureSet);
        unionTest.addAll(divineSet);
 *
 * - Create an intersection of both - filters only elements appearing on both sets - using retailAll
 *      Set<String> intersectionTest = new HashSet<>(natureSet);
        intersectionTest.retainAll(divineSet);  // [ is , to ]
 *
 * - Get the Symmetric difference
 *      System.out.println("Symmetric Difference");
        unionTest.removeAll(intersectionTest); [err,forgive,is,to,divine,human,all,but,art,thee,nature,unknown].removeAll[is,to]
 *      printList(unionTest); [err,forgive,divine,human,all,but,art,thee,nature,unknown]
 *
 * - unionTest, now contains all the green and blue words and not the words in the intersection [is,to] which is the symmetric difference
 *
 *
 * ////////// containsAll()
 *  - returns true if s2 is a subset of s1. (s2 is a subset of s1 if set s1 contains all of the elements in s2.)
 *  - Test if 1 set is a superset of another
 *  - is actually non-destructive
 *      - it only tests membership and doesn't actually modify the sets
 *
 *  - Example
 *      natureSet.containsAll(divineSet); false - not a subset
 *
 *      natureSet.containsAll(intersectionTest); true - is a subset ;
 *
 *          [ is , to ] is a subset of [all, but, art, thee, nature, is, to, unknown]
 *
 *
 *      divineSet.containsAll(intersectionTest); true - is a subset ;
 *
 *          [ is , to ] is a subset of [err,forgive,is,to,divine,human]
 *
 * ////// Why Using a Set matter..
 *  - Set are very useful, if you want to get all the unique items in the collection
 *  - HashSet class itself is very fast
 *  - can be used as a way of grouping related items together even if you don't plan to perform mathematical Set operations on it
 *
 *
 *
 * ///// Add printList(Set<String> set)
 *  - Takes a Set<String> and print them out
 *
 */
public class Main {

    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();

        for (int i = 1; i <= 100; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }

        System.out.println("There are "+squares.size() + " squares and "+ cubes.size() + " cubes");

        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);

        //System.out.println(squares);
        //System.out.println(cubes);
        System.out.println("Union contains "+union.size()+ " elements"); // 38 elements : 64 and 1 are duplicates and were not added

        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);
        System.out.println(intersection);
        System.out.println("Intersection contains "+intersection.size()+ " elements");


        for (double i : intersection) {
            System.out.println(i + " is the square of "+ Math.sqrt(i)+ " and the cube of "+Math.cbrt(i));
        }


        Set<String> words = new HashSet<>();
        String[] arrayWords = "One day in the year of the fox".split(" ");
        words.addAll(Arrays.asList(arrayWords));

        for (String word: words) {
            System.out.println(word);
        }
        
        Set<String> natureSet = new HashSet<>();
        String[] natureWords = {"all","nature","is","but","art","unknown","to","thee"};
        natureSet.addAll(Arrays.asList(natureWords));

        Set<String> divineSet = new HashSet<>();
        String[] divineWords = {"to","err","is","human","to","forgive","divine"};
        divineSet.addAll(Arrays.asList(divineWords));


        System.out.println("\n==============\n");

        System.out.println("Nature set : "+natureSet);
        System.out.println("Divine set : "+divineSet);

        System.out.println("\n==============\n");

        System.out.println("nature - divine:");
        Set<String> diff1 = new HashSet<>(natureSet);
        diff1.removeAll(divineSet); // [all, but, art, thee, nature, is, to, unknown].removeAll(err, forgive, is, to, divine, human)
        printSet(diff1); // [all,but,art,thee,nature,unknown]

        System.out.println("divine - nature:");
        Set<String> diff2 = new HashSet<>(divineSet);
        diff2.removeAll(natureSet); // [err,forgive , is , to , divine , human].removeAll[all,but,art,thee,nature,is,to,unknown]
        printSet(diff2); // [err,forgive , divine , human]

        //Symmetric Difference
        Set<String> unionTest = new HashSet<>(natureSet);
        unionTest.addAll(divineSet); //[err,forgive , is , to , divine , human , all, but, art, thee, nature,unknown]

        Set<String> intersectionTest = new HashSet<>(natureSet);
        intersectionTest.retainAll(divineSet);  // [ is , to ]

        System.out.println("Symmetric Difference");
        unionTest.removeAll(intersectionTest); //[err,forgive,divine,human,all,but,art,thee,nature,is,to,unknown].removeAll[is,to]
        printSet(unionTest); // [err,forgive,divine,human,all,but,art,thee,nature,unknown]


        // containsAll - subset of another set
        if (natureSet.containsAll(divineSet)) {
            System.out.println("divine is a subset of nature");
        }

        if (natureSet.containsAll(intersectionTest)) {
            System.out.println("intersection is a subset of nature");
        }

        if (divineSet.containsAll(intersectionTest)) {
            System.out.println("intersection is a subset of divine");
        }

    }

    private static void printSet(Set<String> set) {
        System.out.println("\t"+set);
    }

}
