package arrays_and_lists.part11_linked_list_challenge;

import java.util.*;

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
 *          - add a song that doesn't exist and test ("Speed King")
 *
 *      - Create the second album "For those about to rock"
 *          - add songs to this album
 *          - add "For those about to rock" album to albums arraylist
 *          - add a song that doesn't exist ( trackNo 24)
 *
 *
 * play(LinkedList<Song> playList)
 *     - pass the playlist to the play()
 *     - use a ListIterator to play the first song in the playlist
 *     - use a scanner class to get input from the user
 *     - create a boolean "quit" and initialize it to false
 *     - create a while loop that loops as long as "quit" variable is false
 *          - get input from the user using the Scanner class
 *              - if the user enters 0
 *                  - print to the user that the playlist is complete
 *                  - exit by setting quit to false
 *                  - break so that the other cases are not executed
 *
 *              - if the user enters 1
 *                  - play the next song
 *                  - check the forward variable and if it's false, meaning we're going backward
 *                      - set the next element to be the current element, if it exists
 *
 *              - if the user enters 2
 *                  - play the previous song
 *                  - check the forward variable and since it's true, meaning we were going forward
 *                      - set the previous element to be the current element , if it exists
 *
 *              - if the user enters 3
 *                  - re-play the current song
 *                  - we can take advantage of the fact that the list iterator is actually somewhere in between
 *                     the previous and the next - somewhere in the middle
 *                  - one of those will give us the track we last started playing and then work out which one to use
 *                  - all we really need to know is which direction we were moving in at the time
 *                  - For example:
 *                      - if we were moving forward, we're just after the currently playing song, and therefore need
 *                        to go back because we're in a forward position
 *                          - set forward to false because we have changed the direction
 *
 *                      - if we're actually going backwards, we will be going to the previous one, then we're just
 *                        before the current song, so we need to go forward
 *                          - set forward to true because we have changed the direction to play the next song
 *
 *              - if the user enters 4
 *                  - print the playlist
 *
 *              - if the user enters 5
 *                  - print menu options  - available options
 *
 *              - if the user enters 6
 *                  - check if the playlist size is > than 0 and call listIterator.next()
 *                  - next, call listIterator.next() or listIterator.previous() to point to the next song
 *                      - or even start playing the next/previous if any exist
 *                  - to avoid getting a potential error if we try to delete a song again
 *                  - in short, if we delete the song from the playlist
 *                      - the next song starts playing automatically, if we were at the start or somewhere in the middle
 *                      - the previous song starts playing, if we were at the end of the playlist
 *
 *
 * printMenu()
 *  - prints available options
 *
 * printList(LinkedList<Song> playList)
 *  - create a regular iterator to loop through the songs
 *      - ideal if we're moving just forward and not traversing backwards and so on
 *  - use a while loop to loop as long as iterator.hasNext() condition is true
 *      - print the current song using iterator.next()
 *
 *
 * Optional
 *  - remove the current song from the playlist
 *
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
        albums.get(0).addToPlaylist(9,playList);

        //add album 2  "AC/DC" and add songs related to that album
        albums.get(1).addToPlaylist(8,playList);
        albums.get(1).addToPlaylist(3,playList);
        albums.get(1).addToPlaylist(2,playList);
        albums.get(1).addToPlaylist(24,playList); // there is no track 24


        // play playlist
        play(playList);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if (playList.size() == 0){
            System.out.println("No songs in playlist");
            return;
        }else {
            System.out.println("Now playing: "+ listIterator.next().toString());
            printMenu();
        }
        while (!quit){
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action){
                case 0:
                    System.out.println("Playlist complete");
                    quit = true;
                    break;
                case 1:
                    if (!forward){
                        System.out.println("we were going backwards and now we want to go foward");
                        if (listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()){
                        System.out.println("Now playing "+listIterator.next().toString());
                    }else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward){
                        System.out.println("we were going forward and now we want to go backwards");
                        if (listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.println("Now playing "+listIterator.previous().toString());
                    }else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward){
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        }
                        else
                            System.out.println("We are at the start of the list");
                    }else{
                        if (listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next());
                            forward = true;
                        }
                        else
                            System.out.println("We have reached the end of the list");
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6 :
                    if (playList.size() > 0){
                        listIterator.remove();
                        if (listIterator.hasNext()){
                            System.out.println("Now playing: "+listIterator.next());
                        }else if (listIterator.hasPrevious()){
                            System.out.println("Now playing: "+listIterator.previous());
                        }
                    }
                    break;
            }
        }
    }

    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("=================================");
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        System.out.println("=================================");
    }

    private static void printMenu() {
        System.out.println("Available options\n"+
                "0 - to quit\n"+
                "1 - to play next song\n"+
                "2 - to play previous song\n"+
                "3 - to replay the current song\n"+
                "4 - list songs in the playlist\n"+
                "5 - print available actions\n"+
                "6 - delete current song from playlist");
    }
}










