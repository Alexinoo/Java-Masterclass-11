package first_steps.part18_logical_OR_operator;

/*
 * Logical OR operator
 * ...................
 *
 * - Logical OR operator is represented by 2 pipes (||)
 * - Using 1 pipe (|) is actually a BITWISE OR operator and it will return different results
 *
 * - Define 2 integer  variables
 *      - topScore and initialize to 80;
 *      - secondTopScore and initialize to 81;
 *
 * - Test using (||) to see if
 * - use extra parentheses to clarify code meaning/readability
 *      - topScore is greater than 90,
 *      - secondTopScore is less than or equal to 90
 *
 * - The Logical OR operator works in a similar way as Logical AND operator, but it only requires 1 of the conditions
 *   to the left and right hand side to be true
 *
 * - The Logical AND, requires BOTH, the right and the left hand side operands conditions to be true
 *
 * Logical AND & Logical OR
 * ..........................
 * - The AND operator comes in 2 flavors in Java, as does the OR operator
 * - && is the Logical AND which operates on boolean operands
 *      - checks if a given condition is true/false
 * - & is a bitwise operator, working at the bit level ( more advanced concept)
 *
 * - || is the Logical OR which operates on boolean operands
 *      - checks if a given condition is true/false
 * - | is a bitwise operator, working at the bit level ( more advanced concept)
 *
 * - In this case
 *  - The LHS is false but the RHS condition evaluates to true
 *  - This returns true and the println statement is executed
 *
 * - Update secondTopScore to 95
 *  - Both conditions are now false, and false is returned
 *  - No output in this case, since the condition evaluates to false
 *
 */
public class Main {

    public static void main(String[] args) {

        int topScore = 80;
        int secondTopScore = 81;

        if( (topScore > 90) || (secondTopScore <= 90) ){
            System.out.println("Either or both of the conditions are true");
        }

    }
}
