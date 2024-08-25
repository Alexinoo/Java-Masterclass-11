package java_files_input_output._07_try_with_resources;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/*
 * ///////////////////
 *  Try with resources
 * ///////////////////
 *
 *
 * - Let's start with a mini-challenge
 * - Why did we declare FileWriter locFile = null; , above the main() instead of doing it inside try-block ?
 *      - Well the reason we had to do that is because try catch and finally blocks introduced new scope.
 *      - FileWriter declared inside the try block would not be available in the finally block
 *      - We can say that try and finally block are independent of each other
 *          - Any variable you declare in a given block isn't available to another block in inside another block
 *
 * ////////
 * - We might be thinking that's there's quite a lot of code when writing out data and most of it is concerned with
 *   catching the exceptions that the FileWriter might actually throw
 * - There isn't really much point catching an exception unless you're going to do something about it or with it
 *      and all our code is doing at the moment is just printing out a stack trace
 *
 *
 * /////
 * - We'll use a very similar code to read the data back in when the game starts, and if the game data can't be loaded
 *   then the rest of the game isn't going to work very well
 * - So there's really not much can be done when a file can't be opened and the best thing we can do here is to send
 *   the exception back to the calling program and alert the user
 * - Java insists that we handle exceptions and there are 2 ways to handle them
 *      - Throw them back up the call stack
 * - Since the main() is the entry point for this part of the program, we don't actually have any calling code that
 *   can handle the exception, the idea of throwing an exception instead of catching is very useful
 *     - We'll take a look at that now and how to do that
 * - Java docs uses the term catch or specify when dealing with checked exceptions
 *      - This means, the method must either catch the exception or specify that it's going to throw it
 *      - Often, it will make more sense to specify rather than catch and we'll do that with our main()
 *
 *        public static void main(String[] args) throws IOException{}
 *
 *      - and that means we're basically specifying that the main() throws IOException and now the caller knows that
 *         can be thrown from this method
 *      - and because IOException is a checked exception, the caller then must either catch the exception or sepcify
 *        it's going to throw it
 *
 * /////
 * - ultimately, we can now remove the part of the catch block since we're not dealing with the IOException as such
 *   anymore, we're just throwing the error
 * - we'll leave the finally block in because we still want to actually process the correct closing of the file
 * - we can also remove the second one as well, in the finally block, because we're throwing the same exception
 *   and we can now remove our try altogether
 *      - If there is any IOException that's going to be thrown in the finally block, it is actually going to be
 *          thrown back up the call stack because of how we specified on the main()
 *
 *     FileWriter locFile = null;
        try{
            locFile = new FileWriter("locations.txt");
            for (Location location : locations.values() ) {
                locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
                //throw new IOException("test exception thrown while writing");
            }

        }finally {
            System.out.println("In finally block");
                if (locFile != null){
                    System.out.println("Attempting to close locFile");
                    locFile.close();
                }
        }
 *
 * - But still we have done it, we can probably do without a try block altogether, because we're basically passing the
 *   responsibility for dealing with IOException in this case to another method,
 *      - In this example, we're leaving the finally clause because of the extra processing that we're doing to check
 *         whether we can close the file
 *
 * ///////////
 * - Simulate throwing an exception when writing out data to the file, and we can place throw statements in code that
 *   you want to test to make sure that our catch and finally blocks do actually work
 *      - Ensure you've deleted them immediately after testing before pushing your code to production ENV
 *
 *       for (Location location : locations.values() ) {
                locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
                throw new IOException("test exception thrown while writing");
            }
 *
 *      - If we run this, the first entry location is written but then we threw an exception and the finally block
 *         was executed and the file was closed successfully
 *
 *
 * /////////
 * - We've now successfully written the locations data to a file and let's actually write the exits
 * - The old way before Java 7 would involve creating a FileWriter obj for the exits file then writing exits mapped
 *   to each location in a loop , similar to what we have done with our locations loop
 * - And then finally checking that the FileWriter obj is not null and then closing it within the finally block
 * - We've seen how that code can be messy but Java 7 added a try-with-resources statement that makes everything
 *   a lot cleaner
 *
 * - Check the link for more info
 *      https://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html
 *
 * //////////
 * - Let's try to change the code we've written to use the try-with-resources statement and see how concise it
 *   makes our code
 *
 *       try(FileWriter locFile = new FileWriter("locations.txt") ){
          for (Location location : locations.values() ) {
              locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
          }
      }
 *
 *      - The code is now neater and we don't need the finally block anymore because with try-with-resources
 *         introduced in Java 7, it automatically ensures that the FileWriter stream is closed
 *          - regardless of whether the code executes normally or an exception occurs which is pretty cool
 *
 * ////////
 * - The doc that we just pulled has a section on the differences in the way that exceptions can be thrown.
 * - It's worth noting that this code does behave differently to the try-finally version
 *      - The differences occur if an exception is thrown when closing the stream in addition to an error in the
 *         try block
 * - In the try-finally example, it would have been the exception that was thrown by the close(), that was thrown
 *   up the stack, by writing the code this way causes the exception to be suppressed
 *      - And the exception from the try block is the one that's actually thrown back, up the stack
 * - This makes sense as it is very likely that whatever problem occurred while opening or writing to the stream is
 *   of more interest than the error closing the stream
 * - And as a matter of fact, the first error is probably the root cause of the error when closing it
 *
 * ////////
 * - So using try-finally, the first error would be hidden by the exception when closing the stream whereas this method
 *    ensures that the first error is the one thrown back which is highly likely to be the error that you want to be
 *    working with
 * - It's a subtle difference but definitely worth bearing in mind if you're converting existing code to use
 *   try-with-resources
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException{
      try(FileWriter locFile = new FileWriter("locations.txt") ){
          for (Location location : locations.values() ) {
              locFile.write(location.getLocationId() + ","+ location.getDescription() + "\n");
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

        // From location 2 (Hill): You can go to the following locations/rooms
        locations.get(2).addExits("N",5);

        // From location 3 (Building): You can go to the following locations: Road to the West , Q to quit
        locations.get(3).addExits("W",1);

        // From location 4 (Valley): You can go to the following locations: Hill to the West ,Road to the North
        locations.get(4).addExits("N",1);
        locations.get(4).addExits("W",2);

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
