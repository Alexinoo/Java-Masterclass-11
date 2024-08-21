package java_files_input_output._20_random_access_file_example;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a new RandomAccessFile with read-write mode ("rw")
            RandomAccessFile raf = new RandomAccessFile("RandomAccessFileExample.txt", "rw");

            // Write some data to the file
            raf.writeUTF("Hello, World!");
            raf.writeInt(12345);
            raf.writeDouble(3.14);

            long length = raf.length();
            System.out.println("File Length: " + length + " bytes");

            // Move the file pointer to the beginning of the file
            raf.seek(0);

            // Read and print the data from the file
            String str = raf.readUTF();
            int num = raf.readInt();
            double d = raf.readDouble();

            System.out.println("Read String: " + str);
            System.out.println("Read Integer: " + num);
            System.out.println("Read Double: " + d);

            // Move the file pointer to a specific position and overwrite data
            raf.seek(7);
            raf.writeUTF("Java");

            // Move back to the beginning to read the modified content
            raf.seek(0);

            // Read and print the modified data
            str = raf.readUTF();  // This will now read "Hello, Java!"
            num = raf.readInt();
            d = raf.readDouble();

            System.out.println("\nAfter modification:");
            System.out.println("Read String: " + str);
            System.out.println("Read Integer: " + num);
            System.out.println("Read Double: " + d);

            // Close the file
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
