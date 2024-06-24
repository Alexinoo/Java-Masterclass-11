package java_tutorial.part12_bonus_challenge_solution;
/*
 * Seconds and Minutes Challenge - Bonus Challenge
 * ...............................................
 *
 * - Display a leading 0 -(zero) if we only have 1 number for hours, minutes or seconds
 * - We can use if statement to check when there's only 1 digit
 *
 * - But when do we actually have 1-digit
 *      - there is 1 digit when the number is less than 10
 *
 * - There are a number of ways to format, but we'll use the knowledge presented so far in this solution by using
 *   the if-statement
 *
 * - Create a String as follows
 *      e.g. hours + "h"
 *  - Check if the hours is less than 10
 *      - prepend "0" as a String to hourString
 *
 * - Apply the same concept for both minutes and seconds
 *
 * Alternative
 * ...........
 * - There is a method in Java "join()" we could use for this
 *      - But we are using the knowledge that we've covered so far
 *
 * - There are more optimal ways of doing this
 *
 * - The code can also be optimized because of the repeating if-statements
 *      - we can create a method and eliminate the repeating code
 *      - created appendZero(long unitNumber, String unit)
 *
 * Next,
 *  - Let's use constants for repeated string value "Invalid value"
 *
 * - A constant is a variable that we cannot change its value, once we have assigned it
 * - Constants can be very useful, we've repeated "Invalid value" String twice for invalid values, when the parameters
 *   aren't valid
 *
 * - Instead of typing the same String twice, we can create a constant instead
 *
 * - In order to use this constant, it must be declared outside the method
 *
 * Next,
 *  - Declare
 *      public static final String INVALID_VALUE_MESSAGE = "Invalid value";
 *  - Constants are conventionally typed in UPPERCASE, so that they're easily identified as constants in our program
 *    code because they're not going to be changed
 *  - substitute to the return statement
 *
 * - Constants are also useful in case you're making a typo, when you're typing the same string multiple times in your
 *   program, it's easy to make a typo
 *      - e.g., typing a letter in uppercase, or forget a letter or things along those lines
 *
 * - They help us to modify the code, suppose we wanted to change the message to some other string, we'll have to
 *   do it in 1 place
 *
 */
public class SecondsAndMinutesBonus {

    public static final String INVALID_VALUE_MESSAGE = "Invalid value";

    public static void main(String[] args) {
        System.out.println(getDurationString(65L,45L));
        System.out.println(getDurationString(61L,0));
        System.out.println(getDurationString(3660L));
        System.out.println(getDurationString(2560L));
        System.out.println(getDurationString(3945L));
        System.out.println(getDurationString(5925L));
        System.out.println(getDurationString(40926L));
        System.out.println(getDurationString(-41L));
        System.out.println(getDurationString(65L,9));
    }

    private static String getDurationString(long minutes , long seconds){
        if ( (minutes < 0) || (seconds < 0 ) || (seconds > 60)){
            return INVALID_VALUE_MESSAGE;
        }
        long hours = minutes / 60;

//        String hourString = hours + "h";
//        if (hours < 10){
//            hourString = "0" + hourString;
//        }
        String hourString = appendZero(hours , "h");

        long remainingMinutes  = minutes % 60;

//        String minuteString = remainingMinutes + "m";
//        if (remainingMinutes < 10){
//            minuteString = "0" + minuteString;
//        }
        String minuteString = appendZero(remainingMinutes,"m");

//        String secondsString = seconds + "s";
//        if (seconds < 10){
//            secondsString = "0" + secondsString;
//        }

        String secondsString = appendZero(seconds , "s");

        return hourString +" "+ minuteString +" "+ secondsString;
    }

    private static String getDurationString(long seconds){
        if (seconds < 0){
            return INVALID_VALUE_MESSAGE;
        }
        long noOfMinutes = seconds / 60;
        long remainingSeconds= seconds % 60;

        return getDurationString(noOfMinutes , remainingSeconds );
    }

    public static String appendZero(long unitNumber, String unitString){
        String numberString = unitNumber + unitString;
        if (unitNumber < 10){
            numberString = "0" + numberString;
        }
        return numberString;
    }
}
