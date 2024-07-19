package interfaces_inner_and_abstract_classes.part7_inner_class_challenge;


import java.util.*;

/*
 * LinkedList Challenge
 * ....................
 *
 * Modify the playlist challenge so that the Album class uses an inner class.
 *
 * Instead of using an ArrayList to hold its tracks, it will use an inner class called SongList
 *
 * The inner SongList class will use an ArrayList and will provide a method to add a song.
 *
 * It will also provide findSong() methods which will be used by the containing Album class
 * to add songs to the playlist.
 *
 * Neither the Song class or the Main class should be changed.
 */
public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        Album album = new Album("Stormbringer", "Deep purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.2);
        album.addSong("You can't do it right", 6.23);
        album.addSong("Highball shooter", 4.27);
        album.addSong("The gipsy", 4.2);
        album.addSong("Soldier of fortune", 4.2);

        // Add Stormbringer album to albums array list
        albums.add(album);

        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Let's go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);

        //add "For those about to rock" to albums array list
        albums.add(album);

        //add some songs to the playlist
        LinkedList<Song> playList = new LinkedList<>();

        //get the first album "Deep Purple" and add "You can't do it right" song to the playlist
        // add to playlist via title
        albums.get(0).addToPlaylist("You can't do it right", playList);
        albums.get(0).addToPlaylist("Holy man", playList);
        albums.get(0).addToPlaylist("Speed king", playList); // does not exist in "Deep purple" album

        // add to playlist via track number
        albums.get(0).addToPlaylist(9, playList);

        //add album 2  "AC/DC" and add songs related to that album
        albums.get(1).addToPlaylist(8, playList);
        albums.get(1).addToPlaylist(3, playList);
        albums.get(1).addToPlaylist(2, playList);
        albums.get(1).addToPlaylist(24, playList); // there is no track 24


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
