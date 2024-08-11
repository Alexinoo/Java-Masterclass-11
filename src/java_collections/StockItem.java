package java_collections;

public class StockItem implements Comparable<StockItem>{
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
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        if (this == o)
            return 0;
        if (o != null)
            return this.name.compareTo(o.getName()); // leverage String.compareTo() to compare the stock items
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price "+ String.format("%.2f",this.price);
    }
}
