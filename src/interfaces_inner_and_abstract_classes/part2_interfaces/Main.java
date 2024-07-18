package interfaces_inner_and_abstract_classes.part2_interfaces;

import java.util.*;

/*
 * Power of Interfaces
 * ...................
 * - Use PlayList LinkedList challenge and change it to use a List instead
 * - The reason we've used the challenge is for us to see how Java libraries makes extensive use of interfaces, and the one that we've used recently
 *   was the List interface
 * - We'll look at how we can make a change without breaking any functionality
 *
 * Album
 *  - addToPlaylist(int trackNumber , LinkedList<Song> playList) : boolean
 *      - update from LinkedList to List
 *          addToPlaylist(int trackNumber , List<Song> playList)
 *
 *  - addToPlaylist(String title , LinkedList<Song> playList) : boolean
 *      - update from LinkedList to List
 *          addToPlaylist(String title , List<Song> playList)
 *
 * - Then we can go to the main() and it's actually calling this code, and make a change, we can run this code virtually without changing anything
 * - This is because the java libraries are making extensive usage of interfaces and if they weren't doing that we would find a lot of this code
 *   would break
 *
 * main()
 *
 * - printList(LinkedList<Song> playList) : void
 *      - Update parameter LinkedList<Song> to List<Song>

 *  - play(LinkedList<Song> playList) : void
 *      - Update parameter LinkedList<Song> to List<Song>
 *
 *  - change declaration of ArrayList to List
 *           private static ArrayList<Album> albums = new ArrayList<>();
 *      - to
 *           private static List<Album> albums = new ArrayList<>();
 *
 *  - change creation of a LinkedList<Song>
 *       LinkedList<Song> playList = new LinkedList<>();
 *  - to
 *       List<Song> playList = new List<>();
 *
 * POWER OF INTERFACES IN JAVA
 * ...........................
 *
 * - You can see we've done all of that & it's still going to work because the Java libraries use interfaces extensively
 * - Everything else works as before
 *
 * - Java libraries do make extensive usage of interfaces and because of that we're able to change out the entire implementation from what was a
 *   LinkedList to an ArrayList very simply & we have done that with a minimal amount of changes , which is pretty cool
 *
 * - This is one of the reason why you'd want to be using an interface which makes it a heck a lot easier to make your code work in this case
 * - The Java libraries have set it up to , so you can use ArrayLists, LinkedLists and so on and make those really interchangeable
 *
 * - You can even use another type and the reason this works , is that the contract that's defined by the List interface, so just to be clear
 *      - CTRL + click on List interface
 *          - Notice how List is a public interface and because of that contract with these method(s) that are actually in the List  or being
 *            specified in the interface
 *
 * - So long as you create a class that implements these methods, and actually creates the code that creates that functionality, things are actually
 *   going to work fine
 *
 * - And as you can see here , for example, we were able to change this to make a generic list, but then in the main(), we had actually set it
 *   up as an ArrayList, but the PlayList which was originally a LinkedLIst, we changed it to an ArrayList
 *
 * - And because the ArrayList which implements the base List interface, still supporting all the method(s), it has to have implemented those
 *   method(s), everything still works nicely
 *
 * - And that's really the power of an interface
 *
 * - We could also change
 *       List<Song> playList = new ArrayList<>();
 *
 * - to a Vector
 *       List<Song> playList = new Vector<>();
 *
 * - Vector is another type of list & we do that and the code still works and it's not broken
 *
 * - So it's very normal when you're creating lists in your code, to define the basic type of type "List"
 * - Then when you actually create , or initialize the variable, that's when you specify the actual type, in this case a Vector or a LinkedList or
 *   an ArrayList
 *
 * - So it's probably a good habit just to make your list , use just a generic List as the type as mentioned below
 *       List<Song> playList
 * - And then, you just specify the actual type that you want in your program and you initialize it
 *
 * - In terms of actually deciding whether to implement an interface or inherit from a base class, sometimes it can be a little bit difficult to
 *   decide which one to do
 *
 * - In terms of should you create a class and extend from that , inherit from that class, or should you use an interface
 *
 * - An example, is with the Telephone interface, we could have had it as a class and extended it to create a DeskPhone or a MobilePhone class or
 *   both of them
 *
 * - But in this example, it wouldn't have been a good approach
 *
 * - SO the way to consider that generally, is to really consider the relationship of the final class to the object that it's extending or implementing
 *
 * - For example,
 *      - With our Telephone example, both devices have a Telephone, so the DeskPhone doesn't have anything else but the mobile phone is not just
 *        a phone, but can be used in much more than call/receive phones
 *
 * - We can actually say that a mobile phone is actually a computer that has a phone interface
 *
 * - A DeskPhone can Telephone, and a MobilePhone can Telephone, which indicates that both should implement a Phone interface rather than extending
 *   a Phone class if that makes sense
 *
 * - Another thing to understand is that a class can only inherit from 1 super class, unlike other languages like C++ for example where you can do
 *   that
 * - But you can actually implement from many interfaces which is quite powerful
 *
 * - Some languages allow multiple inheritance by allowing a class to inherit from several base classes, but in Java, multiple inheritance, is only
 *   available by implementing several interfaces
 *
 * - Consider the Animal class created on the lectures in the Inheritance section, though not all animals can walk, so you can't put a walk() in
 *   the Animal base class
 *
 * - But we could define, say, a walk interface, though,
 *
 * - So if that is the case, we could then consider birds, they can walk and most of them can also fly, so we can create a fly interface as well
 *
 *
 *
 * POINT/ IMPORTANT
 * ................
 * - A dog would extend animal and implement walk interface
 * - A bird would extend animal and implement both walk and fly
 *
 * - So in this case, a dog is an animal and a bird is an animal as they both inherit from the Animal class
 *
 * - But a dog can walk , so it implements walk, and a bird can both walk and fly, so it implements from both interfaces
 *
 * - Consider the relationship of the final class, to the object that you're extending or implementing
 */
public class Main {
    private static List<Album> albums = new ArrayList<>();
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
        List<Song> playList = new Vector<>();

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

    private static void play(List<Song> playList) {
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

    private static void printList(List<Song> playList) {
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










