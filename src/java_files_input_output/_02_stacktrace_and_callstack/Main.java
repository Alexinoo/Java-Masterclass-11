package java_files_input_output._02_stacktrace_and_callstack;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Stack Trace and Call Stack
 *
 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }

    private static int divide() {
        int x = getInt();
        int y = getInt();
        System.out.println("x is "+ x + " y is "+y);
        return x/y;
    }

    private static int getInt() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter an integer");
        return scanner.nextInt();
    }
}
