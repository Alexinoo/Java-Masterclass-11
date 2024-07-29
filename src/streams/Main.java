package streams;

public class Main {

    public static void main(String[] args) {
        //nSum(int n) - returns the sum of all numbers from 0 to n
        // The first 10 numbers are : 0, 1 , 3 , 6 , 10 , 15 , 21 , 28 , 36 , 45 , 55
        for (int i = 0; i <= 10 ; i++) {
            System.out.print(nSum(i) + " ");
        }

        System.out.println();

        //factorial
        for (int i = 0; i <= 10; i++) {
            System.out.print(factorial(i) + " ");
        }
        System.out.println();

        //fibonacci series - return nth Fibonacci number
        for (int i = 0; i <= 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    public static long nSum(int n){
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return n + nSum(n - 1);
    }

    public static long factorial(int n){
        if (n == 0)
            return 1;
        return n * factorial(n - 1); // 5 * 4!
    }

    public static long fibonacci(int n){
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
