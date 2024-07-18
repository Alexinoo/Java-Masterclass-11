package interfaces_inner_and_abstract_classes.part1_interfaces;
/*
 * - Create MobilePhone class and implement ITelephone interface
 *
 * Fields
 *  myNumber : int
 *  isRinging : boolean
 *  isPhoneOn : boolean - set it to false initially
 *
 * Constructor
 *  DeskPhone(int myNumber)
 *      - initialize myNumber to the number passed
 *
 * Methods - implement all
 *
 *  powerOn() : void
 *      - set isPhoneOn to true - since phone has been turned on
 *      - print "Starting up.."
 *
 *  dial(int phoneNumber) : void
 *      - check if phone is on
 *      - if so
 *          - print "dialing phoneNumber ..."
 *      - otherwise
 *          - print "mteja.."
 *
 *  callPhone(int phoneNumber) : boolean
 *      - check if number passed matches with my phone number and whether the phone is still on
 *          - if so,
 *              - print "Melody Ringing..." and set isRinging to true
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
 */
public class MobilePhone implements ITelephone{
    private int myNumber;
    private boolean isRinging;
    private boolean isPhoneOn = false;

    public MobilePhone(int myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public void powerOn() {
        isPhoneOn = true;
        System.out.println("Starting phone up...");
    }
    @Override
    public void dial(int phoneNumber) {
        if (isPhoneOn)
            System.out.println("Now dialing "+ phoneNumber +" ....");
        else
            System.out.println("Mteja wa nambali unayopiga, hapatikani kwa sasa");
    }
    @Override
    public boolean callPhone(int phoneNumber) {
        if ( phoneNumber == myNumber && isPhoneOn ){
            isRinging = true;
            System.out.println("Melody Ringing.....");
        }else{
            isRinging = false;
            System.out.println("Mobile phone not on or number different");
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