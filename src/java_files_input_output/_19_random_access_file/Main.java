package java_files_input_output._19_random_access_file;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Random Access File
 *
 */

public class Main {
    private static Locations locations = new Locations();
    private static Map<String, String> vocabulary = new HashMap<>();

    private static int STARTING_LOCATION = 1;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Add Vocabularies
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");

       // int loc = 64;

        Location currentLocation = locations.getLocation(STARTING_LOCATION);

        while (true){

            System.out.println(currentLocation.getDescription());

            if (currentLocation.getLocationId() == 0)
                break;

            //Get available exists for location specified and print them
            Map<String,Integer> possibleExits = currentLocation.getExits(); // get map of valid exits from the current location which is : Road
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
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction)); //possibleExits.get("N") ; Get the integer corresponding to the direction entered based on the key that has been typed in
            }else{
                System.out.println("You cannot go in that direction");
            }
        }

        locations.close();

    }
}
