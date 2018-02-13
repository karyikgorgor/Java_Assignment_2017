//Chan Kar Yik 16031635 (Leader)
//Daniel Yap
//Zen Sebastian
//Hafiz

/*Team leader's phone number: 0123892183
  Team leader's email address: 16031635@imail.sunway.edu.my	OR mryik@live.com */

import java.util.ArrayList; // import ArrayList
import java.util.InputMismatchException;
import java.util.Scanner;

//start class InquiryComplaint
public class InquiryComplaint {

    private String category; //FAQ categories
    private int option; // chosen option
    private int back = 0;

    //array list for storing FAQs
    private ArrayList < String > productFAQ = new ArrayList < String > ();
    private ArrayList < String > deliveryFAQ = new ArrayList < String > ();
    private ArrayList < String > paymentFAQ = new ArrayList < String > ();
    private ArrayList < String > orderFAQ = new ArrayList < String > ();

    private Scanner input = new Scanner(System. in );

    //function to add FAQ inside all the array lists
    public void addFAQ() {
        productFAQ.add("1. What are the materials you use to make your products?\n" + "Answer: Our products are made with high grade stensile steel and 100% natural latex.\n");
        productFAQ.add("2. Where are your products manufactured?\n" + "Answer: They are manufactured in Malaysia as we believe in providing jobs to our own ecenomy.\n");
        productFAQ.add("3. Are your products certified safe?\n" + "Answer: Our products go through vigorous testing by us and also have obtained the SIRIM certificate.\n");

        deliveryFAQ.add("1. Which countries do you currently deliver to?\n" + "Answer: We currently deliver to Malaysia ONLY.\n");
        deliveryFAQ.add("2. How much does delivery cost?\n" + "Answer: Normal delivery = RM5; VIP delivery = RM10\n");
        deliveryFAQ.add("3. How long will delivery take?\n" + "Answer: Normal delivery = 4 days; VIP delivery = 2 days\n");

        paymentFAQ.add("1. What payment methods do you accept?\n" + "Answer: We currently accept Credit Card[Visa, Master & Amex], AliPay and wire transfer MBB 142353533234.\n");
        paymentFAQ.add("2. Do you accept Debit Card?\n" + "Answer: No. Please refer to Question 1 for our accepted payment methods.\n");
        paymentFAQ.add("3. I think I was overcharged.\n" + "Answer: Please note that prices shown are not inclusive of 6% GST.\n");

        orderFAQ.add("1. How do I start ordering?\n" + "Answer: First, register an account with our retailer. After filling in all the required information, you're good to go.\n");
        orderFAQ.add("2. How many orders can I place at one time?\n" + "Answer: We understand that there must be some shopaholics who use our " + "system. If you're one of them, no worries at all. You're free to order as many as you want, as long as you pay it.\n");
        orderFAQ.add("3. How do I know how much I need to pay for the orders?\n" + "Answer: An order information will pop up whenever you finish ordering. All the crucial information are inside.\n");
    }

    public boolean backToOperator () {
        if (back == 1) {
            return true;
        } else {
            return false;
        }
    }

    // main menu of inquiry/complaint
    public void helpCenterMainMenu() {
        boolean loop = true;
        addFAQ();
        System.out.println("Customer Help Center");
        System.out.println("--------------------");
        System.out.println("Enter 1 = Inquiry");
        System.out.println("Enter 2 = Complaint");
        System.out.println("Enter 3 = Return to operator's main menu");

        while (loop) {
            option = handleExceptionsInt();
            switch (option) {
                case 1:
                    inquiry();
                    loop = false;
                    break;
                case 2:
                    submitComplaint();
                    loop = false;
                    break;
                case 3:
                    back = 1;
                    loop = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

    }

    // inquiry menu
    public void inquiry() {
        boolean loop = true;
        System.out.println("Have a question? Check out our FAQ or submit an inquiry!");
        System.out.println("Enter 1 = FAQ");
        System.out.println("Enter 2 = Submit an inquiry");
        System.out.println("Enter 3 = Return to Help Center's main menu");

        while (loop) {
            option = handleExceptionsInt();

            switch (option) {
                case 1:
                    FAQ();
                    loop = false;
                    break;
                case 2:
                    submitInquiry();
                    loop = false;
                    break;
                case 3:
                    helpCenterMainMenu();
                    loop = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    // functions to print FAQs
    public void FAQ() {
        boolean loop = true;
        System.out.println("Frequently Asked Questions");
        System.out.println("--------------------------");
        inquiryMenu();
        while (loop) {
            option = handleExceptionsInt();

            switch (option) {
                case 1:
                    productFAQ();
                    loop = false;
                    break;
                case 2:
                    deliveryFAQ();
                    loop = false;
                    break;
                case 3:
                    paymentFAQ();
                    loop = false;
                    break;
                case 4:
                    orderFAQ();
                    loop = false;
                    break;
                case 5:
                    helpCenterMainMenu();
                    loop = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

    }

    // Product FAQ
    public void productFAQ() {
        System.out.println("Product [Frequently Asked Questions]");
        System.out.println("------------------------------------");

        for (String x:
                productFAQ) {
            System.out.println(x);
        }
        inquiry();
    }

    // Delivery FAQ
    public void deliveryFAQ() {
        System.out.println("Delivery [Frequently Asked Questions]");
        System.out.println("------------------------------------");

        for (String x:
                deliveryFAQ) {
            System.out.println(x);
        }
        inquiry();
    }

    // Payment FAQ
    public void paymentFAQ() {
        System.out.println("Payment [Frequently Asked Questions]");
        System.out.println("------------------------------------");

        for (String x:
                paymentFAQ) {
            System.out.println(x);
        }
        inquiry();
    }

    //order FAQ
    public void orderFAQ() {
        System.out.println("Order [Frequently Asked Questions]");
        System.out.println("------------------------------------");

        for (String x:
                orderFAQ) {
            System.out.println(x);
        }
        inquiry();
    }

    // choices of inquiries
    public void inquiryMenu() {
        System.out.println("Enter 1 = Product Related");
        System.out.println("Enter 2 = Delivery Related");
        System.out.println("Enter 3 = Payment Related");
        System.out.println("Enter 4 = Order Related");
        System.out.println("Enter 5 = Return to Help Center's main menu");
    }
    // processing choice of inquiry
    public void submitInquiry() {
        boolean loop = true;

        System.out.println("What is your inquiry about?");
        inquiryMenu();

        while (loop) {
            option = handleExceptionsInt();
            switch (option) {
                case 1:
                    category = "Product Related";
                    loop = false;
                    break;
                case 2:
                    category = "Delivery Related";
                    loop = false;
                    break;
                case 3:
                    category = "Payment Related";
                    loop = false;
                    break;
                case 4:
                    category = "Order Related";
                    loop = false;
                    break;
                case 5:
                    helpCenterMainMenu();
                    loop = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

    }

    // complaint Menu
    public void complaintMenu() {
        System.out.println("Enter 1 = Product related");
        System.out.println("Enter 2 = Delivery related");
        System.out.println("Enter 3 = Payment related");
        System.out.println("Enter 4 = Order related");
        System.out.println("Enter 5 = Return to Help Center's main menu");
    }

    // processing choice of complaints
    public void submitComplaint() {
        boolean loop = true;
        System.out.println("What is your complaint about?");
        complaintMenu();

        while (loop) {
            option = handleExceptionsInt();
            switch (option) {

                case 1:
                    category = "Product Related";
                    loop = false;
                    break;
                case 2:
                    category = "Delivery Related";
                    loop = false;
                    break;
                case 3:
                    category = "Payment Related";
                    loop = false;
                    break;
                case 4:
                    category = "Order Related";
                    loop = false;
                    break;
                case 5:
                    helpCenterMainMenu();
                    loop = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    //return category of FAQ
    public String getInComCategory() {
        return category;
    }

    //handle any input mismatch exceptions
    public int handleExceptionsInt() {
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

    //handle any invalid input
    public void invalidInput() {
        System.out.println("Invalid input. Please try again: ");
    }

} // end class InquiryComplaint
