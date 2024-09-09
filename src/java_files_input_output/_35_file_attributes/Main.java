package java_files_input_output._35_file_attributes;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/*
 * File Attributes
 * - Let's look at creating files and the Files class has methods for creating files and directories
 * - The Files class does have methods for creating files and directories
 *
 * - However, keep in mind that we often create file because we want to write something to it and more often than
 *     not, we might want to open a stream  or a channel and doing that will actually create the file
 *
 * //// Files.createFile(Path path)
 *
 * - We'll create a file named file2.txt in the Examples directory
 *
 *      Path fileToCreate = FileSystems.getDefault().getPath("Examples","file2.txt");
        Files.createFile(fileToCreate);
 *
 *      - Examples\file2.txt created though it's empty
 *      - if we wanted to write to it, we'd have to create a stream or a channel and there's probably no point of
 *        creating a file with Files.createFile() and we know it's going to be empty anyway
 *      - But it's good to know that it's there for some reason, if we want to create it that way
 *
 *
 * ///// Files.createDirectory(Path path)
 * - The createFile() is an exception to the rule in that you can't use it to create directories
 * - However we can't use it to create directories and instead we use Files.createDirectory()
 * - Instead, let's actually create a dir under Examples folder using the methods you need to create which is the
 *   Files.createDirectory()
 *
 *       Path dirToCreate = FileSystems.getDefault().getPath("Examples","Dir4");
         Files.createDirectory(dirToCreate);
 *
 *       - NAd if we run this, we can now see that Dir4 folder is now created under Examples subfolder
 *
 *
 *
 * //////// Files.createDirectories(Path path) :    Created nested directories - Option 1
 * - Let's now take this to the next level
 * - Let's create Dir6 with some nested folders such as (dir2/dir3/dir4/dir5/dir6)
 * - You might think that you'll have to call createDirectory() multiple times , and you could do it like that but
 *   there's an easier way and you could call createDirectories()
 *
 *      Path dirToCreate = FileSystems.getDefault().getPath("Examples","Dir5\\Dir6\\Dir7\\Dir8\\Dir9");
 *      Files.createDirectories(dirToCreate);
 *
 *      - And if we run this , we get "Dir5\\Dir6\\Dir7\\Dir8\\Dir9" folders created
 *      - Escape the backslash by using 2 of them on Windows
 *      - is a pretty cool method, not only creates the last entry, the last path Dir9 , but it also creates
 *        non-existing parent directories as well
 *
 * //////// Created nested directories - Option 2
 * - There is another alternative where we can just do with it with one-liner
 *
 *      Path dirToCreate = FileSystems.getDefault().getPath("Examples\\Dir5\\Dir6\\Dir7\\Dir8\\Dir9");
 *      Files.createDirectories(dirToCreate);
 *
 *      - The above will also equally work as well, run
 *      - Change backslashes to forward slashes on Unix/Linux
 *
 *
 *
 * ////// Get File Attributes - file metadata
 * - Let's now look at how to get the file attributes, things like :-
 *      - file size
 *      - last modified
 *      - whether a path points to a file/directory
 * - This data is also referred to as file's metadata : file attributes , or file metadata
 *
 * - Using the Files class, you can get the value of a single attribute , or alternatively, retrieve all attributes
 *   all at once
 *
 *
 * ///// long Files.size(Path path)
 * - Let's look at getting a single attribute file size for "Example\Dir1\file1.txt"
 *
 *          Path filePath = FileSystems.getDefault().getPath("Examples","Dir1","file1.txt"); // Option-1
            Path filePath = FileSystems.getDefault().getPath("Examples","Dir1\\file1.txt"); // Option-2
 *
            long size = Files.size(filePath);
            System.out.println("Size = "+size);
 *
 *          - And if we run this, we get the file size printed for the file that we specified
 *
 *
 * /////// FileTime Files.getLastModifiedTime(Path path)
 * - Let's look at getting the last modified date - datetime
 *
 *          FileTime lastModified =  Files.getLastModifiedTime(filePath);
            System.out.println("Last Modified = "+lastModified)
 *
 *          - And if we run this, we can see that we've got a time that shows the time that file was last modified
 *          - Note that it contains both date and time
 *          - You may have to adjust that time for your time zone
 *
 * //////////
 * - You can also check the Files class and look for other methods that are available for getting the value for a
 *   single attribute
 * - check the link below also available in the resources section
 *
 *          https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
 *
 *
 *
 * //////// Files.readAttributes(filePath,BasicFileAttributes.class)
 * - We can also get all the attributes in a single shot
 * - We need to retrieve an instance of a class that implements an interface containing methods that can retrieve
 *   each individual attribute
 * - It's actually BasicFileAttributes interface , that covers a basic set of attributes that are common to all OS's
 * - Let' take a look of the attributes for the file in : Examples\Dir\file1.txt
 *
        Path filePath = FileSystems.getDefault().getPath("Examples","Dir1","file1.txt");
 *      BasicFileAttributes attrs = Files.readAttributes(filePath,BasicFileAttributes.class);
 *
 *      - pass the Path instance as the 1st parameter
 *      - pass sets of attributes you want to retrieve by passing BasicFileAttributes.class
 *
 * - We can then call methods from the above instance to get the values of the attributes that you're sort of
 *      interested with by calling them on attrs instance
 *
 *      System.out.println("Size = "+attrs.size());
        System.out.println("Last Modified = "+attrs.lastModifiedTime());
        System.out.println("Created = "+attrs.creationTime());
        System.out.println("Is Directory = "+attrs.isDirectory());
        System.out.println("Is regular file = "+attrs.isRegularFile());
 *
 *      - prints the size
 *      - prints the last time the file was modified
 *      - print the time the file was created
 *      - print whether the file is a directory
 *      - print whether the file is a file
 *
 * ////////
 * - The important part is the readAttributes() that we're calling from the Files class to get the basic file
 *   attributes
 * - We tell the method which sets you want to retrieve by passing the second argument, BasicFileAttributes.class
 * - The readAttributes() returns an instance that implements the BasicFileAttributes interface
 * - We have to actually pass BasicFileAttributes.class because there are system specific sub-interfaces of basic
 *   file attributes in the java.nio package that can also be retrieved using this method
 * - You can then call appropriate methods to get the values for attributes you're interested with
 * - And this is only a very small sub-set of what is available for BasicFileAttributes.class
 *
 * ////////
 * - Check the following link for more info also attached in the resources section
 *      https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/BasicFileAttributes.html
 * - To check also the methods that can get access to, to find out more information if needed
 *
 *
 * //////
 * - java.nio contains other interfaces for file attributes by the way that are OS specific in that some
 *   of the attributes they retrieve are only available on the OS they target
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


            /* File Attributes - file size **/
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
