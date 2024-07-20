package interfaces_inner_and_abstract_classes.part9_abstract_classes_2;
/*
 * Difference between an abstract class and interface, and how do you actually go about deciding which one to use
 *
 * - We need to consider the relationships
 *      - "is a " vs "has a" or "can"
 * - In other words:
 *      - "a Dog is an Animal"
 *      - "a Bird is an Animal"
 * - That makes sense for both Dog and a Bird to inherit from an Animal class, rather than implementing an actual Animal
 *   interface
 * - A "Parrot is a Bird" and once again, you would inherit from a Bird based class and we've actually done that
 *
 * - But there is a problem with this example though, as birds aren't the only animals that can fly
 *
 * - "Bats" are perfect at it, and even dragon flies are just 2 examples
 * - But a "Bat" is not a bird though and to get around that, we could name our Bird class Flying animals, and have Birds
 *   and Bats both inherit from that
 *
 * - However, bats give birth to young ones rather than laying eggs
 * - This may lead to having flying animals being inherited by classes called Mammals and Birds
 * - But again, not all animals can fly, and we can run into difficulties because we got it wrong at the base level
 *
 * - So, what actually, went wrong here is putting the fly() in the Bird class
 *      - We added an abstract method fly() in the Bird class
 *      - A Bird can fly and a bat can fly
 * - A better design would be to have created a canFly interface, which both bats and birds can implement
 *
 *
 * So, let's actually do that
 *  - create canFly interface
 *
 * Next
 *  - Go to the Bird class which now extends Animal class, but we also now implements CanFly interface
 *      - then override fly() from the CanFly interface
 *
 * Next
 *  - Go to the Parrot class and remove fly() , since it's going to be inherited from the Bird's class automatically
 *
 * Next
 *  - And also looking at Penguin class, it now makes sense to override that and leave that overridden, because, we
 *     want to implement some specific functionality for the Penguin class
 *
 *  - And if we wanted to, we can also call super.fly()
 *      - prints "Emperor is flapping its wings"
 *      - also prints "I am not very good at that, can I go for a swim instead ?"
 *
 *  - And the argument for actually leaving the super.fly() for the Penguin, is that the Penguin has got those little
 *    wings that it can flap, but it can't actually fly
 *      - It doesn't get very far
 *  - So, now e can have a Bat and a Dragon fry class that inherit from a suitable base class, and then both could then
 *    implement the CanFly interface
 *  - So, by adding the CanFly interface, we're actually adding more flexibility into the design, to enable us to create
 *    other classes
 *      - without falling victims to the potential problems you would have had, trying to fit everything into the
 *        one class we had earlier
 *
 * Summary between the differences between an abstract class and an interface
 *  - An abstract class can have member variables that are inherited, which isn't possible with an interface
 *  - Interfaces can have variables, bit they are all public static final variables , or rather constant values that
 *    should never change with a static scope
 *  - They have to be static because, non-static variables require an instance, and of course, you can't instantiate
 *    an interface
 *  - Interfaces also cannot have constructors, but abstract classes can
 *  - All method(s) in an interface are automatically public by default, whereas the method(s) of an abstract class can
 *    have any visibility. "private", "protected" e.t.c
 *  - Abstract classes can have defined method(s) ,
 *      - methods with an implementation
 *  - All methods in an interface are abstract, and all the implementations is done at the class level, that implements
 *    a particular interfaces
 *
 *  - As , we can see, the Animal class implemented the getName(), and as a result that code does not have to be
 *    reproduced in all classes that inherit from this abstract class
 *
 */
public class Main {
    public static void main(String[] args) {
        Penguin penguin = new Penguin("Emperor");
        penguin.fly();
    }
}
