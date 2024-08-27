package java_files_input_output._17_readbinarydata_eof_exceptions;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/*
 * Reading Binary Data and End Of File Exceptions
 *
 * - Now let's read from the "locations.dat" file that is written from the Locations.main() via the binary DataOutputStream class
 * - Reading from a binary data is actually easier than reading the data from a text file because we don't need to do any String parsing to get
 *   integer values
 * - The DataInputStream class takes care of building primitive types such as int, double from the bytes in the file
 * - Let's go ahead and do that in the static initialization block
 *
 * ///////// Read-in the "locations.dat"
 *
 * - Use try-with-resources
 *      - Create a DataInputStream obj that stores contents of "locations.dat" as a stream
 *          DataInputStream locFile  = new DataInputStream()
 *
 *      - Pass a new BufferedInputStream obj/ instance to DataInputStream's constructor
 *          DataInputStream locFile  = new DataInputStream(new BufferedInputStream())
 *
 *      - Pass a new FileInputStream obj/instance to BufferedInputStream's constructor
 *          DataInputStream locFile  = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))
 *
 *      - Pass the "locations.dat" file name to FileInputStream's constructor
 *
 * /////////
 *
 * - Use a while loop that loops and exits the loop after we've finished processing the data
 *      - Create a Map<String,Integer> of exits
 *           Map<String,Integer> exits = new LinkedHashMap<>();
 *
 *      - Extract locationId from locFile via readInt() and store that into an integer variable
 *           int locId = locFile.readInt();
 *
 *      - Extract description from locFile via readUTF() and store that into an String variable
 *          String description = locFile.readUTF();
 *
 *      - Extract numOfExits from locFile via readInt() and store that into an integer variable
 *          int numExits = locFile.readInt();
 *
 *      - Loop through the numOfExits for that location
 *          - read the exits back in using a for loop to
 *              - Extract direction from locFile via readUTF()
 *              - Extract destination from locFile via readInt()
 *
 *          - add direction to the exits map
 *              exits.put(direction,destination);
 *
 *      - Save entire location to locations HashMap : locId, description and exits Map
 *          locations.put(locId , new Location(locId,description,exits));
 *
 *      - Catch any potential exceptions IOException
 *
 *
 * //////////
 * - We can also agree that reading from the "locations.dat" is also tidier because no parsing of data is required
 *      - We read integers and Strings from the methods in the DataInputStream class
 *
 * ////////
 * - One strange thing we've noticed is that the loop wile(true), doesn't have an exit condition to terminate the loop and in fact we're getting a
 *   warning from IntelliJ and this isn't a good way to write code hence the warning
 * - But if we attempt to write this by just referring to some examples on the internet or even in the java docs, then this seems to be a reasonably
 *   way to do it
 *
 * /////
 * - Run the program and make sure it works
 * - Here we don't want to execute the Locations.main() because we did that from the previous video so that we could create "locations.dat" file
 * - What we want to do now is to run the main() from the main class so we can actually test that the program is working
 * - This seems to be working OK
 *
 * ///////
 * - The code in the static initialization block in the Locations.java is executed when the Location's class is loaded from the main() in the
 *   main class
 *
 * /////// ***** Very Important ***********
 * - The java docs should be read carefully before applying it to your own code , and it's quite easy to pick up on the fact that the Data stream readers
 *   throw an IOException when there's no more data to be read from the stream
 * - And in fact they throw a subclass of IOException called EOF Exception and we should be really checking for that as a way to terminate the
 *   while loop rather than just using the IOException
 *
 * ///
 * - If we run again, Main.main(), we can see that the code in the Catch block is executed meaning that an IOException is thrown and caught it in the
 *   catch block
 * - However this isn't really the best way of terminating the while loop because the IOException is going to cause the program to jump out of the
 *   loop and be caught in the catch block but the code is really messy
 * - For example, what happens if an IOException is raised for another reason than the EndOfFileException
 *      - It's very difficult to treat real exception such as the FileNotFound or having insufficient permission to allow us to open it differently
 *        to the end of file being reached which in fact is an expected event, very few data streams are infinite after all
 *      - The point is, it's very easy to cause an exception but we'll just try to read the binary data from a file that doesn't exist by changing
 *         the file name
 *          - Just to sort of simulate what will happen - change name to "location.dat"
 *
 * //////
 * - If we run this again, we get a NullPointerException which we'd expect because the file doesn't exist, and we can see obviously that IOException
 *   was printed and so the code in the catch block did execute
 * - If we were debugging this, does that mean it's because the data stream ran out of bytes or was it some other reason
 * - We need to use a better mechanism to sort of determine what the error was and the way to fix this is
 *
 *      - We'll add another try block
 *      - Then create a boolean variable "eof" and set it to false
 *      - Then change while(true) to while(!eof)
 *          - Then in within the while loop add another try block and catch EOFException
 *          - Then all we want to do is set eof to true
 *
 * ////
 * - In short we'll continue looping until EOFException is thrown
 * - If we hit EOFException which is generated automatically by these classes, when it reaches the end of input
 * - We'll set the eof variable to true and then we can exit from the while loop automatically - causes the while loop to terminate nicely
 * - Another good thing is that any other exceptions going to be caught by the try-with-resources catch block and if we're debugging this
 *   we'll know that the problem isn't caused by the InputStream running out of data
 * - But we'll do something differently in production code such as logging the error and the file name
 *
 * ///
 * - Revert the filename to "locations.dat" and re-run just to make sure everything is working as expected
 *      - And surely, everything is working fine and we no longer get the EOFException that we were getting before
 *
 *
 * /////
 * - Working with byte streams is really no more complicated than reading and writing character streams but you do have to know the format of the data
 *   that you're reading
 * - In this example, we knew when to use readUTF() and that's because we'd written the original file and so it was just a case of using the
 *   corresponding read methods in the same order as would use the write methods
 * - So provided that you know the format of the data , reading from a byte stream is straight forward
 * - And of course we had to know the format of the text file so that we knew when to try and convert the data to an int, but in the case of a text
 *   file it's easy to open it in a text editor to work out the format of the data
 * - As we've established when we open the binary data file in the previous class, that's not so easy to explore
 * - For example, a sequence of 4 bytes in a binary file could be a single int value, 2 shorts or 4 bytes for example and you wouldn't know that
 *   if you weren't involved in the process that created that binary file
 * - And as result, you want to know the format of the data by reading the specifications from the system that produced that binary file so that you can
 *   read the code to import and read in that binary data
 *
 *
 *
 * //////
 * - Byte streams can be used when dealing with java primitive types and we'll look at how to handle binary IO when dealing with objects
 *
 *
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException{
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

        try(DataInputStream locFile  = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){

            /* *** throw a subclass of IOException called EOF Exception ***

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
            */

           /*  ****  A better way of sorting this out *** */

            boolean eof = false;
            while (!eof){
                try{
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
                }catch (EOFException e){
                    eof = true;
                }
            }

        }catch (IOException e){
            System.out.println("IO Exception");
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
