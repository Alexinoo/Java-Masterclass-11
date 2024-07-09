package arrays_and_lists.part9_autoboxing_unboxing_challenge;

import java.util.ArrayList;

/*
 * Bank Class
 *
 * Fields
 *  - name : String
 *  - branches : ArrayList<Branch>
 *
 * Constructor
 *  - takes 1 parameter : name
 *  - initializes this.name to the name passed
 *  - initializes this.branches to a new ArrayList
 *
 * Methods
 *  - addBranch(String branchName) : boolean
 *      - checks if the Branch name exists
 *      - if null, calls add() on this.branches ArrayList and passes new Branch(branchName)
 *      - returns true, otherwise false
 *
 *  - addCustomer(String branchName, String customerName, double initialAmount) : boolean
 *      - checks if a certain branch exist
 *      - if it exists, call newCustomer from the branch class and pass the customer name plus the initial amount
 *      - return true if successful, otherwise, false
 *
 *  - addCustomerTransaction(String branchName, String customerName, double amount) : boolean
 *      - adds transaction to an existing customer
 *      - checks if the branch passed exist
 *      - if so, call branch.addCustomerTransaction(customerName,amount) and pass the Customer name & transaction amt
 *
 *  - findBranch(String branchName) : Branch
 *      - loop through branches ArrayList
 *      - check for any matches and returns branch record
 *      - otherwise, returns null
 *
 *  - listCustomers(String branchName, boolean showTransactions) : boolean
 *      - list all the customers in a given branch and optionally, all the transactions for each customer
 *
 *
 */
public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    public boolean addBranch(String branchName){
        if (findBranch(branchName) == null){
            this.branches.add(new Branch(branchName));
            return true;
        }
        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialAmount){
        Branch branch = findBranch(branchName);
        if (branch != null){
            return branch.newCustomer(customerName,initialAmount);
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double amount){
        Branch branch = findBranch(branchName);
        if (branch != null){
            return branch.addCustomerTransaction(customerName,amount);
        }
        return false;
    }

    private Branch findBranch(String branchName) {
        for (int i = 0; i < this.branches.size(); i++) {
            Branch checkedBranch = this.branches.get(i);
            if (checkedBranch.getName().equals(branchName))
                return checkedBranch;
        }
        return null;
    }

    public boolean listCustomers(String branchName , boolean showTransactions){
        Branch branch = findBranch(branchName);
        if (branch != null){
            System.out.println("Customer details for branch "+ branch.getName());

            ArrayList<Customer> branchCustomers = branch.getCustomers();
            for (int i = 0; i < branchCustomers.size(); i++) {
                Customer branchCustomer = branchCustomers.get(i);
                System.out.println("Customer: "+branchCustomer.getName() + "[" + (i+1) + "]");
                if (showTransactions){
                    System.out.println("Transactions");
                    ArrayList<Double> transactions = branchCustomer.getTransactions();
                    for (int j = 0; j < transactions.size(); j++) {
                        System.out.println("["+ (j+1) + "] Amount "+ transactions.get(j)); // unboxing - we can add doubleValue()
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }
}






