package java_files_input_output._09_read_with_scanner;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            // Create a FileReader object representing the file to read
            FileReader fileReader = new FileReader("example.txt");

            // Create a Scanner object using the FileReader
           scanner =  new Scanner(fileReader);

            // Read the file line by line
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }finally {
            // Close the scanner
            System.out.println("Closing the scanner...");
            if (scanner != null)
                scanner.close();
        }
    }
}

