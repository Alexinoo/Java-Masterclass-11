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
 *  - Team<T extends Player> is a way of restricting the type of class that we can actually use for team
 *  - we've told Java that the Type parameter will accept any type that extends from Player class or a subclass of
 *    player
 *  - Team<T extends Player> , Player is said to be the UPPER bound of T , & of course T is a bounded type parameter
 *  - So, if we attempt to use another class that is not derived from Player, we'll get an error which we're seing
 *    here with the statement below
 *      Team<String> brokenTeam = new Team<>("this won't work");
        brokenTeam.addPlayer("no-one");
 *
 *  - And now that we have done that we don't need any casting from the addPlayer(). remove it and leave it as it was
 *    before which is player.getName()
 *      ((Player)player).getName() to player.getName()
 *  - And the reason we're able to do that, is because of the bounded type parameter that we've put up there
 *  - We've said all valid UPPER bounds of T , have to extend from Player, and so it's going to have all its
 *    methods, including getName() of player and casting is no longer needed
 *
 *  - Java is also showing an error when we try to parse a String to the addPlayer(), which is exactly what we want
 *    as we have defined the addPlayer() and the class to use the "Player player" class or subclass of Player
 *      - and obviously String isn't and hence the error on this line
 *          brokenTeam.addPlayer("no-one");
 *
 * Few Points to note
 *  - An argument passed for a Type parameter can either be a class or an interface
 *  - Although we use a class, in this example, Player class, you could have declared Team to accept an interface
 *    which is also valid in Java
 *  - Another thing is that interfaces can also specify Type parameters
 *      - You can create instances of a class that implement an interface and ensure that interface method(s) that
 *         you implement act on a specific type of object(s)
 *  - We also use a Single bound when we specified the Team Type parameter, but Java also allows multiple bounds
 *
 *  - You can have Team<T extends Player & Coach & Manager> to allow multiple instances of player, coach or manager
 *    to be added to the team
 *  - However the normal inheritance rule still applies, i.e. you can only extend from a single class, but multiple
 *    interfaces
 *  - So in short, if you need to do this with multiple classes, you'll need to use interfaces for other types as well
 *  - If you're specifying multiple bounds, the class must be listed first, otherwise, you'll get a compile error
 *      - Class first and then interfaces
 *
 *  - And the way you do that for argument’s sake is
 *           Team<T extends Player & Coach & Manager>
 *  - separating the interfaces with an "&"
 *  - But of course, you can only have 1 class, which is the Player in this scenario and Coach and Manager have to
 *    be interfaces and that would be the syntax for multiple interfaces
 *
 *  - And finally SoccerPlayer and FootballPlayer are both classes of Player as we know, and obviously there's a rship
 *    between them, so either can be used when calling a method that expects a Player
 *
 *  - Next, let's record the game results for some random Football teams
 *
 *  - Modify, matchResult() and a String variable that lets us know whether our team won, lost or drew
 *      - then print the message inside the if statement since the opponent will never be null at that point
 *      - And now as you can see we're getting a better feedback on who lost/ won/drew with who
 *
 *  - Notice the odd result that we're getting between AFC and Chicago Cubs, where AFC is a football team and Chicago
 *    cubs is a baseball team
        afcLeopards.matchResult(chicagoCubs , 1,1 );
 *  - And you probably already know what we need to do to prevent that, which is to ensure only the correct team Type
 *    can be passed to the matchResult() , inside any obj of type Team
 *
 *      matchResult(Team opponent , int ourScore , int theirScore)
 *  - to
 *
 *      matchResult(Team<T> opponent , int ourScore , int theirScore)
 *
 *  - After doing this, we're now correctly getting an error from below line
 *       afcLeopards.matchResult(chicagoCubs , 1,1 );
 *  - meaning we're only supposed to use football teams, and Java has got that correctly indicating that's a compile
 *    time error
 *
 *
 * Ranking
 *  - The Team class, include a simple ranking which is a very simple formula to calculate how the team is ranked in
 *    the league
 *      - which is the number of games won multiplied by 3 plus games tied
 *  - Obviously, a typical league ranking would consider the number of games played, goal differences and other things
 *    as well , and we can implement those complexities if interested and play around but we'll keep it simple here
 *
 *  - Let's go ahead and add the rankings
 *      - printRankings(Team<T> team)
 *          - print name of the team and the points (rankings)
 *
 *  - This works well, but 1 thing we can't do is to compare Teams, and see how they're doing in the league
 *
 *  - Let's make a change to our Team class, and implement something very useful in interface called Comparable
 *
 *  - Update the Team class to implement Comparable and then use the diamond again to make sure that we're using the
 *    same generics again
 *      Team<T extends Player> implements Comparable<Team<T>>
 *  - And because we only want to compare our teams to other teams, that's why we're actually using the Team<T> ,team
 *    and the type, because it doesn't make sense to compare a Football Team to a Baseball Team
 *  - Therefore, that's why we're adding Team<T> , team and the actual type , in another set of diamonds
 *
 *  - This looks complicated but in actual sense we've created the ability to have generic types, such as Football
 *    Team and so we're specifying a generic type as a parameter, to the generic interface comparable
 *
 *  - So, in this one here, Comparable<Team<T>> , Team is the type in this case, Team<FootballPlayer> and Team<T> is
 *    also a Type, and so Comparable<Team<T>>
 *     - can accept either
 *          Comparable<Team<FootballPlayer>>
 *          Comparable<Team<BaseballPlayer>>
 *          Comparable<Team<SoccerPlayer>>
       - and we're doing this so that we don't compare Football Team with a Baseball Team for argument's sake
 *
 *  - So if just do Comparable<Team>, and just use literally Team, compareTo() could only take Team as its parameter
 *    , then when we write compareTo(), it will only take Team as it's parameter
 *      - then we wouldn't have that ability to ensure that only the types of teams, in this case, are appropriate
 *        to be passed to that method
 *
 *  - We then need to implement compareTo() defined in the Comparable interface takes an Object and returns
 *      - a negative number if this is less than the object
 *      - zero, if equal
 *      - positive number if greater than the obj
 *
 *  - less than means, we should sort lower and teams with higher rankings to sort higher in the list
 *  - therefore we need to make our compare to return less if the ranking, is greater than the opponents
 *
 *  - We've used the compareTo() in our LinkedLit challenge and many of Java classes actually implement this
 *    interface including the String and Integer classes
 *      - And that's how these obj(s) can be sorted as a result of doing that
 *
 * compareTo()
 *  - first test
 *      - check if the current object ranking "this.ranking()" is greater than the object being passed ranking
 *          - return -1
 *          - i.e. the current team, the one that we're actually in, referred by "this", if the ranking for that team
 *            is higher than the team passed to this method, then it actually means "this" is actually higher in
 *            essence & we'll return -1
 *     - check if the current obj ranking "this.ranking()" is less than "team.ranking(), return 1, because the
 *       opposite is true
 *          - the current object hasn't got more points, essentially, it's got less points, so return 1
 *     - otherwise
 *          - return 0
 *
 * Next
 *  - compare the teams
 *      i.e. afcLeopards.compareTo(fremantle) ( 6 -0 ) returns -1
 *
 * - If you got a List<Obj> that implement Comparable, such as an ArrayList<T> for example, what you, can do is you
 *   can quickly sort the list by using the static sort() of the Collections class
 *
 * - But essentially, if you've got an ArrayList of Teams, you can use this code , something like this
 *      ArrayList<T> teams
 *
 * - You can do
 *      Collections.sort(teams)
 *
 * - And that would be sorted, and the whole point of this is it will actually be sorted, using the compareTo() that
 *   we actually wrote for the actual Team itself
 *
 * - That's obviously, 1 way of sorting, but you can also refer to the sort() that we wrote for the Arrays challenge
 *
 * - However, this is quite simple, because we're using the built-in Java Collections class
 *
 * - ANd we've got an entire section about the Collections in upcoming videos
 *
 *
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

        //Team<String> brokenTeam = new Team<>("this won't work");
        // brokenTeam.addPlayer("no-one");

        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");
        Team<FootballPlayer> melbourne = new Team<>("Melbourne");

        //game results
        hawthorn.matchResult(fremantle,1,0); // Hawthorn 1 - 0 Fremantle
        hawthorn.matchResult(afcLeopards,3,8); // Hawthorn 3 - 8 AFC Leopards
        afcLeopards.matchResult(fremantle,2,1 ); // AFC 2 - 1 Fremantle

        // odd Result FootballTeam vs BaseballTeam
       // afcLeopards.matchResult(chicagoCubs , 1,1 );
        System.out.println("=== Rankings ======");
        afcLeopards.printRankings(afcLeopards);
        fremantle.printRankings(fremantle);
        hawthorn.printRankings(hawthorn);

        // Compare Teams
        System.out.println("=== Compare ======");
        System.out.println(hawthorn.compareTo(fremantle)); // Hawthorn(3 points) Fremantle(0 points) (-1)
        System.out.println(afcLeopards.compareTo(fremantle)); // AFC(6 points) Fremantle(0 points)  (-1)

        System.out.println(fremantle.compareTo(afcLeopards)); // Fremantle(0 points) AFC(6 points)  1

        System.out.println(fremantle.compareTo(melbourne)); // Fremantle(0 points) Melbourne(0 points)  0



    }

}
