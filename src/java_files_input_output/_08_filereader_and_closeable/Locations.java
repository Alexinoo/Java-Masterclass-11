package java_files_input_output._08_filereader_and_closeable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*
 * /////////////////////////////////////
 * Java IO - FileReader and Closeable
 * ////////////////////////////////////
 *
 * - Let's now add code to write out exits for each location
 * - Using try-with-resources allow us to specify more than 1 resource and as a result we can create a second FileWriter
 *   obj to write the exits data out
 * - As the code loops through the locations, we'll add an inner loop to write the exits for each locations, rather
 *   than create 2 separate blocks of code
 *
 * //////
 * - Add a FileWriter obj - dirFile - to the try-with-resources block separated by a semicolon from the first one
 *  *
 *       try(FileWriter locFile = new FileWriter("locations.txt") ;
          FileWriter dirFile = new FileWriter("directions.txt")
      )
 *
 * - Then after the initial write of the first location, loop through all the exits for that location and write them
 *   out to the directions.txt file
 *
 *      for (Location location : locations.values() ) {
              locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
              for (String direction : location.getExits().keySet() ) {
                  dirFile.write(location.getLocationId() + ", "+
                            direction +", "+ location.getExits().get(direction) + " \n");
              }
          }
 *
 *      - so we're writing out locationId , direction and exits to a particular location
 *         e.g  0, Q, 0
                1, Q, 0
                1, S, 4
                1, E, 3
                1, N, 5
                1, W, 2
 *
 * - So that's a simple way to write data out to a file
 *
 *
 * //////////////  Reading Data - FileReader class////////
 *
 * - We've created the data files for what the game needs and let's use these files to initialize the games locations
 * - We'll comment out the code that's currently in the initialization block that was populating the Map<Integer,Location>
 *    locations HashMap with the data
 * - And now that we've written the locations.txt and directions.txt, let's attempt to read that data and initialize
 *   Map<Integer,Location> locations - HashMap from the 2 files instead
 *      - Comment it out first
 *
 * - We'll use FileReader objects to get data from the 2 input streams namely, the locations.txt and directions.txt
 *   that we've now generated
 * - The process is much similar to how we've written out the data, just the other way around
 *
 *
 * ////// Parse data - Scanner class
 *
 * - The main difference is that we have to parse the data into individual strings and integers and we'll use the
 *   Scanner class to just do that
 * - We'll see how it's done prior to Java 7 by using try-finally but will later convert the code to use try-with-rsources
 *   instead
 *
 *      Scanner scanner = null;
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
 *
 *      - Create a Scanner obj and initialize it to null
 *      - Open a try block
 *          - Initialize the Scanner obj and pass : A character source implementing the Readable interface to the
 *              Scanner constructor below
 *
 *              scanner = new Scanner(new FileReader("locations.txt"));
 *
 *               public Scanner(Readable source) {
                    this(Objects.requireNonNull(source, "source"), WHITESPACE_PATTERN);
                }
 *          - Then call new Scanner().useDelimiter(",") , which is a way to tell Scanner that our info is separated by comma
 *          - use a while loop that runs as long as new Scanner().hasNextLine() is true
 *              - will keep lopping as long as there's a line of input
 *
 *          - Start retrieving the data : "0,You are sitting in front of a computer learning Java"
 *              - read locationId and store that to a loc in variable
 *                  int loc = scanner.nextInt()
 *
 *              - skip the comma delimiter
 *                  scanner.skip(scanner.delimiter());
 *
 *              - and then read description and store that to a String description variable
 *                  String description = scanner.nextLine();
 *
 *              - print out the line read - just to see that it is importing correctly - wouldn't put that in our code
 *                  System.out.println("Imported loc: "+ loc + ": "+ description);
 *
 *          - Create a Location obj with the above info
 *                   Map<String, Integer> tempExit  = new HashMap<>();
 *
 *          - Add to Map<Integer,Location> locations - HashMap
 *                  locations.put(loc,new Location(loc,description,tempExit));
 *
 * /////////
 *  - We're now using a FileReader obj and we're passing the name of the file to FileReader constructor and that's
 *    going to ultimately get the data from the file stream
 *  - The FileReader stream is then passed to the constructor of the Scanner causing the Scanner to work with the
 *    data in the stream itself, rather than System.in that reads from the keyboard
 *      - which obviously is a different stream but the scanner still operates the same and then it's retrieving data
 *          from this time a file and not the keyboard
 *  - We could declare a FileReader obj variable and pass that to the scanner, but you'll actually find constructors
 *     chained together like this a lot when performing IO operations in Java
 *      - In fact, the actual classes are designed to work together in this fashion
 *
 * ///// Mini-challenge ///
 * - Why is the FileReader obj not closed despite the instructor saying that closing streams is very important ?
 *  - The reason is that when the scanner is closed, it's close() also takes care of closing any stream that it was
 *     using, provided that the stream object implements the Closeable interface
 *      - And the FileReader does do that
 * - We shouldn't really have used the term stream here, it's probably more accurate to refer to it as a Readable
 *   because the source for a Scanner must be an object that implements the Readable interface
 *
 * - If we Ctrl+click on Scanner class and look for the close()
 *      - We can see that it tests for the source whether it's an instance of Closeable
 *      - And it automatically calls source's close() if it is
 *      - Consequently, we don't need to worry about closing the FIleReader obj because it's automatically handled by
 *         the Scanner class itself when it's been closed down which is pretty cool with how that is handled for us
 *
 * - Remember that a class can implement more than 1 interface, so an object can be an instance of a Closeable and a
 *   Runnable at the same time
 *
 * /////
 * - Reading the directions.txt file to build up the exits for the location is very similar to reading the locations
 *
 * ////
 * - But will do something slightly different there and introduce a BufferedReader -  in the next video
 *
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

        // From location 2 (Hill): You can go to the following locations/rooms
        locations.get(2).addExits("N",5);

        // From location 3 (Building): You can go to the following locations: Road to the West , Q to quit
        locations.get(3).addExits("W",1);

        // From location 4 (Valley): You can go to the following locations: Hill to the West ,Road to the North
        locations.get(4).addExits("N",1);
        locations.get(4).addExits("W",2);

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
