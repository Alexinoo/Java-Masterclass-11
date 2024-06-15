package first_steps.part16_if_then_statement;

/*
 * if-then Statement
 * .................
 * - The if-then statement is the most basic of all the control flow statements
 * - It tells your program to execute a certain section of code ONLY if a particular test (which is an expression)
 *    evaluates to true
 * - This is known as Conditional Logic
 *
 * Conditional Logic
 * .................
 * - Conditional logic uses specific statements in Java to allow us to check a condition and execute certain code based
 *  on whether that condition (expression) is true or false
 *
 * - Let's see how this works in practice
 *  - Define boolean variable
 *      boolean isAlien = false;
 *
 * - Notice that
 * - First
 *      - the statement below does not have a semicolon , that you would normally expect on each line
 *          if (isAlien == false)
 *      - the semicolon doesn't get added until the following println() statement
 *          System.out.println("It is not an alien!");
 *
 * - So, we don't put a semicolon after the parentheses
 * - What's happening here, is that the statement is actually spanning 2 lines, which is perfectly valid to do in
 *   Java, and we'll talk more about white space, which is the concept of using multiple lines and any no of spaces
 *
 * - But check out the operators we've used in the above lines of code
 * - We have a single equals (=) and on the second line we've equality (==)
 *
 * - The first equal (=) sign is an assignment operator
 *      - assigns the value of an expression to the variable on the left of the equals operator
 *      - "isAlien" is the variable in this case and is set to false
 *
 * - The second equal (==) sign is an equality operator
 *      - tests to see whether a particular value is false
 *      - in other words, we're saying if the value of the "isAlien" is false
 *      - tests/compares if the operands are identical or equal to each other
 *      - the LH operand is "isAlien" and the RH operand is "false"
 *
 * - Are they the same ?
 *      - is "isAlien" have the value false and in this case, it does because we know that "isAlien" is defined as
 *        false
 *      - In other words is (false == false)
 *
 * - Therefore the expression (isAlien == false) is true
 * - This can be confusing to grasp, but in other words, we're checking if 2 operands are equal to each other and if
 *   that is the case, a true is returned, the expression returns the value true, otherwise returns false to say that
 *   they're not equal
 *
 * - So the if keyword, or if(isAlien == false) , takes what's in the parentheses and if and only if the result of that
 *   expression is true, will the next line be executed
 *
 * - A better way to read this is, do the contents of what is in the parentheses evaluate to true ?
 * - And sure enough, it is true and so the line below is printed
 *      System.out.println("It is not an alien!");
 *
 * - Also notice that if we hover around the expression, the IntelliJ is telling us that we can further simplify or
 *   abbreviate (isAlien == false) to (!isAlien) but more to that later in the course
 *
 * - But what we're doing is still valid as well
 *
 * Next,
 * - What happens if we add a semicolon on the if- statement line below after the right parentheses
 *      if(isAlien == false);
 *
 * - Notice, if we do that, we've got a little potential warning that  the if block is empty, but there are no erros
 * - And if we run this:
 *      - we still got the same output, and at first glance, you would assume that's nothing has changed and the program
 *        is still working as it should
 *  - However, if we change this, (isAlien == false) to (isAlien == true) and run it again
 *      - we get the same output printed
 *
 * - This is because, we have detached, the if block by adding a semicolon and Java is treating the 2 statement
 *   as independent of each other
 *
 * - In other words, our println() statement is no longer using conditional logic, and is just going to be executed no
 *   matter what
 *
 * - So basically we have created an if-then statement, which doesn't do anything and that's what's confusing about
 *   adding the semicolon - so don't put it there
 *
 * Next,
 *  - remove the semicolon and re-run again
 *  - this time, the println statement is not executed since we know that, that's not the case as the expression
 *    now returns false
 *
 * - Revert back to false again
 * - and now this time the println statement is executed because "isAlien" value is set to false
 *
 * Next
 * - The instructor wanted us to see the if-statement without using a code block, but you can see that we can run into
 *   problems as it's not clear what's going on which is a disadvantage of using an if-statement without a code block
 *
 * - If we add another println statement, "And I am scared of Aliens", this will be executed regardless of whether
 *   our expression returns true or false
 *
 * - The reason is because we haven't used a code block in our if-then statement
 * - If we don't use a code block, then only line of code can be added to this if-then statement
 *
 * - So
 * - What we can do, or should do, a 100% of the time is actually add a code block
 *
 * if-then Rule - Always use a code block
 *
 * - A code block allows more than 1 statement to be executed - a block of code
 * - e.g.
 *      if (expression) {
 *          // put 1 or more statements here
 *      }
 * - add statements that we want to be executed if the expression is true in between {}
 *
 * Next,
 * - Run it if the condition is false
 *      - Technically, we shouldn't get any output at all
 *
 * Next
 *  - Run if the condition is true
 *      - Both statements of output should be printed
 *
 * - So using code blocks makes it easy to understand, but also enables us to have more than one statement execute
 *
 * - In general, always use an if-then statement with a code block, even if you're executing only 1 line, because , if
 *   you come back to this code in the future, and decide to add another line, you may not realise that you've introduced
 *   a bug
 *
 * - So bottom line, always use a code block
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        boolean isAlien = false;

        if (isAlien == false) {
            System.out.println("It is not an alien!");
            System.out.println("And I am scared of aliens");
        }
    }
}
