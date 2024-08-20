package java_files_input_output._15_bufferedwriter_and_challenge;

import java.io.*;
import java.util.*;
/*
 * Challenge
 *
 *  - Part 1
 *      - Modify the program so that it uses a BufferedReader to read in the locations data
 *      - Run the program to test it before moving on to Part 2
 *
 *  - Part 2
 *      - Modify the main method of the Locations class so that it uses a BufferedWriter to write the data
 *      - Open the locations.txt and direction.txt files to check that the data has been written successfully
 *      - You'll then need to make another change to the program to allow for the 0 (Quit) exits before using the newly created files
 *
 *      - Hint:
 *          - You may want to change the 3 instances of HashMap to LinkedHashMap in the Location class and one instance in the Locations class so
 *            that you can compare the files more easily
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) {

      /// Try-with-resources//////
      try(BufferedWriter locFileWriter = new BufferedWriter( new FileWriter("locations.txt"));
          BufferedWriter dirFileWriter = new BufferedWriter(new FileWriter("directions.txt"))
      ){

          for (Location location : locations.values() ) {
              locFileWriter.write(location.getLocationId() + ","+ location.getDescription() + "\n");
              for (String direction : location.getExits().keySet() ) {
                  dirFileWriter.write(location.getLocationId() + ","+
                            direction +","+ location.getExits().get(direction) + "\n");
              }
          }
      }catch (IOException e){
          e.printStackTrace();
      }

    }

    static {

        try( BufferedReader bufferedReader = new BufferedReader(new FileReader("locations_big.txt"))) {
            String line;
            while ( (line = bufferedReader.readLine()) != null){
                String[] data = line.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];
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
