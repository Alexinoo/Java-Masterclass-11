package arrays_and_lists.part10_linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

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
 *
 * Next,
 *  - Call addInorder() and
 *      - pass placesToVisit linked list
 *      - pass places we had added before
 *  - call printList() and pass placesToVisit
 *
 * Next
 *  - Add to test compare conditions
 *      - Add "AliceSprings"
 *  - Add a place that already exist
 *      - Add "Darwin"
 *
 * Next
 *  - Include a menu that's going to allows us to decide when to move onto the next city
 *  - Also look at how we can go back to a city we've already visited
 *
 * - We have .next() , which enable us to move to the next entry
 * - the ListIterator also gives us an option to go back in both directions
 *      - we can go to the next record in a forward motion but you can also go backwards as well
 * - his is possible because Java has implemented linked list as a double linked list
 *
 * Doubly Linked List
 *  - Stores the reference to the previous item as well as a reference to the entry that's next as well
 *
 * - Add visit()
 *  - specifies whether you want to go forward or back when you're actually searching
 *      - check if the linked list is empty and if not
 *          - print we're visiting the first city in the linked list "Adelaide"
 *      - else
 *          - print there no cities to visit
 *  - use a scanner class to get input from the user if they want to go to the next/previous city
 *      - use a while loop and a switch statement with the following options:
 *          - 0 to quit
 *          - 1 to go forward
 *          - 2 to go backward
 *          - 3 to print menu options
 *
 * Testing:
 *  - there's a problem if we move forward and then want to go backward
 *      - see below
 *          ..      Now visiting "Adelaide"
 *          .. 1    Now visiting "Alice Springs"
 *          .. 1    Now visiting "Brisbane"
 *          .. 1    Now visiting "Canberra"
 *          .. 2    Now visiting "Canberra"
 *          .. 2    Now visiting "Brisbane"
 *          .. 2    Now visiting "Alice Springs"
 *  - the problem is that Java does not implement the linked list in quite the way that we've actually described
 *  - there's an explanation of the listIterator, that can help us understand how all this works
 *  - from Java docs
 *      - An iterator for lists that allows the programmer to traverse the list in either direction, modify the
 *         list during iteration, and obtain the iterators current position in the list.
 *      - A ListIterator has no current element;
 *      - Its cursor position always lies between the element that would be returned by a call to previous() and
 *          the element that would be returned by a call to next()
 *
 * Explanation:
 *  - the ListIterator actually hovers between the items that would be returned when you go to previous or you go to
 *    next
 *  - so if you go forward with 1 and then go back with 2, then go forward with 1 etc, you never actually leave the
 *    city you're actually in
 *  - so this is actually a problem but there's a good reason for this
 *
 *  - if they had actually allowed access to the item at .next or .previous in the normal way, then an error would
 *    lead to loops in the list
 *  - For example:
 *      - if we made a mistake where we're moving Perth from the list, then we'd end up in an infinite loop by moving
 *        from Sydney -> Alice Springs -> Melbourne -> Brisbane -> Alice Springs -> Melbourne and so on
 *  - Algorithms have been developed for detecting and fixing loops and structures like linked lists
 *  - Google something like Tortoise and Hare Algorithm, you'll see that there's actually a big issue with that and that's
 *    where this linked list is sort of looping over itself
 *
 *  - Therefore, we need to keep track of which direction we're going and allow for the in between nature of the iterator
 *      - There's a little bit more work to do to get that to work
 *  - Solution
 *      - Add a boolean variable to track our direction
 *          boolean goingForward and set it to true
 *      - Then what we need to do is when we're going forward or backwards, we'll need to do a test and we need to
 *        set that direction
 *
 *  - Will start with
 *    - case 1 : forward
 *          - and say if !goingForward - in case the user has selected back
 *          - then do a test that checks whether the listIterator hasNext is true
 *              - and if so, call next() on the iterator
 *          - set goingForward to true
 *      - so we're doing an additional check to actually make sure, that we actually can go, that there is the ability
 *          to go next and then actually go to the next entry again, so that we can continue with where we left off
 *
 *    - case 2 : backward
 *          - and likewise, for going backwards
 *          - if we're actually going forward currently, then if there is a previous record, we go then to the previous
 *            entry
 *          - then set goingForward to false
 *
 * Again, just as a recap:
 *  - we're setting this up
 *  - case 1:
 *      - If the user has selected to go forward, and we're currently not going forward, we check to see whether
 *         there's an entry we can go forward to
 *      - And if so, set it to go there
 *      - That's sets it up in the right direction, so that the following code can actually work
 *      - then once we get to the end of the list, set goingForward to false
 *
 *  - case 2:
 *      - set goingForward to true once we get to the start of the list when traversing backwards
 *      - in short we've reached the first entry, and so we automatically change the direction in pointing forward
 *        again
 *  - we're just doing a little bit more of a housekeeping to make sure that in the event that we're going in a
 *    particular direction, once we reach either the start,in this case, with the goingForward
 *
 *  - in this case, if we are actually going forward, what we're doing is a test to make sure that if we aren't going
 *    forward, prior to the actual menu option being selected, we need to point in that direction to be going forward
 *
 *  - But also, if we get to the end of the list, we need to set going forward to false which means, from now on, we'll
 *    actually be navigating going backwards
 *      - and we're doing the reverse with case 2, if we were going forward but the user has selected to go previous
 *         then we need to check whether we can go previous
 *      - and if we can we go to that previous entry to point it in the right direction
 *
 * - So in short,
 *  - there is an extra .next you need to actually use and .previous if you're moving from 1 direction to another
 */
public class Demo {

    public static void main(String[] args) {

        LinkedList<String> placesToVisit = new LinkedList<>();

        /*
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
        */
        addInOrder(placesToVisit,"Sydney");
        addInOrder(placesToVisit,"Melbourne");
        addInOrder(placesToVisit,"Brisbane");
        addInOrder(placesToVisit,"Perth");
        addInOrder(placesToVisit,"Canberra");
        addInOrder(placesToVisit,"Adelaide");
        addInOrder(placesToVisit,"Darwin");

        printList(placesToVisit);

        addInOrder(placesToVisit , "Alice Springs");
        printList(placesToVisit);

        addInOrder(placesToVisit , "Darwin");
        printList(placesToVisit);

        visit(placesToVisit);

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

    private static void visit(LinkedList<String> cities){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<String> listIterator = cities.listIterator();

        // check if there are places to visit - call isEmpty
        if (cities.isEmpty()){
            System.out.println("No cities in the itenerary");
            return;
        }else {
            System.out.println("Now visiting "+ listIterator.next()); // get the first city
            printMenu();
        }

        while (!quit){
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action){
                case 0:
                    System.out.println("Vacation over");
                    quit = true;
                    break;
                case 1:
                    if (!goingForward){
                        if (listIterator.hasNext()){
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()){
                        System.out.println("Now visiting "+listIterator.next());
                    }else {
                        System.out.println("Reached the end of the list");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if (goingForward){
                        if (listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.println("Now visiting "+listIterator.previous());
                    }else {
                        System.out.println("We are at the start of the list");
                        goingForward = true;
                    }
                    break;
                case 3:
                    printMenu();
                    break;
            }
        }
    }

    private static void printMenu(){
        System.out.println("Available actions: \npress");
        System.out.println("0 - to quit\n"+
                "1 - go to next city\n"+
                "2 - go to previous city\n"+
                "3 - print menu options\n");
    }

}
