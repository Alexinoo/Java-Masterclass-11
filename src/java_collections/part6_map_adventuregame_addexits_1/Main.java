package java_collections.part6_map_adventuregame_addexits_1;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Adding Exits to the Game Adventure
 * //////////////////////////////////////////
 *
 * - Enhance this program to build the exits
 * - This is to set up so that when you're at a particular location , you've got a list of exits and you can only use those exits that are valid for
 *   that particular location
 *
 * Potential problem
 *  - We have a potential problem, and this is what happens if we run it and enter a valid location, the program is working nicely
 *  - But if we enter an invalid location say 25 , we actually get an exception
 *      - It does quite helpfully tells us that we can't go in that direction , but then what happens is that it loops back and tries to retrieve
 *        that location with the id of 25 and of course it get's itself into difficulty when it's trying to print it out
 *          - based on our get for that particular location
 *      - We won't fix that bug & we're going to extend the program anyway
 *
 * Next
 *  - We have defined our exits also as a Map<String,Integer> so that the key is going to be the direction
 *      - The key will hold initial letters such as N, S, E and W to signify "North", "South", "East" and "West" and "Q" to quit
 *      - The value will be the number of the location will get to if we go in that direction - sort of location id
 *
 *
 * Next
 *  - Map exits for each location
 *  - For example:
 *      -  From location 1 (Road): You can go to the following locations/rooms
 *          locations.get(1).addExits("W",2);  // at the top of a hill
            locations.get(1).addExits("E",3);  // inside a building, a well house for a small spring
            locations.get(1).addExits("S",4);  // in a valley beside a stream
            locations.get(1).addExits("N",5);  // in the forest
 *      - or press Q to quit - corresponds to 0
 *          locations.get(1).addExits("Q",0); // quit
 *
 *      - so on and so forth
 *
 *      - Map literals are still not available in Java, there was a talk to include them in Java 8, but it didn't happen as a result we'll have to
 *        use repeated calls to add Exit for each location which is a bit painful but it does get the job done
 *
 * Next
 *  - To get this working so that we can actually use those indicators, those directions for our program, it doesn't really need a lot of changes
 *  - Instead of reading an integer, we're going to read a character which we'll convert to uppercase to match the keys in the maps
 *  - Then we'll print out the keys in a location's exits map using the keySet() that we saw earlier
 *  - Then look up the new location matching the chosen direction and set the loc variable if the direction exists in the map
 *  - And if direction isn't a key in exits , then the location's not changed, and consequently, it's not going to be a bug
 *      - And that's the reason why we didn't bother fixing the bug that came up previously because we're going to fix it in this version of the code
 *
 *
 * Explanation
 *  - We're using getExits() to retrieve the map of valid exits from the current location
 *
 *      Map<String,Integer> possibleExits = locations.get(loc).getExits(); // prints {Q=0, S=4, E=3, N=5, W=2} for loc-1:Road
 *
 *      - this returns a copy, and if we did any changes to that map, it wouldn't affect the map field in the location instance
 *
 *  - We then loop through the possibleExits and print the keys for that location-1 ; {Q=0, S=4, E=3, N=5, W=2}
 *
 *       System.out.print("Available exits are ");
            for (String exitRoute : possibleExits.keySet()){
                System.out.print(exitRoute + ", ");             // prints Q,S,E,N,W
            }
            System.out.println();
 *
 *  - Then if direction isn't a key in exits , then the location variable will not be changed, and consequently, it's not going to be a bug
 *      and instead we'll print to the user the following "You cannot go in that direction"
 *
 *  - If the possibleExits Map contains the specified character : i.e.  Q,S,E,N,W
 *      - then get the location id for the specified character
 *      - the loop will iterate and print the current location plus the valid exits for that location
 *
 *           if (possibleExits.containsKey(direction)){
                loc = possibleExits.get(direction); //possibleExits.get("N") ; Get the integer corresponding to the direction entered based on the key that has been typed in
            }else{
                System.out.println("You cannot go in that direction");
            }
 *
 * Next
 *  - The entry
 *
 *      locations.get(1).addExits("Q",0);
 *
 *  - appears in every location exits map, and what we could do is we can remove it from main and add it to the Location constructor
 *      - comment out on them
 *  - and because the Location class has got a constructor and we're defining a new HashMap for the particular exits that are valid for this location,
 *    we can actually put in that there
 *
 *      this.exits.put("Q",0);
 *
 *  - and this means for each location that is added , we're automatically adding the ability to quit out of the program which saves us a bit of code
 *
 * Next ////////// Very Important //////////
 *  - We're now using the same map methods discussed earlier and the main difference is that the location.get() returns an instance of the Location
 *    class rather than a String
 *  - For example
 *
 *       locations.get(0) - returns an instance of the Location class rather than a String

 *      - returns the actual obj , the location obj that has been defined when we created the new class for each one of our map entries
 *
 *  - We've got the key now, which is the location id and the value for our location's map is a Location class
 *  - Then from there we can get the appropriate info from
 *
 *      locations.get(0).getExits()
 *  - Which gets a copy of the map of available exits for that particular location
 *
 *
 * Next
 *  - We've got a pretty basic interface and at the moment we can only type initials abbreviations
 *  - We can make an improvement to the user interface to allow a player to type full words like North, South, East
 *    or West if they wish and we can even do short phrases such as "go west"
 *  - And interestingly enough, a UI that allows you to type go west and have the computer understand was literally
 *    state of the art back in 1976 when the colossal cave adventure game which this is based on was actually written
 *  - We only need to understand the String class split()
 *
 * String.split()
 *  - String.split() in action
 *  - Example 1
 *      String[] road = "You are standing at the end of the road before a small brick building".split(" ");
 *      System.out.println(Arrays.toString(road)); //[You, are, standing, at, the, end, of, the, road, before, a, small, brick, building]
 *
 *  - Example 2
 *      String[] building = "You are inside a building, a well house for a small spring".split(", ");
 *      System.out.println(Arrays.toString(building)); //[You are inside a building, a well house for a small spring]
 *
 * Explanation to Example 1
 *  - The first use of the split() creates an array of Strings with 14 items , containing each of the word in the
 *    location String
 *  - The split() breaks up the string into individual items based on a delimiter which was the space passed to the
 *     split()
 *  - So essentially, it uses that as a delimiter, and each word that is separated by space has been added in the string
 *    array
 *
 * Explanation to Example 2
 *  - Does something very similar , but it's using the comma followed by a space and consequently we end up with an
 *    array containing only 2 entries this time
 *      - uses ", " which is passed to the split()
 *
 * - The split() is very useful for splitting down content , or splitting down a large string into smaller pieces
 *
 *
 *
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
        //locations.get(5).addExits("Q",0);

        int loc = 1;

//        while (true){
//            System.out.println(locations.get(loc).getDescription());
//
//            if (loc == 0)
//                break;
//
//            //Get available exists for location specified and print them
//            Map<String,Integer> possibleExits = locations.get(loc).getExits(); // get map of valid exits from the current location which is : Road
//            System.out.print("Available exits are ");
//            for (String exitRoute : possibleExits.keySet()){
//                System.out.print(exitRoute + ", ");
//            }
//            System.out.println();
//
//            String direction = scanner.nextLine().toUpperCase(); // Get character from Keyboard corresponding to the direction: N , S , E , W
//            if (possibleExits.containsKey(direction)){
//                loc = possibleExits.get(direction); //possibleExits.get("N") ; Get the integer corresponding to the direction entered based on the key that has been typed in
//            }else{
//                System.out.println("You cannot go in that direction");
//            }
//        }



    }
}
