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
 * - Instead an object of type Comparator can be created with a compare method that can sort the objects we're interested
 *   in , which in this case it's the seats
 * - More than 1 comparator can be created and it allows objects to be sorted in different ways which is pretty cool
 * - We can either create a Comparator object within an existing class , or we could create a new class that implements
 *   that comparator interface
 *
 * //// Using anonymous class ///////
 * - Use an anonymous class within the Theater class
 * - Added it as a static field
 *
 *      static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice())
                return -1;
            else if (seat1.getPrice() > seat2.getPrice())
                return 1;
            else
                return 0;

            }
        };
 *
 * - That's how simple the compare() can be
 * - It may look like we're instantiating the Comparator interface - but remember from section-10, it's an anonymous
 *   inner class implementing comparator & it's providing an implementation of the single compare()
 * - The compare() is very straight forward and has to return the same result as the compareTo() does
 *      - i.e.
 *             Negative if seat1 < seat2
 *             Positive if seat1 > seat2
 *             Zero if seat1 == seat2
 *
 * - However, there's a serious problem with this method, and a potential problem that we need to be aware of
 *
 * - To see this in action
 *      - Let's create a new ArrayList<Seat> and then add a couple of seats to list and then need to sort in the
 *        order of price, with the cheapest seats first
 *      - We'll pass our new comparator to the Collections.sort()
 *
 *      List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00",13.00));
        priceSeats.add(theatre.new Seat("A00",13.00));
 *
 * - Next, call Collections.sort() on our priceSeats list and pass our comparator
 *      Collections.sort(priceSeats,Theatre.PRICE_ORDER);
        printList(priceSeats);
 *
 * - Next, print the seats
 *
 * - The seats are printed in price order, and it's worth noting that the the sort routines used is guaranteed to be
 *   stable
 * - This means the elements won't be swapped if they don't need to be, so that all seats with the same price appear
 *   in seat number order
 *      - And that's because that's how they were entered
 *      - The new 2 seats also appear in the original order, i.e "B00" , comes before "A00" because that's the order
 *        they're added to the list
 *      - Again, the way this sort routine works, elements aren't swapped if they don't need to be
 *      - In this way we're looking at the comparator which is purely based on price , and that's why it's not looking
 *        at the seat number to determine any sort of sort criteria
 *
 * - We can add as many comparators as we want & we could include one to list the highest price seats first
 *      - The Comparator doesn't have to be static & it makes easy to use since we don't need a class instance to
 *        use it
 *      - In this case we made the Comparator a static variable of the Theater class, and that's because the Theater
 *        class is controlling all access to the Seats
 *      - It could have made sense also to include it in the Seat class itself, especially if Seat class was not an
 *        inner class
 *      - But had we done that there would be no seat instance available in main to call on, and so static would for
 *        that reason be a lot more convenient
 *
 * Potential Problems with Comparator
 *  - The instructor did allude to a potential problem that could come up when you're using compareTo() or the compare()
 *  - With regards to the java documentation on collections framework, there's a lot of talk about it abd they use the
 *    words such as "an ordering being consistent with equals"
 *
 *  - What this means is that a method that produces ordering that is consistent with equals will only return 0 if the
 *    elements being compared to are actually equal
 *  - Our compareTo() behaves well in this respect,
 *
 *       public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }
 *  - So if 2 seat objects have the same seat number, then they're the same seat effectively, and the String.compareTo()
 *    will return 0 and that's quite correct
 *
 *  - But looking at our Comparator compare() though
 *
 *       public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice())
                return -1;
            else if (seat1.getPrice() > seat2.getPrice())
                return 1;
            else
                return 0;

            }
 *
 *  - This is definitely not consistent with equals()
 *  - Comparing any of the seats with the same price , will return 0 when they are quite obviously not the same seat
 *    because of course, we can have multiple seats with the same price
 *  - This causes problems with sorted sets and maps actually if they're sorted using Comparators such as the one we've
 *     used here and that's inconsistent with equals
 *
 * Solution
 *  - All you need is to do a further test on the seatNumber whenever the price is equal just as we described for first
 *    and last names
 *  - Will leave as is for now so that we can see the problems that can arise with Sorted sets and Sorted maps later
 *    in this video
 *  - The bottom line is that we can't use the compare() as we've done here without more work using the price because
 *    it's going to be more than 1 seat return an equals , which is a 0 , because the price is the same
 *  - need to break it down a little bit further
 *
 *
 *
 *
 *
 *
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

        //Using comparator
        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00",13.00));
        priceSeats.add(theatre.new Seat("A00",13.00));

        Collections.sort(priceSeats,Theatre.PRICE_ORDER);
        printList(priceSeats);

    }

    private static void printList(List<Theatre.Seat> list){
        for (Theatre.Seat seat: list){
            System.out.print(" "+seat.getSeatNumber() + " $"+ seat.getPrice());
        }
        System.out.println();
        System.out.println("============================================================");
    }
}
