package naming_conventions_packages.part10_final_keyword;

public class SomeClass {
    public final int instanceNumber;
    private static int classCounter = 0;

    private final String name;


    public SomeClass(String name) {
        this.name = name;
        classCounter++;
        instanceNumber = classCounter;
        System.out.println(name +" created, instance is "+instanceNumber);
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }
}
