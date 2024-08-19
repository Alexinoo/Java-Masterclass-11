package java_files_input_output._13_challenge;

import java.util.HashMap;
import java.util.Map;

/*
 * Location class
 *
 * Fields
 *  final locationId : int
 *  final description : String
 *  final exits : Map<String,Integer>
 *
 * Constructor
 *  Location(int locationId , String description)
 *      - Initialize locationId and description
 *      - Initialize exits Map to a new HashMap
 *
 * Methods
 *
 *  addExit(String direction , int location) : void
 *      - add the ability to add an exit to our current location
 *      - call exits.put(direction,location)
 *          - where direction is the key
 *          - and location is the value
 *
 * Getters
 *  getLocationId() : int
 *      - returns locationId
 *
 *  getDescription() : String
 *      - returns description
 *
 *  getExists : Map<String,Integer>
        - return a map of our exits
 *
 * - One thing about this class is that we're programming defensively
 *      - Anything that doesn't need to be exposed to the outside isn't exposed
 *
 * - All the fields are marked final, and can't be changed until the constructor has finished creating a location instance
 *
 * - One useful technique that we've used is in the Getter for the exits map and with the getExits(), we're not just returning the exits map,
 *      - We're creating a new Hashmap<>(exits) and passing our exits in the constructor
 *      - A new map is created with all the mappings from the exits map
 *
 * - The reason why this would be useful , is that nothing outside our class can change exits
 *      - The getExits() getter returns a copy of exits so that if the calling program wants to add or remove mappings from it, then the exits mapping
 *        field won't be affected
 * - Although we won't be using the Location class as a key in the map, we'll use it to show later some ways to make your classes immutable
 * - And returning mutable objects from a getter in this way is one of the techniques here to make sure that that works
 *
 *
 *
 */
public class Location {

    private final int locationId;
    private final String description;

    private final Map<String,Integer> exits;

    public Location(int locationId, String description,Map<String,Integer> exits) {
        this.locationId = locationId;
        this.description = description;
        this.exits = new HashMap<>(exits);
        this.exits.put("Q",0);
    }

//    public void addExits(String direction , int location){
//        this.exits.put(direction,location);
//    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(this.exits);
    }

    protected void addExit(String direction , int location){
        this.exits.put(direction,location);
    }
}
