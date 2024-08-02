package naming_conventions_packages.part10_final_keyword;
/*
 * The final statement
 *
 * - final keyword is used generally to define constant values
 *
 * - strictly speaking final fields are not actually constants, because they can be modified but only once, and any modification must be performed
 *   before the class constructor finishes
 * - this means we can assign a final field it's value either when we first declare it , or in the constructor
 *
 * Example:
 *  - Create SomeClass
 *  - Add a final int variable and set it to 1
 *      - since it's marked as final, it's value can't be changed  anywhere else in the class, or if we made it public, from anywhere outside of the
 *        other classes either
 *
 * - It's very usual to assign the value when declaring the field, but also, if you prefer, you can do it from the constructor instead
 *
 * But why and when would we need to initialize the value from the constructor ?
 *  - 1 reason is when it's value is a result of some calculation that perhaps relies on some other code such as a method from another class
 *  - Say reading a record from a database, you might want to store the class instances unique database's key which wouldn't be available until you
 *    read the record from the database but then ready to create the class instance from the database field
 *
 * - We can simulate this by creating a mechanism that ensures every instance of a class has a unique number , or ID
 *      - This will help us track a variable each time a new class instance is created and this is ideal for using a class variable
 *      - We'll make the instanceNumber public , to make it easy to demonstrate that it can't be changed, but normally when doing this you'd make it
 *        private
 *
 * Next
 *  - Update private to public
 *  - remove the initialization and do it from the constructor
 *
 * Next
 *  - Create a static variable - integer to keep track of the number of instances created and initialize it to 0;
 *  - Create a final String variable - name
 *
 *  Constructor
 *      SomeClass(String name)
 *          - initialize name
 *          - increment the counter
 *          - set instanceNumber to the value of the classCounter
 *          - output the name of the instance and created and print the instance number
 *
 *  Getter
 *    - getInstanceNumber() : int
 *
 * main()
 *  - Create some instances of SomeClass
 *
 * - Note that unlike in the discussion of static where we had a getter for the static field and we always get the same value, whichever class instances
 *  we're calling it on
 *      - here we are actually storing the incremented static value in a class field, and so each instance of a class will maintain its own value
 *
 * - Let's see what the above means by printing the instanceNumber for the 3 instances
 *
 *      System.out.println(one.getInstanceNumber()); // 1
        System.out.println(two.getInstanceNumber()); // 2
        System.out.println(three.getInstanceNumber()); // 3
 *
 *      - Notice that they're all maintaining their own value for instance number
 *
 * - So by making the instanceNumber final, it's value cannot be changed , once the class instance has been created
 *
 * - will get an error if we try to change the value of instanceNumber
        one.instanceNumber = 4;
 *
 * - It's always a good idea to mark variables as final whenever you know that the value of the variable shouldn't change once the initial value's
 *   been set
 *
 * Next,
 *  - You may have seen constant variable's been declared as "static final" which is very normal for constant
 *
 * However,
 *  - our instanceNumber wasn't treated as constant because we did want to assign it's value but we only wanted to assign it one and then didn't
 *    want to change it after that point
 *  - that's also why we didn't use UPPERCASE for the variable name - which is one of the conventions on naming constant variable names
 *
 * So whey are constants declared as static final ?
 *  - if the value really is a constant and won't change, then it doesn't make sense to store a copy of every single class instance
 *  - they all hold the same value, and this makes sense to store it only once at the class level
 *  - hence, values that are constant are usually declared using static final
 *  - For example:
 *      - PI in the Math class
 *          System.out.println(Math.PI)
 *      - It's declared a public static final double PI
 *  - It's usual to assign the value of static field variables at the time they're declared , but then again , it can also be done after in a similar
 *    manner to non-static final fields , which we'll look at later
 *
 * Next,
 *  - Let's stay with the Math class and have a look at the final keyword
 *  - Let's create some instances of the Math class and do some sums
 *
 *       Math m = new Math();
 *
 *  - Unfortunately , this doesn't work and we get the error 'Math()' has private access
 *      - And the reason for this is, that the Math Class has a private constructor & it's documentation
 *        also suggests that 'Don't let anyone instantiate this class'
 *      -  the Math() constructor has been marked private to prevent instances of it being created
 *      - The reason for doing this is that all the methods in the Math class are static and static fields as well
 *          and are all available without requiring an instance
 *      - To enforce this and make it clear, the creators of this class made the constructor private and that prevents
 *        any instances from being created
 *
 *  - Can we get around this by creating a subclass of Math which we can then instantiate
 *      - Fortunately/unfortunately, we can't do that because the Math class is declared as final
 *      - By marking a class final, it prevents the class from being subclassed, and any attempt to extend Math will
 *         fail
 *
 * Next
 *  - It's possible that you might want a class to be extended or subclassed , but you might have 1 or more methods
 *     that are crucial to the correct operation of your class and you don;t want them to be overridden
 *  - You can mark them as final and this prevents them from being overridden even if you do allow the class itself
 *    to be subclassed
 *
 * Example
 *  - Create a Password class
 *  - Fields
 *      key : int (static final)
 *          - initialize to 748576362
 *
 *      encryptedPassword : in (final)
 *
 *  - Constructor
 *      Password(int password)
 *          - initialize encryptedPassword by using a generic function encryptDecrypt(password)
 *
 *  - Methods
 *      encryptDecrypt(int password) : int
 *          - uses the key to XOR the password
 *          - is a basic XOR which just check the bits in the 2 values and returns a 1 in any position where there's
 *            1 in anyone of the values
 *          - if you apply the XOR to the result you get back the original value
 *
 *      storePassword : void
 *          - prints saving encrypted password and prints it out
 *
 *      letMeIn(int password) : boolean
 *          - check if the password passed matches the one stored
 *          - if so
 *              - print welcome
 *          - otherwise
 *              - print logging denied
 *
 *
 * Next
 *  - Create Password instances and store it by calling storePassword
 *  - This is an example of why you would want to prevent your methods from being overridden
 *
 *  - The constructor takes the password and encrypts it, setting a field to store the encrypted value so that it can be
 *    checked when the user is logging in
 *
 *  - Let's call the letMeIn() with a few passwords and check if it's working
 *
 * Next
 *  - The problem often comes when someone overrides the storePassword() method in a subclass
 *
 * But what would prevent us for someone from extending Password class and changing storePassword functionality ?
 *
 *  - Create ExtendedPassword class that extends from Password class
 *  - Fields
 *      decryptedPassword : int
 *  - Constructor
 *      - ExtendedPassword(int password)
 *          - calls super constructor to initialize the password
 *      - initializes decryptedPassword to password passed
 *
 *  - Methods
 *      - Override storePassword()
 *      - Instead of calling super.storePassword() , print save password as is
 *
 *
 * Next
 *  - Create an instance of ExtendedPassword and pass the same password
 *  - Works but the problem is that we're now saving password as is as since we've overridden the storePassword()
 *    from the super class
 *  - You can see how this consequently changes the security of the original Password class and it compromises it
 *
 *  - This is where something like final would be really really useful
 *      - To prevent this, all we need to do is add a final keyword to storePassword() in the Password class
 *      - And once we do that, we should get an error method, which says we can't override password as the method
 *        is now marked as final
 *
 *  - We can see how useful it is to selectively make some methods final in your classes if you know there's a chance
 *    they will be overridden
 *  - All we're saying is that it's important that this is the final method and all our code at that point and we'll
 *    not allow it to be overridden
 *
 * - Commented the @override storePassword() for code to run
 *
 */
public class Main {
    public static void main(String[] args) {
        SomeClass one = new SomeClass("one");
        SomeClass two = new SomeClass("two");
        SomeClass three = new SomeClass("three");

        System.out.println(one.getInstanceNumber());
        System.out.println(two.getInstanceNumber());
        System.out.println(three.getInstanceNumber());

        // will get an error if we try to change the value of instanceNumber
        // one.instanceNumber = 4;

        System.out.println(Math.PI);

        //Math m = new Math();


        int pwd = 674312;
        Password password = new Password(pwd);
        password.storePassword(); // Saving password as 747902050

        password.letMeIn(1); //incorrect password
        password.letMeIn(523266); //incorrect password
        password.letMeIn(9773); //incorrect password
        password.letMeIn(0); //incorrect password
        password.letMeIn(-1); //incorrect password
        password.letMeIn(674312); // correct password


        // Using subclass
        System.out.println("///////////////////");

        password = new ExtendedPassword(pwd);
        password.storePassword(); // saving password as 674312

        password.letMeIn(1); //incorrect password
        password.letMeIn(523266); //incorrect password
        password.letMeIn(9773); //incorrect password
        password.letMeIn(0); //incorrect password
        password.letMeIn(-1); //incorrect password
        password.letMeIn(674312); // correct password

    }
}













