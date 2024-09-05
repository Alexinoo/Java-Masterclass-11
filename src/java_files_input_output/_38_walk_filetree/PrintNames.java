package java_files_input_output._38_walk_filetree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/*
 * SimpleFileVisitor
 *  FileVisitResult.CONTINUE
 *
 *  FileVisitResult.SKIP_SIBLINGS
 *      - Traversal should skip all the other entries in the same dir as the file
 *
 *
 *  FileVisitResult.SKIP_SUBTREE
 *      - Used when you want to skip a directory
 *
 *  FileVisitResult.TERMINATE
 *      - means you want to stop a traversal
 *      - you'd return TERMINATE, when you've found the file that you're searching for, so that the traversal is actually stopped
 *
 * - It only makes sense to return FileVisitResult.SKIP_SUBTREE from preVisitDirectory()
 *  - Returning it from other methods is equivalent to continuing
 *
 *
 * - Also returning FileVisitResult.SKIP_SIBLINGS from the preVisitDirectory() , then the directory itself is also skipped and also the
 *   preVisitDirectory() is never called for that directory
 *      - In this scenario you're basically saying you want to completely ignore the directory and all its descendants and siblings
 *
 *
 * visitFileFailed()
 *  - If we don't implement this and an error occurs, then an IOException will actually be thrown
 *  - But instead we can notify the user or handle the error and then return continue if you want to actually continue on in the case of an error
 *  - We'll just print out some message here in this case
 *
 *
 *
 */

public class PrintNames extends SimpleFileVisitor<Path> {
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
}
