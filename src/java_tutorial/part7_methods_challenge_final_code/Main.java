package java_tutorial.part7_methods_challenge_final_code;
/*
 * Method Challenge - Final Code Changes
 * .....................................
 *
 * - Just before we look at method overloading, there's a small logical problem in our conditions, in the program that
 *   we've been currently working on
 *
 * - If we carefully check the if-statement in the calculateHighScorePosition()
 *      - the 1st condition is checking if player's score is >= 1000 and if that's false, then obviously score isn't
 *          greater than 1000
 *          - this means that the score is less than 1000
 *      - the 2nd, we're checking here to see whether the player score is less than 1000, but again if we managed to get
 *         here, we know that the player score is already less than 1000, otherwise we wouldn't have got to that line
 *          - the bottom line here is that we can remove that condition since it's always true, in other words, because
 *             we've already established that the player score >= 1000 is false
 *      - same applies to the 3rd condition, where we've got the same problem there, because we're checking to see
 *         whether the score >= 500 here , and if we only get here because we know that the player's score is < 500
 *
 * - In short, that's a bit of a redundant bit of code
 *
 * N/B
 * - If you have the latest IntelliJ, notice IntelliJ suggestions and effect the changes
 *      - Condition 'playerScore < 1000' is always 'true' when reached
 *
 * Next,
 * - We can also remove the else statement and have return 4 after the closing brace
 *      - the code will behave the same in that scenario
 *      - and we still get the same results
 *
 * Next,
 * - Let's look at one more solution that eliminates multiple return statements
 * - Multiple return statements can be confusing for people starting out with programming
 *      - Let's make our code a bit easier to follow and use only 1 return statement
 *          - Initialize an integer variable position to 4 - Assume that it will return 4
 *          - check the if statements and update the position for each range
 *              - if no condition matches the if statement, 4 will be returned, otherwise return the updated position
 *          - return position
 *      - We still get the same results
 *
 */
public class Main {
    public static void main(String[] args) {
        int highScorePosition = calculateHighScorePosition(1500);
        displayHighScorePosition("Tim",highScorePosition);

        highScorePosition = calculateHighScorePosition(900);
        displayHighScorePosition("Bob",highScorePosition);


        highScorePosition = calculateHighScorePosition(400);
        displayHighScorePosition("Percy",highScorePosition);

        highScorePosition = calculateHighScorePosition(50);
        displayHighScorePosition("Gilbert",highScorePosition);

        highScorePosition = calculateHighScorePosition(1000);
        displayHighScorePosition("Louise",highScorePosition);

        highScorePosition = calculateHighScorePosition(500);
        displayHighScorePosition("Carol",highScorePosition);

        highScorePosition = calculateHighScorePosition(100);
        displayHighScorePosition("Frank",highScorePosition);
    }

//    public static int calculateHighScorePosition(int playerScore){
//        if (playerScore >= 1000){
//            return 1;
//        }else if (playerScore >= 500){
//            return 2;
//        }else if (playerScore >= 100){
//            return 3;
//        }else{
//            return 4;
//        }
//    }

//    public static int calculateHighScorePosition(int playerScore){
//        if (playerScore >= 1000){
//            return 1;
//        }else if (playerScore >= 500){
//            return 2;
//        }else if (playerScore >= 100){
//            return 3;
//        }
//            return 4;
//    }

    public static int calculateHighScorePosition(int playerScore){
        int position = 4;
        if (playerScore >= 1000){
            position = 1;
        }else if (playerScore >= 500){
            position = 2;
        }else if (playerScore >= 100){
            position = 3;
        }
        return position;
    }


    public static void displayHighScorePosition(String playerName , int highScorePosition){
        System.out.println(playerName + " managed to get into position "+ highScorePosition + " on the high score table" );
    }
}
