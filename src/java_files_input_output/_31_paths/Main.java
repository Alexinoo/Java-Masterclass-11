package java_files_input_output._31_paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * FileSystem
 *
 * Overview
 *
 * - We've learned how to create , read , and write files and data sources using java.nio
 * - Sometimes, you don't want to read from a file or write to a file
 * - You may want to
 *      - copy files,
 *      - delete files
 *      - or move files
 * - In other words, you want to work with the File system
 * - java.nio has you covered
 * - In fact there's a whole package dedicated to working with the File System , called java.nio.file
 *
 * /////
 *
 * - We'll start by taking a look at the Path interface which is what we'll use to identify a file on the file system
 * - We've already used Paths
 *      - We created them when we wanted to use java.io classes to read and write files , but we called methods such as
 *            - Files.newBufferedWriter() so that the BufferedWriter would be backed by a java.nio buffer and channel
 *
 * /////
 *  But what actually is a Path ?
 *
 * - Well, we're familiar with the concept of a file path because we use that all the time
 * - If you open the File Explorer on our system, you'll see a bunch of root folders usually
 *      - Hard drives
 *      - peripheral devices like DVD or Blu-ray drives
 *      - External drives
 *      - Thumb drives etc
 * - Each of the these top-level drives is a root node in a path
 * - For example:
 *      C:\ is the root node for the C drive
 *
 * //////
 * - Each folder , also referred to as a directory, is also a node in a path
 * - And then of course there's the file itself
 * - If you have downloaded a file and placed it in the C:\downloads directory, then the file path has 3 nodes
 *      - One for C:\
 *      - One for downloads directory
 *      - One for the file
 *
 * /////
 * - The path is unique
 * - You can't have 2 files with the same name in the same directory because each file is identified by it's path
 * - For the downloads example
 *      - If you named the file you downloaded "file.txt" , then the path would be "C:\downloads\file.txt"
 *      - You wouldn't be able to place a second file called "file.txt" into the same directory
 *
 * /////
 * - The character used to separate the directory names in a path is called a delimiter
 * - This delimiter can differ between operating systems
 * - For example:
 *      - for Windows, the delimiter is the backslash ('\')
 *      - On Unix/Linux , the delimiter is forward slash ('/')
 *
 * //////
 * - Everything we discuss about paths applies to files and directories
 * - To kep things simple, we won't always refer to both
 * - But we create a Path for a file or directory
 * - Sometimes it won't make sense to use a path to a directory for example when you want to create a file
 * - Other times, it will only make sense to use a path to a directory
 * - It depends on what you want to do - just keep in mind that Paths can refer to either file or a directory
 *
 * /////
 * - File paths can be absolute or relative
 * - The path
 *      "C:\downloads\file.txt" is an absolute path
 *
 * - The path
 *      "photos\vacation\mountain.jpg" is a relative path because
 *
 *      - it doesn't specify a root node
 *      - it doesn't contain enough information to identify the file
 *      - it would have to be combined with another path that contains a root node
 *
 * ///// Relative Path
 * - When using relative paths in applications, there's usually the concept of a current or working directory that
 *    you can combine with relative paths
 * - For example, when we're running applications that used Path, we didn't specify the entire Path
 * - We did something like:
 *
 *      Path dataPath = FileSystems.getDefault().getPath("data.txt"); -- relative path
 *
 *      - The String "data.txt" doesn't give enough info about where the file is located
 *      - But notice that we're calling FileSystems.getDefault() and then getPath()
 *      - getDefault() returns a FileSystem obj with a working directory set to the current user directory
 *
 * /////
 * - When running an application from within IDEA, the current user directory is the IDEA project directory and so
 *    that's the working directory for the FileSystem obj
 * - The working directory is then combined with the Path you passed to the getPath() call, and that's why the files
 *     are created in the IDEA project's directory
 *
 *
 *
 * //// Absolute Path
 * - Instead, we could have done something like the following:
 *      Path dataPath = Paths.get("C:\\JMC17\\Java-Masterclass-11\\data.txt"); -- absolute path
 *
 *      - Notice we're using the Paths class ( which has an 's' at the end) when we call the get()
 *      - There are only 2 methods in the class, and both of them return a Path based on a String
 * - Also notice we're using 2 backslashes there because we're using a backslash and we have to escape that so that
 *   java actually knows we're referring to a backslash
 *
 * ///////
 * - Create 3 files
 *      - WorkingDirectoryFile.txt
 *          - copy and paste some text to this file
 *
 *      - Create files directory within this project
 *          - call the file SubdirectoryFile.txt
 *          - copy and paste some text to this file
 *
 *      - Create a file in another folder that is outside the entire path for our current project
 *          - "C:\JMC17\OutThere.txt"
 *
 *
 *
 * ////
 * - How do we access this ?
 *      - Well, we need to build paths to start accessing files
 *
 * - Create a static method printFile(Path path)
 *
 *      - Use BufferedReader with java.nio.file.Files.newBufferedReader(Path path) and pass the path that is passed
 *         to this method
 *      - use a loop and call readLine() to read and print each line to the console until it reaches eof
 *
 *          private static void printFile(Path path){
                try(BufferedReader fileReader = Files.newBufferedReader(path)){
                    String line;
                    while ((line = fileReader.readLine())!= null){
                        System.out.println(line);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
 *
 * - Basically, that's the method that's going to read the contents of a file and output them on the screen
 *
 * /////
 * - Create a Path obj for the WorkingDirectoryFile.txt and call printFile(Path path) and pass our path
 *
 *      Path filePath = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt"); // relative path
 *      printFile(filePath);
 *
 *      - we're creating a Path the same way we've done previously and then calling the printFile(Path path) with it
 * - Since this file is in the working directory , we only have to pass the file name to the getPath()
 * - In other words, we're specifying a relative path and we can run the application to confirm that it works
 * - And clearly, this is working and we've read the "WorkingDirectoryFile.txt" file successfully
 *
 * ////
 * - Create a Path obj for the SubDirectoryFile.txt and call printFile(Path path) and pass our path
 *
 *      filePath = FileSystems.getDefault().getPath("SubDirectoryFile.txt"); // relative path
 *      printFile(filePath);
 *
 *      - If we run this , we get a NoSuchFileException: SubDirectoryFile.txt and it's thrown when the line that creates
 *         the buffered reader was executed
 *      - In this case, the file wasn't found or couldn't be found because we didn't build the path correctly
 *      - When we only specify the file name as we've done above, the String you pass to getPath() , is combined with
 *         the File systems object default or working directory to come up with an absolute path
 *      - In this case though, the working directory is the project directly which of course isn't the folder/directory
 *         that contains the file we're tying to look at
 *      - And since the file isn't located in the working directory, we have to provide more information to the
 *         getPath() by passing a 2nd parameter to the overloaded getPath() to achieve that
 *      - So we do that by passing "files" as the first argument, being the name of the subfolder and the 2nd argument
 *         we leave it as it is which rep the name of the file
 * - And if we run this again , this time we are able to get it filled within the subdirectory and the output
 *   corresponds to the output in the file
 * - If the files has 3 subdirectories deep, then you'll add each subdirectory as a parameter in the order that you
 *   want them to appear in the path
 * - And you can also pass everything as a single string and we'll see an example of that a little bit later
 *
 *
 * ///////
 * - Let's print the contents of the OutThere.txt file
 * - Since the file is located outside the working directory, we can't call the FileSystems.getDefault().getPath()
 * - Instead, we can only work with the Paths.get() and specify the absolute path
 *
 *      filePath = Paths.get("C:\\JMC17\\OutThere.txt");
        printFile(filePath);
 *
 *      - we need to provide the exact location where that file is
 *      - need \\ to escape the backslash for the path on windows
 *
 */

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
