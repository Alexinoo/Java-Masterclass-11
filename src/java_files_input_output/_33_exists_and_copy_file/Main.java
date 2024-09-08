package java_files_input_output._33_exists_and_copy_file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Exists and CopyFile - java.nio.file.Files class
 *
 * - We looked at the File class and it's limitations, let's now talk about how java.nio solves these problems and
 *   also see how it splits the functionality that was in the File class into several classes and interfaces
 *
 * - The java.io.file class contains methods for pointing to Files and also for performing file operations like delete
 * - In java.nio the Path interface sticks to file paths
 * - So file operations have been moved to the java.nio.file.Files class
 *
 * - We saw the Files class when we used it to read text files but we'll look at in a bit more detail
 * - The Files class contains methods that perform file system operations such as
 *      - copy
 *      - move
 *      - delete e.t.c
 *  - All it's methods are static - and so ultimately, you don't need an instance to call its methods
 *
 * /////
 * - Let's start off by checking whether a file or directory exists , something that you may want to do before you
 *   attempt other operations on that particular file or directory
 * - It is important to understand that the Path interface doesn't actually know anything about the FileSystem ,it
 *   merely understands paths
 * - In a previous video, we got an exception when we deliberately failed to pass enough info to build a path to the
 *   SubDirectoryFile.txt
 * - In fact the exception didn't occur when we created the Path but rather when we tried to create a BufferedReader
 *   obj
 * - This demonstrates that you can create Paths to files that don't actually exist
 *
 *
 * //////
 * - Create a Path obj to a file that doesn't exist
 *
 *       Path path1 = FileSystems.getDefault().getPath("thisFileDoesntExist.txt");
         System.out.println(path1.toAbsolutePath());
 *
 * /////
 * - IF we run this, we still get the absolute path to a file that doesn't exist printed
 *      C:\JMC17\Java-Masterclass-11\thisFileDoesntExist.txt
 *
 *
 *
 * /////
 * - The java.io.file class operates the same way by the way
 * - Until you try to access a file , the path and file is actually abstract
 * - In our app, the rubber only meets the road when we try to create a BufferedReader obj using the path and
 *   that's why the exception is thrown at that point
 * - Only then does it matter if the file pointed to by the Path actually exists
 *
 * /////
 * - Now of course if you're creating a file, then it doesn't matter if it does exist
 * - But to create a file the directory that you specify has to exist
 * - Let's see that in code
 *
 *      Path filePath = Paths.get("Z:\\","abddef","whatever.txt");
        System.out.println(filePath.toAbsolutePath());
 *
 *      - And obviously we're not doing anything different from the previous example other than specifying
 *        another path
 *      - And we quite happily get the path printed as below
 *
 *          "Z:\abddef\whatever.txt"
 *
 *          - and no exceptions are thrown
 *
 * /////
 * - And because of this, depending on what you wanted to do, it can be a good idea to check for the existence of a
 *   file or a directory before using it
 * - Let's check to see if the files subfolder actually exists - we jnow it exists because we created it before
 *    in our previous video
 *
 *      filePath = FileSystems.getDefault().getPath("files");
        System.out.println("Exists = "+ Files.exists(filePath));
 *
 *      - And we get
 *          "Exists = true"
 *      - and that means the files directory or subdirectory does actually exist
 *
 *
 * //////
 * - Let's check either of our bogus folder
 *
 *      Path filePath = Paths.get("Z:\\","abddef","whatever.txt");
        System.out.println(filePath.toAbsolutePath());
 *
 * - Whether they exist
        System.out.println("Exists = "+ Files.exists(filePath));
 *
 *      - and of course we get "Exists = false" as this folder doesn't exist which is what we would expect
 *
 * //////
 * - Files.exists() has an overloaded method that accepts a 2nd parameter that specifies how you want to handle
 *   symbolic links
 * - You can choose to follow them or not to follow them, but by default, they're followed
 * - And if they aren't followed , then if the path contains a symbolic link that points to an existing file the
 *   method will return false
 *
 * //////
 * - There's also Files.notExists() which returns true when a file or directory pointed to by the Path doesn't
 *    exist
 * - In a real world app, you could check for the Paths permission in the printFile() and throw an exception if
 *   the file doesn't exist or perhaps let the IOException be thrown when you try to create BufferedReader obj
 * - Now even if your file exists of course your app may not have permission to use it
 * - But you can actually check that by using these methods
 *      - boolean Files.isWritable(Path path)
 *      - boolean Files.isExecutable(Path path)
 *
 *
 * /////////////////////
 *  Copying a File
 * /////////////////////
 *
 * - Create Example Directory with the following structure
 *
 *      - ExamplesDir
 *          - Dir1
 *             - file1.txt
 *             - file2.txt
 *
 *          - Dir2
 *             - Dir3
 *                - file1.txt
 *                - file2.txt
 *             - file1.txt
 *             - file2.txt
 *             - file3.txt
 *          - file1.txt
 *  - Add some files to the folders as shown above
 * - So basically, we've built a file tree with a few files that we can work with
 *
 *
 * //////
 *
 * - Let's write some code to copy "Examples\file1.txt" to the same folder "Examples\file1copy.txt"
 * - Create both sourceFile Path and copyFile Path instances
 *
 *          Path sourceFile = FileSystems.getDefault().getPath("Examples","file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples","file1copy.txt");
 *
 * - Call copy() on Files and pass both the sourceFile and the copyFile as parameters
 *
            Files.copy(sourceFile,copyFile);
 *
 *          - and once we run this, we can see that "file1copy.txt" file was created from the copy
 *
 * - Catch any IOException that might be thrown by Files.copy()
 *      - print stack trace if we get an error
 *
 * ////
 * - We'll continue to look at how we move a file, rename a file and how to delete a file and so on and so forth
 */
public class Main {
    public static void main(String[] args) {
        // Creating paths that leads to nowhere - don't point to anything - NO EXCEPTIONS ARE THROWN
        Path path1 = FileSystems.getDefault().getPath("thisFileDoesntExist.txt");
        System.out.println(path1.toAbsolutePath()); //C:\JMC17\Java-Masterclass-11\thisFileDoesntExist.txt

        Path filePath = Paths.get("Z:\\","abddef","whatever.txt");
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
