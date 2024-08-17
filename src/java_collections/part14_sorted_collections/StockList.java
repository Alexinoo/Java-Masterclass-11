package java_collections.part14_sorted_collections;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * StockList class
 *  - Hold a list of stock items in a Map
 *  - Will also need methods to add stock to the StockList class and also to remove it when the actual stock items
 *      product is sold
 *  - Since the StockItem class uses the name when overriding equals() and hashCode(), we can safely use the name as the
 *    key for the map, which is going to make our life easier
 *
 * /////////////////
 * Fields
 *  final list : Map<String,StockItem>
 *
 * /////////////////
 * Constructor
 *  StockList()
 *      - Initialize our list marked as final to a LinkedHashMap()
 *
 * /////////////////
 * Methods
 *
 *  addStock(StockItem newItem) : int (adds stock items to our inventory which is this list)
 *      - process below only if item passed is not null
 *          - check if the item exists in the map - using the list.get(newItem.getName()) and passing then name of the newItem passed
 *          - change to list.getOrDefault(newItem.getName(), newItem)
 *              - if exist , return existingItem that was found in the list map
 *              - otherwise , return the default, which is the newItem passed
 *          - Either way, inStock is going to have a StockItem item, one retrieved from the map, or the one passed to this method
 *          - Check whether the item returned above is the same as the newItem passed
 *              - if they're not the same, this means, it existed, and so
 *                  - adjust the quantities of the existing
 *          - Either way, we'll add an entry into our map,
 *              - with the name of the item
 *              - and the item itself
 *          - If it already exist, put() will overwrite the one that was there
 *          - Then return the quantity of the newItem added
 *      - if item passed is null
 *          - return 0 - meaning no items were added
 *
 *   ////////////Alternatively ,
 *
 *      - we could have done something like this: old way of doing it
 *
 *         StockItem inStock = list.get(newItem.getName());
           if(inStock != null){
               newItem.adjustStock(inStock.getQuantityStock());
           }
 *
 *      - we're basically saying that before we add the new item to the list, we're basically testing to see whether
 *         there's already one in the map prior to us inserting whatever has been passed to this method
 *      - and if it is not equal to null means it found something and then adjust it's stock that way
 *
 *      - using getOrDefault just saves us the extra test for null because either way, it's going to return a valid
 *        Stock item
 *      - The extra check is instead of checking for null, we're actually check to see that if they're not the same
 *         which means that the item already existed in the map prior to the call of this method
 *
 *  sellStock(String itemName , int quantity) : int ( sell stock and adjust the stock levels accordingly )
 *      - takes a String representing the itemName to look for an item in our map and the quantity
 *      - use the itemName to look for the item from the list : Map<String,StockItem>
            - use getOrDefault(itemName, null) that either
 *              - returns the item if it was found
 *              - otherwise, return null
 *      - Then check for the following conditions before proceeding that
 *          - inStock is not null  (we're selling something that already exist)
            - check quantityInStock of the item  > quantityPurchased ( quantity in stock is more than one purchased)
            - check quantityPurchased is > 0    ( quantity purchased is more than 0 , don't allow negative or credits)
 *
 *          - If the above is true, the we'll do an adjustment to both the quantities and the stockList
 *              - pass a negative quantity to adjustStock() of that specific item as below
 *                   inStock.adjustStock(-quantityPurchased);
 *
 *              - the minus, represent we're deducting the item to make sure the negative amounts get adjusted
 *
 *              - then return how many quantities were taken out of stock
 *          - If false,
 *              - meaning
 *                  - we didn't have the item in stock
 *                  - or there wasn't sufficient quantity to take it out of stock
 *                  - or the quantity that was passed wasn't equal to zero or less
 *              - and if the above was the case return 0, - indicates nothing was taken out of stock
 *
 * ////////////////////
 *
 * Getters
 *  get(String key) : StockItem (searching for item in our list map :  Map<String,StockItem> list )
 *      - searches for a StockItem by the name passed and returns the StockItem
 *      - returns list.get(key)
 *          - return StockItem item , or null if not found
 *
 *   Items() : Map<String,StockItem>
        - returns Collections.unmodifiableMap(list)
 *      - returns a view, read-only
 *      - Instead of creating and return a copy so that the calling code could have access to the list
 *
 *      - Refer to the adventure game where we used id and it was fine because the calling code could also add new
 *        exits to the map
 *      - This didn't bother our class at all because the caller was working with a copy, but it's probably unkind to
 *        let the code think it could make changes when actually it was having no effect
 *
 *      Collections.unmodifiableMap(list)
 *      //////////////////////////////////
 *      - Collections framework provides a wrapper around the list , map and set collection to provide an unmodifiable
 *        collection
 *          - returns an unmodifiable view of the specified map
 *          - allows modules to provide users with "read-only" access to internal maps
 *          - query operations on the returned map "read through" to the specified map, and attempts to modify the
 *            returned map whether direct or via it's collection views results in an UnsupportedOperationException
 *      - Means we're only returning a read-only view into our map, and this prevents other code from adding/removing
 *        items and is faster as it doesn't have to create a copy of the collection
 *
 * /////////////////////
 *
 * Override toString()
 *  - toString() is going to return a list of all the items - a complete stock list
 *  - There is no reason stopping us from overriding toString for a class like StockList class that maintains
 *    quantity of a given number of objects and have it return a complex bit of output
 *  - print full list of all the stock plus the total value
 *
 *  - Iterate the entire list Map<String,StockItem>
 *       - basically loop through the entire item
 *              for (Map.Entry<String,StockItem> item : list.entrySet() )
 *
 *       - for each individual item, get StockItem with getValue() which return a single stock item
 *              StockItem stockItem = item.getValue();
 *
 *       - then get itemValue:double (which is no of quantities * price) for the stockItem above
 *              double itemValue = stockItem.getPrice() * stockItem.quantityInStock();
 *
 *       - print the string for that specific stock item
 *            e.g. "There are &quantity& in stock with a value of &itemValue&"
 *
 *       - add itemValue cumulatively to the totalCost for all the items
 *
 *   - The return a String in the format below
 *      "Total cost value &totalCost&"
 *
 *
 * /////
 *  - Renamed getter "getQuantity()" to "quantityInStock()" in the StockItem class
 *
 *
 * /////
 *  - Next we'll build up a Basket to hold items that we're going to sell
 *
 */
public class StockList {
    private final Map<String , StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem newItem){
        if (newItem != null){
            StockItem inStock = list.getOrDefault(newItem.getName(),newItem);
            if (inStock != newItem){
                newItem.adjustStock(inStock.quantityInStock());

            }
             list.put(newItem.getName(),newItem);
            return newItem.quantityInStock();
         }
        return 0;
    }

    public int sellStock(String itemToSell , int quantityPurchased){
        StockItem inStock = list.getOrDefault(itemToSell,null);
        if ((inStock != null) && (inStock.quantityInStock() >= quantityPurchased) && (quantityPurchased > 0)){
            inStock.adjustStock(-quantityPurchased);
            return quantityPurchased;
        }
        return 0;
    }

    public StockItem get(String name){
        return this.list.get(name);
    }

    public Map<String,StockItem> Items(){
        return Collections.unmodifiableMap(this.list);
    }

    public Map<String,Double> PriceList(){
        Map<String,Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String,StockItem> item: list.entrySet() ) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
       String s = "\n Stock List \n";
       double totalCost = 0.0;
        for (Map.Entry<String,StockItem> item : list.entrySet() ) {
            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();
            s += stockItem + " - There are "+stockItem.quantityInStock() +" in stock. Value of items: ";
            s += String.format("%.2f",itemValue)+ " \n";
            totalCost += itemValue;
        }

        return s + "============\nTotal stock value "+String.format("%.2f",totalCost);
    }
}
