package control_flow_statements.part2_switch_statement;
/*
 * Day of the Week Challenge
 * .........................
 * - Write a method : printDayOfTheWeek that has 1 parameter of type int and name it day
 * - The method should not return any value (hint:void)
 * - Using a switch statement, print "Sunday", "Monday",.."Saturday", if the int parameter day is 0,1,..6 respectively
 * - Otherwise, it should print "Invalid day"
 *
 * Bonus:
 *  - Write a second solution using if-then else, instead of using a switch
 *
 * Solution:
 *  - Both work the same
 */

public class DayOfTheWeekChallenge {

    public static void main(String[] args) {
        printDayOfTheWeek(1); // Monday
        printDayOfTheWeek(3); // Wednesday
        printDayOfTheWeek(0); // Sunday
        printDayOfTheWeek(8); // Invalid day
        printDayOfTheWeek(6); // Saturday
        printDayOfTheWeek(-1); // Invalid day
    }

    public static void printDayOfTheWeek(int day){
        switch (day){
            case 0:
                System.out.println("Sunday");
                break;
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            default:
                System.out.println("Invalid day");
                break;
        }

        //Using if- statement

//        if (day == 0){
//            System.out.println("Sunday");
//        } else if (day == 1) {
//            System.out.println("Monday");
//        } else if (day == 2) {
//            System.out.println("Tuesday");
//        } else if (day == 3) {
//            System.out.println("Wednesday");
//        } else if (day == 4) {
//            System.out.println("Thursday");
//        } else if (day == 5) {
//            System.out.println("Friday");
//        } else if (day == 6) {
//            System.out.println("Saturday");
//        }else {
//            System.out.println("Invalid day");
//        }
    }
}
