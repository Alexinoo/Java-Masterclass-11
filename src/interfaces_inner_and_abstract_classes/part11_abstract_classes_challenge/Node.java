package interfaces_inner_and_abstract_classes.part11_abstract_classes_challenge;
/*
 * Node class
 *  - concrete class that extends ListItem abstract class
 *
 * Constructor
 *  - calls the super constructor in the ListItem class to set the value of the object
 *
 * Override abstract method(s) from the ListItem class
 *
 *  next() : ListItem
 *      - returns the next item to the right  i.e. this.rightLink
 *
 *  setNext(ListItem item) : ListItem
 *      - set rightLink to the item passed i.e. this.rightLink = item
 *      - and return it i.e. this.rightLink
 *
 *  previous() : ListItem
 *      - returns the previous item to the left  i.e. this.leftLink
 *
 *  setPrevious(ListItem item) : ListItem
 *      - set leftLink to the item passed i.e. this.leftLink = item
 *      - and return it i.e. this.leftLink
 *
 *  compareTo(ListItem item) : int
 *      - Compare the string values of the current object and the provided item and returns either a +ve, 0, -ve
 *          - a negative indicate, the current obj is less than the obj being compared
 *          - a positive indicate, the current obj is greater than the obj being compared
 *          - a zero indicate, the current obj is equal to the obj being compared
 *      - Return -1 if the provided item is null.
 *      - String class has it's own compareTo() and that's why we're leveraging it's compareTo() to exactly do that
 *          and will return the above values
 *
 * Next
 *  - We ow have a concrete class that extends our abstract ListItem
 *  - We can create object(s) of type node, and build structures by making the nodes next and previous links point
 *    to each other
 *
 * - A good idea will be probably to define an interface that any structure using a node object(s) will implement
 *   and then use that interface later on
 *
 *
 */
public class Node extends ListItem{
    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        return this.rightLink;
    }

    @Override
    ListItem setNext(ListItem item) {
        this.rightLink = item;
        return this.rightLink;
    }

    @Override
    ListItem previous() {
        return this.leftLink;
    }

    @Override
    ListItem setPrevious(ListItem item) {
        this.leftLink = item;
        return this.leftLink;
    }

    @Override
    int compareTo(ListItem item) {
        if (item != null){
            return ((String)super.getValue()).compareTo((String)item.getValue());
        }else{
            return -1;
        }
    }
}
