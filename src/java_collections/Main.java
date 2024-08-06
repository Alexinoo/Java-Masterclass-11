package java_collections;

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
