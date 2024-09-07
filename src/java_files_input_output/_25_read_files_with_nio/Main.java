package java_files_input_output._25_read_files_with_nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Reading Files with Java NIO
 *
 * - We've used java.nio to write to a data.dat file and it's now tie to read that data back to the application
 * - We'll use java.nio but we'll start off using java.io
 *
 * - A file is just a file and it doesn't matter how it was written
 *      - So, we can use java.io to write a file and java.nio to read it and vice versa
 *
 * - Let's use java.io.RandomAccessFile class to read in this data
 *
 *
 * /////////
 * - Create a RandomAccessFile obj that takes the file name "data.dat" with mode as "rwd"
 *      Random ra = new RandomAccessFile("data.dat","rwd");
 *
 * - Define a byte[] that is going to contain the data we're reading
 *      byte[] b = new byte[outputBytes.length];
 *
 *      - pass the length, based on the byte[] length we used for our "Hello World!" text
 *
 * - Then use
 *      ra.read(b);
 *
 *      - call read() from RandomAccessFile obj and pass the byte array, so that we read is going into the byte[]
 *
 * - Print contents by byte[] b,
 *
 *      System.out.println(new String(b)); // Hello World!
 *
 *      - converts the byte[] b to a String
 *
 *
 * ////
 * - And we get "Hello World!" text in the console
 *
 * ///
 * - Read in the 2 integers by calling readInt() on RandomAccessFile obj
 *
 *       long int1 = ra.readInt();
         long int2 = ra.readInt();

 *      - And print them out
 *
 *       System.out.println(int1); // 245
         System.out.println(int2); // -98765
 *
 * - Comment out on the code that reads from java.io.RandomAccessFile and let's read from java.nio.RandomAccessFile class
 *
 *
 * ////////
 * - Read the data.dat file using java.nio.RandomAccessFile
 * - Create the RandomAccessFile obj
 *
 *       RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
 *
 * - Get the FileChannel instance
 *
         FileChannel channel = ra.getChannel();
 *
 * - We'll use existing buffers to read data back into the app
 *
 *      long numBytesRead = channel.read(buffer);
 *
 *      - After making this call, the String will be in the variable buffer which we used to write out the String
 *         earlier in the main()
 *
 * - To print the String, we need to access the byte[] backing the buffer and can actually get that in 2 ways
 *
 *      - If you recall when we passed the called the wrap(), the byte[] we pass as the parameter is used to back the
 *         buffer
 *          - So, after the read, the String should be in the outputByte array
 * - But first, we are forgetting something, what do we have to do before switching from writing to reading
 *      - Well, we should call the flip()
 *      - But let's actually see what happens if we don't call it
 *
 *          System.out.println(new String(outputByte)); // Hello World!
 *
 *      - And sure enough, we see the expected String showing in the console but this is actually deceiving
 *      - This is because the outputByte array already contained Hello World from when we wrote the file originally
 *      - And because we didn't flip(), the read() that we called below
 *
 *          long numBytesRead = channel.read(buffer);
 *          - didn't actually do anything because the buffer position was actually at the end of the buffer
 *
 * - To demo this, let's change the contents of the byte[] outputByte , before we do the read
 *
 *          outputBytes[0] = 'a';
 *          outputBytes[1] = 'b';
 *          long numBytesRead = channel.read(buffer);
 *
 *          - and now we get "abllo World!" printed representing the fact that we changed the outputByte variable
 *
 * - So, what should have happened is when we read this buffer, we have saved this as "Hello World!" and that's what's
 *    been saved to the file
 * - And so we've changed the buffer in memory to "abllo World!" , and so the call to the channel.read(buffer) should
 *    have actually replaced that with "Hello World!"
 * - Clearly, this confirms that the channel.read(buffer) isn't being called correctly , or rather being called but
 *   not actually returning what we think it should be returning
 * - Add a call to flip() before the channel.read(buffer)
 *
 * ////\
 * - And this time, we get the expected results
 *      - We see "Hello World!" printed to the console even though we changed the byte[] , and we should have got
 *        "Hello World" showing because we wrote that originally to the buffer
 *      - And now that we made a call to flip, the read() is successfully able to read that byte[] back in and we're
 *         getting Hello world printed
 * - The bottom line is , always remember to call the flip()
 *
 *
 * ////
 * - It is unfortunately so easy to forget skip the flip(), and this is the reason why many developers aren't really
 *    keen on using java.nio to do IO for their files
 *
 * ////
 * - There's another way to get the String from the buffer
 *      - We can call the buffer.hasArray() which returns the byte[] backing the buffer
 *
            if(buffer.hasArray()){
                System.out.println("byte buffer = "+ new String(buffer.array()));
            }
 *      - call byteBuffer.hasArray() to ensure that you can access the buffer backing the array
 *
 * ////
 * - Let's now read the 2 integers using the existing intBuffer instance
 *
 *      intBuffer.flip();
        numBytesRead = channel.read(intBuffer);
        intBuffer.flip();

        System.out.println(intBuffer.getInt()); // 245
        intBuffer.flip();
        numBytesRead = channel.read(intBuffer);
        intBuffer.flip();
        System.out.println(intBuffer.getInt()); // -98765
 *
 *
 * - we should also be closing both the FileChannel and RandomAccessFile instances as we used a plain try block
 *      channel.close()
 *
 * - And also on the RandomAccessFile instance
 *      ra.close()
 *
 * /////
 * - But we had to flip between each access of the buffer and just to confirm, the read call actually writes the
 *    buffer and the getInt() reads the buffer if that make sense
 *
 * //////
 * - To avoid calling flip(), we can call another version of getInt()
 * - When we don't pass any parameters as we're doing below
 *
 *      System.out.println(intBuffer.getInt());
 *
 *      - with our call to getInt() , if we do that without any parameters, then the read begins at the buffer's
 *        current position
 *      - we can also pass an index to getInt() , so that the read can begin at the index specified
 *
 *
 * ///// relative Read
 * - The first type of reads based on the buffer's position is called a relative read
 * - Relative read in action, we have to call flip()
 *
 *          intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            intBuffer.flip();

            System.out.println(intBuffer.getInt());
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt());
 *
 *          - we don't pass any parameters to getInt()
 *          - we've called flip() , a total of 4 times
 *
 *
 * ///// Absolute Read
 * - The second type of read is called an absolute read
 *
 * - Let's change the code and pass 0 and see absolute reads in action
 *
 *          intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));

            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
 *
 *          - we're still doing the initial flip, but we don't need to flip again, after reading from the FileChannel
 *          - We've only called flip() twice in total
 *
 * - You can do relative and absolute gets for all the data types and you can do the same when doing puts to the
 *   buffer
 * - You can flip the buffer or you can provide an index  but be consistent depending with what you prefer to do
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {

        try (FileOutputStream binaryFile = new FileOutputStream("data.dat");
             FileChannel binaryChannel = binaryFile.getChannel()) {
            byte[] outputBytes = "Hello World!".getBytes();

            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);

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
                System.out.println("byte buffer = "+ new String(outputBytes));
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
            System.out.println(intBuffer.getInt(0));

            // We've called flip() twice here -and so called it a total of four times with absolute reads
            // check if it works - and we get the same result

            // By using absolute reads - we don't have to flip the buffer after reading from the file channel because we use the absolute
            //  version of getInt() and passing an index of 0

            //close the file after reading the data - we didn't use try-with-resources
            channel.close();
            ra.close();
        }
    }
}
