package java_tutorial.part10_method_overloading_challenge;
/*
 * Seconds and Minutes Challenge
 * .............................
 *
 */
public class SecondsAndMinutes {

    public static void main(String[] args) {
        System.out.println(getDurationString(65L,45L));
        System.out.println(getDurationString(61L,0));
        System.out.println(getDurationString(3660L));
        System.out.println(getDurationString(2560L));
        System.out.println(getDurationString(3945L));
    }

    private static String getDurationString(long minutes , long seconds){
        if ( (minutes < 0) || (seconds < 0 ) || (seconds > 60)){
            return "Invalid value";
        }
        long hours = minutes / 60;
        long remainingMinutes  = minutes % 60;
        return hours +"h "+ remainingMinutes+ "m "+ seconds+ "s";
    }

    private static String getDurationString(long seconds){
        if (seconds < 0){
            return "Invalid value";
        }
        long noOfMinutes = seconds / 60;
        long remainingSeconds= seconds % 60;

        return getDurationString(noOfMinutes , remainingSeconds );
    }
}
