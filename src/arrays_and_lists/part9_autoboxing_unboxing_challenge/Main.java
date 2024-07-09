package arrays_and_lists.part9_autoboxing_unboxing_challenge;
/*
 * Banking Application
 *
 * - Create a simple banking application
 * - There should be a Bank class
 * - It should have an arraylist of Branches
 * - Each Branch should have an arraylist of Customers
 * - The customers class should have an arraylist of Doubles (transactions)
 * - Customer:
 *   - Name, and the ArrayList of Doubles
 * - Branch:
 *   - Need to be able to add a new customer and initial transaction amount
 *   - Also needs to add additional transactions for that customer/branch
 * - Bank:
 *   - Add a new branch
 *   - Add a customer to that branch with initial transaction
 *   - Add a transaction for an existing customer for that branch
 *   - show a list of customers for a particular branch and optionally a list of their transactions
 * - Demonstration autoboxing and unboxing in your code
 * - Hint: Transactions
 *   - Add validation
 *      e.g. check if exists, or does not exist e.t.c
 * - Think about where you're adding the code to perform certain actions
 *
 *
 * main()
 *  - Create a bank
 *  - Add a branch to the bank
 *  - Add new customer
 *      - call addCustomer and pass details such as Branch, Customer name, initial deposit
 */
public class Main {

    public static void main(String[] args) {
        //Add bank
        Bank bank = new Bank("National Australia Bank");

        //create a branch
        if (bank.addBranch("Adelaide")){
            System.out.println("Adelaide branch created");
        }

        bank.addCustomer("Adelaide","Tim",50.05);
        bank.addCustomer("Adelaide","Mike",175.34);
        bank.addCustomer("Adelaide","Percy",220.12);

        bank.addBranch("Sydney");
        bank.addCustomer("Sydney","Bob",150.54);

        //Add transactions to existing customers
        bank.addCustomerTransaction("Adelaide","Tim",44.22);
        bank.addCustomerTransaction("Adelaide","Tim",12.14);
        bank.addCustomerTransaction("Adelaide","Mike",1.65);

        //list customers
        bank.listCustomers("Adelaide",true);
        bank.listCustomers("Sydney",true);

        //validate - add a customer to a branch that doesn't exist
        // next - add the branch and re-run
        bank.addBranch("Melbourne");
        if (!bank.addCustomer("Melbourne","Brian",5.53)){
            System.out.println("Error, Melbourne branch does not exist");
        }

        // validate - add a branch that we already know exist
        if (!bank.addBranch("Adelaide")){
            System.out.println("Adelaide branch already exists");
        }

        // validate - add transactions for a customer who does not exist
        if (!bank.addCustomerTransaction("Adelaide","Fergus",52.33)){
            System.out.println("Customer does not exist at this branch");
        }

        // validate - add a customer who already exist
        if (!bank.addCustomer("Adelaide","Tim",12.21)){
            System.out.println("Customer Tim already exist");
        }



    }
}
