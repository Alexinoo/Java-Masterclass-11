package java_files_input_output._13_challenge;

import java.io.*;
import java.util.*;
/*
 * Challenge
 *  - Rewrite the static initialization block in lecture "_12_loadbiglocation_exitfiles" package using
 *    try-with-resources instead of try-finally
 *
 *
 * ////
 * - You may have noticed that we stopped needing a scanner to read the exits when we changed the code to read a
 *   line at a time and split it
 * - Removing a Scanner is quite easy and we can get rid of it all together and use a BufferedReader instead
 *      BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))
 *
 * - Declare an input of type String variable
 *      String input;
 *
 * - Loop as long as dirFile.readLine() doesn't return null
 *        while ( (input = dirFile.readLine()) != null)
 *
 * - Use the split() to split the String input into an array using a comma delimiter
 * - Extract the required fields and parse if necessary to convert to integers
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException{

      /// Try-with-resources//////
      try(FileWriter locFile = new FileWriter("locations_big.txt") ;
          FileWriter dirFile = new FileWriter("directions_big.txt")
      ){
          for (Location location : locations.values() ) {
              locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
              for (String direction : location.getExits().keySet() ) {
                  dirFile.write(location.getLocationId() + ","+
                            direction +","+ location.getExits().get(direction) + "\n");
              }
          }
      }

    }

    static {

        try( Scanner scanner = new Scanner(new FileReader("locations_big.txt"))) {

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
        }


        //////////// Now Read the Exits
        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt")))  {
            String input;
            while ( (input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
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
