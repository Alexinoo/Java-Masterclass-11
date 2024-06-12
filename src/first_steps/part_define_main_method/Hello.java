package first_steps.part_define_main_method;

/*
 * Defining the Main Method
 * ........................
 *
 * - What is a method ?
 *  - It's a collection of statements (one or more) that performs an operation
 *  - We'll be using a special () call the main() that Java looks for when running a program
 *  - It's the entry point of any Java code
 *
 * - You can also create your own main ()s as we'll see later in the course
 *
 * public static void main(String[] args) {

    }
 *
 * public
 *  - is an access modifier we discussed when defining the class in the last video - same principle here
 *
 * static
 *  - is a java keyword here that needs an understanding of other concepts
 *  - for now, know that we need to have static for java to find our method to run the code
 *
 * void
 *  - is a keyword used to indicate that the method won't return anything
 *
 * ()
 *  - The left and right parenthesis in a method are mandatory and can optionally include 1 or more parameters
 *  - This is a way to pass information to a method
 *  - Here, 1 parameter has been defined and will discuss what that is a little bit later
 *
 * {}
 *  - represents a code block
 *  - A code block is used to define a block of code
 *  - It's mandatory to have one in a method declaration and this is where we'll be adding statements to perform
 *    certain tasks
 *
 * - For now though, to enable Java to define the main method it has to be typed exactly like it's typed here
 *
 *
 * - There is a specific order to learning Java, and it pays to learn things in the right order so that things don't
 *   get too overwhelming
 *
 * Recap
 * .....
 * - A class has got a body block of code which is the left and the right curly brace of the entire class
 * - And a method is effectively within that class and it's got itw own code block which is a group of code statements
 *  if you will that we'll be defining and that are unique to that specific method
 *
 * Next
 * - Print out a message
 *      System.out.println("Hello world");
 *
 * Statement
 *  - The above is a complete command to be executed and can include 1 or more expressions
 *
 * For now, we have typed in a command to basically call a method that comes as part of Java called println() and we
 *  sent out the information, in this case the text "Hello World"
 *
 *
 *
 */
public class Hello {

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
