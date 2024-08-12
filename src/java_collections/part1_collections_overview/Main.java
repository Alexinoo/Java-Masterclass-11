package java_collections.part1_collections_overview;
/*
 * Collections Overview
 *
 * - We've looked at 3 of the Java's list types
 *      - List interface
 *      - ArrayList & LinkedList classes
 * - They form part of the Java's collections framework and that framework also includes things like
 *      - Sets
 *      - Maps
 *      - Trees
 *      - Queues
 * - At the top level of the Collections framework is the Collections class
 *
 * Collections class
 *  - Exposes static methods that either operate on collections such as the sort() that we've used previously
 *  - They also return collections objects such as the list()
 *
 * Collection Interface
 * - The interfaces in the Collection framework , what they do is that they allow the framework to be extended
 * - They define methods for all fundamental operations required for various collection types
 * - One of the design goals for collection framework is that there should be good interoperability amongst various
 *   collections
 *      - Not just the ones created in the framework but literally anything that may also be created in the future
 *        that is a reasonable representation of a collection
 *      - That also includes Arrays
 * - Arrays couldn't be made part of the collection framework without changing the Java language
 * - However, the framework does include methods that enable collections to be moved into arrays and vice versa and
 *   additionally methods to allow arrays to be viewed as collections
 *
 * - The core components or the main elements of the Collections framework are interfaces
 *      - These are the abstract types that represent collections , including the List interface
 * - And we'll be looking at the interface hierarchy of the collections framework
 * - Also included are the aggregate operations
 *      - We've seen iterators nut we'll cover them in much more detail
 * - Another core element is the implementations
 *      - This is the concrete implementations of the interface, including the ArrayList and LinkedList classes, which
 *        are 2 good examples of an implementation
 * - But also algorithms
 *      - The Java JDK provides a range of polymorphic algorithms that work on collections objects, technically
 *         speaking on objects that implement the collection interface, and they provide reusable functionality
 *
 * - Let's start by reviewing the ArrayList and LinkedList objects as well as the List<T> interface and consider them
 *   within the context of the Collections framework
 *
 *
 * Example
 *  - Will use a simple Seat Booking System in a Theatre to see how this classes tht we've used earlier fit into the
 *    java collections framework
 *  - Theatre Seats will be numbered with a row letter and then a Seat number within each row
 *
 * - Will create a Theatre class that will contain an inner Seat class
 *
 *
 * Next
 *  - main()
 *      - Add a Theatre named "Olympian" with 8 number of rows , with 12 seats per row
 *      - print seats by calling getSeats
 *      - Try to reserve "D12"
 *          - If successful,
 *              - advise the client to proceed with the payment
 *          - Otherwise,
 *              - tell the user that the Seat is already taken
 *      - Try reserving the same seat "D12" again
 *          - use the same messages and check that this time it fails
 *
 * Next
 *  - We'll look at changing the data structure defined as
 *      private List<Seat> seats = new ArrayList<>();
 *  - in the Theatre class using an ArrayList, and we'll start changing that to a few different formats, the List
 *     and also look at changing it to a collection by making it more generic and see how thta will work
 */
public class Main {

    public static void main(String[] args) {

        Theatre theatre = new Theatre("Olympian",8,12);
        theatre.getSeats();

        if (theatre.reserveSeat("D12")){
            System.out.println("Please proceed with payment");
        }else {
            System.out.println("Sorry, seat is already taken");
        }
        if (theatre.reserveSeat("D12")){
            System.out.println("Please proceed with payment");
        }else {
            System.out.println("Sorry, seat is already taken");
        }
    }
}
