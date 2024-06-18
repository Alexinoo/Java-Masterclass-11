package java_tutorial.part5_methods_in_java;
/*
 * Methods in Java
 * ................
 *
 * - Methods allows us to ensure that our code is not duplicated, and this makes our code a lot easier to maintain
 *
 * - We'll use the code we had before on part3_if_else_statement but now use the concept of methods
 *
 * - Create calculateScore()
 *      - public static void are all keywords
 *      - add our statement inside the {}
 *
 * - Call calculateScore() from the main()
 *
 * - Passing information to the method
 *      - define in the method parameters, what information we'll be sending through to it from our main()
 *      - separate each variable with a comma
 *          - notice we're not putting a value there, we're only adding the datatype and the name of the variable
 *
 * - Then on the main()
 *   - we need to call calculateScore() and provide the arguments
 *   - arguments are the actual values, not the data type, or the variable name, but the actual values we want to send to the method
 *
 * - Please also note that the actual arguments that we're passing needs to match the parameters we've defined for the method
 *   - This is really important because if you don't those entirely, you'll get an error
 *
 * - When we're creating a method, and you're defining parameters of a certain type and a name, you then don't have to create them in
 *   the method at all
 * - What happens is that if you define these parameters, Java will automatically create variables with the appropriate data type for us and they'll
 *   be deleted when the process goes back to the line after the calculateScore
 *
 *
 * Next
 * - We can also calculate something and send the value back to the code that was actually calling the method
 * - update void to int
 *      - void means we're not returning any value back to the caller
 *      - update void to a data type of the information that we want to return
 * - In this case let's return the final score
 *      - return finalScore if the game is over
 *      - otherwise, if the game is not over, return -1
 *
 * - All the program variations have to be accounted for when returning from a method
 *
 * But why are we returning -1 ?
 * .............................
 * - In programming terms, -1 is conventionally used to indicate an error
 *      - In searching algorithms, -1 indicates an invalid value, or value not found
 *
 * Next,
 * - store the value that the method is returning from calculateScore() to "highScore" variable
 * - print the final score
 *
 *
 *
 *
 * Features of a method
 * ....................
 * - Easier to maintain your code
 *      - we've only got 1 place now where our code is modified
 *      - we've cleaned up a lot of code, literally 2 lines
 */
public class Main {

    public static void main(String[] args) {
       int highScore = calculateScore(true, 800, 5, 100);
       System.out.println("Your final score was "+highScore);


        highScore =  calculateScore(true, 10000, 8, 200);
        System.out.println("Your final score was "+highScore);
    }

//    public static void calculateScore(boolean gameOver , int score , int levelCompleted, int bonus){
//        if (gameOver){
//            int finalScore = score + (levelCompleted * bonus);
//            finalScore += 1000;
//            System.out.println("Your final score was "+finalScore);
//        }
//    }

    public static int calculateScore(boolean gameOver , int score , int levelCompleted, int bonus){
        if (gameOver){
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 2000;
            return finalScore;
        }
        return -1;
    }
}
