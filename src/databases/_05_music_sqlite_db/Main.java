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
 *
 *
 *
 *
 * ///////////////////////////////
 * Query Artist for Song Method - queryArtistsForSong(String songName , int sortOrder)
 * ///////////////////////////////
 *
 * As mentioned earlier in the last video, we're going to redefine the queryAlbumsForArtist() even further and it
 *  would be nicer if we could somehow have a place holder in the SQL statement for the artist's name
 * And this would be dynamically replaced every time we performed a query rather than having to append the artist's
 *  name to the statement, but hold that thought for a future video in this section
 *
 * Let's now do 1 of the queries that we expect our application would want to perform
 * Let's add a method that would return which artist recorded a particular song
 * One wrinkle here is that we'd want to return the artist name, the album name and the track no for the song in the
 *  album
 * To do that we'd need to create a class in our Model that would return this 3 values - SongArtist
 *
 * SongArtist : class
 *
 *      artistName : String
 *      albumName : String
 *      track   : int
 *
 *      getArtistName : String
 *      getAlbumName : String
 *      getTrack : int
 *
 *      setArtistName(String artist) : void
 *      setAlbumName(String album) : void
 *      setTrack(in track) : void
 *
 * Let's now go ahead and test our SQL statement that we're going to write with DB Browser
 * We want to query artist for 3 songs
 *  - Heartless
 *  - She's On Fire
 *  - Go You Own Way
 *
 * Separate SQL statements
 *      SELECT * FROM songs WHERE title = 'Heartless';  --album = 308
 *      SELECT * FROM albums WHERE album = 308;         --artist = 39
 *      SELECT * FROM artists WHERE _id = 39;           --name = Heart
 *
 * Repeat the same SELECT above for the second song
 *      SELECT * FROM songs WHERE title = 'She's On Fire';  --album = 248
 *      SELECT * FROM albums WHERE album = 248;             --artist = 152
 *      SELECT * FROM artists WHERE _id = 152;              --name = Aerosmith
 *
 * Repeat the same SELECT above for the 3rd song
 *      SELECT * FROM songs WHERE title = 'Go Your Own Way';  --album = 416
 *      SELECT * FROM albums WHERE album = 416;               --artist = 92
 *      SELECT * FROM artists WHERE _id = 92;                 --name = Fleetwood Mac
 *
 * We now know that the SQL is right and we're able to get the info we need
 * To achieve what we want though, we'll need to create an INNER JOIN on those various tables to get the info
 * Create another tab and create the SQL below
 *
 *      SELECT artists.name , albums.name, songs.track FROM songs
        INNER JOIN albums ON songs.album = albums._id
        INNER JOIN artists ON albums.artist = artists._id
        WHERE songs.title = "Go Your Own Way"
        ORDER BY artists.name , albums.name COLLATE NOCASE ASC;
 *
 * Now that the above is working, we can now swing back to our Datasource and translate that into java code
 * We'll start by adding the static parts of the SQL statement and the only varying parts are the song title and
 *  sort oder and we'll do something similar to what we did when finding the albums for an artist
 * We'll have a constant for the part of the statement that occurs before the song title and a constant for the
 *  sort order
 *
 *       public static final String QUERY_ARTISTS_FOR_SONG_START =
            "SELECT "+ TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME +", "+
                    TABLE_ALBUMS+ "."+ COLUMN_ALBUM_NAME +", "+
                    TABLE_SONGS+ "."+ COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN "+ TABLE_ALBUMS +" ON "+
                            TABLE_SONGS +"."+COLUMN_SONG_ALBUM + "="+ TABLE_ALBUMS +"."+COLUMN_ALBUM_ID +
                    " INNER JOIN "+ TABLE_ARTISTS +" ON "
                            + TABLE_ALBUMS +"."+COLUMN_ALBUM_ARTIST + "="+ TABLE_ARTISTS +"."+COLUMN_ARTIST_ID+
                    " WHERE "+ TABLE_SONGS + "."+ COLUMN_SONG_TITLE +" =\"";
 *
 * Then we add our Sort oder Constant
 *
 *      public static final String QUERY_ARTISTS_FOR_SONG_SORT =
            " ORDER BY "+ TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME + ", "+
                    TABLE_ALBUMS+ "."+ COLUMN_ALBUM_NAME +" COLLATE NOCASE ";
 *
 *
 * Now we need to write queryArtistsForSong() which will be very similar to the method that queries the albums for
 *  an artist
 * However in this case we're going to return a List<SongArtist> objects
 * Also we can't use our column index constants because they're returning columns from different tables
 * We're okay to hard code the indices, because we can be sure that the order of the columns returned for this query
 *  will always be the same even if the column position changes within the tables
 * We've specified the columns that we want and their order in the query
 *
 * So let's now add queryArtistsForSong(String songName , int sortOrder)
 *  - Takes 2 parameters : songName and sortOrder value
 *  - Returns List<SongArtist>

 *  - Create a StringBuilder obj and pass QUERY_ARTISTS_FOR_SONG_START constant
 *  - Append songName passed as a parameter to this method
 *  - Escape the closing double quotes
 *
 * Then append QUERY_ARTISTS_FOR_SONG_SORT constant if one is passed other than the ORDER_BY_NONE constant
 *      - sorts by artist name, albums name too
 * Order by DESC if the constant matches the ORDER_BY_DESC constant , otherwise, Order by ASC
 *
 *       if (sortOrder != ORDER_BY_NONE){
            sb.append(QUERY_ARTISTS_FOR_SONG_SORT);
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }
 *
 * Print out the SQL statement, checks to see if there's any problems with it
 *
 * Next, we need to use try-with resources and query the database
 *  - Initialize Statement obj from the Connection instance
 *  - Create a ResultSet obj by calling executeQuery(sb.toString()) on Statement instance and pass our query String
 *     that we build with StringBuilder class
 *
 *  - Initialize an ArrayList of SongArtist objects  - songArtists
 *  - loop through the resultSet.next()
 *      - Create a SongArtist object : songArtist
 *          - call setArtistName() on songArtist obj & pass name of the artist obtained by resultSet.getString(1)
 *          - call setAlbumName() on songArtist obj & pass name of the album obtained by resultSet.getString(2)
 *          - call setTrack() on songArtist obj & pass track no obtained by calling resultSet.getInt(3)
 *
 *      - add songArtist obj it to the songArtists arrayList
 *
 *  - return songArtists array list
 *
 *
 * Let's now call queryArtistsForSong() from the main() to get the artists for one of these songs
 * If songsByArtist is empty
 *  - print could'nt find artists for the song
 *
 *      List<SongArtist> songsByArtist = datasource.queryArtistsForSong("Go Your Own Way",Datasource.ORDER_BY_ASC);
        if (songsByArtist.isEmpty()) {
            System.out.println("Could not find the artist for the song!");
            return;
        }
 *
 * Otherwise, loop and print the artist , album and the track no of the song
 *
        for (SongArtist songByArtist : songsByArtist) {
            System.out.println("Artist Name = "+songByArtist.getArtistName() + ", Album Name = "+ songByArtist.getAlbumName()+
                    ", Track no = "+songByArtist.getTrack());
        }
 *
 *
 * And if we run this, we get the results that we should het for the other songs as well
 * i.e "Heartless" and "She's On Fire"
 *
 * Those are all the queries we're going to code for now
 * Doing the others will just be a matter of defining the static constants and then building up the String in a new
 *  method
 * Depending on the query, we may have to create a new class to hold the results
 * We didn't do any queries that contains wild cards but as we've seen when we do a query, it's just a matter of
 *  writing a SQL statement, testing it against a database and then building the query string using a StringBuilder
 *  and Constants
 * Then we pass it to the statement.executeQuery() or statement.execute()  and process our results with the ResultSet
 *  class
 *
 *
 *
 * ///////////////////////////////
 * ResultSet Meta Data - querySongsMetadata()
 * ///////////////////////////////
 *
 * When we were working with the command line, we're able to query the schema from the tables in the database
 * But when using JDBC, this is one area where the support for this and sometimes the way we do it can be different
 *  depending on the database
 * Unfortunately, SQLite doesn't have good support for querying the database schema
 * The Connection class has a getMetaData() that is supposed to return info about the database tables
 * But when using this method with a SQLite database, unfortunately the return object doesn't have any of it's
 *  fields set and they're all null
 * What we can do though is we can query metadata using the result set from a query
 * Often this is sufficient because we're usually interested in the definition of a specific table
 * For example, we may want to get the column index for a value or find out what the the column names are for a
 *  table
 *
 * Let's add a method to the Datasource class to do a general query on the songs table and then we'll look at the
 *  metadata, that comes back
 *
 *      String sql = "SELECT * FROM "+ TABLE_SONGS;
 *
 *      Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)
 *
 *      ResultSetMetaData meta = resultSet.getMetaData();
        int numOfColumns = meta.getColumnCount();
 *
 *      for (int i = 1; i < numOfColumns; i++)
               System.out.format("Column %d in the songs table is named %s\n", i , meta.getColumnName(i));
 *
 * We're performing a query and then we call resultSet.getMetaData() to get the schema info from the table
 * We get the column count and then use the loop to print each column name
 * Notice we initialize i to 1 because the first column is at position 1 and not position 0
 * Using ResultSetMetaData, we can get info such as column names and types and their attributes, in other words
 *  know whether they're nullable etc
 *
 * Let's now call querySongsMetadata() from the main()
 *
 *      datasource.querySongsMetadata();
 *
 *      - and we're able to print the column names for the songs table using meta.getColumnName(int index)
 *
 * In relation to the other types of information that we can look at, check oracle's documentation
 * And we can see there's quite a few methods there that do various things
 * And so it's worthwhile checking them
 *
 * For example, if we're in a situation where we didn't know the names of the columns in the table, or how
 *  many they were, we can do a query that doesn't require that information and then get it from the ResultSet
 * But at least we need to know the name of the table which we've done in this scenario that we've just coded
 *
 * As mentioned, this is 1 area , getting schema info where the support and the way of getting it can differ across
 *  databases
 *
 *
 *
 *
 *
 * //////////////////////
 *  Functions  - getCount(String table):int
 *  and
 *  Views
 * //////////////////////
 *
 * SQL Functions : Count() , MIN() , MAX() , SUM()
 *
 * As we've seen in previous videos, when we want to perform a query, we build up a query string and then we pass it
 *  to statement.executeQuery()
 * The same applies when our query contains functions, but how do we get into the result
 *
 * For example:
 *      SELECT COUNT(*) FROM songs;
 *
 * So how do we get count from the resultSet of the above query ?
 *
 * Let's take a look at how we can achieve this by adding a method in our Datasource class
 *
 * Create method : getCount(String table) : int
 *
 *      String sql = "SELECT COUNT(*) FROM "+ table;
 *      Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(sql)
 *
 *      int count = results.getInt(1);
        int min = results.getInt(2);
 *
        return count;
 *
 *      - sql - count the number of records in the table passed to this method
 *      - process query by creating a Statement obj and pass sql statement to statement.executeQuery(String sql)
 *
 *      - process ResultSet the same way as you would but by calling the appropriate getter methods
 *      - To get the result, we treat the function result as a column
 * Since we're only asking for the count, the result will be at column 1
 *
 * //// Call getCount(String table)
 * Let's call this method now from the main() and print the number of records returned from the table passed
 *
 *      int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);
 *
 * And if we run this we get, the number of songs returned to be 5350
 * The fact that our Constant has table in it, doesn't break the separation between our model code and the rest
 *  of the application
 * Nothing about it forces main() to know that the data is stored in a database
 *
 * Let's change the getCount() to do a bit more
 * Let's also try to retrieve the minimum song value
 *
 * Update our SELECT statement as follows
 *
 *      String sql = "SELECT COUNT(*), MIN(_id) FROM "+ table;
 *
 * Then we'll need to retrieve the min value as follows
 *
 *      int min = resulSet.getInt(2);
 * So we're getting our min result here and that's column 2 and extracting it into our min integer variable
 *
 * And now that we can't return 2 integers, let's just print them out
 *
 *      System.out.printf("Count = %d , Min = %s\n",count , min);
 * And now if we run this, we get count = 5350, and min= 1 , and this is true since the minimum value is 1 which
 *  would be correct because that's the first entry in the table
 *
 * ///// Aliases
 * This works but is a good practice to assign names as the resulting columns using AS
 * Modify our sql statement to use Aliases as follows
 *
 *      String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM "+ table;
 *
 * And now we need to call an overloaded method and pass the aliases instead of the column indices
 *
 *      int count = results.getInt("count");
        int min = results.getInt("min_id");
 *
 * And if we run this, we get the same results
 * So if we wanted alter the query possibly to return more results, and the column positions of the resulting values
 *  change, we won't have to change the getter calls
 *
 *
 *
 *
 * /////////////////////////////
 * Creating and Querying Views
 * /////////////////////////////
 *
 *
 * Let's take a look at creating and querying views
 * There's really nothing about working with views using JDBC
 * When we need to use views from an application , it's possible that the view will ship with the database.
 * In addition, we don't want to create the view everytime the application runs
 * Let's create the same view we created when working from the command line the one that contains the following columns , artist , album
 *  track and title
 *
 * When the user wants to know the artist for a song we can query the view instead of a JOIN
 *
 * Write the SQL to create the view and execute the SQL to create the view
 *
 *   CREATE VIEW IF NOT EXISTS artist_list AS
     SELECT artists.name , albums.name AS album, songs.track, songs.title FROM songs
     INNER JOIN albums ON songs.album = albums._id
     INNER JOIN artists ON albums.artist = artists._id
     WHERE songs.title = "Go Your Own Way"
     ORDER BY artists.name , albums.name , songs.track;
 *
 * Take note that we're using IF NOT EXISTS, and SQL will only create the view if it doesn't already exist and thta's how we would normally create
 *  it
 * Let's now create in Java code in the Datasource class
 * We'll add this SQL statement using constants
 *
 * First
 *  - Define the name of the view using a constant
 *
 *       public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
 *
 *  - The add CREATE VIEW script using a constant
 *
 *       public static final String CREATE_ARTIST_FOR_SONG_VIEW =
            "CREATE VIEW IF NOT EXISTS "+ TABLE_ARTIST_SONG_VIEW +" AS SELECT "+
                    TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME + ", "+
                    TABLE_ALBUMS +"."+ COLUMN_ALBUM_NAME+ " AS album, "+
                    TABLE_SONGS +"."+ COLUMN_SONG_TRACK + ", "+
                    TABLE_SONGS + "."+ COLUMN_SONG_TITLE+
            " FROM "+ TABLE_SONGS +
            " INNER JOIN "+ TABLE_ALBUMS + " ON "+
                    TABLE_SONGS +"."+ COLUMN_SONG_ALBUM +" = "+ TABLE_ALBUMS +"."+COLUMN_ALBUM_ID +
            " INNER JOIN "+ TABLE_ARTISTS + " ON "+
                    TABLE_ALBUMS +"."+ COLUMN_ALBUM_ARTIST +" = "+ TABLE_ARTISTS +"."+COLUMN_ARTIST_ID +
            " ORDER BY "+ TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME + ", "+ TABLE_ALBUMS +"."+ COLUMN_ALBUM_NAME+
            ", "+ TABLE_SONGS +"."+ COLUMN_SONG_TRACK;
 *
 *  - Then add a method for creating the view: createViewForSongArtists
 *
 *      Statement statement = conn.createStatement();
 *      statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
 *
 * We're returning true to the caller to let them know that the VIEW was successfully created and false if otherwise
 *
 *
 * ////////
 * Then call createViewForSongArtists() from the main() as follows
 *
 *      if (datasource.createViewForSongArtists())
            System.out.println("artist_list View Created..");
 *
 * Print out to the user if the VIEW is successfully created
 * Run and confirm with the DB Browser for SQLite that the VIEW was created
 *  - Check that it still has the data
 *
 * Re-run it again, and we still don't get an error, since the VIEW already exist, our SQL statement can only create one if it doesn't exist
 *
 *
 *
 * //////////////////
 *  Querying Views
 * /////////////////
 *
 * Now that we've created artist_list VIEW , we can actually change the queryArtistForSong() and query using the view instead if we wanted to
 * But we won't do that, but rather create a new method for this
 * The user of the Datasource class could fall back to using the queryArtistForSong() if the creation of the view fails or calling the new method
 *  results in an error
 * We can query a view just as we'd do with a table
 * In terms of querying a view we can use the following SQL statement
 *
 *      SELECT name,album,track FROM artist_list WHERE title = "Go Your Own Way";
 *
 *      - And if we run this against the db, we get 4 rows returned , 4 albums by Fleetwood Mac
 *      - This time they're ordered by album title, because we ordered the records that way when we created the view
 *
 * Let's start writing the Java code for this query, again using the constants
 *
 *       public static final String QUERY_VIEW_SONG_INFO =
            "SELECT "+ COLUMN_ARTIST_NAME +" ,"+ COLUMN_SONG_ALBUM +", "+
                        COLUMN_SONG_TRACK +" FROM "+ TABLE_ARTIST_SONG_VIEW +
            " WHERE "+ COLUMN_SONG_TITLE  +"=\"";
 *
 * Then write a method for querying from view : querySongInfoView(String title)
 *      - Takes song title and searches the songs via song title
 *      - Returns a List<SongArtist> objects
 *
 * Create a StringBuilder obj and append the title parameter
 *
 *       StringBuilder sb = new StringBuilder(QUERY_VIEW_SONG_INFO);
         sb.append(title);
         sb.append("\"");
 *
 * Create an ArrayList that's going to hold a List<SongArtist> objects
 *
 *      List<SongArtist> songArtistsList = new ArrayList<>();
 *
 * Create a Statement obj and pass the query string to executeQuery
 *
 *      Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sb.toString())
 *
 * Process the ResultSet using a loop
 *      - Create a SongArtist obj : songArtist
 *          - call setArtistName() on songArtist obj & pass name of the artist returned by resultSet.getString(1)
 *          - call setAlbumName() on songArtist obj & pass name of the album returned by resultSet.getString(2)
 *          - call setTrack() on songArtist obj & pass track no returned by resultSet.getInt(3)
 *
 *      - Add songArtist object to songArtistsList ArrayList
 *
 *      - Return songArtistsList ArrayList , otherwise , return null
 *
 * ///////
 * The only real difference between querySongInfoView(String title) and queryArtistForSong() is the query string we're using
 *
 * //////
 * Call querySongInfoView(string title) from the main()
 *
 *      List<SongArtist> songsForArtistViewList = datasource.querySongInfoView("She's On Fire");
        if (songsForArtistViewList.isEmpty()) {
            System.out.println("Couldn't find the artist for the song specified");
            return;
        }
        for (SongArtist songForArtist : songsForArtistViewList) {
            System.out.println("FROM VIEW - Artist = " + songForArtist.getArtistName() + " ,Album = " + songForArtist.getAlbumName()
                    + " ,Track = " + songForArtist.getTrack());
        }
 *
 *      - check if songsForArtistViewList arraylist is empty and if so, print out the user
 *          - This is because we always create the list in the data source methods
 *
 *      - Otherwise, loop through the array list and print the results to the console
 *
 * Run and test with different songs :
 *      - "She's On Fire"
 *      - "Go Your Own Way"
 *      - "Heartless
 *
 *
 * ///////////////////////////////
 * ///////////////////////////////
 *  NOT - VERY IMPORTANT
 * ///////////////////////////////
 * ///////////////////////////////
 *
 * Our code is decent at the moment but there's one more important improvement we can make
 * The way we're querying the database now isn't best practice.
 * SQL statements have to be compiled into a form that the database understands
 * When we use a Statement obj to perform queries, the statements are compiled everytime we perform a new query
 * In this little application, it doesn't matter but if we were using Statement objects in an enterprise application, servicing thousands
 *  of connections, then it would definitely impact performance
 * Also the way that we're building our query strings, makes our database vulnerable to hacking attempts
 *
 *
 *
 *
 *
 *
 * ///////////////////////////////////////////////
 *  SQL Injection Attacks and Prepared Statements
 * ///////////////////////////////////////////////
 *
 * Tim alluded at the end of the last video that the way we're building our query strings makes our database vulnerable to hacking attempts
 * Let's go ahead and demonstrate this
 *
 * Rather than hard coding the title of the song to querySongInfoView(String title) method, we're going to let the user enter a song title in
 *  the console using a Scanner class
 * Then we'll call the querySongInfoView() to get the information for the title
 * We'll prompt the user for a song title and pass the input to querySongInfoView(String title)
 *
 *      Scanner scanner = new Scanner(System.in);
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
        }
 *
 * Run this and enter "Go Your Own way" and confirm we get our results back as we'd expect , "Fleetwood Mac" and the 4 albums the particular
 *  song is on
 *
 * Now we'll do something different and illustrate how we can get into trouble
 * Re run and write below :
 *      Go Your Own Way" or 1=1 or "
 *
 *      - returns all the records from the artist_list view
 *
 * The SQL statement executed is as follows:
 *
 *      SELECT name ,album, track FROM artist_list WHERE title="Go Your Own Way or 1=1 or "";
 *
 * So what, we've done is we have performed what's known as a SQL Injection Attack
 * Perhaps it doesn't matter in this case, if a user gets to see all the songs in a view, but what happens if this was a view or table of
 *  user ids and passwords for example, or it contained credit card details or rather sensitive info or even medical info
 * That wouldn't be a good thing and how did this actually happen
 *
 * When we built the SQL statement, we're blindly concatenating whatever the users typed
 * So when we entered the above string, the code built the following SQL statement below
 *
 *      SELECT name ,album, track FROM artist_list WHERE title="Go Your Own Way or 1=1 or "";
 *
 * Since 1=1 will be true for every record, all the records in the artist_list view are returned
 * This is known as a SQL Injection Attack because a user has injected SQL into the statement that we didn't intend to run
 *
 * The user in this scenario would normally be a hacker, because they know something about how applications that use databases are coded
 * They guess that we'd be concatenating the name into a WHERE clause as we're doing here and since it's a String , they guess that we're
 *  concatenating a closing quote which is why they end their input String with a double quote
 *
 * A malicious user can even do something like DROP tables
 * Since we're using JDBC , we do have some protection against that at least when using the SQLite driver, the execute() and executeQuery()
 *  won't run more than a single SQL statement
 *
 * In other words, a user can't tack on something like ; DROP TABLE songs
 *
 * But if an application written in a language like perhaps PHP was running SQL directly based on user input rather than going through an API,
 *  then a malicious user would be able to execute whatever SQL statements they wanted
 *
 * So, instead of building up query strings and using the statement class to execute them, we should really be using the prepared statements
 *  class
 * PreparedStatements can help prevent SQL Injection Attacks, because when we use them, we don't concatenate user input into the SQL statement
 *  that will ultimately be running
 * As we've noted, the only thing that changes between queries is the song title
 *
 * It would be nice if we could have placeholders in SQL statements for the values that change from query to query and PreparedStatements
 *  allow us to do just that
 *
 * ////// Implementation
 *
 * We'll add a Constant that queries from the VIEW, that will ultimately use PreparedStatement
 *
 *   public static final String QUERY_VIEW_SONG_INFO_PREP =
            "SELECT "+ COLUMN_ARTIST_NAME +" ,"+ COLUMN_SONG_ALBUM +", "+
                    COLUMN_SONG_TRACK +" FROM "+ TABLE_ARTIST_SONG_VIEW +
                    " WHERE "+ COLUMN_SONG_TITLE  +"= ?";
 *
 *
 * The only difference between this constant and the one we used before is that we're using a question mark (?) for the song title
 * This is the placeholder character that we use in Prepared Statements
 * When we're ready to perform the query, we'll replace the placeholder with the actual title we want to query
 *
 * This is what basically we've created, with the constant line above
 *
 *      SELECT name , album , track, title FROM artist_list WHERE title = ?;
 *
 * Note that importantly, we don't put quotation marks around the song title
 * When replacing the placeholder in a Prepared Statement, the database understands that a String like "Go Your Own Way" is one value
 * So we only substitute a single value for each placeholder
 *
 * We'll also need to declare an instance variable for the PreparedStatement and that's because we only want to create it once
 * We don't want to create it everytime we query because we only want it to be pre-compiled once
 * If we were to create a new instance every time we did a query, we'd lose the performance benefit that the PreparedStatement comes with
 *
 * So let's add a Prepared Statement Instance variable
 *
 *      private PreparedStatement querySongInfoView;
 *
 * Then we'll need to initialize it in the open() and pass the Query Constant to prepareStatement() on the Connection instance
 *
 *      querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
 *
 * We could check for null in the querySongInfoView(), and create the instance there
 * But instead we'll create the instance in the open()
 *
 * We call the connection.prepareStatement() to create that instance of Prepared Statement passing at the SQL statement we want to execute
 * And that SQL remember contains the placeholders that will be replaced everytime we use the statement to make a query
 *
 *
 *
 * Create a method that uses PreparedStatement : querySongInfoViewPrepStatement
 *
 *      querySongInfoView.setString(1, title);
        ResultSet resultSet = querySongInfoView.executeQuery();
 *
 * And now we don't need the StringBuilder anymore
 * We're using PreparedStatement instance to call setString(1,title) and set the title as the first parameter in the query for PreparedStatement
 * And since we're passing title as String to this method, we're using the setString()
 * There are other methods that we can use e.g. setInt depending on the type of variable you're using
 *
 * We also have to specify the position of the placeholder we want to replace because there can be more than 1
 *
 * As usual, when working with JDBC, the position is 1-based
 *
 * Note that the prepared statement below, is a subclass of the Statement
 *
 *      querySongInfoView.setString(1, title);
        ResultSet resultSet = querySongInfoView.executeQuery();
 *
 * In other words, it's got all the other methods that the Statement class has such as execute() , executeQuery() and that's why we're able to
 *  use executeQuery()
 *
 *
 * Closing Resources
 * Now that we're not declaring resources with querySongInfoViewPrepStatement() , we'll need a finally clause to close them
 * Tim said that if we close the statement, any associated ResultSte is also closed and there can only be 1 active result set associated with
 *  a statement
 * If we use a statement to do more than 1 query, which is very common with prepared statements , each time we need to do a new query, the existing
 *  result set is closed and a new one is created
 * So, the upshot of all this is that we don't have to worry about closing the result set, when we're using the PreparedStatement
 * When we close the PreparedStatement whichever ResultSet is active will also be closed
 * And we should also close the prepared statement in the close() as opposed to closing it in the querySongInfoViewPrepStatement()
 *
 * Add below to close()
 *
 *       if (querySongInfoView != null){
                querySongInfoView.close();
 *          }
 *
 * ////
 *
 * Call querySongInfoViewPrepStatement(String title) from the main()
 *
 *      Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();
 *
 *      List<SongArtist> songsForArtistViewListPrepStatement = datasource.querySongInfoViewPrepStatement(title);
        if (songsForArtistViewListPrepStatement.isEmpty()) {
            System.out.println("Couldn't find the artist for the song specified");
            return;
        }
        for (SongArtist songForArtist : songsForArtistViewListPrepStatement) {
            System.out.println("FROM VIEW - Artist = " + songForArtist.getArtistName() + " ,Album = " + songForArtist.getAlbumName()
                    + " ,Track = " + songForArtist.getTrack());
        }
 *
 *
 * Test with different test cases and confirm everything is working
 *
 * But most importantly, let's run it again and presume that this time we're a hacker and we'll try to enter that same code we did last time
 * This time we get a different message : "Couldn't find the artist for the song" which means no data was returned
 *
 * ///// Explanation
 * But how did the PreparedStatement protect the database against SQL Injection Attack ?
 *
 * If the String is simply being substituted for the question mark (?), wouldn't we see the same results as before ?
 *
 * Well, we don't and that's because when we're using a PreparedStatement, the values being substituted are treated as literal values
 *      - In other words, nothing within the value is treated as SQL
 *
 * When we're using a StringBuilder to build the query statement and concatenating the title, effectively this is what is passed to the
 *  database and got executed
 *
 *      SELECT name ,album, track FROM artist_list WHERE title="Go Your Own Way" or 1=1 or "";
 *
 * But in the second scenario, with prepared statement , this is what is sent to the server
 *
 *       SELECT name ,album, track FROM artist_list WHERE title="Go Your Own Way or 1=1 or "";
 *
 * And we can se the differences here, in the 2nd scenario, whatever we have typed in, is basically substituted for the Song title
 * It's really the double quotes in between that is making all the difference, and the database are being searched for a song that
 *  equals that in it's entirety
 * And since there's no song on the databases with the title "Go Your Own Way or 1=1 or "" , no records were returned
 *
 * In other words, a malicious user can't inject SQL into the statement
 * Because anything substituted as a placeholder is treated as a single literal value and won't be interpreted as SQL
 *
 */

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtist(Datasource.ORDER_BY_NONE);
        if (artists.isEmpty()) {
            System.out.println("No artists!");
            return;
        }
        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        System.out.println("_".repeat(50));

        List<String> albums = datasource.queryAlbumsForArtist("Iron Maiden", Datasource.ORDER_BY_ASC);
        //albums = datasource.queryAlbumsForArtist("Pink Floyd",Datasource.ORDER_BY_DESC);
        //albums = datasource.queryAlbumsForArtist("Carole King",Datasource.ORDER_BY_NONE);

        if (albums.isEmpty()) {
            System.out.println("No albums!");
            return;
        }
        albums.forEach(System.out::println);

        System.out.println("_".repeat(50));

        List<SongArtist> songsByArtist = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if (songsByArtist.isEmpty()) {
            System.out.println("Could not find the artist for the song!");
            return;
        }
        for (SongArtist songByArtist : songsByArtist) {
            System.out.println("Artist Name = " + songByArtist.getArtistName() + ", Album Name = " + songByArtist.getAlbumName() +
                    ", Track no = " + songByArtist.getTrack());
        }

        /* Get songs table metadata */
        System.out.println("_".repeat(50));
        datasource.querySongsMetadata();

        /* COUNT(*) FUNCTIONS - count number of songs in specified tables */
        System.out.println("_".repeat(50));
        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);



        /* Create VIEW - artist_list */
        System.out.println("_".repeat(50));
        if (datasource.createViewForSongArtists())
            System.out.println("artist_list View Created..");

        /* Query VIEW - artist_list */
        System.out.println("_".repeat(50));
        List<SongArtist> songsForArtistViewList = datasource.querySongInfoView("She's On Fire");
        if (songsForArtistViewList.isEmpty()) {
            System.out.println("Couldn't find the artist for the song specified");
            return;
        }
        for (SongArtist songForArtist : songsForArtistViewList) {
            System.out.println("FROM VIEW - Artist = " + songForArtist.getArtistName() + " ,Album = " + songForArtist.getAlbumName()
                    + " ,Track = " + songForArtist.getTrack());
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
        if (songsForArtistViewListPrepStatement.isEmpty()) {
            System.out.println("Couldn't find the artist for the song specified");
            return;
        }
        for (SongArtist songForArtist : songsForArtistViewListPrepStatement) {
            System.out.println("FROM VIEW - Artist = " + songForArtist.getArtistName() + " ,Album = " + songForArtist.getAlbumName()
                    + " ,Track = " + songForArtist.getTrack());
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
        datasource.insertSong("Bird Dog", "Everly Brothers", "All-Time Greatest Hits", 7);

        /*
         * Close the connection
         */
        datasource.close();
    }
}
