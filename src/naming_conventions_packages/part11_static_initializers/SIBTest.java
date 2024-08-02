package naming_conventions_packages.part11_static_initializers;

public class SIBTest {
    public static final String owner;

    static {
        owner = "Alex";
        System.out.println("SIBTest static initialization block called");
    }

    public SIBTest() {
        System.out.println("SIB constructor called");
    }

    static {
        System.out.println("2nd initialization block called");
    }

    public void someMethod(){
        System.out.println("someMethod() called");
    }
}
