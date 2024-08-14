package java_collections.part7_immutable_classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Immutable Classes
 *
 * - Let's look at making our class immutable , meaning that they can't be changed once they're created
 * - The techniques are valuable if you want instances of your class to be immutable
 * - But also using some of the techniques in our mutable classes is a great way to increase encapsulation and to reduce
 *   errors even if you're going to allow external code to modify your class instances
 *
 * - We'll use the Location class as an example, and you can argue that it's only a game and it's my code, why do I want
 *   to protect the object from external modification
 *
 * - To answer this question, you might want to consider modern software that supports 3rd party extensions or plugins
 *
 * Examples:
 *  - If you're using IntelliJ IDEA , support for other languages such as Python is provided by plugins that can be
 *    developed by 3rd parties
 *  - Many modern browsers have supported 3rd party plugins for years
 *  - Games allow external developers to create additional rooms or levels
 *  - Microsoft Office provides access to its objects to Macros written in VBA
 *
 * - All these examples expose the program's inner objects to external developers who don't have access to the source
 *   code and therefore can't know the implications of changes they might make to objects if allowed to do that
 *
 *  - Reading the API docs will help them and it's also reasonable for them to assume that if they're permitted to
 *    change fields and properties, they can go ahead and do so
 *
 *
 * // 1 Technique //////
 *
 *  - We discussed 1 technique which we used in the Location class which was the getExits() getter for the Map
 *      - Rather than returning the exits obj directly, which would expose it to the potential for being changed
 *        externally, we created a new HashMap obj that contains all the elements from the exits Map
 *      - The external code that needs to use the exits, as we did when we displayed the list of available exits to
 *        the player in our main class can do so and there's no chance of it changing our internal map
 *      - As it turns out we could in fact have returned a list of available exits as String which may have been
 *        convenient for our particular program, though that might have reduced flexibility
 *      - And if that kind of approach is taken as a rule, then we have to consider all the possible uses that external
 *          code may actually want to make from our map
 *      - The approach taken here doesn't restrict the external code from doing what it needs to do with the Map, it
 *        just prevents it from changing our map which is obviously good from our point of view
 *
 * // 2 Technique ////
 *  - Making our fields private and final, which means once they have been set, they can't be changed
 *  - We might argue that final is unnecessary here as fields are private which means they then can't be accessed
 *    externally anyway and while that's true, the use of final does provide 2 benefits
 *      - makes it clear to anyone reading the code that the field shouldn't be changed and we didn't of course forget to
 *        write a Setter
 *      - makes it clear that we don't change the fields if we change the code in the Location class at some time in
 *        the future
 *
 *  - The first point hints at the 3rd technique that was employed here, the fact that we didn't provide setters for
 *     the id and description fields
 *  - As we've seen, IntelliJ has an option of generating Getters, Setters and it can be tempting to get to the habit
 *    of automatically generating both getters and setters just in case you might need them down the track
 *      - But this is actually a bad habit to get into
 *      - It's much better to only provide a Setter if the class actually needs that
 *  - It might appear at first glance that the Location class does in fact need a setup for the exits available at each
 *    location and we indeed did use it to give each location its exists
 *      - And it may have made sense to leave the Setter in our class, so that if someone else wanted to extend the
 *        game , they could add new exits from existing locations to the new ones they add
 *  - But we're discussing how to create an immutable class, and so we'll look at a technique for setting the initial
 *    exits then preventing any further change
 *
 * - So if a class fields or elements of a mutable class like a List or a Map is to be set only once and never changed
 *   again, then what it needs to be done and where it needs to be set is in the constructor
 *
 * - We'll modify the Class constructor to accept a Map object and we'll change our Constructor signature and assign the
 *   passed in map to our class fields
 *      - And this means we can delete the addExits() altogether
 *
 *  - Change from
 *       public Location(int locationId, String description) {
                this.locationId = locationId;
                this.description = description;
                this.exits = new HashMap<>();
                this.exits.put("Q",0);
            }
 *
 *  - To
 *       public Location(int locationId, String description, Map<String,Integer> exits) {
                this.locationId = locationId;
                this.description = description;
                this.exits = exits;
                this.exits.put("Q",0);
            }
 *
 * - And consequently, we no longer need to use addExit anymore, because we're getting all the access basically when
 *   the object is constructed
 *
 * Next
 *  - We might look at this class now and think that it's now immutable, but there's 1 more change we need to make to
 *    make it truly immutable
 *  - And to demonstrate why we haven't achieved immutability first, let's modify the main() to use the changed
 *    Location class so that we can run the program and see where the program is
 *
 *  - So we need a temporary Map so that we can set up with the correct exits for each location and then we pass those
 *    to the Location constructor
 *       Map<String,Integer> tempExit = new HashMap<>();
 *
 *  - Then use IntelliJ Control + R and regex to replace
 *      locations.get().addExits("...");
 *
 *  - With
 *      tempExit.put("...");
 *
 * Next
 *  - Then we need to make sure that we're creating a new or setting tempExit to a new HashMap after each location
 *    so that the previous location exits aren't included in that new room
 *
 *  - For example
 *
 *     // From location 1 (Road): You can go to the following locations/rooms
        tempExit.put("W",2);
        tempExit.put("E",3);
        tempExit.put("S",4);
        tempExit.put("N",5);
 *
 *  - And so on
 *      // From location 2 (Hill): You can go to the following locations/rooms
        tempExit = new HashMap<>();
        tempExit.put("N",5);
 *
 *  - Updated them later to locations for readability
 *  - For example
 *      - location 1 has 4 exits
 *      - location 2 has 1 exit and so on...
 *  - All locations are now defined with various exits
 *
 * Next
 *  - is to move below lines
 *
 *      locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java" ));
        locations.put(1 , new Location(1,"You are standing at the end of the road before a small brick building" ));
        locations.put(2 , new Location(2,"You are at the top of a hill" ));
        locations.put(3 , new Location(3,"You are inside a building, a well house for a small spring" ));
        locations.put(4 , new Location(4,"You are in a valley beside a stream" ));
        locations.put(5 , new Location(5,"You are in the forest" ));
 *
 *  - into appropriate area after it's exits have been defined and pass the Map<String,Integer> locationX that's mapped
 *    to the constructor
 *
 *  - then pass locationX map to Location constructor for each locations
 *  - for example
        Map<String,Integer> location2 = new HashMap<>();
        location2.put("N",5);
        locations.put(2 , new Location(2,"You are at the top of a hill",location2 ));
 *
 * Next
 *  - The only special case we need to do is for the first room
 *  - Create location0 HashMap and pass it to the constructor with no exits
 *      Map<String,Integer> location0 = new HashMap<>();
        locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java",location0 ));
 *
 * - Running the program still works
 *
 * - However, we still have 1 problem that exposes our Location class
 * - For example:
 *      - Add below line
 *           location5.remove("S");
 *
 *      - which removes "South" from our map
 * - And notice now , that if go North, we've suddenly got no South , and that's because we managed to remove it
 *   from the class that's calling our Location class and was able to delete part of the map
 *
 * - So had we created a Map for each location instead of reusing tempExit, we could have wrecked havoc on the exits for all locations and not just
 *   the last one, and the reason we were able to do this is because we still had a reference to the exits map for location 5 which was the forest
 *
 * ///
 * - To cure this problem we need to use the same technique as we did for the getExits() only now we're going to apply it to a Setter instead of
 *   a to Getter
 * - What we mean by that , is in our constructor
 *
 *       public Location(int locationId, String description, Map<String,Integer> exits) {
                this.locationId = locationId;
                this.description = description;
                this.exits = exits;
                this.exits.put("Q",0);
            }
 *
 *      - we need to change
 *
 *           this.exits = exits;
 *
 *      - to
 *           this.exits = new HashMap(exits);
 *
 * ///
 *  - If we run this now and go North again, we can now go South even if we've added code to remove "S" from the location5
 *  - It hasn't affected our Location class at all because we've created a new HashMap based on the HashMap (exits) that was passed to us
 *
 * ///
 *  - At this point , we could say that the class is now fully immutable , once an instance is created, it cannot be changed
 *  - This means it could be used as a key to a map with no ill effects
 *
 *  - Though we did this to demonstrate the techniques not because there's any really good reason for making a Location a Key
 *  - Even though we don't want to use it as a key though,  these techniques have resulted in a very secure class that fully encapsulates its fields
 *  - Depending on the functionality a class must provide, it may not be possible to employ all these techniques
 *  - They should certainly be considered and we should be doing this when we're creating our classes considering these things
 *  - And as many of them as possible used in order to increase encapsulation and reduce errors as a result
 *
 * ///
 *  - There's also 1 further step that Oracle recommends when creating a fully immutable classes
 *
 *  - Check strategies for defining immutable objects from Oracle doc through the link below
 *
 *      https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
 *
 *  - We've covered
 *      point -1 : don't provide setter methods
 *      point -2 : make all fields final and private
 *      point -3 : declare the class as final
 *          - we didn't declare the calss as final but made all the fields final
 *      point -4 : if the instances fields include references to mutable objects, don't allow those objects to be changed
 *          - we do have references to a mutable object, the exits map
 *          - we have removed addExit() that modified the map
 *          - we also changed the constructor and getExits() so that we're not sharing references to the map
 *
 *  - It's not just overriding methods that can prevent our class from being immutable, adding new methods that expose our map would also do so
 *    and obviously hence the instructions - it's probably more accurate to say don't allow the class to be subclassed
 *      - Making a class final, prevents it from being subclassed
 *
 *      - A more sophisticated approach of making the constructor private also prevents the class from being subclassed
 */
public class Main {
    private static Map<Integer, Location> locations = new HashMap<>();
    private static Map<String, String> vocabulary = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Integer> location0 = new HashMap<>();
        locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java",location0 ));

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
