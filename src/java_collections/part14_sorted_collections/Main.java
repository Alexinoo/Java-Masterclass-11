package java_collections.part14_sorted_collections;
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

        // print stocklist
        System.out.println(stockList);

        // print just keys from stockList.Items()
        for (String name : stockList.Items().keySet()) {
            System.out.println(name);
        }


    }
}
