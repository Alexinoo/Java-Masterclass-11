package java_files_input_output._30_pipes_with_threads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/*
 * Pipes With Threads
 *  - Transfer data between threads, and they're actually a one-way connection so the data can only flow one way
 *  - A pipe got 2 channels
 *      - Sink channel
 *      - Source channel
 *  - One or more threads can write or writes to the sink channel and the other thread or threads read from the source
 *     channel
 *
 * - We need 2 threads
 *      - one that will write to the sink channel
 *      - one that will read from the source channel
 * - Start both threads
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
                            buffer.flip();
                            Thread.sleep(100);
                        }
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

            // start both threads
            new Thread(writer).start();
            new Thread(reader).start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
