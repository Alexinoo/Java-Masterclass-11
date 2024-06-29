package control_flow_statements.part8_parse_values_from_string;
/*
 * Parsing values from a String
 * ............................
 * - means converting a String to some other data type such as a number string into an int
 * - 1 use case where we might need to convert a number string is when reading input from the user, perhaps after it's
 *   been entered into a console , or after being retrieved from a UI
 * - we'll look at this in more detail when we cover how we can read values from the user using a scanner class
 *
 * - let's discuss, how we can convert a string value into some other primitive data type
 *
 * Example:
 *  - Define a number as a String or text
 *  - convert it to a number
 *
 * - 1 way is to use a parsing method
 * - with these ()s, we can convert a string into various primitive types depending on the specific parse we chose
 *   to use
 *
 * Integer.parseInt(String s)
 *  - we use an Integer class which is a wrapper class for the primitive type int
 *  - the wrapper class integer contains some useful static methods parseInt()
 *
 * parseInt(String s)
 *  - tries to convert the string we're passing as an argument into an integer
 *  - if successful, the number is converted from a string to an integer, but if the conversion fails, we'll get an
 *    error
 *  - Notice that we used Integer.parseInt() to convert type into an integer, in order to convert type string into some
 *    other data type, in other words parse the string, we need to include the type we're converting to so that we're
 *    able to get the parsing method associated with that type
 *
 * - parsing is useful, when we need to convert a string into a number, in order to use that number to calculate
 *  something
 *
 * Example
 *  - Add a random number to both variables
 *
 *      numberAsString += 1;
 *       number += 1;
 *
 * - when we add number to a string, java will automatically convert the number 1 into a string and then concatenate
 *   the two strings
 *      e.g.
 *      numberAsString will have the value 20181
 * - however, adding a number to an integer will increment it by 1, because it's an int value and not a string
 *      e.g.
 *      number will have a value of 2019
 *
 * - Next
 * - we'll get an error if we try to convert a non-numeric string to a number or a combination of both
 *      - e.g. "2018a"
 *      - we'll get a NumberFormatException exception if we try to convert the string "2018a"
 *      - the problem here is our string since, it's value "2018a" cannot be converted to an int because that's not a
 *        valid number and so java throws us a NumberFormatException to indicate that the format isn't correct
 *
 * - we can handle this problem by handling the actual exception which we'll loop at later in the course
 * - we can create a method of our own as alternative, but that too will come up later in the course
 *
 * - For now, let's keep in mind that we cannot just convert anything to a number an therefore, below won't work
 *      String numberAsString = "2018a";
 *
 *
 * - We can also convert to other primitive types such as double , float e.t.c by calling the relevant wrapper classes
 *    - if we wanted to convert "20181" to a double , we use Double.parseDouble(String s) to achieve that
 *    - if we wanted to convert "20181" to a float , we use Float.parseFloat(String s) and so on
 *
 * - Update numberAsString to
 *     - "2018a"
 *     - "2018.125"
 * - re-run and check the output
 *      - won't work with Integer.parseInt()
 *
 */
public class Main {
    public static void main(String[] args) {

        //String numberAsString = "2018";
       // String numberAsString = "2018a";
        String numberAsString = "2018.125"; // DOESN'T WORK WITH parseInt()

        System.out.println("numberAsString = "+numberAsString);

        int number = Integer.parseInt(numberAsString);
        System.out.println("number = "+number);

        numberAsString += 1;
        number += 1;

        System.out.println("numberAsString = "+numberAsString);
        System.out.println("number = "+number);

        double numberAsDouble = Double.parseDouble(numberAsString);
        System.out.println("numberAsDouble = "+numberAsDouble);

    }
}
