package java_tutorial.part6_methods_challenge;
/*
 * Methods Challenge
 *..................
 *
 * - Create a method called displayHighScorePosition
 * - Should accept a player's name as the 1st parameter, and position as a 2nd parameter in the high score table
 * - Should display the player's name along with a message like
 *      " managed to get into position " and the position they got and a further message " on the high score table".
 *
 * - Create a 2nd method called calculateHighScorePosition
 * - Should take only 1 argument, the player score
 * - should return an int
 *
 * - return data should be
 *      - 1 if the score is >= 1000
 *      - 2 if the score is >= 500 and < 1000
 *      - 3 if the score is >= 100 and < 500
 *      - 4 in all other cases
 *
 * - call both methods and display the results of the following
 *      - a score of 1500,900,400, and 50
 *
 *
 * Next,
 * - Correct what was a flaw in the original code developed in this lecture
 *      - If we pass 1000, returns position 4
 *
 * - Added >= to cater for exact values - 1000, 500,100 to our conditions
 *      - Returns the correct position which is 1 in this case
 *
 * - Do other tests for 500 and 100
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

    public static int calculateHighScorePosition(int playerScore){
        if (playerScore >= 1000){
            return 1;
        }else if (playerScore >= 500 && playerScore < 1000){
            return 2;
        }else if (playerScore >= 100 && playerScore < 500){
            return 3;
        }else{
            return 4;
        }
    }

    public static void displayHighScorePosition(String playerName , int highScorePosition){
        System.out.println(playerName + " managed to get into position "+ highScorePosition + " on the high score table" );
    }
}
