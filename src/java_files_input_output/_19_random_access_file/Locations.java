package java_files_input_output._19_random_access_file;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/*
 * Java IO - Random Access File
 *
 * - So far, we've been reading and writing files sequentially, we start at byte 0 and we read and write bytes one
 *    after the other until we reach the end of the file or until we finish writing the data
 *      - we don't jump around in the file in other words
 * - For example,
 *      - we don't read bytes 20 to 35 in the file
 *      - and then read bytes 50 to 100
 *      - and then bytes 10 to 15
 *
 * - And likewise,
 *      - we don't write out bytes 100 to 150
 *      - and then bytes 20 to 30
 * - So far, we've only moved forward and we've done so without skipping any bytes
 *
 * ////////
 * - Let's actually say we have thousands of locations in our file, so many that we don't want to store the locations
 *   in memory because they take up too much room
 * - So instead, every time a player moved to a new location, we would read that location from the file
 * - To do that , we need to jump to the place in the file where the information for that location is stored rather
 *    than reading the file sequentially we'd need to read it in a random fashion
 * - This is where the Random Access File class comes in
 * - But honestly, it would be far more efficient if in that scenario to use an embedded database like sql lite
 *   especially when we've got 2 data sets that are related to each other as we do in this case with locations and exits
 *      - Because of course every location has a set of exits
 * - But let's just pretend that for whatever reason we have to use a flat file
 *
 * - The first 4 bytes will contain the number of locations (bytes 0-3)
 * - The next 4 bytes will contain the start offset of the locations section (bytes 4-7)
 * - The next section of the file will contain the index ( the index is 1692 bytes long )
 * - The final section of the file will contain the location records (the data). It will
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) {
        try(RandomAccessFile rao = new RandomAccessFile("locations_rand.dat","rwd")) {
            rao.writeInt(locations.size());

            int indexSize = locations.size() * 3 * Integer.BYTES;
            int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
            rao.writeInt(locationStart);

        }catch (IOException io){
            io.printStackTrace();
        }

    }

    static {
        System.out.println("======================== Loading.... static initialization block =================");
        try(ObjectInputStream locFile  = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
            boolean eof = false;
            try{
                while (!eof){
                    Location location = (Location) locFile.readObject();
                    System.out.println("Read location "+location.getLocationId()+ " : "+location.getDescription());
                    System.out.println("Found "+ location.getExits().size() + " exits");

                    locations.put(location.getLocationId(), location);
                }
            }catch (EOFException e){
                eof = true;
            }
        }catch(IOException io){
            System.out.println("IOException "+io.getMessage());
        }catch (ClassNotFoundException cnfe){
            System.out.println("ClassNotFoundException "+cnfe.getMessage());
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
