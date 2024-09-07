package java_files_input_output._21_java_nio;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
/*
 * Java NIO
 *
 *
 * ///////
 * - We'll first create a Path obj that represents the file path for both "locations_big_nio.txt" and directions_big_nio.txt
 *
 *      Path locPath = FileSystems.getDefault().getPath("locations_big_nio.txt");
 *
 *      Path dirPath = FileSystems.getDefault().getPath("directions_big_nio.txt");
 *
 *      - FileSystems.getDefault()
 *          - This method retrieves the default file system of the JVM.
 *          - The default file system provides access to the file system of the host operating system.
 *
 *      - getPath("locations_big_nio.txt")
 *          - This method is called on the default file system object obtained in the previous step.
 *          - It creates a Path object that represents the specified file path ("locations_big_nio.txt" and directions_big_nio
 *             in this case)
 *
 * - The resulting Path object can be used to perform various file operations, such as reading from or writing to
 *   the file, checking if it exists, getting its attributes, and so on.
 *
 * - It is part of the NIO (New Input/Output) package introduced in Java 7, which provides a more flexible and
 *   efficient way to work with file systems and paths compared to the older java.io.File class.
 *
 *
 * //////
 * - Use try-with-resources block
 *      - Create BufferedWriter objects for writing text to files specified by the Path objects locPath and dirPath
 *
 *          BufferedWriter locFile = Files.newBufferedWriter(locPath);
 *
            BufferedWriter dirFile = Files.newBufferedWriter(dirPath)
 *
 *          - locFile and dirFile are the BufferedWriter instances created for the file paths represented by locPath and
 *            dirPath, respectively.
 *
 *
 *      - Files.newBufferedWriter(Path path)
 *          - This method is part of the java.nio.file.Files class, and it creates a BufferedWriter object that is
 *            linked to the specified file path.
 *          - The BufferedWriter allows for efficient writing of text data to the file.
 *
 *          - By default, this method uses the system's default character encoding and creates the file if it does not exist.
 *              - If the file already exists, it is truncated (i.e., its content is cleared).
 *
 * //// BufferedWriter
 * - A BufferedWriter buffers characters to provide efficient writing of text data.
 * - Instead of writing each character or string directly to the file, it stores them in a buffer and writes
 *   larger chunks at once, which improves performance
 * - It provides methods like write(), newLine(), and flush() to write data to the file.
 * - The flush() method can be used to force any buffered output to be written to the file.
 *
 *
 * ///////
 * - Important Note: Always make sure to close the BufferedWriter after use (or use a try-with-resources statement)
 *   to avoid resource leaks and ensure that all data is properly written to the file.
 *
 *
 * ///////////////////////////////////////////////////////////////////////////
 * ///////////////////////////////////////////////////////////////////////////
 *
 * - Differences between writing a file using java.io.BufferedWriter and java.nio
 *
 *  - java.nio.file.Files
 *
 *      Path locPath = FileSystems.getDefault().getPath("locations_big_nio.txt");
 *      Path dirPath = FileSystems.getDefault().getPath("directions_big_nio.txt");
 *
 *      BufferedWriter locFile = Files.newBufferedWriter(locPath);
        BufferedWriter dirFile = Files.newBufferedWriter(dirPath)
 *
 *  - java.io.BufferedWriter
 *      BufferedWriter locFile = new BufferedWriter( new FileWriter("locations_big_nio.txt));
 *      BufferedWriter dirFile = new BufferedWriter( new FileWriter("directions_big_nio.txt));
 *
 *  - All that's really changed is the way that we create the BufferedWriter
 *  - Instead of creating 1 directly by calling the BufferedWriter constructor which we have done on line 90 and 91
 *      BufferedWriter locFile = new BufferedWriter( new FileWriter("locations_big_nio.txt));
 *      BufferedWriter dirFile = new BufferedWriter( new FileWriter("directions_big_nio.txt));
 *
 *  - We're u sing java.nio.file.Files class to create the instance
 *      BufferedWriter locFile = Files.newBufferedWriter(locPath);
        BufferedWriter dirFile = Files.newBufferedWriter(dirPath);
 *
 *  - The Files.newBufferedWriter() accepts a java.nio.Path instance and we obviously declared the path instance as
 *    shown here below
 *      Path locPath = FileSystems.getDefault().getPath("locations_big_nio.txt");
 *      Path dirPath = FileSystems.getDefault().getPath("directions_big_nio.txt");
 *
 * - We'll take a look at Path and FileSystems classes in more depth later when we learn how to work with the
 *   FileSystem
 * - All we need to know at this juncture is that the Path class is meant as a replacement for the File class
 *
 * - Path class is a more robust class when it comes to working with files
 * - Classes in java.nio package work with path instances and not File instances
 *
 * /////////
 * - It's recommended practice to work with the Path class if using java.nio wherever possible
 * - Sometimes you may have to use the File class, that's because a method or constructor wants the File instance
 *   as a parameter, but when you do have a choice, make sure you use Path over File
 *
 *
 * //////
 * - So creating a BufferedWriter this way
 *      BufferedWriter locFile = Files.newBufferedWriter(locPath);
 *
 *      - creates a writer that uses java.nio to do it's output
 * - Remember that the BufferedWriter wraps another java.io class
 * - When we use the Files.newBufferedWriter(locPath) , a BufferedWriter is returned that performs IO using an instance
 *   of the java.nio class called ByteChannel
 *
 *
 * /////////
 * - Running Locations.main() generate
 *  - locations_big_nio.txt
 *  - directions_big_nio.txt
 *
 * Then Update static initializer to use Files.newBufferedReader to create the bufferedReader
 *
 *
 * ////////  Reading from Static Initializer block using java.nio.file.Files.newBufferedReader
 *
 * - Create the 2 Path instances again
 *
 *      Path locPath = FileSystems.getDefault().getPath("locations_big_nio.txt");
        Path dirPath = FileSystems.getDefault().getPath("directions_big_nio.txt");
 *
 * - Read locations  - using the Scanner class with Files.newBufferedReader class
 *      - Open a try-with-resources block
 *          - Create a Scanner obj and pass Files.newBufferedReader(locPath) to the Scanner constructor
 *          - call useDelimiter(",") on the scanner obj and pass "," as the delimiter
 *          - loop as long as scanner.hasNextLine() is true
 *              - read location id via scanner.nextInt()
 *              - skip the comma via scanner.skip(scanner.delimiter())
 *              - read location description via scanner.nextLine()
 *              - add id, and desc to : Map<Integer, Location> locations - HashMap()
 *
 *                   locations.put(loc,new Location(loc,description,null));
 *
 *
 * - Read directions  - using the BufferedReader class with Files.newBufferedReader class
 *      - Open a try-with-resources block
 *          - Call Files.newBufferedReader(dirPath) and create a BufferedReader obj
 *          - Create a line of type String
 *          - loop with while as true
 *              - call readLine() on the BufferedReader obj and store that to the String line literal variable
 *              - split the line String into an array via the split() using the comma delimiter
 *                  String[] data = input.split(",");
 *
 *              - extract location id , direction and destination from the String[]
 *                  int loc = Integer.parseInt(data[0]);
 *                  String direction = data[1];
 *                  int destination = Integer.parseInt(data[2]);
 *
 *              - get the current Location obj via locations.get(loc)
 *                  Location location = locations.get(loc);
 *
 *              - add the exits to the location obj returned
                    location.addExit(direction,destination);
 *
 *          - exit when line is equal to null
 *
 *
 *
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException{

        Path locPath = FileSystems.getDefault().getPath("locations_big_nio.txt");
        Path dirPath = FileSystems.getDefault().getPath("directions_big_nio.txt");

        try(BufferedWriter locFile = Files.newBufferedWriter(locPath);
            BufferedWriter dirFile = Files.newBufferedWriter(dirPath)){

            for (Location location : locations.values()){
                locFile.write(location.getLocationId() + "," + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()){
                    if (!direction.equalsIgnoreCase("Q")){
                        dirFile.write(location.getLocationId() +"," + direction + ","+location.getExits().get(direction) + "\n");
                    }
                }
            }

        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }


        /* **** Comparison with java.io.BufferedWriter *****
        try(BufferedWriter locFileWriter = new BufferedWriter( new FileWriter("locations.txt"));
          BufferedWriter dirFileWriter = new BufferedWriter(new FileWriter("directions.txt"))){
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
      */

    }

    static {
        System.out.println("======================== Loading.... static initialization block =================");

        ///////// Read locations

        Path locPath = FileSystems.getDefault().getPath("locations_big_nio.txt");
        Path dirPath = FileSystems.getDefault().getPath("directions_big_nio.txt");

        try(Scanner scanner = new Scanner(Files.newBufferedReader(locPath))){
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()){
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: "+ loc + ": "+description);
                locations.put(loc,new Location(loc,description,null));
            }
        }catch (IOException e){
            e.printStackTrace();
        }



        ///// Read Exits - with BufferedReader instead of Scanner obj
        try(BufferedReader reader = Files.newBufferedReader(dirPath)){
            String input;
            while (true){
                input = reader.readLine();
                if (input == null)
                    break;
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                System.out.println(loc + ": "+ direction+ ": "+ destination);
                Location location = locations.get(loc);
                location.addExit(direction,destination);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    /* *** Used for reading "locations_obj_nio.dat" to write "locations_big_nio.txt" and "directions_big_nio.txt" ****

    try(ObjectInputStream locFile  = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations_obj_nio.dat")))){
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
