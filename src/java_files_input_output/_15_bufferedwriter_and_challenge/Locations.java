package java_files_input_output._15_bufferedwriter_and_challenge;

import java.io.*;
import java.util.*;
/*
 * Buffered Writer and Challenge
 *
 * - We've looked at reading and writing data using character streams to write out the data we used the FileWriter
 *   class and to read it again we created FileReader objects
 * - We looked at the idea of buffering the stream so that large chunks of the data are read into memory by BufferedReader
 * - This prevents excessive access to disk because the data is only read from the disk when the buffer is empty,
 *    otherwise the FileReader continues to take data from the memory buffer
 *      - It's much more optimized instead of reading a character at a time
 * - You can also buffer data when it's written to a disk , and unsurprisingly, this makes use of a BufferedWriter class
 * - The FileWriter puts data into the buffer and the data is only written to the disk when the buffer is full
 * - Using a BufferedWriter, data is written in sizeable chunks rather than sort of character at a time
 *
 *
 * ///////////
 * - We didn't use a BufferedWriter to write the directions file
 * - The data from locations file was read from disk line by line and that resulted in many accesses to the locations.txt
 *   file
 *
 *
 * //////
 * - We'll cover byte streams which write binary data rather than character data to the streams and it would make
 *   sense though to have an efficient program to modify
 *
 *
 * ////////
 * Challenge
 *
 *  - Part 1
 *      - Modify the program so that it uses a BufferedReader to read in the locations data
 *      - Run the program to test it before moving on to Part 2 *
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
 * //////////
 * Solution
 * - Change the Locations.main() to use BufferedWriter instead of a FileReader
 *      - Write to both locations.txt and directions.txt files with locations data which comes from both
 *         the locations_big.txt and directions_big.txt
 *      - Ideally, both locations.txt and directions.txt data should be identical to the data in locations_big.txt and
 *        directions_big.txt files
 *
 * ////////
 * - At the moment we're using HashMaps and they have no guaranteed ordering of their keys and that's why in the hint,
 *   we're being encouraged to use LinkedHashMap
 *      - The reason for doing so is that the ordering will be the order that the keys were added to the Map instead
 *         of just HashMap which can't guarantee you the ordering of their keys
 * /////
 * - Change the 3 instances of HashMap to LinkedHashMap in the Location.java class
 * - Change the 1 instance of HashMap to LinkedHashMap in the Locations.java
 *
 *
 * /////////
 * - However, if we look at directions.txt and directions_big.txt , there are extra entries for the quit option
 *   which we've added with this code and they've now been added
 *      - directions_big.txt starts from 1
 *      - directions.txt starts from 0 - extra entries for the quit options , one for each location
 * - There are 2 ways to stop this:
 *      - Stop the Location constructor from adding the quit exit automatically
 *      - Change the code that writes out the data so that it doesn't write out any "Q" keys
 * - There are both pros and cons to both approaches here ,
 *      - If we prevent the Location class from automatically adding the Quit option, we're cluttering the data file
 *        with entries that aren't really part of the game
 *      - And there's a risk that someone editing the files may forget to add a Quit option for any new location that
 *         they create
 * - But if we instead change the program, so that it doesn't write the Quit exits then there's really no need to
 *    remember to include them in the data file
 *
 * ///////////
 * - So, we'll change the Locations.main() so that it doesn't write out location 0
 *       if (!direction.equalsIgnoreCase("Q")) {
                      dirFileWriter.write(location.getLocationId() + "," +
                              direction + "," + location.getExits().get(direction) + "\n");
                  }
 *
 *      - And once we do that, we should now find that the directions file is identical when we run again
 *
 *
 * ///// Testing
 * - We have read locations_big.txt and directions_big.txt using BufferedReader and populated that info to
 *    locations HashMap
 * - Then used BufferedWriter to write values from locations HashMap to write out locations.txt and directions.txt
 *    files
 * - Run Locations.main() and confirmed this was successfully done
 * - Run the game from the Main.java and confirmed the game is working fine
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
      try(BufferedWriter locFileWriter = new BufferedWriter( new FileWriter("locations.txt"));
          BufferedWriter dirFileWriter = new BufferedWriter(new FileWriter("directions.txt"))
      ){
          for (Location location : locations.values() ) {
              locFileWriter.write(location.getLocationId() + ","+ location.getDescription() + "\n");
              for (String direction : location.getExits().keySet() ) {
                  if (!direction.equalsIgnoreCase("Q")) {
                      dirFileWriter.write(location.getLocationId() + "," +
                              direction + "," + location.getExits().get(direction) + "\n");
                  }
              }
          }
      }
    }

    static {
        try( BufferedReader bufferedReader = new BufferedReader(new FileReader("locations_big.txt"))) {
            String line;
            while (true){
                line = bufferedReader.readLine();
                if (line == null)
                    break;
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
