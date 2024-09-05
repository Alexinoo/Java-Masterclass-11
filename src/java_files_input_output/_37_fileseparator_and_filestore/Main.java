package java_files_input_output._37_fileseparator_and_filestore;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/*
 *
 * Separators Temp Files and File Stores
 *
 * Let's tie a few loose ends:
 *  - On windows, we use the backslash to separate a path segment / path segments
 *  - On Unix/Linux, we use the forward slash
 *
 * - When building paths and/or writing regex or globs , we may need to know the file separator,
 *      - Unless we're writing for ourselves when we're learning, we can't assume that we're on a specific OS when we're writing Java code
 *
 * - There are two ways of getting the file separator for the file system
 *  - Using File.separator constant which is still commonly used
 *  - Call FileSystems.getDefault().getSeparator()
 *
 *           String separator = File.separator;
 *           separator = FileSystems.getDefault().getSeparator();
 *
 *      - prints forward slash on unix/linus and back slash on Windows
 *
 *  - It's a good practice to never hard code the file separator , but we've been doing that for our demo apps , but in a real world application, we'd
 *    the following instead of hard coding below, using the back slash
 *
 *       Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");
 *
 *      - and the code still works as expected
 *          - File.separator , will still works on both Windows/Unix/Linux unmodified
 * - And this is actually the best way to go when we're actually producing apps and releasing them commercially
 *
 * - When building long paths, you'd probably use StringBuilder, but we'll continue to hard code the File.separator for readability and to keep the code
 *    simple
 *
 * ///// Create Temp files
 * - Figure out how to create temporary files
 *      - Sometimes an app needs to create files that it doesn't want to keep
 * - Files.createTemp(prefix , suffix)
 *      - Creates temporary file in the OS default temporary file directory
 *
 * - The first parameter in Files.createTemp() is a prefix
 *      - The temp file created will have a name that begins with this prefix
 *
 * - The second parameter in Files.createTemp() is a suffix
 *      - And in this case, we usually pass the file extension for this parameter
 *
 * - We can actually pass a 3rd OPTIONAL parameter, of type FileAttribute that specifies attributes for the file, but for temporary files, there's
 *    often no reason to do that
 *
 *
 * ///
 *  - Running below creates, the specified file and we can trace it from here
 *      C:\Users\ALEXWA~1.STL\AppData\Local\Temp\myapp3233695997991007791.appext
 *
 * - One thing to note is that the resulting path name is always associated with the default file system
 *      - If we wanted to store the temporary file somewhere else, we can do that by using another version of the File.createTemp() that accepts more
 *        parameters including a parent directory path
 * - There also Files.createTempDirectory() as well
 *
 *
 * /// File Stores or Drives
 *  - On Unix, each mounted system is a file store
 *  - On Windows, each drive or volume is a file store
 *      - e.g.
 *              - C:\
 *              - D:\
 *              - F:\
 *
 *  - So how do we get the File stores
 *
 *  - We can use FileSystem.getDefault().getFileStores() for that
 *      - This returns an Iterable, of FileStore : Iterable<FileStore>
 *
 * ////
 * - The FileSystems.getDefault() returns a FileSystem obj
 *      - Some systems can have more than 1 file system, and when we call getDefault(), you'll be getting the default and often only File system
 *
 * - We get the names "New Volume" printed
 *      New Volume
        New Volume
 *
 * - It would be useful to get the Drive letters and there's actually 2 ways to get them
 *      - Use just store
 *          System.out.println(store);  -- prints New Volume (D:)
 *          System.out.println(store);  -- prints New Volume (E:)
 *
 * - We can also parse the String to get the drive letter, if on Windows machine , though potentially risky, but some developers will consider that
 *    risky in the case of the formatting of the return string if that ever changes
 *      - Though the likelihood of that happening is pretty low
 *
 * - There's another way of doing this as well, though not a very ideal way
 *      - And that's by calling FileSystems.getDefault().getRootDirectories(), which returns Iterable<Path> rootPaths
 *      - Iterate and get each path
 *
 *      - Prints only the drive letters, but drive letters for all available drives in their systems will be printed when they're actually
 *        available or not
 *          C:\
 *          D:\
 *          E:\
 *      - Including USB ports connected to Flash drive if any
 *          F:\
 *
 *      - If we had 4 USB ports, but only 2 of them are occupied , drive letters for all 4 would actually still be printed
 *
 * ////
 *  - The truth is we don't really need to get the root drives , as there aren't really many use cases for this
 *  - This is because when an app is installed, it remembers or it can find out where it leaves on the FileSystem
 *      - And then if it then needs the user to tell it where to save files or where to load them from, the user will usually be presented with a
 *          dialogue and can then navigate to or type in the path
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

            for (Path file : contents_all){
                System.out.println(file.getFileName());
            }
            System.out.println("_".repeat(50));

            for (Path file : contents_dat_files){
                System.out.println(file.getFileName());
            }

            System.out.println("_".repeat(50));

            for (Path file : contents_files_only){
                System.out.println(file.getFileName());
            }

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
}
