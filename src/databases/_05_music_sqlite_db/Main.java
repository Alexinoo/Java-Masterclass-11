package databases._05_music_sqlite_db;

import databases._05_music_sqlite_db.model.Artist;
import databases._05_music_sqlite_db.model.Datasource;
import databases._05_music_sqlite_db.model.Song;
import databases._05_music_sqlite_db.model.SongArtist;

import java.util.List;

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
        datasource.close();
    }
}
