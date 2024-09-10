package java_files_input_output._39_copy_entire_tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * Copy Entire Free
 *
 * - Let's copy everything in FileTree\dir2 folder to a dir2 directory that is within the dir4 directory
 *
 * - To do this, we'll override the visitFile() , preVisitDirectory() and the postVisitDirectory()
 *
 * - The visitFile() , will be the same as it is for the PrintNames example
 *
 * - Create a new class for this
 *      - CopyFiles that extends SimpleFileVisitor class
 *
 *      - Check if it can also implement FileVisitor
 *
 *
 * /////////
 * - Kickstart things off
 *
 * - Create Path sourcePath obj
 *
 *      Path sourcePath = FileSystems.getDefault().getPath("FileTree" +File.separator+ "Dir2");
 *
 * - Create Path copyPath obj
 *
        Path copyPath = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir4" +File.separator+ "Dir2Copy");
 *
 *
 *  - Use a try block
 *
 *      - call Files.walkFileTree()
 *
 *          Files.walkFileTree(sourcePath , new CopyFiles(sourcePath , copyPath));
 *
 *          - pass sourcePath as the 1st parameter
 *          - pass a new instance of the CopyFiles(sourcePath,dirPath) as the 2nd parameter which we all know that it implements FileVisitor
 *
 *  - catch any IOException
 *      - print the error message
 *
 *
 * ////
 * - Running this works well and we're able to copy all the contents of FileTree\Dir2 to FileTree\Dir2\Dir2Copy
 * - We have been able to copy the entire tree now which is pretty cool
 *
 *
 * //////
 *
 * - In a real world application, we should consider checking destination files/folders whether they exist and probably ask/prompt users whether they
 *   want to overwrite existing contents in the destination folder if that happens
 *
 * - In addition, you can pass more parameters to the copy() to specify what should happen if the destination path already exists
 * - You have to consider all these scenarios in a real world app
 *
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Copy FileTree\\Dir2  to FileTree\\Dir4\\Dir2Copy --");
        Path sourcePath = FileSystems.getDefault().getPath("FileTree" +File.separator+ "Dir2");
        Path copyPath = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir4" +File.separator+ "Dir2Copy"); // FileTree/Dir4/Dir2Copy

        try {
            Files.walkFileTree(sourcePath , new CopyFiles(sourcePath , copyPath));
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }

    }
}
