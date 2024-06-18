package java_tutorial.part1_keyword_and_expressions;
/*
 * Keywords and Expressions
 * ........................
 *
 * Keywords
 * ..........
 *
 * - Java has 50 reserved words that are used as keywords in Java applications
 * - IntelliJ will always highlights them in blue to show you they're actually keyword
 *
 * - We can't do thi for example
 *      int int = 5; // int is a keyword and can't be used as a variable name
 *      int int2 = 5; Ok
 *
 * - Other keywords that you can't also use are :  false, true, null etc
 *
 * Expressions
 * ...........
 * - Expressions are essentially building blocks of all Java programs
 * - They're built with values, operators and operands, and also with method calls
 *
 * - Suppose we wanted to convert miles into kilometers
 * - If we wanted to convert 100 miles, we would do something like this
 *      double kilometres = (100 * 1.609344);
 * - The actual expression here is
 *      kilometres = (100 * 1.609344);
 * - The data type doesn't form part of an expression
 * - The expression component is variables, operators and values
 *
 * - By adding a data type, we're creating a valid Java statement, plus the semicolon at the end of the line
 *
 * - more examples of an expression
 *      int highScore = 50;
 * - highScore = 50 is a also valid expression
 *
 * - for the if block below, within the brackets
 *      highScore == 50
 * - above is the expression
 * - The if and the () do not form part of the expression
 *
 * - And finally, another valid expression
 *      System.out.println("This is an expression");
 * - The component between the brackets below is an expression
 *      "This is an expression"
 *
 *
 * N/B
 * ...
 * - Semicolon are not included as part of the expressions
 * - Only used to tell Java that we've reached the end of the statement
 *
 * Challenge
 * ........
 * - Write down parts of the code that are expressions in the code below
 *      int score = 100;
 *      if(score > 99){
 *          System.out.println("You got the high score!");
 *          score = 0;
 *      }
 * - My solution
 *      score = 100            //data type and semicolon at the end do not form part of the expression
 *      score > 99
 *      "You got the high score"
 *      score = 0
 *
 */
public class Main {

    public static void main(String[] args) {
        // a mile is equal to 1.609344 kilometres
        double kilometres = (100 * 1.609344);

        int highScore = 50;
        if (highScore == 50){
            System.out.println("This is an expression");
        }

    }
}
