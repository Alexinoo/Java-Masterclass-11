package interfaces_inner_and_abstract_classes.part9_abstract_classes_2;

/*
 * Bird class
 *  - extends Animal class
 *      - inherits name and getName() from the Animal class
 *      - must implement
 *          abstract eat() : void
 *          abstract breathe() : void
 */
public abstract class Bird extends Animal implements CanFly {

    public Bird(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName()+ " is pecking");
    }

    @Override
    public void breathe() {
        System.out.println("Breathe in , breathe out, repeat");
    }

    @Override
    public void fly() {
        System.out.println(getName() + " is flapping it's wings");
    }
}
