package java_tutorial.part9_method_overloading;
/*
 * Method Overloading
 * ..................
 *
 * - This is a feature in Java where you use the same method name, but with different parameters
 *
 * Example
 *  - Create calculateScore(String playerName, int score) with 2 parameters
 *     - print player name and the score
 *     - return the score multiplied by 1000 which is just an arbitrary number
 *  - call calculateScore("Tim",500); and store the result to newScore variable
 *      - print new score
 *
 * Next,
 * - Let's overload this method which is basically, creating another method with the same but with a variable number,
 *   different number of parameters
 *
 * - Create calculateScore(int score) with only 1 parameter
 *      - print Unnamed player scored some points
 *
 * Notice,
 *  - the method is grayed out, because we haven't used it before
 *
 * Next,
 *  - call calculateScore(75);
 *
 * Next,
 * - Create calculateScore() with no parameter
 *      - print no player name and no score
 *      - return 0
 * - call calculateScore() from main()
 *
 * So,
 * - we have created calculateScore() but with separate output, working quite nicely on the screen
 * - when we overload a method, we need to create a unique method signature
 * - the signature is the
 *      - actual method name
 *      - parameters
 *
 * Next,
 * - Let's create another calculateScore() that doesn't return anything
 *  - returns a void and not an int
 * - we might think we can do that by changing the return type, but does that makes it unique ?
 * - the error we're getting here is that calculateScore() is already defined,
 * - Java is telling us that just changing the data type of the return type , the type of the data that's
 *   going to be returned from the method doesn't change the overall signature
 * - So in short, we do need to change the number of parameters to make it unique
 *      - comment it out
 */
public class Main {

    public static void main(String[] args) {
      int newScore = calculateScore("Tim",500);
        System.out.println("New score is "+newScore);

        calculateScore(75);
        calculateScore();
    }

    public static int calculateScore(String playerName, int score){
        System.out.println("Player "+ playerName + " scored "+ score+ " points" );
        return score * 1000;
    }

    public static int calculateScore(int score){
        System.out.println("Unnamed player scored " +score+ " points" );
        return score * 1000;
    }

    public static int calculateScore(){
        System.out.println("No player name, no player score" );
        return 0;
    }

//    public static void calculateScore(){
//        System.out.println("No player name, no player score" );
//    }
}
