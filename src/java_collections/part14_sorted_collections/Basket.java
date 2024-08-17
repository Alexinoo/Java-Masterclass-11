package java_collections.part14_sorted_collections;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/*
 * Shopping Basket
 *
 * - We've used a Map for the StockList, because this section is about the Java Collections Framework
 * - The actual StockList could be stored in a number of ways, such as db, which is the most likely one that you would
 *   go for
 * - When customers come to buy the goods though, we need some way to store the items they're buying and the quantity
 *   of each one
 * - We store the quantity in stock in a field of the StockItem class.
 *      - And this is fine, because there's only 1 stock level for each item
 *
 * - When we come to the Shopping Basket though, the program will have to keep track of the quantities for each customer
 * - There could be thousands of customers, and having a field for each one in the StockItem class is not an option
 * - A Map is designed to store key-value pairs & it makes a good candidate for associating a quantity with a stock
 *   item
 * - By creating a new shopping basket for each customer, the program can track how many of each item, the customers
 *   are actually buying
 * - Now the StockItem is the key in the Map, in this situation and the quantity purchased is the value for the relevant
 *   key
 * - We could have just used the item's name as the key, but then the Basket class would have to look up the items in
 *   the StockList to update the stock count or get the price
 * - By storing the actual items, the Basket class can retrieve any info that it needs directly from the item which is
 *   very convenient
 *
 * ////////////////
 * Basket Class
 * ////////////////
 *  Fields
 *   final name : String
 *        - name of the basket (vary with customer)
 *
 *   final cart : Map<StockItem,Integer>
 *        - contains shopping items and the no of quantity to be purchased
 * /////
 * Constructor
 *      Basket(String name)
 *          - Initialize the name
 *          - Initialize the cart to a new HashMap
 * //////
 * Methods
 *      addToCart(StockItem item, int quantity) : int (takes StockItem and the quantity of each item ordered by the customer)
 *       - basic validation
 *          - check whether an item to be added is not null and if quantity to buy > 0
 *              - if true
 *                  - check if the item is already in cart, if so, return the quantity for that item, otherwise
 *                      default to 0
 *                      int quantityInBasket = cart.getOrDefault(itemToBuy,0);
 *
 *              - add the item to the cart, and increase the quantity (quantityInBasket  + orderedQuantity)
 *                  - 0 if the item wasn't found, or add to the existing quantity , if the itemToBuy was already in cart
 *
 *                      int quantityInBasket = cart.getOrDefault(itemToBuy,0);
                        cart.put(itemToBuy,quantityInBasket + quantityOrdered);
 *
 *              - then return number of quantities added to the basket
 *         - Otherwise, if the basic validation fails
 *              return 0 - 0 items added to the basket
 *
 *      /////////////
 *
 *      Items() : Map<StockItem,Integer>
 *       - returns unModifiableMap(cart) for our cart items
 *       - returns items in the cart as a view "read-only"
 *
 * Override toString
 *      toString() : String ( returns cart items plus the total price for the quantity ordered as a String)
 *          - Initialize totalCost to 0
 *          - loop through the items in the cart - Map<StockItem,Integer> cart
 *              - get name of each item in the Map and quantity purchased (which is the value)
 *              - get totalCost for each item in the cart
 *                  - multiply the quantity with the price of the current item
 *
 *           for (Map.Entry<StockItem,Integer> item : cart.entrySet()) {
                s += item.getKey() + ". "+ item.getValue() + " purchased\n";
                totalCost += ( item.getKey().getPrice() * item.getValue() );
            }
 *
 * ///////////////////////
 *
 * Summary of our Basket class
 *  - The items in the basket are stored as a Map, and the actual item is the key that we're using and the quantity
 *    purchased is the value in the map
 *
 *  - addToBasket(StockItem,Quantity)
 *      - checks the cart to see if there's an item already present in the shopping basket, when a new one is being
 *        added
 *      - using getOrDefault will either get the quantity already present or returns 0
 *      - the quantity returned is added to the requested quantity - convenient to handle for new item or add to existing
 *         if that item was already there in the cart
 *      - when the item is put in the map, it replaces any quantity that may already be present
 *
 *          cart.put(itemToBuy,quantityInBasket + quantityOrdered);
 *
 *  - Items()
 *      - Returns unmodifiable map for our cart items
 *
 *  - Overrode toString()
 *      - Returns cart items plus the total price for the quantity ordered
 *      - Returns total cost for all the items in the cart as a String
 *
 *
 *
 *
 */
public class Basket {
    private final String name;
    private final Map<StockItem,Integer> cart;

    public Basket(String name) {
        this.name = name;
        this.cart = new TreeMap<>();
    }

    public int addToBasket(StockItem itemToBuy , int quantityOrdered){
        if ((itemToBuy != null) && (quantityOrdered > 0) ){
            int quantityInBasket = cart.getOrDefault(itemToBuy,0);
            cart.put(itemToBuy,quantityInBasket + quantityOrdered);
            return quantityInBasket;
        }
        return 0;
    }

    public Map<StockItem,Integer> Items() {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public String toString() {
        String s = "\n Shopping basket "+ name + " contains "+ cart.size() + ((cart.size() > 1) ? " items" : " item") +"\n";
        double totalCost = 0;
        for (Map.Entry<StockItem,Integer> item : cart.entrySet()) {
            s += item.getKey() + ". "+ item.getValue() + " purchased\n";
            totalCost += ( item.getKey().getPrice() * item.getValue() );
        }
        return s + " Total cost "+ String.format("%.2f",totalCost);
    }
}
