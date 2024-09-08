package java_files_input_output._30_pipes_with_threads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/*
 * java.nio.channels.Pipe - Pipes With Threads
 *
 * - A pipe is used to transfer data between threads and they're actually a 1-way connection ans so the data can only
 *   flow one way
 * - A pipe consists a pair of channels
 *      - A writable sink channel
 *      - A readable source channel
 * -  Once some bytes are written to the sink channel they can be read from the source channel in exactly the order
 *    in which they were written.
 * - Whether a thread writing bytes to a pipe will block until another thread reads those bytes, or some
 *    previously-written bytes, from the pipe is system-dependent and therefore unspecified.
 *
 * - Many pipe implementations will buffer up to a certain number of bytes between the sink and source channels,
 *    but such buffering should not be assumed.
 *
 *  - One or more threads can write or writes to the sink channel and the other thread or threads read from the source
 *     channel
 * - Let's actually look at an example
 *
 * //////////////
 *
 * - Open a pipe
 *
 *      Pipe pipe = Pipe.open();
 *
 *      - A new pipe is created
 *          - The new pipe is created by invoking the openPipe method of the system-wide default SelectorProvider object.
 *      - Handle any IOException
 *      - Returns a new pipe
 *
 * - Then we need 2 threads
 *      - one that will write to the sink channel
 *      - one that will read from the source channel
 *
 * ////////
 * - Let's add the writer thread first
 * - We'll create an anonymous Runnable that writes data to a Pipe.SinkChannel using a ByteBuffer
 * - We're writing the current time as a string to the pipe, looping ten times with a delay between each write.
 *
 *      Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try{
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for (int i = 0; i < 10; i++) {
                            String currentTime = "The time is " +System.currentTimeMillis();
                            buffer.put(currentTime.getBytes() );
                            buffer.flip();
                            while (buffer.hasRemaining()){
                                sinkChannel.write(buffer);
                            }
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };
 *
 *          - We could have used a lambda expression for the Runnable, but we've spelt it out here for clarity
 *          - The write() is calling pipe.sink() to get the Pipe.SinkChannel sinkChannel
 *          - We then create a ByteBuffer with a size of 56 bytes
 *          - Then we use a for loop here because we want a thread to terminate at some point
 *          - Inside the loop,
 *              - We're creating a String that includes the current time and converting it to bytes and writing that
 *                  to the buffer
 *              - Then we're flipping the buffer so that we can read the buffer and write it to the sinkChannel
 *              - At this point we have to flip the buffer again once we finish that for the next iteration of the loop
 *              - Then the thread sleeps for 100ms to give the reader thread a chance to read the source channel
 *
 * /////////
 * - Let's now set up a Runnable that reads from a Pipe.SourceChannel using a ByteBuffer,
 * - This complements the writer thread by receiving the data written by the SinkChannel.
 *
 *      Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try{
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for (int i = 0; i < 10 ; i++) {
                            int bytesRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip();
                            buffer.get(timeString);
                            System.out.println("Reader Thread : "+ new String(timeString));
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
 *
 *          - This is very similar to the writer Runnable, except that we're calling the pipe.source() to get the
 *              Pipe.SourceChannel sourceChannel
 *          - Then we're reading from the sourceChannel into the buffer
 *          - Then we're creating a byte[] timeString, equivalent to the size of number of bytesRead in each iteration
 *          - flipping the buffer so that we can start reading from the buffer using the get() from the timeString
 *              - Printing it out the timeString to the console
 *          - Then flipping the buffer to reset to 0 for the next iteration of the loop
 *
 * //////
 * - Then we need to Kickstart both threads
 *      - First the Writer,
 *      - Then the reader
 *
 *
 * ////
 * - If we run this
 *      - We get the Reader thread reading what the time was and it went through 10 times as per the for loop
 *         and then stopped
 * - And if we run this again
 *      - And obviously, it's still working nicely
 *
 *
 * ///////
 * - So, that's how to go about using a pipe to send data from 1 thread to another
 * - There are other ways for threads to communicate and pass data, and so it would be a good idea to benchmark
 *    the different ways and then choose the one that's the most efficient for your application
 * - Sometimes using java.nio.channels.Pipe , may not actually make sense
 *
 *
 * ////
 * - But it is important to know how we can use pipe to send data from 1 thread to another
 *
 *
 *
 * /////
 * - Added Performance improvements
 *      - Close the sink and source channels
 *      - Used buffer.clear() after each iteration in both cases
 * - Reader Thread
 *      - Break if bytesRead equals -1 - means we're done reading
 */
public class Main {
    public static void main(String[] args) {
        try{
            Pipe pipe = Pipe.open();
            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try{
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for (int i = 0; i < 10; i++) {
                            String currentTime = "The time is " +System.currentTimeMillis();
                            buffer.put(currentTime.getBytes() );
                            buffer.flip();
                            while (buffer.hasRemaining()){
                                sinkChannel.write(buffer);
                            }
                            //buffer.flip();
                            buffer.clear(); // Clear the buffer to write new data in the next iteration
                            Thread.sleep(1000);
                        }
                        sinkChannel.close(); // Close the channel when done
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };

            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try{
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for (int i = 0; i < 10 ; i++) {
                            int bytesRead = sourceChannel.read(buffer);
                            if (bytesRead == -1) {
                                break; // Handle end-of-stream condition
                            }
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip();
                            buffer.get(timeString);
                            System.out.println("Reader Thread : "+ new String(timeString));
                            //buffer.flip();
                            buffer.clear(); // Clear the buffer to read new data in the next iteration
                            Thread.sleep(1000);
                        }
                        sourceChannel.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };

            // start both threads
            new Thread(writer).start();
            new Thread(reader).start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
