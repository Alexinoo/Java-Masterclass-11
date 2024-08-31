package java_files_input_output._29_copyfiles_with_filechannel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * FileChannel to Copy Files and Pipes With Threads
 *  - Using transferFrom() and transferTo() methods
 *
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

            //long numTransferred = copyChannel.transferFrom(channel,0, channel.size());
            long numTransferred = channel.transferTo(0, channel.size(),copyChannel);
            System.out.println("Num Transferred = "+numTransferred);

            channel.close();
            ra.close();
            copyChannel.close();

         }
    }
}
