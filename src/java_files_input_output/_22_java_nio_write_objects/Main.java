package java_files_input_output._22_java_nio_write_objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Java NIO - Writing Objects with Java NIO
 *
 */

public class Main {
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

        int loc = 64;

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
