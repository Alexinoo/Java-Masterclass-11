package java_files_input_output._01_exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int x = 98;
        int y = 0;
        System.out.println( divideLBYL(x,y));
        System.out.println( divideEAFP(x,y));
        //System.out.println( divide(x,y));

        //int number = getInt();
        //System.out.println("number is "+number);

        //int number = getIntLBYL();
        //System.out.println("number is "+number);

        int number = getIntEAFP();
        System.out.println("number is "+number);
    }

    private static int getInt() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter your integer : ");
        return scanner.nextInt();
    }

    private static int getIntLBYL() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter an integer : ");
        boolean isValid = true;
        String input = scanner.next();
        for (int i = 0; i < input.length() ; i++) {
            if (!Character.isDigit(input.charAt(i))){
                isValid = false;
                break;
            }
        }
        if (isValid)
            return Integer.parseInt(input);
        return -1;
    }

    private static int getIntEAFP(){
        scanner = new Scanner(System.in);
        System.out.println("Please enter your integer : ");
        try{
            return scanner.nextInt();
        }catch (InputMismatchException e){
            return -1;
        }
    }

    private static int divideLBYL(int x , int y){
        if ( y != 0)
            return x/y;
        return 0;
    }

    private static int divideEAFP(int x , int y){
       try{
           return x/y;
       }catch (ArithmeticException e){
           return 0;
       }
    }

    private static int divide(int x , int y){
            return x/y;
    }
}
