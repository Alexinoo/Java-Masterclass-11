package java_files_input_output._21_java_nio;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Java NIO
 *
 * Overview
 * - In Java 1.4, a new I/O package was added to the Java SDK
 * - They called it java.nio, the package was described as an improvement to Java I/O because the classes in java.io
 *   perform I/O in a non-blocking manner
 * - java.nio was also meant to fix some problems developers could run into when using the java.io classes when working
 *   with the file system
 *
 * - java.nio classes fall into 1 of 2 buckets
 *      - Those that deal with the file system
 *      - Those that deal with reading and writing data
 *
 * - When using classes in the java.io package, a thread will block while it's waiting to read or write to a stream or
 *   a buffer
 * - However, threads using java.nio classes will not block, they are free to continue executing , so java.nio was
 *   introduced as a performance improvement
 *
 * - However, many developers have argued that the java.nio package was a step backwards.
 *      - Some have shown that blocking I/O is faster than non-blocking I/O
 *
 * - Also working with java.nio class is more complex
 *      - As it has happened with other additions to the Java language, many developers still prefer to write code using
 *        the older java.io classes and you may decide to do that too
 *      - But it's important to also understand the new way of doing I/O, in case you're ever asked to work with code
 *        that uses it
 *
 * - We've learnt that the java.io classes work with streams (characters and binary)
 *      - Data is read 1 byte or character at a time and sometimes buffered, depending on which classes are used
 *
 * - When using java.nio, you'll deal with data in blocks, rather than processing one byte or character at a time, one
 *   block will be processed at a time
 *      - We use channels and buffers to accomplish this
 *
 * - Having said all that about java.nio , you can use java.nio methods to create a java.io stream
 *
 * ///////
 * - We'll use the adventure game project that we last used to read and write the data as an ObjectStream i.e. Before
 *   we looked into Random access
 *
 * - Let's start looking at the classes that read data from, and write data to a datasource
 *
 *
 *
 * /////////
 * - The first thing we're going to do is to update the Locations class so that it reads data from directions_big.txt
 *   and locations_big.txt files
 * - We'll first run the Locations.main() class to write out the data and then we'll change the Locations class static
 *    initializer which loads the data when the first instance of the Locations class is created
 * - Once that is done, we can call main() from the Main class to run the game
 *
 * //// ----> Locations.class
 *
 */

public class Main {
    private static Locations locations = new Locations();
    private static Map<String, String> vocabulary = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add Vocabularies
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");

        int loc = 64;

        while (true){
            System.out.println(locations.get(loc).getDescription());

            if (loc == 0)
                break;

            //Get available exists for location specified and print them
            Map<String,Integer> possibleExits = locations.get(loc).getExits(); // get map of valid exits from the current location which is : Road
            System.out.print("Available exits are ");
            for (String exitRoute : possibleExits.keySet()){
                System.out.print(exitRoute + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase(); // Get character from Keyboard corresponding to the direction: N , S , E , W
            if (direction.length() > 1){
                for (String word : direction.split(" ")) {
                    if (vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if (possibleExits.containsKey(direction)){
                loc = possibleExits.get(direction); //possibleExits.get("N") ; Get the integer corresponding to the direction entered based on the key that has been typed in
            }else{
                System.out.println("You cannot go in that direction");
            }
        }



    }
}
