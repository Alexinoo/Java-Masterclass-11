package java_files_input_output._14_readwithscanner_bufferredreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Simplified {

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader( new FileReader("example.txt"))){
            String line;
            while (true){
                line = bufferedReader.readLine();
                if (line == null)
                    break;
                System.out.println(line);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
