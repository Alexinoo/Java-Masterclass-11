package java_collections.part14_sorted_collections;

import java.util.Map;

/*
 * Sorted Collections
 *
 * - We've looked at the following so far
 *      - List<E> interface and it's implementation with ArrayList and LinkedList
 *      - Map<K,V> interface and it's implementation with HashMap
 *      - Set<E> interface and it's implementation with a HashSet
 *
 * - There's 2 other collection interfaces which we haven't looked at and might look ito them later in this course
 *      - Queue
 *      - Dequeue
 * - Now let's look at the variant of the HashMap and HashSet classes which are known as
 *      - LinkedHashMap
 *      - LinkedHashSet
 * - They're also the sorted versions of the Map<K,V> and Set<E> interfaces, sorted map and sorted set that are
 *   implemented in TreeMap and TreeSet
 * - Since the operations on Sets and Maps are so similar, the examples in here will concentrate on Maps<K,V> rather
 *    than Sets
 * - The Linked variant of HashMap and HashSet are called LinkedHashMap and LinkedHashSet and the only difference is
 *    that they've got an ordering to them
 * - In the oracle docs, the ordering of items in a HashMap or HashSet is described as chaotic , but the linked versions
 *   maintain insertion order
 *      - Once you put items in a collection, you know the order that the result will come back when you iterate over it
 * - Will look at example of this by adding our items in alphabetical order
 *
 * /////
 *  - The example is going to allow users to add purchases to a shopping basket after choosing them from a list of all
 *    items on sale
 *
 * ////
 *  - We need to maintain 2 collections
 *      1. A list of all the items that are in stock
 *      2. Items that a customer has added to their basket
 *  - Though it might seem contrived to add the stock items in alphabetical order, in reality, items will certainly come
 *    from the database and they could be in any order that we wanted by using an ORDER BY clause in the query
 *
 * ////
 *  - Let's get on with the example to see how a LinkedHashMap can be useful and will ultimately extend that to use a
 *    TreeMap
 *
 * /////
 *  - Create StockItem class
 *  - Create StockList class
 *  - Create Basket class
 *
 * /////////////////////////////////////////////////////////////////////
 * Main() - Add items to StockList and start selling
 *
 * ////////////////////////////////////////////////////////////////////
 *
 * - Create an instance of a StockList to have access to addStock() and so on
 *       private static StockList stockList = new StockList();
 *
 * - Create StockItems
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
 *
 * - Add all the stock items to stockList by calling addStock() on StockList stockList instance
 *      stockList.addStock(bread);
        stockList.addStock(cake);
        stockList.addStock(car);
        stockList.addStock(chair);
        stockList.addStock(cup);
        stockList.addStock(door);
        stockList.addStock(juice);
        stockList.addStock(phone);
        stockList.addStock(towel);
        stockList.addStock(vase);
 *
 * - Print stockList
 *      - uses the toString() that we overload to give us an overview of the actual StockList
 *
 * //////////
 * - Running
 *      - format price in the StockList class because we're calling stockList.toString() implicitly
 *          - format individual price in the toString() in the StockItem class
 *
 *      - notice that even though we added the stock items alphabetically, the actual items are still stored at
 *          the moment in a pretty random order
 *
 *      - this is because the StockList class is using a HashMap to store the items and the order is undefined for that
 *        reason
 *      - to maintain the alphabetical order that we added the items, we're going to change StockList class to use a
 *        LinkedHashmap instead
 *          - and now our list is showing in alphabetical order
 *
 * //////////
 * - The most important thing is that when you're coding, use generic setups like Map which is the interface
 *      Map<String , StockItem> list
 * - and then you are able to specify the actual class we're using here in the constructor
 *       this.list = new LinkedHashMap<>();
 * - and we only needed to change 1 line of code above from
 *       this.list = new HashMap<>();
 * - and this changed the entire implementation
 *
 * ////////////
 * - You might also be wondering do we need to change anything for our Items(), and well there's no need to change
 *   the map returned by that method, and in fact there's no such thing such as an unmodifiable LinkedHashMap
 *      - unmodifiable collection objects are just a view into the underlying collection
 *      - so the map returned by the Items(), will have the same ordering as our main list
 *
 * ////////////
 * - Let's print out the keys returned from the Items() in the main and confirm that
 *
 *        for (String name : stockList.Items().keySet()) {
            System.out.println(name);
          }

 *      - we can see the items are stored the same way , depending on how our list Map was configured
 *          - if configured as a HashMap, keys will be printed without any order
 *          - if configured as a LinkedHashMap, keys will be printed in alphabetical/insertion
 *      - the unmodifiable map is identical to the map order that is being returned by default
 *
 * ///////////////// //////////////////////////
 *  TreeMap and UnModifiable Maps - Section
 * ////////////////////////////////////////////
 * ////////////////////////////////////////////
 *
 *  - Before we actually sell some stock , let's check what happens if we add an existing item again
 *      - We've built in a mechanism for adding stock to see if there's already stock in there, and should just add
 *        to the quantity
 *      - add some 7 additional cups, with a price of 0.45
 *
 *           stockList.addStock(new StockItem("Cup",0.45,7));
 *
 *      - and this seems to be working nicely
 *
 * ////////////////
 *  - Let's add a static method : sellItem() for selling some items
 *
 *  static sellItem(Basket basket , String item , int quantity) : int (returns the quantity of items sold)
 *
 *     - retrieve the item from the stock list
 *          StockItem stockItem = stockList.get(itemOrdered);
 *
 *     - if null, print "we don't sell the item requested"
 *          if (stockItem == null){
                System.out.println("We don't sell "+itemOrdered);
                return 0;
            }
 *        - return 0 as quantity, which will tell the calling process that the item was invalid and it didn't
 *          actually exist because the quantity was 0
 *
 *
 *     - check if we're able to sell some stock by calling stockList.sellStock() which returns quantity sold depending
 *       on of course if there was enough from our stock
 *          - if quantity to sell > 0  or if we have a valid quantity
 *
 *                if (stockList.sellStock(itemOrdered,quantityOrdered) != 0){
                    basket.addToBasket(stockItem,quantityOrdered);
                    return quantityOrdered;
                }
 *              - add the stockItem to the shopping basket
 *
 *              - by calling addToBasket(StockItem stockItem,int quantity) from the basket instance
 *
 *
 *
 *          - return quantity that was effectively sold
 *
 *      - Otherwise return 0, which means that we didn't have sufficient stock to sell
 *
 * //////////////////////////
 * - Create a basket and try selling some items
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
 * ////////////
 *  - Then sell another Car to Alex again
 *      sellItem(alexCart , "car",1);
 *
 * - We know we have 2 Car and 2 have been sold to Alex
 *      - print the basket
 *
 * ///////
 *
 * - Then sell another car to Jane
 *      - we should get an error - sort of no output because quantity is now 0
 *
 * ////////
 *  - Sell an item that we don't have in stock , for example Spanner to Cyrus
 *
 *  - Sell some other items to Alex such as
 *      - bread , cups, juice etc
 *
 *      sellItem(alexCart,"Juice",4);
        sellItem(alexCart,"Cup",12);
        sellItem(alexCart,"Bread",1);

        - Then print my cart
            System.out.println(alexCart);
 *
 * //////
 *  - Print the Stock list again
 *
        System.out.println(stockList);
 *
 *      - confirm all stock levels are adjusted correctly
 *          - Bread was initially 100 ,sold 1 , now we have 99
 *          - Cars were initially 2 ,sold 2, now we have 0 in stock
 *          - Cups were initially 207 ,sold 12, now we have 195
 *          - Juice was initially 36 ,sold 4, now we have 32
 *
 * ////////
 *  - Update Basket.toString
 *      - print item if 1 item is sold otherwise, print items if more than 1
 *
 *       String s = "\n Shopping basket "+ name + " contains "+ cart.size() + ((cart.size() > 1) ? " items" : " item") +"\n";

 * ///////////
 *  - Check if we were able to sell 1 car to Jane and if so , print something more meaningful
 *      if(sellItem(janeCart , "Car",1) != 1){
            System.out.println("There are no more cars in stock");
        }
 *      - probably it would have been a good idea to do for every call to sellItem() checking that that the return
 *          value is equivalent to the number requested or not equal to 0
 *      - but it doesn't matter which condition we use there
 *          - then printing a message if the item is out of stock
 *      - Just being a little bit nicer, than no just printing anything in case it's not working
 * /////////
 *  - The contents of the shopping basket are printed, and they appear in a random order
 *      - check cartItems/ shopping basket for Alex
 *
 *  - We could have them printed in the order they're added to the basket by changing the basket class to implement
 *    a LinkedHashMap instead of a HashMap, but this is a good opportunity to see a TreeMap in action
 *
 *
 * //// TreeMap ///////////
 *
 *  - Since we've already implemented the Comparable interface in StockItem, the only change we really need to make
 *    is to the constructor itself to get this to work
 *
 *  - If we go to Basket class
 *      - at the moment, you can see that we're using a HashMap
 *          this.cart = new HashMap<>();
 *
 *      - all we really need to do to get that working is to change that to a TreeMap
 *          this.cart = new TreeMap<>();
 *
 *  - And once we do that, we should find that the basket is now in alphabetical order
 *
 *  - Also notice that the compareTo is being called because we're using a TreeMap, and it's sorting it for us
 *    automatically
 *
 *  - We can see that adding items to a TreeMap results in far more work, for your code than using a HashMap
 *      - And that's because each time, we're entering a stock item, it's using .compareTo() to check basically the
 *        order comparing that in other words
 *
 *  - The TreeMap has to actually check through it's list to find the correct place to insert the new item
 *      - And of course it does that by comparing the items it finds with the items being added
 *
 *  - Although the TreeMap implementation is quite efficient , keep in mind that there is a performance cost to
 *    having the items ordered,
 *      - This is something to consider when trying to decide between a TreeMap and one of the unsorted map classes
 *        like HashMap and LinkedHashMap which will be faster as it doesn't need to execute that extra code
 *
 * /////////////////////////////
 *
 *  - Let's attempt to use the StockList class Items() to bypass the correct way to add stock
 *  - Create a stock item - pen
 *      - Add it to the unModifiable Map
 *
 *      StockItem pen = new StockItem("Pen",1.12);
        stockList.Items().put(pen.getName(),pen);
 *
 *      - Instead of do it the correct way as below
 *      stockList.addStock(pen);
 *
 * /////////
 * - We actually get an error "UnSupportedOperationException", and this is good because we know that we set up the
 *   items & we're returning an unmodified map
 *
 * - This is a good security mechanism to ensure that there's no other way that items can be added to our basket
 *   other than using the methods that we've actually defined
 *
 * - However, there's one thing to be aware of though when allowing calling code to access your collections
 * - Even if you've only provided the unmodifiable object,
 *      - although the collection itself can't be changed, items can't be added/removed , it's only the collection
 *        itself that is unmodifiable
 *
 *  - comment out on below ,
 *          stockList.Items().put(pen.getName(),pen);
 *      - so that we don't get UnSupportedOperationException and the code that follow is able to run
 *
 * ///////////
 *
 * - The individual objects in the collection can be accessed and modified, which is a little bit of a gotcha ther
 *
 * - For example
 *      stockList.Items().get("Car").adjustStock(2000);
 *
 *  - If we now try to print the stocklist
 *
 *      System.out.println(stockList);
 *
 *  - You would get that this would actually work, this means even though the map returned unmodifiable map, we're
 *     able to adjust the quantity of an individual item
 *
 * ///////////
 * Is that a problem ?
 * - And the answer is : It depends
 *    - The calling program can't do anything with the stock items via the Items() that we hadn't allowed to do anyway
 *
 *          stockList.get("Car").adjustStock(-1000); // or directly from the StockList.get()
 *    - You can see now that we've got only 1000 cars in stock , and the adjustment of 2000 is something that the
 *      calling program could have done anyway
 *    - There's no problem in this case we had the functionality available to do that anyway
 *          - There's no problem with this particular case without having access to objects by the read-only collection
 *
 *  - But if you design a System so that the StockList didn't have a get() though, then you wouldn't want the code
 *     to use the get() of the unModifiable collection either
 *
 * - The point to consider here is that it's the collection itself that is unmodifiable and not the objects within it
 *
 * - By providing the Items() , the StockList class allows callers to retrieve individual items and if you don't want
 *   this to happen, then consider another way to provide a list of items if you really want to
 *
 * - To see 1 possible option, let's add a PriceList() to the StockList class
 *
 *      PriceList() : Map<String,Double>
            - create a Map<String,Double> prices - that uses a LinkedHashMap
                Map<String,Double> prices = new LinkedHashMap<>();

 *          - loop through the cart items and add to prices Map

                for (Map.Entry<String,StockItem> item: list.entrySet() ) {
                    prices.put(item.getKey(), item.getValue().getPrice());
                }
                return Collections.unmodifiableMap(prices);
 *
 * ///////////
 * - Then call the PriceList and loop through the prices Map
 *
 *       for (Map.Entry<String,Double> price : stockList.PriceList().entrySet()) {
            System.out.println(price.getKey() +" costs "+ price.getValue());
        }

 *      - So here we're providing unmodifiable map of names and prices for the caller to use
 *
 *      - The entries in the map consist of pairs of String and Double and both of this are immutable
 *
 *      - not only can't the map be modified, but the individual entries in it can't be either
 *
 * - So if it's important that access to your objects in your collection is restricted, then avoid returning any sort
 *   of collection that contains the actual object
 *
 * /////
 * - After the above step , now any attempt to change the prices of an item below will result to an error
        stockList.PriceList().get("Car").setPrice(200);
 *
 *
 * - If you designed a System so that the StockList didn't have a get(), then you wouldn't want code using the get()
 *    of the unmodifiable collection either
 *      - just wanted us to know there are 2 ways of sor of accessing and modifying individual elements
 */
public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {

        // Create StockItems
        StockItem bread = new StockItem("Bread", 0.86, 100);
        StockItem cake = new StockItem("Cake", 1.10, 7);
        StockItem car = new StockItem("Car", 12.50, 2);
        StockItem chair = new StockItem("Chair", 62.0, 10);
        StockItem cup = new StockItem("Cup", 0.5, 200);
        StockItem door = new StockItem("Door", 72.95, 4);
        StockItem juice = new StockItem("Juice", 2.50, 36);
        StockItem phone = new StockItem("Phone", 96.99, 35);
        StockItem towel = new StockItem("Towel", 2.40, 80);
        StockItem vase = new StockItem("Vase", 8.76, 40);

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

        // print stocklist
        System.out.println(stockList);

        // print just keys from stockList.Items()
        for (String name : stockList.Items().keySet()) {
            System.out.println(name);
        }

        // Create a basket - Alex
        Basket alexCart = new Basket("Alex");
        sellItem(alexCart , "Car",1);

        // print my Basket
        System.out.println(alexCart);

        // sell another car to Alex again and print the basket
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

        // Bypass the correct way to addStock by calling stockList.addStock(StockItem item, int price)
        // Instead do it via unModifiableMap returned from "stockList.Items()"
        StockItem pen = new StockItem("Pen",1.12);
        //stockList.Items().put(pen.getName(),pen);

        // try to adjust individual items in the Map - will work
        stockList.Items().get("Car").adjustStock(2000); // using the unmodifiable map
        stockList.get("Car").adjustStock(-1000); // or directly from the StockList.get()

        System.out.println(stockList);

        // call StockList.priceList and print the contents out
        for (Map.Entry<String,Double> price : stockList.PriceList().entrySet()) {
            System.out.println(price.getKey() +" costs "+ price.getValue());
        }

        // Attempt to change the prices of an item below will result to an error
        //stockList.PriceList().get("Car").setPrice(200);

    }

    private static int sellItem(Basket basket , String itemInCart , int quantityToBuy){
        StockItem stockItem = stockList.get(itemInCart);
        if (stockItem == null){
            System.out.println("We don't sell "+itemInCart);
            return 0;
        }
        if (stockList.sellStock(itemInCart,quantityToBuy) != 0){
            basket.addToBasket(stockItem,quantityToBuy);
            return quantityToBuy;
        }
        return 0;
    }
}
