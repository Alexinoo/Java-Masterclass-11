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

    //artists table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    //songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    // create connection
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
    public List<Artist> queryArtist(){
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM "+TABLE_ARTISTS)) {

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
