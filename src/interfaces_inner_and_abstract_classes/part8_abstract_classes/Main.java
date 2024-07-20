package interfaces_inner_and_abstract_classes.part8_abstract_classes;

/*
 * Abstract Classes
 * ................
 *
 * - Before we talk about abstract classes, let's understand first what abstraction really means
 *
 * - Abstraction is when you define the functionality for something without actually implementing the details
 * - In short, we're focusing on what needs to be done and not how it's to be done
 * - So, interfaces, are purely abstract as they don't specify any actual aspect of the implementation
 * - The actual implementation is left to the classes that implement this interface and that's why we're using the
 *   word implements when we're using an interface
 * - Interface defines what needs to be done, it doesn't define how it's done
 * - Interfaces are by definition, in Java, abstract
 *
 * - Java also allows abstract classes
 * - These are classes that defines methods, but do not provide an implementation of those methods
 * - The implementation itself is left to the classes that inherit from the abstract class
 * - So, this is different from inheriting from an interface, you can also inherit from an actual class as well
 *
 * Use case
 *  - Create an Animal class and make it abstract by adding "abstract" keyword
 *      - add fields
 *          name : String
 *      - constructor
 *          Animal(String name)
 *      - add abstract method(s)
 *          abstract eat() : void
 *          abstract breathe() : void
 *  - Create a Dog class that inherits from the abstract class 'Animal'
 *      - implement/ override
 *          eat() : void
 *          breathe() : void
 *
 * - Next,
 *  - Not all method(s) in the Animal class, have to be abstract
 *
 * - This is one of the differences between an abstract class and an interface
 *
 * - An interface is completely abstract, and there's no implementation whatsoever, we only specify methods in that interface
 *   that a class that's going to implement that particular interface had to implement to make it valid
 *
 * - But, with Abstract class, such as the Animal class that we have created
 *      - we can add fields such as
 *          name : String
 *      - we can add constructor
 *          Animal(String name)
 *      - we can add regular methods such as getName()
 *          getName() : String
 *      - we can also specify that certain method(s) are abstract
 *          abstract eat() : void
 *          abstract breathe() : void
 *
 * - Next
 *     - Let's create a Bird class that extends the Animal class
 *      - add the necessary fields and implement the abstract methods as well
 *
 * - Next
 *  - Birds can also fly, it could be a good idea to add a fly method to our bird class
 *  - But, not all birds can fly and so implementing a fly() in the Bird class is not a good idea
 *  - A better idea would be to create an abstract Bird class, that extends Animal, and also has an abstract fly() that
 *    individual bird object(s) can implement as they are able to
 *
 * - So, let's do that
 *      - update Bird class to be abstract
 *      - add fly method and mark it as abstract
 * - The Bird class now is an abstract class that is now extending from another abstract class 'Animal'
 * - It's also implementing the required method(s) that the Animal class requires it to, namely
 *      eat()
 *      breathe()
 * - And it's also defining an abstract method
 *      fly()
 * - That ultimately has to be overridden by another class as well, and of course we've made the Bird class abstract
 *   which it needs to be in order to do this anyway
 *
 * - N/B
 *  - You can't directly instantiate a class that is abstract
 *  - For example, if we do as below
 *      Bird bird = new Bird("Parrot");
 *  - we'll get an error
 *
 *  - Therefore, we need to create another class, which we'll name as Parrot that extends the Bird class
 *      - The parrot class will automatically inherit eat() and breathe() from Bird class and so we don't have to
 *          re-implement those method(s) again
 *      - And it now has its own implementation for fly()
 *
 * Next
 *  - Create a Penguin class that extends the Animal class
 *      - Add a constructor and implement the fly()
 *
 */
public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("Yorkie");
        dog.breathe();
        dog.eat();

//        Bird bird = new Bird("Parrot");
//        bird.breathe();
//        bird.eat();

        Parrot parrot = new Parrot("Australian Ringneck");
        parrot.breathe();
        parrot.eat();
        parrot.fly();

        Penguin penguin = new Penguin("Emperor");
        penguin.fly();
    }
}
