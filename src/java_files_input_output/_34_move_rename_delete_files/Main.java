package java_files_input_output._34_move_rename_delete_files;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/*
 * Move , Rename and Delete Files
 *
 * /// Copying A File Twice and the problems you can run into ///
 *
 * - If we try to run this again after file1copy.txt is generated , we'll get an exception because file1copy.txt already exists and that's the
 *   default behavior
 *
 * - We can change this by passing an OPTIONAL parameter to Files.copy() , StandardCopyOption.REPLACE_EXISTING
 *      - And now this works as expected
 *
 * ////
 * - We can also copy files and directories with Files.copy()
 *      - Copy Examples\Dir1 to Examples\Dir4
 *          - Done but the actual files were not copied
 *          - For this to happen, we have to actually walk the file tree
 *      - By default, it only copies the directory
 *          - Delete Dir4, as we won't need it anymore
 *
 *
 * ///// Files.move() - Moving files
 *  - Moving files instead of copying to another directory or rename it
 *      - Let's move Examples\file1copy.txt to the Examples\Dir1 folder
 *          Files.move(Path source , Path destination);
 *
 *      - Confirmed the file was moved to Examples\Dir1 folder
 *      - We have to provide the full path of destination as we have done below
            Path destination = FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
 *      - Notice it was moved from Examples\file1copy.txt and it no longer exist there anymore
 *
 * ////// Renaming files
 *  - Lets rename Examples\file1.txt to file2.txt
 *      - Use Files.move(fileToRename, RenameTo);
 *  - When renaming files, the source and destination have to be the same
 *
 *  - Just like Files.copy() , we can pass the StandardCopyOption.REPLACE_EXISTING as a 3rd parameter if you want to move a file to a file that already
 *    exists
 *      - An example is when your replication uses temporary files until a user explicitly asks to save
 *      - You then want to move the temp file to the permanent file, and that file may already exist if the user is working on a file that they've saved
 *        before
 *      - You can also use the move() with a directory, but depending on the OS, it might only work for empty directories which ain't really useful
 *
 *
 * ////// Deleting files
 *  - Delete Examples\Dir1\file1copy.txt since we don't need it anymore and it's time to delete
 *  - We use Files.delete() to do just that
 *
 *      Path fileToDelete = FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
        Files.delete(fileToDelete);
 *
 *  - And sure enough, Examples\Dir1\file1copy.txt is deleted
 *
 *  - However, if we do run again after the file has been deleted, the catch block is executed which give us the name of the file which in this case
 *    doesn't exist as we've already deleted it
 *  - But what we can do if a file doesn't exist instead of using Files.delete() we can use Files.deleteIfExists() and we use this we no longer get
 *     an error
            Files.deleteIfExists(fileToDelete);
 *
 * ////// Files.deleteIfExists(fileToDelete)
 *  - Delete if it exists , otherwise if it doesn't exists, it does nothing , and doesn't throw an exception like Files.delete(fileToDelete)
 *  - It's a good one to use if we don't want to get an exception to be thrown if a file doesn't exist
 *
 * ////
 * - We can use delete() to delete directories but they have to be empty
 * - When you work with directories , once again you'll have to walk the file tree
 *
 *
 */
public class Main {
    public static void main(String[] args) {

        try{

            /* ** Copying a file twice -- throws error - add 3rd parameter (OPTIONAL)
            Path sourceFile = FileSystems.getDefault().getPath("Examples","file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples","file1copy.txt");
            Files.copy(sourceFile , copyFile, StandardCopyOption.REPLACE_EXISTING); */

            /* **Copying Directories***
            sourceFile = FileSystems.getDefault().getPath("Examples","Dir1");
            copyFile = FileSystems.getDefault().getPath("Examples","Dir4");
            Files.copy(sourceFile , copyFile, StandardCopyOption.REPLACE_EXISTING); */

            /*  ** Moving files***
            Path fileToMove = FileSystems.getDefault().getPath("Examples","file1copy.txt");
            Path destination = FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
            Files.move(fileToMove,destination); */

            /*  ** Renaming files***
            Path fileToRename = FileSystems.getDefault().getPath("Examples","file1.txt");
            Path RenameTo = FileSystems.getDefault().getPath("Examples","file2.txt");
            Files.move(fileToRename,RenameTo); */

            Path fileToDelete = FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
            //Files.delete(fileToDelete);
            Files.deleteIfExists(fileToDelete);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
