package interfaces_inner_and_abstract_classes.part8_abstract_classes;
/*
 * Bird class
 *  - extends Animal class
 *      - inherits name and getName() from the Animal class
 *      - must implement
 *          abstract eat() : void
 *          abstract breathe() : void
 */
public abstract class Bird extends Animal{

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

    public abstract void fly();
}
