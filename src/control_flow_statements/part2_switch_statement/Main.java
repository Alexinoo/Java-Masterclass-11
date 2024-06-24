package control_flow_statements.part2_switch_statement;

/*
 * The Switch statement
 * ....................
 *
 * - Compare switch with if-statement
 *      - write a couple of if statements that determine what the value is
 *      - initialize an integer variable with number 1
 *          - use the if statement and check the value
 *          - print matching statements
 *      - update the variable and test the conditions
 *
 * - However, this can get a little bit messy, if you've got 10 values to test or like a large number of values that
 *   you want to test and perform different code based on that value
 *
 * - There's an alternative and that is using a switch statement
 *
 * Switch statement
 *  - comprises the keyword switch
 *  - takes in the value that we want to test
 *  - then start a code block
 *      - we then type the word case followed by the literal value and a colon
 *          - literal value in this case can be of any primitive types (int , float, double, String,char)
 *          - the add the statements to be executed in case the case value matches the value that we're looking for
 *      - terminate with break keyword
 *          - exits from the switch statement
 *      - default keyword
 *          - covers any other case that's not been covered
 *
 *      - we can use multiple case statements
 *          - case 1:
 *              // statements;
 *              // break;
 *
 *          - case 2:
 *              // statements;
 *              // break;
 *
 *          - case 3:
 *              // statements;
 *              // break;
 *
 *          - default
 *              // statements
 *              // [optional] break
 *
 * - Example
 *      - Define an integer value with number 1 - "switchValue"
 *      - Add a switch statement and tests for the following cases:  case 1:, case 2: ,...
 *      - print the value if the case matches
 *      - update "switchValue" to 2 or 3 and test again
 *
 * - Both if-statement and switch statement achieve the same thing, however, the if-statement is a little bit more
 *   flexible since we can test for 1 or more conditions in the brackets
 * - However, for switch statement, we're only testing for the switch value - only 1 variable and can't introduce any
 *    other variable
 *
 * - Switch is useful in case you're testing the same variable and you want to test different values for that variable
 * - Can also get a little bit tedious in cases where you're testing so many values, and there is a little bit of a
 *   shortcut by grouping the cases
 *      - e.g. case 3: case 4: case 5:
 */
public class Main {
    public static void main(String[] args) {

        int value = 1;
        // value = 2;
        // value = 3;
        if (value == 1) {
            System.out.println("Value was 1");
        } else if (value == 2) {
            System.out.println("Value was 2");
        } else {
            System.out.println("Was not 1 or 2");
        }

        // switch statement
        int switchValue = 2;
        switch (switchValue) {
            case 1:
                System.out.println("Value was 1");
                break;
            case 2:
                System.out.println("Value was 2");
                break;
            case 3: case 4: case 5:
                System.out.println("Was a 3 or 4 or 5");
                break;
            default:
                System.out.println("Was not 1 or 2");
        }

    }
}
