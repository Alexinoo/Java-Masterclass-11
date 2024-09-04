package java_files_input_output._32_more_on_paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * More on Paths
 *  - Using Paths.get()
 *      - toAbsolutePath() in Action
 *
 *  - normalize()
 *      - skips the dots
 */

public class Main {

    public static void main(String[] args) {

        // Using Paths.get()
        Path filePath = Paths.get(".");
        System.out.println(filePath.toAbsolutePath()); // C:\JMC17\Java-Masterclass-11\.


        System.out.println("_".repeat(50));

        filePath = Paths.get(".","files","SubDirectoryFile.txt");
        System.out.println(filePath.toAbsolutePath()); // C:\JMC17\Java-Masterclass-11\.\files\SubDirectoryFile.txt
        printFile(filePath);

        System.out.println("_".repeat(50));
        filePath = Paths.get("C:\\","JMC17","OutThere.txt");
        System.out.println(filePath.toAbsolutePath()); // C:\JMC17\OutThere.txt
        printFile(filePath);

        // Same as, "." means cwd - somewhat redundant,
        // ".." means back to parent directory
        System.out.println("_".repeat(50));
        filePath = Paths.get(".","files","..","files","SubDirectoryFile.txt");
        System.out.println(filePath.toAbsolutePath()); // C:\JMC17\Java-Masterclass-11\.\files\..\files\SubDirectoryFile.txt
        System.out.println("Normalize path : "+filePath.normalize().toAbsolutePath()); // Normalize path : C:\JMC17\Java-Masterclass-11\files\SubDirectoryFile.txt
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
