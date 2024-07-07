package arrays_and_lists.part7_arraylist_challenge;

import java.util.Scanner;

/*
 * - Create a program that implements a simple mobile phone with the following capabilities
 * - Able to :
 *      - store
 *      - modify
 *      - remove
 *      - query contact names
 * - Create a separate class for Contacts (name and phone number)
 *
 * - Create a Master class (MobilePhone) that holds the ArrayList of Contacts
 * - The MobilePhone class has the functionality listed above
 *
 * - Add the menu options that are available
 *      - Quit
 *      - print list of contacts
 *      - add new contact
 *      - update existing contact
 *      - remove contact
 *      - search/find contact
 *
 * - When adding/updating a contact be sure to check if the contact already exist (use name)
 * - Be sure not to expose the inner workings of the ArrayList to MobilePhone
 *      e.g. no ints , no .get(i)
 *
 * - MobilePhone should do everything with Contact objects only
 *
 *
 *
 * Solution
 *  - Create Contact class
 *  - Create MobilePhone class
 *
 *  - Create an instance of the Scanner class
 *  - Create an instance of the MobilePhone class
 *
 *  -  Create a while loop that runs as long as quit is false
 *      - get choice from a user
 *          - use a switch case and print the available actions
 *
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("+254717316925");

    public static void main(String[] args) {

        boolean quit = false;
        startPhone();
        printActions();
        while (!quit){
            System.out.println("\nEnter action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action){
                case 0:
                    System.out.println("\nShutting down");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:;
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }

    }

    private static void addContact() {
        System.out.println("Enter new contact name");
        String name  = scanner.nextLine();
        System.out.println("Enter phone number");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name,phone);
        if (mobilePhone.addNewContact(newContact)){
            System.out.println("New contact added: name = "+ name + ", phone = "+phone);
        }else {
            System.out.println("Cannot add, " + name + " already on the file");
        }
    }

    private static void updateContact(){
        System.out.println("Enter existing contact name: ");
        String existingContact = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(existingContact);
        if (existingContactRecord == null){
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Enter new contact name: ");
        String newName = scanner.nextLine();

        System.out.println("Enter new contact phone number");
        String newPhoneNumber = scanner.nextLine();

        Contact newContact = Contact.createContact(newName,newPhoneNumber);
        if (mobilePhone.updateContact(existingContactRecord , newContact)){
            System.out.println("Successfully updated record");
        }else {
            System.out.println("Error updating record");
        }

    }

    private static void removeContact() {
        System.out.println("Enter existing contact name: ");
        String existingContact = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(existingContact);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }
       if (mobilePhone.removeContact(existingContactRecord)){
           System.out.println("Successfully deleted");
       }else {
           System.out.println("Error deleting contact");
       }
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name: ");
        String existingContact = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(existingContact);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Name: "+ existingContactRecord.getName() +
                " phone number is "+existingContactRecord.getPhoneNumber());
    }


    private static void printActions() {
        System.out.println("\nAvailable actions: \npress");
        System.out.println("0 - to shutdown\n"+
                "1 - to print contacts\n"+
                "2 - to add a new contact\n"+
                "3 - to update existing contact\n"+
                "4 - to remove an existing contact\n"+
                "5 - query if an existing contact exists\n"+
                "6 - print a list of available actions.");
        System.out.println("Choose your action: ");
    }

    private static void startPhone() {
        System.out.println("Starting phone....");
    }
}
