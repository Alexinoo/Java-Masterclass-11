package java_files_input_output._05_intro_to_java_io;


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
 * ////////////////////////////
 *
 * Introduction to Java I/O
 * .........................
 *
 * ///////////////////////////
 *
 */
 class Main {

    private static Locations locations = new Locations();
    private static Map<String, String> vocabulary = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
