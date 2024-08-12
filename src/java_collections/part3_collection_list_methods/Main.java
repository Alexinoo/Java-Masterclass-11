package java_collections.part3_collection_list_methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Collections List methods
 *
 * - Add printList()
 *      - prints the content of the List passed to it
 *
 * - Next
 *      - create a new copy of Seat objects and if we pass in the existing seats from our Theatre object, we're going to get a new list containing
 *        all the seats
 *      - the seatCopy list contains all of the elements that were in the original
 *
 * - The method that we use to create a seatCopy and populate it in all the elements on theatre.seats is called a shallow copy
 *
 * Shallow copy
 *  - It creates an arraylist containing all the elements from the list that were passed to the constructor
 *  - Note that they aren't copied and hence the term shallow copy, if we modify, 1 of the seats say by reserving it, then that list will be reserved,
 *    which ever list we check for it
 *  - We may have a new list but the contents are a same objects as exists in the theater.seats
 *      - we're taking a copy, a shallow copy of the same data and putting it into another array list but they're effectively the same shared objects
 *
 *
 * Demonstrate
 *  - reserve the second seat from seatCopy
 *  - reserve the same seat from theatre.seats("A02")
 *
 * Outcome
 *  - We are able to reserve seat "A02" from seatCopy
 *  - reserve from theatre.reserveSeat("A02") failed,
 *
 * - It's clear now that we're sharing the same data , because the 2nd reservation would also have worked on the other arraylist
 * - The 2 separate lists here each contains references to the same 96 seat object
 *      - Separate references but same object
 *      - There are 2 separate ArrayLists, but the references are to the same 96 seat objects that we initially created, when we created the Theatre
 *         object
 *
 * Another Example
 *  - Reverse seatCopy
 *
 *  - print both
 *
 *      Printing seatCopy
 *      C06 C05 C04 C03 C02 C01 B06 B05 B04 B03 B02 B01 A06 A05 A04 A03 A02 A01
 *      =======================================================================
 *
 *      Printing theatre.seats
 *      A01 A02 A03 A04 A05 A06 B01 B02 B03 B04 B05 B06 C01 C02 C03 C04 C05 C06
 *      =======================================================================
 *
 *  - clearly, there's 2 separate arraylists that we've got working there
 *      - seatCopy is in reverse order, but theatre.seats has still got the original order that was set
 *
 *  - So clearly, they are separate ArrayList and the only thing is that they refer to the same objects that were initially created
 *
 *
 * Collections.shuffle()
 *  - shuffles the elements of the list in a random or sort of pseudo-random order
 *  - psuedo-randomizes a list using the shuffle() that's part of the collections framework
 *
 * - There's lots of other useful methods that form part of the collections class
 *
 *
 *
 * Collections.min() and Collections.max()
 * - A couple of useful ones are min and max and they return the smallest and the highest elements recorded into their natural order
 *      - i.e. the sort order that the compareTo() maintains
 * - Get min/max from seatCopy
 * - uses the compareTo() to determine the sort order , basing the results on that natural order from the compareTo() for a given list
 * - this will still work even if the list hasn't been sorted , as we had already shuffled it , but it's going through the entire list and
 *   determining the minimum and maximum based on the compareTo sort order
 *
 * Collections.swap()
 *  - If you want to implement your own sort or for any other reason , the collections class provides a swap() to swap 2 elements in a list
 *  - We pass the name of the list and the index position of the 2 elements that we want to swap
 *  - The swap() swaps their position in a list
 *  - We'll create a variation on the bubble sort method from the arrays challenge , but this time will use nested for loops , and the reason for
 *    that is because we had a student in the course who had asked if it was also acceptable
 *
 *
 *  - add sortList()
 *      - use compareTo() and if the result is greater than 0
 *      - call Collections.swap(list, i , j)
 *
 * Explanation
 *  - We are using a variation of a bubble sort method that we wrote in the arrays challenge , but here we're using nested for loops
 *      - Note that the inner loop doesn't start at 0
 *      - it actually starts after where the current position that the outer loop has reached
 *      - inner loop is checking fewer and fewer elements each time around
 *
 *  - It's still much slower than the built-in merge sort , but can be useful in situations where speed wasn't important but memory was at premium
 *
 *  - Merge Sort requires far more memory than a Bubble sort, which can be an issue with extremely large lists
 *  - It would be rare to use your own routines and more often than not, you'd rather use the ones that are built into the collections framework
 *
 * - Unlike the printList() which was the method directly above , had to access seat.getSeatNumber() getter and therefore only works on seat lists
 *
 * - We have made our sortList() more generic, so that it can sort any kind of list of Theatre.Seat so as long as they implement the Comparable
 *
 * - Next
 *      - call sortList on seatCopy list that we had shuffled
 *      - pass it to printList() to print it again
 *
 *
 * Collections.copy()
 *  - is a rather odd copy method provided by the Collections class
 *  - takes 2 parameters,
 *      - destination
 *      - source list
 *  - the destination has to be of generic type, there's a super set of the source list and it basically means it can be a collection , an iterable or
 *    a list
 *  - the source must be a list
 *  - the problem that tends to arise with Collections.copy() , is that people either expect to use it as we just did when we used the list constructor
 *    to create a copy of our seats
 *  - Or people may expect to create a deep copy of a list
 *
 *  - As opposed to a shallow copy, a deep copy is a copy where the elements are not just references to the same elements as in the original list,
 *    but are themselves copied
 *  - Collections.copy() will fail if unless the destination is big enough to hold all the elements copied to into it
 *
 *  - You would think that doing as below will help
 *      List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
        Collections.copy(newList,theatre.seats);
 *
 *  - Where we first initialize a new list with a capacity of the source list size, but if we try to copy, we get "source does not fit in dest"
 *    error
 *      - And the reason the below fails
 *
 *          List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
 *
 *      - is what it does is that it only sets the capacity of the ArrayList, giving it the potential to hold that many elements, but initially
 *        containing none
 *      - it still doesn't create a number of elements
 *
 *      - To get Collections.copy() to work, you actually need to have a 96 seat obj in that new list that we created as below
 *
 *          List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
 *      - before we can attempt to copy
 *
 *  - It's really hard to think of an actual use for the Collections.copy(), although it would allow all of the elements of a list to be copied into
 *    another collections object, providing the object had been initialized by enough elements first
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian",3,6);

        List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.seats);
        printList(seatCopy);

       // reserve 2nd seat : A02
        seatCopy.get(1).isSeatReserved();

        // reserve the same seat from the other ArrayList
        if (theatre.reserveSeat("A02")){
            System.out.println("Please pay for A02");
        }else {
            System.out.println("Seat already reserved");
        }

        // reverse
        Collections.reverse(seatCopy);
        System.out.println("Printing seatCopy");
        printList(seatCopy);

        System.out.println("Printing theatre.seats");
        printList(theatre.seats);

        // shuffle
        Collections.shuffle(seatCopy);
        System.out.println("Shuffled seatCopy");
        printList(seatCopy);

        // Collections.min() and Collections.max()
        Theatre.Seat minSeat = Collections.min(seatCopy);
        Theatre.Seat maxSeat = Collections.max(seatCopy);

        System.out.println("Min seat number is "+minSeat.getSeatNumber());
        System.out.println("Max seat number is "+maxSeat.getSeatNumber());


        // Collections.swap() - in action with sortList()
        sortList(seatCopy);

        System.out.println("Printing sorted seatCopy");
        printList(seatCopy);

        // Collections.copy()

        List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
        Collections.copy(newList,theatre.seats);


    }

    public static void printList(List<Theatre.Seat> list){
        for (Theatre.Seat seat : list){
            System.out.print(" "+ seat.getSeatNumber());
        }
        System.out.println();
        System.out.println("====================================================================");
    }

    public static void sortList(List<? extends Theatre.Seat> list){
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = (i+1); j < list.size(); j++) {
                if ( list.get(i).compareTo(list.get(j)) > 0 ){
                    Collections.swap(list,i , j);
                }
            }
        }
    }
}

