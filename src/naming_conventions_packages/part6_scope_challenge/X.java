package naming_conventions_packages.part6_scope_challenge;

import java.util.Scanner;

public class X {
    private int x;

    public X(Scanner x) {
        System.out.println("Enter number to print multiplication table for: ");
        this.x = x.nextInt();
    }

    public void x(){
        for (int x = 1; x <= 12; x++) {
            System.out.println(x + " times "+ this.x  +" equals "+ (x * this.x));
        }
    }
}
