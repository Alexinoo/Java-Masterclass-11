package arrays_and_lists.part7_arraylist_challenge;

/*
 * Create a Contact class
 *
 * - Fields
 *      - String : name
 *      - String : phoneNumber
 * - Constructor
 *      - initializes the 2 fields
 * - Getters
 *      - getters for both name and phoneNumber
 *
 * Next - in other words a factory method - to be accessed directly instead of instantiating the Contact class
 *      - used in the main() when adding a new contact
 *  - Expose a public static method so that we can create a new contact record
 *  - This will be very useful when we are accessing the functions that are built into mobile phone
 *  - Instead of having to pass the parameter for the name and the phoneNumber, we can create a contact record
 *  - And we can also create it without creating a new separate object
 *
 *  - So we're basically calling the constructor to create a new contact record and it returns it
 *  - we'll see this later in use in the application
 *
 *
 */
public class Contact {

    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Contact createContact(String name, String phoneNumber){
        return new Contact(name, phoneNumber);
    }
}
