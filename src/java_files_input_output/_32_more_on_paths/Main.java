package java_files_input_output._32_more_on_paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * java.nio.file.Paths - More on Paths
 *
 * - We can always use Paths.get(URI uri) for the WorkingDirectoryFile.txt and SubDirectoryFile.txt files as well
 * - But when you know the file path will be relative to your working directory, it's actually more convenient and
 *   better practice to get the working directory using FileSystems().getDefault().getPath(String filename)
 * - We have no way of knowing which directory a user will install your application into
 * - So when you want to install files with your application, you can't specify absolute paths in your code and
 *    you have to rely on getting the working directory for the app
 * - You could have the installer store the installation path somewhere, but it's still better to get the working
 *   directory using the methods because it's a foolproof way to do it
 * - For user created files, absolute paths may make sense because usually when the user wants to open them , they'll
 *   navigate to them in the file system
 *
 * /////
 * - In addition to FileSystems.getDefault(), there's also another way to get the working directory for an application
 * - You can use the dot (.) to represent the current working directory
 * - And if you know your way around the terminal/cmd prompt, you'll know that the (.) always refer to the current
 *    directory on both windows and unix based systems
 * - So, let's actually print out the working directory using code
 *
 *       Path filePath = Paths.get(".");
 *       System.out.println(filePath.toAbsolutePath());
 *
 *       - we get the output "C:\JMC17\Java-Masterclass-11\." which is the absolute path printed to the console
 *       - we've used toAbsolutePath() to get the absolute path represented by the Path instance
 *       - note that it also appends the full stop to the path and that will be a backslash full stop or period if
 *          you're running on windows
 *
 * ///////
 *
 * - What we can do is use the dot or period to build a path rather than calling FileSystems.getDefault()
 * - So let's change the way we're building the path to the SubDirectoryFile.txt file
 *
 *      filePath = Paths.get(".","files","SubDirectoryFile.txt");
        System.out.println(filePath.toAbsolutePath());
 *
 *      - prints the following "C:\JMC17\Java-Masterclass-11\.\files\SubDirectoryFile.txt"
 *      - here we're actually passing 3 path segments to the get()
 *      - first we're passing
 *          - the current directory which will resolve to intelliJ project directory
 *      - then we're passing
 *          - the files which is the subfolder
 *      - and finally we're passing
 *          - the file name itself
 *      - so we do have to pass them in the correct order
 * - If we're to pass the file name before the files directory, then we'd get something completely different
 * - And we would get something completely different as
 *
 *       "C:\JMC17\Java-Masterclass-11\SubDirectoryFile.txt\files"
 *
 *      - and obviously it would be wrong because the actual file name is appearing before the files directory
 *      - and so this order is very important in how you'd go about calling that
 *
 * /////////
 * - And just to be sure, let's run this and make sure we're getting the same results - and sure enough, it's still
 *   working well
 *
 *
 * /////
 * - You can build paths relative to any directory and doesn't have actually to be the current directory, but to keep
 *   in mind that a root node has to be included
 * - So when we use this path to get access to "OutThere.txt" file,
 *
 *      filePath = Paths.get("C:\\JMC17\\OutThere.txt");
 *
 *      - we actually specified an absolute path as a single String
 *      - but we can also split it up into pieces such as
 *
 *      filePath = Paths.get("C:\\","JMC17","OutThere.txt");
 * - And if you run that, we should still get the same results
 * - And we've broken our path into 3 distinct area and we can also break it up even more if we wanted to
 *
 * /////
 * - As mentioned earlier, the period or dot or full stop printed means directory
 *       "C:\JMC17\Java-Masterclass-11\."
 *
 *      - and so appending it as (\.) is actually redundant and we don't actually need that in the path
 * - So depending on how you build paths, you could then end up with something weird with something like this
 *    in windows terms
 *       "C:\JMC17\Java-Masterclass-11\.\files"
 *
 *      - and clearly, this wouldn't be right with that extra dot there and it's just actually redundant
 *      - and by the way, if we change that to
 *
 *       "C:\JMC17\Java-Masterclass-11\.\files\..\Java-Masterclass-11"
 *
 *      - this is still valid path where (..) 2 dots/periods means the parent directory
 *      - and in this particular case the path ends up being
 *
 *       "C:\JMC17\Java-Masterclass-11\"
 *
 *      - and the reason for that is because the 2 dots means move up to the previous folder, in this case, we're
 *        moving to the "JMC" folder and would then navigate to "Java-Masterclass-11" folder from "JMC"
 *      - It's just a way to move back up one level to whatever the parent folder was for the current folder
 *
 * /////
 * - But I think, you would agree that it's actually better and more readable to not have those dots , the period or
 *   the 2 periods in them
 * - And we can remove these path segments using the path.normalize()'
 * - Let's actually see how that will work in practice and we can do something like this
 *
 *       filePath = Paths.get(".","files","..","files","SubDirectoryFile.txt");
 *       System.out.println(filePath.toAbsolutePath());
 *       System.out.println("Normalize path : "+filePath.normalize().toAbsolutePath());
 *
 *      - the above will print,
 *
 *          "C:\JMC17\Java-Masterclass-11\.\files\..\files\SubDirectoryFile.txt"
 *
 *       - and we'll get something like this printed after calling normalize() on the Path instance
 *
 *           "Normalize path : C:\JMC17\Java-Masterclass-11\files\SubDirectoryFile.txt"
 *
 * //////
 * - And now that we have done that believe it or not that entire path will still get us to SubDirectoryFile.txt
 *
 *       filePath = Paths.get(".","files","..","files","SubDirectoryFile.txt");
 *
 *       - We're starting on the current working directory - the single period (.) returns the cwd and effectively
 *          redundant and does nothing
 *       - then we move to the files subfolder
 *       - then we move back up a level because we're using the 2 periods which navigates us back to the parent, which
 *          is the working directory in this case
 *       - then we move forward again to the files subfolder and then we get to SubDirectoryFile.txt path which is
 *          the actual file name itself
 * - Run that and make sure that this still works
 *      - and we can see the full path printed and everything else is working as expected
 *
 *
 * /////
 * - The point here is that when you're getting a Path from a user, it's generally a good idea to normalize the path
 *   before using it
 *
 * /////////////////
 * /////////////////
 *
 * Paths
 * - Now that we have an idea of how to build paths to files in the current directory, in a path relative to another
 *   directory, or to anywhere on the file system using an absolute path
 * - Let's briefly discuss why the Path interface came about
 * - The java.io.file class also points to files on the file system
 * - Why did the Java developers introduce another class that does the same thing in java 7 ?
 * - The File class, while useful has a few problems
 * - What are those problems ?
 *
 * ///////
 * - And specifically those problems are :
 *  1. Many methods in the File class don't throw exceptions or provide specific error messages when they fail
 *    File.delete() which returns a boolean
 *      - if the deletion fails, you can't tell if it was because the file didn't exist, or the application didn't
 *         have permissions to delete the file , or...some other reason
 *      - Unfortunately, the method doesn't provide that level of information
 *    File.rename()
 *      - works differently on different platforms
 *      - Java is supposed to be portable across platforms, meaning the code shouldn't worry about which OS it's
 *         running on
 *
 *  2. There's no support for symbolic links.
 *      - A symbolic link is a kind of a file that points to another file
 *      - They're often used with networks, to point to a remote location
 *      - The File class doesn't understand them
 *
 *  3. The File class doesn't provide a way to get metadata about a file, such as its permissions, it's owner, and
 *      other security information
 *      - The metadata it provides is retrieved inefficiently
 *
 *  4. Many of the methods don't perform well when working with lots of data
 *      - For example, if you use the File class to request a list of all files in a directory, and there are a lot
 *        of files, the method can hang believe it or not
 *
 *  5. Since the File class doesn't understand symbolic links, walking a directory tree is problematic
 *
 * /////
 * - We'll talk about how java.nio solves these problems and also how it splits the functionality that was in the
 *  File class into several different classes and interfaces
 *
 *
 *
 *
 *
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
        //filePath = Paths.get("C:\\JMC17\\OutThere.txt");
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
