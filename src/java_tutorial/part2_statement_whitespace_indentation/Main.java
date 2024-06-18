package java_tutorial.part2_statement_whitespace_indentation;

/*
 * Statements, Whitespaces and Indentation (Code Organization)
 * ...........................................................
 *
 * Statements
 * ..........
 *
 * - Define an integer variable myVariable and initialize it to 50
 *      int myVariable = 50;
 *
 * - In the above statement "myVariable = 50" is the expression
 * - while "int myVariable = 50;" the entire line is a statement, - the complete line basically
 *
 * - Another Java statements would be
 *      myVariable++;
 *      myVariable--;
 * - Method calls can also be termed as Java statements
 *      System.out.println("This is a test");
 *
 * - Semicolon is needed to complete a Java line, to make it a statement, but there are exceptions to that but we'll
 *   cover that much later
 *
 * - Statements also don't need to be on the same line
 * - We can do something like below
 *       System.out.println("This is" +
                " another" +
                " still more");
 *
 * - Java is smart enough and will collectively adds that altogether and effectively creates a line, just as what
 *   we've done
 *      System.out.println("This is" +" another" + " still more");
 * - So as far as Java is concerned, the above line is exactly the same with the one on line 23 (which is also valid as well)
 *
 * - So, as long as you're not putting the semicolon, this is all valid to Java
 *
 * - We can also have multiple statements in 1 line as below
 *      int myVariable = 50; myVariable++;
 * - Though it's something, Tim would not recommend us to do, as it takes a little bit more effort to figure out
 *   whether that's all part and parcel on the same line
 * - But the bottom line is you can put many thins on the same line, if you wanted to as below
 *       int anotherVariable=50; myVariable++;System.out.println("This is another one");
 *
 * Whitespace
 * ..........
 * - The white space is the space between some of your expressions, operators and so forth
 *
 * - For example, the code below, we have added a space between int and variable name, to make sure that java is
 *   aware, that they're both separate things
 *      e.g. int myVariable = 50;
 *           intmyVariable = 50; // Will result to an error
 *
 * - Whether, you have 1 space or 10 spaces, Java won't mind
 *      e.g. int                 myVariable
 *                  =
 *                  50
 *                  ;
 * - Though, generally, this is not how we'd write our code, but it is still valid with Java
 * - Java will look at the spaces and delete the spaces internally, and as far as it's concerned, it's going to be
 *   exactly the same as
 *      int myVariable = 50;
 *
 * - It's a good idea to use spaces, to clarify the meaning of particular things
 * - Another common places to put the spaces is in-between variable names and operators
 * - And likewise again in between the equal sign and the literal value of 50 as well
 *
 * - for example
 *          int anotherVariable = 50;
 * - rather than below
 *          int anotherVariable=50;
 *
 * - So, in general, with white space, you can do whatever you like there and Java will ignore the white space and work
 *   quite happily
 *
 * Indentation
 * ...........
 * - Indentation is indenting your code so that it's more readable
 * - It makes your code more readable
 *
 * - We normally indent the code from the method definition, but also, IntelliJ normally indent most of
 *   the lines automatically for us when writing code blocks such as if() etc
 *
 * - The concept of indenting is to make it easy for you to see the logical flow of the code
 * - It's important as it make sure that things are as they should be
 *
 * - If we do mess up anyway, we can goto  "Code > Reformat Code" or press "Ctrl + Alt + L" and by doing that, the
 *   IntelliJ automatically reformats your code and it's re-indented
 *
 * - So, when in doubt, go to the Code menu and Select Reformat Code, or you can use the appropriate shortcut
 *  for that matter
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        int myVariable = 50;
        myVariable++;
        myVariable--;
        System.out.println("This is a test");

        System.out.println("This is" +
                " another" +
                " still more");
        int anotherVariable = 50;
        myVariable++;
        System.out.println("This is another one");


    }
}
