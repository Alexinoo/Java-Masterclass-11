package naming_conventions_packages.part9_static_keyword;
/*
 * The static keyword
 *
 * - We've been using the static keyword when declaring variables and methods
 * - Let's look at what static actually means and why we used it and why we had to use it in particular places
 *
 * Example
 *  - Create StaticTest class
 *  - Added the following fields
 *      String name variable to hold instance name
 *      int numInstances to keep track of the number of instances created
 *  - Add a constructor
 *      - Takes name variable and initializes it
 *      - increments numInstances
 *  - Add Getters
 *      - getName() and getNumInstances
 *
 * Main()
 *  - Create 2 instance of the StaticTest class
 *  - Print them
 *
 * Explanation
 *  - Both instances have a copy of numInstances which is incremented to 1 in the constructor for both instances
 *
 * static field
 * - However, for a static field , which is also known as a class variable, is associated with a class rather than any
 *   particular instance of it
 * - There's only copy of the variable in the memory
 * - All instances of the class share that 1 class variable
 *
 * - Therefore, we can get the behaviour we originally wanted where this instance number was incremented every time
 *    we instantiated a new object from this class
 *
 * Next,
 *  - Let's update numInstances to be a static field
 *  - And now we get the behaviour that we wanted where numInstances is now keeping track of the number of instances
 *    created via the static numInstances variable
 *
 * Next
 *  - Create a 3rd instance
 *  - Rerun and check the numInstances which should not be set to 3
 *
 * - Only 1 version of numInstances exist in memory at any given time
 *
 * Accessing static field
 *  - We normally use Class.staticField to access the value of the static field
 *  - It makes sense to use Class since the field does not belong to any particular instance, and a static field
 *    is more of a Class instance
 *
 * Next,
 *  - If you're using a method that only works on static fields , it makes more sense to make that method also static
 *    and that means we can access the method without having to create a class instances at all
 *
 * Next
 *  - update getNumInstance() to static
 *      - still returning the same variable "numInstances" which we have updated to static
 *  - now we can go back and instead of referencing
 *      firstInstance.getNumInstances()
 *      secondInstance.getNumInstances()
 *      thirdInstance.getNumInstances()
 *  - we can now use the Class name instead
 *      StaticTest.getNumInstances()
 *      StaticTest.getNumInstances()
 *      StaticTest.getNumInstances()
 *  - and this makes it clear now that it's the class name , and we're no longer using the instance name, and we're
 *    accessing a static method
 *
 * Examples / Use cases
 *  - We've use static methods before, weh calling Integer.parseInt() from the interface's challenge, parseInt() is a
 *    static method of the Integer class
 *  - And for that reason we can use that without having to create an instance of the Integer class to call it from
 *
 * Recap
 *  - static fields and methods belong to the class, and not to instances of the class and as a result they can be
 *    called by referencing the class name rather than a class instance
 *
 *  - This explains why the main() we've used throughout the entire course has to be static
 *      - Because when we want to run a Java program there has to be an entry point, a method that is executed when
 *        the program runs
 *      - However, until the program runs, there's no classed instances to call methods on, so Java has to use this
 *        static method that can be called from the class name rather than from a class instance
 *      - That's the reason why the main() is marked as static
 *
 *  - So when we run our project in IntelliJ, what IntelliJ does is invokes a Java executable and passes to it the
 *    name of the class that contains the public static void main
 *  - SO provided you actually built the project, you can run the program from the command line by changing it into
 *    the project production directory
 *  - And then typing something like
 *      java com.company._static.Main
 *  - Java will then expect Main class to have a static method called main() with the exact signature that we've been
 *    using
 *  - Now, the main() doesn't have to be in a class called Main, it can exist in any of the classes, but the convention
 *    is to put it in a class called Main
 *  - If we're given a project containing hundreds of classes to work on, you will at least know where the program
 *    starts by looking for the Main class rather than checking every other class to check for the main()
 *
 * Next
 *  - We've learnt static fields and methods are sometimes know as class variables and class methods, we can work out
 *    why all the methods we've created in Main have also been static
 *
 *
 * Example: 2
 *  - Create an integer class field in this class : Main class
 *  - Create a method called multiply()
 *      - takes a number parameter & returns the product of the number parameter multiplied by the multiplier variable
 *
 *  - In this example, we're just interested with what we can and what we can't do with static methods
 *      - public multiply(int number) takes a number and returns the product of the number parameter
 *        multiplied by public multiplier field defined at the top
 *      - we've made multiplier public so that there's no confusion stemming from its visibility
 *      - so now , let's call the method in main() and see what 6 * 7 is
 *
 *      int answer = multiply(6);
        System.out.println("The answer is "+ answer);
 *
 *  - However we get an error that 'Non-static method 'multiply(int)' cannot be referenced from a static context
 *
 *  - And we'll get a similar error 'Non-static field 'multiplier' cannot be referenced from a static context
 *    if we try to print the multiplier variable too
 *      System.out.println("Multiplier is "+ multiplier);
 *
 *  - So if multiplier is public, why can't we access it from our main()
 *      - Well ,as we have seen normal class fields require an instance of the class that don't actually exist until
 *        an instance has been created
 *      - Now the main() is static as we can see & it can be called with the class instance
 *      - So as result Java can't allow static method to access non-static fields or methods because they don't
 *        exist when the static method is called
 *  - So many of our earlier examples, we created methods from main to call , and in order for them to work, we had
 *    to make the methods static
 *  - And we need to do that here now so that this can work
 *
 * Solution
 *  - Make multiplier field static
 *  - Update multiply() to static
 *
 * - This should now work, because we're now calling a static method and accessing a static field
 *
 * Note:
 *  - Also note that the reverse is not true , with no reason why it should be
 *  - There's no problem at all with nonstatic constructor of the StaticTest class
 *
 *  - There's no problem with StaticTest constructor accessing the static numInstances field
 *  - We can also call static methods from nonstatic ones with no problems
 *  - There's nothing to prevent a static method from accessing nonstatic fields and methods in another class because it creates an instance of a
 *    class in order to do so
 *  - The restriction is purely on a static method access nonstatic field/methods in it's own class
 *
 *  - There's more aspect of static that we need to look at, and that's the static initializers
 *  - However, the discussion of these also need to consider final fields
 *
 */
public class Main {
    public static int multiplier = 7;
    public static void main(String[] args) {
        StaticTest firstInstance = new StaticTest("1st Instance");
        System.out.println(firstInstance.getName() + " is instance number "+StaticTest.getNumInstances());

        StaticTest secondInstance = new StaticTest("2nd Instance");
        System.out.println(secondInstance.getName() + " is instance number "+StaticTest.getNumInstances());

        StaticTest thirdInstance = new StaticTest("3rd Instance");
        System.out.println(thirdInstance.getName() + " is instance number "+StaticTest.getNumInstances());

        int answer = multiply(6);
        System.out.println("The answer is "+ answer);
        System.out.println("MUltiplier is "+ multiplier);
    }

    public static int multiply(int number){
        return number * multiplier;
    }
}
