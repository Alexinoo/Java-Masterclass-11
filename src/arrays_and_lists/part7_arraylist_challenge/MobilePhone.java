package arrays_and_lists.part7_arraylist_challenge;

import java.util.ArrayList;

/*
 * Create a MobilePhone class
 *
 * - Create an option for it to store the mobile phone number
 *      - String myNumber
 *
 * - Fields
 *      - ArrayList<Contact> myContacts
 *
 * - Constructor
 *      - 1 arg constructor
 *      - initializes both myNumber and the myContacts to a new ArrayList
 *
 * - Methods
 * - addNewContact(Contact contact) : boolean
 *      - return true if the addition was successful
 *      - return false if otherwise
 *
 *      - Takes Contact object as we're not sending the String and the phone number but rather a contact object which
 *        has been created outside this object
 *
 *      - Call findContact() and returns an integer
 *          - if position is >= 0
 *              - means contact is already added
 *              - return false
 *
 *          - if position is -1
 *              - means contact does not exist
 *              - add contact to the array list
 *              - return true
 *
 *  - findContact()
 *
 *      - Overload
 *          - findContact(Contact contact) that returns -1  if item is not found or >=0 if item is found
 *          - findContact(String contactName)
 *              - loop through myContacts ArrayList
 *                  - check if each item matches with the passed contactName
 *                      - if so , return index
 *                      - otherwise, return -1
 *
 *  - updateContact(Contact oldContact , Contact newContact) : boolean
 *
 *      - call findContact(oldContact) and pass the oldContact obj which return -1 if not found or >=0 if found
 *      - test if position < 0
 *          - return false, because the contact doesn't exist at that point
 *      - otherwise
 *          - proceed to update by calling set()and passing index of the contact plus the contact object
 *
 * - queryContact(Contact contact) : String
 *      - call findContact(contact) and pass the contact object
 *          - if position >=0
 *              - return the name of the contact
 *          - otherwise
 *              - return null
 *
 * - removeContact(Contact contact) : boolean
 *      - call findContact(contact) and pass the contact object
 *          - if (-1)
 *              - print to the user that the contact was not found
 *              - return false
 *          - otherwise
 *              - call remove(foundPosition) on myContacts and pass the index
 *              - print to the user that the contact was deleted successfully
 *              - return true for successful deletion
 */
public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact){
        if (findContact(contact.getName()) >= 0){
            System.out.println("Contact is already on file");
            return false;
        }
        myContacts.add(contact);
        return true;
    }
    public boolean updateContact(Contact oldContact , Contact newContact){
        int foundPosition = findContact(oldContact);
        if (foundPosition < 0){
            System.out.println(oldContact.getName() +  ", was not found ");
            return false;
        }
        // check if there is an existing contact with the same name as the new contact name
        if (findContact(newContact.getName()) != -1) {
            System.out.println("Contact with name "+newContact.getName()+ " already exist. Update was not successful");
            return false;
        }
        this.myContacts.set(foundPosition , newContact);
        System.out.println(oldContact.getName() + ", was replaced with "+newContact.getName());
        return true;
    }

    public boolean removeContact(Contact contact){
        int foundPosition = findContact(contact);
        if (foundPosition < 0){
            System.out.println(contact.getName() + ", was not found");
            return false;
        }
        this.myContacts.remove(foundPosition);
        System.out.println(contact.getName() + ", was deleted");
        return true;
    }

    public String queryContact(Contact contact){
        if (findContact(contact) >= 0)
            return contact.getName();
        return null;
    }
 public Contact queryContact(String contactName){
        int foundPosition = findContact(contactName);
        if ( foundPosition >= 0) {
            return this.myContacts.get(foundPosition);
        }
        return null;
    }

    public void printContacts(){
        System.out.println("Contact List");
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            System.out.println((i+1) + ". "+ contact.getName() + " -> "+ contact.getPhoneNumber());
        }
    }

    private int findContact(Contact contact) {
        return this.myContacts.indexOf(contact);
    }

    private int findContact(String contactName) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            if (contact.getName().equals(contactName))
                return i;
        }
        return -1;
    }

}
