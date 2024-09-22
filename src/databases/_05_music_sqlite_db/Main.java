package databases._05_music_sqlite_db;

import databases._05_music_sqlite_db.model.Artist;
import databases._05_music_sqlite_db.model.Datasource;
import databases._05_music_sqlite_db.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtist(Datasource.ORDER_BY_NONE);
        if (artists == null) {
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

        if (albums == null) {
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
         */
       // datasource.insertSong("Touch of Grey","Grateful Dead","In The Dark",1);
        datasource.insertSong("Like a Rolling Stone","Bob Dylan","Bob Dylan's Greatest Hits",5);

        /*
         * Close the connection
         */
        datasource.close();
    }
}
