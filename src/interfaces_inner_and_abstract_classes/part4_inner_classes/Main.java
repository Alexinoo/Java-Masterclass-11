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
 */
public class Main {
    public static void main(String[] args) {
        Gearbox mcLaren = new Gearbox(6);
        Gearbox.Gear first = mcLaren.new Gear(1,12.3);
        System.out.println(first.driveSpeed(1000));
    }
}
