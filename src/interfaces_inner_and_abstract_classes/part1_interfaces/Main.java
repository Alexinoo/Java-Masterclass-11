package interfaces_inner_and_abstract_classes.part1_interfaces;
/*
 * Create an instance of type interface "ITelephone"
 *   ITelephone alexPhone = new DeskPhone(717316925);
 *
 * - What we can do is this
 *      ITelephone alexPhone = new ITelephone(717316925);
 *
 * - and is not valid to do that because by doing that, it knows automatically what intelliJ did, was it overrode it all to create a class within the
 *   implementation which we obviously don't want
 *
 * - And the reason that all this makes sense is that we have define an interface, but the actual class implementation in this case DeskPhone is the
 *   specific functionality for a certain type of telephone
 * - So the interface is there at an abstract level to tell you what method(s) are valid and have to be actually overridden in an actual class
 * - That's why you can't actually instantiate it using the ITelephone interface, and you have to actually use the actual class that has overridden
 *   that functionality
 *
 * Note that it's also valid to do
 *       DeskPhone alexPhone = new DeskPhone(717316925);
 * - We'll look at why we need to do that in certain circumstances, but it's also valid to do as below
 *       ITelephone alexPhone = new DeskPhone(717316925);
 *
 * - To use the data type of type ITelephone in the definition, provided you use the appropriate class that has implemented the interface, in our
 *   case rather, it was DeskPhone
 *
 * - We can also create both instance and with the same reference as below
 *      ITelephone alexPhone;
 *      alexPhone = new DeskPhone(717316925);
 *      alexPhone = new MobilePhone(717316925);
 * - This is because the 2 classes DeskPhone and Mobile phone implement the same interface
 */
public class Main {

    public static void main(String[] args) {
        ITelephone alexPhone = new DeskPhone(717316925);
        alexPhone.powerOn();
        alexPhone.dial(717316925);
        alexPhone.callPhone(717316925);
        alexPhone.answer();

        System.out.println("===================================");

        ITelephone anotherPhone = new MobilePhone(717045796);
        anotherPhone.powerOn(); // comment out and test if phone is on
        anotherPhone.dial(717045796);
        anotherPhone.callPhone(717045796);
    }
}
