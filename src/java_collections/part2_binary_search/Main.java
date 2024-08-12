package java_collections.part2_binary_search;

/*
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

        //////// Check for D12 which could give use a best case scenario ///////////
        theatre.reserveSeat("D12");
    }
}
