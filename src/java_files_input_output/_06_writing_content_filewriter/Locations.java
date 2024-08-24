package java_files_input_output._06_writing_content_filewriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * ///////////////////////////////////////////////////////
 *
 * Writing Content - FileWriter class and Finally block
 * .....................................................
 *
 * //////////////////////////////////////////////////////
 *
 * - We've managed to use a static initialization block to make sure our locations data is only created once
 * - And we have taken the functionality out of the main class in terms of creating our locations and put it into
 *   this class : Locations
 * - But it would really make more sense to read it from a file rather than hard coding it like this
 *      - There's only a few locations but the full game's actually got 140 locations and the exits connecting them
 *         together are quite complex
 *      - So storing that info from a file is a much better solution
 *
 * ////////
 * - The first step we need here is to first write the data out to a file
 * - The code in the static initialization block is executed when the class is loaded which means that all the data
 *   is going to be available by the time we're going to execute any methods in the class
 * - Therefore, we can create main method to write out the locations and exits data to a file
 *
 * ////// FileWriter class
 * - One of the simplest ways to do this is to use a FileWriter object
 *
 *
 * ///////
 * - Create a main() in the Locations class object
 *
 * /////
 * - Create a FileWriter obj "locFile" and initialize it to null
 *
 * - Open a try-catch block to handle a checked IOException thrown by the FileWriter object
 *      - Initialize locFile to a new FileWriter obj - pass the name of the file as a parameter to the
 *          FileWriter class constructor
 *
 *      - Use a loop
 *          - Loop through the Map<Integer, Location> locations via locations.values()
 *              - call write() from OutputStreamWriter class that the FileWriter class extends from to write each entry
 *                to "locations.txt" file
 *                  i.e. locationId and locationDescription separated by a comma
 * - Catch IOExceptions
 *      - print stack trace
 *
 * - Finally
 *      - close the locFile instance by calling close() from the OutputStreamWriter class that the FileWriter class
 *         extends from
 *
 *
 * /////
 * - FileWriter.OutputStreamWriter.close() is very important and failing to close streams can really cause problems
 *   such as resource link leaks rather and locked files
 * - So if an output file is not closed , then the data can become corrupted or the file remains locked so that no
 *   other process is able to use it
 * - In fact, it is so important that Java 7 has introduced a much neater way of dealing with streams to ensure they're
 *   correctly closed, and we'll get to that and start using that soon
 *
 * //////
 * - For now though we need to understand why there are errors in the code
 *   - Each line that involves a FileWriter is showing the same error , and if we hover it, we get unhandled exception
 *     java.io.IOException
 * - There are 2 categories of exceptions in Java
 *      - Checked exception
 *      - Unchecked exception
 * - They work quite exactly the same , but the difference is that you can't ignore checked exceptions and
 *   java.io.IOException is one of them
 *      - In other words, our code is not allowed to just ignore it, and we really have to deal with it in some way
 *        and until we do, the program is not even going to compile
 *
 *
 * ///////
 * - Now that we know how to handle exceptions we could just catch the exception and this will fix the problem
 * - Add a try catch block after the FileWriter obj has been initialized for the first time, and
 *   add the code that creates the filename and also the one that writes to it in the try-block
 *
 * - Add a catch block that catches any IOException
 *      - print something more meaningful to the user
 *      - print exception stack trace error on the screen
 *
 * /////
 * - This actually fixes the error for now , and if we actually run this , locations.txt is actually created and
 *   we can open it to examine its contents
 * - And that's pretty much to it when it comes to writing data to a stream
 *      - The stream is opened in this case by passing a file name to the FileWriter's constructor
 *      - The data is then written using a for loop to go through each location
 *      - Then the stream is closed
 *
 * /////
 * - There's a problem to this code though & we need to add a little bit more complexity
 * - The problem is that if there's any error in the loop that writes the data will cause the catch block to be
 *   entered skipping over the call to the close()
 *      - and the file isn't going to be closed & we don't want that and ordinarily when dealing with files we
 *         wouldn't want to skip leaving a file open like that even if there was an error
 *      - as a result, the file is not going to be handled or properly closed
 * - To handle situations like this now, we need some cleanup code that must be executed no matter what
 * - The Java exception handling mechanism also has a finally block and if there's an exception raised in the try
 *    block, the code in the catch block will be executed
 *      - But then regardless of what happened, the finally block is also executed
 * - So we can be certain by using the finally block, the code will always be executed as long as of course JVM itself
 *    doesn't crash which is very unlikely
 * - A better way to code this is actually calling the close() in the finally block
 *
 *
 * //////
 * - However, doing that then introduces another error, which is the same java.io.IOException that got earlier
 *  - It turns out that close() also throws an IOException error and we'll have to call the close() and wrap it in its
 *     own try-catch block
 *  - We want to be extra careful not to cause more exceptions in the try block and we'll also check that the
 *    locFile obj is not null before attempting to call close()
 *
 * //////
 * - We've finally seen how to write data to an output stream in Java which is good
 * - Run again and ensure everything is working fine
 *
 * /////
 * - Up until Java 7, the code in our Location's main class was pretty much the standard template for writing data
 *   to a stream and reading from a stream looks much similar to that
 * - But Java 7, introduced an improvement that actually makes the code quite neater
 *
 * ////
 * - Simulate an error - create a dir with the name locations.txt - delete the existing "location.txt" before proceeding
 * - Now if we run this, we should actually get an error because of course it won't be able to create a file as there's
 *   already a folder there with the same name
 *      - We get java.io.FileNotFoundException - locations.txt is a directory
 *
 * ///
 * - But why is java.io.FileNotFoundException being caught when we have actually only looked for IOException in our
 *   code ?
 *      - The reason is that FileNotFoundException is actually a subclass of IOException and as a result it's caught
 *        by our catch block
 * - Notice, here that there was no attempt to close the file and the reason why it wasn't attempted was because locFile
 *   was set/ initialized to null
 *      - then the attempt to create a new obj from the line below failed
 *
 *           locFile = new FileWriter("locations.txt");
 *
 *      - and therefore lockFile was still null and consequently, we didn't try to close the file that was never
 *         created in the first place
 *      - and that's why it's very useful to have the code test for locFIle being null before trying to do something
 *  - Otherwise, if we hadn't done that then we would have gotten a NullPointerException because locFile wasn't like
 *    a valid object
 *
 *
 * //// Wrap up
 *  - Delete locations.txt director and re-run the program again
 *      - should work now
 *
 *
 */
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {
        FileWriter locFile = null;
        try{
            locFile = new FileWriter("locations.txt");
            for (Location location : locations.values() ) {
                locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
            }

            //locFile.close();

        }catch (IOException e){
            System.out.println("In catch block");
            e.printStackTrace();
        }finally {
            System.out.println("In finally block");
            try{
                if (locFile != null){
                    System.out.println("Attempting to close locFile");
                    locFile.close();
                }

            }catch (IOException e){
               e.printStackTrace();
            }
        }

    }

    static {

        locations.put(0 , new Location(0,"You are sitting in front of a computer learning Java" ));
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
        //locations.get(1).addExits("Q",0);

        // From location 2 (Hill): You can go to the following locations/rooms
        locations.get(2).addExits("N",5);
        //locations.get(2).addExits("Q",0);


        // From location 3 (Building): You can go to the following locations: Road to the West , Q to quit
        locations.get(3).addExits("W",1);
        //locations.get(3).addExits("Q",0);

        // From location 4 (Valley): You can go to the following locations: Hill to the West ,Road to the North
        locations.get(4).addExits("N",1);
        locations.get(4).addExits("W",2);
        //locations.get(4).addExits("Q",0);

        // From location 5 (Forest): You can go to the following locations: Road to the South
        locations.get(5).addExits("S",1);
        locations.get(5).addExits("W",2);
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
