package interfaces_inner_and_abstract_classes.part9_abstract_classes_2;
/*
 * Animal class
 *  - make it abstract by adding abstract keyword
 *
 * Fields:
 *  name : String
 *
 * Constructor
 *  Animal(String name)
 *     - takes the name of the animal
 *
 * abstract methods
 *  public abstract eat() : void
 *  public abstract breathe() : void
 *
 * Getter
 *  getName : String
 *
 * Next,
 *  - we'll inherit from this class & it's going to let us define behaviors that are necessary without specifying
 *    how they are to be performed
 *  - this ensures that subclasses, such as the dog, must actually implement them
 *  - normally, if you're extending from a class, you can create a base method in the class, but there is no requirement
 *    for the other class that is inheriting from the base class to actually implement those methods
 *  - but by creating abstract methods, we're actually forcing a class that will inherit from this class to actually
 *    implement those methods for us
 *  - And that's why we have marked the methods as abstract
 *  - the other methods/fields are quite Okay, and will work as normal Java code, but we're actually specifying there's
 *    some abstract methods here that have to be implemented
 *
 * Next
 *  - Create a Dog class that extends the Animal class
 *
 *
 */
public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }
    public abstract void eat();
    public abstract void breathe();
    public String getName() {
        return name;
    }
}
