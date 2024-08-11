package java_collections;
/*
 * Create an instance of a StockList
 *  - Create Stock Items
 *      - bread - car
 *      - cake  - chair
 *      - cup   - door
 *      - juice - phone
 *      - towel - vase
 *
 *  - Add stock items to the Stock List
 *
 * - Print stockList
 *      - notice that even though we added them alphabetically, the actual items are still stored at the moment in a
 *        pretty random order
 *      - this is because the StockList class is using a HashMap to store the items and the order is undefined for that
 *        reason
 *      - to maintain the alphabetical order that we added the items, we're going to change StockList class to use a
 *        LinkedHashmap instead
 *          - and now our list is showing in alphabetical order
 *
 * - The most important thing is that when you're coding, use generic setups like Map which is the interface
 *      Map<String , StockItem> list
 * - and then you are able to specify the actual class we're using here in the constructor
 *       this.list = new LinkedHashMap<>();
 * - and we only needed to change 1 line of code above from
 *       this.list = new HashMap<>();
 * - and this changed the entire implementation
 *
 * - You might also be wondering do we need to change anything for our Items(), and well there's no need to change
 *   the map returned by that method, as in fact there's no such thing such as an unmodifiable LinkedHashMap
 *      - unmodifiable collection objects are just a view into the underlying collection
 *      - so the map returned by the Items(), will have the same ordering as our main list
 *      - Let's print out the keys returned from the Items() in the main and confirm that
 *          - we can see the items are stored the way it's been configured
 *
 *
 * Next
 *  - Before we actually sell some stock , let's check what happens if we add an existing item again
 *      - We've built in a mechanism for adding stock to see if there's already stock in there, and should just add
 *        to the quantity
 *      - add some 7 additional cups, with a price of 0.45
 *
 *           stockList.addStock(new StockItem("Cup",0.45,7));
 *      - and this seems to be working nicely
 *
 *
 * Next,
 *  - Let's start selling something
 *  - Create a static method : sellItem()
 *
 *  sellItem(Basket basket , String item , int quantity) : int (returns the quantity of items sold)
 *     - retrieve the item from the stock list
 *     - if null, print "we don't sell the item requested"
 *        - return 0 as quantity, which will tell the calling process that the item was invalid
 *     - check if we're able to sell some stock by calling stockList.sellStock() which returns quantity sold depending
 *       on of course if there was enough from our stock
 *          - if quantity to sell > 0 , add to shopping basket
 *     - return quantity that was effectively sold
 *          - otherwise return 0, which means that we didn't have sufficient stock to sell
 *
 *
 * Next,
 *  - Create a basket and try selling some items
 *
 *      Basket alexCart = new Basket("Alex");
 *
    - Then pass the cart to the seller
 *
 *      sellItem(alexCart , "car",1);
 *
 *  - Then sort of print my Basket
 *
 *      System.out.println(alexCart); // which uses the toString() added in the Basket class
 *
 * - We know we have 2 Car and 2 have been sold to Alex
 *      - print the basket
 *
 * - Then sell another car to Jane
 *      - we should get an error - sort of no output because quantity is now 0
 *
 * Next
 *  - Sell an item that we don't have in stock , for example Spanner to Cyrus
 *
 *  - Sell some other items to Alex such as
 *      - bread , cups, juice etc
 *
 * Next,
 *  - The contents of the shopping basket are printed, and they appear in a random order
 *  - We could have them printed in the order they're added to the basket by changing the basket class to implement
 *    a LinkedHashMap instead of a HashMap
 *
 *
 * //// TreeMap ///////////
 *
 *  - But this is a good opportunity to see a TreeMap in action
 *  - Since we've already implemented the Comparable interface in StockItem, the only change we really need to make
 *    is to the constructor itself to get this to work
 *      - change the constructor to use a TreeMap
 *
 *  - And once we do that, we should find that the basket is now in alphabetical order
 *  - Also notice that the compareTo is being called because we're using a TreeMap, and it's sorting it for us
 *    automatically
 *  - We can see that adding items to a TreeMap results in far more work, for your code than using a HashMap
 *      - And that's because each time, we're entering a stock item, it's using .compareTo() to check basically the
 *        order comparing that
 *  - The TreeMap has to actually check through it's list to find the correct place to insert the new item
 *      - And of course it does that by comparing the items it finds with the items being added
 *
 *  - Although the TreeMap implementation is quite efficient , keep in mind that there is a performance cost to
 *    having the items ordered,
 *      - This is something to consider when trying to decide between a TreeMap and one of the unsorted map classes
 *        like HashMap and LinkedHashMap which will be faster as it doesn't need to execute that extra code
 *
 * Next
 *  - Let's attempt to use the StockList class Items() to bypass the correct way to add stock
 *  - Create a stock item - pen
 *      - Add it to the unModifiable Map
 *
 *      StockItem pen = new StockItem("Pen",1.12);
        stockList.Items().put(pen.getName(),pen);
 *
 * - We get UnSupportedOperationException, and this is good because we know that we set up the items and we're
 *   returning an unmodified map , which is a good security mechanism to ensure that there's no other way that items
 *   can be added to our basket other than using the methods that we've actually defined
 *
 * - However, there's one thing to be aware of though when allowing calling code to access your collections
 *      - Even if you've only provided an unmodifiable object, although the collection itself can't be changed, can't
 *        be added/removed , it's only the collection itself that is unmodifiable
 *      - The individual objects in the collection can be accessed and modified
 *
 *  - For example
 *      stockList.Items().get("Car").adjustStock(2000);
 *
 *  - If we now try to print the stocklist
 *
 *      System.out.println(stockList);
 *
 *  - You would get that this would actually work, this means even though the map returned is an unmodifiable map,
 *      we're able to change an individual item
 *
 * - Is that a problem ?
 *      - It depends
 *          - The calling program can't do anything with the stock items via the Items() that we hadn't allowed to do
 *            anyway
 *
 * - The point to consider here is that it's the collection itself that is unmodifiable and not the objects within it
 * - By providing the Items() , the StockList class allows callers to retrieve individual items and if you don't want
 *   this to happen, then consider another way to provide a list of items if you really want to
 *
 * - To see 1 possible option, let's add a PriceList() to the StockList class
 *      PriceList() : Map<String,Double>
            - create a Map<String,Double> prices - that uses a LinkedHashMap
 *          - loop through the cart items and add to prices Map
 *
 * - Then call the PriceList and loop through the prices Map
 *      - We're providing unmodifiable map of names and prices for the caller to use
 *      - The entries in the map consist of pairs of String and Double and both of this are immutable
 *      - not only can't the map be modified, but the individual entries in it can't be either
 *
 * - So if it's important that access to your objects in your collection is restricted, then avoid returning any sort
 *   of collection that contains the actual object
 *
 *
 *
 *
 *
 *
 */

import java.util.Map;

public class MainCart {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {
        // Create StockItems
        StockItem bread = new StockItem("Bread",0.86,100);
        StockItem cake = new StockItem("Cake",1.10,7);
        StockItem car = new StockItem("Car",12.50,2);
        StockItem chair = new StockItem("Chair",62.0,10);
        StockItem cup = new StockItem("Cup",0.5,200);
        StockItem door = new StockItem("Door",72.95,4);
        StockItem juice = new StockItem("Juice",2.50,36);
        StockItem phone = new StockItem("Phone",96.99,35);
        StockItem towel = new StockItem("Towel",2.40,80);
        StockItem vase = new StockItem("Vase",8.76,40);

        // Add to StockList class
        stockList.addStock(bread);
        stockList.addStock(cake);
        stockList.addStock(car);
        stockList.addStock(chair);
        stockList.addStock(cup);
        stockList.addStock(door);
        stockList.addStock(juice);
        stockList.addStock(phone);
        stockList.addStock(towel);
        stockList.addStock(vase);

        // Add additional cups
        stockList.addStock(new StockItem("Cup",0.45,7));

        // Print Stock List
        System.out.println(stockList);

        for (String name : stockList.Items().keySet()) {
            System.out.println(name);
        }

        // Create a basket - Alex
        Basket alexCart = new Basket("Alex");
        sellItem(alexCart , "Car",1);

        /*
        // print my Basket
        System.out.println(alexCart);


        // Then sell another car to Alex again
        sellItem(alexCart , "Car",1);
        System.out.println(alexCart);


        // Now we should get an error if we try to sell a car to jane - we have sold 2 that we had
        Basket janeCart = new Basket("Jane");
        if(sellItem(janeCart , "Car",1) != 1){
            System.out.println("There are no more cars in stock");
        }


        // sell spanner to Cyrus, we know we don't have it in our stock
        Basket cyrusCart = new Basket("Cyrus");
        sellItem(cyrusCart,"Spanner",1);


        // Then sell some juice, cup and bread to Alex
        sellItem(alexCart,"Juice",4);
        sellItem(alexCart,"Cup",12);
        sellItem(alexCart,"Bread",1);

        // print my cart
        System.out.println(alexCart);

        // Print the stock list
        System.out.println(stockList);

        StockItem pen = new StockItem("Pen",1.12);

        // below throws unSupportedOperationException = modify unModifiable map
        // stockList.Items().put(pen.getName(),pen);

        // try to adjust individual items in the Map - will work
        stockList.Items().get("Car").adjustStock(2000); // using the unmodifiable map
        stockList.get("Car").adjustStock(-1000); // or directly from the StockList.get()

        System.out.println(stockList);

        for (Map.Entry<String,Double> price : stockList.PriceList().entrySet()) {
            System.out.println(price.getKey() +" costs "+ price.getValue());
        }

        // Attempt to change the prices of an item below will result to an error
        //stockList.PriceList().get("Car").setPrice(200);

        */

    }

    private static int sellItem(Basket basket , String itemOrdered , int quantityOrdered){
        StockItem stockItem = stockList.get(itemOrdered);
        if (stockItem == null){
            System.out.println("We don't sell "+itemOrdered);
            return 0;
        }
        if (stockList.sellStock(itemOrdered,quantityOrdered) != 0){
            basket.addToBasket(stockItem,quantityOrdered);
            return quantityOrdered;
        }
        return 0;
    }
}
