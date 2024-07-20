package interfaces_inner_and_abstract_classes.part11_abstract_classes_challenge;

/*
 * MyLinkedLit class
 *
 * - Fields
 *      root : ListItem
 *
 * - Constructor
 *      MyLinkedList(ListItem root)
 *      - initializes the root/head
 *
 * - Override method(s) from NodeList interface
 *
 *      getRoot : ListItem
 *          - return this.root
 *
 *      addItem(ListItem item) : boolean
 *          - check if the list is empty
 *              - set the new item as the root/head , if the list was empty
 *          - otherwise
 *              - compare each item in the list with a new item to work out where the new item should be inserted
 *              - because we're sorting in a particular order
 *          - if result is less than 0
 *              - then the String obj needs to move to the right
 *                  - check if there is an entry to the right
 *                      - if not null, traverse by setting the current item to that item - continues looping
 *                      - if null
 *                          - set the tail to point to the newItem
 *                          - set the previous to what was current
 *          - if result is greater than 0
 *              - insert before
 *
 *       traverse(ListItem root) : void
 *          - if root is null,
 *              - print the list is empty
 *          - Else
 *              - use a while loop to loop through the nodes
 *                  - set root to currentItem
 *                  - loop as long as currentItem is not null
 *                      - set currentItem = currentItem.next()
 *                  - exits when currentItem is null
 *
 *       removeItem(ListItem item) : boolean
 *              - traverse the list until we find element to remove
 *              - set the previous link to point to where the item to be deleted point
 *              - sort of filling in the gaps
 *
 *
 */
public class MyLinkedList implements NodeList {

    private ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if (this.root == null) {
            // The list was empty, so this item becomes the head of the list
            this.root = newItem;
            return true;
        }
        // compare from the head of the list
        // use a while loop to compare the next
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = (currentItem.compareTo(newItem)); // compares head with the item passed e.g. Darwin.compareTo("Melbourne")
            if (comparison < 0) {
                // new item is greater, move right if possible
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    // there is no next, so insert at end of list
                   // currentItem.setNext(newItem);//current.rightLink=newItem //root's rightLink points to newItem
                   // newItem.setPrevious(currentItem);//newItem.leftLink=root //newItem's leftLink points back to root
                    currentItem.setNext(newItem).setPrevious(currentItem);  //shortcut
                    return true;
                }
            } else if (comparison > 0) {
                //new item is less, insert before
                if (currentItem.previous() != null) {
                    currentItem.previous().setNext(newItem);
                    newItem.setPrevious(currentItem.previous());

                    newItem.setNext(currentItem);
                    currentItem.setPrevious(newItem);

                  //  currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());  // shortcut
                  //  newItem.setNext(currentItem).setPrevious(newItem); // shortcut
                } else {
                    // the node with a previous is the root
                    // make this new item the root
                    newItem.setNext(this.root); // point to the current root
                    this.root.setPrevious(newItem); //
                    this.root = newItem;
                }
                return true;
            } else {
                // equal
                System.out.println(newItem.getValue() + " already exists");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem deleteItem) {
        if (deleteItem != null) {
            System.out.println("Deleting item " + deleteItem.getValue());
        }
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(deleteItem);
            if (comparison == 0) {
                // found the item to delete
                if (currentItem == this.root) {         //check if this is the root
                    this.root = currentItem.next();    // set root to point to the next element
                } else {
                    // means it's somewhere in between 2 nodes
                    // both pointers , previous and next, point to the right places
                    currentItem.previous().setNext(currentItem.next()); // left node to point to the node after the one to delete
                    if (currentItem.next() != null) { // if there is a node to the right
                        currentItem.next().setPrevious(currentItem.previous()); // right node points to the node before the one to delete
                    }
                }
                return true;
            } else if (comparison < 0) {
                // traverse the list
                currentItem = currentItem.next();
            } else {
                // comparison is > 0
                // we are at an item greater than the one to be deleted
                // so item is not in the list
                return false;
            }
        }
        //we have reached the end of the list
        // without finding item to delete
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
    }
}
