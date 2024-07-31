package naming_conventions_packages.part5_scope_and_visibility;

/*
 * Scope and Visibility
 *
 * - Refactoring and use different variable names which is going to make them easier for us to refer to
 *
 * Class : ScopeCheck
 *      - Rename privateVar class variable to varOne
 *
 * ScopeCheck.timesTwo
 *      - Rename privateVar method variable to varTwo
 *
 * Class : InnerClass
 *      - Rename privateVar class variable to varThree
 *
 * Next,
 *  - Let's look at the scope of each variable in turn
 *
 * varOne
 *  - it's scope is at the class level
 *  - we have used in different places such as
 *      - ScopeCheck constructor
 *      - InnerClass constructor and the timesTwo()
 *  - the class and all the inner classes have got access to varOne
 *  - This is because it's a field on ScopeCheck class
 *  - And because we renamed it, it's no longer shadowed by the InnerClass field, because InnerClass field has got a different field : varThree
 *  - This confirms that the object scope is the block in which it's declared including any contained blocks
 *
 * varTwo
 *  - defined in timesTwo() , a method in the ScopeCheck class,
 *  - It's scope is at a method level and that's why it can also be used inside the for loop as well
 *  - In short, it should also work within the next set of blocks, the enclosing blocks
 *
 * varThree
 *  - is a field in the InnerClass, which is an inner class to ScopeCheck
 *  - It can be used anywhere within the inner class
 *  - We've printed it on the constructor and also multiplied in the timesTwo() as well
 *
 * varFour
 *  - defined in the main()
 *  - can be used/accessed anywhere within the main class and the main()
 *  - it's a local variable for the main()
 *
 * Note:
 *  - It's also worth noting that at no time did any of our other private var variables shadow the one in main()
 *  - This is because , it's totally a different class anyway
 *  - So a variable can only shadow one with the same name if it's declared in an enclosed block
 *
 *
 *
 * Visibility
 *  - In Java, Visibility is governed by the access modifiers
 *  - Visibility is connected to scope, and that's why we needed to look at the scope first before we dive deep into visibility
 *
 * Next
 *  - In the ScopeCheck class, if we take a look at the InnerClass, we've printed the varOne variable despite the fact that it's declared as private
 *      in the ScopeCheck class
 *  - An inner class has got access to all the member variables or fields of it's containing class
 *  - So not only is varOne in scope within the inner class, it's also visible. - the same is true the other way around
 *  - So the containing class , ScopeCheck, can also access all the methods and fields of a contained class even if they're marked as private
 *
 * - We can create a method in the ScopeCheck, to instantiate it's inner class and print varThree variable
 *
 *      public void useInner(){
            InnerClass innerClass = new InnerClass();
            System.out.println("varThree from outer class: "+innerClass.varThree);
        }
 *
 * Next,
 *  - call useInner() from the main()
 *      scopeInstance.useInner()
 *
 * - Also note that we can change InnerClass.varThree from public to private , and this also works as well
 *
 * - Notice that the scope of varThree hasn't extended to the outer class
 *   - We had to actually qualify its name with the class instance in order to print it out
 *      e.g. innerClass.varThree
 *
 * - But the visibility does extend into the outer class, but with that said, the variable itself can't be accessed from any class external to
 *   ScopeCheck class
 *      e.g.
 *          ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass()
 *          System.out.println("varThree is not accessible here "+innerClass.varThree); // doesn't work
 *
 *  - This is because varThree , has got private access and therefore has no visibility outside it's class or a containing class of it's class
 *      - In other words, from ScopeCheck or ScopeCheck.InnerClass
 *
 *  - However, if we update it to public, this will work, and we will not get the error, because we have given it visibility for that variable
 *    basically as a public to any other class
 *
 * - This concept is now moving away from scope and into visibility and access
 * - But varOne and varThree's visibility within the ScopeCheck is related to the scope of the variables
 *
 * - In particular, the fact that both varOne and varThree are declared as private, but both can be accessed from both the Outer and Inner in the
 *   ScopeCheck can be surprising especially when you're starting out learning Java
 *
 * Finally
 *  - When we look at Inheritance, we often have a subclass override a method from the superclass - in other words , the class it was extending.
 *
 *  - The mechanism for this is very similar into variable scope that we've discussed, and so Java will look for a method in that case, in the current
 *    class, and use it if it finds it,
 *
 *  - Otherwise, it's going to look for the method in the immediate superclass and so on up to chain until it finds a class that actually has the
 *    method
 *  - But it's not actually correct to say the overriding method shadows the one in the super class, override is actually the correct term and
 *    not shadow
 *  - If though, the method was static then it can be shadow not overridden
 *
 */
public class Main {

    public static void main(String[] args) {
        String varFour = "this is private to main()";

        ScopeCheck scopeInstance = new ScopeCheck(); // ScopeCheck created, publicVar = 0 : privateVar = 1
        System.out.println("Scope instance varOne is "+scopeInstance.getVarOne()); // Scope instance privateVar is 1
        System.out.println(varFour); // this is private to main()

        scopeInstance.timesTwo();

        System.out.println("//////////////////////////////////////");

        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
        innerClass.timesTwo();

        System.out.println("//////////////////////////////////////");
        scopeInstance.useInner();


        ScopeCheck.InnerClass anotherInnerClassInstance = scopeInstance.new InnerClass();
        //System.out.println("varThree is not accessible here "+anotherInnerClassInstance.varThree); // doesn't work
    }
}
