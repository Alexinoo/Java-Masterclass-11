package java_files_input_output._23_read_write_with_nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/*
 * Java NIO - Read and Write to Text Files
 */
public class Main {
    public static void main(String[] args) {
        try{
            // create FileInputStream obj
           // FileInputStream file = new FileInputStream("data.txt");

            // call getChannel() to get a file channel
          //  FileChannel channel = file.getChannel();

            Path dataPath = FileSystems.getDefault().getPath("data.txt");

            // Adding/Appending some data to a file
            Files.write(dataPath,"\nLine 4".getBytes("UTF-8"), StandardOpenOption.APPEND);

            // Read all several lines
            List<String> lines = Files.readAllLines(dataPath);

            // Printing content
            for (String line : lines){
                System.out.println(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
