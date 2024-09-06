package java_files_input_output._39_copy_entire_tree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/*
 * Class : CopyFiles that extends SimpleFileVisitor
 *
 * Override
 *  visitFailed()
 *      - Print the error
 *      - Return FileVisitResult.CONTINUE
 *
 *  preVisitDirectory()
 *      - In order to figure out the path for the copied file, we'll use Path.relativized()
 *          - This method constructs a relative path that resolves to a given path
 *          - e.g. If a path to relativized against is C:\path1 and the given path is C:\path1\path2\path3 , then the relativized path would be
 *              \path2\path3 which is the given path relative to C:\path1
 *      - Once we get the relative path, we then need to resolve it against the copy directory's location by calling the path.resolve()
 *      - And that will turn the relative path we got from the relativized() into the full path for the copied file
 *
 * ////
 * - The root of the source path is the FileTree\Dir2
 * - And the root of the destination path is FileTree\Dir4\Dir2 -  (with Dir2) copy folder which at the moment doesn't exist
 *
 * - So, the relative path of the file in both the source and destination roots will be dir3\file1.txt
 *
 * - So we call the relativized() to get the relative path to the source root
 *
 * // Example:
 *      sourcePath = "FileTree\Dir2\Dir3\file1.txt";
 *      sourceRoot = "FileTree\Dir2";
 *      targetRoot = "FileTree\Dir4\Dir2Copy";
 *      relativizedPath = sourceRoot.relativize(sourcePath);  which = "Dir3\file1.txt"
 *      resolvedPathForCopy = targetRoot.resolve(relativizedPath); which = "FileTree\Dir4\Dir2Copy\Dir3\file1.txt"
 *
 * - We're essentially figuring out the relative path to the sourceRoot and then appending it to the targetRoot because when doing a copy of the
 *   relative path to both, the source and target roots will be the same
 *
 * - As we may have noticed, we need the source root path and destination root path to figure out the path for the copy
 * - But the preVisitDirectory() and visitFile() are only passed to the directory or file that's being visited
 *
 * - So we'll have to save the source and destination roots as instance variables in the CopyFiles class  and consequently, add those fields
 *   and a constructor to handle that
 *
 *
 * ////////
 *  - preVisitDirectory()
 *      - we'll use relativized and resolve methods to figure out the path for the copied file
 *      - Once we get the resolved destination path, we can call the copy() to do the actual copy
 *      - In case of an error, skip the enitre directory and it's contents via the FileVisitResult.SKIP_SUBTREE constant
 *
 *  - visitFile()
 *      - we'll use relativized and resolve methods to figure out the path for the copied file
 *      - Once we get the resolved destination path, we can call the copy() to do the actual copy
 *      - In case of an exception, we'll just print the error message
 *
 *
 * ///
 *  - Kick off the copy
 */

public class CopyFiles extends SimpleFileVisitor<Path> {

    private Path sourceRoot; //"FileTree\Dir2";
    private Path targetRoot; //"FileTree\Dir4\Dir2Copy";

    public CopyFiles(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("preVisitDirectory called ...");
        Path relativizedPath = sourceRoot.relativize(dir);        // "FileTree\Dir2".relativize("FileTree\Dir2\Dir3\file1.txt");
        System.out.println("RelativizedPath = "+relativizedPath); // relativizedPath = "Dir3\file1.txt"

        Path copyDir = targetRoot.resolve(relativizedPath); // "FileTree\Dir4\Dir2Copy".resolve("Dir3\file1.txt");
        System.out.println("Resolved path for copy = "+copyDir); // copyDir = "FileTree\Dir4\Dir2Copy\Dir3\file1.txt"

        try{
            Files.copy(dir , copyDir);                      // Files.copy("FileTree\Dir2\Dir3\file1.txt" , "FileTree\Dir4\Dir2Copy\Dir3\file1.txt");
        }catch (IOException exc){
            System.out.println(exc.getMessage());
            return FileVisitResult.SKIP_SUBTREE;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("visitFile called ...");
        Path relativizedPath = sourceRoot.relativize(file);        // "FileTree\Dir2".relativize("FileTree\Dir2\Dir3\file1.txt");
        System.out.println("RelativizedPath = "+relativizedPath); // relativizedPath = "Dir3\file1.txt"

        Path copyDir = targetRoot.resolve(relativizedPath); // "FileTree\Dir4\Dir2Copy".resolve("Dir3\file1.txt");
        System.out.println("Resolved path for copy = "+copyDir); // copyDir = "FileTree\Dir4\Dir2Copy\Dir3\file1.txt"

        try{
            Files.copy(file , copyDir);                      // Files.copy("FileTree\Dir2\Dir3\file1.txt" , "FileTree\Dir4\Dir2Copy\Dir3\file1.txt");
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing "+ file.toAbsolutePath() + " "+ exc.getMessage());
        return FileVisitResult.CONTINUE;
    }
}
