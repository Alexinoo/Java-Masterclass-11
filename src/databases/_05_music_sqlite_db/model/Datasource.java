package databases._05_music_sqlite_db.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Initialize connection obj
    private Connection conn;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch (SQLException exc){
            System.out.println("Couldn't connect to database : "+exc.getMessage());
            return false;
        }
    }

    public void close(){
        try{
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

//    SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id
//    WHERE artists.name = "Carole King" ORDER BY albums.name COLLATE NOCASE ASC;
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
