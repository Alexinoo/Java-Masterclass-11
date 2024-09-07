package java_files_input_output._23_read_write_with_nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/*
 * Java NIO - Read and Write to Text Files
 *
 * - In the last video, we finished working with java.nio and java.io , and we're getting the java.nio classes to
 *   create commonly used java.io streams
 *
 * - Let's look at how to read and write text files using java.nio only
 * - We said that doing IO using java.nio means that data is processed in blocks rather than 1 byte or character at
 *    a time
 * - To accomplish this, java.nio uses channels and buffers and also sometimes selectors
 *
 * Channel
 *  - A channel is the data source you're reading from or writing to
 *  - It can be a file, a socket or any other data source
 *  - To use a data source as a channel, we need a class that implements the java.nio.channel interface and that can
 *    connect to the data source
 *
 * Buffer
 *  - Is the container for the block of data that you want to read or write
 *  - Buffers are typed and that means that they can only hold 1 type of data and you can actually specify the size
 *     of the buffer as well
 *
 * Selectors
 *  - They allow a single thread to manage the IO for multiple channels
 *
 * /////////
 * - When working with streams, which we saw can be character/byte based as we saw, you need two instances of a java.io
 *    if you want to both read and write a file
 *      i.e. 1 instance for reading
 *           1 instance for writing
 * - For example, if we wanted to read and write to a file in a non-random way, we need to use FileReader and FileWriter
 * - But when using java.nio, we only need 1 instance of a channel to both read and write the same data source
 * - Also reading and writing is always buffered and we don't need to wrap an instance with a Buffered instance as we
 *   did when working with java.io
 * - So to be used as a channel, classes MUST implement java.nio.channels.Channel interface
 * - The JDK contains channels for several data sources including files, network IO, as well as sockets
 *
 *
 * /////////////
 * - To kickstart things off, we'll create data.txt and add 3 lines of vertex as follows
 *      - Line 1
 *      - Line 2
 *      - Line 3
 * - Now that we got a file to work with, we'll create a channel that's going to be used to read and write the file
 * - There are 2 ways to do this:
 *      - We can create a File from an open File instance
 *      - Or alternatively , use the FileChannel.open()
 *
 * - Let's start by creating a channel from an open file
 * - We can get a FileChannel from the instance of 3 classes
 *      - FileInputStream
 *      - FileOutputStream
 *      - RandomAccessFile
 *
 *
 * //////
 * - So we're actually going to be using a FileInputStream
 * - Use a try block and catch any IOException
 *
 *       FileInputStream file = new FileInputStream("data.txt");
         FileChannel channel = file.getChannel();
 *
 *      - We created the FileInputStream
 *      - Then call getChannel() to get a FileChannel from our FileInputStream instance
 *
 * - We said earlier that when using channels, you only need 1 channel for both reading and writing
 * - Now FileChannel is an exception to the rule,
 *      - IF you create a file from a FileInputStream, it's actually only open for reading
 *      - IF you create a file from a FileOutputStream, it's actually only open for writing
 *      - IF you create a file from a RandomAccessFile, this will depend on the parameter you
 *         pass to the RandomAccessFile constructor
 *
 * - If you remember, when we created a RandomAccessFile instance, you can specify whether the file is open to read
 *    or write by using either "r" or "rw" as the 2nd parameter you pass to the constructor
 * - The next step is to create the Buffer that's going to be used to read from the file
 *
 * /////
 * - But hang on a minute, under the hood, FileChannel is an instance of SeekableByteChannel and we're actually working
 *    with text
 * - We might think that it would be easy to read a String or text from a file using java.nio but it's not or at least
 *    it wasn't until fairly recently
 * - When you create a buffer to use with a channel, you have to specify the size of the buffer, which will specify
 *    how many bytes are read from the file at any one time
 *
 * /////
 * - Now what should the buffer size be for your file ?
 * - Right now all the lines are of the same length, and so you could create a buffer that has a size equal to the
 *    length of each line in the file
 * - But what about files that contain lines of varying different lengths
 *      - IF the first line is 25 bytes long
 *      - AND the next line is 100 bytes long,
 *
 *      - what should the buffer size be ?
 *          - IF it's 25, then on the second read, you'll only get 25 bytes of the second line
 *          - IF it's 100, then the first read will return all the first line and first 75 bytes of the second line
 * - So on each read, you'll have to pass the buffer for new lines
 *      - IF you only found part of the line, you'd have to do another read to get more of the partial line
 *      - Sounds complicated, but this is why you'd really want to do it that way when dealing with text files
 *
 * //////
 * - But fortunately, new methods were introduced in the java.nio.files class in Java 8, that makes it easy to read
 *    1 line of a text file at a time
 * - Perhaps now we're getting some idea of why the development community hasn't embraced java.nio for file IO
 * - It has been around for years but the performance improvement often aren't there and some pretty common ways of
 *   doing IO were in fact overlooked
 * - In fact the Files class was only introduced in java 7,
 *
 * //// java.nio.files.Files
 * - It contains static methods that typically deal with the file IO - Hurrah ,,..... Finally... Okay
 * - Since the file we're working with is small, we're going to change the code so that it reads the entire file in
 *   1 shot
 * - And since we're going to be using a method introduced in Java 8, this won't work with early versions of the jdk
 *      - So it's jdk 8 specific
 *
 * ////
 * - So we'll comment out on the code that we had written before
 *
 *       FileInputStream file = new FileInputStream("data.txt");
         FileChannel channel = file.getChannel();
 *
 *      - And create a Path obj instead
 *
 *          Path dataPath = FileSystems.getDefault().getPath("data.txt");
 *
 *      - Then create a List<String> obj and call Files.readAllLines(dataPath) and pass our Path path instance
 *          List<String> lines = Files.readAllLines(dataPath);
 *
 *      - Then loop and print each line using enhanced for loop
 *          - created printLines<List<String> allLines) to handle that
 *
 *
 * /////////
 * - So, essentially what we're doing is we've got the Path to the file as we've done before
 * - Then call Files.readAllLines()  which returns a List<String> objects
 * - Then each entry, correspond to one line in the file when we loop through the contents
 *
 * ////
 * - Now , if we want to access the file in a random fashion , then we'll get a FileChannel from an instance of
 *   RandomAccessFile and we'll look at that in upcoming videos
 * - But if you're working with a text file that you want to read or write sequentially, using the methods in the
 *   Files class is the way to go
 *
 * /////
 * - So we've seen how to read data, let's actually add some code to write some data using Files.write()
 *
 * //// Few things to note :
 * - When using the Files class to write a text file, each write is considered an isolate write in the sense that
 *   you don't open a file, call a write() a bunch of times then close the file
 * - Each call to Files.write() involves opening the file writing to it and then closing the file
 * - Because of that, if we wanted to write more than 1 line , you'd typically use something like StringBuilder
 *    to build the text up that you wanted to output and do it all in 1 shot
 * - If the amount of output is large , you could do it in chunks perhaps, something like 10 or 50 or maybe a 100
 *    lines at a time
 * - The Files.write() write bytes not Strings and so we're going to be calling the String.getBytes() to convert a
 *    String to bytes
 *
 *      Files.write(dataPath,"\nLine 4".getBytes("UTF-8"), StandardOpenOption.APPEND);
 *
 *      - So that's the code to write some data that file
 * - And running this now, we now have our lines ouf output appearing , and our file has now 4 entries
 *
 *
 * //////
 * - The only real thing here that could potentially be confusing is that we've got a String here that we want to
 *   write, but we're using .getBytes() and passing the parameter UTF-8 for the character set
 * - So we're converting that String to bytes in other words and that's because Files.write() needs bytes and it
 *   actually write bytes and doesn't process the String directly
 * - So we're passing UTF-8, as the character set , but when you're actually using readAllLines(), the UTF-8 is
 *    assumed
 * - So, if you want to write text that's in a different character set, you can specify the character set as a second
 *   parameter to readAllLines()
 *      - Ctrl+click on Files.readAllLines
 *      - We can see here that it's returning readAllLines(path, StandardCharsets.UTF-8) which is an overloaded method
 *         and we could do something similar to that and pass something like StandardCharsets.US_ASCII,
 *      - Though, we won't do that and UTF-8 is assumed
 *
 * ///// StandardOpenOption.APPEND
 * - This means that if you want to write to a file that already exists, and you want to append the bytes to the end
 *    of the file, then that would be the right one to use
 * - If you don't specify a 3rd parameter, the method assumes that you in fact want to start afresh and so ,
 *      - IF the file doesn't exist it'll create it,
 *      - IF it does exist, it'll truncate it or wipe out the existing contents
 * - You can explicitly ask for that behavior by passing StandardOpenOption.CREATE as a 3rd parameter and also
 *   StandardOpenOption.TRUNCATE_EXISTING as the 4th parameter
 *
 *
 * /// Files.readAllLines(Path path)
 * - Another thing to note, is that this method reads the entire file into memory
 * - What would you do if you needed to read a huge text file and you didn't want to read the entire thing into
 *   memory ?
 *      - Well, honestly, to be completely honest you would use the java.io classes
 *      - You could use a ByteBuffer and go through the pain of parsing each line
 *      - Or use Files.newBufferedReader to create a buffered reader that's using a ByteChannel under the hood
 *
 * ///
 * - There's really no rule or best practice that dictates using java.io over java.nio, if you're considering
 *   java.nio, you should benchmark both ways and see which one wins for your application
 * - The only case in which java.nio might be the recommended approach, is when you're working on a large
 *   application that uses multiple threads to do IO
 * - Otherwise java.io is absolutely fine to use
 *
 * ///
 * - However, as we'll see later though, java.nio is an improvement when it comes to dealing with the file system
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        try{

           /* FileInputStream file = new FileInputStream("data.txt");
              FileChannel channel = file.getChannel(); */

            Path dataPath = FileSystems.getDefault().getPath("data.txt");

            // Read all several lines
            List<String> lines = Files.readAllLines(dataPath);

            printLines(lines);

            // Adding/Appending some data to a file and then read again
            Files.write(dataPath,"\nLine 4".getBytes("UTF-8"), StandardOpenOption.APPEND);
            lines = Files.readAllLines(dataPath);
            printLines(lines);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void printLines(List<String> allLines){
        System.out.println("_".repeat(50));
        for (String line : allLines){
            System.out.println(line);
        }

    }
}
