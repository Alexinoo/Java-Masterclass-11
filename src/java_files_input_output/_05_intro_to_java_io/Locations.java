package java_files_input_output._05_intro_to_java_io;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Locations Class
 *  - Implements Map<Integer,Location>
 *
 * Fields
 *  locations : Map<Integer,Location>
 *      - store our data or store our locations
 *      - initialized it to a new HashMap()
 *
 * - Implement methods with "Alt+Ins" to implement various methods that are part of Map interface
 *   - For example:
 *      - size():int
 *      - isEmpty() : boolean
 *      - containsKey(Object key) : boolean
 *      - containsValue(Object value) : boolean
 *      - get(Object key) : V
 *      - put(K key , V value) : V
 *      - putAll() : void
 *      - remove(Object key) : V
 *      - clear() : void
 *      - keySet() : Set<K>
 *      - values() : Collection<V>
 *      - entrySet() : Set<Entry<K,V>>

 * - Next, customise the default code that is generated
 *
 * - size() : int
 *      - returns locations.size()
 *          - returns how many elements that are in our Map<Integer,Location> locations - HashMap
 *
 * - isEmpty() : boolean
 *      - returns locations.isEmpty()
 *      - returns true/false depending with whether we've got any elements in our Map<Integer,Location> locations - HashMap
 *
 * - containsKey(Object key) : boolean
 *      - return locations.containsKey(key)
 *      - returns true/false, if the key passed to this method exists in the Map<Integer,Location> locations
 *
 * - containsValue(Object value) : boolean
 *      - return locations.containsValue(value)
 *      - returns true/false, if the value passed to this method exists in the Map<Integer,Location> locations
 *
 * - get(Object key) : Location
 *      - return Location obj mapped to the key passed to this method
 *
 * - put(Integer key , Location value) : Location
 *      - adds element to the map - and returns the Location obj
 *
 * - remove(Object key) : Location
 *      - removes Location specified by the key passed and returns it
 *
 * - clear() : void
 *      - clear all the elements in the Map<Integer,Location> locations
 *
 * - keySet() : Set<Integer>
 *       - returns all the Keys from Map<Integer,Location> locations as a Set<Integer>
 *
 * - values() : Collection<Location>
 *       - returns the values in our Map<Integer,Location> locations object as a Collection<Location>

 * - entrySet() : Set<Entry<Integer,location>>
        - returns each entry in our Map<Integer,Location> locations as a Set<Entry<Integer,location>>
 *
 * //////////
 *
 * - So we're using a HashMap to store the Location just like the main class did and we have implemented the abstract
 *   methods as required from the Map interface
 *      - We won't be using all of them but then we had to and it's worth the effort to encapsulate the location data
 *          this way
 * - What we've just created is a class that pretty much behaves like a map, but which we can customize to load its
 *   contents from an external source, a disk file in this case
 *
 * /////////
 *  - Swing to the main class and start replacing the locations of map with our new class
 *
 *
 */

public class Locations implements Map<Integer,Location> {
    private static Map<Integer,Location> locations = new HashMap<>();

    static {

        locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java" ));
        locations.put(1 , new Location(1,"You are standing at the end of the road before a small brick building" ));
        locations.put(2 , new Location(2,"You are at the top of a hill" ));
        locations.put(3 , new Location(3,"You are inside a building, a well house for a small spring" ));
        locations.put(4 , new Location(4,"You are in a valley beside a stream" ));
        locations.put(5 , new Location(5,"You are in the forest" ));


        /////////////////////////   Adding Exits ////////////////////

        // From location 1 (Road): You can go to the following locations/rooms
        locations.get(1).addExits("W",2);
        locations.get(1).addExits("E",3);
        locations.get(1).addExits("S",4);
        locations.get(1).addExits("N",5);
        //locations.get(1).addExits("Q",0);

        // From location 2 (Hill): You can go to the following locations/rooms
        locations.get(2).addExits("N",5);
        //locations.get(2).addExits("Q",0);


        // From location 3 (Building): You can go to the following locations: Road to the West , Q to quit
        locations.get(3).addExits("W",1);
        //locations.get(3).addExits("Q",0);

        // From location 4 (Valley): You can go to the following locations: Hill to the West ,Road to the North
        locations.get(4).addExits("N",1);
        locations.get(4).addExits("W",2);
        //locations.get(4).addExits("Q",0);

        // From location 5 (Forest): You can go to the following locations: Road to the South
        locations.get(5).addExits("S",1);
        locations.get(5).addExits("W",2);
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
