package arrays_and_lists.part6_list_and_arrayList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * - Add scanner class to get input from the user
 * - Create an instance of our class "GroceryList"
 *
 * - Use a while loop
 *      - continue processing until the user quits
 *
 * - request the user to enter their choice from the scanner class
 *
 * - check the choice entered and execute the relevant instruction
 *
 *  - Option 0
 *      - call printInstructions()
 *
 *  - Option 1
 *      - call groceryList.printGroceryList() to print items in the grocery list
 *
 *  - Option 2
 *      - call addItem() to add an item to the groceryList
 *  - Option 3
 *      - call modifyItem() to update an item
 *  - Option 4
 *      - call removeItem() to remove an item
 *  - Option 5
 *      - call searchItem() to search for an item in the groceryList
 *  - Option 6
 *      - quit the application
 *
 * - Define the methods
 *      - printInstructions()
 *          - add the prompts
 *
 *      - addItem()
 *          - request the user to enter an item that they want to add to the list
 *          - call addGroceryItem from the GroceryList class and pass the new item
 *
 *      - modifyItem()
 *          - request the user for an item number that the user want to update
 *          - request the new item that they want to replace the old item with
 *          - call modifyGroceryItem from GroceryList class and pass the position and the new item
 *
 *      - removeItem()
 *          - request the user for an item number they want to remove
 *          - call removeGroceryItem() and pass the no of the item entered
 *
 *      - searchItem()
 *          - Request the user to enter the item they want to search
 *          - call findItem() and pass the item
 *              - if found
 *                  - tell the user the item was found
 *              - otherwise
 *                  - print to the user that the item does not exist
 *
 *      - processArrayList()
 *          - copy one arrayList to another array list
 *
 *          - Option 1
 *              - create a new ArrayList
 *              - call addAll() on the new arrayList and pass groceryList.getGroceryList() getter
 *              - is a quicker way to copy instead of creating a for loop and go through each record, determine the
 *              entry and save it
 *
 *          - Option 2
 *              - another quicker way of copying an arrayList is to pass it directly to the constructor
 *          - pretty much identical to Option-1 though this is happening at the point of declaring and initializing
 *             the new object for our new array list object
 *          - its another handy way of copying things, and these are the types of the things you wanna do whenever possible
 *
 * Next,
 *  - Suppose we want to copy the contents of an ArrayList to a regular array of Strings
 *  - We could do something like this
 *      - Create an Array of Strings and initialize it to the length of the ArrayList
 *      - call the getter which returns an ArrayList<String>
            - convert it to an Array by calling toArray() which then returns an array of whatever the elements are
 *
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GroceryList groceryList = new GroceryList();
    public static void main(String[] args) {
        boolean quit = false;
        int choice = 0;
        printInstructions();
        while (!quit){
            System.out.println("Enter your choice");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    printInstructions();
                    break;
                case 1:
                    groceryList.printGroceryList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4 :
                    removeItem();
                    break;
                case 5:
                    searchForItem();
                    break;
                case 6:
                    processArrayList();
                    break;
                case 7:
                    quit = true;
                    break;
            }
        }
    }

    private static void processArrayList() {
        ArrayList<String> newArrayList = new ArrayList<>();
        newArrayList.addAll(groceryList.getGroceryList());

        ArrayList<String> newArrayListTwo = new ArrayList<>(groceryList.getGroceryList());

        String[] myArray = new String[groceryList.getGroceryList().size()];
        myArray = groceryList.getGroceryList().toArray(myArray);
    }

    private static void searchForItem() {
        System.out.println("Enter item to search for");
        String searchItem = scanner.nextLine();
        if (groceryList.onFile(searchItem)){
            System.out.println("Found "+ searchItem+ " in our grocery list");
        }else {
            System.out.println(searchItem + " is not on file");
        }

    }

    private static void removeItem() {
        System.out.print("Enter item name: ");
        String itemToRemove = scanner.nextLine();
        groceryList.removeGroceryItem(itemToRemove);
    }

    private static void modifyItem() {
        System.out.print("Enter item you want to update: ");
        String currentItem = scanner.nextLine();
        System.out.print("Enter the new item to replace with: ");
        String newItem = scanner.nextLine();
        groceryList.modifyGroceryItem(currentItem , newItem);
    }

    private static void addItem() {
        System.out.print("Please enter the grocery item: ");
        groceryList.addGroceryItem(scanner.nextLine());
    }

    private static void printInstructions() {

        System.out.println("\nPress");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - To print the list of grocery items");
        System.out.println("\t 2 - To add an item to the list.");
        System.out.println("\t 3 - To modify an item in the list");
        System.out.println("\t 4 - To remove an item from the list");
        System.out.println("\t 5 - To search for an item in the list.");
        System.out.println("\t 6 - To quit the application.");
    }

}
