//Chan Kar Yik 16031635 (Leader)
//Daniel Yap  
//Zen Sebastian
//Hafiz

/*Team leader's phone number: 0123892183
  Team leader's email address: 16031635@imail.sunway.edu.my	OR mryik@live.com */
import java.util.ArrayList;

//start class Category
public class Category {

    //creating objects for storing productID, category, product name, discount rate, and original price
    Product C1I1 = new Product("P1", "Apple", "Iphone5", 0, 1200.00); //discount equals zero when there is no discount
    Product C1I2 = new Product("P2", "Apple", "Iphone6", 10, 1999.50);
    Product C1I3 = new Product("P3", "Apple", "Iphone7", 15, 2599.00);

    Product C2I1 = new Product("P1", "Samsung", "Galaxy S8", 0, 1100.00);
    Product C2I2 = new Product("P2", "Samsung", "Galaxy S9", 10, 1500.00);
    Product C2I3 = new Product("P3", "Samsung", "Galaxy S10", 15, 2000.50);

    Product C3I1 = new Product("P1", "Sony", "Xperia Z4", 0, 1200.50);
    Product C3I2 = new Product("P2", "Sony", "Xperia Z5", 10, 1750.50);
    Product C3I3 = new Product("P3", "Sony", "Xperia Z6", 15, 2050.00);

    ArrayList < Product > cat1 = new ArrayList < Product > ();
    ArrayList < Product > cat2 = new ArrayList < Product > ();
    ArrayList < Product > cat3 = new ArrayList < Product > ();

    //constructor
    public Category() {
        cat1.add(C1I1);
        cat1.add(C1I2);
        cat1.add(C1I3);

        cat2.add(C2I1);
        cat2.add(C2I2);
        cat2.add(C2I3);

        cat3.add(C3I1);
        cat3.add(C3I2);
        cat3.add(C3I3);
    }
    //print categories
    public void getCategory(int cat) {
        System.out.printf("\nProducts\n-------------------------------\n");
        System.out.printf("%-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s \n", "ID", "Category", "Name", "Discount(%)", "Price(RM)", "After Discount(RM)");
        switch (cat) {
            case 1:
                for (Product p:
                        cat1) {
                    System.out.println(p);
                }
                break;
            case 2:
                for (Product p:
                        cat2) {
                    System.out.println(p);
                }
                break;
            case 3:
                for (Product p:
                        cat3) {
                    System.out.println(p);
                }
                break;
        }
    }
    //return categories
    public ArrayList < Product > returnCatArrayList(int cat) {

        if (cat == 1) {
            return cat1;
        } else if (cat == 2) {
            return cat2;
        } else {
            return cat3;
        }
    }
} //end class Category