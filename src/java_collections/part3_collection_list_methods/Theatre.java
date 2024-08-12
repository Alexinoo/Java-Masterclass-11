package java_collections.part3_collection_list_methods;

import java.util.ArrayList;
import java.util.List;

/*
 * Theatre Class with an inner class Seat
 *
 * Collections List Method
 *
 * - We've looked at Collections.binarySearch() and we've seen that it's very efficient in terms of how many iterations were required compared to
 *   our version , and it was significantly more efficient
 * - The algorithms that come with java are very efficient and we don't have to write our own versions of these algorithms that are included
 * - Collections.binarySearch() relies on the list that you're searching being sorted and we saw in an earlier lecture how to use Collections.sort()
 *
 *
 * Collections.sort()
 *  - We saw Collections.sort() in action in an earlier lecture,and we used it to sort a list, but that was providing the items in that list
 *    implement the comparable interface
 *  - We generated our seat numbers in a sorted order , so there's no need to sort before performing the search, but the Collections.sort() is
 *     available if needed
 *  - Interestingly enough, it uses a very efficient merge sort which performs much better than the bubble sort that we used in an earlier challenge
 *
 *
 * Collections.reverseSort()
 *  - sorts the list in a reverse order
 *  - so that we don't mess up the List<Seat> seats for our theater, we have to really look at a way to use the lists or a List constructor to copy
 *    1 list to another
 *
 *
 * - The Collections framework provides a method to copy lists and other collection objects though it doesn't really work as you would expect
 *      - It copies a list into an existing one , of at least the same size
 *
 * - We'll change the Theatre class so that the Seat class is public, though you wouldn't normally do this with a real program , but it will make
 *   our testing of these concepts that come through a bit easier
 *      - Change
 *              private List<Seat> seats
 *      - and
 *              private class Seat
 *      - to
 *              public class Seat
 *
 * Next,
 *  - main()
 */

public class Theatre {
    private final String theatreName;

    public List<Seat> seats = new ArrayList<>();

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

    public class Seat implements Comparable<Seat>{
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
