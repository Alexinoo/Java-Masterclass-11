package control_flow_statements.part6_while_and_do_while_loop;
/*
 * The while and do while statements
 * .................................
 *
 *
 * while statement
 *
 * - for loop is used to execute a code block a given number of times when the condition is true
 * - with looping, sometimes, you might want to loop, instead of a specific no of times, you want to loop until a certain
 *   expression evaluates to false
 * - you may not know how many times you want to loop in advance, and that's where a while loop comes in handy
 *
 * syntax:
 *      init;
 *      while(condition){
 *          // statements
 *          // increment;
 *      }
 *
 *  - initialize count variable to 1
 *  - add while condition
 *      - check if count variable value is equal to 6
 *      - print the value of the count variable
 *      - increment the count variable by 1
 *
 * - this while loop is equivalent to the for loop below - #Example-2
 *
 * - we can also write the above while condition as below - #Example-3
 *      - reset the count to 1
 *      - loop as long as the while condition is true
 *      - add a code block
 *          - break out of the loop if the count value is 6
 *      - print the value of the count variable
 *      - increment the count value by 1
 *
 * - The while loop in #Example-1 and #Example-3 do the same thing
 *
 * - However, in #Example-3, we are specifically saying if we get to the point where the value of the count variable
 *   is equal to 6, then break out of the loop
 * - In both scenarios, we need to make sure that the variable is incremented
 *
 * - In case, we don't increment the count variable, we'll end up with an endless loop since while(count !=6) or
 *   while(true) will always be true
 *
 *
 *
 *  while(true)
 * - you'll often see "while(true)" in Java, and this means it's going to be an infinite loop
 *      - the loop will process forever
 * - the way to get out of the loop is to do a test of some sort, or add some sort of expression that evaluates to
 *   true, then break out of the loop like we're doing below
 *      if(count == 6)
 *          break;
 *
 * Example-4
 *  - the code block will not be executed at all as the condition evaluates to false
 *
 *
 *
 *
 *
 *
 *
 * do while
 * - will ALWAYS BE EXECUTED AT LEAST ONCE if not more times depending on the way you've defined your test expression
 *
 * syntax:
 *  - initialize
 *  - add do keyword
 *  - add a code block
 *      init;
 *      do {
 *         // statements
 *         // increment
 *      }while(condition);
 *
 * Example-6
 *  - If we set the value of the count to 6
 *      - the do while is guaranteed to execute at least once
 *      - we have the initial print out - where the count value is 6
 *      - we then increment the count to 7
 *          - and since while(7 != 6) evaluates to true from that point
 *      - we end up with an infinite loop where it continues to increment the number and runs the loop infinitely
 *
 * Example-7
 *  - We can use a break as a solution to #Example-6, to break when the count value is equal to a 100
 *  - prints from value 6 and exits the loop when the value of the count is equal to 100
 */
public class Main {

    public static void main(String[] args) {
        // Example-1
        int count = 1;
        while (count != 6){
            System.out.println("Count value is "+count);
            count++;
        }

        // Example-2
        System.out.println("Example-2");
        for (count  = 1; count != 6; count++) {
            System.out.println("Count value is "+count);
        }

        // Example-3
        System.out.println("Example-3");
        count = 1;
        while (true){
            if (count == 6){
                break;
            }
            System.out.println("Count value is "+count);
            count++;

        }

        // Example-4
        System.out.println("Example-4 : no output");
        count = 6;
        while (count != 6){
            System.out.println("Count value is "+count);
            count++;

        }

        // Example-5
        System.out.println("do while - Example-5");
        count = 1;
        do {
            System.out.println("Count value is "+count);
            count++;
        }while (count != 6);

        // Example-6
       // System.out.println("do while - Example-6");
       // count = 6;
       // do {
       //     System.out.println("Count value is "+count);
       //     count++;
       // }while (count != 6);


        // Example-7
        System.out.println("do while - Example-7");
        count = 6;
        do {
            System.out.println("Count value is "+count);
            count++;
            if (count == 100){
                break;
            }
        }while (count != 6);
    }
}
