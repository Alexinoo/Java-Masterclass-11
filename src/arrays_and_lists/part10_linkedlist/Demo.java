package arrays_and_lists.part10_linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * Linked List Example
 * - Create a Linked list of places to visit, the capital cities in Australia
 *    - create placesToVisit linked list
 *      - add several places
 *
 *    - create printList() and pass linked list
 *      - loop through them using an iterator
 *
 * Loop through a Linked List
 * - An iterator is another way of accessing and going through all entries that are in a particular array, or an
 *   ArrayList , or a LinkedList
 * - Since a linked list entry is pointed to another entry, what we're saying is that while the starting element
 *   in the linked list is pointing to something else, there's at least 1 record actually to visit,
 *      - while that is true, show the current record
 *      - and i.next() is part of that and it actually sets it and as we go through i.next()
 *      - actually changes the value of the iterator
 *      - it's equivalent to typing i++ or i+1
 *  - We're actually saying, output that which actually gives you the current record but that also moves onto the next
 *    record as well
 *  - So consequently, hasNext() is just to say, is there another entry but .next() is actually moving to the next
 *    entry, but it's actually returning what the current value is before it moves on
 *  - That's why we've put on "Now visiting..." and that will show in, the first time we run this, it'll actually show
 *    Sydney, but then automatically move to Brisbane
 *  - So that's actually how it works
 *
 * Inserting a place
 *  - Suppose we want to visit Alice Springs immediately after Sydney and before Melbourne
 *  - So basically, insert at index position 1
 *      placesToVisit.add(1, "Alice Springs");
 *
 *  - Then print the result again
 *
 * - Notice how Sydney is pointing to Alice Springs which is also now pointing to Melbourne
 * - Whereas the first time when we run, Sydney was pointing to Melbourne
 * - Java does automatically that for us because of the actual linked list functionality in Java has that does that
 *   for us automatically, that it's sort of built-in to do that
 *
 * Removing a place
 *  - If we wanted to remove a place, suppose "Perth", we don't want to visit Pert in our list,
 *  - We need to pass the index of that place to remove()
 *  - Therefore we can do something like this
 *      placesToVisit.remove(4)
 *  - What happens is that Brisbane which was pointing t Perth, is now pointing to Canberra, so Perth has been sort
 *    of removed completely and that's how a linked list works
 *  - Handles the linking to the next record in the list automatically for you
 *  - With this mechanism, if you had 1000 of records,there's not really gonna be any slowdown in performance since
 *    it's literally only changing 1 or 2 entries at most
 *  - Everything else remains in the same position
 *
 * Advantages of a linked list
 *  - add the cities in alphabetical order
 *  - though alphabetical, may not be the best way to visit the cities in a country, what we're saying here is that
 *    everytime an entry is added to the linked list, we'll make sure that it's actually added in alphabetical order
 *  - this is where you would use some other algorithm to work out the distances and figure out the most efficient way
 *    and perhaps add it in that way
 *  - But in this case, just as an example, we'll do it in alphabetical order
 *
 * Steps
 *  - Start of with an empty list
 *      - first item will be added right from the start
 *      - when adding the second item, well compare it to the first and then 3 things can actually happen
 *          - if it's the same as the first, (duplicate), we don't want to visit the same city twice and if that's
 *              the case we'll ignore that entry and not actually accept it
 *          - if it sorts alphabetically before the first city, we'll insert it before the current city
 *          - if it's alphabetically greater than the current city, we'll insert it to the next city
 *      - then repeat the process
 *  - So you can see that by comparing that as we're actually inserting it, we can put it in the right place each time
 *  - Eventually, when we get to the end of the list, if the item has not been added by now, then it belongs to the
 *    end, so we'll add it there and implement it
 *  - We'll use something similar to an iterator and that's called a List iterator
 *
 * ListIterator
 *  - gives you some more flexibility than a traditional iterator
 *  - it's really made for situations like this where we're inserting certain records and so forth
 *
 * addInOrder(LinkedList<String> linkedList , String newCity) : boolean
 *  - Returns true if addition was successful, otherwise false
 *  - Takes 2 parameters
 *      - linked list we're adding to
 *      - the new city we want to add
 *  - Create a ListIterator of type String
 *      ListIterator<String> stringListIterator = linkedList.listIterator();
 *  - Use while loop
 *      - the first time, when you point to the listIterator,it's automatically going to the very first entry that's
 *         stored in the linked list
 *      - then we'll use .hasNext() and using the while loop we'll navigate to all entries that are in the
 *          stringListIterator
 *      - then we'll use a compareTo function
 *          - this returns a number
 *              - if 0, then both the value that's in the linked list, the current index entry that we're
 *                 searching for is equal to the new city variable  (0 - means match)
 *                  - in that case , we don't want to do anything
 *                  - print "place" already in the list
 *                  - return false
 *              - if > 0 , then appear before the current item
 *                  - newCity should appear before
 *                  - call stringListIterator.previous() first ,since we had moved to .next and add the new city
 *                  - return true
 *
 *  - We're using ListIterator, because normally you can't do that with a regular iterator,
 *  - Normally, you'll go through all the records, there's no way to back to a previous record
 *  - But the list iterator is a special type of iterator that enables us to actually do that
 */
public class Demo {

    public static void main(String[] args) {
        LinkedList<String> placesToVisit = new LinkedList<>();

        placesToVisit.add("Sydney");
        placesToVisit.add("Melbourne");
        placesToVisit.add("Brisbane");
        placesToVisit.add("Perth");
        placesToVisit.add("Canberra");
        placesToVisit.add("Adelaide");
        placesToVisit.add("Darwin");

        printList(placesToVisit);

        placesToVisit.add(1, "Alice Springs");
        printList(placesToVisit);

        placesToVisit.remove(4);
        printList(placesToVisit);

    }

    private static void printList(LinkedList<String> linkedList) {
        Iterator<String> i = linkedList.iterator();
        while (i.hasNext()){
            System.out.println("Now visiting "+ i.next());
        }
        System.out.println("==============================");
    }
    private static boolean addInOrder(LinkedList<String> linkedList , String newCity){
        ListIterator<String> stringListIterator = linkedList.listIterator();

        while (stringListIterator.hasNext()){
            int comparison = stringListIterator.next().compareTo(newCity);
            if (comparison == 0){
                //equal , do not add
                System.out.println(newCity + " is already included as a destination");
                return false;
            } else if (comparison > 0) {
                // new City should appear before this one
                // Brisbane -> Adelaide
                stringListIterator.previous();
                stringListIterator.add(newCity);
                return true;
            }else if (comparison < 0){
                // move on next city
                // Brisbane was passed and we now want to add Adelaide, Brisbane doesn't come before Adelaide,
                // so we don't want to do anything
            }
        }
        stringListIterator.add(newCity);
        return true;
    }
}
