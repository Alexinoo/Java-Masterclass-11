package naming_conventions_packages.part9_static_keyword;

public class StaticTest {
    private String name;
    private static int numInstances = 0;

    public StaticTest(String name) {
        this.name = name;
        this.numInstances++;
    }

    public String getName() {
        return name;
    }

    public static int getNumInstances() {
        return numInstances;
    }
}
