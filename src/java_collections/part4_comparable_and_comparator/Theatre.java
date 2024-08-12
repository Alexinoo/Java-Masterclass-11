package java_collections.part4_comparable_and_comparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 *  Comparable and Comparator
 *
 * - The collections framework includes classes that sorts their elements
 * - To really understand all this we need to understand how that works in order to create our own classes that we can use or we could store in
 *   sorted collections
 * - We implemented the Comparable interface, so that we could experiment with the Collections.sort() and Collections.reverse()
 *      - We've seen how easy it is to create the 1 required method of the interface, which of course was the compareTo()
 *
 * - We'll continue using the Theater example because the Seats give us a reasonably sized data set to play with
 *      - And also because it's regenerated for us by the Theater's class constructor
 *
 * - Use Theater class and make changes to the fields
 *      - Change List<Seat> to private
 *
 *      - Change Seat class
 *          - add a price field
 *          - initialize it in the Constructor
 *
 *      - use the direct Collections.binarySearch() approach
 *
 *      - add a getter for Seats
 *          - and since we had one,that was just looping through the Seat
 *          - update it to return a Collection<Seat> and returns seats
 *
 *      - add a getter for price in the Seat class
 *
 * - Change the code on the constructor where we're creating Theatre constructor
 *      - charge a different price based on what level
 *          - added a base price of $12 for the seats
 *
 *      - set a price of $12 for seats that are middle in the first 3 rows
 *          - if the seats are near the middle of the first 3 rows, the price increases to $14
 *              - ignore the first and the last 3 seats in the edges
 *              - considered as premium sort of Seat
 *
 *          - seats that are in the back 2 rows and are on the edges of the theatre to cost $7
 *              - charge $7 for the last 2 rows , and seats that are in the edges
 *              - i.e. the first 3 seats on the LHS and the last 3 seats in RHS
 *
 *      - pass the price to the Seat obj instance
 *
 *  - N
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
                double price = 12.00;
                if ( (row < 'D') && (seatNumber >=4 &&  seatNumber <= 9) ){
                    price = 14.00;
                }else if( (row > 'F') || (seatNumber < 4 || seatNumber > 9)){
                    price = 7.00;
                }
                Seat seat = new Seat(row + String.format("%02d",seatNumber),price);
                seats.add(seat);
            }
        }
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    public boolean reserveSeat(String requestedSeatNumber){

        // Using Collections.binarySearch() - Directly///////////////////////////////////////////

        Seat requestedSeat = new Seat(requestedSeatNumber , 0);
        int foundSeat = Collections.binarySearch(seats,requestedSeat,null);
        if ( foundSeat >= 0){
            return seats.get(foundSeat).isSeatReserved();
        }else{
            System.out.println("There is no seat "+requestedSeatNumber);
            return false;
        }

    }

    public class Seat implements Comparable<Seat>{
        private final String seatNumber;
        private double price;
        private boolean isReserved;

        public Seat(String seatNumber,double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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
