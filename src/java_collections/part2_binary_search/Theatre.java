package java_collections.part2_binary_search;

import java.util.*;

/*
 * Theatre Class with an inner class Seat
 *
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
 *
 * Collections.binarySearch
 * ........................
 *
 * - The Collections class provides a binarySearch () that performs much better so that as a result we can improve the
 *   reserveSeat() and it's performance
 *      - In order to get this to work, we need to implement the Comparable interface our Seat class and that's
 *        how Java knows how to compare 2 seats
 *      - Change Seat class to implement Comparable<Seat> on seats
 *      - Next, we'll need to override the compareTo and we need that so that the comparison can work
 *          - implement compareTo and compare current seatNumber with the object passed
 *          - uses String.compareTo built into the String class
 *      - The Seat class could contain more than 1 field, and if that's the case, java wouldn't have any idea of which would be used for comparison
 *
 * - So if you have a more complex class, this is where you put your code for testing using that Comparable interface and then you provide a
 *   compareTo() that the framework can use
 *
 * - Now we have a comparison that fulfills the interface, and now we can be able to use a different type of set for our searching, which is a lot
 *   more efficient
 * - Now we can implement binary search and it's going to be more efficient
 *
 * Next
 *  - Instead of setting requestedSeat to null , pass the requestedSeatNumber to the constructor
 *  - call binarySearch and store it into foundSeat integer variable
 *      int foundSeat = Collections.binarySearch();
 *  - check if foundSeat >= 0 , which means we have found the seatNumber
 *      - if true
 *          - return seats.get(foundSeat).isReserved()
 *      - Otherwise
 *          - return false
 *
 *  - The reason why binary search is more efficient is it works on a sorted list and it works by checking the element in the midpoint of the list
 *  - So the method basically plays higher or lower with the list
 *      - If the middle element is greater than the item we're looking for,
 *          - then it knows the one we want must be in the first part of the list
 *          - then it performs binary search on that half of the list in the same way
 *  - The list of elements is reduced to half each time
 *  - As a result, it will take not more than 10 checks to find an item, or decide it's not present in a list of 1024 elements
 *      - 2 to the power of 10 is 1,024
 *  - Over a million items 1,048,576 can be checked in not more than 20 comparisons and
 *
 *  - 64 comparisons are all that's required to search a list with a huge number such as
 *      1.844674407 * 10^19
 *  - And so it's blindingly fast compared to the brute force approach that we took earlier
 *
 *  - We may not be able to see too much in terms of the performance , but let's work with the code from the actual binary search Java source
 *    code
 *      - works recursively by getting the mid-point and checking if the number searched is > greater than the one in the mid point
 *          - If so , look for the 1st half, otherwise, look for the 2nd half
 *          - do this recursively
 *      - add some full stops and check how many seats it needs to search for
 *
 * - When we used Collections.binarySearch , we haven't directly got a way of getting access that part
 *
 * - And after running this, we're now able to find seat H11 , only with 6 searches which is significantly faster than the last case
 *
 * Next
 *  - Try some other bookings and check different results
 *      - try booking "D12"
 *      - only looked for it once and then on the second time , it found it , much faster
 *
 *  - H12 or B11 are 2 of the worst
 *
 *
 *  - Also try "B13" , which we doesn't exist
 *      - we can see that it didn't go through all the elements , only 7 iterations including the last one, that there's no seat "B13"
 *
 *  - So it is significantly faster than our other attempt at searching
 *
 *  - Obviously with this particular case, with only 96 seats in our Theatre, it's really not an issue but if we start talking thousand of records
 *    using features like this, in this the binary search which is part of the collections framework, it makes a lot more sense
 *
 *
 * N/B
 *  - Normally, you wouldn't copy the collections methods like we've done, we just call them like we did with the binarySearch earlier
 *  - It's really interesting to see how efficient binary search algorithm is and we did that by adding (.) dot, to show us how many iterations
 *    it took to get to where we wanted to go to
 *  - We also don't need to write our own versions of the algorithms that are included in the Collections class
 *
 *
 *
 *
 */

public class Theatre {
    private final String theatreName;

    private List<Seat> seats = new ArrayList<>();

    public Theatre(String theatreName , int numOfRows , int numberOfSeatsPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numOfRows - 1);

        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNumber = 1; seatNumber <= numberOfSeatsPerRow; seatNumber++) {
                Seat seat = new Seat(row + String.format("%02d",seatNumber));
                seats.add(seat);
            }
        }
    }

    public void getSeats() {
        for (Seat seat : this.seats){
            System.out.println(seat.getSeatNumber());
        }
    }

    public boolean reserveSeat(String requestedSeatNumber){

        // Brute Force search //////////////////////////////////
        ////////////////////////////////////////////////////////

        /* Seat requestedSeat = null;
        for (Seat seat : this.seats){
            System.out.print(".");
            if (seat.getSeatNumber().equals(requestedSeatNumber)){
                requestedSeat = seat;
                break;
            }
        }
        if (requestedSeat == null){
            System.out.println("There is no seat "+requestedSeatNumber);
            return false;
        }
        return requestedSeat.isSeatReserved(); */


        /////////////////////////////////////////////////
        // Using Collections.binarySearch() - Directly
        /////////////////////////////////////////////////

        /*Seat requestedSeat = new Seat(requestedSeatNumber);
        int foundSeat = Collections.binarySearch(seats,requestedSeat,null);
        if ( foundSeat >= 0){
            return seats.get(foundSeat).isSeatReserved();
        }else{
            System.out.println("There is no seat "+requestedSeatNumber);
            return false;
        } */

        //////////////////// Using binarySearch Java source code ////////////
        /////////////////////////////////////////////////////////////////////

        int low = 0;
        int high = this.seats.size() - 1;

        while (low <= high){
            System.out.print(".");
            int mid = (low + high) / 2;
            Seat midVal = this.seats.get(mid);
            int comparison = midVal.getSeatNumber().compareTo(requestedSeatNumber);
            if (comparison < 0){
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            }else {
                return seats.get(mid).isSeatReserved();
            }
        }
        System.out.println("There is no seat "+requestedSeatNumber);
        return false;

    }

    private class Seat implements Comparable<Seat>{
        private final String seatNumber;
        private boolean isReserved;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean isSeatReserved(){
            if (!this.isReserved){
                this.isReserved = true;
                System.out.println("You have successfully reserve the seat : " +getSeatNumber());
                return true;
            }
            System.out.println(getSeatNumber() + " has already been reserved");
            return false;
        }

        public boolean cancel(){
            if (this.isReserved){
                this.isReserved = false;
                System.out.println("Reservation of seat " + seatNumber+ " cancelled");
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }
    }


}
