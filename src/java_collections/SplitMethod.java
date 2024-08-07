package java_collections;

import java.util.Arrays;

public class SplitMethod {

    public static void main(String[] args) {
        String[] road = "You are standing at the end of the road before a small brick building".split(" ");
        String[] building = "You are inside a building, a well house for a small spring".split(", ");

        System.out.println(Arrays.toString(road));

        System.out.print("============================\n");

        System.out.println(Arrays.toString(building));
    }
}
