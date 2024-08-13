package java_collections.part6_map_adventure_game;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Adventure Game Colossal Cave
 *
 * - Model a small part of the original adventure game colossal cave , which allows player to move around various locations by typing in compass
 *   directions
 *
 * - Create a Location class
 *
 * Next
 *  - Create a Map<Integer,Location> locations
 *      - add new locations to the Map above
 *
 *      - add sample locations to Map<Integer,Location> locations
 *
 *           	locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java" ));
                locations.put(1 , new Location(1,"You are standing at the end of the road before a small brick building" ));
                locations.put(2 , new Location(2,"You are at the top of a hill" ));
                locations.put(3 , new Location(3,"You are inside a building, a well house for a small spring" ));
                locations.put(4 , new Location(4,"You are in a valley beside a stream" ));
                locations.put(5 , new Location(5,"You are in the forest" ));
 *
 *      - We're putting the original key there , which is the integer 0,1,2,3,4,5 - converted into an Integer object via autoboxing
 *      - Then creating a new Location obj, and passing it as the value
 *      - Then we're still using the same technique for our Location obj
 *          - we're passing the number, which is going to be sort of our location id
 *          - then a description of what the particular location is all about
 *
 * Next
 *  - Add some code that allows us to type in a number and it prints the location description that's retrieving from the map
 *
 *  - Initialize starting location to locationId - 1 "You're standing at the end of the road before a small brick building"
 *
 *  - Add a Scanner instance
 *
 *  - Add a while loop that runs as long as the condition is true
 *      - print the starting location
 *          - locations.get(1) returns Location instance
 *          - we can then chain getDescription as below
 *              new Location(1,"You are standing at the end of the road before a small brick building" ).getDescription()
 *
 *      - break out of the while loop when location is 0
 *
 *      - get the next location from the user through scanner
 *
 *      - if the location doesn't contain the key/location id entered , print to the user - that he can't go to that direction
 *
 */
public class Main {

    private static Map<Integer, Location> locations = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java" ));
        locations.put(1 , new Location(1,"You are standing at the end of the road before a small brick building" ));
        locations.put(2 , new Location(2,"You are at the top of a hill" ));
        locations.put(3 , new Location(3,"You are inside a building, a well house for a small spring" ));
        locations.put(4 , new Location(4,"You are in a valley beside a stream" ));
        locations.put(5 , new Location(5,"You are in the forest" ));

        int loc = 1;

        while (true){
            System.out.println(locations.get(loc).getDescription());

            if (loc == 0)
                break;

            loc = scanner.nextInt();

            if (!locations.containsKey(loc)){
                System.out.println("You cannot go in that direction");
            }

        }

    }
}
