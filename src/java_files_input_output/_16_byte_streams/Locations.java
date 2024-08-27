package java_files_input_output._16_byte_streams;

import java.io.*;
import java.util.*;
/*
 * Byte Streams
 *
 * - We've looked at how to read and write text data using FileReader and FileWriter classes
 * - We've also looked at buffering the data to make the program more efficient which reduces the disk access time
 *    by reading larger chunks of the file into memory in case of a read operation or writing data to a buffer
 *      - then saving a large chunk to a file ina single operation when writing
 *
 * /////
 * - It's time to look at byte streams or binary data and we'll continue using our locations data for this example
 *   but once we've covered how to read/write binary data, we'll switch over and use diff examples so that we don't
 *   get too bored
 *
 * //////
 * - To clarify what is happening in the static initialization block, when we run Locations.main(), the class has to
 *   be loaded before it's main() can ultimately be executed
 *      - This means the code in the static initialization block is executed before the main()
 * - This means the locations and directions files have been read in before the main() is executed in this class
 *
 * //////
 * - We'll start by rewriting the main() to save the data that's written by static initialization block in a binary
 *   format
 * - Once we've got a binary format to experiment with then we'll change the static initialization block to read
 *   from this newly created file
 * - In terms of binary data, 1 advantage of dealing with data or byte stream is that we don't have to parse the data
 *    into various data types that were stored
 * - The reason for this is that a byte stream itself can be used to read and write any of the primitive types such as
 *   int , double , byte and can also handle String variables even though String isn't actually a primitive type
 * - Working with byte streams is much similar to and the difference is that we'll use FileInputStream and FileOutputStream
 *   classes instead of FileReader and FileWriter classes
 *
 *
 * ///////
 * - We'll buffer the data again using the BufferedInputStream and BufferedOutput stream classes
 * - We could work directly with these objects but they only handle raw bytes , so reading or writing a single raw
 *   byte or a specified number of bytes
 * - There are occasions where generally that's all you need but generally speaking when storing and retrieving data,
 *   you'll probably want to work with the java types
 * - The java.io package makes this easy by providing the DataInputStream and DataOutputStream classes and they provide
 *   methods such as readInt to read integers values from a stream
 *      - And it does this by reading 4 bytes from the Stream and building up the integer from the 4 bytes
 *
 *
 *
 *
 * ///////// - Use Try-with-Resources
 * - Create a DataOutputStream obj
 *      - DataOutputStream locFile = new DataOutputStream()
 *
 * - Pass a BufferedOutputStream obj to the above DataOutputStream's constructor
 *      - DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream())
 *
 * - Then, pass FileOutputStream obj to BufferedOutputStream's constructor
 *      - DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream()));
 *
 * - Then pass the name of the file name to FileOutputStream's constructor
 *      - DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")));
 *
 * ///////
 * - Loop through the Map<Integer, Location> locations but only locations.values
 *      locations.put(0,  new Location(0,"You are sitting in front of a computer learning Java" ));
        locations.put(1 , new Location(1,"You are standing at the end of the road before a small brick building" ));
        locations.put(2 , new Location(2,"You are at the top of a hill" ));
        locations.put(3 , new Location(3,"You are inside a building, a well house for a small spring" ));
        locations.put(4 , new Location(4,"You are in a valley beside a stream" ));
        locations.put(5 , new Location(5,"You are in the forest" )
 *
 *      - call writeInt(location.getLocationId()) on DataOutputStream instance obj and pass the location Id
 *      - call writeUTF(location.getDescription()) on DataOutputStream instance and pass the location description
 *      - Though optional
 *          - print to the user what is written : id and description
 *          - print the number of exits for that particular location : location.getExits().size() -1
 *      - call writeInt(location.getExits().size() - 1) on DataOutputStream instance obj and print the no of exits on the "locations.dat"
 *
 *      - Loop through the location exits keySet
 *          - ignore the entries for Quits "Q"
 *          - print the direction : "N","W","E" and exit location mapped to the direction , 2, 6 ,7
 *
 *          - call writeUTF(direction) on DataOutputStream instance obj and write the direction
 *          - call writeInt(location.getExits().get(direction)) on DataOutputStream instance obj and write exit location
 *
 *
 * ///////////
 * - We've used try-with-resources which is just the recommended way of doing that which means that we don't need finally
 *   and catch because we're throwing IOException from the main()
 *      - and we don't have to worry about finally because this is automatically setup to close our files when we
 *        finish processing them
 * - We're going to be writing "locations.dat" and we're using .dat because it's no longer a text file
 * - We need because of 2 reasons
 *      - So that it doesn't conflict with "locations.txt"
 *      - it's not a regular text file and effectively is going to store binary data and might not open with a normal editor
 *
 *
 * ////////
 * - Running the program and "locations.dat" is generated and if we try to open it, IntelliJ has a bit of fit but we'll
 *   tell it to treat as a text anyway and open it
 *      - It looks a little bit gibberish and has got some weird stuff in there
 *      - The bottom line here is that IntelliJ is saying this isn't a regular text file compared to say "locations.txt"
 *         which of course we could read but that was a text file and this is a binary file
 * - However, we can still see some of the information , say line 1
 *      - those bytes are ultimately going to be an integer corresponding to location id
 *
 *
 * /////
 * - Also , not how each location is printed in the console with the number of exits
 *      - And then the directions and exits locations are listed for that particular location
 * - So, basically that's the text representation of what has been written in that binary file
 *      - When working with binary files, it's a good idea to do something sort of debugging capability built in
 *        to print to console what is happening, if you're writing data to a file
 *      - Makes it easier to work on it in the future
 *
 * ////////
 * - We'll look at logging later since this output shouldn't be really shown to users of the program and it's better
 *   to write it to a log file where it's hidden to users but available to us as developers , for diagnosing errors in
 *   the future
 *
 *
 * ///////
 * - Notice that we created "locations.dat" file and secondly , did you pick the fact that we're now writing locations
 *   and exits all in the same file
 * - Since locations have different number of exits, the number of exits is written first followed by the exit data
 *   for that location
 * - There's no good reason we couldn't have done that for the text files as well, but it was useful to see how data from
 *   different files could be related and that's the reason why we used a different approach
 * - In case of the text files , they can also be readily read by humans and so it's worth spending time to decide how
 *   the data should be presented in that case,
 *      - whether a single file, or split into locations and exits as we did with text files
 * - In the case of binary data though, humans wouldn't be reading the contents of the file and the question in this
 *   case will be why use 2 separate files when a single file will do as there's no advantage of using 2 separate files
 *
 * ///////
 * - Note that the code to write byte stream is very similar to writing to a character stream , the FileOutputStream
 *   is used to open the file for writing and then we're buffering the output with the BufferedOutputStream obj
 * - We could have written code to produce bytes representing the integer and string values to be written but a bit
 *   fiddly doing that way though not too difficult and fortunately the DataOutputStream class deals with all that
 *   automatically
 * - All we need here to
 *      - write an integer is call writeInt
 *      - write an String is call writeUTF
 * - There are also methods to write all the java primitive types , so the DataOutputStream class includes
 *      - writeLong()
 *      - writeFloat()
 *      - writeDouble()
 * - Let's look at what the writeInt() method actually does
 *      - Ctrl + click on writeInt()
 *
 *     public final void writeInt(int v) throws IOException {
        out.write((v >>> 24) & 0xFF);
        out.write((v >>> 16) & 0xFF);
        out.write((v >>>  8) & 0xFF);
        out.write((v >>>  0) & 0xFF);
        incCount(4);
    }
 *
 *      - Notice we're using a Shift Right 0 Fill operator
 *      - But basically what it's doing there, by using 24, it's actually moving the top 8 bits, the highest byte
 *         into the lowest position by shifting them right 24 places if that makes sense
 *
 * /////////
 * - Pull up an image with an original value of 922,342,959
 * - Let's look at how various shift operations result in the 4 bytes being extracted to the file
 *
 * - The first case : (v >>> 24) - Right Shifting by 24 bits
 *      - results in the value : 00110110 , which is Hex 36 or 54 in decimal being moved to the right most byte
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

      try(DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
          for (Location location : locations.values() ) {
              locFile.writeInt(location.getLocationId());
              locFile.writeUTF(location.getDescription());
              System.out.println("Writing location "+location.getLocationId()+ " : "+location.getDescription());
              System.out.println("Writing "+ (location.getExits().size() - 1) + " exits.");
              locFile.writeInt(location.getExits().size()-1);
              for (String direction : location.getExits().keySet()){
                  if (!direction.equalsIgnoreCase("Q")){
                      System.out.println("\t\t"+ direction + ","+location.getExits().get(direction));
                      locFile.writeUTF(direction);
                      locFile.writeInt(location.getExits().get(direction));
                  }
              }
          }
      }
    }

    static {
        System.out.println("======================== Loading.... static initialization block =================");

       /* try(DataInputStream locFile  = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
            while (true){
                Map<String,Integer> exits = new LinkedHashMap<>();
                int locId = locFile.readInt();
                String description = locFile.readUTF();
                int numExits = locFile.readInt();
                System.out.println("Read Location "+locId+ " : "+description);
                System.out.println("Found "+numExits+ " exits");
                for (int i = 0; i < numExits; i++) {
                    String direction = locFile.readUTF();
                    int destination = locFile.readInt();
                    exits.put(direction,destination);
                    System.out.println("\t\t"+direction+ ","+destination);
                }
                locations.put(locId , new Location(locId,description,exits));
            }
        }catch (IOException e){
            e.printStackTrace();
        } */


        /////// We'll read from locations_big.txt and directions_big.txt files first and write to locations.data ////
        ////// Once locations.dat is written - then below code is no longer needed and we'll use DataInputStream instead
        ///// to read from "locations.data
        /// In other words, run once, and comment out below code

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
        System.out.println("======================== static initialization block Loaded.. =================\n\n");
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
