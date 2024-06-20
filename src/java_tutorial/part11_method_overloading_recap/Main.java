package java_tutorial.part11_method_overloading_recap;
/*
 * Method Overloading Recap
 * ........................
 *
 * - Method overloading is a feature that allows us to have more than 1 method with the same name, so long as we use different parameters
 *
 * - It's the ability to create multiple methods of the same name with different implementations
 *
 * - calls to an overloaded method will run a specific implementation of that method
 *
 * - In the previous example from the previous video, we had the following overloaded versions of calculateScore method
 *
 *      calculateScore(String playerName, int score); // 2 parameters
 *      calculateScore(int score);  // 1 parameter
 *      calculateScore();  // 0 parameters
 *
 * - As an example, let's say you have to create a method which can do
 *      - The sum of 2 numbers
 *      - The sum of 3 numbers
 *      - The sum of 4 numbers
 *
 * - Each method would have parameters passed to it with the numbers to sum
 *
 * - Ending up with the following method(s) is not a good practice & it is not actually method overloading
 *
 * - Never ever write code like below, it is bad practice
 *
 *      public static int sumTwoNumbers(int a , int b){
 *          return a + b;
 *      }
 *
 *      public static int sumThreeNumbers(int a , int b, int c){
 *          return a + b + c;
 *      }
 *
 *      public static int sumFourNumbers(int a, int b, int c, int d){
 *          return a + b + c + d;
 *      }
 *
 *
 * - The problem is we have 3 different names to remember that do the sum
 * - Now, imagine that you wanted to have a method that adds up to 20 numbers , you'll effectively end up having to write 20 different methods with
 *   20 different names
 * - Remembering 20 names would be quite hard
 *
 * - To overcome this problem, we can use the same name for the method
 *
 *      public static int sum(int a , int b){
 *          return a + b;
 *      }
 *
 *      public static int sum(int a , int b, int c){
 *          return a + b + c;
 *      }
 *
 *      public static int sum(int a, int b, int c, int d){
 *          return a + b + c + d;
 *      }
 *
 * - We'll still end up with 3 methods, but they'll be all sharing the same name, but it's much easy to remember
 *
 * - Method overloading is a common thing, it's used everywhere and in many other languages as well
 *
 * Example:
 *  - The println() is a great example of method overloading in the Java language
 *  - There are actually 10 methods with the name println
 *  - We can print
 *      - an integer,
 *      - a double,
 *      - a string
 *      - an array
 *      - a float
 *      - a character
 *  - and so on
 *
 *
 * Final Recap
 * ...........
 *
 * - Improves code readability and re-usability
 * - It's easier to remember 1 method name instead of remembering multiple names
 * - Achieves consistency in naming.
 *      - 1 name for methods that are commonly used for example println
 *
 * - Overloaded method give programmers the flexibility to call a similar method with different types of data
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println(5);
        System.out.println(10.75d);
        System.out.println(10.7f);
        System.out.println('\u00A9');
        System.out.println(Arrays.toString(new int[]{1, 2, 3}));
    }
}
