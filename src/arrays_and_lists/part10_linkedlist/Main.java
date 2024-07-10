package arrays_and_lists.part10_linkedlist;

import java.util.ArrayList;

/*
 * LinkedList
 * - A LinkedList is another type of list
 * - We've seen that an array is an index list of items
 *      - there can be primitive types e.g. int, double, boolean
 * - we can also use objects in the case of an ArrayList and store them like that
 *
 *  LinkedList
 *  Index   Address Value
 *  0       100     34
 *  1       104     18
 *  2       108     91
 *  3       112     57
 *  4       116     453
 *  5       120     68
 *  6       124     6
 *
 * - we have an array of integers
 * - index position to the left is the number that we use to access the particular value
 *      - to access the 1st element, we use index position 0 - returns the value of 34
 *      - to access the 2nd element, we use index position 1 - returns the value of 18
 * - what happens internally, is that Java will allocate 4 bytes of memory, for each integer, because it needs up to that
 *   amount to be able to store the highest value integer
 * - Java will try to do this continuously, there's actually a formula that Java can use to actually figure out which
 *   physical memory address to use to grab the value
 * - So it can look at the base address, for the first element in the array, index-0 and use that formula and then
 *   calculate the actual address based on that
 * - For example:
 *      - If we wanted to get access to the actual 3rd index position
 *          - we would multiply index position 3 * 4 which is 12 and add that to our base address which is a 100 which was
 *            the first element, and that would give us 112, which is what we have here
 *
 * - So, that's how Java handles things internally, which also works the same for primitive types as well
 * - In the case for a double for example, it uses up to 8 bytes of memory for each double, because it needs that for
 *   floating point numbers
 * - So the same formula would apply, we would actually still do the calculation, but instead of multiplying by 4, for 4 bytes
 *   of integer, we'd multiply by 8, which is 8 bytes for a double
 *      An integer will always consume 4 bytes of memory
 *      A double will always consume 8 bytes of memory
 *
 * - But what about Strings ?
 * - Strings don't actually work the same because they can be of a variable size
 * - This also applies to general objects as well
 *
 * Index   Address  String Address  Address Done
 *  0       100     1034            1024    Hello World
 *  1       108     1037            1034    Tim
 *  2       116     1046            1037    Australia
 *  3       124     1024            1046    Java
 *  4       132     4000            1050    Array
 *  5       140     1050            1055    ArrayList
 *  6       148     1055            4000    Done
 *
 * - we've got the same index to the left-hand side, the same array, but this time , we've got Strings instead of int
 * - we've got the same number and there's a calculation of 8 bytes but that points to another location in memory where
 *   the String is
 * - so consequently by doing that, Java can actually keep a track of the actual strings that are in the array and also
 *   the strings can each be of variable length, can be of different lengths
 * - This is because Strings can be of variable length unlike int which has to be and always is 4 bytes
 * - We can see here:
 *      - index-0, which is at a memory location 100, has got a String address of 1034, and then looking at
 *         the memory address of a position on the address of 1034, it's actually got a String value "Tim"
 *      - index-3, has got a String address of 1024, which points to "Hello World" value
 *
 * - If we use a loop to actually print the values, we'll get the output in the following order:
 *      - "Tim" , "Australia", "Java" ,"Hello World" ,"Done" ,"Array" and "ArrayList"
 * - It's because it's actually going through the index positions from 0 to 6
 *
 * - String address do not need to be contiguous e.g. 1055, 4000
 *
 *
 * Use case:
 *  - Create a Customer class
 *  - Fields
 *      name : String
 *      balance : double
 *  - Constructor
 *      - initializes both fields
 *  - Getters
 *      getName() : String
 *      getBalance() : String
 *
 * - main()
 *  - Create an instance of the Customer class
 *  - Create another instance of the Customer class but points to the customer object
 *
 *  - call setBalance(12.18) on the second instance
 *
 *  - print the balance of the first customer
 *
 * Explanation
 * Why does changing the balance of the second class affect the first class ?
 * - What happens is when we actually do this assignment
 *      anotherCustomer = customer
 * - What we're doing internally, Java was assigning the second customer class to point to the first class
 *      - It just saved the memory address, it didn't create a separate class
 * - As a result of not saving the memory address, when we used the setBalance(), it saved the value in the original
 *   location
 * - So for all intents and purposes , there's only 1 class here
 *      - Customer class
 *      - anotherCustomer is pointing to the first class
 * - So consequently, when we use the setBalance() on the second class,it actually updated the first class
 *
 * - So if Java doesn't let us use pointers, in other words, we can't actually point to these objects directly
 *
 * Next
 *  - Create an ArrayList<Integer> intList;
 *      - Add some integers, 1 3 4
 *      - loop and print individual elements in the array list
 *      - insert 2 at index position 1
 *      - loop the list again
 *
 * - what happens is that for that to be inserted, the other entries had to be moved down to make space
 *   for the new entry
 * - this is not a big deal in terms of computer processing time when we're dealing with a small array, however,
 *   if we talk about an array containing thousands, perhaps millions of existing records, it's gonna get a lot
 *   slower to process
 * - similarly, if you have to remove an item from an array, all the other items have to be moved up to fill the gap
 *
 * LinkedList
 * - LinkedList is an alternative to arrays, and it's really useful in some situations
 * - It stores the actual link to the next item in the list as well as the data, so hence the name, linked list
 * - Each element in the list actually actually holds a link to the item that follows it as well as the actual value
 *   you want to store as well
 * - So if we created a linked list , we can iterate through the list of items in it
 * - We can start on the first one and check to see whether the link has got anything ele attached to it
 *
 * - in other words, whether it's linking to anything, if it does, we pick up the string, store it at the location
 *   and actually save that
 *
 * Inserting and removing items in a LinkedList
 * - Inserting/Removing an item at a particular position is a lot easier in a linked list as compared to an array
 *   because there is no re-indexing as we saw with Arrays
 *
 * Inserting an element
 * - In a linked list, inserting an element at a particular position requires the pointer of the new element to point
 *   to the next element it is inserted to
 * - Then the previous element is made to point to the next element
 *
 * Removing an element
 * - the pointer to the element is made to point to another element
 * - the pointer the element was pointing to is set to null
 * - element dropped is collected through a process known as garbage collection
 * - no re-indexing is required
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Tim",54.96);
        Customer anotherCustomer;
        anotherCustomer = customer;

        anotherCustomer.setBalance(12.18);
        System.out.println("Balance for customer "+ customer.getName() + " is "+ customer.getBalance());

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(3);
        intList.add(4);
        for (int i = 0; i < intList.size(); i++) {
            System.out.println(i + ": "+ intList.get(i));
        }
        intList.add(1,2);

        for (int i = 0; i < intList.size(); i++) {
            System.out.println(i + ": "+ intList.get(i));
        }
    }
}
