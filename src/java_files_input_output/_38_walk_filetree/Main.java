package java_files_input_output._38_walk_filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * Files.walkFileTree(Path path ,new PrintNames<Path>())
 *
 * - Walking a FIle Tree in this case means visiting every file, or directory that can be reached from a starting or root directory for
 *   that tree
 *
 * - For example, if we started in FileTree\dir2 in our project, we would visit the following files and directories but not necessarily in
 *   any particular order
 *      - file1.txt , file2.txt and file3.dat
 *      - dir3
 *          - file1.txt
 *          - file2.txt
 * - Since we're going to sat at dir2 folder , this will be considered as the root folder of its file tree , and we're never going to move up
 *   a level from the root directory, and we're only be moving down levels
 *
 *
 * ///////
 *
 * - You might be wondering why would you want to do this ?
 *
 * - One use case might be when the application has to find a file,
 * - Another use case could be when the application is searching all the files in the directory and below for a specific string
 * - When you want to copy a directory, you have to walk file tree if you want to copy everything below it as well
 * - Otherwise, only the files directly within the directory itself would be copied
 * - As we saw earlier when we were using the DirectoryStream to get contents of a directory, only the direct descendants are returned by default
 *
 *
 * - Let's now talk briefly about the FileVisitor interface, which is what we need to use to walk the directory tree
 *
 * ///////////////////////////////
 * FileVisitor interface Overview
 * ///////////////////////////////
 *
 * - To walk the directory tree, you'll have to use the FileVisitor Interface
 * - Using the methods in FileVisitor, you can run code at each stage of the traversal process
 * - Below are the methods you'll have to implement:
 *
 *      - preVisitDirectory( Path dir, BasicFileAttributes attrs)
 *          - this method accepts a reference to the directory, and the BasicFileAttributes instance for the directory
 *          - It's called before entries in the directory are visited
 *
 *      - postVisitDirectory(Path dir, BasicFileAttributes attrs)
 *          - this method accepts a reference to the directory and also an exception object (when necessary)
 *          - It's called after entries in the directory, and all it's descendants , have been visited
 *          - The exception parameter will be set when an exception happens during the traversal of the entries and descendants
 *          ///////
 *          - There's a way to skip files as you're traversing and so not every file has to have been visited for this method to be called.
 *          - Every file has to be visited or explicitly skipped
 *          - Of course, if an exception is thrown and not handled , the traversal will prematurely end and postVisitDirectory() will be called
 *
 *      - visitFile(Path file, BasicFileAttributes attrs )
 *          - probably the method that you'll care most about
 *          - This method accepts a reference to the file and a BasicFileAttributes instance
 *          - This is where you run the code that will operate on the file
 *          - It's only called for files
 *
 *      - visitFileFailed(Path file, IOException exc)
 *          - this method is called when a file can't be accessed
 *          - The exception that's thrown is passed to this method
 *          - You can then decide what to do with it (throw it , print it, or anything else that makes sense for the application and operation
 *             being performed)
 *          - can be called for both files and directories
 *
 * - As with most interfaces like this , the jdk has got an implementation that you can use called SimpleFileVisitor
 * - You can extend this class and override just the methods that you require
 *
 *
 * /////////
 * - Let's create a class that extends SimpleFileVisitor and traverse the file tree with dir2 as its root
 * - We'll print the name of every file and directory it visits
 *
 *      - Create PrintNames class -->
 *
 * /////////////////\\\\\\\\\\\\\\\\
 * /////////////////\\\\\\\\\\\\\\\\\
 *
 *  Files.walkFileTree(Path start , FileVisitor<? super Path> visitor )
 *
 * - Create a Path obj for the root directory where we're starting from
 *
 *      Path dir2Path = FileSystems.getDefault().getPath("FileTree" + File.separator +"Dir2");
 *
 * - Start the traversal by using Files.walkFileTree(Path start , FileVisitor<? super Path> visitor )
 *
 *      Files.walkFileTree(dir2Path , new PrintNames());
 *
 *      - pass the Path obj as the 1st parameter
 *      - pass an instance of the PrintNames class that extends SimpleFileVisitor as the 2nd parameter
 *
 * - Catch any IOException
 *
 * - IF we run this :
 *
 *      --- Walking Tree for Dir2 ----
 *
        pre-visit : C:\JMC17\Java-Masterclass-11\FileTree\Dir2
        pre-visit : C:\JMC17\Java-Masterclass-11\FileTree\Dir2\Dir3

        visiting... C:\JMC17\Java-Masterclass-11\FileTree\Dir2\Dir3\file1.txt
        visiting... C:\JMC17\Java-Masterclass-11\FileTree\Dir2\Dir3\file2.txt
        visiting... C:\JMC17\Java-Masterclass-11\FileTree\Dir2\file1.txt
        visiting... C:\JMC17\Java-Masterclass-11\FileTree\Dir2\file2.txt
        visiting... C:\JMC17\Java-Masterclass-11\FileTree\Dir2\file3.dat
 *
 *      - and we can see that we're able to traverse to every node
 *
 * //////
 *
 * - There's also another version of walkFileTree() that accepts 4 parameters
 *
 * - The additional 2 parameters allow you to specify the number of levels to traverse and to pass a set of file visit options
 *
 * - Right now the only option available is whether to follow symbolic links
 *
 * - When we're printing out the names, it doesn't matter whether we use preVisitDirectory() to print directory names or postVisitDirectory()
 * - But depending on what you want to do, it can matter though
 *
 * - For example :
 *
 * - if you wanted to copy a file tree, then you'd want to handle the directory before you handle it's entries because the copy
 *   of the directory will have to exist before you can copy entries into it
 * - In that scenario, you would actually handle the directory using the preVisitDirectory()
 *
 *  - if you wanted to traverse a file tree to delete it, then you actually want to handle the directory in the postVisitDirectory() because you have
 *    to delete all the entries in the directory and all it's descendants before you can delete the directory.
 *
 * - So, whether to handle directories pre or post will actually depend on makes sense for the type of processing that you want to do
 * - You also can't make any assumptions about the order in which files and directories are visited, and shouldn't make any assumptions
 *     with your code in that scenario
 *
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("--- Walking Tree for Dir2 ----");
        Path dir2Path = FileSystems.getDefault().getPath("FileTree" + File.separator +"Dir2");

        try{
            Files.walkFileTree(dir2Path , new PrintNames());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }


    }
}
