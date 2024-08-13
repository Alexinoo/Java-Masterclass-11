package java_collections.part5_maps;

import java.util.HashMap;
import java.util.Map;

/*
 * Maps Interface
 *
 * - We'll look at the Map interface and some other java classes that implement it
 * - The Map interface is part of the java collections framework even though it's not a true collection as we've seen
 *   in the images before
 * - A map is out there on its own and it's not part of the collection exactly such as a List , Set, Queue, or a Dequeue
 *   is
 * - The Map interface replaces the now obsolete dictionaries abstract class, and like the class that it replaces it
 *   maps keys to values
 * - A language dictionary is a classic example of a Map with the keys being the words in the dictionary and the values
 *   being the definitions/descriptions of the words
 * - Unfortunately, the analogy falls down a bit with the English language , and the reason for that is because many
 *   English words have the same meanings
 *
 * - For example
 *      - The word put has 4 definitions
 *          - 2 as a verb
 *          - 2 as a noun
 *  - A Java map can't contain duplicate keys , and each key can only map to a single value
 *  - We'll look at 2 of the Java classes that implement the map interface which are :-
 *          - HashMap
 *          - LinkedHashMap
 *  - As well as look at TreeMap, that implements the SortedMap interface
 *  - Maps like all the core collections are generic and they take 2 types,
 *          - 1 for the key
 *          - 1 for the value
 *  - It's possible to use raw maps where the types aren't specified , though as we've seen in generics, this is not a
 *    good idea
 *
 * Example
 *  - Let's start with an example of using HashMap to store descriptions of a few computer languages
 *
 *      Map<String,String> languages = new HashMap<>();
 *  - We've defined our Map and set with 2 generic parameters ,
 *      - String as both the key and the value
 *
 * // Adding elements to a map //////////
 *
 *  - To add languages, we use put(String key , String value) that takes 2 parameters
 *      - Key
 *      - Value
 *
 * // Retrieving elements from a Map /////
 *  - We use a get(Object Key) to retrieve the values of the associated key
 *  - For example
 *      System.out.println(languages.get("Java"));
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        Map<String,String> languages = new HashMap<>();

        languages.put("Java","a compiled high level, object-oriented,platform independent language");
        languages.put("Python","an interpreted object-oriented, high-level programming language with dynamic semantics");
        languages.put("Algol","an algorithmic language");
        languages.put("BASIC","Beginners All Purpose Symbolic Instruction Code");
        languages.put("LISP","Therein lies madness");
    }
}
