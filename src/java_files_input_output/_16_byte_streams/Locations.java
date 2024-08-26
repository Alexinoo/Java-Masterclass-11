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
 *
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) {

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
      }catch (IOException e){
          e.printStackTrace();
      }

    }

    static {
        System.out.println("======================== Loading.... static initialization block =================");

        try(DataInputStream locFile  = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
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
