package arrays_and_lists.part11_linked_list_challenge;

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
 *
 *
 *
 *
 */

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String title , double duration){
        if (findSong(title) == null){
            this.songs.add(new Song(title , duration));
            return true;
        }
        return false;
    }

    private Song findSong(String title) {
        for (Song checkedSong: this.songs ) {
            if (checkedSong.getTitle().equals(title))
                return checkedSong;
        }
        return null;
    }

    public boolean addToPlaylist(int trackNumber , LinkedList<Song> playList){
        int index = trackNumber - 1;
        if ( (index >=0) && (index <= playList.size()) ){
            playList.add(this.songs.get(index));
            return true;
        }
        System.out.println("This album doesn't have a track "+trackNumber);
        return false;
    }

    public boolean addToPlaylist(String title , LinkedList<Song> playList){
        Song checkedSong = findSong(title);
        if (checkedSong != null){
            playList.add(checkedSong);
            return true;
        }
        System.out.println("The song " + title + " is not in this album");
        return false;
    }
}
