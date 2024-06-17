package first_steps.part20_ternary_operator;
/*
 * Ternary Operator
 * ................
 * - Define
 *      boolean wasCar = isCar ? true : false;
 *
 * - The ternary operator has takes 3 operands
 * - The 1st operand
 *      - is the condition we are testing that evaluates to either true or false
 *      - can be more complex than what we have
 *
 * - The 2nd operand
 *      - is the value to assign to "wasCar", if the condition is true
 *
 * - The 3rd operand
 *      - is the value to assign to "wasCar", if the condition is false
 *
 * - Test if "wasCar" is true and print a message
 *      - no output since "wasCar" is falsy
 *
 * - update isCar to true before the line below
 *       boolean wasCar = isCar ? true : false;
 *
 * - And this time the statement "wasCar is true" is printed
 *
 * Ternary Operator (? :)
 * ......................
 * - The ternary operator is a shortcut to assigning 1 of 2 values to a variable depending on a given condition
 * - It's a shortcut fo "if-then-else" statement
 *
 * - Consider an example below:
 *      int ageOfClient = 20;
 *      boolean isEighteenOrOver = ageOfClient == 20 ? true:false;
 *
 * - Operand 1 - (ageOfClient == 20)
 *      - represents the condition that we are checking for which needs to evaluate to true/false
 *
 * - Operand 2 - true
 *      - represent the value to assign to the variable "isEighteenOrOver", if the condition above is true
 *
 * - Operand 3 - false
 *      - represent the value to assign to the variable "isEighteenOrOver", if the condition above is false
 *
 * - In this case, "isEighteenOrOver" is assigned the value true because ageOfClient has the value of 20
 *
 * - It can be a good idea to use parentheses like below to make the code even more readable
 *      boolean isEighteenOrOver = (ageOfClient == 20) ? true:false;
 *
 */
public class Main {

    public static void main(String[] args) {
        boolean isCar = false;
        if (isCar){
            System.out.println("This is not supposed to happen");
        }

        isCar = true;
        boolean wasCar = isCar ? true : false;
        if (wasCar){
            System.out.println("wasCar is true");
        }
    }
}
