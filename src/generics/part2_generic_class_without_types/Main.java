package generics.part2_generic_class_without_types;
/*
 * Generics With Classes
 *
 * - We've seen how Generic types can be used and how they're actually useful to ensure that we don't produce a program that compiles fine but actually
 *   give us errors at runtime
 * - We've looked at an example where we added a String to what was an ArrayList that we assumed only contained integers
 *
 * - Let's take that to another step by creating our own generic classes
 *
 * - Let's add a concept of adding/creating players to various types of sporting teams
 *
 * - Let's create some basic classes
 *
 *  abstract class : Player
 *
 *  Fields:
 *      name  : String
 *
 *  Constructor
 *      Player(String name)
 *
 *  Getter
 *      getName : String
 *
 * - Next create 3 types of players, that all inherit from Player class
 *      - Baseball player
 *      - Football player
 *      - Soccer player
 *
 * - calls the constructor of the superclass (Player) to initialize the name attribute for each sets of
 *   Players
 *
 * - So, we have created the 3 classes, but so far there is nothing generic about them at this stage
 *
 * - Next, let's create a Team class, and in that Team class, add the ability to add players
 * - We'll not extend anything because this is a base class and will have the following fields
 *
 *  Team : class
 *      - name of the team
 *      - track the number of games played
 *      - track the number of games won
 *      - track the number of games lost
 *      - track the number of games drew
 *
 *  Fields
 *      name : String
 *      gamesPlayed : int
 *      won : int
 *      lost : int
 *      drew : int
 *
 *      members : ArrayList<Player>
 *          - Create an ArrayList that's using generic for our Player's object(s) and call it members
 *              - Player, is our abstract class that all the other 3 classes extends from
 *
 *  Constructor
 *      Team(String name)
 *          - Initialize name and members ArrayList
 *
 *
 *
 *  Methods
 *      addPlayer(Player player) : boolean
 *          - Checks if the player exists in Members ArrayList
 *          - If so
 *              - prints, player exist and return false
 *          - Otherwise
 *              - add player to the members
 *              - print "player added to team"
 *              - and return true
 *
 *      numberOfPlayers() : int
 *          - return no of players in the members arraylist
 *
 *      matchResult(Team opponent , int ourScore , int theirScore) : void
 *          - if
 *              our score > opponent score, then increase gamesWon
 *              our score is equal to opponent score, then increase gamesDrew
 *              otherwise, increase games lost
 *          - increment games played as well
 *          - then check if opponent is null and if not   -- update opponent score
 *              - call matchResult with our score and their score
 *                  opponent.matchResult(null, theirScore, ourScore)
 *              - passing ull so that matchResult doesn't get executed again
 *          - this way we're saving results for our team, and also saving results for the opponent's team
 *              - sort of saving the results in their sort of object as a separate
 *
 *      ranking() : int
 *          - return gamesWon by 2 and add the games tied
 *
 *
 *
 * - Next,
 *      - Create the 3 players : Baseball Player , Football Player , Soccer Player
 *
 *      - Create Team : AFC Leopards
 *          - Add the 3 players to AFC Leopards
 *
 * Next,
 *  - Let's tackle this problem of making sure that a particular team will only sign a player of the right type
 *  - For example, if we specify team, then we will only sign players associated with that team
 *
 */
public class Main {
    public static void main(String[] args) {
        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        Team afcLeopards = new Team("AFC Leopards");
        afcLeopards.addPlayer(joe);
        afcLeopards.addPlayer(pat);
        afcLeopards.addPlayer(beckham);

        System.out.println(afcLeopards.numberOfPlayers());

    }
}
