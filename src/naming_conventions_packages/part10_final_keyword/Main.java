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

        Math m = new Math();
    }
}













