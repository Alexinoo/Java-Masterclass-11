package interfaces_inner_and_abstract_classes.part8_abstract_classes;
/*
 * Dog class - extends Animal abstract class
 *
 * - We immediately get an error that the class Dog must either be declared abstract or implement abstract method 'eat()'
 *   from the 'Animal' class
 * - You can see now, in order to create a valid class, we have to implement those method(s)
 *
 * Fields
 *  - Inherits the name right away from the Animal class
 *
 * Constructor
 *  Dog(String name)
 *      - call the Animal constructor using the super keyword, which calls the constructor that is actually in the
 *         Animal class
 *
 * Implement/Override abstract methods
 *    eat() : void
 *      - print something
 *      - call getName() which was defined in the Animal class, but since , the Dog class is extending to it, it
 *        becomes available to us as well
 *
 *    breathe() : void
 *      - print something
 *
 * -
 */
public class Dog extends Animal{

    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName()+ " is eating");
    }

    @Override
    public void breathe() {
        System.out.println("Breathe in , breathe out, repeat");
    }
}
