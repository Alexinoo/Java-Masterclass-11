package control_flow_statements.part6_while_and_do_while_loop;
/*
 * EvenNumber challenge
 * .....................
 *
 * - add a while loop that only prints even numbers using isEvenNumber() between the number ranging from 4 to 20
 *      - start with 5
 *
 * - Create a method called isEvenNumber that takes a parameter of type int
 * - determine if the argument passed to it is an even number or not
 * - returns true, if the arg is an even number, otherwise, return false
 *
 * Hint:
 *  - use modulo operator to determine if there is a remainder
 *
 * Solution
 *  - initialize start and finishNumber to 4 and 20 respectively
 *  - at first, number 4 will be immediately incremented to 5 , and ultimately up-to 20
 *  - test to see whether the number in the current iteration is an even number
 *      - if it's not an even number, we'll just do a continue, which is different to break
 *
 * - Both break and continue keywords have an effect of interrupting the code
 *
 * continue
 *  - effectively bypasses the print statement, and in case there are more statements, all code after continue would
 *    be passed as well
 *  - in other words no processing would be done further down and the execution goes back to the start of the loop
 *  - so the loop get's finished and starts on the next number again
 *
 *  - in short, we are saying, if the number is an odd number, do nothing, and start the while loop again with the next
 *    number if the condition is still valid
 *      - in our case , if the value is 2o or less
 *
 * - in summary,
 *      - continue tells the program, stop where you are, continue to the next sort of test if you will, next condition,
 *        next iteration of the while loop
 *      - prints all even numbers since the if statement will not be executed,
 *          - if the if-statement evaluates to true, that means, the number is an odd number has been found and the
 *              print statement will be bypassed/skipped and the while loop condition checked again
 *
 *
 * - while loop is used more often than a do-while, but they've all got their use cases
 *
 *
 * #Challenge Example - 2
 *  - Modify the while code above
 *  - count the total number of even numbers found
 *      - break once the count is equal to 5
 *  - calculate and print the sum of the total even numbers
 *
 *
 */
public class EvenNumberChallenge {

    public static void main(String[] args) {

        // Example-1
        System.out.println("Example-1 : Challenge#1 - Print even numbers between > 4 and <= 20");
        int number = 4;
        int finishNumber = 20;
        while (number <= finishNumber){
            number++;
            if (!isEvenNumber(number)){
                continue;
            }
            System.out.println("Even number "+number);

        }

        // Example-2
        System.out.println("Example-2 : Challenge#2 - Sum the total no of the first 5 even numbers between > 4 and <= 20");
        number = 4;
        finishNumber = 20;
        int sum = 0, evenNumbersFound = 0;
        while (number <= finishNumber){
            number++;
            if (!isEvenNumber(number)){
                continue;
            }
            System.out.println("Even number = "+number);
            evenNumbersFound++;
            sum += number;
            if (evenNumbersFound == 5)
                break;
        }
        System.out.println("Sum of the even numbers = "+sum);

    }

    public static boolean isEvenNumber(int number){
        return  (number % 2) == 0;
    }
}
