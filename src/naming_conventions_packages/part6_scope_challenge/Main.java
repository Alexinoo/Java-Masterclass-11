package naming_conventions_packages.part6_scope_challenge;

import java.util.Scanner;

/*
 * Scope Challenge
 * ...............
 *
 * Write a small program to read an integer from the keyboard (using Scanner) and print out the times table for that number.

 * The table should run from 1 to 12.

 * You are allowed to use one variable called scanner for your Scanner instance.
 *
 * You can use as many other variables as you need, but they must must all be called x.
 * That includes any class instances and loop control variables that you may decide to use.

 * If you use a class, the class can be called X (capital), but any instances of it must be called x (lower case).

 * Any methods you create must also be called x.

 * Optional Extra:
 * Change your program so that ALL variables (including the scanner instance) are called x.
 *
 * NOTES:
 *  - We've used x everywhere and managed by using the appropriate scope to get the program to work without any problems, with X being considered as
 *    a duplicate
 */
public class Main {

    public static void main(String[] args) {

        X x  = new X(new Scanner(System.in));
        x.x();
    }
}
