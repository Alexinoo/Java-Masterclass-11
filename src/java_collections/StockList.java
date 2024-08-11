package java_collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * StockList class
 *  - Hold a list of stock items in a Map
 *
 *  - Since the StockItem class uses the name when overriding equals and hash code, we can safely use the name as the
 *    key for the map, which is going to make our life easier
 *
 * Methods
 *  addStock(StockItem newItem) : int
 *      - add stock items
 *      - check if the item exists in the map
 *          - if exist , get the name of the item
 *          - otherwise , return the default, which is the item passed
 *      - Either way, inStock is going to have an item, one retrieved from the map, or the one passed to this method
 *      - Next
 *          - check if we already have quantities of this item
 *
 *  sellStock(String item , int quantity) : int
 *      - sell stock and adjust the stock levels accordingly
 *
 * Getters
 *  get(String name) : StockItem
 *      - searches for a StockItem by the name passed and returns the StockItem
 *
 *  Items() : Map<String,StockItem>
        - returns Collections.unmodifiableMap
 *      - returns a view, read-only
 *      - Instead of creating and return a copy so that the calling code could have access to the list
 *
 *      - Refer to the adventure game where we used id and it was fine because the calling code could also add new
 *        exits to the map
 *      - This didn't bother our class at all because the caller was working with a copy, but it's probably unkind to
 *        let the code think it could make changes when actually it was having no effect
 *
 *      - Collections framework provides a wrapper around the list , map and set collection to provide an unmodifiable
 *        collection
 *          - returns an unmodifiable view of the specified map
 *          - allows modules to provide users with "read-only" access to internal maps
 *          - query operations on the returned map "read through" to the specified map, and attempts to modify the
 *            returned map whether direct or via it's collection views results in an UnsupportedOperationException
 *      - Means we're only returning a read-only view into our map, and this prevents other code from adding/removing
 *        items and is faster as it doesn't have to create a copy of the collection
 *
 * Override toString()
 *  - toString() is going to return a list of all the items - a complete stock list
 *  - so far in the course we've used it to return 1 item
 *  - but there is no reason stopping us from overriding toString for a class like StockList class that maintains
 *    quantity of a given number of objects and have it return a complex bit of output
 *
 *  - print full list of all the stock plus the total value
 *
 *
 */
public class StockList {
    private final Map<String , StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem newItem){
        if (newItem != null){

            /* Below can also be written as
             StockItem inStock = list.get(newItem.getName());
             if(inStock != null){
                 newItem.adjustStock(inStock.getQuantityStock());
             }
             */

            // check if already have quantities of this item
            StockItem inStock = list.getOrDefault(newItem.getName(),newItem);

            // if there are already stocks on this item, adjust the quantity
            if (inStock != newItem){
                // means we got the item from our Map or is it the same stock that has been passed
                // if it's already in the Map, adjust the stock quantity with whatever the quantity that was passed to
                // this method (StockItem item)
                // pass the old stock quantity to be added to old stock quantity
                newItem.adjustStock(inStock.quantityInStock());

            }
            // Either way, new item will be added to the Map
            // If it already exists, it will overwrite the one tht was there, otherwise add as normally it would
             list.put(newItem.getName(),newItem);

            // return the new quantity in stock
            return newItem.quantityInStock();
         }
        return 0; // indicates no stock movement
    }

    public int sellStock(String itemToSell , int quantityPurchased){
        // look for the item to sell, return it if found, otherwise, return null
        StockItem inStock = list.getOrDefault(itemToSell,null);

        //check for the following conditions
        // inStock is not null
        // check quantityInStock of the item  > quantityPurchased
        // check quantityPurchased is > 0

        if ((inStock != null) && (inStock.quantityInStock() >= quantityPurchased) && (quantityPurchased > 0)){

            //if above is satisfied - adjust quantity of the item sold
            inStock.adjustStock(-quantityPurchased);

            // return the quantity of how many items taken out of stock
            return quantityPurchased;
        }
        // indicate nothing was taken out of stock
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
       // Iterate over the entire map
       // get individual item
       // current stock * price for each item
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
