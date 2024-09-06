package java_files_input_output._40_map_io_and_nio_methods;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Mapping IO and NIO Methods
 *
 * toPath()
 *
 * Paths.get()
 *
 * resolve()
 *
 *
 *
 * list() : String[]
 *  - Returns first level entries and doesn't walk the tree down to sub-folders
 *
 *
 * listFiles() : File[]
 *  - Returns first level entries and doesn't walk the tree down to sub-folders
 *  - need to use getName() to get the name of the actual file
 *
 *
 * Files.listRoots()
 *  - List drive letters for drives actually aren't available
 *
 *
 * //////
 * - Use java.nio when working with the file system
 * - When it comes to reading and writing file contents , sometimes java.io streams are still the better choice
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Examples\\file.txt");
        Path convertedPath = file.toPath();
        System.out.println("convertedPath = "+convertedPath); // convertedPath = C:\Examples\file.txt

        File parent = new File("C:\\Examples");
        File resolvedFile = new File(parent , "dir\\file.txt");
        System.out.println(resolvedFile.toPath()); //C:\Examples\dir\file.txt

        resolvedFile = new File("C:\\Examples","dir\\file.txt");
        System.out.println(resolvedFile.toPath()); //C:\Examples\dir\file.txt

        Path parentPath = Paths.get("C:\\Examples");
        Path childRelativePath = Paths.get("dir\\file.txt");
        System.out.println(parentPath.resolve(childRelativePath));

        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("Working directory = "+workingDirectory.getAbsolutePath()); // C:\JMC17\Java-Masterclass-11

        System.out.println("-- print Dir2 contents using list() --");
        File dir2File = new File(workingDirectory,"FileTree\\Dir2");
        String[] dir2Contents = dir2File.list();

        for (int i = 0; i < dir2Contents.length; i++) {
            System.out.println("i = "+i+ ": " +dir2Contents[i]);
        }


        System.out.println("--- print Dir2 contents using listFiles() --");
        File[] dir2FilesArray = dir2File.listFiles();
        for (File dir2file : dir2FilesArray){
            System.out.println(dir2file.getName());
        }

    }
}
