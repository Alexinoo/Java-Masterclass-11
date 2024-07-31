package naming_conventions_packages.part8_access_modifiers_minichallenge;
/*
 * Challenge
 *
 * In the following interface declaration , what is the visibility of:
 *
 * 1. the Accessible Interface ?
 *      - set to package private
 *      - means it's accessible to all the classes in this case "package naming_conventions_packages.part8_access_modifiers_minichallenge"
 *
 * 2. the int variable SOME_CONSTANT ?
 *      - set by default public
 *      - means all interface variables are public static final
 *
 * 3. methodA ?
 *      - is public, just standard public
 *
 * 4. methodB and methodC ?
 *      - also public because all interface methods are automatically public, so the lack of an access modifier here doe not imply package-private
 *
 * - With methodA() , methodB() and methodC() are both public because this is an interface
 *      - It's not possible to have anything except public methods in an interface
 *
 * - This makes sense for an interface because declaring an interface is to provide methods that have to be implemented
 *      - If you hide these methods in any class implementing the interface, it will struggle to implement the hidden methods which sort of defeats
 *        the whole purpose of using an interface
 *
 * - You can make the methods effectively package-private by ensuring the interface itself is package-private as we've done here
 *
 * - Although, all 3 methods are public, if the interface itself is not visible outside the current package, then those methods won't be visible
 *   either
 *
 * - The lack of an access modifier means the default of package-private except with interface methods and variables, which are always public
 */
 interface Accessible {
     int SOME_CONSTANT = 100;
     public void methodA();

     void methodB();

     boolean methodC();
}
