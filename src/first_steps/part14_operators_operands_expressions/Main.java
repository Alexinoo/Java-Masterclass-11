package first_steps.part14_operators_operands_expressions;
/*
 * Operators, Operands and Expressions
 * ...................................
 *
 * What are Operators ?
 * ....................
 * - Operators in Java are special symbols that perform specific operations on 1 or 2 or 3 operands and then return a
 *   result
 * - As an example, we've used the + (addition) operator to sum the value of 2 variables
 * - There are mano other operators in Java
 *
 * What is an Operand ?
 * ....................
 * - An operand is a term used to describe any object that is manipulated by an operator
 * - Consider the statement below
 *      int myVar = 15 + 12;
 * - plus(+) is the operator, and 15 and 12 are the operands
 * - variables used instead of literals are also operands
 * - for example
 *      double mySalary = hoursWorked * hourlyRate;
 * - multiplication (*) is the operator, while hoursWorked and hourlyRate are the operands
 *
 *
 * What is an Expression ?
 * .......................
 * - An expression is formed by combining variables, literals , method return values and operators
 * - In the line below, 15 + 12 is the expression which has (or returns) 27 in this case
 *      int myValue = 15 + 12;
 * - In the statement below, hoursWorked * hourlyRate is the expression,
 *      - If hoursWorked was 10.00 and hourlyRate was 20.00, then the expression would return 200.00
 *      - e.g.
 *           double mySalary = hoursWorked * hourlyRate;
 *
 * Next
 * - Let's write more expressions
 *      int result = 1 + 2;
 * - Here, we've got 2 operators
 *      - equal(=) sign
 *      - plus(+) sign
 * - The equal(=) operator is an assignment operator and is used to assign a value
 *   - in this case, it's assigning what's on the RHS, of the equal operator to the variable we've called result
 *
 * Next,
 * Let's introduce another variable
 *      int previousResult = result;
 * - Then print it out
 *
 * Next,
 * - Let's change the value of the result variable
 *      result = result - 1;
 * - Then print it out
 *
 * - Notice how, previousResult and result are independent of each other
 * - Even though we assigned result to previousResult, previousResult doesn't get updated when result gets a new value
 * - And to confirm that, we printed the value of previousResult after the result variable was updated which still
 *    reads 3 as the value, and we never update the value of previousResult
 *
 *
 * Next,
 * - Let's try something else with another operator
 * - Print out the value of the result variable
 *      result = result * 10;
 *
 * Next,
 * - Lets try a division
 * - Print out the value of the result variable
 *      result = result / 5;
 *
 * Next
 * - Let's look at the remainder or modulus operator
 * - It returns/retains the remainder of two operands
 *      result = result % 3;
 *
 *
 */

public class Main {

    public static void main(String[] args) {

        int result = 1 + 2;
        System.out.println("1 + 2 = "+result);  // 1 + 2 = 3

        int previousResult = result;
        System.out.println("previousResult = "+previousResult); //3

        result = result - 1;
        System.out.println("3 - 1 = "+ result); // 3 -1 = 2
        System.out.println("previousResult = "+previousResult); //3

        result = result * 10;
        System.out.println("2 * 10 = "+ result); //20

        result = result / 5;
        System.out.println("20 / 5 = "+result); // 4

        result = result % 3;
        System.out.println("4 % 3 = "+result); // 1

    }
}
