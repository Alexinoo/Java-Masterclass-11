package java_files_input_output._34_move_rename_delete_files;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/*
 * Move , Rename and Delete Files
 *
 * - In the last video we created a code that copied "Example\file1.txt" to "Example1.file1copy.txt" and we saw
 *   that the file "Example1.file1.txt" was copied correctly to "Example1.file1copy.txt"
 *
 *
 *
 * /// Copying A File Twice and the problems you can run into
 * - Let's see what happens if we try to run this again
 *      - Update and instead printing stack trace, print exc.getMessage()
 * - If we try to run this again after file1copy.txt is generated , we'll get an exception because the destination file
 *   file1copy.txt already exists
 * - And by default the copy() then throws an exception if that happens
 * - But fortunately we can change , if we want the file to be copied even when the destination file already exists,
 *   you can pass an OPTIONAL parameter to Files.copy() , StandardCopyOption.REPLACE_EXISTING
 *
 *       Files.copy(sourceFile , copyFile, StandardCopyOption.REPLACE_EXISTING);
 *
 *      - And now if we run this again, we don't get any errors that we got previously
 *      - and the file copy was successful and the file got replaced as expected
 *
 * //////// Copy Directories as well as files using Files.copy()
 *
 *  - Copy Examples\Dir1 to Examples\Dir4
 *
 *      sourceFile = FileSystems.getDefault().getPath("Examples","Dir1");
 *      copyFile = FileSystems.getDefault().getPath("Examples","Dir4");
 *
 *      Files.copy(sourceFile , copyFile, StandardCopyOption.REPLACE_EXISTING);
 *
 *      - And if we run this we get Dir4 folder was successfully created from that operation
 *      - However, note that the actual files "file1.txt" and "file2.txt" were not copied
 *
 * - It's actually expected by default for it not to do that , when you want to copy a directory and all the files
 *   and all directories it contains, we have to actually walk the file tree
 * - By default, it only copies the directory & we can delete Dir4, as we won't need it anymore
 *
 *
 * ///// Files.move(Path source , Path destination) - Moving files
 * - But there's another option, instead of copying a file, you may want to move it to another directory or rename it
 * - And we can do that using Files.move()
 * - Let's move Examples\file1copy.txt to the Examples\Dir1 folder
 *
 *      Path fileToMove = FileSystems.getDefault().getPath("Examples","file1copy.txt");
        Path destination = FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
        Files.move(fileToMove,destination);
 *
 *      - And once we run this, we can confirm that "file1copy.txt" file was moved to Examples\Dir1 folder
 *
 *      - We have to provide the full path of destination as we have done below
            Path destination = FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
 *      - It's not enough only to specify the directory
 *
 *      - Notice it was moved from "Examples\file1copy.txt" to "Examples\Dir1\file1copy.txt "and it no longer exist
 *        in the Examples directory as previously when we did the initial copy
 *
 * ////// Renaming files
 * - That as a move, but if we wanted to rename a file , then we use the new file name in the destination path as
 *   follows
 * - Let's take a look of doing that
 * - Lets rename Examples\file1.txt to file2.txt
 *
 *      Path fileToRename = FileSystems.getDefault().getPath("Examples","file1.txt");
        Path RenameTo = FileSystems.getDefault().getPath("Examples","file2.txt");
 *      Files.move(fileToRename, RenameTo);
 *
 *      - And if we run this, we've now got file2.txt from the examples folder
 *      - We're effectively moving it to into the new file name, which is the equivalent of the rename because its
 *        actually moving but in essence is actually renaming
 *      - When renaming files, the source and destination directories have to be the same
 *
 * ////////
 * - Just like Files.copy() , we can pass the StandardCopyOption.REPLACE_EXISTING as a 3rd parameter if you want to
 *    move a file to a file that already exists
 * - An example of when you'd want to do this might be when your replication uses temporary files until a user
 *    explicitly asks to save
 * - You then want to move the temp file to the permanent file, and that file may already exist if the user is
 *    working on a file that they've saved before
 * - You can also use the move() with a directory, but depending on the OS, it might only work for empty directories
 *   which of course isn't very useful
 *
 *
 * ////// Files.delete(Path path)
 * - As you may have guessed, you can use the Files.delete() to delete files
 * - Delete Examples\Dir1\file1copy.txt since we don't need it anymore and it's time to delete
 * - We use Files.delete() to do just that
 *
 *      Path fileToDelete = FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
        Files.delete(fileToDelete);
 *
 *      - And sure enough, Examples\Dir1\file1copy.txt is deleted
 *      - However, if we do run again after the file has been deleted, the catch block is executed which give us
 *          the name of the file which in this case doesn't exist as we've already deleted it
 *      - But what we can do if a file doesn't exist instead of using Files.delete() we can use Files.deleteIfExists()
 *          and once we use this we no longer get an error and in this case the file doesn't exist
 *
            Files.deleteIfExists(fileToDelete);
 *
 * ////// Files.deleteIfExists(fileToDelete)
 *  - Delete if it exists , otherwise if it doesn't exists, it does nothing , and doesn't throw an exception like
 *    Files.delete(fileToDelete)
 *  - It's a good one to use if we don't want to get an exception to be thrown if a file doesn't exist
 *
 * ////
 * - We can use the 2 delete() to delete directories but they have to be empty
 * - When you work with directories , once again you'll have to walk the file tree, which we'll cover in upcoming
 *   videos
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
