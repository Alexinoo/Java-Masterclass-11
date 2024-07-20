package interfaces_inner_and_abstract_classes.part11_abstract_classes_challenge;
/*
 * NodeList interface
 * ..................
 *
 *  - Will have the following abstract method(s)
 *
 *      getRoot() : ListItem
 *          - returns the starting node
 *
 *      addItem(ListItem item) : boolean
 *          - add node passed to the list
 *
 *      removeItem(ListItem item) : boolean
 *          - remove node specified from the list
 *
 *      traverse(ListItem root) : void
 *          - start at the root
 *          - print the values of each item in the structure
 *
 *  - Any data structure that we create must have starting node, and that's why we have the getRoot()
 *  - In the case of a linked list, this is often called the "head" but the term root is often used a lot
 *
 *
 * Next
 *  - Let's create a LinkedList class that's going to implement NodeList interface
 *  - name it MyLinkedList
 */

public interface NodeList {
    ListItem getRoot();
    boolean addItem(ListItem item);
    boolean removeItem(ListItem item);
    void traverse(ListItem root);
}
