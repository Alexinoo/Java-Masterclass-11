package generics.part4_generics_challenge;
/*
 * Generics Challenge
 * ..................
 *
 * - Create a Generic class to implement league table for a sport
 * - The class should allow teams to be added to the list, and store a List of teams that belong to the league
 * - Your class should have a method to print out the teams in order, with the team at the top of the league printed
 *   first
 * - Only teams of the same type should be added to any particular instance of the league class
 *      - the program should fail to compile if an attempt is made to add an incompatible team
 *
 * Solution
 *  - Define League class to be a parameterized type - bound by the Team class
 *      - a Team or any subclass would be allowed as the type argument when we create an instance of this league
 *          i.e. League<T extends Team>
 *
 *  - Then create a List<T> With a parameterized type of the Team class - which will hold league's teams
 *          private List<T> league;
 *
 *  - addTeam(T team) : boolean
 *      - checks if team already exist in the league
 *      - if so, return false, otherwise, add it to the league ArrayList
 *
 *  - showLeagueTable() : void
 *      - call Collections.sort(league) to sort the teams in the League ArrayList
 *      - Loop through the teams in the League and print them out with their ranking
 *
 * main()
 *  - Add Football players
 *      e.g. Cole Palmer
 *      e.g. Marcus Rashford
 *
 *  - Add Team<FootballPlayer>
 *       e.g. Chelsea FC
 *       e.g. Manchester United FC
 *
 *  - Add players to their respective clubs
 *      e.g. Sign Cole Palmer to Chelsea FC
 *      e.g. Sign Marcus Rashford to Manchester United
 *
 *  - Create a League
 *      e.g. England Premier League
 *
 *  - Add Teams to EPL
 *      e.g. Chelsea to EPL
 *      e.g. Manchester United to EPL
 *
 *  - Play the teams
 *      e.g. Chelsea 2 - 1 Manchester Utd
 *           Man utd 1 - 1 Chelsea
 *
 *  - Show League table
 *      print the table
 *
 * Next,
 *  - Let's talk about the warnings using the raw types so that we understand why we don't want to do that
 *  - The raw types are actually allowed in order to allow the legacy code, that's code prior to 1.5 or code written
 *    before the Generics was introduced to Java to allow that to work
 *  - But you can't rely on the root, compiling it give you an error when you use those raw types.
 *      - You'll actually get a warning, and IntelliJ will also give a warning but the code will compile any way
 *
 * Check /// Raw /// Section
 *  - The code will actually run, although, we're getting unchecked warning, and not how we can literally add
 *    whatever we want
 *      - Notice, how we're adding football / baseball players to "rawTeam" and then added it to "Raw" league and
 *        it's quite happily allowing us to do that
 *      - Even though they are all parts of different types of sports, nad you wouldn't normally want to do that
 *
 * - And this is all because we're using the raw types, not the generics
 * - You, it really should hit home that you should really be using these generics whenever you can because it makes
 *    your code cleaner, less likely to have crashes and eay to debug and maintain
 * - The errors that you're getting are going to be there at compile time and not obviously when the code's in
 *   production when you're trying to fix these weird bugs
 *
 * - The real issue is a
 *       League reallyRaw  = new League("Really Raw");
 * - where we're not even specifying what type of the team that this should hold
 *
 * - We could actually mitigate some of these problems to at least prevent the rawLeague declaration from compiling
 * - We could actually go back tour league class and add the following
 *      League<U extends Player ,T extends Team>

 * - And then, now, we can do this on the rawLeague
 *      League<FootballPlayer ,Team<FootballPlayer>> rawLeague = new League<>("Raw");
 * - Which would be 1 way of actually solving the problem , cuz now we can't add "lakers" to this league
 *
 * - However, still not ideal, and the instructor would recommend we do the way we had done, so that it's far easy
 *   to even work with
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        //ArrayList<Teams> teams;
        // Collections.sort(teams);

        // Football players
        FootballPlayer colePalmer = new FootballPlayer("Cole Palmer");
        FootballPlayer rashford = new FootballPlayer("Marcus Rashford");

        // Football
        Team<FootballPlayer> chelsea = new Team<>("Chelsea FC");
        Team<FootballPlayer> manutd = new Team<>("Manchester United FC");
        chelsea.addPlayer(colePalmer);
        manutd.addPlayer(rashford);

        League<Team<FootballPlayer>> epl = new League<>("English Premier League");
        epl.addTeam(chelsea);
        epl.addTeam(manutd);

        // Baseball - Players and Team
        BaseballPlayer kobeBryant = new BaseballPlayer("Kobe Bryant");

        Team<BaseballPlayer> lakers = new Team<>("Lakers");
        lakers.addPlayer(kobeBryant);

        // We cant add Baseball Team "Lakers" to epl as the league is defined for football players only "League<Team<FootballPlayer>> epl"
        //epl.addTeam(lakers); // Error

        chelsea.matchResult(manutd , 2,1);
        manutd.matchResult(chelsea , 1,1);

        epl.showLeagueTable();

        System.out.println("=======================================");

        // Raw Teams /////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////

        Team rawTeam = new Team("Raw Team");
        rawTeam.addPlayer(colePalmer); //unchecked warning
        rawTeam.addPlayer(rashford); //unchecked warning
        rawTeam.addPlayer(kobeBryant); //unchecked warning

        epl.addTeam(rawTeam); // unchecked warning

        League<Team> rawLeague = new League<>("Raw");
        rawLeague.addTeam(manutd);  // no warning
        rawLeague.addTeam(chelsea); // no warning
        rawLeague.addTeam(lakers); // no warning

        League reallyRaw  = new League("Really Raw");
        reallyRaw.addTeam(manutd); // unchecked warning
        reallyRaw.addTeam(chelsea); // unchecked warning
        reallyRaw.addTeam(lakers); // unchecked warning
    }
}
