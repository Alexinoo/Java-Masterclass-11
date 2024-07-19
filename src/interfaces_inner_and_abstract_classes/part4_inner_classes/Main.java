package interfaces_inner_and_abstract_classes.part4_inner_classes;
/*
 * Inner Classes
 *
 * - It is possible in Java to nest a class inside another class
 * - There are 4 types of nested classes
 *      - Static nested class
 *      - Non-static nested class - also known as an inner class
 *      - local class - declared within a scope , normally a method
 *      - anonymous class - nested class without a class name
 *
 * Static nested class
 *  - mainly used to associate a class with it's outer class
 *  - in terms of behaviour, you'd think of it as being identical to a top-level class
 *  - however, the difference is that it's packaged in its outer class rather than in the package
 *  - this means it cannot access the non-static methods or members of its outer class without first creating an
 *     instance of that class
 *
 * Non-static class / Inner class
 *  - very useful because it doesn't make sense to refer to a class without it's outer class
 *  - For example, we're modelling an engine
 *      - we might have a GearBox class and a Gear class
 *
 *
 *
 * main()
 *  - create a Gearbox instance with a maximum of 6 gears
 *
 * - To actually create an instance of the Gear class, we need to use Gearbox.Gear
 *
 * - It's important to understand the declaration because Gear is an inner class to Gearbox, and therefore, we need
 *   to specify "Gearbox.Gear" and then use the Gearbox instance  "mcLaren" to access inner class constructor
 *      Gearbox mcLaren = new Gearbox(6);
 *      Gearbox.Gear first = mcLaren.new Gear(1,12.3);
 *
 * - This is a slightly different syntax when you're creating an inner class
 * - You'll actually get an error if you try to create a Gear object without having an instance of Gearbox to create
 *   it from
 * - For example you can't do something like this:-
 *       Gearbox.Gear second = new Gearbox.Gear(2, 15.4);
 *       Gearbox.Gear third = new mcLaren.Gear(3, 17.8);
 *
 * - Steps is to
 * - First
 *      - Create an instance of the outer class as shown below
 *          Gearbox mcLaren = new Gearbox(6);
 *
 * - Second
 *      - Use . operator with the instance of the outer class to access the constructor of the inner class
 *          Gearbox.Gear first = mcLaren.new Gear(1,12.3);
 *
 * - The correct syntax might be a bit clunky, but that's how it's done
 * - That's how the designers of Java have implemented this
 *
 * - Another thing to note is that the inner class is going to be private anyway
 * - In other words, instances will only be created by the outer class
 * - The Gear class is something you don't want people to normally directly access anyway, and the instance can
 *   only be created by the outer class
 * - In other words, your public interface that you're exposing to your program here would be Gearbox, and you
 *   won't be exposing Gear directly, you'd exposing the fact that you can change gears
 * - Therefore, you wouldn't be interacting with the Gear itself
 * - Notice, how we added in the Gearbox constructor, a new Gear for neutral by default
 *
 * - Suppose, we change the modifier, of the Gear class to private, we'll get errors because we can't access that
 *      directly anymore and we'll need to make some changes to the Gearbox to actually do that
 *
 *
 *
 *
 * Next,
 * - So, basically, we added gears to our Gearbox via this
 *       this.gears.add(new Gear(number , ratio));
 * - We can change the gear as long as the gear is in range and the clutch is in
 * - And also print out of how fast the wheel is spinning
 *
 * - And if we forget to operate the clutch when changing gears, it'll either grind the gears or cause the engine to scream as it hits the red line
 *   and bounces off the limiter
 *
 * - Notice that we didn't have any references at all now to the Gear class, in order to drive the car and that's because the Gear class is like
 *   local to that Gearbox class itself now
 *
 * N/B
 * ..
 *
 * - addGear(1, 5.3) is really a part of building the Gearbox and not actually using it
 * - therefore, you could consider that it'd be better to put
 *       mcLaren.addGear(1,5.3);
 *       mcLaren.addGear(2,10.6);
 *       mcLaren.addGear(3,15.9);
 *
 * - in the constructor of our Gearbox class, and use a for loop rather than having them as separate calls which they're actually now as
 *   we've added them here
 *
 * - And this still works, and as you can see, the Gear class, the private class can be a good way to improve encapsulation
 * - So, objects only know about other objects that they need to know about
 *
 * - So, in this particular example, nothing apart from the Gearbox needs to have any details about the actual Gear, and so we hide the Gear class
 *    completely by making it a private inner class of the Gearbox class
 *
 * - So, you're not accessing the Gear class at all in anything other than the actual Gearbox class itself
 *
 * - This is a great way to encapsulate that functionality and to restrict other people from accessing it
 *
 * - Obviously, if we were writing a stock control program, then the program would have to have some knowledge of the different gears so that they
 *   can be ordered or request sent to a manufacturer to make them
 *
 * - So it's up to us to decide how our programs should be designed to work, and by doing that or figuring out how to do that, that's really the
 *   great skill of OOP
 *
 *
 * - There's 2 special cases of inner classes:
 *      - local class
 *      - anonymous class
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Gearbox mcLaren = new Gearbox(6);

        //Gearbox.Gear first = mcLaren.new Gear(1,12.3);
        //System.out.println(first.driveSpeed(1000));

        //Add gear , 1, 2, 3
        // Each gear has a different ratio
        // DOne via the Gearbox constructor
        //mcLaren.addGear(1,5.3);
        //mcLaren.addGear(2,10.6);
        //mcLaren.addGear(3,15.9);

        //operate clutch
        mcLaren.operateClutch(true);

        //change gear now that we have press the clutch
        mcLaren.changeGear(1);

        // then set clutch to false
        mcLaren.operateClutch(false);

        // print wheel speed
        System.out.println(mcLaren.wheelSpeed(1000));

        //then try to change to second gear - fail because clutchIsIn is false
        mcLaren.changeGear(2);
        System.out.println(mcLaren.wheelSpeed(3000));

        // press clutch
        mcLaren.operateClutch(true);

        // change to 3-rd gear
        mcLaren.changeGear(3);

        //release clutch
        mcLaren.operateClutch(false);

        // print wheel speed
        System.out.println(mcLaren.wheelSpeed(6000));


    }
}
