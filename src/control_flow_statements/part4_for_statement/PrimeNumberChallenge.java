package control_flow_statements.part4_for_statement;
/*
 * Prime Number
 * ............
 *
 * - A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 * - In other words, a prime number is a number that cannot be formed by multiplying two smaller natural numbers.
 *
 * For example:
 *   2 is a prime number because its only divisors are 1 and 2.
 *   3 is a prime number because its only divisors are 1 and 3.
 *   4 is not a prime number because it can be divided by 1, 2, and 4 (it can be formed by multiplying 2 by 2).
 * - The first few prime numbers are: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, and so on.
 *
 * isPrime()
 *  - tests if no is 1, and if so, returns false since it has to be greater than 1
 *
 *  - another way to look at it is that a prime number has only 2 divisors
 *      - 1 and the number itself
 *  - if the divisors > 2 , then it's not prime
 *  - therefore we need a loop that starts from 2 to the given number
 *  - for example: given number 7
 *      2,3,4,5,6,7
 *  - check if it's divisible by numbers to and if the divisors > 2, then it's not prime
 *
 * Challenge
 * .........
 * - Create a for-statement using any range of numbers
 * - Determine if the number is a prime number using isPrime()
 * - if it is, print it out and increment a count of the prime numbers found
 *      - if the count > 3 , exit the for loop
 * - hint:
 *      - use the break statement to exit
 *
 * N/B
 * ...
 * - We can also use a square root for further optimization
 *     - minimises the no of times that we need to loop
 */
public class PrimeNumberChallenge {

    public static void main(String[] args) {

        int count = 0;
        for (int i = 11; i <= 11; i++) {
            System.out.println(isPrime(i));
            if (isPrime(i)){
                count++;
                System.out.println(i +" is a prime number");
                if (count == 10) {
                    System.out.println("Exiting for loop");
                    break;
                }
            }

        }
    }

    public static boolean isPrime(int n){
        if (n <= 1)
            return false;

        // Option 1 /////

//        int count = 1;
//        for (int i = 2; i <= n; i++){
//            if (n % i == 0) {
//                count++;
//            }
//            if(count > 2){
//                return false;
//             }
//        }
//        return true;

        // Option 2 - Though the number of iterations will be significantly high
        //////////////////////////////////////////////////////////////////////////

//        for (int i = 2; i <= n/2 ; i++) {
//            if (n % i == 0) {
//                return false;
//            }
//        }
//        return true;

        // Option 3 - optimized - number of iterations are less
        //////////////////////////////////////////////////////////////////////////

        for (int i = 2; i <= (long) Math.sqrt(n) ; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
