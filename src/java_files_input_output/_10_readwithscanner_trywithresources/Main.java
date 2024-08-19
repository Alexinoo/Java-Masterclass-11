package java_files_input_output._10_readwithscanner_trywithresources;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try(FileReader fileReader = new FileReader("example.txt")) {

            // Create a Scanner object using the FileReader
            Scanner scanner =  new Scanner(fileReader);

            // Read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

        }
    }
}

