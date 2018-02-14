//Chan Kar Yik 16031635 (Leader)
//Daniel Yap
//Zen Sebastian
//Hafiz

/*Team leader's phone number: 0123892183
  Team leader's email address: 16031635@imail.sunway.edu.my	OR mryik@live.com */

//start class Product
public class Product {
    private final String category;
    private final String id;
    private String name;
    private double discount;
    private double price;
    private double dPrice;

    //constructor
    public Product(String product, String cat, String nm, double d, double p) {
        id = product;
        category = cat;
        name = nm;
        discount = d;
        price = p; //price equals to discount * price of item
        dPrice = (1 - (d / 100)) * p;

    }

    //return product ID
    private String getId() {
        return id;
    }
    //return product category
    private String getCategory() {
        return category;
    }
    // set product name
    public void setName(String nm) {
        name = nm;
    }
    //return product name
    public String getName() {
        return name;
    }
    // set discount of products
    public void setDiscount(double d) {
        if (d >= 0) { //discount rate can not be less than zero
            discount = d;
            dPrice = (1 - (d / 100)) * price;
        }
        else {
            throw new IllegalArgumentException("Value is less than zero.");
        }

    }

    //return discount of products
    private double getDiscount() {
        return discount;
    }
    //set prices of products
    public void setPrice(double p) {
        price = p;
    }
    //return prices of products
    private double getPrice() {
        return price;
    }
    //return discount price
    public double getdPrice() {
        return dPrice;
    }
    //print info
    public String toString() {
        return String.format("%-10s \t %-10s \t %-10s \t %-10.0f \t %-10.02f \t %-10.02f", getId(), getCategory(), getName(), getDiscount(), getPrice(), getdPrice());
    }

} // end class Product
