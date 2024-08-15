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
 *
 * ////////////
 *  Create HeavenlyBody class
 *
 * /////////////
 *
 * - We'll store the Heavenly bodies in a Map<K,V> called solarSystem
 *
 *      private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
 *
 * - that's also going to contain a Set<E> containing the ones that are planets
 *
 *      private static Set<HeavenlyBody> planets = new HashSet<>();
 *
 * - Create Heavenly Bodies :  Planets
 *      HeavenlyBody earth = new HeavenlyBody("Earth",365);
 *
 * - add to the Set<HeavenlyBody> planets HashSet<>()
 *       planets.add(earth);
 *
 * - add to the Map<String,HeavenlyBody> solarSystem HashMap
 *      solarSystem.put(earth.getName(),earth);
 *
 * - Create Heavenly Bodies : Moon
 *      HeavenlyBody ganymedeMoon = new HeavenlyBody("Ganymede",7.1);
 *
 * - Add the Moon to the Map<String,HeavenlyBody> solarSystem HashMap<>()
 *      solarSystem.put(ganymedeMoon.getName(), ganymedeMoon);
 *
 * Next
 *  - call addMoon() and add moons orbiting Mars planet
 *      mars.addMoon(deimosMoon);
        mars.addMoon(phobosMoon);
 *
 *  - call addMoon() and add moons orbiting Jupiter planet
 *      jupiter.addMoon(callistoMoon);
        jupiter.addMoon(ganymedeMoon);
        jupiter.addMoon(europaMoon);
        jupiter.addMoon(iOMoon);
 *
 * Next
 *  - Print out planets
 *
 *      for (HeavenlyBody planet : planets) {
            System.out.println("\t" +planet.getName());
        }
 *
 *  - Print out Moons orbiting Jupiter
 *
 *      HeavenlyBody body = solarSystem.get("Jupiter");
 *          - Return instance of Jupiter "new HeavenlyBody("Jupiter",4332)";
 *
        System.out.println("Moons orbiting "+body.getName());
 *          - call  new HeavenlyBody("Jupiter",4332).getName() prints "Jupiter"
 *
        for (HeavenlyBody jupiterMoon : body.getSatellites()) {
            System.out.println("\t"+ jupiterMoon.getName());
        }
 *          - call new HeavenlyBody("Jupiter",4332).getSatellites() which returns moons orbiting Jupiter
 *
 * ///////
 * The Set<E> interface supports iteration, as you can see that we're able to iterate all the planets which is quite
 *  easy
 * And similarly, we can display moons orbiting certain planet as we have done above
 *  - And quite easy we can print moons orbiting Mars, also if we wanted to
 *
 * //////
 * - The Set<HeavenlyBody> planets groups all the planets together
 * - However at the moment, there's no collection representing all the moons in the Solar System
 * - Although it would have been possible to have a Set<HeavenlyBody> moon in the Main , and add the moons to it as
 *   they're created, we can actually generate the union of the Set<HeavenlyBody> set of each planet's moon
 *
 * - So, in Set<E> theory, a UNION of 2 or more Set<E> is a set containing all the elements of the Set<E> sets
 * - Because Set<E> don't contain duplicates, each element in a UNION will appear once even if it was present in
 *   more than 1 of the Set<E>s
 *
 *
 * ////
 * addAll()
 * - To generate a Set<HeavenlyBody> union, we can use the addAll() of a Set<E> , so that by looping through all the
 *   planets calling getSatellites() , we can generate a Set<HeavenlyBody> moons , even though we didn't create one
 *   when initializing the data
 * - That's sort of a cool features of Set<E>

 * - Create an empty Set<HeavenlyBody> of moons
 *      Set<HeavenlyBody> moons = new HashSet<>();
 *
 * - Loop through all the planets and get moons added to each planet and populate to Set<HeavenlyBody> moons using
 *   addAll()
 *      for (HeavenlyBody planets : planets) {
            moons.addAll(planets.getSatellites());
        }
 *
 * - Then print out all the moons from Set<HeavenlyBody> of moons
 *
 *       for (HeavenlyBody moon: moons ) {
            System.out.println("\t" +moon.getName());
        }
 *      - prints all the moons orbiting all planets : both Mars and Jupiter
 *
 * - We've created a Set<E> UNION with that addAll() of a Set<E> , and since Set<E> don't contain duplicates and
 *   each element in a UNION will only appear once even if it was present in more than one of the sets
 *
 * - There's more named objects in the Solar System than just planet and moons
 * - The main class could have included asteroid and comet Sets
 *
 *      Set<HeavenlyBody> asteroids
 *      Set<HeavenlyBody> comets
 *
 * - and that would be useful in the same way as Set
 *
 * - A good case could be made for including a destination field in HeavenlyBody, which would identify the body as
 *   a planet, a moon, a comet etc
 * - The Set<E> would then be built up by iterating through the entire Solar System and checking the destination
 *   before adding each body to the appropriate Set<HeavenlyBody>
 * - So the code to assign the moon in their respective planets would have got fairly complicated at that point
 *
 * //////////////
 * - One thing that sometimes confuses people with code like this is that it's only references to the objects that
 *   have been stored in the sets
 *      - Europa for example , appears in the Map<String,HeavenlyBody> solarSystem map and in a
 *          Set<HeavenlyBody> satellites for Jupiter but there's really only 1 instance of the HeavenlyBody class
 *          for Europa
 * - This confusion can lead to a common mistake when dealing with collections , like in this example, where the
 *   Set<HeavenlyBody> would contain the name of the bodies rather than references them
 *
 * - So if the HeavenlyBody class contains kilobytes of info on each body, the faulty reasoning is that it's more
 *   efficient to just store the String name in the Set<E> or whatever collection you're using for that matter
 * - But, a reference to a String is exactly the same size as a reference to any other object
 * - So nothing is gained,and the code becomes more complex because the actual object has to be retrieved from that map before you can get any
 *   details
 * - If the id of the object was an integer rather than a String, then it might be tempting to store that in the sets , but this is also a false
 *   optimization
 * - On a 32-bit Java virtual machine, an int and an object reference both take 4 bytes
 *      - a byte is 8 bits
 *      - 4 bytes is 32 bits
 * - On a 64 bit machine, the object reference would normally be 64 bits which of course is 8 bytes
 * - But interestingly enough, Java 7 uses compressed pointers that can even end up even using less space than the 32-bit int
 *      - Check the link below for more info on JVM
 *          https://docs.oracle.com/javase/8/docs/technotes/guides/vm/performance-enhancements-7.html
 *
 * - There's 1 good reason for using the body's name in the Set<HeavenlyBody> and it's got nothing to do with using memory more efficiently
 * - We've discussed making classes immutable, and the reason for doing that is so that they can be used as the keys in a map and a Set<HeavenlyBody>
    has the same restrictions and warnings as Map<K,V> keys
 *
 * ///////
 * Unfortunately though, our HeavenlyBody class can't be made immutable because new info is constantly being discovered about the planets even in our
 *  Solar system
 * So, modelling a more distant Solar system would certainly result in info about the bodies changing
 *  - We've allowed moons to be added to planets, which is what makes our heavenly body class immutable but that's necessary
 *
 * Taking Saturn for example, currently over 60 moons have been identified and many of those have been discovered since 2000
 * It's very likely that more will have to be added in the future
 * There's also quite a few that haven't been named yet and they'll also be added at some point
 *  - There's a good reason, the Instructor stopped adding moons when he got to Jupiter as you can see for that reason
 * Java doesn't prohibit using mutable objects and Sets and it's Map keys , but there's some recommendations that should be followed and we'll look at
 *  them in the next video
 *
 *
 *
 *
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();
    public static void main(String[] args) {

        // Create Heavenly Bodies
        HeavenlyBody mercury = new HeavenlyBody("Mercury",88);
        HeavenlyBody venus = new HeavenlyBody("Venus",225);
        HeavenlyBody earth = new HeavenlyBody("Earth",365);
        HeavenlyBody mars = new HeavenlyBody("Mars",687);
        HeavenlyBody jupiter = new HeavenlyBody("Jupiter",4332);
        HeavenlyBody saturn = new HeavenlyBody("Saturn",10759);
        HeavenlyBody uranus = new HeavenlyBody("Uranus",30660);
        HeavenlyBody neptune = new HeavenlyBody("Neptune",165);
        HeavenlyBody pluto = new HeavenlyBody("Pluto",248);

        // Add 9 planets to Set<HeavenlyBody> planets HashSet
        planets.add(mercury);
        planets.add(venus);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(saturn);
        planets.add(uranus);
        planets.add(neptune);
        planets.add(pluto);

        // Add HeavenlyBodies(Planets) to the solarSystem Map
        solarSystem.put(mercury.getName(), mercury);
        solarSystem.put(venus.getName(), venus);
        solarSystem.put(earth.getName(), earth);
        solarSystem.put(mars.getName(), mars);
        solarSystem.put(jupiter.getName(), jupiter);
        solarSystem.put(saturn.getName(), saturn);
        solarSystem.put(uranus.getName(), uranus);
        solarSystem.put(neptune.getName(), neptune);
        solarSystem.put(pluto.getName(), pluto);

        // Add HeavenlyBodies - Moons
        HeavenlyBody earthMoon = new HeavenlyBody("Moon",27);
        HeavenlyBody deimosMoon = new HeavenlyBody("Deimos",1.3);
        HeavenlyBody callistoMoon = new HeavenlyBody("Callisto",16.7);
        HeavenlyBody ganymedeMoon = new HeavenlyBody("Ganymede",7.1);
        HeavenlyBody europaMoon = new HeavenlyBody("Europa",3.5);
        HeavenlyBody iOMoon = new HeavenlyBody("Io",1.8);
        HeavenlyBody phobosMoon = new HeavenlyBody("Phobos",0.3);



        // Add Moons to the Solar System
        solarSystem.put(earthMoon.getName(), earthMoon);
        solarSystem.put(deimosMoon.getName(), deimosMoon);
        solarSystem.put(callistoMoon.getName(), callistoMoon);
        solarSystem.put(ganymedeMoon.getName(), ganymedeMoon);
        solarSystem.put(europaMoon.getName(), europaMoon);
        solarSystem.put(iOMoon.getName(), iOMoon);
        solarSystem.put(phobosMoon.getName(), phobosMoon);

        // Add moons specific to Mars
        mars.addMoon(deimosMoon);
        mars.addMoon(phobosMoon);

        // Add moons specific to Jupiter
        jupiter.addMoon(callistoMoon);
        jupiter.addMoon(ganymedeMoon);
        jupiter.addMoon(europaMoon);
        jupiter.addMoon(iOMoon);

        // Print planets
        System.out.println("Planets \n"+
                "=========");
        for (HeavenlyBody planet : planets) {
            System.out.println("\t" +planet.getName());
        }

        // print moons orbiting Jupiter/Mars
       // HeavenlyBody body = solarSystem.get("Jupiter");
        HeavenlyBody body = solarSystem.get("Mars");
        System.out.println("Moons orbiting "+body.getName());
        for (HeavenlyBody jupiterMoon : body.getSatellites()) {
            System.out.println("\t"+ jupiterMoon.getName());
        }

        // Create an empty HashSet of Set<HeavenlyBody> moons
        Set<HeavenlyBody> moons = new HashSet<>();

        // loop through all the planets and get moons added to each
        // populate to Set<HeavenlyBody> moons HashSet
        for (HeavenlyBody planets : planets) {
            moons.addAll(planets.getSatellites());
        }

        // print out all the moons from Set<HeavenlyBody> moons
        System.out.println("All Moons");
        for (HeavenlyBody moon: moons ) {
            System.out.println("\t" +moon.getName());
        }

    }
}
