package java_files_input_output._24_write_binary_files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Java NIO - Writing Binary files
 *   - convert String to bytes
 *   - create ByteBuffer from the byte[]
 *   - write our buffer to the channel - get to know how many bytes were written to the file
 *   - close the input stream and the file channel - if you're using try-with-resources, this may not be necessary
 *
 * ////
 *
 * - Remember to call the flip() whenever you want to reset a buffer's position to zero
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
