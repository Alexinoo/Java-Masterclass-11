package naming_conventions_packages.part11_static_initializers;
/*
 * Static Initializers
 *
 * - Constructors are also known as instance constructors and they're not static and they'll be executed everytime
 *   we create a new instance of a class
 *
 * - Now the static equivalent of that is a static initialization block and the difference is that this block is
 *   executed once when the Class is first loaded into the project
 *
 * - It's no often at all that you need to create a class constructor which is sort of what a static initialization
 *   block is even though that's not really the correct term for it
 *
 * - The reason this is the case is that the static final variable MUST BE initialized by the time all static
 *   initialization blocks terminate
 * - So the same way we can set the value of a final field in the constructor, we can also assign the value of a
 *   static final variable in the static initialization block
 * - Static initialization blocks are an advanced feature and rarely will I use them
 *
 * Example
 *  - Let's print some text using the program flow, so that we can see when each part of the program is executed
 *  - We'll assign a value to the final static variable using an initialization block
 *
 * Next
 *  - Create SIBTest class
 *  - Fields
 *      owner : static final String
 *
 *  - Add an initialization block
 *      static {
 *          owner == "Tim";
 *      }
 *  - Add a constructor
 *      SIBTest()
 *      - print "SIB constructor called"
 *  - Add another static initialization block
 *
 *  - Methods
 *      someMethod() : void
 *      - print "someMethod() called"
 *
 * - Note the static initialization blocks are declared using the static keyword , and there can be as many static
 *   initialization blocks as you would like
 *      - we've added 2 in this class for illustration purposes
 *      - and are also called in the order in which they're declared in the class
 *      - we've added 1 before the constructor and after it , though it's not a good practice & it can lead to people
 *        to expect the constructor to be called before the second block which isn't the case
 *
 * Next
 *  - Create SIBTest instances from the main()
 *
 * After running this:
 *  - Interestingly enough
 *  - the line below is executed first
 *       System.out.println("Main method called");
 *
 *  - then both the static initialization blocks were called before the constructor
 *       System.out.println("SIBTest static initialization block called");
 *       System.out.println("2nd initialization block called");
 *
 *  - and in fact they're called before any non-static methods including the constructor
 *
 *  - and also we're able to print the value of Owner = "Alex" which was set in the initialization block which means
 *    the static code below was executed
 *
          static {
                owner = "Alex";
                System.out.println("SIBTest static initialization block called");
            }
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Main method called");

        SIBTest test = new SIBTest();
        test.someMethod();
        System.out.println("Owner is "+SIBTest.owner);
    }
}
