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
