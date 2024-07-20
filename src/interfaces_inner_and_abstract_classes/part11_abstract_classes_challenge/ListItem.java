package interfaces_inner_and_abstract_classes.part11_abstract_classes_challenge;
/*
 * ListItem class
 *  - Will serve as Base ListItem class
 *  - With 2 variables that hold references to object(s) of type ListItem
 *     protected rightLink : ListItem
 *     protected leftLink : ListItem
 *  - Each ListItem object can hold a link to the previous and next items in the List that we're actually going
 *    to be saving
 *
 *  - We've also defined a constructor for the abstract class ListItem as well
 *  - Any concrete class that inherits from ListItem will need a constructor to set the value
 *      - It makes sense to have the constructor in the ListItem abstract class
 *  - Added a getter and setter for the value of type Object
 *
 *  - Then we have the abstract method(s), that define what must be implemented by any concrete class that inherits
 *      from this ListItem class
 *
 *     abstract next() : ListItem
 *          - returns a reference to the next ListItem in the list
 *
 *     abstract setNext(ListItem item) : ListItem
 *          - sets the reference to the next item to refer to in the parameter, which is of course item
 *
 *     abstract previous() : ListItem
 *          - returns a reference to the previous item in the list
 *
 *     abstract setPrevious(ListItem item) : ListItem
 *          - sets the reference to the previous item to referred to in the parameter
 *
 *     abstract compareTo(ListItem item) : int
 *          - compares an object to the item, whatever has been passed to the parameter
 *              - returns 0 , if they are equal
 *              - returns -ve , if the object passed should be sorted before the object
 *              - returns +ve , if the object passed should be sorted after
 *
 * - The fields have been declared as protected, rather than public, is because we need to be able to access them from
 *   our concrete subclass
 * - We could have left the access modifier, and this would mean that the member variables are packaged private
 *      - In other words, subclasses in the same package would be able to access them but not subclasses in other
 *        packages
 *      - So, that's why we've made them protected
 *
 * Next,
 *  - In order to create list items, we need to create a concrete class, one that actually implements these method(s)
 *    and as a result can therefore be instantiated
 *
 * Next
 * - Create a class called Node that extends ListItem class
 *
 */
public abstract class ListItem {
    protected ListItem rightLink = null;
    protected ListItem leftLink = null;

    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    abstract ListItem next();
    abstract ListItem setNext(ListItem item);
    abstract ListItem previous();
    abstract ListItem setPrevious(ListItem item);

    abstract int compareTo(ListItem item);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
