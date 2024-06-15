package first_steps.part15_abbreviating_operators;

/*
 * Abbreviating Operators
 * ......................
 *
 * - Let's find out how to use an abbreviated form for common operators
 *
 * - We can write
 *      result = result + 1;
 * - As
 *      result++;
 *
 * - Therefore, result++; is an abbreviation of result = result + 1;
 * - We can do the same for the other major operators as well
 *
 * - For example
 *      result--; // 2-1=1
 *
 * Next,
 * - We can even reduce assigning a value by a different value
 * - If we want to add 2 for example to result, we w'd normally have to do something like this
 *      result = result + 2;
 * - We can abbreviate that by typing
 *      result += 2 ;
 *
 * Next,
 * - We can do the same for multiplication
 *      result = result * 10;
 * - We can abbreviate as
 *      result *= 10;
 *
 * Next,
 * - We can also do the same for division
 *      result = result / 3;
 * - Can be abbreviated as
 *      result /= 3;
 *
 * Next,
 * - Let's do a subtraction
 *      result = result - 2;
 * - Can be abbreviated as
 *      result -= 2;
 *
 */

public class Main {

    public static void main(String[] args) {
        int result = 4 % 3 ; // 1

        result++;
        System.out.println("1 + 1 = "+result); // 1 + 1 = 2

        result--;
        System.out.println("2 - 1 = "+result); //1

        result += 2;
        System.out.println("1 + 2 = "+result); //3

        result *= 10;
        System.out.println("3 * 10 = "+result); // 30

        result /= 3;
        System.out.println("30 / 3 = "+result); // 10

        result -= 2;
        System.out.println("10 - 2 = "+result); // 8

    }
}
