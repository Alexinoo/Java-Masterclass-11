package java_files_input_output._28_writing_sequentially;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Writing Sequentially
 *
 * - Let's try reading the integers in reverse order that we wrote them
 *      - e.g
 *          - Read 1000 first
 *          - Followed by -98765
 *          - Followed by 245
 *
 * - Let's write data in a random fashion - instead of writing it sequentially
 *      - We'll write all the integers first into the same positions they've always occupied and then the 2 strings
 *
 *
 * Key Take away with reading with java.nio
 *  - Remember to flip the buffer when switching from reading to writing and vice versa
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



            //////////////////////////////////////////////////////////////////
            ///////////  Let's write data in a random fashion  ///////////////
            byte[] outputString = "Hello, World!".getBytes();
            long str1Pos = 0;
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
            binaryChannel.position(str1Pos);
            binaryChannel.write(ByteBuffer.wrap(outputString));
            binaryChannel.position(str2Pos);
            binaryChannel.write(ByteBuffer.wrap(outputString2));

        }
    }
}
