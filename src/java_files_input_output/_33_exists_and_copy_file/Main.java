package java_files_input_output._33_exists_and_copy_file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Exists and CopyFile - java.nio.file.Files class
 *
 *  - All it's methods are static - don't need an instance to call it's methods
 *
 * - Create a Path that doesn't exist
 *  - Check with Files.exists()
 *
 * - Also have Files.notExists()
 *      - Returns true if the file or directory pointed to by the path doesn't exist, otherwise false
 *
 * - Also have methods to check if a file is
 *
 *      - Files.isReadable()
 *      - Files.isWritable()
 *      - Files.isExecutable()
 *
 * - Copying a File
 *      - Create Example Directory with the following structure
 *
 *          - ExamplesDir
 *              - Dir1
 *                  - file1.txt
 *                  - file2.txt
 *
 *              - Dir2
 *                  - Dir3
 *                      - file1.txt
 *                      - file2.txt
 *                  - file1.txt
 *                  - file2.txt
 *                  - file3.txt
 *              - file1.txt
 *
 *      - Write code to copy "Examples\file1.txt" to the same folder "Examples\file1copy.txt"
 *
 *
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        // Creating paths that leads to nowhere - don't point to anything - NO EXCEPTIONS ARE THROWN
        Path path1 = FileSystems.getDefault().getPath("thisFileDoesntExist.txt");
        System.out.println(path1.toAbsolutePath()); //C:\JMC17\Java-Masterclass-11\thisFileDoesntExist.txt

        Path filePath = Paths.get("C:\\","Examples","whatever.txt");
        System.out.println(filePath.toAbsolutePath()); //C:\Examples\whatever.txt

        //Files.exists(Path path) check if a directory exist
        filePath = FileSystems.getDefault().getPath("files");
        System.out.println("Exists = "+ Files.exists(filePath)); // true

        // check if path1 exist
        System.out.println("Exists = "+ Files.exists(path1)); // false

        // Overloaded Files.exists(Path path, LinkOption... options )


        ///////Copy Examples\file1.txt to Examples\file1copy.txt

        try {

            Path sourceFile = FileSystems.getDefault().getPath("Examples","file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples","file1copy.txt");
            Files.copy(sourceFile,copyFile);

        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }
}
