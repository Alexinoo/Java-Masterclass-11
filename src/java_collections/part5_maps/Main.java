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
 *
 *  * Map.get(K key)
 * ////////////////////////
 *
 *
 *  - We use a get(Object Key) to retrieve the values of the associated key
 *  - For example
 *      System.out.println(languages.get("Java"));
 *
 *  - prints
 *      "a compiled high level, object-oriented,platform independent language"
 *
 * - Another key important of Maps is that Keys are unique , and what happens is that if we try to use a key again, the existing value gets
 *   overwritten
 *      - For a particular key, there can only be 1 value and if you try to insert more than once, using the put, then the old value gets overwritten
 *
 * Map.put(K key , V value)
 * ////////////////////////
 *
 * - The put() itself can be used to tell if a value is being added for the first time
 * - It can be useful in certain circumstances to know that, because it returns the previous value if there was 1
 *
 * - For example , below statements will print the following
 *
 *      - Below statement will print - null
 *
 *          System.out.println(languages.put("Php"," a popular general-purpose scripting language")); // null
 *
 *          - This what essentially means is that this is a brand-new reference
 *          - A brand new key-value pair that was added to the dictionary
 *
 *      - While a consequent statement of the same will return previous value above
             System.out.println(languages.put("Php","another popular php language")); // a popular general-purpose scripting language
 *
 *          - returns the value that already existed prior to the value being added
 *
 * - Well that's helpful to determine what the value was, but it didn't prevent us from adding/overwriting the existing one
 *      - It still got added whether you wanted it to or not
 *      - So if do want to determine and then pragmatically only add if it's not already there, we can do that as well
 *
 * containsKey()
 * //////////////////
 *
 * - We can use the containsKey() to check to see whether a key exist and if so ,
 *      - print something to the user to let them know about this
 *      - otherwise , add it to the map
 *
 * - For example
 *
 *      - Add a "Laravel" to the Map
 *
 *           languages.put("Laravel","A PHP Framework");
 *
 *      - Then check if the Map contains "Laravel" key

            if (languages.containsKey("Laravel")){
                System.out.println("Laravel is already in the map");
            }else{
 *              languages.put("Laravel","A very popular PHP Framework");
 *          }
 *      - Otherwise, add it tio the Map, if it isn't there
 *
 *  - This can be a good way to programmatically ensure that you're only adding an item once and it's never going to be overwritten by adding it
 *    a second time
 *
 *  languages.putIfAbsent()
 * ////////////////////////
 *
 * - Adds to the map if the key is not already present in the Map
 * - Mostly intended to prevent concurrency issues so that 1 thread does not add to the map only for that entry to be overwritten by another thread
 * - It doesn't help in a null case because it will happily overwrite keys with null values
 *
 * languages.remove()
 * ///////////////////
 *
 * - We can also remove items from the Map
 *
 * Printing contents of the Map
 * /////////////////////////////
 *
 * - print our map's contents so that we can check for the items that we're going to remove
 *
 *
 * Map.KeySet()
 * //////////////
 *
 *  - Loop through all the keys in the map using the keySet()
 *  - returns a set of all the keys : Set<String>

 *      for (String key : languages.keySet()){
            System.out.println(key + " : "+ languages.get(key));
        }
 *
 *  - then use languages.get(key) to retrieve the corresponding values
 *
 *  - Notice, that there's no specific order , and the order doesn't relate to how we've entered our entries for the first time
 *  - There's no guaranteed ordering with a HashMap
 *      - The keys haven't appeared in the order that we added them, nor in alphabetical order
 *
 *
 * Map.remove(Object key)
 * //////////////////////
 *
 *  - Used to remove a specific key from the Map
 *  - There are actually 2 ways to do this
 *
 * void remove(Object key)
 *      - Remove by specifiying the key
 *         languages.remove("Lisp");
 *
 * boolean Map.remove(Object key, Object value)
 *      - Removes the entry for the specified key only if it is currently mapped to the specified value.
 *      - For example
 *          if(languages.remove("Laravel","A PHP Framework"); ){
 *               System.out.println("Laravel removed , key/value pair matches");
 *          }else{
 *              System.out.println("Laravel not removed , key/value pair not found");
 *          }
 *      - above will work, since the value is matching with the key
 *      - won't work if the value is not the same for the key specified
 *
 * - The second version returns true/false and we can use if-then to indicate that the key was either removed successfully or not
 *
 *
 * Map.replace(Key k, Value v)
 * ///////////////////////////
 *
 *  - Replaces the entry for the specified key only if it is currently mapped to some value.
 *
 *       languages.replace("PHP", "PHP is a general-purpose scripting language geared towards web development");
 *
 *      - value of PHP is replaced with the content specified
 *
 *  - Replaces if the key specified exist in the map
 *
 *      languages.replace("Scala", "This will not be added");
 *
 *      - value of Scala is not replaced as the key doesn't exist in the first place
 *      - works with the assumption that the key already existed in the map , and it doesn't get added unlike put which does that and overwrite
 *        the contents if it's already there
 *
 *
 * boolean replace(K key, V oldValue, V newValue)
 * //////////////////////////////////////////////
 *
 *  - Just like remove , we can also specify the existing value so that our mapping is only updated, if the key was mapped to the old value, to
 *   achieve this we provide the expected old value before the new one
 *
 *  - For example
 *
 *      languages.put("Algol","an algorithmic language");
 *
 *      - replace to
 *
 *      languages.replace("Algol","an algorithmic language","an algorithmic language - updated one");
 *
 *  - Check if replace works with a wrong old value and see if it gets replaced
 *      languages.replace("Algol","a wrong value","updated with a wrong value");
 *
 *      - the above doesn't work, oldValue needs to match with the existing value for replacement to work
 *
 *  - This function takes a 3rd parameter ,
 *      - The key
 *      - what the old value was
 *      - pass the new value to update
 *  - This ensures that we're only updating if the value for that specified existed
 *
 * Use case
 *  - Suppose we want to update someone's name after they got married , you can ensure that the correct person is updated , or probably their
 *    changing their address
 *  - Checks that you're checking that exact entry before updating and therefore no chance of accidentally overwriting the wrong info
 *
 * N/B
 * /////////////////////////////////////////////////////////////
 *  - For the example below, we've used the methods defined by the Map<K,V> interface , we've used a Map<String,String> that uses a String type for
 *    both the key and the value
 *  - Notice, both the Key and the value can be of any Object
 *  - We can even use a Map type as the values in another map
 *  - In actual fact, you could add a map as a value to itself
 *      - no requirement that the keys of a map be immutable , unlike other languages such as Python
 *      - immutable objects are things like strings and Integers whose value can't change
 *  - When we assign a new string to a String variable, what we're doing is that we're changing the value that the variable holds , we're not changing
 *    the String
 *
 *  - One thing that is not permitted is for a map to contain itself as a key
 *
 */
public class Main {

    public static void main(String[] args) {
        Map<String,String> languages = new HashMap<>();

        languages.put("Java","a compiled high level, object-oriented,platform independent language");
        languages.put("Python","an interpreted object-oriented, high-level programming language with dynamic semantics");
        languages.put("Algol","an algorithmic language");
        languages.put("BASIC","Beginners All Purpose Symbolic Instruction Code");
        languages.put("Lisp","Therein lies madness");

        System.out.println(languages.get("Java")); // a compiled high level, object-oriented,platform independent language

        languages.put("Java","This course is about Java");
        System.out.println(languages.get("Java")); // This course is about Java

        System.out.println(languages.put("Php"," a popular general-purpose scripting language")); // null
        System.out.println(languages.put("Php","another popular php language")); // a popular general-purpose scripting language

        languages.put("Laravel","A PHP Framework");

        if (languages.containsKey("Laravel")){
            System.out.println("Laravel is already in the Map");
        }else{
            languages.put("Laravel","A very popular PHP Framework");
        }

        System.out.println("================================");
        for (String key : languages.keySet()){
            System.out.println(key + " : "+ languages.get(key));
        }

        // Remove Lisp
        languages.remove("Lisp");

        if(languages.remove("Laravel","A PHP Framework") ){
                System.out.println("Laravel removed , key/value pair matches");
           }else{
               System.out.println("Laravel not removed , key/value pair not found");
        }

        System.out.println("================================");
        for (String key : languages.keySet()){
            System.out.println(key + " : "+ languages.get(key));
        }

        // Replace(Key k , V newValue)
        languages.replace("Php", "PHP is a general-purpose scripting language geared towards web development");
        languages.replace("Scala", "This will not be added");


        System.out.println("================================");
        for (String key : languages.keySet()){
            System.out.println(key + " : "+ languages.get(key));
        }

        // replace(Key key , V oldValue , V newValue)
        languages.replace("Algol","an algorithmic language","an algorithmic language - updated one");

        System.out.println("================================");
        for (String key : languages.keySet()){
            System.out.println(key + " : "+ languages.get(key));
        }

        languages.replace("Algol","a wrong value","updated with a wrong value");

        System.out.println("================================");
        for (String key : languages.keySet()){
            System.out.println(key + " : "+ languages.get(key));
        }
    }
}
