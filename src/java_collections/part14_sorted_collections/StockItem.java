package java_collections.part14_sorted_collections;
/*
 * StockItem class
 *  - Represent the details of the product such as name, price and quantity in stock
 *  - Will also override equals() and hashCode() and override Comparable so that the stock items can be used in the
 *     sorted collections
 *
 * Fields
 *  final name : String
 *        price : double
 *        quantityStock : int
 *             - can be initialized either during declaration or in the constructor (but not in both)
 *
 * Constructor
 *  StockItem(String name, double price)
 *      - Initialize name and price
 *      - Initialize quantityStock to 0
 *
 * Getters
 *  getName() : String
 *
 *  getPrice() : double
 *
 *  getQuantityStock() : int
 *      - Renamed later to quantityInStock()
 *
 * Setters
 *  setPrice(double price) : void
 *      - validate that price is > 0.0 before saving
 *      - set current price to the price that was passed
 *
 *  setQuantity() : void
 *  - Rename to
 *  adjustStock(int newQuantity) : void
 *      - add current quantity to the newQuantity passed and store that in newQuantity variable
 *      - update currentQuantity to the newQuantity (only if newQuantity >= 0)
 *
 * Override equals(Object obj) and hashCode()
 *
 *  equals() : boolean
 *      - print "entering equals()"
 *      - test if we're testing against the same object, if so, return true
 *      - test if passed obj is null or ( whether the class of the current obj is not equal to the class of the obj passed)
 *          - if that's the case, return false, we can't compare them because they belong to different classes
 *      - then cast the obj passed to StockItem obj and call getName() and store as that as String
 *      - return the Standard String.equals() , comparing the name of the current obj and the obj passed
 *
 *
 *  hashCode() : int
 *      - return the String.hashCode of the name and add an arbitrary number ; 31 or 57
 *
 * Override compareTo(StockItem obj)
 *
 *  compareTo : int
 *      - print "entering StockItem.compareTo()"
 *
 *      - Test case
 *          - if obj passed is the same as the current obj , return 0
 *
 *      - Other Test cases ( Optional)
 *          - if obj passed is > than the current obj, return -1
 *          - if obj passed is < than the current obj, return 1
 *
 *      - If obj is not null
 *          - call compareTo on the name with the name of the obj passed
 *          - uses the inbuilt functionality - String.compareTo() to compare current object's name with
 *              the name of the object passed to this method
 *      - throw new NullPointerException - if we get to that point
 *          - just to say we're not comparing anything to null
 *
 * toString()
 *      - print this.name : String.format("%.2f",price)
 *      - name and price formatted in 2 decimal places
 *
 *
 *
 * ////////////////
 * - It would probably be useful to allow the quantity of the stock to be set, when the stock items are created and
 *   we should really include that field in the Constructor as well
 * - It's also useful to have a constructor that doesn't include the stock levels , and this can allow someone in
 *   the office perhaps to set up the stock items before the list was passed to the warehouse for the stock take
 *   to be done
 * - Overload the constructor, so that items can be created with/without stock levels
 *
 *      StockItem(String name, double price, int quantityStock)
 *      - initialize all the 3 items to the items passed
 *
 * - And now it makes more sense to initialize the quantityStock to 0 in the 2nd-args constructor below
 *      StockItem(String name, double price)
 *
 *
 * /////////////////// adjustStock(int quantity)
 *  - Allow stock levels to be increased/decreased as stock is added and sold
 *  - includes a check to make sure the stock levels can't drop below 0
 *      - we're doing it that way to ensure we can also pass negative quantities, and literally add/deduct stock
 *         levels as well
 *
 * ////////////////// @Override equals() , hashCode() , compareTo() and toString()
 *
 *
 *
 * ////////////////
 *  - Next, Implement a StockList class that will hold a list of all the stock items in a map
 *  - Then will need some methods to add stock and remove it when it's sold
 *
 */
public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock;

    public StockItem(String name, double price) {
       this.name = name;
       this.price = price;
       this.quantityStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if (price > 0.0)
            this.price = price;
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0)
            this.quantityStock = newQuantity;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (this == obj)            // test if you're testing against the same obj in memory
            return true;            // returns true - it's the same obj in memory - share same reference
        if ((obj == null) || (obj.getClass() != this.getClass()) ) // if obj is null or don't belong to the same class
            return false;
        String objName = ((StockItem) obj).getName(); // cast as a StockItem obj and call getName to return a String obj
        return this.name.equals(objName); // compare the name of our current obj with the name of the obj passed ; true/false if same
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 57; // hash the name and add an arbitrary number - like 57/31
    }

    @Override
    public int compareTo(StockItem item) {
        System.out.println("Entering StockItem.compareTo()");
        if (this == item) return 0;
        if (item != null)
            return this.name.compareTo(item.getName());
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price "+ String.format("%.2f",this.price);
    }
}
