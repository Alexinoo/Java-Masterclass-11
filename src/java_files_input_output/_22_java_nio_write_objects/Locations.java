package java_files_input_output._22_java_nio_write_objects;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/*
 * Java NIO - Writing Objects with Java NIO
 *
 *
 * /////////
 * - When you use Files.newBufferedReader() or Files.newBufferedWriter() , to create the BufferedReader and
 *   BufferedWriter objects, the underlying instance used to do the InputOutput is a java.nio instance rather than
 *   java.io instance
 *
 *
 * ///////////////
 *
 * - What about reading and writing objects ?
 *
 *  - There isn't a Files.newObjectInputStream or Files.newObjectOutputStream(), but there are Files.newInputStream and
 *     Files.newOutputStream methods which return an InputStream and an OutputStream
 *  - In short, you can construct an ObjectInputStream from an InputStream and an ObjectOutputStream from an
 *    OutputStream
 *  - We should be able to create an ObjectInputStream or an OutputStream from a path using a 2-step process
 *  - So we call the Files method to get the appropriate stream and then call the ObjectInputStream or
 *    ObjectOutputStream constructed directly using the stream you got from the Files method
 *
 * ///////
 * - work with locations_nio_objects.dat files
 *
 * - Read from locations_nio_objects.dat and write to locations_nio_objects_2
 *
 * Locations.main()
 * - Generating "locations_nio_objects_2.dat"
 *  - Create a Path obj
 *      Path locPath = FileSystems.getDefault().getPath("locations_nio_objects_2.dat");
 *
 *  - Create an ObjectOutputStream obj
 *      - Pass new BufferedOutputStream obj to ObjectOutputStream constructor
 *      - Pass Files.newOutputStream(locPath) to BufferedOutputStream constructor
 *
 *  - Loop through the values from the  Map<Integer, Location> locations : HashMap
 *      - call writeObject() from ObjectOutputStream instance and pass individual locations
 *
 * /////// Few Things to note :
 * - We're using Files.newOutputStream(locPath) to get an OutputStream using a Path instance by passing locPath which
 *   is using java.nio.file.Path instance
 * - Then secondly, we're doing something similar to what we did when we created FileOutputStream using the
 *    FileOutputStream constructor
 * - We're still wrapping it up in this BufferedOutputStream and so the IO will be buffered and then wrapping that up
 *    in a ObjectOutputStream
 * - The only change we've made to this code is the call to Files.newOutputStream(locPath) rather than the call to
 *   FileOutputStream constructor
 * - And so that's where the java.nio code comes in
 *
 *
 *
 * ////////////////
 * - Modify static initializer so that it uses Files.newInputStream to read "locations_nio_objects_2.dat" created
 *   from the Locations.main()
 *
 * - Create a Path obj/instance that points to the file generated after running Locations.main()
 * - Use try-with-resources
 *      - Create ObjectInputStream obj by passing a new BufferedInputStream object to ObjectInputStream constructor
 *      - Pass Files.newInputStream(locPath) to BufferedInputStream constructor
 *      - Initialize an eof boolean variable and set it to false
 *      - Loop as long as eof is not reached
 *          - Nest a try block
 *              - call readObj() on locFile instance and cast it to Location obj
 *              - add to Map<Integer, Location> locations : HashMap
 *
 *          - Handle EOFException by declaring a boolean eof variable and initialize it to false
 *              - Set eof to true from the catch block as it will always be thrown after the readObject() on ObjectInputStream obj
 *                  is done
 *      - Catch
 *          - InvalidClassException
 *          - IOException
 *          - ClassNotFoundException
 *      - In that order and print out the message
 *
 * /////
 * - The code is very similar or identical to what we used previously to read objects from locations.dat except
 *    that we're using the Files.newInputStream() using that method to create an InputStream
 * - Then we're wrapping that with a BufferedInputStream and finally wrapping that with an ObjectInputStream
 *
 *
 * ////
 * - So essentially we can use Files.newOutputStream and Files.newInputStream to create any stream that descends
 *   from input stream and output stream respectively
 *
 * //////
 *
 *  - Run Main.main() and ensure that we can still play the game
 *
 *
 * ////////
 * - At this point, we now know how to use java.nio classes to create commonly used java.io streams
 * - We'll start looking at how to read and write to text files using java.nio exclusively
 *
 * - Remember that doing IO using java.nio, means the data is processed in blocks rather than 1 byte
 *   or character at a time
 * - To accomplish this java.nio uses channels and buffers and sometimes selectors
 *
 * - Let's start looking at channels, buffers and selectors and use java.nio to do our InputOuput
 *
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        // Read from locations_nio_objects.dat and write to locations_nio_objects_2
        Path locPath = FileSystems.getDefault().getPath("locations_nio_objects_2.dat");
        try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
            for (Location location : locations.values()){
                locFile.writeObject(location);
            }
        }

    }

    static {
        System.out.println("======================== Loading.... static initialization block =================");
        Path locPath = FileSystems.getDefault().getPath("locations_nio_objects.dat");
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    locations.put(location.getLocationId(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        }catch (InvalidClassException ice){
            System.out.println("InvalidClassException "+ice.getMessage());
        }catch (IOException ioe){
            System.out.println("IOException "+ioe.getMessage());
        }catch (ClassNotFoundException cnfe){
            System.out.println("ClassNotFoundException "+cnfe.getMessage());

        }

        /* **** Read from locations_nio_objects ***

        try(ObjectInputStream locFile  = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations_nio_objects.dat")))){
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
        }catch(InvalidClassException ice){
            System.out.println("InvalidClassException "+ice.getMessage());
        }catch(IOException io){
            System.out.println("IOException "+io.getMessage());
        }catch (ClassNotFoundException cnfe){
            System.out.println("ClassNotFoundException "+cnfe.getMessage());
        } */
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
