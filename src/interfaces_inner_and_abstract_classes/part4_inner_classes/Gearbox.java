package interfaces_inner_and_abstract_classes.part4_inner_classes;

import java.util.ArrayList;

/*
 * Model a Gear class in the context of a GearBox class
 *
 * A Gearbox class
 * - rep the base class
 * - contains an ArrayList<Gear> , numberOfGears and gearNumber
 *
 * Fields:
 *  gears : ArrayList<Gear>
 *  maxGears : int
 *  gearNumber : int
 *
 * Constructor
 *  Gearbox(int maxGears)
 *     - initialize maxGear
 *     - initialize gears to a new ArrayList
 *     - create a new Gear "neutral"
 *     - add it to the arrayList
 *
 * Gear
 *  - rep the inner class
 *  - Fields
 *     gearNumber : int
 *     ratio : double
 *
 * - Constructor
 *     Gear(int gearNumber , double ratio)
 *      - initialize both fields
 *
 * - Methods
 *      driveSpeed(int revs) : double
 *          - accepts number of revs and multiply that with the ratio to get the drive speed
 *
 *
 * - Gear is a definition for an inner class because it doesn't make any sense to refer to a Gear, in this scenario
 *   except when we're talking about a gear box
 * - Gear isn't something that sort of is useful in it's own right
 * - It's coupled with a GearBox to make it useful and that's the idea of using an inner class
 * - In this case, it doesn't make sense to talk about Gears unless it's within the context of a GearBox
 * - And hence, that's why we've modelled the Gear using an inner class of the GearBox class
 *
 * - Instances of a Gear class have got access to all the methods and fields of the outer GearBox class, even those
 *   marked as private
 * - Notice that the use of "this" keyword in the inner class refers to members in the inner class and not the one
 *   from the outer class
 *      e.g. this.gearNumber in the Gear constructor refers to the member variable gearNumber
 * - So keep in mind when you're using "this" particularly with inner classes, that you're actually dealing with the
 *   class that you're currently in, and in this case the Gear class
 *
 * - Rename gearNumber from the GearBox class to currentGear to avoid confusing the same with the member variable
 *   gearNumber from the inner class
 *
 * Next
 *  - The Gear class itself is a member of Gearbox & it must be created within instance of it
 *
 *
 * - Add clutchIsIn boolean variable
 *
 * - Add the following methods
 *   operateClutch(boolean in) : void
 *      - sets the clutchIsIn to the variable passed
 *
 *   addGears(int number , double ratio) : void
 *      - check if it's a valid gear - between the range e.g. [0,1,2,3,4,5,6]
 *      - add the new gear to gears ArrayList
 *
 *   changeGear(int newGear) : void
 *      - check if it's a valid gear - between the range e.g. [0,1,2,3,4,5,6] and whether the clutchIsIn is true
 *      - If so
 *          - set the currentGear to the new Gear
 *          - print some info to the user "Gear changed ot something.."
 *      - Else
 *          - print "Grind.."
 *          - reset currentGear to 0
 *
 *   wheelSpeed(int revs) : double
 *      - checks if clutchIsIn
 *      - If true
 *          - means we're not moving, and there would be a problem and we're actually printing "Screaming"
 *          - then return 0.0
 *      - Else
 *          - calculate wheel speed by multiplying the revs by the current gear
 *          - then use .getRatio() called from the inner class
 *
 * - We've written the Gearbox class to do all the functionality it needs in the Gear class itself
 * - We're creating new instance of the gear and saving it in our ArrayList and also using various functions
 *   within the Gear to get the ratio and for other sorts of calculations
 *
 *
 */
public class Gearbox {
    private ArrayList<Gear> gears;
    private int maxGears;
    private int currentGear = 0;

    private boolean clutchIsIn;
    public Gearbox(int maxGears) {
        this.maxGears = maxGears;
        this.gears  = new ArrayList<Gear>();
        Gear neutral = new Gear(0,0.0);
        this.gears.add(neutral);
    }

    public void operateClutch(boolean in){
        this.clutchIsIn = in;
    }

    public void addGear(int number , double ratio){
        if (number >= 0 && number <= maxGears){
            this.gears.add(new Gear(number , ratio));
        }
    }

    public void changeGear(int newGear){
        if (newGear >= 0 && newGear < this.gears.size() && this.clutchIsIn){
            this.currentGear = newGear;
            System.out.println("Gear "+ newGear+ " selected");
        }else {
            System.out.println("Grind!");
            this.currentGear = 0;
        }
    }

    public double wheelSpeed(int revs){
        if (clutchIsIn){
            System.out.println("Scream !!!");
            return 0.0;
        }else{
            return revs * this.gears.get(currentGear).getRatio();
        }
    }

    private class Gear {
        private int gearNumber;
        private double ratio;
        public Gear(int gearNumber, double ratio) {
            this.gearNumber = gearNumber;
            this.ratio = ratio;
        }

        public double getRatio() {
            return ratio;
        }

        public double driveSpeed(int revs){
            return revs * (this.ratio);
        }
    }
}
