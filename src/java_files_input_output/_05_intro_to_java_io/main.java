package java_files_input_output._05_intro_to_java_io;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Adventure Game Challenge
 * ////////////////////////
 *
 * Change the program to allow players to type full words, or phrases, then move to the correct location based upon their input.
 *
 * The player should be able to type commands such as "Go West", "run South", or just "East" and the program will move to the appropriate location if there is one.
 *
 * As at present, an attempt to move in an invalid direction should print a message and remain in the same place.
 *
 * Single letter commands (N, W, S, E, Q) should still be available.
 *
 * Solution
 *
 * - Create a Map that contain words that will be recognised
 *      private static Map<String, String> vocabulary = new HashMap<>();
 *
 * - Add full and initials to the vocabulary map , that the user may type
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");
 *
 * - Check the length of the user input
 * - if length of the user input is > 1 or not a single letter command, we need to do more processing
 * - We'll call split(" ") on the user input which turns this into an array
 *
 * - Then loop through this array and check whether any word correspond with the keys from our vocabulary map
 *
 *      vocabulary.containsKey(word)
 *
 * - retrieve the value of the key from the vocabulary if any of the word matches the key and assign to direction
 *   variable
 *       direction = vocabulary.get(word);
 *       break;
 * - break from the enhanced foreach, and effectively we're ignoring all the other subsequent words
 *
 * - if there are no matches, the loop will terminate and attempt to look at the input in the exits map will fail
 *
 * - And of course if the direction length is not greater than one, all the code gets ignored and the code will
 *   process as if it was a single letter command as well
 *
 * Full Solution
 *
 * Map<String, String> vocabulary = new HashMap<>();
 *
 *      vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST","W");
        vocabulary.put("QUIT","Q");
 *
 * String direction = scanner.nextLine().toUpperCase();
 *
 *  if (direction.length() > 1){
 *          String[] words = direction.split(" ");
                for (String word : words) {
                    if (vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }
 *
 *
 * ////////////////////////////
 *
 * Introduction to I/O
 * .........................
 *
 * ///////////////////////////
 *
 * - Now that we've looked at the basics of handling errors by catching exceptions, we're ready to look at input and
 *   output in Java
 * - There are several different approaches to java io
 *      - java.io package  - has a bewildering array of objects dealing with different io methods
 *      - java.nio package - introduced in Java 7
 * - In this section, we'll look at the most useful ways of getting data into and out of our programs and the advantages
 *   of using various methods as we encounter them
 *
 * ///////
 * - But what do we mean by input and output ?
 *      - input involves reading data from a source
 *      - output involves writing it to some destination
 * - Quite often, the source and destination will be files on the computer's disk drives, but they can also be network
 *   connections and pipes as well as the computer's keyboard on screen
 * - In fact, we've used the Scanner class quite often to read from the keyboard
 * - Strictly speaking we aren't reading from the keyboard, we're actually reading from System.in
 *
 * /////
 * - We'll also be looking at files and directories, because quite often, you'll want to make a new directory to store
 *    your data or to present the user with a list of files to choose from
 * - The Files class provides methods for doing things like that and much more
 *
 * /////
 * - io can be performed using byte or character data and the methods used are pretty much similar for each
 * - It's just the actual classes used that will vary
 *
 * /// character data
 * - When reading and writing character data, the data is in a readable format & you can open the resulting files
 *    if it is files that are read or written in a text editor and make sense out of it
 *
 * /// binary data
 * - Binary data on the other hand, involves writing bytes that will not look very meaningful if you open the files
 *   in a normal text editor
 * - Which type should you use really depends on what the data represents
 * - And if you're creating a report file to be imported into a spreadsheet, you'd probably use a character stream
 * - And similarly, character should probably be the correct type for writing xml or JSON data
 *
 * - But when storing your program's variables or classes, it maybe appropriate to use a binary format, but it really
 *   depends on what you're trying to achieve
 *
 * /////
 * - Another distinction that needs to be made, is between serial or sequential files and random access files
 *
 * /// Sequential access
 * - Can be thought of as a stream of data that arrives at your program or is sent out from it, in a defined order
 *   with each piece of data following in sequence
 *
 * /// Random access
 * - Only applies to files and allows you to jump in the file or within the file retrieving or overwriting any data
 *   in any location within that file that you choose
 * - Similar to what a database program would work with some sort of index showing where a particular record is within
 *    the file, so that it's read without to be read through the thousand of earlier records first
 *
 * /////
 * - Compare sequential data to a stream and, Java uses various stream objects to deal with it
 * - Let's use the location data from the Adventure game example that we modified for the challenge in the Collections
 *    section
 * - We'll use and make some changes to create a file to store the game's locations
 * - So if we're going to read the location data from a file, it makes sense to create a separate class to deal with that
 *   and to keep details of that implementation out of our main program
 *
 * ///
 * - Since the main program uses a Map, to store the locations, one approach is to create a Locations class that
 *   implements the Map<Integer,Location> interface
 *      - We'll create Locations class and then implement a map based on the location
 *      - We'll need a HashMap to actually store the locations and then we'll use the code generator to implement
 *         all the abstract methods it needs to with the help of IntelliJ
 *
 * - Import/Copy the files from that challenge and copy here
 *
 * ////
 * - We'll need to create a class, Locations class , to be used to sort of map the actual location or each one of the
 *   locations that we have
 * - The original main program uses a Map<Integer,Location> to store the locations
 *
 * ///
 * - We'll create a Locations class that implements the Map interface and this will basically allow us to place all
 *   the initialization code into the new class and not have to make any changes in our main method
 *
 * ////////////////////
 *  Once done creating Locations class
 * ///////////////////
 *
 * - Replace
 *      private static Map<Integer,Location> locations = new HashMap<>();
 *
 * - With an instance of our Locations class
 *      private static Locations locations = new Locations();
 *
 * - Next, remove below line of code, which we had added to demonstrate the problems with exposing our exits to an
 *   external class and we don't actually need that anymore
 *      e.g. tempExit.remove("S");
 *
 * - We've managed to update our code, and we seem not to have any compilation errors which is good
 *
 * /////////
 * - We need to cut out all the code that initializes the locations and paste them into the new Locations class
 * - And since there only needs to be a single instance of the location data for the game, it doesn't really make
 *   much sense to allow multiple instances of the Locations class to each have their own copy of the data
 * - We saw about the static keyword that there's only 1 copy of static data that is shared amongst all class instances
 * - So it makes sense to keep this locations data static nad we looked at static initialization blocks and this
 *   is actually a good time to actually use one
 *
 * /////
 * - Let's create a static initialization block in the Locations class and pass our locations data there
 *      - Initializes the Map<Integer,Location> locations object when the Locations class is loaded
 *
 * - Note that the static initialization block will be executed once, and that's when the Locations class is loaded,
 *   and the class has a HashMap field called Map<Integer,Location> locations which is also static
 *      - So, there's only 1 copy of that shared by many instances of the Locations class that are created
 * - There' actually no real reason to create more than 1 Locations object, but as can't make that interface methods
 *   static, we have to allow at least 1 instance to be created, and once you do that you can't of course guarantee
 *   that someone else won't create more than one of them
 *
 * ///////
 * - There are ways to ensure that only a single instance of the class can be created, the singleton design patter
 *   is probably the simplest here,
 * - But in this app, making the data static and ensuring it's only initialized once is actually fine and using the
 *   static initialization block confirms that, that is actually the case
 *
 *
 * /////
 *  - Run the program and check that it's working nicely
 *
 * ////
 *  - The main class really doesn't care that it's now using the Locations class, rather than a HashMap that it used
 *    to use because we've implemented the Map interface in all the required methods
 *
 * ///
 *  - Next, we'll start looking at getting this data into a file and reading from the file rather than the way we're
 *    using it in the static initialization block, which is sort of hard coding
 *
 * ///
 *  - Let's actually start doing that in the next video
 *
 *
 *
 */
 class Main {

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

        int loc = 1;

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
