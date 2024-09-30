package databases._05_music_sqlite_db.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Datasource Class
 *
 *
 * /////////////////
 * Define Constants
 * /////////////////
 *
 * Add CONSTANTS for :
 *      - Database
 *      - Connection String
 *      - tables
 *      - column names
 *
 * We'll keep all our database code within the model package
 * Instead of connecting to the database directly from the main(), let's create a method within the datasource to do that
 * We need to create a Connection instance variable because we'll want the user of the Datasource class to call a close() once they're
 *  done working with the database
 *
 * /////////////////////////////
 * Create a Connection Instance
 *
 *      private Connection conn;
 *
 *
 *
 * /////////////////////////////
 *  Open - Establish connection
 * /////////////////////////////
 *
 * open() : boolean
 *
 * We can use try-with-resources but we can as well use the traditional try-catch-finally so that we can get ourselves into the habit of
 *  closing the resources manually
 *
 * Call getConnection(CONNECTION_STRING) on the DriverManager class and pass the CONNECTION_STRING constant
 *  - Return true if a connection is established successfully
 *
 * Catch any SQLException and print something to the user to let them know that the connection was not successful
 *  - Return false if otherwise
 *
 *
 *
 *
 * ///////////////////////////////
 *  Close - Disconnect connection
 * //////////////////////////////
 *
 * close() : void
 *
 * Close the Connection instance
 *  - Only if it's not NULL , so that we don't get a NullPointerException
 *  - Call close() on the Connection instance
 *
 * Handle SQLException raised by the close()
 */


public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\JMC17\\Java-Masterclass-11\\"+ DB_NAME;

    // albums table
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    //albums table - Columns Indices
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    //artists table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    //artists table - Columns Indices
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    //songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    //artists table - Columns Indices
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    // sorting
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    //Query albums by Artist Constant
    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT "+ TABLE_ALBUMS + "."+ COLUMN_ALBUM_NAME +" FROM "+ TABLE_ALBUMS +
                    " INNER JOIN "+ TABLE_ARTISTS +" ON "+ TABLE_ALBUMS +"."+ COLUMN_ALBUM_ARTIST +
                    " = "+ TABLE_ARTISTS +"."+COLUMN_ARTIST_ID+
                    " WHERE "+ TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME + " = \"";

    // Order by Constant
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY "+ TABLE_ALBUMS +"."+ COLUMN_ALBUM_NAME +" COLLATE NOCASE ";

    // Query artist,album, song track - for a certain song
    public static final String QUERY_ARTISTS_FOR_SONG_START =
            "SELECT "+ TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME +", "+
                    TABLE_ALBUMS+ "."+ COLUMN_ALBUM_NAME +", "+
                    TABLE_SONGS+ "."+ COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN "+ TABLE_ALBUMS +" ON "+
                            TABLE_SONGS +"."+COLUMN_SONG_ALBUM + "="+ TABLE_ALBUMS +"."+COLUMN_ALBUM_ID +
                    " INNER JOIN "+ TABLE_ARTISTS +" ON "
                            + TABLE_ALBUMS +"."+COLUMN_ALBUM_ARTIST + "="+ TABLE_ARTISTS +"."+COLUMN_ARTIST_ID+
                    " WHERE "+ TABLE_SONGS + "."+ COLUMN_SONG_TITLE +" =\"";

    public static final String QUERY_ARTISTS_FOR_SONG_SORT =
            " ORDER BY "+ TABLE_ARTISTS +"."+ COLUMN_ARTIST_NAME + ", "+
                    TABLE_ALBUMS+ "."+ COLUMN_ALBUM_NAME +" COLLATE NOCASE ";

    // Create artist_list view
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    /*
     CREATE VIEW IF NOT EXISTS artist_list AS
     SELECT artists.name , albums.name AS album, songs.track, songs.title FROM songs
     INNER JOIN albums ON songs.album = albums._id
     INNER JOIN artists ON albums.artist = artists._id
     WHERE songs.title = "Go Your Own Way"
     ORDER BY artists.name , albums.name , songs.track;
     */
    public static final String CREATE_ARTIST_FOR_SONG_VIEW =
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

    // Query artist_list VIEW
    //SELECT name , album , track, title FROM artist_list WHERE title = 'Go Your Own Way';
    public static final String QUERY_VIEW_SONG_INFO =
            "SELECT "+ COLUMN_ARTIST_NAME +" ,"+ COLUMN_SONG_ALBUM +", "+
                        COLUMN_SONG_TRACK +" FROM "+ TABLE_ARTIST_SONG_VIEW +
            " WHERE "+ COLUMN_SONG_TITLE  +"=\"";


    /*
     * Using Prepared Statements
     * - Use placeholders ? - this is what we use when we want to use PreparedStatements
     * - When we're ready to use the query, we'll replace the placeholder with the actual title we want to query
     * - This is what basically we've created:
     *      SELECT name , album , track, title FROM artist_list WHERE title = ?;
     * - With the constant below
     * - Note that we don't put quotation marks around the song title
     */
    public static final String QUERY_VIEW_SONG_INFO_PREP =
            "SELECT "+ COLUMN_ARTIST_NAME +" ,"+ COLUMN_SONG_ALBUM +", "+
                    COLUMN_SONG_TRACK +" FROM "+ TABLE_ARTIST_SONG_VIEW +
                    " WHERE "+ COLUMN_SONG_TITLE  +"= ?";

    /*
     * DB Transactions
     * Add the following INSERT CONSTANTS
     *  - Insert into artists
     *  - Insert into albums
     *  - Insert into songs
     *
     * Add PreparedStatement instance variables for the INSERT statements
     *
     * Add code to the open() to create the prepared statement instances for the INSERT CONSTANTS
     *  - Return generated keys after for the artists and albums table
     *
     * Close them prepared statements (INSERTS) in the close()
     */

    public static final String INSERT_ARTIST = "INSERT INTO "+ TABLE_ARTISTS +
            "("+ COLUMN_ARTIST_NAME +") VALUES(?)";

    public static final String INSERT_ALBUMS = "INSERT INTO "+ TABLE_ALBUMS +
            "("+ COLUMN_ALBUM_NAME + ", "+ COLUMN_ALBUM_ARTIST +") VALUES(?,?)";

    public static final String INSERT_SONGS = "INSERT INTO "+ TABLE_SONGS +
            "("+ COLUMN_SONG_TRACK + ", "+ COLUMN_SONG_TITLE + ", "+ COLUMN_SONG_ALBUM +") VALUES(?,?,?)";

    /*
     * BEFORE adding an artist into artists table
     *  - Query to see if the artist already exists
     *  - search by artist name and return the id for that artist if found
     *  - If it doesn't exist, we'll return the new id after the insert
     *
     * BEFORE adding an album into albums table
     *  - Query to see if the album already exists
     *  - search by album name and return the id for that album if found
     *  - If it doesn't exist, we'll return the new id after the insert
     *
     * BEFORE adding a song into songs table
     *  - Query to see if the song already exists
     *  - search by song's name and return the id for that song if found
     *  - If it doesn't exist, we'll return the new id after the insert
     */

    public static final String QUERY_ARTIST = "SELECT "+ COLUMN_ARTIST_ID +" FROM "+ TABLE_ARTISTS +
            " WHERE "+ COLUMN_ARTIST_NAME +" = ?";

    public static final String QUERY_ALBUM = "SELECT "+ COLUMN_ALBUM_ID +" FROM "+ TABLE_ALBUMS +
            " WHERE "+ COLUMN_ALBUM_NAME +" = ?";

    public static final String QUERY_SONG = "SELECT "+ COLUMN_SONG_ID +" FROM "+ TABLE_SONGS +
            " WHERE "+ COLUMN_SONG_TITLE +" = ?";


    /*
     * Declare an instance variable for the PreparedStatement, because we only want to create it once
     * We don't want to create it everytime we query, because we only want it to be pre-compiled once
     * If we were to create a new instance every time we did a query, we'd lose the performance benefit
     *  that the PreparedStatement has
     *
     * - Create the instance in the open()
     * - Note that we call conn.prepareStatement() to create that instance of PreparedStatement, and then passing
     *    it the SQL we want it to execute
     * - And also remember that the SQL contains placeholders that will be replaced everytime we use the statement
     *   to make a query
     * - Update the query() to use a PreparedStatement
     */


    // Initialize connection obj
    private Connection conn;
    private PreparedStatement querySongInfoView;

    //Add PreparedStatements instances for the 3 INSERTS
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    //Add PreparedStatements instances for the 3 QUERIES
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;
    private PreparedStatement querySong;

    //Initialize the PreparedStatements instances for the 3 INSERTS in the open()
    //Initialize the PreparedStatements instances for the 3 QUERIES in the open()
    /*
     *
     */
    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);

            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST,Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS,Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);

            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);
            querySong = conn.prepareStatement(QUERY_SONG);

            return true;
        }catch (SQLException exc){
            System.out.println("Couldn't connect to database : "+exc.getMessage());
            return false;
        }
    }

    /*
     * Close connection resources
     * Also close the PreparedStatement instance in the close()
     *  - all the underlying resultSet will also be closed automatically
     * The order is also important
     *  - need to close PreparedStatement first, then connection
     */

    public void close(){
        try{
            if (querySongInfoView != null)
                querySongInfoView.close();

            if (insertIntoArtists != null)
                insertIntoArtists.close();

            if (insertIntoAlbums != null)
                insertIntoAlbums.close();

            if (insertIntoSongs != null)
                insertIntoSongs.close();

            if (queryArtist != null)
                queryArtist.close();

            if (queryAlbum != null)
                queryAlbum.close();

            if (querySong != null)
                querySong.close();

            if (conn != null)
                conn.close();
        }catch (SQLException exc){
            System.out.println("Couldn't close connection "+exc.getMessage());
        }
    }

    // Query artists table - try-with-resources
    public List<Artist> queryArtist(int sortOrder){
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()){
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        }catch (SQLException exc){
            System.out.println("Query failed: "+exc.getMessage());
            return null;
        }
    }

/* SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id
   WHERE artists.name = "Carole King" ORDER BY albums.name COLLATE NOCASE ASC; */
    public List<String> queryAlbumsForArtist(String artistName , int sortOrder){

        /*StringBuilder sb = new StringBuilder("SELECT ");
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
        sb.append("\""); */

        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE){
            /*sb.append(" ORDER BY ");
            sb.append(TABLE_ALBUMS);
            sb.append('.');
            sb.append(COLUMN_ALBUM_NAME);
            sb.append(" COLLATE NOCASE "); */
            //Replace with ORDER BY Constant
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }
        System.out.println(sb.toString());

        try(Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sb.toString())){

            List<String> albums = new ArrayList<>();
            while (resultSet.next()){
                albums.add(resultSet.getString(1));
            }
            return albums;

        }catch (SQLException exc){
            System.out.println("Error retrieving albums");
            return null;
        }

    }

    /*
        SELECT artists.name , albums.name, songs.track FROM songs
        INNER JOIN albums ON songs.album = albums._id
        INNER JOIN artists ON albums.artist = artists._id
        WHERE songs.title = "Go Your Own Way"
        ORDER BY artists.name , albums.name COLLATE NOCASE ASC;

     */
    public List<SongArtist> queryArtistsForSong(String songName , int sortOrder){

        StringBuilder sb = new StringBuilder(QUERY_ARTISTS_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE){
            sb.append(QUERY_ARTISTS_FOR_SONG_SORT);
            if (sortOrder == ORDER_BY_DESC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {

            List<SongArtist> songArtists = new ArrayList<>();
            while (results.next()){
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        }catch (SQLException exc){
            System.out.println("Query failed: "+exc.getMessage());
            return null;
        }

    }

    /*
     * Query Songs table metadata
     * - Using ResultSetMetaData meta - we can get info such as column names and types and their attributes
     * - Whether they are nullable etc
     */
    public void querySongsMetadata(){
        String sql = "SELECT * FROM "+ TABLE_SONGS;

        try(Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(sql)){

            ResultSetMetaData meta = results.getMetaData();
            int numOfColumns = meta.getColumnCount();
            for (int i = 1; i < numOfColumns; i++) {
               System.out.format("Column %d in the songs table is named %s , holding data of type %s\n",
                       i , meta.getColumnName(i),meta.getColumnTypeName(i));
            }
        }catch (SQLException exc){
            System.out.println("Query failed: "+exc.getMessage());
        }
    }

    /*
     * count() - How to get a value from a function
     * - call getInt(columnIndex) and pass column index
     * - We treat the function result as a column
     * - Since we're only asking for the count, the result will be at column 1
     */
    public int getCount(String table){
        String sql = "SELECT COUNT(*), MIN(_id) FROM "+ table;
        // Using aliases
        //String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM "+ table;
        try(Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(sql)){

            /* Using column index */
            int count = results.getInt(1);
            int min = results.getInt(2);

            /* Alternatively we can use aliases
            int count = results.getInt("count");
            int min = results.getInt("min_id");*/


            System.out.printf("Count = %d , Min = %d\n",count , min);
            return count;
        }catch (SQLException exc){
            System.out.println("Query failed: "+exc.getMessage());
        }
        return -1;

    }

    /*
     * Create artist_list VIEW
     */
    public boolean createViewForSongArtists(){
        try(Statement statement = conn.createStatement()){

            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        }catch (SQLException exc){
            System.out.println("Create View failed: "+exc.getMessage());
        }
        return false;
    }

    /*
     * Query artist_list VIEW
     * SELECT name , album , track, title FROM artist_list WHERE title = 'Go Your Own Way';
     *
     */

    public List<SongArtist> querySongInfoView(String title){
        StringBuilder sb = new StringBuilder(QUERY_VIEW_SONG_INFO);
        sb.append(title);
        sb.append("\"");

        System.out.println(sb);

        List<SongArtist> songArtistsList = new ArrayList<>();
        try(Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sb.toString())){

            while (resultSet.next()){
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(resultSet.getString(1));
                songArtist.setAlbumName(resultSet.getString(2));
                songArtist.setTrack(resultSet.getInt(3));
                songArtistsList.add(songArtist);
            }
            return songArtistsList;

        }catch (SQLException exc){
            System.out.println("Querying View Failed "+exc.getMessage());
            return null;
        }
    }

    /*
     * Query artist_list VIEW - USING PREPARED STATEMENT
     * SELECT name , album , track FROM artist_list WHERE title = ?;
     *
     *
     * N/B : VERY IMPORTANT
     * When we're using a PreparedStatement, the values being substituted are treated as literal values
     * In other words, nothing within the value is treated as SQL
     * Effectively, this is what was passed and got executed
     *
     *      SELECT name ,album, track FROM artist_list WHERE title="Go Your Own Way or 1=1 or "";
     * Whatever we have typed in, is basically substituted for the Song title
     * And since there isn't song with the title "Go Your Own Way or 1=1 or" , no records are returned
     * A Malicious user can't inject SQL into the statement
     * Anything substituted as a placeholder is treated as a single literal value and won't be interpreted as SQL
     *
     *
     * When we're using a StringBuilder to build the query statement and concatenating the title, effectively
     *  this is what is passed to the database and got executed
     *
     *      SELECT name ,album, track FROM artist_list WHERE title="Go Your Own Way" or 1=1 or "";
     * It's really the double quotes in between that is making all the difference
     *
     * - close the prepared statement in the close()
     */
    public List<SongArtist> querySongInfoViewPrepStatement(String title){

        try{
            querySongInfoView.setString(1, title);
            ResultSet resultSet = querySongInfoView.executeQuery();

            List<SongArtist> songArtistsList = new ArrayList<>();
            while (resultSet.next()){
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(resultSet.getString(1));
                songArtist.setAlbumName(resultSet.getString(2));
                songArtist.setTrack(resultSet.getInt(3));
                songArtistsList.add(songArtist);
            }
            return songArtistsList;

        }catch (SQLException exc){
            System.out.println("Querying View Failed: "+exc.getMessage());
            return null;
        }
    }

    /*
     * Insert into Artists Table
     *  insertArtist(String name) : int
     *
     * Steps:
     *  - query to check if the artist already exists
     *      - if he exists,
     *          - return the id that we retrieve from the result set. since we no longer need to insert a duplicate
     *            because we found the artist on file
     *          - will always be on column 1 since we're only returning the artist id
     *      - if he doesn't exist
     *          - insert the artist
     *              - call insertIntoArtists.setString() with a placeholder index of 1 and pass the name variable
     *              - call insertIntoArtists.executeUpdate()
     *                  - note we're not calling execute(), as it returns a Boolean indicating what type of result
     *                     the executed SQL statement returned
     *                  - executeUpdate() returns the number of rows affected by the SQL statement that run
     *              - since we're inserting a single row, we're only expecting 1 record to be returned
     *                  - if it isn't, we're throwing an SQL exception, coz obviously something has gone wrong
     *      - IF we get to the other point , we know there was a successful insertion,
     *          - we call insertIntoArtists.getGeneratedKeys() and store that in the result set
     *          - check if there is a record by calling next() on ResultSet instance
     *              - retrieve the key via getInt(1) on the ResultSet instance
     *              - and the key will be at position 1, since we only expect 1 key to be returned
     *          - if no record was returned
     *              - throw an SQL exception
     *      - Finally returning that key to the caller
     *  - Also notice, we're throwing an SQL Exception in this method because we want the caller to handle these
     *  - And these makes things a little bit cleaner
     *
     */
    private int insertArtist(String name) throws SQLException{

        queryArtist.setString(1, name);
        ResultSet resultSet = queryArtist.executeQuery();
        // if a record was found - means artist already exists - we don't need to do anything
        // proceed with the insert in the else statement
        if (resultSet.next()){
            return resultSet.getInt(1);
        }else{
            // Insert the artist, since they're not on file
            insertIntoArtists.setString(1,name);
            int affectedRows = insertIntoArtists.executeUpdate(); // save and let us know how many rows inserted
            if (affectedRows != 1)
                throw new SQLException("Couldn't insert artist");
        }

        // Retrieve generated key after successful insertion
        ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
        if (generatedKeys.next())
            return generatedKeys.getInt(1);
        else
            throw new SQLException("Couldn't get _id for artist");

    }

    /*
     * The insertAlbum() is pretty much the same except that we'll change some variable names and messages
     * We also have to set 2 values in the prepared statements
     *  - we'll copy insertArtist() and change a few things
     */

    private int insertAlbum(String name, int artistId) throws SQLException{

        queryAlbum.setString(1, name);
        ResultSet resultSet = queryAlbum.executeQuery();
        // if a record was found - means album already exists - we don't need to do anything
        // proceed with the insert in the else statement
        if (resultSet.next()){
            return resultSet.getInt(1);
        }else{
            // Insert the album, since it's not on file
            insertIntoAlbums.setString(1,name);
            insertIntoAlbums.setInt(2,artistId);
            int affectedRows = insertIntoAlbums.executeUpdate(); // save and let us know how many rows inserted
            if (affectedRows != 1)
                throw new SQLException("Couldn't insert album");
        }

        // Retrieve generated key after successful insertion
        ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
        if (generatedKeys.next())
            return generatedKeys.getInt(1);
        else
            throw new SQLException("Couldn't get _id for album");

    }

    /*
     * public insertSong(title,artist,album,track) : void
     *
     * Check if the song already exist
     *  - don't do any processing, return
     *
     *
     * call insertArtist(artist) - to get the artistId
     *  - returns artist for existing/newly inserted, otherwise handle SQLException from here
     *
     * call insertAlbum(album, artistId) to get the album id
     *  - returns album id for existing/newly inserted or handle SQLException from here
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
     * ///// FIX THE ERROR ////
     * - Re-run to make sure things are still working as they should
     * - Change the index back from 7 to 3
     */

    public void insertSong(String title, String artist , String album ,int track){
        try{
            querySong.setString(1, title);
            ResultSet resultSet = querySong.executeQuery();
            // if a record was found - means album already exists - we don't need to do anything
            // proceed with the insert in the else statement
            if (resultSet.next()) {
                System.out.println("Song Already Exists, Duplicate not allowed!");
                return;
            }
            /*
             * start the transaction by turning off the auto-commit behaviour of the conn obj
             * default behavior is to commit every change, and the DB does that by running every update,delete and
                 insert statement as a transaction
             */
            conn.setAutoCommit(false);

            //get artistId for the artist passed to this method
            //will return artist id for an existing id, or the id for the newly inserted artist record
            int artistId = insertArtist(artist);

            //get albumId for the album passed to this method for a specific artist(passed artistId returned above)
            //will return album id for an existing id, or the id for the newly inserted album record
            int albumId = insertAlbum(album,artistId);

            //Set various filed for inserts : track, title, album
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2,title);
            insertIntoSongs.setInt(3,albumId);

            // Insert and return affected rows
            int affectedRows = insertIntoSongs.executeUpdate(); // save and let us know how many rows inserted

            // If we get 1 row affected, successful insertion, commit the transaction
            if (affectedRows == 1)
                conn.commit();
            else
                throw new SQLException("The Song insert failed");

        }catch (Exception exc){
            // If something goes wrong, call rollback , which rolls back changes of out transaction
            System.out.println("Insert song exception: "+ exc.getMessage());
            try{
                System.out.println("Performing rollback");
                conn.rollback();
            }catch (SQLException exc2) {
                System.out.println("Oh boy!, Things are really bad: " + exc2.getMessage());
            }
        }finally {
            // setting auto-commit to true to return to the default auto-commit behavior
            // done here, whether the transaction succeeds or fails
            try{
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            }catch (SQLException exc){
                System.out.println("Couldn't reset auto-commit! "+exc.getMessage());
            }
        }

    }


    // Query Artist table - try-catch-finally
   /* public List<Artist> queryArtist(){
        Statement statement = null;
        ResultSet results = null;
        try {
            statement = conn.createStatement();

            results = statement.executeQuery("SELECT * FROM "+TABLE_ARTISTS);

            List<Artist> artists = new ArrayList<>();
            while (results.next()){
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        }catch (SQLException exc){
            System.out.println("Query failed: "+exc.getMessage());
            return null;
        }finally {
            try{
                if (results != null)
                    results.close();
            }catch (SQLException exc){
                System.out.println("Error closing result set: "+exc.getMessage());
            }
            try{
                if (statement != null)
                    statement.close();
            }catch (SQLException exc){
                System.out.println("Error closing statement: "+exc.getMessage());
            }
        }
    } */
}
