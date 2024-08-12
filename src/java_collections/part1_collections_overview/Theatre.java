package java_collections.part1_collections_overview;

import java.util.*;

/*
 * Theatre Class with an inner class Seat
 *
 * Fields
 *  theatreName : final String
 *  seats : List<Seat>

 * Constructor
 *  Theatre(String theatreName , int numberOfRows , int numberOfSeatsPerRow)
 *      - Initialize Theatre name
 *      - Create last row which ideally should be rows starting with letter 'A' + (numberOfRows - 1)
 *          e.g.
 *              int lastRow = 'A' + (numberOfRows - 1)
 *          - if we pass numberOfRows as 8, then, lastRow will be
 *              int lastRow = 'A' + (8 - 1)
 *              int lastRow = 65 + 7 = 72
 *
 *      - Loop through the first letter 'A' up to 72 rep by character 'Z' and allocate number of seats in that seat as
 *          - Assuming number of seats are 12, the final result will be
 *              A01, A02,A03,A04,A05,A06,A07,A08,A09,A10,A11,A12
 *              B01, B02,B03,B04,B05,B06,B07,B08,B09,B10,B11,B12
 *              ...
 *              ...
 *              H01, H02,H03,H04,H05,H06,H07,H08,H09,H10,H11,H12
 *
 *          - so on until H12
 *              - create a Seat obj and pass (row + String.format("%02d",seatNum)) to the constructor
 *                  - prints with leading zero's
 *              - Then add them to the List<Seat> seats obj
 *
 *
 * Getter
 *     getTheatreName() : String
 *
 * Methods
 *      reserveSeat(String seatNumber) : boolean
 *          - reserve a particular seat for our Theatre
 *          - loop through the List<seat> seats and check for requested seat, break out of the loop once found
 *          - if not found - print to the user, there is no such seat number
 *              - return false in that case
 *          - Otherwise
 *              - call reserve() from the Seat class which returns true/false on whether the current seat was available
 *
 *
 *      getSeats() : void
 *          - loop through and print out the List<Seat> seats by calling seatNumber from the Seat class
 *
 *
 * Seat class - private
 *
 * Fields
 *  seatNumber : String
 *  reserved : boolean
 *
 * Constructor
 *  Seat(String seatNumber) - initialize seat number
 *
 * Methods
 *  reserve : boolean
 *      - check if reserve is false for the requestedSeat
 *          - if not reserved , then set to reserve
 *          - print seatNumber was reserved
 *          - return true
 *      - otherwise, means it means the seat was reserved and we can return false in that case
 *
 *  cancel  : boolean
 *      - does the other way round
 *      - if the seat was reserved
 *          - set it to false so that it can be allocated again
 *          - print "reservation for the seatNumber was cancelled"
 *          - return true
 *      - Otherwise
 *          - return false as it means that the seat wasn't already reserved
 *
 * Getter
 *   getSeatNumber : String
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
       Seat requestedSeat = null;
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
        return requestedSeat.isSeatReserved();
    }

    private class Seat{
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

    }

}
