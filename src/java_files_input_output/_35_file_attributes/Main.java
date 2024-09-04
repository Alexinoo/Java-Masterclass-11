package java_files_input_output._35_file_attributes;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/*
 * File Attributes
 *  - Let's look at creating files and the Files class has methods for creating files and directories
 *
 *  - However, keep in mind that we often create file because we want to write something to it and more often than not, we might want to open a stream
 *    or a channel and doing that will actually create the file
 *
 *
 * //// Files.createFile(Path file)
 *
 * - Let's create Examples\file2.txt
        Files.createFile(fileToCreate);
 *
 *      - Examples\file2.txt created though it's empty and if we wanted to write to it, we'd have to create a stream or a channel and there's probably no
 *         point of creating a file with Files.createFile() and we know it's going to be empty anyway
 *          - But it's good to know that it's there for some reason, if we want to create it that way
 *
 * ///// Files.createDirectory()
 *  - However we can't use it to create directories and instead we use Files.createDirectory()
 *
 *
 * //////// Created nested directories - Option 1
 *  - Create Dir6 and nest (dir2/dir3/dir4/dir5/dir6)
 *  - You might think that you'll have to call createDirectory() multiple times , and you could do it like that but there's an easier way and you
 *    could call createDirectories()
 *
 *      Path dirToCreate = FileSystems.getDefault().getPath("Examples","Dir5\\Dir6\\Dir7\\Dir8\\Dir9");
 *      Files.createDirectories(dirToCreate);
 *
 *      - Escape the backslash by using 2 of them
 *
 * //////// Created nested directories - Option 2
 *  - Change back slashes to forward slashes
 *
 *      Path dirToCreate = FileSystems.getDefault().getPath("Examples\\Dir5\\Dir6\\Dir7\\Dir8\\Dir9");
 *      Files.createDirectories(dirToCreate);
 *
 *
 *
 * ////// Get File Attributes - file metadata
 *  - Get file attributes such as :-
 *      - file size
 *      - last modified
 *      - whether a path points to a file/directory
 *
 *  - Using the Files class, you can get the value of a single attribute , or alternatively, retrieve all attributes all at once
 *
 *  - Let's look at getting a single attribute file size for "Example\Dir1\file1.txt"
 *
 *      - Files.size(Path path)
 *          - return file size
 *
 *      - Files.getLastModifiedTime(Path path)
 *          - return last modified time - datetime
 *
 *  - Checks the Files class and look for other methods that are available for getting the value for a single attribute
 *      - check the link below
 *          https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
 *
 * /////
 * - We can get all the attributes in a single shot
 * - We need to retrieve an instance of a class that implements an interface containing methods that can retrieve each individual attribute
 *
 * - It's actually BasicFileAttributes interface , that covers a basic set of attributes that are common to all OS's
 * - Call
 *
 *     Files.readAttributes()
 *      - pass the Path instance
 *      - pass sets of attributes you want to retrieve by passing BasicFileAttributes.class
 *
 * - Files.readAttributes() returns an instance that implements the BasicFileAttributes interface and we have to actually pass BasicFileAttributes.class
 *    because there are system specific sub-interfaces of basic file attributes in the java.nio package that can also be retrieved using this method
 *
 *      BasicFileAttributes attrs;
 *
 * - We can then call methods from the above instance to get the values of the attributes that you're sort of interested with
 *      attrs.size(); etc
 *
 * - Check the following doc for more info
 *      https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/BasicFileAttributes.html
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        try{
            /* ** Create Examples\file2.txt **
            Path fileToCreate = FileSystems.getDefault().getPath("Examples","file2.txt");
            Files.createFile(fileToCreate); */

            /* * Create Nested directories **
            Path dirToCreate = FileSystems.getDefault().getPath("Examples","Dir4");
            Files.createDirectory(dirToCreate); */

            /* Create Nested Dir - option 1
            Path dirToCreate = FileSystems.getDefault().getPath("Examples","Dir5\\Dir6\\Dir7\\Dir8\\Dir9");
            Files.createDirectories(dirToCreate); */

            /* Create Nested Dir - option 2
            Path dirToCreate = FileSystems.getDefault().getPath("Examples\\Dir5\\Dir6\\Dir7\\Dir8\\Dir9");
            Files.createDirectories(dirToCreate); */


            /* File ATributes - file size **/
            Path filePath = FileSystems.getDefault().getPath("Examples","Dir1","file1.txt"); // Option-1
            //Path filePath = FileSystems.getDefault().getPath("Examples","Dir1\\file1.txt"); // Option-2
            long size = Files.size(filePath);
            System.out.println("Size = "+size);

            /* File Attributes - last modified */
            FileTime lastModified =  Files.getLastModifiedTime(filePath);
            System.out.println("Last Modified = "+lastModified);


            /* Get All file attributes */
            BasicFileAttributes attrs = Files.readAttributes(filePath,BasicFileAttributes.class);
            System.out.println("Size = "+attrs.size());
            System.out.println("Last Modified = "+attrs.lastModifiedTime());
            System.out.println("Created = "+attrs.creationTime());
            System.out.println("Is Directory = "+attrs.isDirectory());
            System.out.println("Is regular file = "+attrs.isRegularFile());

        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }
}
