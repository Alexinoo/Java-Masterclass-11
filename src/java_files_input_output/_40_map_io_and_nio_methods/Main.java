package java_files_input_output._40_map_io_and_nio_methods;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Mapping java.IO and java.NIO Methods
 *
 * - We've learned quite a lot about using java.nio with the File System
 * - Some developers still use java.io and many existing java applications, especially those written before java.nio became available still
 *   use java.io
 * - In addition , many code examples around the web also use java.io
 * - It is very important for us to be able to read java.io code and understand what it's doing
 *
 * - Let's wrap the IO section by looking at how we can map common java.io operations to java.nio
 *
 * ///////
 * - Let's start with Paths
 *
 * - Tim mentioned that rather than using java.io.file , it's better to use java.nio.path
 * - We can use File.toPath() to convert a File instance to the preferred java.nio.path instance
 * - Let's actually give this a try
 *
 *      File file = new File("C:\\Examples\\file.txt");
        Path convertedPath = file.toPath();
        System.out.println("convertedPath = "+convertedPath);
 *
 *      - this prints "convertedPath = C:\Examples\file.txt"
 *      - notice that it doesn't matter whether the String that we pass to the File constructor points to an existing file
 *      - just like Paths, the file doesn't have to exist until the code tries to access it, and even then , sometimes it's
 *         okay if the file doesn't exist for example when opening a stream
 *      - if the file doesn't exist, it'll be created at that point
 *
 * ///////
 *
 * - Once we've got a Path obj , we can go ahead now and use it with all the java.nio classes and methods
 * - We learnt about the path.resolve() that takes part of a Path and resolves it relative to another Path instance
 * - We can do the same thing using the File class , but we have to do it when the File instance is created using the version of the
 *    constructor that accepts a File instance and a String as parameters or the constructor
 *      - Or with an overloaded version with a constructor that accepts 2 Strings
 *
 * - Let's take a look at on doing that
 *
 * - Using the version: new File (File parent , String child)
 *
 *      File parent = new File("C:\\Examples");
        File resolvedFile = new File(parent , "dir\\file.txt");
        System.out.println(resolvedFile.toPath());
 *
 *      - prints "C:\Examples\dir\file.txt"
 *      - we're passing the File instance as the parent and String as the child
 *      - and thereafter converting this to a Path via toPath() and returning a Path instance
 *
 * - Using the version: File(String parent , String child)
 *
 *      resolvedFile = new File("C:\\Examples","dir\\file.txt");
        System.out.println(resolvedFile.toPath());
 *
 *      - prints "C:\Examples\dir\file.txt"
 *      - we're passing the parent and the child as Strings and we get the same result
 *
 * - And incidentally, both of them are equivalent to us doing something along these lines
 *
 *      Path parentPath = Paths.get("C:\\Examples");
        Path childRelativePath = Paths.get("dir\\file.txt");
        System.out.println(parentPath.resolve(childRelativePath));
 *
 *      - prints "C:\Examples\dir\file.txt"
 *
 * //////////////
 *
 * - We've also looked on how to call the
 *      - Files.move() - to move or rename files
 *      - Files.delete() - to delete files
 *
 * - The equivalent methods in the File class are File.renameTo() and File.delete()
 * - Pretty much self explanatory and we won't look at an example on this
 *
 *
 * //////////////
 * - In all the examples, we've used FileSystems.getDefault() to get the working directory for the application and that returns a Path
 * - When working with java.io methods , you actually need to use a File instance
 * - There are several ways to do this , but some of them have drawbacks and that's another reason why working with Paths and the
 *   java.nio classes is better
 * - But here's a hack that is more robust than a lot of other solutions and that is getting the working directory
 *
 *      File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("Working directory = "+workingDirectory.getAbsolutePath());
 *
 *      - prints "C:\JMC17\Java-Masterclass-11" which is the current working directory for this particular project
 *      - a bit of a hack but one that is more robust than other solutions
 *      - this works because when you pass an empty String to the File constructor, it actually translates it to the cwd for you which
 *         of course is the working directory for the application
 *
 * ///////
 *
 * - One major difference between java.io and java.nio is the way you list the contents of a directory
 * - We learnt how to use a DirectoryStream<Path> when working with java.nio , but when working with java.io , we use
 *      - String[] File.list()
 *      - File[] File.listFiles()
 *
 * - The 2 methods above do the same thing but the list() returns a String[] , while listFiles() returns a File[]
 * - In addition, you can pass an OPTIONAL filtering parameter to the list()
 * - Let's take a look at a couple of examples of this
 *
 * - print Dir2 contents using list()
 *
 *      File dir2File = new File(workingDirectory,"FileTree\\Dir2");
        String[] dir2Contents = dir2File.list();

        for (int i = 0; i < dir2Contents.length; i++) {
            System.out.println("i = "+i+ ": " +dir2Contents[i]);
        }
 *
 *      - prints contents of "FileTree\Dir2" folder which are : Dir3 , file1.txt, file2.txt, file3.txt
 *      - Returns 1-level deep or the first level entries and doesn't walk the tree down to files from the sub-folders
 *          - it did not return contents of Dir3 folder
 *
 *
 * - We can do something similar using listFiles()
 *
 * - print Dir2 contents using listFiles()
 *
 *      File[] dir2FilesArray = dir2File.listFiles();
        for (File dir2file : dir2FilesArray){
            System.out.println(dir2file.getName());
        }
 *
 *      - prints contents of "FileTree\Dir2" folder which are : Dir3 , file1.txt, file2.txt, file3.txt : equivalent to what we got from list()
 *      - Returns first level entries and doesn't walk the tree down to sub-folders
 *      - need to use getName() to get the name of the actual file , because we got an array of file objects here
 *      - Another difference is the getName() , when we print the files that returns the last segment to the file path, in this case
 *        the file name
 *
 *
 * /////////////// File.listRoots()
 *
 * - We looked at previously how to get a System's root drives or File stores and how it can be problematic
 * - You can actually use File.listRoot() to get the roots but it has the same problem as the FileSystems.getDefault().getRootDirectories()
 * - It lists drive letters for drives that actually aren't available
 *
 *
 * //////
 * - There are many more java.io versus java.nio operations, but the differences are straightforward in that the method names are usually a
 *   close match
 * - We should now know enough to be able to read code that accesses and modifies the file system using java.io and java.nio methods
 *
 * ////// Key TakeAway
 * - Use java.nio when working with the file system
 * - When it comes to reading and writing file contents , sometimes java.io streams are still the better choice
 * - Need to benchmark though and decide what's best for your application though
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Examples\\file.txt");
        Path convertedPath = file.toPath();
        System.out.println("convertedPath = "+convertedPath); // convertedPath = C:\Examples\file.txt

        File parent = new File("C:\\Examples");
        File resolvedFile = new File(parent , "dir\\file.txt");
        System.out.println(resolvedFile.toPath()); //C:\Examples\dir\file.txt

        resolvedFile = new File("C:\\Examples","dir\\file.txt");
        System.out.println(resolvedFile.toPath()); //C:\Examples\dir\file.txt

        Path parentPath = Paths.get("C:\\Examples");
        Path childRelativePath = Paths.get("dir\\file.txt");
        System.out.println(parentPath.resolve(childRelativePath));

        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("Working directory = "+workingDirectory.getAbsolutePath()); // C:\JMC17\Java-Masterclass-11

        System.out.println("-- print Dir2 contents using list() --");
        File dir2File = new File(workingDirectory,"FileTree\\Dir2");
        String[] dir2Contents = dir2File.list();

        for (int i = 0; i < dir2Contents.length; i++) {
            System.out.println("i = "+i+ ": " +dir2Contents[i]);
        }


        System.out.println("--- print Dir2 contents using listFiles() --");
        File[] dir2FilesArray = dir2File.listFiles();
        for (File dir2file : dir2FilesArray){
            System.out.println(dir2file.getName());
        }

    }
}
