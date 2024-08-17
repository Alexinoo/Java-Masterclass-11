package java_collections.part14_sorted_collections;
/*
 * Sorted Collections
 *
 * - We've looked at the following so far
 *      - List<E> interface and it's implementation with ArrayList and LinkedList
 *      - Map<K,V> interface and it's implementation with HashMap
 *      - Set<E> interface and it's implementation with a HashSet
 *
 * - There's 2 other collection interfaces which we haven't looked at and might look ito them later in this course
 *      - Queue
 *      - Dequeue
 * - Now let's look at the variant of the HashMap and HashSet classes which are known as
 *      - LinkedHashMap
 *      - LinkedHashSet
 * - They're also the sorted versions of the Map<K,V> and Set<E> interfaces, sorted map and sorted set that are
 *   implemented in TreeMap and TreeSet
 * - Since the operations on Sets and Maps are so similar, the examples in here will concentrate on Maps<K,V> rather
 *    than Sets
 * - The Linked variant of HashMap and HashSet are called LinkedHashMap and LinkedHashSet and the only difference is
 *    that they've got an ordering to them
 * - In the oracle docs, the ordering of items in a HashMap or HashSet is described as chaotic , but the linked versions
 *   maintain insertion order
 *      - Once you put items in a collection, you know the order that the result will come back when you iterate over it
 * - Will look at example of this by adding our items in alphabetical order
 *
 * /////
 *  - The example is going to allow users to add purchases to a shopping basket after choosing them from a list of all
 *    items on sale
 *
 * ////
 *  - We need to maintain 2 collections
 *      1. A list of all the items that are in stock
 *      2. Items that a customer has added to their basket
 *  - Though it might seem contrived to add the stock items in alphabetical order, in reality, items will certainly come
 *    from the database and they could be in any order that we wanted by using an ORDER BY clause in the query
 *
 * ////
 *  - Let's get on with the example to see how a LinkedHashMap can be useful and will ultimately extend that to use a
 *    TreeMap
 *
 * /////
 *  - Create a Basic stock item class
 *
 *
 */
public class Main {
}
