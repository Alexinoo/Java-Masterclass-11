package java_files_input_output._29_copyfiles_with_filechannel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * java.nio.file - Using FileChannel to Copy Files
 * - Let's now look at how we can use a FileChannel to copy 1 file to another
 * - We'll copy the "data.dat" file that we've been reading and writing to a file called "datacopy.dat" file
 *
 * - To do so we'll use a method called transferFrom() , which is from the FileChannel class
 *
 * /////// transferFrom()
 *
 * long transferFrom(ReadableByteChannel src, long position, long count)
 *
 *  - Transfers bytes into this channel's file from the given readable byte channel.
 *  - An attempt is made to read up to count bytes from the source channel and write
 *    them to this channel's file starting at the given position.
 *  - This method is potentially much more efficient than a simple loop that reads from the source channel and
 *     writes to this channel.
 *
 * Params:
        src       – The source channel
 *      position  – The position within the file at which the transfer is to begin; must be non-negative
 *      count     – The maximum number of bytes to be transferred; must be non-negative
 *
 * Returns:
 *       - The number of bytes, possibly zero, that were actually transferred
 *
 * ////// transferTo()
 *
 * long transferTo(long position, long count, WritableByteChannel target)
 *
 *  - Transfers bytes from this channel's file to the given writable byte channel.
 *
 * Params:
 *  - position – The position within the file at which the transfer is to begin; must be non-negative
 *  - count – The maximum number of bytes to be transferred; must be non-negative
 *  - target – The target channel
 *
 * Returns:
 *  - The number of bytes, possibly zero, that were actually transferred
 *
 * //////
 * - Create a RandomAccessFile obj that points to "datacopy.dat" with "rw" mode
 *      copyFile = new RandomAccessFile("datacopy.dat","rw");
 *
 * - Open a FileChannel on RandomAccessFile instance
 *      copyChannel = new RandomAccessFile("datacopy.dat","rw").getChannel();
 *
 * - call transferFrom() from FileChannel instance
 *
 *      numTransferred = copyChannel.transferFrom(channel,0,channel.size());
 *
 *      - pass the source channel which is the one we're copying from
 *      - pass the position to start reading from the FileChannel
 *      - pass the no of bytes that we want to copy - used channel.size() - meaning all the data to be transferred
 *
 * - Notice we're calling the transferFrom using the destination channel - copyChannel
 * - This method returns the number of bytes transferred which we're saving in a long variable and printing the
 *   value out
 *
 * ////
 * - We get the number of bytes transferred is 24 and we get datacopy.dat file
 * - And if we open it , we get some mumbo jumbo data, which means that something went wrong here
 * - So, what is the problem here ?
 *      - Well from the source channel, we're using the same channel we used to read the integers in reverse order
 *      - The position we pass to the transferFrom() , isn't an absolute position but rather a relative one
 *      - In this case, the channel position is currently set to 1 byte after the last integer that we read
 * - If we want to copy the file starting at absolute position 0, then we need to set the FileChannel position before
 *   calling the transferFrom()
 *      - i.e.
 *          channel.position(0);
 *
 *
 * /////
 * - And now if we run this again, we get the num of bytes transferred this time is 40
 * - And if we have a look at our "datacopy.dat" file, we get contents which is identical to the contents from
 *   "data.dat" file
 *
 *
 * ////
 * - So this demonstrates that when working with methods that accepts channels and buffer positions, we need to
 *   be careful to read the methods documentation to see if it uses the position as an absolute value or a relative
 *   one
 * - In this particular case, the transferFrom(), is using a relative value, which means whatever you pass is treated
 *   as an index relative to the current position
 * - We also have a transferTo() which does exactly the same thing as transferFrom() except that we call it using the
 *   source channel rather than destination channel
 * - Let's actually change our code to use that
 *
 *      long numTransferred = channel.transferTo(0, channel.size(),copyChannel);
 *
 * /////
 * - Delete "datacopy.dat" file and re-run again
 * - and we should get the exact same results as we got with transferFrom
 *
 *
 * /////
 * - Usually, you use a method in the Files class to copy 1 file to another but if you've already got one of the
 *   files open, using transferFrom or transferTo is more efficient to do it depending on the OS
 * - We'll see how to do that with the Files class in the upcoming videos
 *
 * /////
 * - Remember to close all the channels manually if we didn't use try-with-resources
 */

public class Main {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream binaryFile = new FileOutputStream("data.dat");
             FileChannel binaryChannel = binaryFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(100);

            ///////  Using SeekableByteChannel Interface methods ////////

            byte[] outputBytes = "Hello World!".getBytes();
            buffer.put(outputBytes);
            long int1Pos = outputBytes.length;
            buffer.putInt(245);
            long int2Pos = int1Pos + Integer.BYTES;
            buffer.putInt(-98765);
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes2);
            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
            buffer.putInt(1000);


            // flip to write and write contents of the buffer
            buffer.flip();
            binaryChannel.write(buffer);

            RandomAccessFile ra = new RandomAccessFile("data.dat","rwd");
            FileChannel channel = ra.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
            channel.position(int3Pos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int3 = "+readBuffer.getInt());

            readBuffer.flip();
            channel.position(int2Pos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int2 = "+readBuffer.getInt());

            readBuffer.flip();
            channel.position(int1Pos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int1 = "+readBuffer.getInt());

            //////////////// Copying Files from data.dat //////////////////////////
            RandomAccessFile copyFile = new RandomAccessFile("datacopy.dat","rw");
            FileChannel copyChannel = copyFile.getChannel();
            channel.position(0);

            long numTransferred = copyChannel.transferFrom(channel,0, channel.size());
            //long numTransferred = channel.transferTo(0, channel.size(),copyChannel);
            System.out.println("Num Transferred = "+numTransferred);

            channel.close();
            ra.close();
            copyChannel.close();

         }
    }
}
