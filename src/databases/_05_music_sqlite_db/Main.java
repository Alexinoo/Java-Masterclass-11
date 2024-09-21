package databases._05_music_sqlite_db;

import databases._05_music_sqlite_db.model.Artist;
import databases._05_music_sqlite_db.model.Datasource;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        if (!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        List<Artist> artists = datasource.queryArtist();
        if (artists == null) {
            System.out.println("No artists!");
            return;
        }
        for (Artist artist : artists) {
            System.out.println("ID = "+artist.getId() + ", Name = "+ artist.getName());
        }
        datasource.close();
    }
}
