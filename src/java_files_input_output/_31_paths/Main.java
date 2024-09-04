package java_files_input_output._31_paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        // Print the contents of "WorkingDirectoryFile.txt" file
        Path filePath = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt"); // relative path
        printFile(filePath);


        System.out.println("\n////////////////////////////////// \n");


        // Print the contents of "/files/SubDirectoryFile.txt" file
        filePath = FileSystems.getDefault().getPath("files","SubDirectoryFile.txt"); // relative path
        printFile(filePath);

        System.out.println("\n////////////////////////////////// \n");



        //print the contents of OutThere.txt - file not located here - can't use FileSystems.getDefault().getPath()
        // need to provide absolute path but use Paths.get()

        filePath = Paths.get("C:\\JMC17\\OutThere.txt");
        printFile(filePath);
    }

    private static void printFile(Path path){
        try(BufferedReader fileReader = Files.newBufferedReader(path)){
            String line;
            while ((line = fileReader.readLine())!= null){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
