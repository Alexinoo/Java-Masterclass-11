package control_flow_statements.part7_while_and_do_while_recap;
/*
 * Digit Sum challenge
 * ...................
 *
 * - Write a method with the name sumDigits that has 1 parameter called number
 *      - if parameter >= 10, then the method should process the number and return the sum of all digits
 *      - otherwise, return -1 to indicate an invalid value
 * - numbers from 0 to 9, have 1 digit, so we don't want to process them
 * - also we don't want to process negative numbers, so return -1 for negative numbers
 *
 * Example:
 *  - if we call sumDigits(125) should return 8 since 1 + 2 + 5 = 8
 *  - if we call sumDigits(1) should return -1 as per the requirements described above
 *
 * - Add some code to the main(), to test out the sumDigits method to determine that it is working correctly for valid
 *   and invalid values passed as arguments
 *
 * Hint:
 *  - use n % 10 to extract the least-significant digit
 *  - use n = n /10 to discard the least significant digit
 */
public class DigitSumChallenge {

    public static void main(String[] args) {
        System.out.println("Sum of 125 = "+ sumDigits(125));
        System.out.println("Sum of 1 = "+ sumDigits(1));
        System.out.println("Sum of 328 = "+ sumDigits(328));
        System.out.println("Sum of 1001 = "+ sumDigits(10001));
        System.out.println("Sum of 10 = "+ sumDigits(10));
        System.out.println("Sum of 100 = "+ sumDigits(100));
    }

    public static int sumDigits(int number) {
        if (number < 10) {
            return -1;
        }

        int sum = 0;
        while(true){
            int lastDigit = number % 10; //125%10 = 5 ; 12%10 = 2 ; 1%10 = 1
            sum += lastDigit;             // 0 + 5 ; 5 +2 ; 7 + 1
            number = number / 10;       // 125/10 = 12 ; 12/10 = 1; 1/10 = 0

            if(number == 0)
                break;
        }
        return sum;
    }

}
