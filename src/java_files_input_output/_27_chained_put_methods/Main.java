package java_files_input_output._27_chained_put_methods;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Chained Put Methods
 *
 * - comment out on buffer.flip() and re-run the app again
 *
 * - Let's use a single buffer to read the contents of a file in one shot
 *
 * - put() all return the ByteBuffer and that's great because we can chain multiple puts together
 */

public class Main {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream binaryFile = new FileOutputStream("data.dat");
             FileChannel binaryChannel = binaryFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(100);

            /////// Do it one by one ////////
            /*
            byte[] outputBytes = "Hello World!".getBytes();
            buffer.put(outputBytes);
            buffer.putInt(245);
            buffer.putInt(-98765);
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes2);
            buffer.putInt(1000); */

            /* Chaining methods above - put()
            byte[] outputBytes = "Hello World!".getBytes();
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000); */

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

            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);
            readBuffer.flip();
            byte[] inputString = new byte[outputBytes.length];
            readBuffer.get(inputString);
            System.out.println("inputString = "+ new String(inputString));
            System.out.println("int1 = "+readBuffer.getInt());
            System.out.println("int2 = "+readBuffer.getInt());

            byte[] inputString2 = new byte[outputBytes2.length];
            readBuffer.get(inputString2);
            System.out.println("inputString2 = "+ new String(inputString2));
            System.out.println("int3 = "+readBuffer.getInt());

        }
    }
}
