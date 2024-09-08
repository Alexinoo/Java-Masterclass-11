package java_files_input_output._28_writing_sequentially;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * java.nio.file - Writing Sequentially
 *
 * - Let's try reading the integers in reverse order that we wrote them
 *      - e.g
 *          - Read 1000 first
 *          - Followed by -98765
 *          - Followed by 245
 *
 * - We'll use the channel.position() to set the FileChannel position before each read
 * - Remember we also need to call the flip() for that to be able to work
 *
 * /////
 * - First, we'll create a ByteBuffer obj and call it readBuffer with a size of an integer - Integer.BYTES
 * - Then call
 *      channel.position(int3Pos);
 *      channel.read(readBuffer)
 *      readBuffer.flip()
 *      System.out.println("int3 = " + readBuffer.getInt()); // 1000
 *
 *      - set the channel position to start from int3Pos, which is the start location for the 3rd integer
 *      - then start the read from that position into readBuffer
 *      - then flip to reset the buffer's position to 0
 *      - write out the int(1000) by calling readBuffer.getInt()
 *
 * - Next is to call flip()
 *
 *      readBuffer.flip();
 *      channel.position(int2Pos);
 *      channel.read(readBuffer);
 *      readBuffer.flip();
 *      System.out.println("int2 = " + readBuffer.getInt()); // -98765
 *
 *      - set the channel position to start from int2Pos, which is the start location for the 2rd integer
 *      - then start the read from that position into readBuffer
 *      - then flip to reset the buffer's position to 0
 *      - write out the int(-98765) by calling readBuffer.getInt()
 *
 * - Next is to call flip()
 *
 *      readBuffer.flip();
 *      channel.position(int1Pos);
 *      channel.read(readBuffer);
 *      readBuffer.flip();
 *      System.out.println("int1 = " + readBuffer.getInt()); // 245
 *
 *      - set the channel position to start from int1Pos, which is the start location for the 1st integer
 *      - then start the read from that position into readBuffer
 *      - then flip to reset the buffer's position to 0
 *      - write out the int(245) by calling readBuffer.getInt()
 *
 * - Let's write data in a random fashion - instead of writing it sequentially
 *      - We'll write all the integers first into the same positions they've always occupied and then the 2 strings
 *
 *
 * /////
 * - So for each integer, we're first calling position() to specify the start position in the FileChannel
 * - And then we're calling channel.read(readBuffer)
 * - If you want to write the result to the console, we have to call flip() before calling the getInt()
 * - And also after writing the value, you have to call flip again before reading the next integer into the buffer
 * - And we then repeat that process for all 3 integers
 *
 * ////
 * - And if we run this, we get all the integers returning in the reverse order
 *
 *
 * /////
 * - When reading in a random manner, probably, you would normally create a buffer of each read that matches the
 *   size of the data
 * - Let's think back to the adventure application
 * - If we wanted to use java.nio to load a location on demand, then we'd already know the length of the location
 *    record from the index
 * - Therefore, for each read we create a new buffer of that exact length
 * - You might think it'd be more efficient to create a buffer that's long enough to hold the longest location record
 *    and reuse it but you may never need to load the longest record and java's garbage collector will take care of
 *    the buffer's you're creating
 * - So they won't actually clog up the memory
 *
 *
 * ////////
 * - Let's write data in a random fashion
 * - Instead of writing it sequentially, we'll write all the integers first into the same positions they've always
 *    occupied and then the 2 Strings
 *
 *      byte[] outputString = "Hello, World!".getBytes();
        long startPosition = 0;
        long newInt1Pos = outputString.length;          // 13
        long newInt2Pos = newInt1Pos + Integer.BYTES;   // 13 + 4
 *
 *      - create our byte[] outputString : "Hello, World!".getBytes()
 *      - define the start position and set it to 0
 *      - define the start position for the byte[] outputString length and set it to 13
 *      - define the start position for the second int and set it to (13 + 4) = 17
 *
 *

        byte[] outputString2 = "Nice to meet you".getBytes();
        long str2Pos = newInt2Pos + Integer.BYTES;
        long newInt3Pos = str2Pos + outputString2.length;
 *
 *      - create our byte[] outputString : "Nice to meet you".getBytes()
 *      - define the start position for the byte[] outputString2 length and set it to (17 + 4) = 21
 *      - define the start position for the third int and set it to (21 + 16) = 37
 *
 * - Then create a ByteBuffer intBuffer and allocate it 4 bytes : Integer.BYTES
 *
 *        Bytebuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
 *
 * - Write the first int(245)
 *
 *        intBuffer.putInt(245);
          intBuffer.flip();
          binaryChannel.position(newInt1Pos);
          binaryChannel.write(intBuffer);
          intBuffer.flip();
 *
 *        - then write our first int(245) into the buffer
 *        - flip our buffer, to set position to 0
 *        - set the FileChannel position to 13
 *        - read from the buffer and write to the channel starting at pos-13
 *        - flip our buffer to set position to 0
 *
 * - Write the 2nd int
 *
 *        intBuffer.putInt(-98765);
          intBuffer.flip();
          binaryChannel.position(newInt2Pos);
          binaryChannel.write(intBuffer);
          intBuffer.flip();
 *
 *        - then write our second int(-98765) into the buffer
 *        - flip our buffer, to set position to 0
 *        - set the FileChannel position to 17
 *        - read from the buffer and write to the channel starting at pos-17
 *        - flip our buffer to set position to 0
 *
 * - Write the 3rd int
 *        intBuffer.putInt(1000);
          intBuffer.flip();
          binaryChannel.position(newInt3Pos);
          binaryChannel.write(intBuffer);
 *
 *        - then write our 3rd int(1000) into the buffer
 *        - flip our buffer, to set position to 0
 *        - set the FileChannel position to 21
 *        - read from the buffer and write to the channel starting at pos-21
 *        - flip our buffer to set position to 0
 *
 * - Finally, Write our 2 Strings
 *
 *          binaryChannel.position(startPosition);
            binaryChannel.write(ByteBuffer.wrap(outputString));
            binaryChannel.position(str2Pos);
            binaryChannel.write(ByteBuffer.wrap(outputString2));
 *
 *          - set FileChannel position to 0
 *          - read from the byte[] outputString and write to the channel starting at pos-0
 *
 *          - set FileChannel position to 21
 *          - read from the byte[] outputString2 and write to the channel starting at pos-21
 *
 *
 * //////
 * - There are other ways to write this code, ut writing it this way makes each step clear
 * - Firstly
 *      - We're calculating all the start positions
 *      - We're writing the 3 integers , and for each integer, we're writing the value to the buffer after flipping
 *         the buffer if necessary
 *      - Then we're flipping the buffer to prepare it for writing it to the channel
 *      - Then we're setting the channel to the start position for the integer
 *      - Then we write the value to the FileChannel
 *
 * - And for the 2nd and 3rd integers, you have to flip the buffer before writing the values to it
 *
 * /////
 * - The strings are simpler because we're using the wrap() and that takes care of creating and flipping the buffer
 *    for us
 *
 *
 * ////
 * - IF we run, this, we still get the integers being read in reverse order but if we take a look at the data.dat file
 *   we see that it pretty much looks the way it's always looked
 * - But this time, we didn't write the values sequentially this time
 * - You have to understand how important it is to make sure we call the flip() when changing from reading the buffer
 *   to writing it and vice versa
 * - Often, the reads and writes aren't intuitive
 * - For example
 *      - When writing a buffer to a channel, the buffer is being read
 *      - Now if you haven't called flip, when you're supposed to, you'll usually get a BufferUnderflowException when
 *          reading from the buffer
 *
 *      - In case of writing values, you won't actually see the values written to the file and that's because you didn't
 *        flip the buffer and the buffer's position would still be positioned after the value put()
 *          - And consequently, the channel goes to read the buffer, so we can write the value to the file and at that
 *            point there's nothing to read
 *
 *
 * /////
 * - Key Take away with reading with java.nio
 *  - Remember to flip the buffer when switching from reading to writing and vice versa
 *
 *
 * ///
 * - Let's look at how we can use a FileChannel to copy 1 file to another
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


            /////////////////////////////////////////////////////////////////////////
            /////////// Reading from "data.dat" file

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



            //////////////////////////////////////////////////////////////////
            ///////////  Let's write data in a random fashion  ///////////////
            byte[] outputString = "Hello, World!".getBytes();
            long startPosition = 0;
            long newInt1Pos = outputString.length;
            long newInt2Pos = newInt1Pos + Integer.BYTES;

            byte[] outputString2 = "Nice to meet you".getBytes();
            long str2Pos = newInt2Pos + Integer.BYTES;
            long newInt3Pos = str2Pos + outputString2.length;

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);

            // write the 3 integers
            intBuffer.putInt(245);
            intBuffer.flip();
            binaryChannel.position(newInt1Pos);
            binaryChannel.write(intBuffer);
            intBuffer.flip();

            intBuffer.putInt(-98765);
            intBuffer.flip();
            binaryChannel.position(newInt2Pos);
            binaryChannel.write(intBuffer);
            intBuffer.flip();

            intBuffer.putInt(1000);
            intBuffer.flip();
            binaryChannel.position(newInt3Pos);
            binaryChannel.write(intBuffer);

            //write 2 strings
            binaryChannel.position(startPosition);
            binaryChannel.write(ByteBuffer.wrap(outputString));
            binaryChannel.position(str2Pos);
            binaryChannel.write(ByteBuffer.wrap(outputString2));

        }
    }
}
