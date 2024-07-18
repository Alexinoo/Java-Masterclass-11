package interfaces_inner_and_abstract_classes.part1_interfaces;
/*
 * Interfaces
 *
 * - An Interface in Java terms, specifies methods that a particular class that implements this interface must implement
 * - The interface itself is abstract, only contains method signatures, and the actual implementation is done at the
 *   class level
 * - The idea to use an interface is to use a common behavior that can be used by several classes by having them all
 *   implement the interface
 *     - It's really a way to standardize the way a particular set of classes is used
 *
 * Consider the example below
 *
 * Gearbox : class
 *
 * Fields:
 *  clutchIsIn : boolean
 *
 * Methods:
 *  operateClutch(String inOrOut) : void
 *      - sets clutchIsIn to either true/false
 *      - if the parameter value "inOrOut" passed to this method has a value of "in" string
 *
 * - This is one way of implementing a particular method to decide whether the clutch is in, in a GearBox class,
 *   but that's not probably the best way of doing it coz it's a little bit untidy
 *
 * - If we wanted to do something different, we might actually change the parameter from a String to a boolean and set
 *   "this.clutchIsIn" member variable to the boolean parameter "inOrOut" passed
 *
 *      public void operateClutch(boolean inOrOut){
 *          this.clutchIsIn = inOrOut;
 *      }
 * - This is a certainly a valid code & it does work,but the problem would be that we have changed the method signature
 *
 * - And if we've got some other code that is reliant on the fact that the 1st parameter was a String, as it originally
 *   was, and we've changed it to a boolean,
 *      - it means we've broken that other code, and that means it has to be changed as well
 *      - and suppose you're using a very popular framework or your code is used in a lot of other places, this could
 *        be a serious problem
 * - The problem is actually that although we've created the Gearbox class, we haven't made a sort of commitment if you
 *   will that won't be changed
 * - We haven't said that once we create
 *
 *      public void operateClutch(boolean inOrOut){
 *          this.clutchIsIn = inOrOut;
 *      }
 * - we aren't going to change it
 *
 * - So, what an interface actually is, is just that, it's actually a commitment, a contract that the method's
 *    signature and that those variables in the interface constants if you defined any will not change
 * - So, consequently, you can create an interface and use it, and provided that you stick to it what's in the interface
 *   the code isn't going to change & you won't be breaking the code anywhere
 *
 * Let's go ahead and create an interface to define a behavior of a telephone
 *  - Create Telephone interface - by convention use an ITelephone which rep an interface named telephone though it's
 *    optional
 */
public class GearBox {
    private boolean clutchIsIn;

    public void operateClutch(String inOrOut){
        this.clutchIsIn = inOrOut.equalsIgnoreCase("in");
    }
}
