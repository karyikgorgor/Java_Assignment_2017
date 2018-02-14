//Chan Kar Yik 16031635 (Leader)
//Daniel Yap  
//Zen Sebastian
//Hafiz

/*Team leader's phone number: 0123892183
  Team leader's email address: 16031635@imail.sunway.edu.my	OR mryik@live.com */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDateTime; //for showing order date and time
import java.time.format.DateTimeFormatter; //for showing order date and time

//start class
public class Order {
    private final String customer;
    private final int assc;
    private String status = "Unshipped";
    private final String orderID;
    private final String date;
    private LocalDateTime deliveryDate;
    private LocalDateTime deliveryDateTemp;
    private String deliveryType;
    private String specialRequest = "\tNone";
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
    private String sales;

    //arraylists
    private ArrayList < String > itemName = new ArrayList < String > ();
    private ArrayList < Double > itemPrice = new ArrayList < Double > ();
    private ArrayList < Integer > quantity = new ArrayList < Integer > ();
    private ArrayList < Integer > quantityTemp; //for update

    //objects
    private Category product = new Category();
    private Scanner input = new Scanner(System. in );

    //constructor
    public Order(int x, String cust, int cID) {
        orderID = setOrderID(x);
        date = dtf.format(now); //2016/11/16 12:08:43
        deliveryDate = now;
        customer = cust;
        assc = cID;
        setOrder();
        updateChanges();
    }
    //return asscociation
    public int getAssc() {
        return assc;
    }
    //generate order id
    private String setOrderID(int x) {
        return String.format("ORD%d", x + 1);
    }
    //get order id
    private String getOrderID() {
        return orderID;
    }
    //get customer info
    private String getCustomerInfo() {
        return customer;
    }
    //get date
    private String getDate() {
        return date;
    }
    //this can help customer to extend the delivery date
    public void setDeliveryDate() {
        boolean loop = true;
        int x;

        do {
            System.out.println("How long would you like to extend the day?");
            x = handleExceptionsInt();

            if (x < 1) {
                System.out.println("Please enter at least 1 quantity.");
            } else {
                loop = false;
            }
        } while ( loop );

        deliveryDate = deliveryDate.plusDays(x);
    }

    //return delivery date
    public String getDeliveryDate() {
        return dtf.format(deliveryDate);
    }

    //set quantity select by customer
    public void setQuantity() {
        for (int counter = 0; counter < itemName.size(); counter++) {
            System.out.printf("\nSet the quantity of this product:\n" + "%-10s \t %-10s \n" + "%-10s \t %-10s \n", "Product", "Price", itemName.get(counter), itemPrice.get(counter));
            System.out.printf("\n\t %10s\n" + "%-10s \t %-10d	=> ", "Original", "Quantity:", quantityTemp.get(counter));
            quantity.set(counter, handleExceptionsInt());
        }
    }

    //update order changes
    @SuppressWarnings("unchecked")
    public void updateChanges() {
        quantityTemp = (ArrayList < Integer > ) quantity.clone();
        deliveryDateTemp = deliveryDate;

    }
    //revert order changes if the update is cancelled
    @SuppressWarnings("unchecked")
    public void revertChanges() {
        deliveryDate = deliveryDateTemp;
        quantity = (ArrayList < Integer > ) quantityTemp.clone();

    }
    //validate the delivery status, if the product has been shipped, operator isn't allowed to change anything beside the delivery status
    public boolean validateStatus() {
        if (getStatus().equals("Shipping") || getStatus().equals("Reached + Received") || getStatus().equals("Reached + Not Received") || getStatus().equals("Returning")) {
            return true;
        } else {
            return false;
        }
    }

    //if the delivery is canceled, do not allow operator to change anything at all
    public boolean validateCanceledStatus() {
        if (getStatus().equals("Canceled")) {
            return true;
        } else {
            return false;
        }
    }

    //start ordering
    private void setOrder() {
        int ctg; //category
        int item; //item
        int qnt; //quantity
        int option;
        boolean loop;
        boolean mainLoop;
        boolean secLoop;
        boolean thirdLoop;
        do {
            mainLoop = true;
            ctg = 0; //reset variables
            item = 0;
            qnt = 0;

            do {
                thirdLoop = true;

                do { //select category
                    loop = true;
                    System.out.println("Choose a category:");
                    System.out.println("Enter 1 = Apple");
                    System.out.println("Enter 2 = Samsung");
                    System.out.println("Enter 3 = Sony");
                    ctg = handleExceptionsInt();

                    if (ctg < 1 || ctg > 3) {
                        System.out.println("Invalid input");
                        loop = true;
                    } else {
                        loop = false;
                        thirdLoop = false;
                    }
                } while ( loop ); //exit category loop

                do { //select item
                    secLoop = true;
                    product.getCategory(ctg);
                    System.out.println("Enter the product ID to puchase without the 'P' (-1 to return to category):\n");
                    item = handleExceptionsInt();

                    if (item == -1) {
                        secLoop = false; //continue secLoop
                        thirdLoop = true;
                        break; //end loop

                    } else if (item < 1 || item > product.returnCatArrayList(ctg).size()) {
                        System.out.print("Invalid input");
                        secLoop = true;

                    } else { //set quantity
                        secLoop = false;
                        do {
                            System.out.print("Select a quantity to purchase (-1 to return to purchase):\n");
                            qnt = handleExceptionsInt();

                            if (qnt == -1) {
                                loop = false;
                                secLoop = true;

                            } else if (qnt < 1) {
                                System.out.println("Enter at least 1 or more to buy this item:");
                                loop = true;
                            } else {
                                itemName.add(product.returnCatArrayList(ctg).get(item - 1).getName());
                                itemPrice.add(product.returnCatArrayList(ctg).get(item - 1).getdPrice());
                                quantity.add(qnt);
                                loop = false;
                            }
                        } while ( loop ); //exit quantity loop
                    } //end else
                } while ( secLoop ); //exit item loop
            } while ( thirdLoop );
            do {
                System.out.println("Continue buying?");
                System.out.println("Enter 1 = Yes");
                System.out.println("Enter 2 = No");
                option = handleExceptionsInt();
                switch (option) {
                    case 1:
                        setDeliveryType();
                        setSpecialRequest();
                        loop = false;
                        mainLoop = false;
                        break;
                    case 2:
                        loop = false;
                        break;
                    default:
                        System.out.println("You've entered an unavailable choice. Please try again:");
                        loop = true;
                        break;
                }
            } while ( loop );
        } while ( mainLoop );
    }

    //set returning delivery status
    public void setStatus(String x) {
        if (!x.equals("Returning")) {
            System.out.println("Status is not allowed to set.");
        } else {
            status = x;
        }
    }

    //set delivery status
    public void setStatus() {
        String temp = "";
        boolean loop = true;
        int option;
        do {
            System.out.printf("\nOrder Status: %s\n", status);
            System.out.println("What status would you like to set?");
            System.out.println("Enter 1 = Unshipped");
            System.out.println("Enter 2 = Shipping");
            System.out.println("Enter 3 = Reached + Received");
            System.out.println("Enter 4 = Reached + Not received");
            System.out.println("Enter 5 = Returned");
            System.out.println("Enter 6 = Canceled");

            option = handleExceptionsInt();

            switch (option) {
                case 1:
                    temp = "Unshipped";
                    break;
                case 2:
                    temp = "Shipping";
                    break;
                case 3:
                    temp = "Reached + Received";
                    break;
                case 4:
                    temp = "Reached + Not Received";
                    break;
                case 5:
                    temp = "Returned";
                    break;
                case 6:
                    temp = "Canceled";
                    break;
                default:
                    System.out.println("Invalid option. Please try again: ");
                    break;
            }
            status = temp;
            loop = false;

        } while ( loop );
    }

    //return delivery status
    private String getStatus() {
        return status;
    }

    //calculate sales
    private void setSales() {
        int size = itemName.size();
        String name;
        int qnt;
        double price;
        double totalPrice = 0;
        sales = "";
        String append = "";

        sales = String.format("%-10s \t %-10s \t %-10s \t %-10s \t %-10s\n", "Items ordered", "Quantity", "Delivery Type", "", "Total(RM)");

        for (int counter = 0; counter < size; counter++) {
            name = itemName.get(counter);
            qnt = quantity.get(counter);
            price = (qnt * itemPrice.get(counter));

            if (deliveryType.equals("Normal(RM 5.00)")) {
                price += 5;
            } else if (deliveryType.equals("VIP(RM 10.00)")) {
                price += 10;
            }
            totalPrice += price;
            append = String.format("%-10s \t %-10d \t %-10s \t %-10s \t %-10.2f\n", name, qnt, deliveryType, "", price);
            sales = String.format("%s%s", sales, append);
        }

        sales = String.format("%s%-10s \t %-10s \t %-10s \t %-10s \t RM%-10.2f", sales, "Total Price:", "", "", "", totalPrice);
    }

    //return sales
    public String getSales() {
        setSales(); //check for any changes
        return sales;
    }

    //set the delivery type of the product
    private void setDeliveryType() {
        int option;
        boolean loop = true;
        do {
            System.out.println("What type of delivery do you prefer?");
            System.out.println("Enter 1 = Normal Delivery (RM 5.00, 5 days to reach)");
            System.out.println("Enter 2 = VIP Delivery (RM 10.00), 2 days to reach)");
            option = handleExceptionsInt();

            switch (option) {
                case 1:
                    deliveryType = "Normal(RM 5.00)";
                    deliveryDate = deliveryDate.plusDays(5); // normal delivery = 5 days to reach
                    loop = false;
                    break;
                case 2:
                    deliveryType = "VIP(RM 10.00)";
                    deliveryDate = deliveryDate.plusDays(2); //VIP delivery = 2 days to reach
                    loop = false;
                    break;
                default:
                    System.out.println("You've entered an unavailable choice. Please try again:");
                    break;
            }
        } while ( loop );
    }

    //return delivery type
    private String getDeliveryType() {
        return deliveryType;
    }

    //set special request
    private void setSpecialRequest() {
        boolean loop1 = true;
        boolean loop2 = false;
        int option;

        do {
            System.out.println("Do you want to leave a special request?");
            System.out.println("Enter 1 = Yes");
            System.out.println("Enter 2 = No");
            option = handleExceptionsInt();

            switch (option) {
                case 1:
                    loop1 = false;
                    loop2 = true;
                    break;
                case 2:
                    loop1 = false;
                    loop2 = false;
                    break;
                default:
                    System.out.println("You've entered an unavailable choice. Please try again:");
                    break;
            }
        } while ( loop1 );
        while (loop2) {
            input.nextLine(); //this is to catch \n
            System.out.println("Please enter your special request:");
            specialRequest = input.nextLine();

            System.out.println("YOUR MESSAGE:");
            System.out.println("-------------");
            System.out.printf("%s\n\n", specialRequest);
            System.out.println("Do you want to set this special request?");
            System.out.println("Enter 1 = Set this message");
            System.out.println("Enter 2 = No");
            option = handleExceptionsInt();

            switch (option) {
                case 1:
                    loop2 = false; //break the loop to set the request
                    break;
                case 2:
                    //nothing happens because loop2 = true
                    break;
                default:
                    System.out.println("You've entered an unavailable choice. Please try again:");
                    break;
            }
        }

    }
    //return special request
    private String getSpecialRequest() {
        return specialRequest;
    }

    //to handle any input mismatch exceptions
    private int handleExceptionsInt() {
        int error = 0;
        boolean loop = true;
        while (loop) {
            try {
                loop = false;
                error = input.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Not an integer. Please enter again: ");
                input.next();
                loop = true;
                continue;
            }

        }
        return error;
    }
    //print out the results
    @Override
    public String toString() {
        return String.format("%s\n" + "ORDER INFORMATION\n" + "-----------------\n" + "Order ID: %s\n" + "Order Date : %s\n" + "Delivery Date: %s\n" + "Status: %s\n\n" + "SALES\n" + "-----------------\n" + "%s\n\n" + "SPECIAL REQUEST\n" + "-----------------\n%s\n" + "Delivery Type: %s\n" + "Operator's Name: Zen Sebastian\n\n", getCustomerInfo(), getOrderID(), getDate(), getDeliveryDate(), getStatus(), getSales(), getSpecialRequest(), getDeliveryType());
    }
} //end class Order	