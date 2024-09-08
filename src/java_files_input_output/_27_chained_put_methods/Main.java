package java_files_input_output._27_chained_put_methods;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * java.nio.file - Chained put/putInt Methods
 *
 * - We consolidated the code to make it a bit more efficient by writing a bunch of data putting that into the buffer
 *   before we actually wrote it out
 * - And the code is a little bit cleaner then the previous versions were, when we used multiple buffers
 *
 * /// Mini-challenge
 * - Let's comment out on buffer.flip() and re-run the app again and see what happens
 *      - If we look at our file, effectively, nothing is written
 *      - There's this strange character showing up on the screen , but obviously the output we saw prior to having
 *         the flip() has changed dramatically
 *      - The point is , if you don't use the flip() , the buffer's position will set to after the last value you wrote
 *         to it when you call binaryChannel.write(buffer)
 *      - And since there's nothing to read and write beyond that position, nothing's actually written to the file
 * - So calling the flip(), resets the buffer's position to 0, and that's why we need that
 * - Uncomment the flip() call and we should get the results back
 *
 *
 *
 *
 * ////
 * - Now that we've written things out in a single buffer, let's use a single buffer to read the contents of a file
 *   in one shot
 * - Create RandomAccessFile obj which allows you to read from and write to a file at any position.
 *      new RandomAccessFile("data.dat","rwd");
 *
 *      - The file mode "rwd" stands for read-write mode with "synchronous data mode," which ensures that both the
 *        file content and metadata are written synchronously to the storage device.
 *
 *
 * - Open a FileChannel on the RandomAccessFile obj
 *
 *      new RandomAccessFile("data.dat","rwd").getChannel()
 *
 *      - getChannel() retrieves a FileChannel associated with the RandomAccessFile.
 *      - A FileChannel is used for reading, writing, and manipulating file contents more efficiently,
 *        especially for larger files, as it provides better performance than simple stream-based I/O operations.
 *
 * - Create a ByteBuffer with a size of 100 bytes
 *
 *      readBuffer = ByteBuffer.allocate(100);
 *
 * - Then read data from them the FileChannel into the readBuffer
 *
 *      channel.read(readBuffer);
 *
 *      - The read operation starts from the current file position (which is initially at the beginning of the file)
 *        and fills the ByteBuffer with up to 100 bytes of data (or less if there are fewer bytes remaining in the file)
 *
 * - The flip() the buffer to start reading from 0
 *
 *      readBuffer.flip();
 *
 *      - We'll now switch from writing to the buffer to reading from the buffer
 *
 * - Create a new byte[] inputString , with the same length as "Hello World!" text
 *      byte[] inputString = new byte[outputBytes.length];

 * - Then read from ByteBuffer (readBuffer) into the inputString array
 *      readBuffer.get(inputString);
 *
 *      - The get(byte[] dst) method transfers the current bytes from the buffer into the provided array (inputString).
 *      - The amount of data transferred is determined by the size of the destination array (inputString).
 *      - This means it will try to transfer as many bytes as the size of inputString, but if the buffer has fewer
 *          bytes remaining, it will transfer only the available bytes.
 *
 * - Then call getInt() to read the two integers
 *
 *      System.out.println("inputString = " + new String(inputString)); // convert byte[] inputString to String
        System.out.println("int1 = " + readBuffer.getInt()); // output 245
        System.out.println("int2 = " + readBuffer.getInt()); // -98765
 *
 * ///////////
 * - Repeat the same steps to read , "Nice to meet you" and the integer3 1000
 *      byte[] inputString2 = new byte[outputBytes2.length];
 *      readBuffer.get(inputString2);
 *
 * - Print out both the string and the integer
 *      System.out.println("inputString2 = " + new String(inputString2)); // convert byte[] inputString to String
        System.out.println("int3 = " + readBuffer.getInt()); // 1000
 *
 *
 * /////
 * - We could reuse the buffer that we used to write the data here, but then the buffer's contents would already be
 *   set to the contents of the file
 *
 *
 * ///
 * - Run to make sure that this works and sure enough we get the contents from the "data.dat" file written to the
 *   console
 *
 * /////// Important to Note
 * - The put methods all return the ByteBuffer and that's great because that means we can chain multiple puts() calls
 *   together
 *
 * - We can rewrite below
 *
 *       byte[] outputBytes = "Hello World!".getBytes();
         buffer.put(outputBytes);
         buffer.putInt(245);
         buffer.putInt(-98765);
         byte[] outputBytes2 = "Nice to meet you".getBytes();
         buffer.put(outputBytes2);
         buffer.putInt(1000);
 *
 * - by chaining and calling appropriate put/putInt methods as below
 *
 *       byte[] outputBytes = "Hello World!".getBytes();
         byte[] outputBytes2 = "Nice to meet you".getBytes();
         buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000);
 *
 * ////
 * - Running this & we get exactly the same results as we did before and we can also see that the reading has
 *   returned the right information also
 * - And we can also confirm that by checking "data.dat" file
 *
 *
 * ////////////////////
 * - We've been using the RandomAccessFile to read the file, but only because it was convenient to get a channel
 *   from it
 * - Since you'll always be reading and writing data sequentially, you could also have used an InputStream
 * - Other than creating the stream, the code will then be the same
 *
 *
 * ////////
 * - Let's learn how to use a RandomAccess using java.nio
 * - To jump around in a file, you have to use a SeekableByteChannel, which has the notion of a current position
 * - SeekableByteChannel only has a small number of methods and it's actually been available since java 7
 * - Before then, RandomAccessFile was the only game in town when it comes to non-sequential I/O
 * - SeekableByteChannel is actually an interface with quite a few methods but there are 6 of them that Tim would
 *   like to bring us to our attention
 * - And they include the following
 *      - read(ByteBuffer buffer)
 *          - reads bytes beginning at the channel's current position, and after the read, updates the position accordingly
 *          - Note we're talking about a channel's position , and not the byte buffer's position
 *          - the bytes will be placed into the buffer starting at its current position
 *
 *      - write(ByteBuffer buffer)
 *          - same as read, except that it writes, There's one exception though
 *          - If a datasource is opened in APPEND mode, then the first write will take place starting at the end of
 *            the datasource, rather than at position 0
 *          - After the write, the position will be updated accordingly
 *
 *      - position()
 *          - returns the channel's position
 *
 *      - position(long)
 *          - sets the channel's position to the passed value
 *
 *      - truncate(long)
 *          - truncates the size of the attached datasource to the passed value
 *
 *      - size()
 *          - returns the size of the attached datasource
 *
 * - So that's actually all the methods in the SeekableByteChannel interface
 * - But here is the good news because we've been using one all along since the FileChannel class implements the
 *   SeekableByteChannel interface
 * - Let's modify the code , so that it reads from "data.dat" file in backwards order
 * - To do so, when we write the data, we'll save the start position for each value
 * - As we saw previously when we're working with RandomAccessFile, normally you'd have to have something like an
 *    index in the file that would enable you to read values on demand , but we'll keep things simple here
 *
 *
 * ////
 * - We'll use the unchained version of the write code because we'll need to store the file position after every
 *   write
 * - In this case we know what the data is and the order in which it is written
 * - We could calculate the position before we do each read but we'll pretend we can't do that
 *
 *      byte[] outputBytes = "Hello World!".getBytes();
        buffer.put(outputBytes);

        long int1Pos = outputBytes.length;
        buffer.putInt(245);

        long int2Pos = int1Pos + Integer.BYTES;
        buffer.putInt(-98765);

        byte[] outputBytes2 = "Nice to meet you".getBytes();
        buffer.put(outputBytes2);

        long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
        buffer.putInt(1000);
 *
 * - It's the same code we had before, except that now we're saving the start position of each integer that we
 *   wrote to the file
 * - We write the first integer 245, right after the string
 *      - So it's start position will be the number of bytes in the string
 * - The second time we're defining the position for the next int, and we're then writing the int -98765 after that
 *      - So the start position for that int is  (int1Pos + Integer.BYTES)
 *
 *          long int2Pos = int1Pos + Integer.BYTES;
 *
 *          - where int1Pos is outputBytes.length
 *          - and Integer.Bytes is the length of integer 245, in terms of bytes
 *
 * - The last integer which we had written last,
 *      - it's position will be
 *          long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length
 *
 *          - where int2Pos is the starting position of the second int
 *          - and Integer.BYTES is the length of integer -98765 , in terms of bytes
 * - And then later we're writing the value to the buffer and doing a flip and then write the contents to the
 *   buffer
 *
 *
 * /////
 * - Now that we've got that defined, we want to start reading the int back in reverse order we wrote them , but let's
 *   work on that in our next video
 */

public class Main {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream binaryFile = new FileOutputStream("data.dat");
             FileChannel binaryChannel = binaryFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(100);

            /////// Do it one by one ////////

            /*byte[] outputBytes = "Hello World!".getBytes();
            buffer.put(outputBytes);
            buffer.putInt(245);
            buffer.putInt(-98765);
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes2);
            buffer.putInt(1000); */

            /* Chaining methods above - put/putInt() */
            byte[] outputBytes = "Hello World!".getBytes();
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000);

            ///////  Using SeekableByteChannel Interface methods ////////

            /*byte[] outputBytes = "Hello World!".getBytes();
            buffer.put(outputBytes);
            long int1Pos = outputBytes.length;
            buffer.putInt(245);
            long int2Pos = int1Pos + Integer.BYTES;
            buffer.putInt(-98765);
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes2);
            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
            buffer.putInt(1000); */


            // flip to write and write contents of the buffer
            buffer.flip();
            binaryChannel.write(buffer);

            //// Read from "data.dat" file

           try( RandomAccessFile ra = new RandomAccessFile("data.dat","rwd");
            FileChannel channel = ra.getChannel()) {

               ByteBuffer readBuffer = ByteBuffer.allocate(100);
               channel.read(readBuffer);
               readBuffer.flip();
               byte[] inputString = new byte[outputBytes.length];
               readBuffer.get(inputString);
               System.out.println("inputString = " + new String(inputString));
               System.out.println("int1 = " + readBuffer.getInt());
               System.out.println("int2 = " + readBuffer.getInt());

               byte[] inputString2 = new byte[outputBytes2.length];
               readBuffer.get(inputString2);
               System.out.println("inputString2 = " + new String(inputString2));
               System.out.println("int3 = " + readBuffer.getInt());
           }

        }
    }
}
