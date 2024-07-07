package arrays_and_lists.part6_list_and_arrayList;

import java.util.ArrayList;

/*
 * ArrayList Implementation
 * ........................
 * - We use the type ArrayList to define one and notice we get < and > and a little E
 * - The E means we're not defining the type as we're doing with arrays
 *      int[] myIntArray = new int[10]
 * - This is different to an array list because an ArrayLists can hold objects
 * - We need to tell it now what type of data is going to be stored in the ArrayList
 *
 * - we don't have a concept of let's say:
 *      private String ArrayList;
 *
 * - what we do, is type ArrayLists<Object type> and specify the type of object we want to be saved in the ArrayList
 *      private ArrayList<String>
 * - this tells Java, that we're creating an ArrayList and the elements that are in the ArrayList, are going to be
 *   of type String and the variable name
 * - then we need to add equal sign and then type new ArrayList<String>()
 * - notice we add the brackets because ArrayList is actually a class, unlike integer with the array, integer is a
 *   primitive type
 * - The ArrayList is actually a class, and it can have its own constructor, but in this case we're calling the empty
 *   constructor
 *
 * Next
 * - let's look at some of the methods we can use on an ArrayList
 *
 * addGrocery(String item)
 * - Add methods to add groceryList
 * - Note that with ArrayList, we don't specify the size that our groceryList is going to be, or the number of
 *   elements that will be stored
 *       private int[] myNumbers = new int[50];
 *
 * - Also note that we don't assign with index like we'll do with the normal arrays a below
 *      myNumbers[0] = 10;
 *
 * - This is because the ArrayList handles that for us automatically, and we need to call a method that is part of the
 *   ArrayList to add our item
 *      groceryList.add(item);
 * - The ArrayList has got all the functionality to determine where to save it, the amount of memory to allocate and
 *   so on
 * - That's an abstracted way for you, and you don't need to worry about that
 *
 * printGroceryList
 *  - we use size() that returns how many elements are currently stored in the arrayList
 *  - we can use a for loop to loop through the ArrayList and print individual items
 *  - we use get(int index) to access a particular element
 *  - this is going to output all the elements that are currently stored in the ArrayList
 *
 * modifyGroceryItem()
 *  - updating an item at a specific position
 *  - we need to pass the position of the element that we want to modify and the new item
 *  - we need to call set(position,newItem) on our groceryList and pass both the position of the element that we want
 *    to update plus the newItem
 *  - this effectively replaces the item in that position
 *
 *      groceryList.set(position,newItem);
 *
 * removeItem(int position)
 *  - we need to pass the position of the item that we want to remove
 *  - retrieve the item from that position, so that we can tell the user what item is being removed
 *  - then we need to call remove() on our groceryList and pass the position of the element we want to remove
 *
 *      groceryList.remove(position);
 *  - For example, if we had the following items in our ArrayList
 *      - Milk
 *      - Cheese
 *      - Bread
 *  - If we call groceryList.remove(1), what happens is that the 2nd element will be removed and the ArrayList will
 *    be automatically changed to
 *      - Milk
 *      - Bread
 *  - It has now 2 items, and it has effectively moved the bread item up into the position where previously we had
 *    cheese
 *  - The good thing is that the ArrayList is doing this for us automatically , it's not code that we need to handle
 *     that complexity
 *
 * findItem(String searchItem)
 *  - queries or finds an item from the groceryList
 *  - we can use a for loop and go through each index entry and compare it to search item and see whether it's a match
 *  - but where possible especially when using Java classes such as the ArrayList, we'd want to see if there's a
 *    shortcut
 *      - one shortcut would be using the contains() that returns true if the list contains the specified element
 *
 *         boolean exists =  groceryList.contains(searchItem);
 *      - so, literally, with 1 line of code, we're able to do that search
 *  - however, we don't just need to figure out that it's in there but rather return the item back because the method we've
 *    created is returning a string
 *
 *  - we can use a similar function grocery.indexOf(Object o) and pass the search item
 *      groceryList.indexOf(searchItem)
 *  - groceryList.indexOf(searchItem) does the same thing , searches the ArrayList for you and finds the item that you've
 *    passed to it and returns the index position of that item in our arrayList
 *  - it returns -1 if the list doesn't contain the element, or the index of the first occurrence
 *  - so we can check the position, and if it's equal to -1, we know that the item does not exist
 *
 *  - if position >= 0 , return the element at that position by calling
 *      groceryList.get(position)
 *  - otherwise, return null
 *
 *
 *
 * Next
 *  - Overload modifyGroceryItem(String currentItem,String newItem)
 *      - The user doesn't need to know about the position of the element that they want to remove
 *          - need to call findItem(String newItem) which returns the position of the item to update
 *
 *      - call modifyGroceryItem(int position ,String newItem) and pass the position plus the newItem to update
 *
 * - Call modifyGroceryItem(String currentItem,String newItem) in the main class instead of calling
 *     modifyGroceryItem(int position ,String newItem)
 *
 *  - modify findItem(String searchItem) to return an integer
 *      - return -1 if the item is found otherwise the position of the item
 *          return groceryList.indexOf(searchItem);
 *
 *  - update access modifier for modifyGroceryItem(int position ,String newItem) to private
 *
 *
 * Next
 *  - Overload removeGroceryItem(int position) with removeGroceryItem(String item)
 *      - The user does not need to know the position of the element that they want to remove
 *      - call findItem(String searchItem) and pass the element the user wants to delete
 *          - if position >=0 , meaning the item has been found
 *              - call removeGroceryItem(int position) and pass the position
 *  - update access modifier from public to private for removeGroceryItem(int position)
 *
 *
 * - By updating the methods to private, this means we're forcing the people who are consuming our class to use only the
 *   methods that we want them to use
 *      - means they'll not be available for selection from the main class/ consumers of this class
 *          private modifyGroceryItem(int position, String newItem)
 *          private removeGroceryItem(int position)
 *          private findItem(String searchItem)
 *              - only used internally
 *
 *
 * Next,
 * - Go to the main class and call the right methods instead
 *      modifyGroceryItem(String currentItem , String newItem)
 *      removeGroceryItem(String item)
 *
 * Next
 * - Add onFile(String searchItem) that will be exposed to the main class
 *      - calls findItem(searchItem)
 *      - if position is >= 0 , returns true
 *      - otherwise, returns false
 *
 *
 * Abstraction
 * - By restructuring, we're removing the need to actually add the index and so forth , and having to pass the number
 *   we're leaving all the complexities to the internal handling in the GroceryList class
 * - Because from the point of view, if you're accessing this GroceryList class, you shouldn't need to know how it's
 *   been implemented, or how internally the items are being saved
 *
 * - The functionality should be in the class that has got where the ArrayList is being added
 * - this means it's all self-contained and we're not releasing any extra functionality out in the wild so to speak
 * - we're not letting other programmers or other parts of the program get access to the internal workings of the
 *   groceryList
 *
 *
 * Next
 *  - Add a getter to retrieve the ArrayList
 *  - call it from the main()
 *
 */
public class GroceryList {
    private ArrayList<String> groceryList = new ArrayList<>();

    public ArrayList<String> getGroceryList() {
        return groceryList;
    }

    public void addGroceryItem(String item){
        groceryList.add(item);
    }

    public void printGroceryList(){
        System.out.println("You have "+ groceryList.size()+ " items in your grocery list");
        for (int i = 0; i < groceryList.size(); i++) {
            System.out.println((i+1) +". "+ groceryList.get(i));
        }
    }

    public void modifyGroceryItem(String currentItem ,String newItem){
        int position = findItem(currentItem);
        if (position >= 0){
            modifyGroceryItem(position,newItem);
        }
    }

    private void modifyGroceryItem(int position , String newItem){
        groceryList.set(position,newItem);
        System.out.println("Grocery item "+ (position+1)+ " has been modified.");
    }

    public void removeGroceryItem(String item){
        int position = findItem(item);
        if (position >= 0){
            removeGroceryItem(position);
        }
    }
    private void removeGroceryItem(int position){
        //String theItem = groceryList.get(position);
        groceryList.remove(position);
    }

//    public String findItem(String searchItem){
//        boolean exists = groceryList.contains(searchItem);
//        if (exists){
//            int position = groceryList.indexOf(searchItem);
//            return groceryList.get(position);
//        }
//        return null;
//    }

    private int findItem(String searchItem){
        return groceryList.indexOf(searchItem);
    }

    public boolean onFile(String searchItem){
        int position = findItem(searchItem);
        if (position >= 0){
            return true;
        }
        return false;
    }

}
