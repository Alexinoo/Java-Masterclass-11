package java_collections.part6_map_adventuregame_challenge_2;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Adventure Game Challenge
 * ////////////////////////
 *
 * Change the program to allow players to type full words, or phrases, then move to the correct location based upon their input.
 *
 * The player should be able to type commands such as "Go West", "run South", or just "East" and the program will move to the appropriate location if there is one.
 *
 * As at present, an attempt to move in an invalid direction should print a message and remain in the same place.
 *
 * Single letter commands (N, W, S, E, Q) should still be available.
 *
 * Solution
 *
 * - Create a Map that contain words that will be recognised
 *      private static Map<String, String> vocabulary = new HashMap<>();
 *
 * - Add full and initials to the vocabulary map , that the user may type
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");
 *
 * - Check the length of the user input
 * - if length of the user input is > 1 or not a single letter command, we need to do more processing
 * - We'll call split(" ") on the user input which turns this into an array
 *
 * - Then loop through this array and check whether any word correspond with the keys from our vocabulary map
 *
 *      vocabulary.containsKey(word)
 *
 * - retrieve the value of the key from the vocabulary if any of the word matches the key and assign to direction
 *   variable
 *       direction = vocabulary.get(word);
 *       break;
 * - break from the enhanced foreach, and effectively we're ignoring all the other subsequent words
 *
 * - if there are no matches, the loop will terminate and attempt to look at the input in the exits map will fail
 *
 * - And of course if the direction length is not greater than one, all the code gets ignored and the code will
 *   process as if it was a single letter command as well
 *
 * Full Solution
 *
 * Map<String, String> vocabulary = new HashMap<>();
 *
 *      vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");
 *
 * String direction = scanner.nextLine().toUpperCase();
 *
 *  if (direction.length() > 1){
 *          String[] words = direction.split(" ");
                for (String word : words) {
                    if (vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }
 *
 *
 */
public class Main {

    private static Map<Integer, Location> locations = new HashMap<>();
    private static Map<String, String> vocabulary = new HashMap<>();
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


        // Add Vocabularies
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");

        int loc = 1;

        while (true){
            System.out.println(locations.get(loc).getDescription());

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
