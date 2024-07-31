package naming_conventions_packages.part4_scope;
/*
 * Scope
 *
 * - scope refers to the visibility of a class, member or variable
 *
 * Non Computing Terms
 *
 * Local Store
 *  - Suppose you want to buy a particular computer, and you find a local store, that sells it with a 20% discount
 *  - Now the same computer in another store would still be at the full price though
 *  - The scope, or the price reduction is just that 1 local store
 *
 * Wallmart
 *  - Contrast that to a large company say Walmart (US) , and let's say they decided to discount a particular computer,
 *    and the discount will then apply in about 4500 stores across US
 *  - In this context, the scope of the discount is therefore much wider
 *  - And that Walmart is a multinational company, if they applied the discount worldwide, the scope would be over
 *    11000 stores
 *  - Important thing to note here is that Walmart's discount does not apply to any other stores
 *  - The scope is limited to the stores controlled only by Walmart
 *
 * - And the same with the local store, that had a 20% discount off - the scope here is just for that 1 local store
 *
 * - The same computer and in a Java context would be the same variable , in other stores it's not discounted
 * - Java Objects have got the scope the same way
 *
 * Example
 *  - Create a new class
 *
 * ScopeCheck : class
 *  Fields
 *     public publicVar : int  - initialize to 0
 *     private privateVar : int - initialize to 0
 *  Constructor
 *      ScopeCheck()
 *
 *  Getter
 *      getPrivateVar() : int
 *
 * main()
 *  - create a String variable privateVar and set it to "this is private to main()"
 *
 * - We've got 2 variables
 *    - String privateVar - main()
 *
 *    - int privateVar - ScopeCheck class
 *       - it's private , and can't be accessed directly
 *       - can only be accessed through a ScopeCheck class instance and via getPrivateVar()
 *
 * Running this:
 *  - Within the main(), whatever we refer to "privateVar" , Java actually knows that we must be referring to the one
 *    declared within main(), as that's the only one that's available in the current scope
 *      - privateVar is referred to as in-scope variable
 *
 * - Access modifiers such as public, protected ,private are actually one way to restrict the scope of an object
 * - It turns out scope is more important within the same class or method
 *
 * Add method in ScopeCheck class
 *  timesTwo() : void
 *  - Create an integer variable "privateVar" - same name with class variable
 *  - initialize it to 2
 *  - use a for loop and multiply the value of the current i with "privateVar" - which is local to timesTwo()
 *      and is actually in-scope
 *
 *
 * Result
 *  - Create a 2 8 multiplication variable using the in-scope variable "privateVar = 2"
 *  - Java would use privateVar at the class level if the in-scope variable was not there
 *
 * - Therefore, "privateVar" variable is local to twoTimes() , and anytime we refer to privateVar inside twoTimes(),
 *   we're referring to the variable within the most local scope
 * - Though they got the same name, the rules of scope ensure that the variable with the narrow scope is the one
 *   that will be used
 *      - narrow scope here means the most local
 *
 * - Another way to consider this is by using the concept of enclosing blocks
 * - When Java sees a reference to a variable, it starts by checking the current block of code to see if the variable
 *   was declared there , and if it is declared there, then that variable is used
 *
 * - If there's no such declaration, then Java checks any block that encloses the current one to see if there's a
 *   declaration there
 * - So it keeps doing this working backwards until it finds the variable declaration , and if it doesn't get one, you
 *   will actually get an error in your code
 *
 * - So, in the twoTimes(), there is no declaration of privateVar within the current block - for loop
 *
 *       for (int i = 0; i < 10; i++) {
            System.out.println(i +" times two is "+ i * privateVar) ;
        }
 * - And so, Java will check the enclosing block which is the method itself
 *
 *      {
            int privateVar = 2;
            for (int i = 0; i < 10; i++) {
                System.out.println(i +" times two is "+ i * privateVar) ;
            }
        }
 * - And it does actually find the "privateVar" and it's able to use it because that's the most local one
 *
 * Variable i
 *  - is declared inside the for loop , and it's scope is limited to that block , plus any contained block if there
 *    was one
 *  - means we can't print the value of i , outside the block and we'll get an error if we do this
 *
 *     for (int i = 0; i < 10; i++) {
            System.out.println(i +" times two is "+ i * privateVar) ;
        }
        System.out.println("Value of i = "+i);
 *  - the i is declared within the for loop & it's scope is restricted to within the for loop, and not available
 *    outside that loop
 *  - as soon as the loop finishes , and has exited that for loop (block of code) , that's it , the variable i, then
 *    goes out of scope
 *
 * Next
 *  - Suppose we comment out the "privateVar" declared within the twoTimes()
 *  - Java will keep looking above , sort of expanding its search, and finds privateVar "member variable" and actually
 *    use it
 *  - And so if we run it now, we'll be multiplying with 1 which is the value for the "privateVar" class/member
 *    variable
 *  - Uncomment out the local variable "privateVar"
 *
 * Next
 *  - Also note that we can also use the "privateVar" class variable within the twoTimes() , even if we got the
 *    same variable with the same name
 *  - We need to tell Java to use "privateVar" class variable rather than the local one
 *  - We do this by qualifying the variable name with "this" , and this tells Java to use the privateVar at the class
 *    level
 *
 * Example
 *  - We've seen that quite extensively with getters and setters, when we refer to this.fieldName to actually
 *    specify the field
 *
 * Next
 *  - If we commented the class variable , out, will get an error because "this.privateVar" will not exist anymore
 *    because as far as Java is concerned, we've told it to look for a field in this class by the name "privateVar"
 *      - and it doesn't exist anymore because we've commented it out
 *
 * Next
 *  - The concept of scope doesn't just apply to variables
 *  - Methods also have scope as well as classes
 *
 *  - Class scope can be a little bit confusing
 *      - Let's actually add an inner class to our ScopeCheck class , and see what it does for the scope of things
 *
 *  - InnerClass
 *      privateVar : int (set to 3)
 *
 *      Constructor
 *      InnerClass()
 *          - print the value of privateVar
 *
 *      twoTimes() : void
 *
 *
 * Next
 *  - Create an instance of the InnerClass in the main()
 *       ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
 *  - Then call timesTwo()
 *
 * Running
 *  - This works fine, and once again the rules of scope applies and because privateVar is declared as a field
 *    of the InnerClass, Java will actually use that
 *  - Java will use that since we've remove the local one , and multiply by 3 in this case
 */
public class Main {

    public static void main(String[] args) {
        String privateVar = "this is private to main()";

        ScopeCheck scopeInstance = new ScopeCheck(); // ScopeCheck created, publicVar = 0 : privateVar = 1
        System.out.println("Scope instance privateVar is "+scopeInstance.getPrivateVar()); // Scope instance privateVar is 1
        System.out.println(privateVar); // this is private to main()

        scopeInstance.timesTwo();

        System.out.println("//////////////////////////////////////");

        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
        innerClass.timesTwo();
    }
}
