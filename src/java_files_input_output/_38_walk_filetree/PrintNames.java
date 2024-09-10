package java_files_input_output._38_walk_filetree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/*
 * class : PrintNames extends SimpleFileVisitor<Path>

 *
 * Overwrite
 *      visitFile(Path file, BasicFileAttributes attrs)
 *
 *      - print the absolute path by calling toAbsolutePath on Path obj
 *      - Instead of returning super , return FileVisitResult.CONTINUE
 *
 * - Often, it's also a good idea to override postVisitDirectory() as well as the preVisitDirectory, so that you can print the name of
 *   the directories
 *
 * - So, let's do that as well
 * - Overwrite
 *
 *      preVisitDirectory(Path dir , BasicFileAttributes attrs)
 *
 *      - print the absolute path by calling toAbsolutePath on Path obj
 *      - Instead of returning super , return FileVisitResult.CONTINUE
 *
 * - Notice, that we have specified <Path> as the type of the SimpleFileVisitor
 * - This will also be the type of reference passed to the methods
 *
 * - As you can see from visitFile(Path file, BasicFileAttributes attrs)
 *
 *      - A Path obj is passed as the first parameter , and it accepts a reference to a file
 *      - We could be more specific than that because the parameter type has to match the type you specify for the FileVisitor
 *      - We're also printing the absolute path for the file and then returning FileVisitResult.CONTINUE constant
 *
 *      - We can also return FileVisitResult.SKIP_SIBLINGS , which means the traversal should skip all the other entries in the
 *        same directory as the file
 *
 *      - And FileVisitResult.SKIP_SUBTREE can be used when you want to skip a directory
 *
 *      - As well as FileVisitResult.TERMINATE, which means you want to stop a traversal
 *      - For example, you'd return TERMINATE, when you've found the file that you're searching for, so that the traversal is actually stopped
 *
 * - It only makes sense to return FileVisitResult.SKIP_SUBTREE from preVisitDirectory()
 * - Returning it from other methods is equivalent to continuing
 *
 *
 * - Also returning FileVisitResult.SKIP_SIBLINGS from the preVisitDirectory() , then the directory itself is also skipped and also the
 *   postVisitDirectory() is never called for that directory
 * - In this scenario you're basically saying you want to completely ignore the directory and all its descendants and siblings
 *
 *
 * Lastly, let's overwrite
 *
 *      visitFileFailed(Path file, IOException exc)
 *
 *      - If we don't implement this and an error occurs, then an IOException will actually be thrown
 *      - But instead we can notify the user or handle the error and then return continue if you want to actually continue on in the case of
 *        an error
 *      - We'll just print out some message here in this case
 *
 *
 *
 * /////
 * - And nw at this point with the 3 overridden methods, we've got a working FileVisitor
 *
 * - So what we need to do is to start the traversal and we'll do using File.walkFileTree()
 *
 * - Now let's proceed to the Main class and add the code to do that
 *
 *
 */

public class PrintNames implements FileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("visiting... "+file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("pre-visit : "+dir.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

        System.out.println("Error accessing file : "+file.toAbsolutePath() +" "+ exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("post-visit : "+dir.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }
}
