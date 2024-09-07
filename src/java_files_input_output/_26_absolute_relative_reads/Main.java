package java_files_input_output._26_absolute_relative_reads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Absolute and Relative Reads
 *
 * - One thing to keep in mind is that when you're using absolute indices, the buffer position isn't updated
 *
 * - For example,
 *      - When we use intBuffer.getInt(0), to read an integer after the method call, the buffer position remain
 *         wherever it was before the call
 *
 *          System.out.println(intBuffer.getInt(0));
 * - To demo this, let's change the code to flip the buffer then do an absolute read and then do a relative read
 * - e.g
 *      intBuffer.flip();
 *      numBytesRead = channel.read(intBuffer);
 *      System.out.println(intBuffer.getInt(0)); // 245
 *      intBuffer.flip();
 *      numBytesRead = channel.read(intBuffer);
 *      intBuffer.flip();
 *      System.out.println(intBuffer.getInt(0)); // -98765   -- absolute read
 *      System.out.println(intBuffer.getInt()); // -98765    -- relative read
 *
 * - Notice that we're now reading the 2nd integer from the buffer twice
 * - After the second channel.read(intBuffer) call, we've flipped the buffer and that sets the position back to 0
 * - And then we're doing an absolute read and then a relative read
 *
 * ////
 * - And as a result, we get -98765 printed twice
 * - If the absolute read had changed the buffer's position, then we would have got a java.nio.BufferUnderflow error
 *    because the buffer's position would have been pointing to the end of the buffer after the absolute read
 * - But an absolute read doesn't change the buffer's position and that's why the relative read succeeded
 *
 *
 * ////
 * - Hopefully now we can see that mixing relative and absolute reads could very quickly become error prone and it's
 *    good to be consistent with your approach
 * - Tim suggests we do 1 way or the other, and mixing them up would make my code a nightmare to maintain
 *
 *
 * ///
 * - In terms of the examples we're using here, we're reusing existing buffers
 * - We could have also allocated new buffers using the ByteBuffer.allocate()
 * - So let's change the code below
 *
 *      ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
 *
 *      - to use
 *
 *      ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
 *
 *      - next call put() on our buffer and pass the outputBytes byte[]
 *
 *      buffer.put(outputBytes);
 *
 * ///
 * - So instead of using the wrap(outputBytes) and pass the byte[] , we're now creating a buffer and
 *   allocating it a size equivalent to our outputBytes byte[] length
 * - Then we're using the
 *      buffer.put(outputBytes);
 *
 *      - to copy the contents of the outputBytes byte[] into the buffer
 *
 * ////
 * - It's important to remember that when we use the wrap(outputBytes) , the byte[] passed to the method is used as
 *   the byte[] that backs the buffer
 * - but that's clearly is not what is happening here, when we use buffer.allocate(outputBytes.length) , a new byte[]
 *   is created and that's used to back the buffer
 * - Earlier when we used buffer created by wrap to read the string, the outputBytes byte[] was updated because it
 *    backed the buffer
 * - That won't be the case anymore as you'll see in a minute
 *
 *
 *
 * ////
 * - You can see what happened here , and we can see that the byte buffer has got some random characters there but
 *   we also got an exception
 * - So basically the code we've added has caused it to crashed because it was working prior to us suing the allocate()
 *   and the put()
 *
 * //// Mini-challenge
 * - What has gone wrong ?
 *      - We didn't flip the buffer
 *
 *
 * /// mantra, with fixing errors with java.nio
 * - We should learn it, keep it and don't forget it
 * - The mantra is when something goes wrong, flip
 *
 *          ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
            buffer.put(outputBytes);
 *          buffer.flip(); -- added after the IndexOutOfBoundsException : mantra
 *
            int numBytes = binaryChannel.write(buffer);
 *
 *          - the above code isn't flipping the buffer between a read and write
 *          - when we call buffer.put(outputBytes) , we wrote it to the buffer
 *          - And when we call binaryChannel.write(buffer) , we're reading from the buffer
 *
 * - We have to flip between those 2 actions
 *
 * ////
 * - If we add a flip before the read, and run again, this time we get the correct results
 * - It's time now to read th String back in
 * - We're still doing it fine, because we're calling the buffer.array() to get the byte[] backing the buffer
 * - When we're using the wrap() to write the String, we didn't have to call the array() because the outputBytes byte
 *  [] was backing the buffer
 * - But now that we're not using the wrap() , that's no longer the case
 * - To demo, that we'll temporary change the code to print out the outputBytes byte[] instead of the one we get from
 *   buffer.array() method call
 *
 *       System.out.println("byte buffer = "+ new String(buffer.array())); // Hello World!
 *
 *      - and instead replace that with
 *
         System.out.println("byte buffer = "+ new String(outputBytes)); // abllo World!
 *
 * - Notice now we get the output "abllo World!" and the problem here is that since the byte[] outputBytes isn't
 *   backing the buffer anymore, the first characters we set aren't overwritten when we use the buffer to read
 *   the string from the file
 *
 *
 *
 * ////////////
 * - So far, we've been reading and writing variables individually, you write a single value to the buffer and then
 *   you write to the channel
 * - And often , you want to write a bunch of data to the buffer before writing the buffer to the channel or read
 *   a bunch of data into the buffer before pulling data from it
 * - Let's update the code so that it reads and writes all 3 values that is the string of and the 2 integers
 *   in 1 shot
 *
 * /////////////////////////////////
 *  Comment out the rest of the code
 * /////////////////////////////////
 *
 * - Create a ByteBuffer and allocate a size of 100
 *
 *      ByteBuffer buffer = ByteBuffer.allocate(100);
 *
 * - The Buffer knows how many bytes have been written to it and also we only have to call the flip() once after we've
 *   written all the values to the buffer
 * - We do so at that point because we're flipping from writing to the buffer to reading from the buffer to write its
 *   contents to the FileChannel
 *
 * - We also haven't specified an index for any of the put() calls and so all the writes are relative writes for that
 *   reason , which is what we actually want here when we're writing the values sequentially
 *
 * - And consequently, the code is a bit cleaner than the previous versions we've worked through when you're using
 *   multiple buffers
 *
 * //////
 * - We'll take a look and see what happens if we remove the flip call , but then continue on and start reading the
 *   data back from the file as well
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {

        try (FileOutputStream binaryFile = new FileOutputStream("data.dat");
             FileChannel binaryChannel = binaryFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(100);
            byte[] outputBytes = "Hello World!".getBytes();
            buffer.put(outputBytes);
            buffer.putInt(245);
            buffer.putInt(-98765);

            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes2);
            buffer.putInt(1000);

            buffer.flip();

            binaryChannel.write(buffer);



//           // ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
//            ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
//            buffer.put(outputBytes);
//            buffer.flip();
//
//            int numBytes = binaryChannel.write(buffer);
//
//            System.out.println("numBytes written was: " + numBytes);
//
//            //////////// pass the size that we want the buffer to be since we want to write an integer - rep the number of bytes in an integer
//            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
//
//            //write 245 to our intBuffer
//            intBuffer.putInt(245);
//
//            // reset the buffer's position to 0
//            intBuffer.flip();
//
//            //write it to our binaryChannel
//            numBytes = binaryChannel.write(intBuffer);
//
//            System.out.println("numBytes written was: " + numBytes);
//
//            // Write another integer - this time a negative number
//            // pretend that we don't understand that we have to call flip()
//            intBuffer.flip();
//            intBuffer.putInt(-98765);
//            intBuffer.flip();
//            numBytes = binaryChannel.write(intBuffer);
//            System.out.println("numBytes written was: " + numBytes);
//
//
//            /////// Read with java.io.RandomAccessFile /////////////////////////////////
//            ///////////////////////////////////////////////////////////////////////////
//           /* RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
//
//            // define byteArray that's going to contain the data that we're reading - based on the length of the outputBytes defined above
//            byte[] b = new byte[outputBytes.length];
//
//            // then read from the buffer
//            ra.read(b);
//
//            // print out
//            System.out.println(new String(b)); // Hello World!
//
//            // Read in the 2 integers
//            long int1 = ra.readInt();
//            long int2 = ra.readInt();
//            System.out.println(int1); // 245
//            System.out.println(int2); // -98765 */
//
//            /////// Read with java.nio.RandomAccessFile /////////////////////////////////
//            ///////////////////////////////////////////////////////////////////////////
//
//            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
//            FileChannel channel = ra.getChannel();
//
//            outputBytes[0] = 'a';
//            outputBytes[1] = 'b';
//            buffer.flip();
//
//            // Use existing buffers to read the data back into the application
//            long numBytesRead = channel.read(buffer);
//
//            //print b
//          //  System.out.println("outputBytes = "+new String(outputBytes));
//
//            //////////// Bottom line - Always remember to call the flip() /////////////
//            // There is another way to get the string from the buffer
//            // We can call the method which returns the byte array backing the buffer
//            // call byteBuffer.hasArray() to ensure that you can access the buffer backing the array
//            if(buffer.hasArray()){
//                System.out.println("byte buffer = "+ new String(buffer.array())); // Hello World!
//               // System.out.println("byte buffer = "+ new String(outputBytes)); // abllo World!
//            }
//
//
//            // Read the 2 integers using existing buffer instance
//           /* ** Relative read **
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//
//            System.out.println(intBuffer.getInt());
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt()); */
//
//            //// To avoid calling flip, we can use an overloaded version of the getInt()
//            // When we don't pass any parameters as we're doing here , with our call to getInt(), then the read begins
//            //  at the buffer's current position
//            // But instead you can pass an index to getInt() , and the read will begin at the index you passed
//            // The first type of read is based on the buffer's position is called a relative read
//            // The second type of read is called an absolute read
//
//            // We're doing relative here, since we're not passing any parameters
//
//            // Let's now change the code so that we can pass zero to getInt calls and get rid of one of the flip class for each method
//            // comment the code above - comment with /* */
//
//            /* Absolute Read */
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//            System.out.println(intBuffer.getInt(0));
//            intBuffer.flip();
//
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt(0)); // absolute read
//            System.out.println(intBuffer.getInt());        // relative read
//
//            // We've called flip() twice here -a nd se called it a total of four times with absolute reads
//            // check if it works - and we get the same result
//
//            // By using absolute reads - we don't have to flip the buffer after reading from the file channel because we use the absolute
//            //  version of getInt() and passing an index of 0
//
//
//            //close the file after reading the data - we didn't use try-with-resources
//            channel.close();
//            ra.close();
        }
    }
}
