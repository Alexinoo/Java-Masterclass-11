package java_files_input_output._11_readwithscanner_bufferedreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*
 *
 * Java IO - BufferedReader
 *
 * - We'll now read in the directions.txt file that we've written, and it's a very similar process , but we'll use
 *   a BufferedReader instead
 * - A BufferedReader reads text from the InputStream and buffers the characters into a character array
 * - We'll still create a FileReader but we'll use it as an InputStream for the BufferedReader to use
 * - Reading chunks of data from a stream such as a file is more efficient than reading just a few characters at a
 *   time
 *
 * ////
 * - The advantage with that is that the overhead of seeking to the correct track and sector on your disk drive are
 *   significant
 * - It actually takes a while to find the correct space on the disk drive to read from and if you have to do that
 *   many times because only a few bytes are read at a time , then the seek times for this really do add up
 *      - So consequently, BufferedReaders can be a lot more streamlined to be faster and more efficient
 * - You can actually specify the size of the buffer, but the default is 8192kb and that's enough for most purposes
 * - Our files here are both smaller than 8192kb and that means the entire content of the file will be read into
 *    the buffer in a single read and be available for the Scanner to use as it needs more data
 *
 *
 * /////
 * - Let's look at reading the exits with the old try-catch-finally but we'll later convert our code to use try
 *   with resources later on
 * - Create a scanner obj
 *       scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
 *
 *      - the FileReader obj is passed to the BufferedReader constructor
 *          - then the BufferedReader obj itself is passed to the Scanner's constructor
 *      - these classes are designed to be used this way and closing the scanner will actually close the BufferedReader
 *          because it extends from the Reader class that implements Readable and Closeable Interfaces
 *          - we can check the BufferedReader's close() and we can see it calls the close() of the Reader class
 *
 * - set our delimiter
 *      scanner.useDelimiter(",");
 *
 * - loop with a while loop as long as scanner.hasNextLine() returns true
 *      - Read a single line/filed and extract that field from the scanner obj and skipping commas in the process
 *          - read loc with scanner.nextInt()
 *          - skip the first comma with scanner.skip(scanner.delimiter())
 *              - so that it knows when to stop reading each piece of information
 *          - read description with scanner.next();
 *          - skip the second comma with scanner.skip(scanner.delimiter())
 *          - read the destination as a String - with scanner.nextLine() which reads up to the EOL because that's
 *             the only data there
 *              - convert destination to an int by parsing it to Integer.parseInt(String destination)
 *          - print - just to see what is happening
 *              - loc : direction : destination
 *      - Then get a specific Location obj for the current location
 *          Location location = locations.get(loc)
 *      - And add exits to it , which is the direction and the destination
 *          location.addExit(direction,destination)
 *
 *      - Catch any potential IOException
 *          - print stack trace
 *      - Finally block
 *          - close the scanner obj if it's not null
 *
 * ///////
 * - The Scanner may not be the best way to parse this data and scanners can be notoriously difficult to use and can
 *   really take some trial and error to get one to behave properly with a particular data set
 * - In this example, we had to skip over the delimiter when reading strings , otherwise the delimiter is included in
 *    the String
 *
 * ////
 * - We got an error when trying to call location's object addExit() , and that's because we actually removed the method
 *   when trying to make our Location class immutable
 *      - So let's add addExit() to our Location class but add it as protected so that it's only available to classes
 *         in our package and any subclasses of Location class as well
 *
 * ////
 * - Run the program and test that both locations.txt and directions.txt are read/imported correctly
 *
 * ///
 * - Run the program from the main() and make sure it still actually run and works as expected
 *      - Move around different locations in our game and it seems our game is working well which is fantastic
 *
 * ///
 * - And obviously the code above was printing out the locations data as it was coming through when it was imported
 *      - We can see it has been read successfully from both the locations.txt and directions.txt files
 *
 * /////
 * - Another way to parse this data is to read it line by line and then we could actually use the String.split() to
 *   get each part of the line into a String[] array
 *      - Its then quite simple to parse the values that really should be integers
 *
 *      String input = scanner.nextLine();   -- reads the entire line like this "1,Q,0", instead of one of 3 fields
        String[] data = input.split(",");    -- split to String[] array using a delimiter(,) - ["1","Q","0"]
        int loc = Integer.parseInt(data[0]); -- extract loc - parse 1st element Integer.parseInt("1") --> 1
        String direction = data[1];          -- extract the direction "Q" - keeping it in a String format
        int destination = Integer.parseInt(data[2]); -- parse 3rd element Integer.parseInt("0") --> 0
 *
 *      - The next lines remain unchanged because they're still taking the same variable names and being processed
 * - The code above is a bit easier to understand and implement when we're dealing with data that's separated in
 *   some way
 *
 * //////
 * - We might be thinking that we're back where we started where we had more code than we had before
 * - So what is actually the advantage?
 *      - With the small no of locations that we have at the moment, the advantage is not really obvious
 * - In the next section, there's a download in the resource section and we'll use those files instead as they've
 *   got a lot more locations and as a result we'll see the advantages when you're dealing with larger text files
 *
 * ////
 *  - Run and make sure that the game is still working after doing the above changes
 *      - Play and ensure that all is working fine
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
                  dirFile.write(location.getLocationId() + ","+
                            direction +","+ location.getExits().get(direction) + "\n");
              }
          }
      }
    }

    static {

        ////// Read The locations data from locations.txt - Use a Scanner with Readable - FileReader

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

        //////////// Now Read the directions.txt - exits - Use a Scanner with BufferedReader
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
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

        /*
            - code for populating locations and exits to our location was removed
            - no longer needed as both the locations.txt and directions.txt files were successfully written
            - the code in this block, reads from both and initializes the locations HashMap
         */

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
