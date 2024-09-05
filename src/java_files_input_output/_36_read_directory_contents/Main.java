package java_files_input_output._36_read_directory_contents;

import java.io.IOException;
import java.nio.file.*;

/*
 * Read Existing Directory Contents
 *
 * - Directory contents includes the list of files it contains
 *
 * - We'll use FileTree directory for this example
 *
 * //////////////////////////////////////
 * //// Files.newDirectoryStream() /////
 *
 *  - We'll read the entries in the directory using Files.newDirectoryStream()
 *      - this method returns a stream which needs to be closed once finished and we'll need to use try-with-resources and catch IOException if we
 *         get one
 *
 *
 * ///// Example
 *
 * - Let's read the contents of FileTree\Dir2 which contains
 *      - Dir3
 *      - file1.txt
 *      - file2.txt
 *      - file3.dat
 *
 * - And sure enough, we get the above from "DirectoryStream<Path> contents"
 *      - Files.newDirectoryStream(directory) returns a DirectoryStream<Path>
 *      - DirectoryStream is an interface that implements the Iterable interface
 *          and consequently what that means is that we can iterate over the return paths to get the contents of the dir2 folder
 * - Also notice, that only the contents directly within the dir2 directory are within the stream
 *      - also referred to as directory direct descendants
 *
 * - It doesn't go into dir3 directory in this case and return it's files as well
 *      - we'll see how to walk an entire directory tree a little bit later on
 *
 * - When iterating over a directory stream, a DirectoryIteratorException may be thrown and it can be a good practice to catch that using the pipe
 *   character in conjunction with IOException
 *      - | is also known as Bitwise Inclusive OR
 *
 * - Suppose we only wanted to list or work with specific types of files in a directory
 * - For example, let's say we only want .dat files
 *      - We could retrieve everything from the directory and then check each path name but there's actually a better way to do it
 *
 * - The newDirectoryStream() accepts a Filter as a second parameter
 *      - And only paths that much the filter will actually be included in the directory stream
 * - This second filtering parameter is known as a glob
 *      - A glob is a pattern similar to a regex but the syntax is more geared towards the type of things you'd want to do when working with paths
 *
 * Examples:
 *  - * character matches any string (can contain any number of characters)
 *  - *.dat will match any path with the .dat extension
 *  - *.{dat,txt} will match any path that has the extension .dat or .txt
 *  - ? matches exactly one character
 *      - e.g. the glob ??? would match any path that contains exactly 3 characters
 *
 *  - myFile* matches any paths that begin with myFile
 *
 *  - b?*.txt matches any paths that are at least 2 characters long and begin with the character b
 *      - the ? forces a second character, and the * matches 0 or more characters
 *
 * ///////
 * - Check FileSystem.getPathMatcher() for more patterns
 *      - If the glob syntax isn't sufficient for us to describe the paths you want to retrieve, we can pass the regex, and take advantage of the
 *        power that they offer
 *      - But majority of the time, the glob should be enough
 *
 * ///
 * Suppose we wanted to retrieve files with .dat extension , we can pass "*.dat" as a second parameter to the Files.newDirectoryStream()
 *      - Refactor file3.dat to file3.dat for this to work and once we run this, we only get file3.dat as the only output
 *      - Because that's the only file that's meet our criteria of *.dat
 *
 *
 * ////
 * - We can also use isDirectory() and isFile() when we retrieve the entire contents of a directory, to separate files from directories but there is
 *   actually an easier way to do it
 * - But this time , a glob won't help that much because globs are used to match patterns against file names
 *      - operate on file attributes
 *
 * - There's a 3rd version of Files.newDirectoryStream() that accept a DirectoryStream.Filter parameter : :- DirectoryStream.Filter<? super Path> filter
 *    and this allows us to write our own filters
 *
 * ///
 * - Let's look at an example of writing a filter that only returns files and not directories
 * - The DirectoryStream.Filter<? super Path> interface has got only 1 method called accept and that's the method we have to implement
 *      - When accept returns true for a path, the path will be included in the directory stream results
 *
 * - The DirectoryStream.Filter interface has only 1 method called accept() and when it returns true for a path, the path is going to be included
 *    in the DirectoryStream results
 *      - we're testing to see if a particular Path is a regular file , and if that returns true. then that means it wil be shown in the
 *         contents_files_only list that we're going to iterate
 *      - And sure enough , we do get the following files
 *          - file1.txt
 *          - file2.txt
 *          - file3.dat
 *      - And basically, the filter has now eliminated the dir directory
 *
 * - And since DirectoryStream.Filter interface , is a @FunctionalInterface , we could also use a lambda expression as well
 *
 *      DirectoryStream.Filter<Path> filterFilesOnly = (path) -> Files.isRegularFile(path);
 *
 *      - more concise but we do get exactly the same results
 *
 *
 */
public class Main {

    public static void main(String[] args) {

       /*  *** Anonymous class ***
        DirectoryStream.Filter<Path> filterFilesOnly = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path path) throws IOException {
                return Files.isRegularFile(path);
            }
        }; */

        /* Using a lambda */
        DirectoryStream.Filter<Path> filterFilesOnly = (path) -> Files.isRegularFile(path);

        // We use FileSystems.getDefault() to get the working directory - so we don't have to specify the absolute path
        Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");

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
    }
}
