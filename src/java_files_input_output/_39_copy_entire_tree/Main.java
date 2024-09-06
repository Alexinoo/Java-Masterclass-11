package java_files_input_output._39_copy_entire_tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * Copy Entire Free
 *
 * - Let's copy everything in dir2 folder to a dir2 directory that is within the dir4 directory
 *
 * - To do this, we'll override the
 *      - visitFile()
 *      - preVisitDirectory()
 *      - postVisitDirectory()
 *
 * - The visitFile() , will be the same as it is for the PrintNames example
 *
 * - Create a new class for this
 *      - CopyFiles that extends SimpleFileVisitor class
 *
 *
 * /////////
 *  - Kickstart things off
 *      - Add
 *          sourcePath
 *          copyPath
 *
 *  - Use a try block
 *      - call Files.walkFileTree()
 *          - pass sourcePath as the first arg
 *          - pass a new instance of the CopyFiles(sourcePath,dirPath) as the 2nd parameter
 *
 *  - catch any IOException
 *
 *
 * //
 *  - We have been able to copy the entire tree now which is pretty cool
 *      - In a real world application, we should consider checking destination folders and probably ask user whether they want to overwrite existing
 *        contents in the destination folder if it already exist
 *
 *  - In addition, you can pass more parameters to the copy() to specify what should happen if the destination path already exists
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
