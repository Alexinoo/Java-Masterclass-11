package interfaces_inner_and_abstract_classes.part1_interfaces;
/*
 * ITelephone
 * - Notice there is a variation with the class definition where we use class keyword to define a class
 *   as
 *      public class GearBox
 * - whereas with an inter, we use the interface keyword instead of a class
 *      public interface ITelephone
 *
 * - Next, we define method signatures that a class that is going to use this interface is going to implement
 * - They include:
 *      powerOn : void
 *      dial(int phoneNumber) : void
 *      answer : void
 *      callPhone(int phoneNumber) : boolean
 *      isRinging : boolean
 *
 * - Therefore these are the methods that a class that is going to implement from this interface , these are the
 *   methods it has to implement
 * - So we've defined the contract, the actual parameters and return types that are valid for each method
 *
 * - public modifier is redundant for the methods and this actually make sense , because the actual implementations is
 *   done at the class level and therefore these methods must be accessible to the class that implements the interface
 * - You can actually remove them altogether
 *
 * - Next create a class "DeskPhone" that implements ITelephone
 */
public interface ITelephone {
    void powerOn();
    void dial(int phoneNumber);
    boolean isRinging();
    void answer();
    boolean callPhone(int phoneNumber);
}

