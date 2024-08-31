package java_files_input_output._26_absolute_relative_reads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Absolute and Relative Reads
 *
 * - When using Absolute Reads - The buffer position isn't updated
 *      - When we use intBuffer.getInt(0), to read an integer after the method call, the buffer position remain
 *         wherever it was before the call
 *
 *          System.out.println(intBuffer.getInt(0));
 *      - Change the code to flip the buffer then do an absolute read and then do a relative read
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {

        try (FileOutputStream binaryFile = new FileOutputStream("data.dat");
             FileChannel binaryChannel = binaryFile.getChannel()) {
            byte[] outputBytes = "Hello World!".getBytes();

           // ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
            buffer.put(outputBytes);
            buffer.flip();

            int numBytes = binaryChannel.write(buffer);

            System.out.println("numBytes written was: " + numBytes);

            //////////// pass the size that we want the buffer to be since we want to write an integer - rep the number of bytes in an integer
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);

            //write 245 to our intBuffer
            intBuffer.putInt(245);

            // reset the buffer's position to 0
            intBuffer.flip();

            //write it to our binaryChannel
            numBytes = binaryChannel.write(intBuffer);

            System.out.println("numBytes written was: " + numBytes);

            // Write another integer - this time a negative number
            // pretend that we don't understand that we have to call flip()
            intBuffer.flip();
            intBuffer.putInt(-98765);
            intBuffer.flip();
            numBytes = binaryChannel.write(intBuffer);
            System.out.println("numBytes written was: " + numBytes);


            /////// Read with java.io.RandomAccessFile /////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////
           /* RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");

            // define byteArray that's going to contain the data that we're reading - based on the length of the outputBytes defined above
            byte[] b = new byte[outputBytes.length];

            // then read from the buffer
            ra.read(b);

            // print out
            System.out.println(new String(b)); // Hello World!

            // Read in the 2 integers
            long int1 = ra.readInt();
            long int2 = ra.readInt();
            System.out.println(int1); // 245
            System.out.println(int2); // -98765 */

            /////// Read with java.nio.RandomAccessFile /////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////

            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = ra.getChannel();

            outputBytes[0] = 'a';
            outputBytes[1] = 'b';
            buffer.flip();

            // Use existing buffers to read the data back into the application
            long numBytesRead = channel.read(buffer);

            //print b
          //  System.out.println("outputBytes = "+new String(outputBytes));

            //////////// Bottom line - Always remember to call the flip() /////////////
            // There is another way to get the string from the buffer
            // We can call the method which returns the byte array backing the buffer
            // call byteBuffer.hasArray() to ensure that you can access the buffer backing the array
            if(buffer.hasArray()){
                System.out.println("byte buffer = "+ new String(buffer.array())); // Hello World!
               // System.out.println("byte buffer = "+ new String(outputBytes)); // abllo World!
            }


            // Read the 2 integers using existing buffer instance
           /* ** Relative read **
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            intBuffer.flip();

            System.out.println(intBuffer.getInt());
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt()); */

            //// To avoid calling flip, we can use an overloaded version of the getInt()
            // When we don't pass any parameters as we're doing here , with our call to getInt(), then the read begins
            //  at the buffer's current position
            // But instead you can pass an index to getInt() , and the read will begin at the index you passed
            // The first type of read is based on the buffer's position is called a relative read
            // The second type of read is called an absolute read

            // We're doing relative here, since we're not passing any parameters

            // Let's now change the code so that we can pass zero to getInt calls and get rid of one of the flip class for each method
            // comment the code above - comment with /* */

            /* Absolute Read */
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();

            numBytesRead = channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt(0)); // absolute read
            System.out.println(intBuffer.getInt());        // relative read

            // We've called flip() twice here -a nd se called it a total of four times with absolute reads
            // check if it works - and we get the same result

            // By using absolute reads - we don't have to flip the buffer after reading from the file channel because we use the absolute
            //  version of getInt() and passing an index of 0


            //close the file after reading the data - we didn't use try-with-resources
            channel.close();
            ra.close();
        }
    }
}
