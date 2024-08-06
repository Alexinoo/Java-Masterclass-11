package java_collections;

import java.util.*;

public class Theatre {

    private final String theatreName;

    private List<Seat> seats;

    public Theatre(String theatreName , int numOfRows , int numberOfSeatsPerRow) {
        this.theatreName = theatreName;
        this.seats = new ArrayList<>();

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

        // Brute Force search //////////
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

        //Collections.binarySearch(seats,requestedSeatNumber,null);

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
            return this.seatNumber.compareToIgnoreCase(seatNumber);
        }
    }


}
