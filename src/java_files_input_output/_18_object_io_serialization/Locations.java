package java_files_input_output._18_object_io_serialization;

import java.io.*;
import java.util.*;
/*
 * Object Input Output Including Serialization
 *
 * - We've written the locations and directions as text, as well as a binary stream
 * - Right now we read and write each obj field individually
 * - For example ,
 *      - we used readInt and writeInt methods to read and write a location's id
 *      - we used readUTF and writeUTF methods to read and write a location's description
 * - If an object had 20 fields, we'll actually have to call 20 methods
 *
 * ///////
 * - Instead of that, we can use the ObjectInputStream and ObjectOutputStream classes to read and write objects as a single unit
 * - When we write an obj to a file, it has to be translated to a format that can be stored to a file and then reassembled into an object later when
 *   it's read by an application
 * - This process of translating a data structure or object into a format that can be stored and recreated is called serialization
 * - In Java, when we want to use an object stream to write and read the objects of a class, we have to make the class Serializable
 *      - We do so by making the class implement Serializable interface
 *
 * /////
 * - This interface has got no methods, it's just used to give the JVM, a heads up that we may want to serialize the object to storage and to read it
 *   back again at some point
 * - When we want to make a class Serializable, it's strongly recommended that we declare a long field called serialVersionUID
 *      - Otherwise, the compiler will give us a warning
 * - This warning can usually be turned off when using an IDE and unfortunately sometimes off by default and it will then calculate a default value for us
 * - However, different compiler implementations may calculate different default values , and that can lead to problems down the road if we change
 *   the compiler we're using
 * - There are other compilers other than the ones included with the jdk
 * - For example
 *      - A class compiled with the standard oracle compiler for a desktop application would be compiled by a different compiler if you were to add it
 *        to an android project
 * - Therefore it's recommended that if we care about serialization, we have to set the serial version uid ourselves and set that value
 * - It's also recommended that the field be private since no other class should have to use it
 *
 * ////////
 * - But, what is this serial version uid field used for ?
 *      - Think of it as a sort of a version number for the class
 * - If we don't explicitly set it, the compiler will create it based on class details such as the number of fields and methods etc
 * - If we ever change a class , for example by adding another field or method, or change a field type, we'd have to change the serial version uid
 *   value
 * - When we read an object from a stream, the runtime checks the stored serial version uid against the one contained within the compiler class file
 *      - If they don't match, then there's a compatibility problem and the runtime will throw an InvalidClassException
 * - If we wanted later versions of an application to be able to load files created by earlier versions, we'll have to provide custom versions of
 *   the right object and read obj methods by overwriting them which are methods we used to read and write objects
 *
 * ///////
 * - The bottom line here is that we don't have to worry about declaring a serial version uid field unless
 *      a) we explicitly use serialization as we do as we use ObjectInputStream and the ObjectOutputStream classes
 *      b) there may be a compatibility issue down the road
 * - However some java developers and java docs recommend always declaring when a is true or whether b is also true
 *
 * //////
 * - Let's modify our code to use the ObjectInputStream and ObjectOutputStream classes
 * - First
 *      - Make our Location class implement Serializable interface
 *      - We don't get an error of implement any methods and that's because Serializable doesn't have any
 *
 * - Second
 *      - declare a serial version uid in Location class and assign it number 1 rep version 1
 *          - Ensure we get exact spelling there with case
 *      - Once we add it, intelliJ will complain that it's never used
 *      - Unfortunately, it doesn't recognize the field as the recommended one to include with serialization
 *
 * - Third
 *      - modify the Locations.main() to write the Location objects using an ObjectOutputStream
 *
 *
 * ///////////
 * - Currently, our Location class has got 3 fields , an int, a String and a Map
 * - int and String are primitive types, but Map is an object
 * - So, will that be serialized when a location obj is written to a file
 *      - In our case , it will be because LinkedHashMap implements the Serializable interface
 * - When we write a Location obj , the entire contents of the exits field will also be serialized
 * - If LinkedHashMap didn't implement Serializable, we'd actually be responsible for writing the code to store it's contents  to the file ourselves
 * - When we want an object to be serializable, all its fields will also be serializable and we have to keep that in mind when writing our own classes
 *
 * /////
 * - If a class we want to serialize, will have fields from other classes that you write, then you need to make sure that those other classes are
 *   serializable as well
 *
 *
 * ////
 * - Remember that when the class is loaded, the static initializer will run and read the locations from the "locations.dat" file
 *      - So by the time the main() runs, we know the locations are loaded and available to use
 *
 *
 * ///// Implementation
 *  - Create an ObjectOutputStream obj and pass to it a BufferedOutPutStream instance that also takes a FileOutputStream obj which in turn takes
 *       the "locations.dat" file
 *       ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))
 *
 *  - Loop through the location.values and call writeObject() on ObjectOutputStream instance and pass the location object
 *       locFile.writeObject(location);
 *
 * ////
 * - We can agree using ObjectOutputStream is considerably simplified code
 * - Since Location is Serializable, and all the non primitive fields in Location are Serializable,  all we really have to do is call writeObject()
 *   for each location object as we've done here, and we're actually done
 *      locFile.writeObject(location);
 * - Also note that we can use BufferedOutputStream to buffer the output and so literally all we had to do was to change the DataOutputStream obj
 *    to an ObjectOutputStream
 *
 *
 * /////
 * - Run Locations.main() , which runs OK, and let's take a look at the "locations.dat" file
 *      - Looks less human readable than it was before and
 * - In fact we're getting an error here that IntelliJ is telling us that the document contains very long lines and softwraps were forcibly enabled
 *    to improve editor performance
 *      - Also note there about wrong encoding
 * - This is actually fine because a file containing serializable objects is meant to nbe read by a java application and not by human or an IDE
 *
 *
 * //////
 * - If we take a closer look at the top of the "locations.dat" file, you can see that the package name's there and if you look carefully, there's
 *   some class names as well
 * - Before we move on to reading the objects, note that the DataOutputStream implements the DataOutput interface and the DataInputStream
 *   implements the DataInput interface
 * - The DataOutput and DataInput interfaces, contain the declarations for the writeInt() and readInt as well as as writeUTF() and readUTF() methods
 *    and other read/write specific type methods
 * - Because of that, object streams can contain a mix of serialized objects and primitive types
 *      - They're not limited to only containing serialized objects
 * - In our case we don't need to mix objects and primitives, but we could do if we actually needed to
 *
 *
 * //////
 * - Modify the static initializer block to now read from the "locations.dat" file
 *      - Create an ObjectInputStream obj - locFile in a try-with-resources block
 *
 *          ObjectInputStream locFile  = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))
 *
 *          - everything else remains unchanged and we're still using BufferedInputStream to buffer the data
 *
 *      - Read the contents of the locFile obj by calling readObject() on locFile instance
 *
 *          Location location = (Location) locFile.readObject();
 *
 *          - takes care of reading all the fields and the exits
 *          - locFile.readObject() returns an object and so we have to cast it to a Location obj
 *
 *      - Loop as long as eof is false and once EOFException is thrown
 *          - create a try - catch block
 *              - Extract both locationId and description() from location obj created by the readObject()
 *
 *              - Add the locationId and the location obj to the locations-HashMap
 *
 *          - catch EOFException
 *              - set eof boolean variable to true
 *
 *      - Catch IOException for try-with-resources
 *      - Catch ClassNotFoundException
 *          - is thrown when the runtime reads an object from the stream and it can't find the corresponding class
 *             on the class path
 *          - For example,
 *              - With another application that didn't contain the Location class tries to read the objects inside the
 *                locations of that file, then it would actually get a class not found exception
 *
 * /////////
 *  - Run Locations.main() and confirm the locations and exits are read correctly
 *  - Run Main.main() and ensure that the game is still working fine
 *
 * ////
 *  - The game is still working and we can agree that the code is a lot easier to read
 *
 *
 * /////
 * - For fun, let's temporary comment out the declaration of the serialVersionUID in the Location class
 * - If we run this again from the Main.main(), we get an error
 *      - an IOException
 *      - a NullPointerException as a result of the first one
 * - And this is because we didn't explicitly set the serial version id, and therefore the compiler assigned one
 *   when it recompiled the Location class
 * - However, it no longer matches the value we wrote to the file which was 1L and an exception is thrown when we
 *    try to read objects from the file
 * - And therefore, we have to uncomment the line the line
 *      //private long serialVersionUID = 1L;
 * - For this to work
 *
 *
 * /////
 * - We talked about an InvalidClassException would be thrown when the serialVersionUID don't match
 * - InvalidClassException is a sub class of IOException and so the existing catch block also catches the invalid
 *   class exception
 * - So let's go ahead and catch that first before catching the IOException first
 * - But before we run it let's first comment out the serialVersionUID again
 *      //private long serialVersionUID = 1L;
 *
 * //////
 * - And now when we run this, we actually get InvalidClassException thrown because for the same reason, the
 *   serialVersionUID no longer match
 * - Uncomment
 *      //private long serialVersionUID = 1L;
 * - run it again and this time, it should work
 *
 *
 *
 * //////
 * - Before we leave this topic, let's touch on what happens when 2 objects that we serialize point to the same
 *   object
 * - Let's say we want to serialize a class called foo, which contains a field for a bar object
 * - Now if 2 foo instances point to the exact same bar instance and we serialize the 2 foo instances to a file,
 *    the question is , will the file contain 2 bar objects or just one ?
 *      - Well, the answer is it'll only contain one
 * - Since both foo instances refer to the same bar instance, only 1 bar instance is serialized
 * - In other words, a serialized file will only ever contain one copy of the same instance which is cool
 *
 *
 * /////
 * - But now imagine that we want to do the same thing , but for some reason we want to create 2 files
 * - So, we write both foo instances to file 1 and then both to file 2
 * - So how many instances of bar will be written this time
 *      - Well the answer in that case will be 2
 *      - Object instances will be unique within a file ,but not across files
 * - In this case, when the 2 files are read back into an application, 2 distinct instances of bar would be created
 *
 *
 * /////
 * - Next, let's take a closer look at the java.io.random access file class
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) {
        try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location : locations.values()){
                locFile.writeObject(location);
            }
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
        }catch(InvalidClassException ice){
            System.out.println("InvalidClassException "+ice.getMessage());
        }catch(IOException io){
            System.out.println("IOException "+io.getMessage());
        }catch (ClassNotFoundException cnfe){
            System.out.println("ClassNotFoundException "+cnfe.getMessage());
        }

        ///////////////////////////////////////////
        ////////////////////////////////////////////

        /* Read via DataInputStream class - Once the file "locations.dat" is written - comment out and use the code
        *  above to read via ObjectInputStream

        try(DataInputStream locFile  = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
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
