package java_files_input_output._17_readbinarydata_eof_exceptions;

/*
 * Working with Binary Data
 *
 * - Let's deep dive into what writeInt() , look further into the shifting operations and understand what's going on at the low level
 *
 * - Create ShiftInt class
 *  - Use the code from the resource section and paste it into ShiftInt class na dit really has got nothing to do with the IO section
 *  - We're just using it to show the 4 values that the writeInt() produces when it saves an int value to the Binary stream
 *
 *
 * ////////
 * - We've created our own writeInt() and it's based on the writeInt()
 * - We've used display(int x) to do some formatting so that the output's easy to read
 * - We've also used a technique to show the right most 8 bits in a different color to make it easy to compare the added values with the values
 *   produced from
 *
 * /////// Running the program now
 * - Works but you can also test with different colors
 * - The original value is written to the file as the
 *      4 bytes - 54
 *              - 249
 *              - 214
 *              - 47
 * - The output shows how the values were obtained by shifting the binary number right then masking off all but the right most 8 bits
 * - This is done by the following code :  x & 0xFF
 *
 * - The DataOutputStream class does take care of the writing java primitive types, and DataInputStream class reads them back again
 *
 */

public class ShiftInt {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        int x = 922342959;

        writeInt(x);
    }


    private static void display(int x) {
        String all = String.format("%32s", Integer.toBinaryString(x)).replace(" ", "0");
        String colouredBinary = all.substring(0, 24) + ANSI_PURPLE + all.substring(24) + ANSI_RESET;
        int y = x & 0xFF;
        String output = String.format("%10d and 0xFF is %8s \t %10d is ",  y, Integer.toBinaryString(y), x) + colouredBinary;
        System.out.println(output);
    }

    private static void writeInt(int v) {
        int x;
        display(v >>> 24);
        display(v >>> 16);
        display(v >>> 8);
        display(v >>> 0);
    }
}
