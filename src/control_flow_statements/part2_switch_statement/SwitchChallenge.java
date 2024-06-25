package control_flow_statements.part2_switch_statement;

/*
 * The Switch Challenge
 * ....................
 * - Create a new switch statement using char instead of an int
 * - create a new char variable
 * - Create a switch statement testing for : A,B,C,D or E
 * - Display a message if any of these are found and then break
 * - Add a default which displays a message saying "Not Found"
 *
 *
 * Next,
 * - In Java version 7, they added the capability of using switch statements with strings
 * - Add a switch statement that tests for months .i.e. Jan, Feb, Mar and so on
 *
 * - However, the switch statement here is case-sensitive
 *  - for example:
 *      - "January" will not be the same as "january" - these are 2 different strings
 *      - so we really need to be precise here so that we don't need to end up doing something like below to cover all
 *        the scenarios, or every possible combination for every scenario
 *          case "january": case "jAnuary": case "jaNuary":
 * - Solution
 *      - we can use a method on Strings class: toLowerCase()
 *      - converts the entire string to lowercase
 *
 * - We can also work with the uppercase by calling toUpperCase()
 *     - will still the same
 *
 * - Used extensively in Java, and it's very common to use, and again very versatile
 */
public class SwitchChallenge {

    public static void main(String[] args) {

        //char myCharValue = '\u0044';

        char myCharValue = 'D';

        switch (myCharValue) {
            case 'A':
                System.out.println("Was an A");
                break;

            case 'B':
                System.out.println("Was a B");
                break;

            case 'C':
                System.out.println("Was a C");
                break;
            case 'D':
                System.out.println("Was a D");
                break;
            case 'E':
                System.out.println("Was an E");
                break;

            default:
                System.out.println("Could Not Found A, B, C, D or E");
                break;
        }

        String month = "JANUARY";
        switch (month.toLowerCase()){
            case "january":
                System.out.println("Jan");
                break;
            case "june":
                System.out.println("Jun");
                break;
            default:
                System.out.println("Not sure");
                break;
        }
    }
}
