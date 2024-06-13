package first_steps.part4_variables;
/*
 * Variables
 * .........
 * - Variables are a way to store information in our computer
 * - Variables that we define in a program can be accessed by a name we give them, and the computer does the hard work
 *   of figuring out where they get stored in the computers random access memory (RAM)
 * - A variable, as the name suggests can be changed, in other words, it's contents are variable
 *
 * - What we have to do is tell the computer, what type of information we want to store in the variable, and give it a
 *   name
 * - There are a lot of different types of data we can define for our variables.
 * - Collectively, these are know as Data types
 *
 * - As you may have guessed, data types are keywords in Java
 *
 * - Let's start by defining a variable of type int
 *  - int being an abbreviation for integer, a whole number ( a number without any decimal points)
 *
 * - To define a variable, we need to specify the data type, then give our variable a name, and optionally, add an
 *   expression to initialize the variable with a value
 *
 * - Let's do that now:
 *      int myFirstNumber = 5;
 * - We have defined our first variable
 *      - we have specified a data type of int, again an integer, or a whole number
 *      - we use equal sign (=) operator and the literal value 5 to set the variable to have the value of 5
 *      - terminated our statement with a semicolon
 *          - the semicolon ends the line and tells Java, that the statement is complete
 *
 * - So what we've typed is a statement or to be more specific a declaration statement
 *
 * Declaration Statement
 * .....................
 * - A declaration statement is used to define a variable by indicating the data type, and the name, and optionally to
 *   set the variable to a certain value
 *
 * - Here,
 *  - the type (Short for Data type) is an int
 *  - the name is myFirstNumber
 *  - the value we are assigning/initialize our variable with is 5
 *
 * In short, we're declaring a variable of type int with the name myFirstNumber and assigning the value of 5 to it
 *
 * N/B
 * ....
 * - The initialization is optional, in other words, we could have omitted the equals 5 (= 5)
 * - with Java in general though, variables have to be initialized before they are used (but more talk on that
 *    in upcoming videos)
 * - If you're initializing a number, what you type to the right of the equal sign is assigned as a value to the
 *    variable
 * - This is known as part after the equal sign as an expression in Java
 *
 * Expression
 * ..........
 * - This is a construct that evaluates to a single value
 * - we will discuss this in much more detail in the upcoming section on expressions
 *
 * - Java has or will when we compile and run the program, read this statement we've created and allocate a place in
 *  memory to store a single whole number
 * - It will then assign myFirstNumber to that area of memory, or more specifically assign that number we've typed
 *   to that area of memory
 *
 * - In other words, we don't need to know anything about where in memory this is taking place, or where Java is
 *    storing the contents , to get access to it when you don't refer to a memory location, you refer to a variable
 *    name, myFirstNumber in this case
 * - We're leaving Java to do all the dirty work there
 *
 * - Alright, now that we've declared the variable, let's see now if we can print out the value
 *
 * Challenge
 * .........
 * - Looking at the first System.out.println statement, what do you think should be typed on the line below our
 *   declaration statement to print out the value of the variable myFirstNumber
 *
 * - When you first try this, you might have tried putting double quotes in the parenthesis similar to what we did
 *   while printing "Hello World", and typed "myFirstNumber"
 *      System.out.println("myFirstNumber");
 * - But then again , when you run the program thinking that you'll get the value, in our myFirstNumber variable, but when
 *   you run it , you saw in fact we got back the output, "myFirstNumber" and not the contents of our variable that we declared
 *   ,which of course the value should be 5
 * - The reason why we've got "myFirstNumber" printed in the console and not the value of our variable is because we put
 *   the text in double quotes, "myFirstNumber"
 * - When we put something in double quotes, this is called a String literal
 *
 * String Literal
 * ..............
 * - Any sequence of characters surrounded by double quotes is a String literal in Java
 *      - It's value can't be changed, unlike a variable
 * - Now a literal, unlike a variable can't be changed, it's an expression and not a variable
 * - So in actual sense, no pun intended, we're saying literally, print text "myFirstNumber", and unsurprisingly after
 *  that we can see the text "myFirstNumber" showing
 * - So in essence that's why we're getting that string value
 *
 * - If we actually remove the double quotes,both from the start and at the end and run this again
 *      - This time the value 5 is printed on the console
 * - The variable we're printing must be identical to the variable declared
 *
 * - Just to confirm, if we change the value to a 10, and re-run again,
 *   - we now got the output as number 10
 *
 * - The expression to the right of the equal sign, can be a lot more complex
 * - At the moment, we've just used a literal integer value, number 5, then later changed it to number 10
 * - But we can be a little bit more complex there
 *
 * - Let's add an expression that is the sum of 2 numbers e.g. 10 + 5
 *      - And we now got 15 output on the screen
 * - Java has looked up to the left expression of the equal sign, and figured that, that is a mathematical expression
 *   and it basically calculated that to be 15
 *
 * - We can also get a little more complex by doing the following (10 + 5) + (2 * 10)
 *   - And surely enough we get the value 35 printed which is the correct answer
 *
 * Java Operators
 * ..............
 * - Java Operators or just operators perform an operation (hence the word) on a variable.
 * - +, - , * and / are 4 common ones that we're quite familiar with
 * - Though, there're lot more operators that we'll learn as we progress
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Alex");

       // int myFirstNumber = 5;
      //  int myFirstNumber = 10;
      //  int myFirstNumber = 10 + 5;
        int myFirstNumber = (10 + 5) + (2 * 10);
        System.out.println(myFirstNumber);
    }
}
