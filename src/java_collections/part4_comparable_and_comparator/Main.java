package java_collections.part4_comparable_and_comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Comparable and Comparator
 *
 * - Add a Theatre
 *      - Reserve a few seats : D12 , B13
 *
 * - printList()
 *      - adjust printList() to print the price
 *
 * - This works as expected, and since the Seat class itself implements Comparable, we had to override compareTo()
 *
 * compareTo
 *  - returns a number less than 0, if the object sorts less than the object that its being compared to
 *      e.g. 2.compareTo(3)  = -1
 *
 *  - returns a number greater than 0, if the object sorts greater than the object that its being compared to
 *      e.g. 3.compareTo(2)  = 1
 *
 *  - returns 0, if equal
 *
 *  - Since this is what String.comparesTo() does, and the String.compareToIgnoreCase() does as well because that's just the same, but it's just
 *    ignoring the case in letters of the alphabet we can use that to implement our comparison
 *
 *  - If you're creating a class that's got more than 1 key field such as people's names, you would probably sort first on surname, then on firstname
 *    and people share the surname, you just perform a test of surnames, and return a positive or negative number as appropriate
 *      - if the surnames are equal, instead of returning 0, you then test the first name and return -ve, +ve or 0 depending on how the first names
 *          compared
 *
 * Next
 *  - Create a list of reversedSeats
 *      - call Collections.reverse() on reversedList seats
 *      - print the list
 *
 * - There's also another way to use Collections.sort() and that's by passing it a comparator
 *      - It's similar to a Comparable
 *
 * Comparator Interface
 * - The Comparator Interface defines a single method called compare
 * - Unlike Comparable, the objects to be sorted don't have to implement Comparator
 * - Instead an object of type Comparator can be created
 *
 */
public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian",8,12);

        if (theatre.reserveSeat("D12")){
            System.out.println("Please pay for D12");
        }else{
            System.out.println("Seat already reserved");
        }

        if (theatre.reserveSeat("B13")){
            System.out.println("Please pay for B13");
        }else{
            System.out.println("Seat already reserved");
        }

        List<Theatre.Seat> reversedSeats = new ArrayList<>(theatre.getSeats());
        Collections.reverse(reversedSeats);
        printList(reversedSeats);

    }

    private static void printList(List<Theatre.Seat> list){
        for (Theatre.Seat seat: list){
            System.out.print(" "+seat.getSeatNumber() + " "+ seat.getPrice());
        }
        System.out.println();
        System.out.println("============================================================");
    }
}
