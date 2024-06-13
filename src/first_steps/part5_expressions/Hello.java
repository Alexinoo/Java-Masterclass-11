package first_steps.part5_expressions;

/*
 * Starting out with Expressions
 * .............................
 * - Let's look at Expressions and Variables in greater detail
 * - We need to declare 2 more variables as a challenge for us
 *
 * Challenge
 * .........
 * - Create 2 additional variables
 *      - Declare the following variables and add to our program
 * - mySecondNumber which is an int and assign a value of 12 to it
 * - myThirdNumber, also an int and assign a value of 6
 *
 * - Put the declaration statements above the System.out.println()
 *
 * - Is there a way we can sum the value of mySecondNumber and myThirdNumber into another variable ?
 *   - Yes, we can do that
 * - Instead of using a literal number, which we've already seen so far in the course for assigning a value to our
 *   variables, let's use a variable name directly in the expression
 *      - Declare myTotal variable and assign that to an expression of adding myFirstNumber ,mySecondNumber and
 *        myThirdNumber
 *
 * Next,
 * Let's print myTotal
 *
 * Change myThirdNumber from 6 to myFirstNumber * 2
 *
 *
 * Final variable challenge
 * ........................
 * - Create a new variable called myLastOne
 * - We want the value to be 1000 less the current value of myTotal - the data type is an int
 * - Print out the value of myLastOne
 *
 * Hint:
 * - Use another operator that we haven't used before, but should be easy to figure that out
 */
public class Hello {

    public static void main(String[] args) {
        System.out.println("Hello, Alex");
        int myFirstNumber = (10 + 5) + (2 * 10); //35
        int mySecondNumber = 12;
        //int myThirdNumber = 6;
        int myThirdNumber = myFirstNumber * 2; // 70
        int myTotal = myFirstNumber + mySecondNumber + myThirdNumber;

        System.out.println(myFirstNumber);
        System.out.println(myTotal); // 117

        int myLastOne = 1000 - myTotal;
        System.out.println(myLastOne); // 883
    }
}
