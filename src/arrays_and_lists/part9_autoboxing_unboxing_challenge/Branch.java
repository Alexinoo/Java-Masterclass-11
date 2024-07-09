package arrays_and_lists.part9_autoboxing_unboxing_challenge;

import java.util.ArrayList;

/*
 * Branch Class
 *
 * Fields:
 *  - name : String
 *  - customers : ArrayList<Customer>

 * Constructor
 *  - initialize this.name variable
 *  - initialize this.customers to a new ArrayList<>() variable
 *
 * Getters
 *  - getName() : String
 *  - getCustomers() : ArrayList<Customer>
 *
 * Methods
 *  - public newCustomer(String customerName , double initialAmount) : boolean
 *      - calls findCustomer(customerName) and if null
 *          - call add(new Customer(customerName,initialAmount)) on the ArrayList and adds new customer to customer ArrayList
 *      - returns true, if the customer was added, otherwise, false
 *
 *  - public addCustomerTransaction(String customerName, double amount) : boolean
 *      - calls findCustomer(customerName) to determine if the customer exists
 *      - if so, call addTransaction() from the Customer class, passing the transaction amount
 *
 *
 *  - private findCustomer(String customerName) : Customer
 *      - loop through the customers ArrayList
 *      - use a getter getName() to check if the current customer name matches with the customerName passed
 *      - returns Customer object if found, otherwise, returns null
 */

public class Branch {

    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer(String customerName , double initialAmount){
        if (findCustomer(customerName) == null){
            this.customers.add(new Customer(customerName,initialAmount));
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction(String customerName, double amount){
        Customer existingCustomer = findCustomer(customerName);
        if ( existingCustomer != null){
            existingCustomer.addTransaction(amount);
            return true;
        }
        return false;
    }

    private Customer findCustomer(String customerName) {
        for (int i = 0; i < this.customers.size(); i++) {
            Customer checkedCustomer = this.customers.get(i);
            if (checkedCustomer.getName().equals(customerName))
                return checkedCustomer;
        }
        return null;
    }
}
