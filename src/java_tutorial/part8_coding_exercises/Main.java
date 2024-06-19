package java_tutorial.part8_coding_exercises;
/*
 * Coding Exercises
 * ................
 * - Coding exercises are features added by Udemy to allow instructors to add exercises that students can complete to
 *   reinforce concepts they've been taught
 * - The cool thing is you can click a button and have your solution checked immediately
 *
 * - The instructor gives a coding exercise to complete and you can type it interactively into the screen and click a
 *    button to see if your solution is correct
 *
 * - This is different to the challenges we've seen so far in the course, when we see Tim give us a challenge and then go
 *   through the solution in a video
 *
 * - Coding Exercises give you the exercise and you do them without seeing a solution in the video
 *
 * - There are plenty of upcoming challenges in the course, and coding exercises are meant to complement those challenges
 *
 * - They can be tricky to understand initially, so let's look at how to go through a sample coding exercise so that
 *   when you get to the first coding exercise in the course, you'll know how to go about it
 *
 * - go over to Udemy's website and open up a coding exercise and get started
 *
 * Sample Exercise
 * ................
 *  1. Positive, Negative or Zero
 *    - Instructions From Udemy
 *
 *      - Write a method called checkNumber with an int parameter number
 *
 *      - The method should not return any value, and it needs to print out :-
 *          - "positive" if the parameter number is > 0
 *          - "negative" if the parameter number is < 0
 *          - "zero" if the parameter number is equal to 0
 *
 *    - N/B
 *          - The checkNumber() needs to be defined as public static like we have been doing so far in the course
 *    - N/B
 *          - Do not add a main() to your solution code
 *
 * Doing Via IntelliJ
 * ...................
 * - Define checkNumber(int number)
 *      - Test for the 3 conditions
 *
 * - call checkNumber() from the main()
 *
 * - Test with different values
 *      checkNumber(1); // positive
 *      checkNumber(0); // zero
 *      checkNumber(-1); // negative
 *
 * - If it works, copy the checkNumber() to Udemy's website
 *      - Do not add the main()
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        checkNumber(1);
        checkNumber(0);
        checkNumber(-1);
    }

    public static void checkNumber(int number){
        if (number > 0){
            System.out.println("positive");
        } else if (number < 0) {
            System.out.println("negative");
        }else {
            System.out.println("zero");
        }
    }
}
