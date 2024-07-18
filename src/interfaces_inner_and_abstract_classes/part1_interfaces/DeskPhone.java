package interfaces_inner_and_abstract_classes.part1_interfaces;
/*
 * DeskPhone class
 * - Create DeskPhone and use implements keyword followed by the interface name
 *
 * Fields
 *  myNumber : int
 *  isRinging : boolean
 *
 * Constructor
 *  DeskPhone(int myNumber)
 *      - initialize myNumber to the number passed
 *
 * Methods - implement all
 *
 *  powerOn() : void
 *      - print something meaningful to the user
 *
 *  dial(int phoneNumber) : void
 *      - print "dialing phoneNumber ..."
 *
 *  callPhone(int phoneNumber) : boolean
 *      - check if number passed matches with my phone number
 *          - if so,
 *              - print "Ringing..." and set isRinging to true
 *          - otherwise
 *              - set isRinging to false
 *
 *  isRinging() : boolean
 *      - return "isRinging" value
 *
 *  answer() : void
 *      - check if isRinging is true,
 *          - print "Answering..."
 *      - set isRinging to false
 *
 * - Although the interface relies on all these methods, even if you don't want to use it
 * - For example , with the desktop phone, you might have decided that we're not going to have a power on button, we still need to implement it
 * - The implementation can be simple, just overriding the function that's actually putting an error message to say "Not supported" or "Not needed
 *   for this type of telephone"
 * - SO you can't sort of decide, with an interface that you only want to implement some of the methods and leave out some
 * - If you're implementing ITelephone interface, you'll actually need to implement all the methods, that's part and parcel of the contract
 *
 */
public class DeskPhone implements ITelephone{
    private int myNumber;
    private boolean isRinging;

    public DeskPhone(int myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public void powerOn() { System.out.println("Not applicable, Not Supported!!");  }
    @Override
    public void dial(int phoneNumber) { System.out.println("Now dialing "+ phoneNumber +" ....");  }
    @Override
    public boolean callPhone(int phoneNumber) {
        if (phoneNumber == myNumber){
            isRinging = true;
            System.out.println("Ringing.....");
        }else{
            isRinging = false;
        }
        return isRinging;
    }

    @Override
    public boolean isRinging() { return this.isRinging;}

    @Override
    public void answer() {
        if (isRinging){
            System.out.println("Hello...");
            isRinging = false;
        }
    }

}
