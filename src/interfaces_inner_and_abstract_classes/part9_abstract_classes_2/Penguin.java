package interfaces_inner_and_abstract_classes.part9_abstract_classes_2;

public class Penguin extends Bird {

    public Penguin(String name) {
        super(name);
    }

    @Override
    public void fly() {
        super.fly();
        System.out.println("I am not very good at that, can I go for a swim instead ?");
    }
}
