package databases._05_music_sqlite_db;

import databases._05_music_sqlite_db.model.Artist;
import databases._05_music_sqlite_db.model.Datasource;
import databases._05_music_sqlite_db.model.SongArtist;

import java.util.List;
import java.util.Scanner;

/*
 * The Music SQLite Database
 *
 * Let's now switch to using music db so that we can have a decent set of data to work with as we look into more details at performing
 *  queries
 *
 * Download the same database music.zip again, unzip it and add it to this project
 *
 * Open the database from the DB Browser for SQLite and we can now see the 3 tables from the music db: artists , albums and songs
 * And we can browse the data as well for all the tables and now we have a good representation of the data
 *
 * Let's now pay more attention to how we structure our code, especially since we'd want to work with the music db with a GUI type
 *  application
 * We'll start by connecting to the database and use String CONSTANTS from the beginning
 *
 * Create a package known as model
 *
 *  - Create Datasource class
 *
 * ////////////////
 * Test Connection
 * ////////////////
 *
 * Create an instance of the Datasource class
 *
 *      Datasource datasource = new Datasource();
 *
 *  - Call open() from the Datasource instance
 *      - Check if the result is false, and print to the user that the connection to the database failed
 *      - If this is the case, check if the sqlite jdbc driver is added to the project and rerun again
 *      - If no errors, then the connection is working fine
 *
 *
 * ////////////////////////////
 * First Query - Artists Table
 *
 * We'll select all the rows in the table and print them to the console
 * We'll add a method to the Datasource class to do this
 * In large enterprise applications, we may need to create a Class in the model package for each table and the connections might be coming
 *  from a connection pool
 * But for this application, we'll keep our database methods within the Datasource class
 * In this scenario, do we want the method to return a ResultSet ?
 *  - We'll no we don't, because we don't want classes that use methods in the model package to have to understand the implementation details
 *     of the model
 *  - That way if we change databases later, or change to something that isn't a database, an xml file for example, we only have to change the
 *     code in the model package
 *  - So, instead of returning a ResultSet, we can return a List<Artist>
 *
 * That means we'll need classes for the Artist, Album and the Song , and we'll create those in the model package
 *
 * So let's go ahead and create those classes plus the Getter and Setter methods
 *
 *
 * ////////////////////////////////
 * Artist , Album and Song classes
 * ////////////////////////////////
 *
 * Artist : class
 *      id : int
 *      name : String
 *
 *      getId(): id
 *      getName(): String
 *
 *      setId(int id) : void
 *      setName(String name) : void
 *
 * Album : class
 *      id : int
 *      name : String
 *      artistId : int
 *
 *      getId() : id
 *      getName() : String
 *      getArtistId() : int
 *
 *      setId(int id) : void
 *      setName(String name) : void
 *      setArtistId(int artistId) : void
 *
 * Song : class
 *
 *      id : int
 *      track : int
 *      name : String
 *      albumId : int
 *
 *      getId() : id
 *      getTrack() : id
 *      getName() : String
 *      getAlbumId : id
 *
 *      setId(int id) : void
 *      setTrack(int track) : void
 *      setName(String name) : void
 *      setAlbumId(int id) : void
 *
 *
 *
 * ///////////////////////////////
 * Writing Java Query for Artists
 *
 * Let's write the queryArtist() in the Datasource class
 *
 * queryArtist() : List<Artist>
 *  - Initialize both Statement and ResultSet obj to null
 *  - Add a try-catch block
 *  - In the try block
 *      - Initialize statement obj by calling createStatement() on the Connection instance
 *      - Call executeQuery(String sql) on the Statement instance and pass the SQL statement for fetching artists which is going to return
 *           all the artist records with all column values
 *
 *      - Initialize a new ArrayList : artistsList
 *
 *      - Loop through the resultSet using a while loop
 *          - For each record, create a new artist obj
 *          - use the resultSet getter methods to get the values from the method
 *          - set them to the artist instance
 *          - add artist instance to the list
 *      - After looping, return the list to the caller
 *
 *  - In the catch block
 *      - print out error message if any
 *      - return null
 *  - In the finally block
 *      - close both Statement and ResultSet instances using 2 try catch blocks
 *      - catch any SQLException if any in both
 *
 * Rewrite the same method with try-with-resources
 *  - Comment out on the old try catch
 *  - Use try-with-resources
 *      - delete the finally block , because both the Statement and the ResultSet will be closed
 *         automatically whether there's an exception generated or not
 *
 * In this scenario, the callers of the method will still have to handle a null return value , however they see it
 *  fit
 * In other words the process that's calling the queryArtist() has to actually check and see whether what we're
 *  getting back in NULL or not
 *
 * //// Calling queryArtist() ///////
 *
 *  List<Artist> artists = datasource.queryArtist();
 *
 *  - Check if artists is null or empty
 *      - print to the user that no artist was found
 *
 *  - Otherwise
 *      - Loop through the artists ArrayList using enhanced for loop and print details to the console
 *
 *      System.out.println("ID = "+artist.getId() + ", Name = "+ artist.getName());
 *
 * We get a list of the artists , 201 in total
 * The main() doesn't make any assumptions about how or where the data is stored
 * The data could be coming from an xml file, a spreadsheet, a MyQL database or even a flat file
 * If we change how and where the data is stored as long as we don't have to change any of the method signatures
 *  in the data source class, we won't have to change any classes that uses it
 * And obviously the main() would remain unchanged
 *
 * //// A couple of more things to note ///
 *
 * First
 * The statement.close() closes the statement and any resultSet obj associated with it
 * We don't have to explicitly close the resultSet and that might make a difference to the code we've written
 * But if we were explicitly closing resources, closing the statement would close the resultSet
 *
 *
 * Second
 * We can make a quick improvement to the existing code
 * We're using column names to get the field values, but we can use the column index instead and that's usually
 *  more efficient
 * However there's a trade off
 *  - If we use column names, we won't have to change the code if the positions of the columns change within the
 *     table
 *      - For example, we might add a column to the artists table and position it between _id and name columns
 *  - If we use name columns, we won't have to change any code
 *  - But if we've used column indices , then we would have to change them
 * However, this easily handled if we use CONSTANTS in our column indices which, of course we will do
 * It's important to note that when working with result sets , the index we pass to the resultSet getter methods
 *  is the index of the column in the resultSet and not in the table
 * When we retrieve all the columns, the resultSet indices will be the same as the table indices
 * But if we only retrieve a specific column, or set of columns, then the result set indices may not actually match
 *  the table column indices
 * We'll start adding all the Column indices constants for all the tables
 *
 *
 *
 * ///////////////////////////////
 * Executing SQL in DB Browser
 * ///////////////////////////////
 *
 * We'll start by adding column index CONSTANTS for all the tables
 * Unlike everything else in Java, column indices are 1-based
 *  - The first col is at position 1
 *  - The second col is at position 2
 * If we use a zero, we'll actually get an exception
 *
 * Let's add the column indices CONSTANTS for all our tables as below
 *
 * Artist Table
 *      public static final int INDEX_ARTIST_ID = 1;
        public static final int INDEX_ARTIST_NAME = 2;
 *
 *
 * Albums Table
 *      public static final int INDEX_ALBUM_ID = 1;
        public static final int INDEX_ALBUM_NAME = 2;
        public static final int INDEX_ALBUM_ARTIST = 3;
 *
 * Songs Table -
 *      public static final int INDEX_SONG_ID = 1;
        public static final int INDEX_SONG_TRACK = 2;
        public static final int INDEX_SONG_TITLE = 3;
        public static final int INDEX_SONG_ALBUM = 4;
 *
 *
 * Let's now update the queryArtist() to use the column indices inside the while loop as below, becaose at the moment
 *  we were using column names
 *
 *      while (results.next()){
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }
 *
 * So let's change that to
 *
 *      while (results.next()){
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
 *
 * Run the program and make sure everything is still working
 *
 * ///
 * It's more efficient to use the column index because the getter methods will know exactly where to go and get the
 *  value in the result set
 * When we use the column names, the method has to match the column name against the columns in the result set
 * That doesn't matter when we're looping through a small set of records, but if we were to loop through thousands
 *  of records, it can as well make a difference
 * The queryArtist() retrieves all the records from the artists table
 * While this is useful, we may need a way to retrieve specific records
 * When working with a real-world application, we don't usually have to accommodate every possible query that can
 *  be made against every table
 * We usually look at the UI for the application, whether that is a web page, a mobile screen or a desktop window
 *  and we figure out what queries we need to perform to populate each web page, window,screen etc with data
 * And those are the queries we'd want to accommodate in our data source
 *
 * ///
 * So, let's think about what queries an application using the music database might want to perform and also the SQL
 *  statements we'd use to get the data
 * Let's assume our application allows the user to do the following
 *      - View a list of artists
 *      - View all albums by a particular artist
 *      - View all songs on a particular album
 *      - Find out which artist recorded a particular album
 *      - Find out which album the song is on
 *      - Find out which artist recorded a particular song along with the album the song is on and the track no
 *
 * Amongst others, but we'll stick to this set for this application
 * We've already coded queryArtist() that retrieves the list of artists
 *
 * //////////
 * Let's now write a method that retrieves all the albums for a particular artist, in other words viewing all the
 *  albums by a particular artist
 * Unfortunately, there isn't an elegant way to build query strings when using JDBC
 * It would be nice to be able to create a query string by chaining methods together like
 *  select()
 *      .join()
 *      .orderBY()
 * and there may well be 3rd party libraries that give us the ability to do just that, but the base API doesn't
 *  unfortunately
 * To perform the query we want, we have to build a query string and pass it to the statement.executeQuery() or
 *  execute
 * But first, let's modify the queryArtist() to accept a parameter that indicates how we want the artists to be
 *  sorted
 * We'll need String constants in the Datasource class that callers can use to specify the sort order
 * So let's add them first
 *
 *      public static final int ORDER_BY_NONE = 1;
        public static final int ORDER_BY_ASC = 2;
        public static final int ORDER_BY_DESC = 3;
 *
 * Then in the queryArtist() , instead of passing "SELECT * FROM artists" as we're doing currently, we're going to
 *  build a query string using a StringBuilder
 *
 * We'll start by adding a query argument to our method: queryArtist(int sortOrder)
 * Then create a StingBuilder class
 *
 *      StringBuilder sb = new StringBuilder("SELECT * FROM ");
 *
 * And then append the Table name as follows
 *
        sb.append(TABLE_ARTISTS);
 *
 * Then check the sortOrder value passed to queryArtist
 *
 * If the caller passes Datasource.ORDER_BY_NONE
 *  - we ignore adding the orderBy clause completely to the SQL statement
 *
 * Otherwise, if the value is none other than Datasource.ORDER_BY_NONE, Then we append the ORDER BY clause as below
 *
 *      append orderBy name with COLLATE NOCASE clause
 *
 *      - used COLLATE NOCASE to do case-insensitive order
 *
 * Next we're then we're checking the descending and ascending depending on the parameter that's actually passed
 *
 * Check if sortOrder value passed matches ORDER_BY_DESC constant
 * If so,
 *      append "DESC" to the String builder
 * Otherwise,
 *      append "ASC" to the String builder
 *
 *      if (sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }
 *
 * We could have changed the sort order to use an enum instead, so optionally we could do that and use it in some
 *  instances that could make more sense because we'd be validating the enum as a parameter
 * But for now the way we've defined the queryArtist(int sortOrder) and the int that has been passed to it,
 * it's just an integer and so it's possible to pass an invalid value that wouldn't be used
 *
 * Now that we have constructed the SQL statement, we then need to pass our String builder query string to the
 *  executeQuery(sb.toString())
 *
 * Also note that the if condition we're checking ORDER_BY_DESC and not ORDER_BY_ASC and we're doing that because
 *  if a caller passed a value that doesn't match one of the Sort CONSTANTS
 * In that case, our method will end up returning the artist sorted in ASC order, which is a reasonable default
 * We could have checked that the caller had passed a valid value and throw an exception if they hadn't or they used
 *  an enum instead, but having a default behavior like this is nicer
 *
 * //////
 * Let's modify the call of the queryArtist() and pass one of the SORT Constants
 *
 *      List<Artist> artists = datasource.queryArtist(Datasource.ORDER_BY_ASC);
 *
 *      - and pass order by ASC by default
 * And now if we run this, we get a list of artists sorted by artist name printed to the console
 * Check also the others, just to make sure they're working
 *      List<Artist> artists = datasource.queryArtist(Datasource.ORDER_BY_DESC);
 *
 *      - and the order by descending is working as well
 *
 *      List<Artist> artists = datasource.queryArtist(Datasource.ORDER_BY_NONE);
 *
 *      - and this implies no sorting, which is also working
 *
 * And also if we pass a number that we know is not represented with a CONSTANT
 *
 *      List<Artist> artists = datasource.queryArtist(5);
 *
 *      - we get the artists sorted by ascending order by default
 *
 *
 *
 *
 * ///////////////
 * Write a method that will retrieve all the albums by a particular artist
 * But before we do let's try the SQL statement in the DB Browser
 * It's always good practice to test the SQL we want to use before coding it
 * We didn't do for the SQL statement that retrieves all the artists because it was simple , but when we get into
 *  complex queries, it's always good to run the queries against the database before coding them
 *
 * Suppose we want to find out all the albums by Iron Maiden artist from the albums table by running the following
 *  SQL
 * We can also execute SQL here in the DB Browser interface by clicking on Execute SQL tab and write below SQL
 *  statement
 *
 *      SELECT * FROM albums WHERE artist = 8;
 *
 *      - and if we run this, we got 3 entries returned
 * So, now that we know what to expect from the query that will use a join, let's run the query we use against the
 *  database
 * Open a new tab from the DB Browser and type in the command below
 *
 *      SELECT albums.name FROM albums
 *      INNER JOIN artists ON albums.artist = artists._id
 *      WHERE artists.name  = 'Iron Maiden'
 *      ORDER BY albums.name COLLATE NOCASE ASC;
 *
 *      - and we get the same 3 entries returned similar to the ones returned above
 *
 * For good measure, let's try another artist : Pink Floyd , has an id of 130
 *
 *      SELECT * FROM albums WHERE artist = 130;
 *
 *      - and we get a lot more albums returned, 12 in total
 *
 * And now we need to update below to Pink Floyd
 *
 *      SELECT albums.name FROM albums
 *      INNER JOIN artists ON albums.artist = artists._id
 *      WHERE artists.name  = 'Pink Floyd'
 *      ORDER BY albums.name COLLATE NOCASE ASC;
 *
 *      - and sure enough we get the same results as above, 12 albums for Pink Floyd
 *
 * Let's also try another artist whose got only 1 album named Tapestry : Carole King , with
 *
 *      SELECT albums.name FROM albums
 *      INNER JOIN artists ON albums.artist = artists._id
 *      WHERE artists.name  = 'Carole King'
 *      ORDER BY albums.name COLLATE NOCASE ASC;
 *
 *      - and now if we run this, we get that 1 album Tapestry returned
 *
 * At this point we've done 3 test case, with 3 test queries and now we're good to go
 * We'll start writing a method for querying albums for artists in Java using JDBC to execute these queries
 *
 *
 *
 * ///////////////////////////////
 * Query Albums by Artist Method - queryAlbumsForArtist(String name , int sortOrder)
 * ///////////////////////////////
 *
 * We now know the SQL statement we want to use because we have tested that with 3 different artists and let's now p
 *  proceed to change that to java code
 * And we don't have to worry whether the SQL statement is correct or not because we have tested that it's working
 *
 * Takes 2 parameters:
 *  - String artistName - the name of the artist we'd want to search albums for
 *  - int sortOrder - The order we'd like our records to be sorted
 * Returns a List of String objects which are the names of the albums rather than the full record , since we're
 *  only asking for the name column
 *
 * Create a String builder for the query
 *
 *      StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(TABLE_ALBUMS);
        sb.append('.');
        sb.append(COLUMN_ALBUM_NAME);
        sb.append(" FROM ");
        sb.append(TABLE_ALBUMS);
        sb.append(" INNER JOIN ");
        sb.append(TABLE_ARTISTS);
        sb.append(" ON ");
        sb.append(TABLE_ALBUMS);
        sb.append('.');
        sb.append(COLUMN_ALBUM_ARTIST);
        sb.append(" = ");
        sb.append(TABLE_ARTISTS);
        sb.append('.');
        sb.append(COLUMN_ARTIST_ID);
        sb.append(" WHERE ");
        sb.append(TABLE_ARTISTS);
        sb.append('.');
        sb.append(COLUMN_ARTIST_NAME);
        sb.append(" = \"");
        sb.append(artistName);
        sb.append("\"");
 *
 * Next, add the code to work on the sort order which is going to be similar to the one we had for queryArtist()
 *
 *  if (sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(TABLE_ALBUMS);
            sb.append('.');
            sb.append(COLUMN_ALBUM_NAME);
            sb.append(" COLLATE NOCASE ");

            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
 *
 * And a good rule of thumb here is to print out the value, esp at the beginning just t make sure that the SQL
 *  statement is correct
 *
 *      System.out.println(sb.toString());
 *
 * Next, we need to use try-with resources and query the database
 *  - Initialize Statement obj from the Connection instance
 *  - Create a ResultSet obj by calling executeQuery(sb.toString()) on Statement instance
 *  - Initialize an ArrayList of Strings - albums
 *  - loop through the resultSet.next()
 *      - use resultSet getter methods to get the value of the column index 1 which is album name via getString(1)
 *          - notice we're hard coding the column index 1 which corresponds to the index of a column in the ResultSet
 *             and not the index of the column in the table
 *          - our query returns only the album name, and so there's only 1 column and the indices count start at 1 and
 *             not 0
 *      - add it to the albums arrayList
 *  - return albums array list
 *
 * /////
 * Call queryAlbumsForArtist(String name , int sortOrder) from the main() and print the results
 *
 *      List<String> albums = datasource.queryAlbumsForArtist("Iron Maiden",Datasource.ORDER_BY_ASC);
        //albums = datasource.queryAlbumsForArtist("Pink Floyd",Datasource.ORDER_BY_DESC);
        //albums = datasource.queryAlbumsForArtist("Carole King",Datasource.ORDER_BY_NONE);

        if (albums.isEmpty) {
            System.out.println("No albums!");
            return;
        }
        albums.forEach(System.out::println);
 *
 *      - And if we run this,
 *          - we get the 3 albums for the artist "Iron Maiden"
 *          - we get the 12 albums for the artist "Pink Floyd"
 *          - we get the 1 album for the artist "Carole King"
 *
 * //// Summary
 * We've build up a long query statement , and looking at it, most of the statement is static.
 * It's really only the artist's name and sort order that may be different each time the method runs
 * So rather than building the query statement from scratch each time the method executes, we can define bits of it
 *  as CONSTANTS
 * So let's go ahead and do that at the to of the Datasource
 *
 *       public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT "+ TABLE_ALBUMS + "."+ COLUMN_ALBUM_NAME +" FROM "+ TABLE_ALBUMS +
                    " INNER JOIN "+ TABLE_ARTISTS +" ON "+ TABLE_ALBUMS +"."+ COLUMN_ALBUM_ARTIST +
                    " = "+ TABLE_ARTISTS +"."+COLUMN_ARTIST_ID+
                    " WHERE "+ TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME + " = \"";
 *
 * Let's also do the same for the Order clause
 *
 *      public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY "+ TABLE_ALBUMS +"."+ COLUMN_ALBUM_NAME +" COLLATE NOCASE ";
 *
 * We could have a StringBuilder with a bunch of chained append calls similar to what we did in the method, but since
 *  the concatenations will only take place once when a Datasource instance is created, we're going to stick with
 *  concatenations
 * Often a Datasource class is used as a Singleton, and that means there would only be 1 instance of the class
 * We'll see how this will work when we use our model from a GUI application
 * You'll probably agree that these Strings look pretty ugly, but if we ever have to change the name of a table or
 *  a column, we'll be glad we didn't hard code any names and we'll only have to type these strings in one place
 * Hopefully, we can start imagining how we may end up with lots of Constants, which is why they'd often be put into
 *  their own classes
 *
 * Let's now modify our code to use the constants
 *
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");
 *
 * Then append QUERY_ALBUMS_BY_ARTIST_SORT constant if one is passed other than the ORDER_BY_NONE constant
 *  if (sortOrder != ORDER_BY_NONE){
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }
 *
 * And now our method is a lot more concise and better and easier to read
 * And if we run this, we get pretty much the same or identical results that we got earlier
 *
 * We could do something similar with the queryArtist() and make most of the String that we're building up the
 * StringBuilder to a constant , but Tim would leave that as an exercise for us based on what we've done here
 *  with the queryAlbumsForArtist()
 * Might be a good challenge for us to actually do the same thing with CONSTANTS for the queryArtist()
 */

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtist(Datasource.ORDER_BY_NONE);
        if (artists.isEmpty()) {
            System.out.println("No artists!");
            return;
        }
        for (Artist artist : artists) {
            System.out.println("ID = "+artist.getId() + ", Name = "+ artist.getName());
        }

        System.out.println("_".repeat(50));

        List<String> albums = datasource.queryAlbumsForArtist("Iron Maiden",Datasource.ORDER_BY_ASC);
        //albums = datasource.queryAlbumsForArtist("Pink Floyd",Datasource.ORDER_BY_DESC);
        //albums = datasource.queryAlbumsForArtist("Carole King",Datasource.ORDER_BY_NONE);

        if (albums.isEmpty()) {
            System.out.println("No albums!");
            return;
        }
        albums.forEach(System.out::println);

        System.out.println("_".repeat(50));

        List<SongArtist> songsByArtist = datasource.queryArtistsForSong("Go Your Own Way",Datasource.ORDER_BY_ASC);
        if (songsByArtist.isEmpty()) {
            System.out.println("Could not find the artist for the song!");
            return;
        }
        for (SongArtist songByArtist : songsByArtist) {
            System.out.println("Artist Name = "+songByArtist.getArtistName() + ", Album Name = "+ songByArtist.getAlbumName()+
                    ", Track no = "+songByArtist.getTrack());
        }

        /* Get songs table metadata */
        System.out.println("_".repeat(50));
        datasource.querySongsMetadata();

        /* COUNT(*) FUNCTIONS - count number of songs in specified tables */
        System.out.println("_".repeat(50));
        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: "+count);

        /* Create VIEW - artist_list */
        System.out.println("_".repeat(50));
        if (datasource.createViewForSongArtists()){
            System.out.println("artist_list View Created..");
        }

        /* Query VIEW - artist_list */
        System.out.println("_".repeat(50));
        List<SongArtist> songsForArtistViewList = datasource.querySongInfoView("She's On Fire");
        if (songsForArtistViewList.isEmpty()){
            System.out.println("Couldn't find the artist for the song specified");
            return;
        }
        for (SongArtist songForArtist: songsForArtistViewList) {
            System.out.println("FROM VIEW - Artist = "+songForArtist.getArtistName() +" ,Album = "+ songForArtist.getAlbumName()
                    + " ,Track = "+songForArtist.getTrack());
        }

        /*
         * SQL Injections
         * - Getting input from the user using a Scanner class - instead of hard-coding as we did above
         *
         * - Type : Go Your Own Way" or 1=1 or" to perform an SQL Injection

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();
        List<SongArtist> songsForArtistViewListPrepStatement = datasource.querySongInfoView(title);
        if (songsForArtistViewList.isEmpty()){
            System.out.println("Couldn't find the artist for the song specified");
            return;
        }
        for (SongArtist songForArtist: songsForArtistViewList) {
            System.out.println("FROM VIEW - Artist = "+songForArtist.getArtistName() +" ,Album = "+ songForArtist.getAlbumName()
                    + " ,Track = "+songForArtist.getTrack());
        } */


        /*
         * Using PreparedStatement
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();
        List<SongArtist> songsForArtistViewListPrepStatement = datasource.querySongInfoViewPrepStatement(title);
        if (songsForArtistViewListPrepStatement.isEmpty()){
            System.out.println("Couldn't find the artist for the song specified");
            return;
        }
        for (SongArtist songForArtist: songsForArtistViewListPrepStatement) {
            System.out.println("FROM VIEW - Artist = "+songForArtist.getArtistName() +" ,Album = "+ songForArtist.getAlbumName()
                    + " ,Track = "+songForArtist.getTrack());
        }

        /*
         * Insert a Song
         *  - Touch of Grey by Grateful Dead, In The Dark album , Track no. 1
         * Note ,
         *  - we have 201 artists
         *  - we have 439 albums
         *  - we have 5350 songs
         * Song inserted
         *
         * Add another song : Like a Rolling Stone, Bob Dylan , Bob Dylan's Greatest Hits , Track no 5
         *
         *
         * //// TESTING ////
         * - Simulate an Error
         *      - insertIntoSongs.setInt(7,albumId);
         *      - we know the last index should be 3, for the placeholder is supposed to be 3
         * - Insert a song : Bird Dog
         *      - by: Everly Brothers,
         *      - album: All-Time Greatest Hits
         *      - track no : 7
         *
         * //// RESULTS /////
         * - We got an ArrayIndexOutOfBoundsException error, which means that under the hood, the JDBC API, uses zero-based
         *  index, and works with some sort of an array-like structure
         *
         * - However, both the artist and the albums were added, but the question is WHY ?
         * - Didn't we have some code in there to do rollback ?
         *
         * //// EXPLANATION ////
         * - SQLException wasn't thrown, but rather the ArrayIndexOutOfBoundsException was thrown
         * - Therefore , the catch block was bypassed and rollback wasn't executed
         * - The finally block was executed which had the side effect of committing the changes and that's why the
         *   artist and the album were both saved
         *
         * ////  SOLUTION /////
         * - So, how do we actually get around this ?
         * - What we should do here is that instead of catching a SQLException, we should really catch all exceptions
         * - So let's change the SQLException to Exception and that will catch array index out of bounds exception or
         *   a SQLException , or any other Exception for that matter and we should get a rollback
         *
         * // TESTING ///
         * - Delete both entries from artists and albums table :-
         *      - artist : Everly Brothers
         *      - album : All-Time Greatest Hits
         * - Rerun again and check whether we get the proper behaviour that we're looking for which is a proper
         *   rollback
         *
         * ///// RESULTS ///
         * - This time, we get the correct behavior
         *      - Insert Song Exception
         *      - We get Performing Rollback
         *      - And Resetting commit behavior
         * - We'll still doing auto-commit to true, but the rollback() was called prior to that, and we should find
         *   that changes weren't added to the database this time
         *
         * - And this time the artist, album or the song were neither added
         *
         *
         * ///// FIX THE ERROR ////
         * - Re-run to make sure things are still working as they should
         * - Change the index back from 7 to 3
         */


       // datasource.insertSong("Touch of Grey","Grateful Dead","In The Dark",1);
       // datasource.insertSong("Like a Rolling Stone","Bob Dylan","Bob Dylan's Greatest Hits",5);
        datasource.insertSong("Bird Dog","Everly Brothers","All-Time Greatest Hits",7);

        /*
         * Close the connection
         */
        datasource.close();
    }
}
