package java_collections.part9_sets_and_hashsets;
/*
 * Sets and HashSets
 *
 * - Sets are generally used less often than Lists and Maps but they can in themselves be extremely useful
 * - Whereas a List is an ordered collection of items that can contain duplicates , a Set has
 *      - no defined ordering, and the Oracle docs defines it as chaotic
 *      - a set cannot contain duplicates
 * - In fact, the lack of duplicates is the most important differentiator but there are also ordered sets such as
 *      - LinkedHashSet
 *      - TreeSet
 * - If you want to ensure that there's no duplicates in your Collection, then a good way of doing that is to use a Set rather than a List
 *
 *
 * Set<E> Interface
 * - The Set interface just like everything else in the Collections Framework, is Generic and it takes a Single type
 * - Just like Map<K,V> and List<E> , it's possible to create a raw set, though this is intended for backwards compatibility with code created before
 *   Generics were added to the Java Language and generally speaking it's not a good idea to do that
 *
 * - The Set<E> Interface defines the basic methods such as
 *      - add()
 *      - remove()
 *      - clear()
 *  - To maintain elements in a Set as well as
 *      - size()
 *      - isEmpty
 *  - To provide some information about how many items are actually in a Set and whether it's empty or not
 *
 *  - We can also check if an item, a specific item is in the Set using the
 *      - contains()
 *  - But interestingly enough , there is no way to retrieve an item from a Set
 *
 *  - You can only check if something exists and you can also iterate over all the elements in the Set, but attempting to get say for example
 *    the 10th element from a Set isn't possible
 *      - You need to use a List<E> to do something like that
 *
 *  - We've already used Set<E> when looking at Maps
 *      - The Keys in a Map<K,V> can be retrieved using the KeySet()
 *
 *  - It's therefore not surprising that everything we said about keys in a Map<K,V> also applies to items in a Set<E>
 *
 *  - So using Immutable objects as elements in a Set<E> can cause problems and the behavior is undefined if changing an object affects equal
 *    comparisons
 *  - Just as a Map<K,V> cannot contain itself as a key, a Set<E> can't also be an element of itself although once again it'd be very bizarre program
 *    that needed to actually do that
 * /////////////////////////////////////////////////////////////////
 *
 *  Set<E> Interface Implementation
 * ////////////////////////////////////////////////////////////////
 *
 *  - The best performing implementation of a Set<E> Interface is the HashSet class and that uses hashes to store the items
 *  - This is just like a HashMap class that we've looked at and in fact , the HashSet implementation currently uses of Java 8 though implementations
 *    although of course implementations may change in future versions of Java
 *
 *  - Now if a Set<E> can be implemented using a Map<K key,V value> , then it's not hard to work out that you could actually use the corresponding Map<K,V>
      object and use only the keys ignoring the values
 *  - So, this is what HashSet class does
 *      - Whenever an element is added to the Set<E>, it becomes a Key in the underlying HashMap, and a dummy object is stored as the value
 *
 *  - Set<E> can be useful because operations on them are very fast , and if you're dealing with a Mathematical notion of a Set<E> , then the Java Collection
 *    Set<E> types really allow the usual Set<E> operations such as Union and Intersection which is pretty cool
 *
 * ///////////////////////////////////
 *
 * Use Case
 * ////////////////////////////////////
 *
 * We'll create a Program to model the bodies of in the Solar System
 *  - We won't try to create every obj because even without considering asteroids and comets , there are nearly 200 in our solar system
 *  - We'll just use a small set,
 *
 * Objects in the Solar system can be grouped into various ways such as :-
 *      - Planets
 *      - Moons
 *      - Asteroids
 *      - Comets
 * Also some of the Moons belong to another group
 *  - So the moon's orbiting a particular planet
 *
 * Ganymede for example belongs to a set of moons in the Solar system, and also the set of moons orbiting Jupiter
 *
 * /////////////
 * The program is going to use a Map<K,V> to store all the heavenly body objects , then declare Set<E> to perform the grouping of the object(s) into
 *  different types
 * The actual class will have fields for the name and the number of earth's days in a year, but obviously could contain far more info about each body
 */

public class Main {
    public static void main(String[] args) {

    }
}
