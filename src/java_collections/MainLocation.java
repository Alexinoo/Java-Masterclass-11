package java_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainLocation {

    private static Map<Integer,Location> locations = new HashMap<>();
    private static  Map<String,String> vocabulary = new HashMap<>();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java" ));
        locations.put(1 , new Location(1,"You are standing at the end of the road before a small brick building" ));
        locations.put(2 , new Location(2,"You are at the top of a hill" ));
        locations.put(3 , new Location(3,"You are inside a building, a well house for a small spring" ));
        locations.put(4 , new Location(4,"You are in a valley beside a stream" ));
        locations.put(5 , new Location(5,"You are in the forest" ));

        //locations.get(0) - returns an instance of the Location class rather than a String
        // returns the actual obj , the location obj that has been defined above that corresponds to the location id

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

        vocabulary.put("QUIT","Q");
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");

        int loc = 1;
        while (true){
            System.out.println(locations.get(loc).getDescription());

            if (loc == 0)
                break;

            //Get available exists for location specified
            Map<String,Integer> exits = locations.get(loc).getExits(); // get map of valid exits from the current location which is : Road
            System.out.print("Available exits are ");
            for (String exit : exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1){
                String[] words = direction.split(" ");
                for (String word : words){
                    if (vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }
            if (exits.containsKey(direction)){
                loc = exits.get(direction); // gets location id based on the key that has been typed in
            }else{
                System.out.println("You cannot go in that direction");
            }

        }

    }
}
