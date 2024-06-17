package first_steps.part17_logical_AND_operator;

/*
 * Logical AND operator
 * ....................
 *
 * - Define integer variable "topScore" and initialize it to 100
 * - Test
 *      - Use equality(==) sign to check if topScore is equal to a 100
 *          - returns true
 *
 *      - Use Not Operator (!=) sign to check if topScore doesn't equal to 100
 *          - returns false
 *
 *      - Use greater than(>) sign to check if topScore is greater than 100
 *          - returns false
 *
 *      - Use greater than or equal to (>=) sign to check if topScore is greater than or equal to 100
 *          - returns true
 *
 *      - Use less than(<) sign to check if topScore is less than 100
 *          - returns false
 *
 *      - Use less than or equal to(<=) sign to check if topScore is less than or equal to 100
 *          - returns true
 *
 * - Update topScore to 80
 *      - Test the conditions again
 *
 * - Define another variable of type int "secondTopScore" and initialize it to 60
 * - Test if topScore is greater than secondTopScore and whether it is also less than 100
 *      - print "greater than second top score and less than 100
 *
 * - In this case, we're testing for 2 scenarios
 *      - Whether topScore > secondTopScore
 *      - Whether topScore < 100
 *
 * - The 2 ampersands (&&) are what's called Logical AND operator
 * - Tests to ensure that BOTH operands are TRUE
 *      - Tests to see that both operands to the left of the && and to the right, both of those have to be set to true
 *        to return the value of true
 *
 * - There's also a single (&) operator we can use which is called a BITWISE AND, which works in a similar way
 *
 *
 * - Update secondTopScore to 81
 *      - no output printed because the first condition is false, and in that case, it returns false in general
 */
public class Main {
    public static void main(String[] args) {

        int topScore = 100;
        if(topScore == 100 ){
            System.out.println("You got the high score!");
        }

        topScore = 80;

        int secondTopScore = 60;
        if(topScore > secondTopScore && topScore < 100){
            System.out.println("Greater than second top score and less than 100");
        }
    }
}
