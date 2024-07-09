package arrays_and_lists.part9_autoboxing_unboxing_challenge;

import java.util.ArrayList;

/*
 * Customer class
 *
 * Fields:
 *  - name : String
 *  - transactions : ArrayList<Double>;
 *
 * Constructor
 *  - takes 2 parameters :- name , initialAmount
 *  - initializes this.name to the name passed to the constructor
 *  - initializes this.transactions to a new ArrayList
 *  - call addTransaction and add initialAmount
 *
 * addTransaction(double) : public
 *  - adds transactions for existing customer
 *
 * Getters
 *  - getName() : String
 *  - getTransactions() : ArrayList<Double>
 */
public class Customer {
    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name, double initialAmount) {
        this.name = name;
        this.transactions = new ArrayList<>();
        addTransaction(initialAmount);
    }
    public void addTransaction(double amount){
        this.transactions.add(amount); // autoboxing
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}
