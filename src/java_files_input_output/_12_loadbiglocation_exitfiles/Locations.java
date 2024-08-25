package java_files_input_output._12_loadbiglocation_exitfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*
 * Java IO - Load All Adventure Locations and Exits Files
 *
 * - Download gameData.zip from the resources section, and we'll use locations_big.txt and directions_big.txt files
 *
 * - Change the references and use the big files instead
 *      - locations.txt to locations_big.txt
 *          - contain the entire locations for the entire game
 *
 *      - directions.txt to directions_big.txt
 *          - contains a lot more entries - exits for each location
 *
 * ////////
 * - Run the game and make sure everything is still working
 *
 * ////
 * - These are locations from original colossal cave adventure game
 * - It actually had a random element so that you don't end up stuck in a forest, which is quite easy to do with
 *   this version
 * - We can cheat a little bit though, by setting the starting location to say location 64 and sort of move to a
 *   different area
 *      - Update start location to 64 which should start us off in a more complex junction
 *
 * /////////
 * - Run the game and make sure everything is still working
 *
 *
 * /////////
 * - If you open the 2 files , locations_big.txt and directions_big.txt - can you imagine actually typing all that
 *   code in to initialize this manually into your class instead of reading it into a file
 * - We can see that this is going to be far more efficient when you've got lots of data to read that data in using
 *   some code rather than trying to type it directly into our java files that we did initially before we got into
 *   I/O section
 * - These files were extracted from the Fortran source from the original colossal cave game
 * - You might be wondering why these descriptions all in uppercase,
 *      - And that's because most of the terminals at the time, in the early's 1970 then, they couldn't actually
 *        display lowercase characters, believe it or not
 *
 * /////
 * - We've seen a simple way to write data to a file and to read it back again
 * - We've also looked at the finally block to ensure that the cleanup code is executed even if an exception is
 *   thrown
 * - We've seen the power of chaining objects together to allow a Scanner to read from a BufferedReader that uses a
 *   stream to get it's data
 * - We've also covered try-with-resources method that became available in Java 7 and that resulted in a much cleaner
 *    code as we've seen with some examples that we have covered
 * - We haven't used that in the example that reads the data back in because it's important to understand how to perform
 *   IO with older java versions when you have to maintain legacy code, which will happen quite a bit
 * - And also to make a good challenge for us to do it
 * - The code to read the data back is a bit cluttered catching exceptions and we don't actually do anything with
 *   those exceptions and unfortunately our options are a little bit limited here
 *
 *
 * //////
 * - When we had code in our main(), we could just specify that the method throws the exceptions and remove the catch
 *   block
 * - This approach can't be taken with a static initialization block because they're actually executed when the class
 *   is loaded
 *      - There is no way for any of our code to catch any exceptions that are thrown
 * - You can throw unchecked exceptions in a initialization block but not checked ones
 *      - Unchecked exceptions don't have to be caught
 *      - Checked exceptions MUST be
 * - With that said though, we can rewrite the code to use the java 7 try-with-resources to make it a bit tidier
 * - And on the next video, the instructor will set us a challenge to just do that
 *
 *
 *
 *
 *
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException{
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

        //// Read the Locations

        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileReader("locations_big.txt"));
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

        //////////// Now Read the Exits
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {

                /*int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                String direction = scanner.next();
                scanner.skip(scanner.delimiter());

                String dest = scanner.nextLine();
                int destination = Integer.parseInt(dest); */

                //// Using split(",")
                String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
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
