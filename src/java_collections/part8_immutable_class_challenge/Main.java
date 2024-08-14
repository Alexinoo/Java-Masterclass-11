package java_collections.part8_immutable_class_challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Immutable Classes Challenge
 *
 * But what exactly is wrong with allowing class to be extended ?
 *  - Let's have a look at our declaration of our location's map
 *
 *      private static Map<Integer, Location> locations = new HashMap<>();
 *
 *  - The Map is declared using generics, so that it accepts an Integer key and a value of type Location
 *
 *  - But if Location is extended, then instances of the new class could also be used
 *  - This is generally not a problem , and in fact it's incredibly useful
 *
 *  - However, if we use the Location type for the key instead of value and we wanted to ensure that only completely immutable objects could be used
 *    as the keys, then we'd have to prevent subclasses over which we got no control from being used
 *  - Anyone who wants to use the functionality of the Location class, once we prevent them from extending it, can still do so using composition
 *  - They create methods that then call the Location's class methods from an instance of a location
 *
 *  - They can still do almost everything that subclassing would have allowed them to do except use their own class where a location object was
 *    expected
 *  - Most of the time, you will not want your classes to be immutable
 *
 *  - For example:
 *      - A Bank Account class where the balance never changes would be of no little use no matter how much we'd love to checkbook on such an
 *        account
 *  - But even if we don't want an immutable class, we can use these techniques in various combinations to make your classes certainly more robust
 *    than if you didn't do anything at all
 *
 *  - The challenge is that there is still a problem with Location, It's possible to crash the program as a result
 *      - The problem is with the Location class itself
 *
 *  - So they don't waste time to crash it by entering invalid output
 *
 *  - There's also no consistency checking in the main()
 *      - If we created exits that refer to a location, say for example location 6, that doesn't exist in our locations map, then the program will
 *        also crash
 *  - But this problem is about finding a problem with the Location class
 *
 *
 * ////////////////////// Challenge
 *
 * - Work out what is wrong with the location constructor that would allow the program to compile and crash at runtime
 * - So when you identify the problem modify the code to fix it
 *
 *
 *
 *
 *
 * ////////////////////// Solution
 *  - Well, the problem is that the constructor will crash with a NullPointerException, if null is passed instead of a map
 *
 * //// To see this in action , update the first
 *
 *  - Update starting location to point to null
 *
 *      locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java",location0 ));
 *
 *  - and we're now passing a null map and if we run this
 *
 *      locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java",null ));
 *
 *  - the program is going to crash and we'll get a NullPointerException
 *
 * ////// solution
 *  - The solution is to test the constructor's argument, and we should not try to initialize the new Map if null is passed for the exits
 *
 *       if (exits != null){
            this.exits = new HashMap<>(exits);
        }else{
            this.exits = new HashMap<>();
        }
 *  - Test the exits variable passed to the constructor
 *      - If it's not null,
 *          - Initialize exits map to a new HashMap with a copy of exits variable
 *
 *      - Otherwise
 *          - Initialize exits map to an empty HashMap with no valid data because obviously no data was passed to this constructor
 *
 *  - Everything else remains the same , and if we run this again, we no longer get the error and still do things as we would normally have done
 */
public class Main {
    private static Map<Integer, Location> locations = new HashMap<>();
    private static Map<String, String> vocabulary = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Integer> location0 = new HashMap<>();
        locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java",null ));

        Map<String,Integer> location1 = new HashMap<>();
        // From location 1 (Road): You can go to the following locations/rooms
        location1.put("W",2);
        location1.put("E",3);
        location1.put("S",4);
        location1.put("N",5);
        locations.put(1 , new Location(1,"You are standing at the end of the road before a small brick building",location1));

        // From location 2 (Hill): You can go to the following locations/rooms
        Map<String,Integer> location2 = new HashMap<>();
        location2.put("N",5);
        locations.put(2 , new Location(2,"You are at the top of a hill",location2 ));


        // From location 3 (Building): You can go to the following locations: Road to the West , Q to quit
        Map<String,Integer> location3 = new HashMap<>();
        location3.put("W",1);
        locations.put(3 , new Location(3,"You are inside a building, a well house for a small spring",location3));

        // From location 4 (Valley): You can go to the following locations: Hill to the West ,Road to the North
        Map<String,Integer> location4 = new HashMap<>();
        location4.put("N",1);
        location4.put("W",2);
        locations.put(4 , new Location(4,"You are in a valley beside a stream",location4 ));

        // From location 5 (Forest): You can go to the following locations: Road to the South
        Map<String,Integer> location5 = new HashMap<>();
        location5.put("S",1);
        location5.put("W",2);
        locations.put(5 , new Location(5,"You are in the forest",location5));


        // Add Vocabularies
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");

        int loc = 1;

        while (true){
            System.out.println(locations.get(loc).getDescription());
            location5.remove("S");

            if (loc == 0)
                break;

            //Get available exists for location specified and print them
            Map<String,Integer> possibleExits = locations.get(loc).getExits(); // get map of valid exits from the current location which is : Road
            System.out.print("Available exits are ");
            for (String exitRoute : possibleExits.keySet()){
                System.out.print(exitRoute + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase(); // Get character from Keyboard corresponding to the direction: N , S , E , W
            if (direction.length() > 1){
                for (String word : direction.split(" ")) {
                    if (vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if (possibleExits.containsKey(direction)){
                loc = possibleExits.get(direction); //possibleExits.get("N") ; Get the integer corresponding to the direction entered based on the key that has been typed in
            }else{
                System.out.println("You cannot go in that direction");
            }
        }



    }
}
