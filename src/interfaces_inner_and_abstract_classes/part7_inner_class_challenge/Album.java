package interfaces_inner_and_abstract_classes.part7_inner_class_challenge;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Album class
 *
 * Fields
 *  name : String
 *  artist : String
 *  songs : ArrayList<Song>
 *
 * Constructor
 *  Album(String name , String artist)
 *  - initialize name and artist
 *  - initialize songs ArrayList
 *
 * Methods:
 *  - addSong(String title, String duration) : boolean
 *      - call findSong that checks if the song exist in the songs arrayList
 *      - if not
 *          - add it to the songs array list
 *          - return true
 *      - otherwise
 *          - return false
 *
 *  - findSong(String title) : Song
 *      - loop through the array list and return the matched Song
 *      - if any
 *          - return the checkedSong
 *      - otherwise
 *          - return null
 *
 *  - addToPlayList(int trackNumber , LinkedList<Song> playList) : boolean
 *      - adds the songs to the playlist via track number
 *      -  create an index variable and initialize it to (trackNumber - 1)
 *          - if a user, adds trackNo 1 , the index of the song will be 0 and will be added to the playList
 *          - if a user, enters trackNO with an index >= the size of the songs arraylist , return false
 *      - return true for successful addition, otherwise, return false
 *
 *  - addToPlayList(String title) : boolean
 *      - add songs to the playlist via the title
 *      - check if the song exists in the songs array list
 *      - if so,
 *          - add it to the playlist Linked list and return true
 *      - otherwise
 *          - print to the user that the song doesn't exist on the album
 */

public class Album {
    private String name;
    private String artist;
    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public boolean addSong(String title , double duration){
       return this.songs.add(new Song(title , duration)); // call inner class - addSong() - from SongList
    }

    public boolean addToPlaylist(int trackNumber , LinkedList<Song> playList){
        Song checkedSong = this.songs.findSong(trackNumber); // call inner class - findSong() - via trackNumber - from SongList
        if (checkedSong != null){
            playList.add(checkedSong);
            return true;
        }

        System.out.println("This album doesn't have a track "+trackNumber);
        return false;
    }

    public boolean addToPlaylist(String title , LinkedList<Song> playList){
        Song checkedSong = this.songs.findSong(title); // call inner class - findSong() - via title - from SongList
        if (checkedSong != null){
            playList.add(checkedSong);
            return true;
        }
        System.out.println("The song " + title + " is not in this album");
        return false;
    }

    private class SongList {
        private ArrayList<Song> songs;
        public SongList() {
            this.songs = new ArrayList<>();
        }
        public boolean add(Song song){
            if (songs.contains(song))
                return false;
            this.songs.add(song);
            return true;
        }

        public Song findSong(String title) {
            for (Song checkedSong: this.songs ) {
                if (checkedSong.getTitle().equals(title))
                    return checkedSong;
            }
            return null;
        }

        public Song findSong(int trackNumber){
            int index  = trackNumber - 1;
            if (index >=0 && index < songs.size())
                return songs.get(index);
            return null;
        }
    }

}
