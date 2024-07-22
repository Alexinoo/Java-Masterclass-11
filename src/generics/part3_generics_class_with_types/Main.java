package generics.part3_generics_class_with_types;

/*
 * Generics With Classes - With Types
 *
 * - The goal is to ensure players get added to their right Teams
 *      - A football player to be added to a Team<Football>
 *      - A basketball player to be added to a Team<Basketball>
 *      - A soccer player to be added to a Team<Soccer>

 * - We need a mechanism to make sure this happens
 *
 * - There are few solutions to this:
 *      - Create 3 different Team classes -> A Football team , A Baseball team , A Soccer Team
 *          - However, the code will largely be identical, with a lot of duplication code
 *
 *      - Java has generics which allows us to specify a type when creating a class, which is exactly what we want here
 *
 *  - So let's go ahead and modify Team class to be a generic one
 *
 *  - First
 *      - Modify the class declaration to include a Type Parameter
 *          i.e. class Team<T>
 *
 *  - Second
 *      - Modify the ArrayList to take T parameter instead of Player Object
 *          i.e.  private ArrayList<T> members;
 *
 *  - Third
 *      - addPlayer(Player player) - update to - addPlayer(T player)
 *
 * - By doing this we're changing the type, so that it's going to work for any type of player
 *
 * - When we instantiate a class, the T acts a placeholder , and will be replaced automatically by Java, with the actual class we're using with the
 *   real type, when we're instantiating the class
 *
 * - And that's the reason we've changed the Player and the ArrayList as well, so that we're able to do that sort of Type checking
 *
 * - Notice, we're getting an error in addPlayer(Player player) after changing it to addPlayer(T player) , and the reason is that we're specifying
 *   that its type is T, which is a parameterized type
 *
 * - And because this type is not known, until we instantiate a class in other words at run time, there's actually no way for Java to know that an
 *   object of type T in this case has a getName()
 *
 * - The only sensible thing, the compiler can do is to flag an error, which is actually showing on the screen
 *
 * - So, what we can do is ultimately cast that to Player because we know it is going to be a player object any way
 *      - it is a solution , but it's a pretty ugly cast in the sense that we're invoking a method on the cast object and we need parentheses around the
 *        cast before we can use getName()
 *
 * main()
 *  - we get raw type warnings because we're not validating the type of player against the particular type of team
 *
 *  - But we can get around that by specifying what type of team our football team variable should be
 *  - So Team now has the ability to accept Type arguments, we've changed in the Team class
 *  - And therefore we need to specify with diamond operator what type of the Team during the declaration
 *      i.e.
 *          Team<FootballPlayer> afcLeopards = new Team<>("AFC Leopards");
 *
 *  - And now we've got a proper assignment of our generic class now
 *  - So we've now indicated that we're going to be using the football player and consequently, you can see that "Mike Olunga" was added and the reason
 *    is that "Mike Olunga" is valid and it's not being and the compiler is noy happy because "Mike Olunga" was assigned as a Football Player
 *
 *  - However, the other 2, KobeBryant and colePalmer, because they're BaseballPlayer and SoccerPlayer, they don't meet the criteria for this type now
 *    and they will be flagged as an error
 *
 *  - In other words, this particular instance of team is now only going to accept FootballPlayer and the attempts to add BaseballPlayer and SoccerPlayer
 *    are showing errors
 *
 *  - So, let's add another Team for Baseball and add KobeBryant
 *
 *      i.e.   Team<BaseballPlayer> chicagoCubs = new Team<>("Chicago Cubs");
               chicagoCubs.addPlayer(kobeBryant);
 *
 *  - This now works because KobeByrant has been defined as a BaseballPlayer and he's of type BaseballPlayer and we've defined that as the team of
 *     being a BaseballPlayer and so consequently, he's able to be added without any problems
 *
 * - But with that said, there's still some problems here
 * - At the moment the Type parameter T in our Team class can be supplied rather any type except primitive types such as int , in other words,
 *   in other words, any object
 *      - So, we're not doing any validation of that
 *
 *  - We can do something for example
 *      i.e. Team<String> brokenTeam = new Team<>("this won't work");
 *           brokenTeam.addPlayer("no-one");
 *
 *  - The String are valid type and we've got no compilation errors, however, we get ClassCastException because java.lang.String cannot be cast to
 *    Player
 *      - where we added the ugly cast in the addPlayer() to get getName() to work
 *
 *  - So, we need to have some way to validate the type of class actually when we're using it for this team
 *  - In other words, we need the ability to restrict the type that can be provided for this T argument from the Team class
 *
 *  - Java also provides a mechanism for restricting the types that can be used as type arguments and are called Bounded Type parameters
 *
 *  - By using those, you can provide them with an upper bound
 *      - we can set our Team<T> to Team<T extends Player>

 *  - And once we do that we get an error from
 *           Team<String> brokenTeam = new Team<>("this won't work");
 *           brokenTeam.addPlayer("no-one");
 *
 *  - that says java.lang.String is not within it's bound and should extend the Player obj
 *
 *  -
 *
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) {

        //create the 3 players
        FootballPlayer mikeOlunga = new FootballPlayer("Michael Olunga");
        BaseballPlayer kobeBryant = new BaseballPlayer("Kobe Bryant");
        SoccerPlayer colePalmer = new SoccerPlayer("Cole Palmer");

        Team<FootballPlayer> afcLeopards = new Team<>("AFC Leopards");
        afcLeopards.addPlayer(mikeOlunga);

        Team<BaseballPlayer> chicagoCubs = new Team<>("Chicago Cubs");
        chicagoCubs.addPlayer(kobeBryant);

        Team<SoccerPlayer> chelsea = new Team<>("Chelsea");
        chelsea.addPlayer(colePalmer);

        Team<String> brokenTeam = new Team<>("this won't work");
        brokenTeam.addPlayer("no-one");

    }
}
