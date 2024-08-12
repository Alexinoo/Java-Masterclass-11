package java_collections.part2_binary_search;

/*
 * Binary Search
 *
 * - Looking at the definition on our Theatre class , we've used an ArrayList as shown below
 *      private List<Seat> seats = new ArrayList<>();
 *
 * - And we can change that format to a LinkedList<>()
 *      private List<Seat> seats = new LinkedList<>();
 *
 * - And the code still runs, and the implementation is clearly working
 *
 * - We can also be even more generic than that and use Collection instead of a List<Seat> instead as below
 *      private Collection<Seat> seats = new ArrayList<>();
 *
 * - And this runs as well, and what it does is that, it's making it really generic by making it of type Collection
 *      - The advantage of doing that is that it enables us to use any of the Collection classes to hold our seats
 *
 * Next
 *  - Let's look at the interface hierarchy of the Java collections framework
 *      - link : https://docs.oracle.com/javase/tutorial/collections/interfaces/index.html
 *
 *  - The diagram gives us a good overview of the Collections classes
 *  - The interfaces, Set , List , Queue and Dequeue all extend the Collection interface
 *      - There's also SortedSet that extends Set
 *  - Incidentally, the framework also contains a separate hierarchy for Maps
 *
 * - So, when we declare seats to be of type Collection, we cal implement the list of seats in a Theatre using any
 *   concrete class that implements one of the interfaces that extend Collection
 *      - We've already used ArrayList and LinkedList that implements the List interface
 *      - We can also use a HashSet or a LinkedHashSet that implements Set if we want to
 * - We'll use each in turn and verify that the program still runs
 *
 * HashSet
 *  - Let's try first with a HashSet
 *      private Collection<Seat> seats = new ArrayList<>();
 *
 *  - And now that we're using a Collection<Seat> , this will still work
 *      private Collection<Seat> seats = new HashSet<>();
 *
 *  - And the program still runs fine , but note now the seats are printed in a different order because now we're
 *    using Sets
 *
 *  - We can also change it to LinkedHashSet
 *      private Collection<Seat> seats = new LinkedHashSet<>();
 *
 *  - And notice how that returns in order this time
 *
 * - We'll look at Sets in greater detail in later videos, but this clearly demonstrate how ArrayList and LinkedList
 *   fit into the Collections framework, and that they can be replaced with other data structure
 *      - That is if that structure provides benefit for a particular task
 * - That said though, we must remain on the same level of the hierarchy
 *
 * - Although we can use any concrete class that implement Set , List , Queue or Dequeue , what we can't do is drop a
 *   level and still expects things to still work
 *
 * - The Collections framework also includes a TreeSet , which is not on the Screen diagram but that implements
 *   SortedSet
 *      - sort of 1 level down
 * - If we try to use a TreeSet
 *      private Collection<Seat> seats = new TreeSet<>();
 *
 *  - We'll actually get an error which is a ClassCastException, and it's having trouble casting to the Comparable
 *    because we're using TreeSet which is again one level down
 *  - So TreeSet, would be below SortedSet
 *      - Any of that we can use & we can't use the one other one below it directly
 *
 *
 * TreeSet
 *  - As it turns out that we tried to use ,implement SortedSet and actually what it does is that it's NavigableSet
 *    which extends SortedSet
 *  - It's got an additional requirement that elements in it must contain or must be comparable and that's how the
 *    Set is sorted
 *  - Because, we didn't make the Seat class Comparable, that's why we got that ClassCastException, if we try to add
 *    it to a TreeSet
 *  - As we move down the Hierarchy, the interface becomes more specialized, so we can only replace classes with other
 *    classes that implement one of the core Collection interfaces at the same level
 *
 *  - If we look at the diagram again - oracle docs, it says that
 *      - Set is a special kind of collection
 *          - A SortedSet is a special kind of Set and so on and that's why we got an issue with making sure things
 *            are at the same level when we're trying to replace and use them
 * - Before we take a look at what other classes are and why you might want to prefer to use one instead of our List,
 *   let's look at some of the remaining methods provided by the collections interface to work with lists
 *
 * - Although there's not really many seats in our Theatre, the reserveSeat() isn't really efficient the way we have
 *    written it
 *      - To see how many seats it has to check in order to find H02, let's make a little change and print a full
 *        stop that will be printed as the loop goes around
 *      - This will enable us how many Seats it had to check to find the relevant seat
 *
 * - Change our Collection to use an ArrayList first , which is what we started with
 *
 * - And after running this, we can see there's heck a lot of dots printed and it's very inefficient
 *      - It's very inefficient the way it has been designed at the moment , and you can more or less say , it's
 *        referred to as brute force search in that it scans every element before it finds the one we want
 *
 * - The Collections class provides a binarySearch () that performs much better so that as a result we can improve the
 *   reserveSeat() and it's performance
 *      - In order to get this to work, we need to implement the Comparable interface our Seat class and that's
 *        how Java knows how to compare 2 seats
 *      - Change Seat class to implement Comparable<Seat> on seats
 *      - Next, we'll need to override the compareTo and we need that so that the comparison can work
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Theatre theatre = new Theatre("Olympian",8,12);
        theatre.getSeats();

        if (theatre.reserveSeat("H11")){
            System.out.println("Please proceed with payment");
        }else {
            System.out.println("Sorry, seat is already taken");
        }
        if (theatre.reserveSeat("H11")){
            System.out.println("Please proceed with payment");
        }else {
            System.out.println("Sorry, seat is already taken");
        }
    }
}
