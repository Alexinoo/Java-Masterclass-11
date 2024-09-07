package java_files_input_output._24_write_binary_files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Java NIO - Writing Binary files
 *
 * - We've looked at the Files class to read and write text files , let's turn our attention to binary files
 * - We're going to start by writing the String "Hello World" to a file in binary format
 *
 *
 * ///
 * - Create a FileOutputStream obj and call our file "data.dat" which we pass to FileOutputStream constructor
 * - Then call getChannel() on our FileOutputStream obj
 *       FileOutputStream binaryFile = new FileOutputStream("data.dat");
         FileChannel binaryChannel = binaryFile.getChannel();
 *
 * - The next thing we need is a buffer because we're writing to a file,
 *      - You've got the data and so you can create a byte buffer that matches the length of the data
 *      - In the read cases, you don't know the length of the data, so what we can really do is create a buffer of
 *          a given length and then parse what it receives
 *  - But note that you don't have to use the same buffer to do all your reads and writes , you can create as many
 *    different buffers you like
 *  - But let's get some terminology out of the way before we write out some code
 *
 * //////
 *
 * Buffer's capacity
 *  - is the number of elements it can contain
 *
 * Buffer's position
 *  - is the index of the next element that should be read or written
 *  - It can't be greater than the buffer's capacity
 *
 * Buffer's mark
 *  - A Buffer's mark is used by the buffer's reset()
 *  - When this method is called, the buffer's position is reset to it's mark
 *  - If you know you want to rewind the buffer to a certain point, you'll mark the point and then later call the
 *    reset() to reset the position to the mark
 *
 *
 * /////
 * - With that out of the way, let's now write "Hello World!" String to a file in binary format
 *
 * First
 *  - We need to create a byte array for the string that we want to output as follows
 *      byte[] outputBytes = "Hello World!".getBytes();
 *
 * Second
 *  - We call ByteBuffer.wrap() which is a static method to create a ByteBuffer from the byte[]
 *      ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
 *
 *  - ByteBuffer.wrap()
 *      - it wraps the byte[] into the buffer ( this means that the buffer is backed by the byte[])
 *      - Any modifications to the buffer, will actually change the array , and the modifications to the byte[]
 *         will change the buffer
 *      - If you use the buffer to write out our String or our bytes and then you use it to read from a file, the
 *         outputByte array will be changed
 *          - It will no longer contain "Hello World!"
 *      - So essentially, when you call the wrap(), you're telling the runtime that you want to use the byte[] as the
 *          buffer
 *      - And since you've used wrap, the buffer is already filled, may not be what you're expecting to happen there
 *
 *      - Secondly
 *          - It sets the position of the buffer to 0, and when you use the buffer, the read/write will start at
 *             position 0
 *      - Thirdly
 *          - The buffer's will be set to the byte[] length which we would expect
 *      - Fourthly
 *          - The buffer's mark will be undefined and that can be set later
 *
 * ////////
 *
 * - So now that we've got our buffer and it contains the bytes, which was the String obj that we wanted to write,
 *     we can use the channel to write it to a file
 *
 * - And the way we do that is by calling write() on the FileChannel instance and pass the buffer
 *
 *      binaryChannel.write(buffer)
 *
 *      - And if we wanted to know how many bytes were actually written, we can get that from the write() returns value
 *         and we can change above to
 *
 *      int numBytes = binaryChannel.write(buffer);
 *
 *      - And then print out the num of bytes written
 *
 *      System.out.println("numBytes written was: "+numBytes);
 *
 *  - And finally, we have to close the OutputStream and the FileChannel , and because of that , you use try-with-resources
 *     which does that for you automatically
 *
 *  - Change the code to use try-with-resources, ad once we do that, we'll find that the channel and the output stream
 *    will be closed once we've finished execution
 *
 * ////
 * - Running this we can see that 12 bytes were written , which will be consistent with "Hello World!" which is exactly
 *   12 bytes & we can see our "data.dat" file was written, with the text appearing in that file
 * - And by the way , it looks like a text file because we've used default UTF-8 character set
 *
 * ///
 * - Let's now write an integer to our data.dat file
 * - We could re-use the buffer, but instead we can create an IntBuffer that we use whenever we want to write an
 *   integer
 *       ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
 *
 *      - Here, we're calling the ByteBuffer.allocate(Integer.BYTES) and we're passing to it the size that we want
 *        the buffer to be which in this case rep the no of Bytes in an integer
 *
 * - Now that we got the buffer, we can write an int into the buffer , say 245
        intBuffer.putInt(245);
 *
 * - And then write it into our FileChannel instance
 *
 *      numBytes = binaryChannel.write(intBuffer);
 *
 * - And print out the num of bytes written again
 *
 *      System.out.println("numBytes written was: "+numBytes);
 *
 *
 * ///////
 * - However, on running it, we get num Of Bytes written is 0, and the integer is neither written to the file , which
 *    is a bit weird
 *
 *
 * //////
 * - So what actually happen and why isn't the integer written to the file and why are we getting 0 as the output
 * - Well here is the deal in a nutshell....
 *      - When you created the buffer, the position of the buffer was set to 0
 *      - When you call putInt(), we wrote the int into the buffer, which changed the buffer's position
 *      - When binaryChannel.write(intBuffer) goes to read the buffer's contents , it'll start the read at
 *         the buffer's position
 *      - So, we have to explicitly reset the position to zero if you want to binChannel.write(intBuffer) to start
 *          reading from the buffer from the beginning
 *
 *      - Solution
 *      - To do this, we call flip() and what it does is, it resets the position to zero and discards any marks that
 *         had been previously set
 * - It would be obviously fantastic if the IO methods did this for us, so that developers don't have to explicitly
 *    reset a buffer's position to 0, but alas, that's not the case, it was a design decision
 * - Because you might want to do multiple writes or reads to a buffer before writing to the channel in which case
 *    you wouldn't want to reset the position to 0 after every write
 *
 *
 * ////////
 * - We don't have to use a flip() when we use the wrap() , and that's because it resets the position to 0
 *      - By using the wrap(), it automatically resets the position to 0
 * - That's 1 method that takes care of this for us and its a convenience method to use when writing byte[]
 *
 *
 * ////
 * - But when writing numeric , i.e. int , long or a short using the putInt, putLong, putShort methods, you do have
 *   to take care of resetting the buffer yourself
 * - You may not want to do so after every read or write
 * - So like if you had 20 integers to write for argument's sake, you could create a buffer large enough to accept
 *    20 integers and then call in putInt() 20 times
 * - Each write would start at the buffer's position in that case
 * - For the first putInt()
 *      - the buffer's position would be 0
 * - For the second putInt()
 *      - the buffer's position would be 4
 * - For the third putInt()
 *      - it would be 8
 *
 * - Given that 4 is the number of bytes for an integer
 *
 * ////
 * - Also note that you can write to an explicit index in the buffer
 * - To do so, you pass a 2nd parameter to the put() that specifies the start index for the read/write
 *
 *
 * ///
 * - But let's flip the buffer before using it to write to the channel
 *      intBuffer.putInt(245);
        intBuffer.flip();
 *      numBytes = binaryChannel.write(intBuffer);
 *
 *      - need to be added after the putInt and before the binary.write(intBuffer)
 *
 * ///
 * - And if we run now, we should get number of bytes written as 4 and the number written to the data.dat file
 *
 *
 * ////
 * - Let's write another integer and use a negative number this time
 * - Pretending that we don't have to call the flip() again to reset the buffer before we write our negative number
 *    and then before you write buffer to a file
 *   as below
 *
 *      numBytes = binaryChannel.write(intBuffer);
 *      intBuffer.putInt(-98765);
 *      numBytes = binaryChannel.write(intBuffer);
 *
 *      - IF we run this, we actually get a BufferOverflowException and the reason for that is that after we wrote
 *        the last integer 245 to the file, the buffer's position was set to 4
 *      - But the buffer is only 4 bytes which we had set with allocate(Integer.BYTES) which is a length of 4 and that's
 *         usual with java at the position zero-based
 *      - So, 4 is actually outside or Out of Bounds of the buffer's capacity and there's no room at index 4 to write
 *         any more bytes and hence the exception for that reason
 *
 *
 * /////
 * - So, calling flip will actually solve the problem and we need 2 flips,
 *
 *      numBytes = binaryChannel.write(intBuffer);
 *      intBuffer.flip();
 *      intBuffer.putInt(-98765);
 *      intBuffer.flip();
 *      numBytes = binaryChannel.write(intBuffer);
 *
 *      - before our call to putInt() and after putInt()  prior to the call to write() to channel
 *
 * ////
 * - Running this, we get 4 bytes were written and we can see a binary rep of the same in our data.dat file
 *
 * /// Sort notes - done earlier
 *
 *   - convert String to bytes
 *   - create ByteBuffer from the byte[]
 *   - write our buffer to the channel - get to know how many bytes were written to the file
 *   - close the input stream and the file channel - if you're using try-with-resources, this may not be necessary
 *
 * //// IMPORTANT
 *
 * - ALWAYS remember to call the flip() whenever you want to reset a buffer's position to zero
 * - It's a common error to forget to do it and then wonder why the data hasn't been written to the data source
 * - IF you're going to do multiple writes to a buffer before writing the buffer tool channel,
 * - However , you don't have to call flip() after each write but you do have to call it whenever you flip from
 *   reading to writing or writing to reading , and hence the name flip
 * - When you've filled the buffer and you're ready to write to a channel, or TV channel, you'll actually be reading
 *   from the buffer at that point
 * - Then read from the buffer and write to the channel and hence the call to flip is required
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) {
      /*  try{
            FileOutputStream binaryFile = new FileOutputStream("data.dat");
            FileChannel binaryChannel = binaryFile.getChannel();


            byte[] outputBytes = "Hello World!".getBytes();

            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);

            int numBytes = binaryChannel.write(buffer);

            System.out.println("numBytes written was: "+numBytes);

            binaryChannel.close();
            binaryFile.close();

        }catch (IOException ioe){
            ioe.printStackTrace();
        } */

        try(FileOutputStream binaryFile = new FileOutputStream("data.dat");
            FileChannel binaryChannel = binaryFile.getChannel()){
            byte[] outputBytes = "Hello World!".getBytes();

            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);

            int numBytes = binaryChannel.write(buffer);

            System.out.println("numBytes written was: "+numBytes);

            //////////// pass the size that we want the buffer to be since we want to write an integer - rep the number of bytes in an integer
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);

            //write 245 to our intBuffer
            intBuffer.putInt(245);

            // reset the buffer's position to 0
            intBuffer.flip();

            //write it to our binaryChannel
            numBytes = binaryChannel.write(intBuffer);

            System.out.println("numBytes written was: "+numBytes);

            // Write another integer - this time a negative number
            // pretend that we don't understand that we have to call flip()
            intBuffer.flip();
            intBuffer.putInt(-98765);
            intBuffer.flip();
            numBytes = binaryChannel.write(intBuffer);
            System.out.println("numBytes written was: "+numBytes);

            // We get an error - BufferOverFlowException
            // After we wrote the first integer to the file, the buffer's position was set to 4
            // but the buffer is only 4 bytes long as we had allocated it with Integer.Bytes
            // and as usual in java, position is zero-based

            // Need to flip before and after writing to intBuffer
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
