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
 * - This is where the RandomAccessFile class comes in
 * - But honestly, it would be far more efficient if in that scenario to use an embedded database like sql lite
 *   especially when we've got 2 data sets that are related to each other as we do in this case with locations and exits
 *      - Because of course every location has a set of exits
 * - But let's just pretend that for whatever reason we have to use a flat file
 *
 * //////
 * - When accessing a file in a random fashion, it's important to understand the concept of a file pointer
 * - The file pointer is an offset in the file where the next read or write will start from
 *      - If the file pointer is set to 100, then the next time we call a read or write methods, the read or write will start at byte position 100
 *         in the file
 *      - If we're reading an int, the byte set positions 100 to 103 would be read since an int is 4 bytes
 *          - The byte position is 0 based
 *          - So, the first byte in the file is at position-0
 *      - Whenever we read or write the file, the file pointer is advanced by the number of bytes we read or wrote
 * - For example
 *      - If the file pointer is equal to 100 and we read 5 bytes, then the file pointer will be equal to 105 after the read/write has taken place
 * - We use the term offset when randomly accessing files
 *      - An offset is a byte location in a file
 *
 * - For example,
 *      - if a value has an offset of 100, that would mean that the value is located at byte 100 in the file and we'd want the file pointer to be set
 *         to 100 when we read/write the value
 * - The value could of course occupy more than 1 byte but the offset is where the values first byte is located
 * - The remaining bytes would follow that first byte sequentially
 *
 * /////////
 * - When using random access files, we refer to each set of related data as a record
 * - In our application, the location id, description and exits make up the record for a location
 *
 * ////
 *
 * When the user moves to a location, how will we know which bytes to be read from the file ?
 *  - Well if all the locations occupy the same length and we wrote them out in order
 *      e.g. location 1, followed by location 2 , followed by location 3 etc, that would be easy
 *  - We could figure out a location's offset from its location id and the fixed location length,
 *  - For example,
 *      - let's say every location occupied 30 bytes in our file
 *      - so the first location's offset would be zero and it's data would occupy bytes 0 through 29
 *      - and the second location would begin at byte 0 and then they'd byte 59 e.t.c
 * - Then when a player moved to location end , we'd figure out which bytes to read using a formula like this
 *      e.g startByte = (n - 1) * 30
 *
 *      - assuming that each location occupied 30 bytes in the file and in this location
 *
 * //////
 * - That would be great if we knew that each location was of a fixed length, but unfortunately our records don't have the same length
 * - The length for each location is different and that's because the description length and the number of exits differ from location to location
 * - Because of that we'd have to include an index in our file, which will store the offset and record length for each location and we'll have to include
 *   the location id in the index
 * - Given that reading a location is going to be a two-step process
 *      1) - We're going to get the index record for the location
 *      2) - We're going to use the index values and read the location data
 *
 * - So every index record has to be the same length, we wouldn't want to have an index for the index after all
 * - Typically an index contains a unique identifier for each data record, the offset of the record in the file and the length of the record
 *      - In java, that's 12 bytes
 * - The index for our application is going to contain the location id, the location's offset and it's record length, which matches the typical case
 * - Now because of the description the location record will always be longer than 12 bytes, so the index will be much smaller than the location's data
 *
 *
 * /////////
 * - We can access the index record we need by reading it from the file when the user moves to a new location or we could load the entire index into
 *    memory when the application starts
 * - Ideally we'd want to load the index into memory and the reason for that is that accessing memory is much faster than accessing files on disk,
 *    so loading the index into memory will always provide better performance
 * - Usually, the size of each index record will be smaller than the size of the data records, but when that isn't the case, then of course it
 *    wouldn't make sense to load the index into memory if we're using a random access file because memory is tight
 *
 * - Another important point is that the first data record starts at byte 0, but that won't be the case if we write the index before the data
 * - We have to save the offset of the data section, in our case locations to the file and that's typically saved near the top of the file
 * - Sometimes the number of data would be saved there and now for our application , we're going to load the index into memory
 *
 *
 * /////////
 * - Our file format will look like the following :-
 *
 *      - The first 4 bytes will contain the number of locations (bytes 0-3) - 141
 *      - The next 4 bytes will contain the start offset of the locations section (bytes 4-7) - 1696
 *      - The next section of the file will contain the index ( the index is 1692 bytes long - will start at byte 8 - 1699)
 *      - The final section of the file will contain the location records (the actual data for the game - will start at byte 1700).
 *
 * - So when we want to move to a specific offset, we'll use the RandomAccessFile seek() to move the file pointer
 * - We won't always have to do this and when we do, we want to do a bunch of sequential read/writes, we'll start by positioning the file pointer
 *    to the offset of where the first read/write will take place
 * - Now if the next read/write we want to do is immediately follows the previous one, we can just use the readInt, readLong, readUTF and the
 *   corresponding functions, writeInt, writeLong,writeUTF etc
 *
 * - The file pointer is always advanced by the number of bytes read or written, so if the next piece of data we want to read/write immediately
 *   followed the last piece that we read or wrote, then the file pointer will already be positioned correctly
 * - SO in that case we wouldn't need to call seek() first
 * - We only have to call the seek() when we want to jump to a different offset in the file and we'll see the examples of this when we move forward
 *
 *
 * ////////
 * - We'll change our application to use a random access file and to load the locations on demand
 *
 *
 * ////////////////////////////
 *
 * Create A Random Access File
 *
 * ////////////////////////////
 *
 * - Let's start changing our application to use a Random Access File and then to load these locations for the game on demand
 * - We'll use Locations.main() to use random access file to write out the locations
 *
 * - First step
 *   - Inside the try-with resources block
 *      - Create RandomAccessFile instance and pass the file name to RandomAccessFile's constructor
 *          RandomAccessFile rao = new RandomAccessFile("locations_rand.dat","rwd")
 *
 *          - RandomAccessFile takes the filename as the first arg , which is the file that we'll be writing to
 *          - The second arg indicates that we want to open the file for reading and writing and also we want writes to occur synchronously
 *          - You can open files as read only and we can also not specify that updates to the file have to occur synchronously
 *          - But if we do that and multiple threads can access the file , you'd actually be responsible for synchronizing the code yourself
 *              - In this case, it doesn't matter since it's only 1 thread that will access the file
 *              - But it's good to have the RAF class handle the synchronization when it does matter and therefore using rwd as the 2nd arg is
 *                 a good idea
 *
 *      - Write the number of locations to the file, by calling writeInt() on RandomAccessFile instance
 *           rao.writeInt(locations.size());
 *
 *      - Write the start offset of the locations section and once again since the file pointer is positioned correctly, we can go ahead and
 *         use writeInt
 *
 *      - Since the file pointer is positioned at byte 0, when a file is first created or opened, we don't have to call seek()
 *
 * - We're going to call the variable we use to read the file something different so we don't confuse it with the one that we're using here which
 *    is the one we're writing the locations with
 *
 * //////
 * - Each index record will contain 3 integers
 *      - location id
 *      - offset for the location
 *      - size or length of the location record
 *
 * - We're calculating the indexSize by noOfLocations by noOfInts contained in each record , in this case 3 by the numOfBytes contained in an integer
 *      indexSize = locations.size() * 3 * Integer.BYTES; // 1692
 *
 * - We're calculating the current position of the file pointer to the indexSize, to account for the value that we've already written to the file
 *      locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);  // 1696
 *
 *      - we also have to account for the integer we're about to write to the file, the location section offset we just created and we need to add
 *        the number of bytes to an integer
 *      - this will give us offset for the location section
 *      - cast to an int because the file pointer is a long value
 *
 *      - As an alternative we could also have added the index size to the size of 2 bytes , but we can use file pointer wherever possible
 *      - We could have written a long , but we're going to read-write ints wherever possible
 * - If we were working with an application with lots of data, such that the file offsets could be larger than an int could hold, then we'd have to
 *    stick to longs to make sure we don't exceed the maximum size of an int
 *
 * ///////
 * - The next section in the file is the index, but before we can write the index , we have to write the locations and that's because each index record
 *   requires the offset for the location, and of course we don't know the offset until we've written the location
 * - We could
 *      - write the location
 *      - and then write the index record for it,
 *      - and then write the next location
 *      - and then write the index record for it and so on and so forth but that will involve jumping back and forth in the file
 * - Disk access is expensive and it's even more expensive when it's not sequential
 * - What we can do is write all the locations and then we'll write the index as a whole
 * - To do that, we'll have to build the index in memory as we write the locations
 * - Since we want to jump back to the file when we're finished writing the locations, we'll save the current position of the file pointer so that
 *    we can jump back to it when we want to write the index since we'll write the index after the 2 integers we've already written,
 * - we'd be writing it to offset 8 which is where the file pointer was currently positioned after writing the no of locations and the location
 *    section offset
 *      long indexStart = rao.getFilePointer()
 *
 * ///////////////
 * - At this point, we're ready to write the locations
 * - We've already calculated the offset of the locations data section and we're going to assign that to a variable called startPointer which will
 *   update after writing each location
 * - And then we'll
 *      - loop through the locations
 *      - write out each location
 *      - create an index for it
 *      - and add an index record to a map
 *
 * ///////
 * - To do all this we need an index record class and we'll add a new Java class called IndexRecord to our project
 *
 * - Fields
 *      int : startByte
 *      int : length
 *
 * - Constructor
 *      IndexRecord(int startByte, int length)
 *
 * - Getters
 *      getStartByte() : int
 *      getLength() : int
 *
 * - Setters
 *      setStartByte(int startByte) : void
 *      setLength(int length) : void
 *
 * /////////
 * - We said that an index record could contain the location id, start offset and length , and when we load the index records into memory, the
 *   location id will act as a map key and that's why we don't have to store it in the class
 *
 *
 * //////
 * - Let's now start to write out each location
 *
 *
 *
 *
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer,IndexRecord> index = new LinkedHashMap<>();

    public static void main(String[] args) {
        try(RandomAccessFile rao = new RandomAccessFile("locations_rand.dat","rwd")) {
            rao.writeInt(locations.size()); // 141

            int indexSize = locations.size() * 3 * Integer.BYTES; // (141 * 3 * 4) = 1692
            int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES); // (1692 + 0 + 4) = 1696
            rao.writeInt(locationStart); // writes 1696

            long indexStart = rao.getFilePointer();

            int startPointer = locationStart;
            rao.seek(startPointer);

            for (Location location : locations.values()){
                rao.writeInt(location.getLocationId());
                rao.writeUTF(location.getDescription());
                StringBuilder builder = new StringBuilder();
                for (String direction : location.getExits().keySet()){
                    if (!direction.equalsIgnoreCase("Q")) {
                        builder.append(direction);
                        builder.append(",");
                        builder.append(location.getExits().get(direction));
                        builder.append(",");
                    }
                }
                rao.writeUTF(builder.toString());

                IndexRecord record = new IndexRecord(startPointer , (int)rao.getFilePointer() - startPointer);
                index.put(location.getLocationId(), record);

                startPointer = (int) rao.getFilePointer();
            }

        }catch (IOException io){
            io.printStackTrace();
        }

    }

    static {
        System.out.println("======================== Loading.... static initialization block =================");
        try(ObjectInputStream locFile  = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations_rac.dat")))){
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
