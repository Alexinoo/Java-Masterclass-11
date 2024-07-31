package naming_conventions_packages.part7_access_modifiers;
/*
 * Access Modifiers
 *
 * - Let's now take a look at the access modifiers , in particular , public , private, protected as well as package private
 * - We'll look at why you would want to restrict access to the fields and methods in your classes
 *
 * Use case
 *  - Implement a very simple and seriously flawed bank account
 *
 *  Account : Class
 *   Fields
 *    accountName : String
 *    balance : int
 *          - initialize to 0
 *    transactions : ArrayList<Integer>
            - store positive as deposits
 *          - store negative as withdrawals
 *
 *   Constructors
 *     Account(String name)
 *          - initialize name and transactions arraylist
 *
 *   Methods
 *     deposit(int amount) : void
 *          - add amount to transactions arraylist
 *          - update balance
 *     withdraw(int amount)
 *          - accept positive value and make it negative (consider negative number as a withdraw)
 *          - add to the transaction
 *          - update balance
 *
 * Next
 *  - Create an Account instance
 *  - Perform some transactions : deposits and withdrawals
 *  - Test deposit/withdraws with negative values
 *
 * Notes:
 *  - We've declared accountName , balance and transactions as private
 *  - This allows any program using the Account class to modify the balance directly
 *  - For example:
 *        account.balance = 5000;
 *        account.transactions.add(4500);
 *
 *      - the above will work
 *
 *  - In a real world system, the transactions list should only be modified by the deposit and withdraw methods
 *  - So by allowing access to the transactions list from outside the class and the balance for that matter, we're introducing potential for errors
 *    in the program
 *  - If you expose fields of your classes, then anyone using the Class will reasonably assume that they can manipulate the values of those fields
 *  - And because this bypasses the methods you've written to use those fields, as we can see here that the classes may no longer behave as you
 *    intended them to
 *  - In other words, by making the accountName, balance and transaction fields public , we've allowed them to be modified outside of the Account class
 *
 * Solution
 *  - Change them to private
 *  - After we do that now from the main, we actually get an error if we try to update them directly
 *  - The only way now we can update them directly is by using the deposit and withdrawal methods which means it's a lot more secure
 *
 *
 * Discussion
 *  - Access Control is granted by the top level or at the member level
 *  - So at the top level, you can make your classes and interfaces public or packaged private
 *  - You can't define a class at the top level
 *      - Let's create one : PrivateClass
 *      - We'll get an error if we try to make it private , and that means we can't have a Private class at this level, at sort of the top level of
 *        this file
 *
 * Top Level
 *  - Only classes , interfaces and enums can exist at the top level, everything else must be included within one of these
 *
 * public
 *  - Means that the object is visible to all classes everywhere, whether they are in the same package, or have imported the package containing the
 *    public class
 *
 * package-private
 *  - the object is only available within it's own package ( and is visible to every class within the same package)
 *  - Package-private is specified by not specifying (It is the default if you do not specify public)
 *  - There is no "package-private" keyword
 *
 *
 *
 * Member Level
 *  - public
 *      - at the member level, public has the same meaning as at top level
 *      - a public field, member and public method can be accessed from any other class anywhere , even in a different package
 *
 *  - package-private
 *      - this also has the same meaning as it does at the top level
 *      - an object with no access modifier is visible to every class within the same package (but not to classes in external packages)
 *
 *  - private
 *      - the object is only visible within the class it is declared in
 *      - it is not visible anywhere else (including in subclasses of its class)
 *
 *  - protected
 *      - the object is visible anywhere in its own package (like package-private) but it's also visible also in subclasses even if they are in
 *        another package
 *      - and that's why you wanna use protected instead of private
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Account account = new Account("Alex Mwangi");
        account.deposit(1000);
        account.withdraw(500);
        account.withdraw(-200);
        account.deposit(-20);
        account.calculateBalance();

        System.out.println("Your balance is "+account.getBalance());

       // account.balance = 5000;
        System.out.println("Your balance is "+account.getBalance()); // Your balance is 5000


    }
}
