package arrays_and_lists.part11_linked_list_challenge;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Linked List Challenge
 * - Create a program that implements a playlist for songs - LinkedList
 * - Create a Song class having Title and Duration for a song
 * - The program will have an Album class containing a list of songs
 * - The albums will be stored in an ArrayList
 * - Songs from different albums can be added to the playlist and will appear in the list in the order they're added
 * - Once the songs have been added to the playlist, create a menu of options to:-
 *      - Quit
 *      - Skip forward to the next song
 *      - Skip backward to a previous song
 *      - Replay the current song
 *      - List the songs in the playlist
 * - A song must exist on an album before it can be added to the playlist (so you can only play songs that you own)
 * - Hint:
 *      - To replay a song consider, what happened when we went back and forth from a city before we started tracking
 *        the direction we were going
 *  - As an optional extra, provide an option to remove the current song from the playlist
 *      (hint : listIterator.remove())
 *
 *
 *
 * main()
 *  - Create an array list of albums
 *      - Create the first album "Stormbringer"
 *          - add songs to this album
 *          - add "Stormbringer" album to albums arraylist
 *
 *      - Create the second album "For those about to rock"
 *          - add songs to this album
 *          - add "For those about to rock" album to albums arraylist
 *
 */
public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();
    public static void main(String[] args) {
        Album album = new Album("Stormbringer" , "Deep purple");
        album.addSong("Stormbringer",4.6);
        album.addSong("Love don't mean a thing",4.22);
        album.addSong("Holy man",4.3);
        album.addSong("Hold on",5.6);
        album.addSong("Lady double dealer",3.2);
        album.addSong("You can't do it right",6.23);
        album.addSong("Highball shooter",4.27);
        album.addSong("The gipsy",4.2);
        album.addSong("Soldier of fortune",4.2);

        // Add Stormbringer album to albums array list
        albums.add(album);

        album = new Album("For those about to rock","AC/DC");
        album.addSong("For those about to rock",5.44);
        album.addSong("I put the finger on you",3.25);
        album.addSong("Let's go",3.45);
        album.addSong("Inject the venom",3.33);
        album.addSong("Snowballed",4.51);
        album.addSong("Evil walks",3.45);
        album.addSong("C.O.D.",5.25);
        album.addSong("Breaking the rules",5.32);
        album.addSong("Night of the long knives",5.12);

        //add "For those about to rock" to albums array list
        albums.add(album);

        //add some songs to the playlist
        LinkedList<Song> playList = new LinkedList<>();

        //get the first album "Deep Purple" and add "You can't do it right" song to the playlist
        // add to playlist via title
        albums.get(0).addToPlaylist("You can't do it right",playList);
        albums.get(0).addToPlaylist("Holy man",playList);
        albums.get(0).addToPlaylist("Speed king",playList); // does not exist in "Deep purple" album
        // add to playlist via track number
    }
}










