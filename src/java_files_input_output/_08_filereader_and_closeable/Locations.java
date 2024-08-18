package java_files_input_output._08_filereader_and_closeable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*
 * Java IO - FileReader and Closeable
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException{

      /// Try-with-resources//////
      try(FileWriter locFile = new FileWriter("locations.txt") ;
          FileWriter dirFile = new FileWriter("directions.txt")
      ){
          for (Location location : locations.values() ) {
              locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
              for (String direction : location.getExits().keySet() ) {
                  dirFile.write(location.getLocationId() + ", "+
                            direction +", "+ location.getExits().get(direction) + " \n");
              }
          }
      }
     ///// Old Way /////
     /*   FileWriter locFile = null;
        try{
            locFile = new FileWriter("locations.txt");
            for (Location location : locations.values() ) {
                locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
                //throw new IOException("test exception thrown while writing");
            }

        }finally {
            System.out.println("In finally block");
                if (locFile != null){
                    System.out.println("Attempting to close locFile");
                    locFile.close();
                }
        } */
    }

    static {

        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()){
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: "+ loc + ": "+ description);
                Map<String, Integer> tempExit  = new HashMap<>();
                locations.put(loc,new Location(loc,description,tempExit));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (scanner != null)
                scanner.close();
        }

       /* locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java" ));
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
        locations.get(5).addExits("W",2); */
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
