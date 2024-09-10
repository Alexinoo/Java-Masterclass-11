package java_files_input_output._37_fileseparator_and_filestore;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/*
 *
 * File Separators , Temp Files and File Stores
 * .....................................
 *
 * - Alright now that we know how to read directories, and we know how to use globs to retrieve paths within a directory that match a given pattern
 * - We also know how to write a filter that allows us to retrieve paths based on attributes other than the name
 *
 * Before we learn how to walk a file tree, or a directory tree, let's tie a few loose ends:
 *
 *
 * /// File Separator
 *  - On windows, we use the backslash to separate a path segment / path segments
 *  - On Unix/Linux, we use the forward slash
 *
 * - When building paths and/or writing regex or globs , we may need to know the file separator,
 * - Unless we're writing applications for ourselves when we're learning, we generally can't assume that we're on a specific OS when we're writing
 *   Java code
 *
 *
 * //////
 *
 * - There are two ways of getting the file separator for the file system
 *      1. Using File.separator constant which is still commonly used
 *      2. Call FileSystems.getDefault().getSeparator()
 *
 * - Let's look at both ways and print the results
 *
 *           String separator = File.separator;
 *           System.out.println(separator)
 *
 *           separator = FileSystems.getDefault().getSeparator();
 *           System.out.println(separator)
 *
 *           - both options prints forward slash on unix/linus and backslash (\) on Windows
 *
 *
 * /////////
 *
 * - It's a good practice to never hard code the file separator , but we've been doing that for our demo apps , but in a real world application, we'd
 *    the following instead of hard coding below, using the back slash
 *
 * - For example we can replace the following backslashes as follows without having to hard code the 2 back slashes
 *
 *       Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");
 *
 *      - and re-write the above as below
 *
 *       Path directory = FileSystems.getDefault().getPath("FileTree" +File.separator+ "Dir2");
 *
 *      - and the code still works as expected
 *      - File.separator , will still works on both Windows/Unix/Linux unmodified
 * - And this is actually the best way to go when we're actually producing apps and releasing them commercially
 *
 * - When building long paths, you'd probably use StringBuilder, but we'll continue to hard code the File.separator for readability and to keep the code
 *    simple
 *
 *
 *
 * ///// Create Temp files :  Files.createTemp(prefix , suffix)
 *
 * - Figure out how to create temporary files
 * - Sometimes an app needs to create files that it doesn't want to keep
 *
 * - Files.createTemp(prefix , suffix)
 *      - Creates temporary file in the OS default temporary file directory
 *
 * - Let's take a look at an example of doing that
 *
 *      Path tempFile = Files.createTempFile("myapp",".appext");
 *
 *      - The first parameter in Files.createTemp() is a prefix
 *      - The temp file created will have a name that begins with this prefix
 *
 *      - The second parameter in Files.createTemp() is a suffix
 *      - And in this case, we usually pass the file extension for this parameter
 *
 *      - We can actually pass a 3rd OPTIONAL parameter, of type FileAttribute that specifies attributes for the file, but for temporary files,
 *         there's often no reason to do that
 *
 *
 * ///
 *  - Running below creates, the specified file, and we can trace it from here
 *
 *      C:\Users\ALEXWA~1.STL\AppData\Local\Temp\myapp3233695997991007791.appext
 *
 *      - contains a temporary id that's been automatically added by java
 *
 * - Note that the location will be different depending with the OS you're running on obviously
 * - In the case of Windows, the path will look like the above
 *
 * - One thing to note is that the resulting path name is always associated with the default file system
 * - If we wanted to store the temporary file somewhere else, we can do that by using another version of the File.createTemp() that
 *    accepts more parameters including a parent directory path
 *
 * - There also Files.createTempDirectory() that ideally does the same thing as well
 *
 *
 * /////// File Stores or Drives
 * - On Unix, each mounted system is a file store
 * - On Windows, each drive or volume is a file store
 *      - e.g.
 *              C:\ , D:\ , F:\
 *
 * - So how do we get the File stores
 * - We can use FileSystem.getDefault().getFileStores() for that

 *      Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store : stores){
            System.out.println("Volume name/Drive Letter :" +store);
            System.out.println("File store = "+store.name());
        }
 *
 *      - getFileStores() returns an Iterable, of FileStore : Iterable<FileStore>

 *
 * ////
 * - The FileSystems.getDefault() returns a FileSystem obj
 * - Some systems can have more than 1 file system, and when we call getDefault(), you'll be getting the default and often only File system
 *
 * - And if we run this , on windows, we get the names of the volumes printed as below and not the drive letters
 *      - New Volume
        - New Volume
 *
 * - It would be useful to get the Drive letters and there's actually 2 ways to get them
 * - Let's have a look of doing that
 *      - print out just store , which gives us volume names and drive letters
 *
 *      System.out.println(store);  -- prints New Volume (D:)
 *      System.out.println(store);  -- prints New Volume (E:)
 *
 *
 * /////////////////
 *
 * - We can also parse the String to get the drive letter, if on Windows machine , though potentially risky, but some developers will consider that
 *    risky in the case of the formatting of the return string if that ever changes
 * - However, the likelihood of that happening is pretty low , and obviously that's only on Windows, if you're not on Windows, you probably don't
 *   need to do that
 *
 *
 * ///////////////
 *
 * - There's another way of doing this as well, though not a very ideal way
 * - And that's by calling FileSystems.getDefault().getRootDirectories(), which returns Iterable<Path> rootPaths
 *
 *      Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths){
            System.out.println(path);
        }

 *      - Iterate and get each path
 *
 *      - Prints only the drive letters, but drive letters for all available drives in the systems will be printed when they're actually
 *         available or not
 *          C:\
 *          D:\
 *          E:\
 *      - Including USB ports connected to Flash drive if any
 *          F:\
 *
 *      - If we had 4 USB ports, but only 2 of them are occupied , drive letters for all 4 would actually still be printed
 *
 *
 *
 * ////
 *  - The truth is we don't really need to get the root drives , as there aren't really many use cases for this
 *  - This is because when an app is installed, it remembers or it can find out where it leaves on the FileSystem
 *  - And then if it then needs the user to tell it where to save files or where to load them from, the user will usually be presented with a
 *    dialogue and can then navigate to or type in the path
 * - In other words we may never need to get the root directory or directories, but if we do have to, then we can leverage either
 *
 *      FileSystems.getDefault().getRootDirectories();
 *
 *      - or
 *
 *      FileSystems.getDefault().getFileStores();
 *
 * /////
 * - Let's lear how to walk a FileTree in the next example
 */
public class Main {

    public static void main(String[] args) {

        String separator = File.separator;
        System.out.println(separator);

        separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);

        System.out.println("_".repeat(50));

        Path directory = FileSystems.getDefault().getPath("FileTree" + File.separator +"Dir2");
        DirectoryStream.Filter<Path> filterFilesOnly = (path) -> Files.isRegularFile(path);

       try(DirectoryStream<Path> contents_all = Files.newDirectoryStream(directory);
            DirectoryStream<Path> contents_dat_files = Files.newDirectoryStream(directory, "*.dat");
            DirectoryStream<Path> contents_files_only = Files.newDirectoryStream(directory, filterFilesOnly)) {

            printContents(contents_all);
            printContents(contents_dat_files);
            printContents(contents_files_only);

        }catch (IOException | DirectoryIteratorException e){
            System.out.println(e.getMessage());
        }

        try {
            Path tempFile = Files.createTempFile("myapp",".appext");
            System.out.println("Temporary file path = "+tempFile.toAbsolutePath());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store : stores){
            System.out.println("Volume name/Drive Letter :" +store);
            System.out.println("File store = "+store.name());
        }

        System.out.println("_".repeat(50));

        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths){
            System.out.println(path);
        }

    }

    private static void printContents(DirectoryStream<Path> files){
        System.out.println("_".repeat(50));
        for (Path file : files){
            System.out.println(file.getFileName());
        }
    }
}
