//Chan Kar Yik 16031635 (Leader)
//Daniel Yap  
//Zen Sebastian
//Hafiz

/*Team leader's phone number: 0123892183
  Team leader's email address: 16031635@imail.sunway.edu.my	OR mryik@live.com */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
//below imports are crucial for sending emails
/*
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/

//start class
public class Operator {
    private Scanner input = new Scanner(System. in );

    private int mainMenuChoice; //let operator choose what to do
    private int search; //variable for searching customers and orders
    private int updateCustomer; // variable for updating customers
    private int updateOrder; // variable for updating orders
    private int account = 1; //account count, will be increment to 0 later to fill in index 0 of arraylist
    private int orders = -1; //order count, will be increment to 0 later to fill in index 0 of arraylist
    private int returns = -1;
    private int id; //variable for id
    private int confirmChange; //variable for confirming updates of customers and orders
    private int breakValue; //variable for breaking a loop
    private boolean startProgram = true;
    private int currentCustomer;
    private int currentOrder;

    //for inquiry & complaint usage
    private String subject;
    private String message;
    private String orderID;

    //test account
    private Customer ctmTest = new Customer(0, "Chan Kar Yik", "Male", "02/09/1998", "0123892183", "mryik34@gmail.com", "LONGKANG");
    private Customer ctmTest3 = new Customer(0, "Chan Kar Yik", "Male", "02/09/1998", "0123892183", "mryik34@gmail.com", "LONGKANG");
    private Customer ctmTest2 = new Customer(1, "Daniel Yap", "Female", "25/08/1998", "0177670195", "danielyap@gmail.com", "Toilet");


    private InquiryComplaint inComTest = new InquiryComplaint();
    //ArrayList for customer, order, and return
    private ArrayList < Customer > ctmInfoDtb = new ArrayList < Customer > ();
    private ArrayList < Order > ordDtb = new ArrayList < Order > ();
    private ArrayList < Return > returnDtb = new ArrayList < Return > ();
    //keep looping the program until stop by a command
    public void programStart() {
        ctmInfoDtb.add(ctmTest3);
        ctmInfoDtb.add(ctmTest2);
        while (startProgram) {
            processMainMenuChoice();
        }
    }

    //start program
    private void processMainMenuChoice() {
        boolean loop = true;
        System.out.println("System for Operator");
        System.out.println("-------------------\n");
        System.out.println("CUSTOMER MENU");
        System.out.println("-------------");
        System.out.println("Enter 1 = Create a new account");
        System.out.println("Enter 2 = Search existing customer");
        System.out.println("Enter 3 = Update existing customer record\n");
        System.out.println("ORDER MENU");
        System.out.println("----------");
        System.out.println("Enter 4 = Place an order");
        System.out.println("Enter 5 = Search an order");
        System.out.println("Enter 6 = Update/Change an order");
        System.out.println("Enter 7 = Return an order");
        System.out.println("Enter 8 = Search a return");
        System.out.println("Enter 9 = Update return pick-up date\n");
        System.out.println("OTHERS");
        System.out.println("------");
        System.out.println("Enter 10 = Inquiry/Complaint");
        System.out.println("Enter 11 = Exit the program");

        while (loop) {
            mainMenuChoice = handleExceptionsInt();
            //selections
            switch (mainMenuChoice) {
                case 1:
                    fillCustomerInfo();
                    loop = false;
                    break;
                case 2:
                    if (account == -1) {
                        System.out.println("No accounts have been created yet. Please enter another choice: \n");
                        break;
                    }
                    searchCustomer();
                    loop = false;
                    break;
                case 3:
                    if (account == -1) {
                        System.out.println("No accounts have been created yet. Please enter another choice: \n");
                    } else {
                        searchCustomer();
                        updateCustomerMenu();
                        updateCustomerInfo();
                        loop = false;
                    }
                    break;
                case 4:
                    if (account == -1) {
                        System.out.println("No accounts have been created yet. Please enter another choice: \n");
                    } else {
                        searchCustomer();
                        orders++;
                        ordDtb.add(new Order(orders, String.format("%s", getCustomer(search - 1)), currentCustomer));
                        System.out.println(ordDtb.get(orders)); //add array exception
                        loop = false;
                    }
                    break;
                case 5:
                    if (account == -1) {
                        System.out.println("No accounts have been created yet. Please enter another choice: \n");
                    } else {
                        searchOrder();
                        loop = false;
                    }
                    break;

                case 6:
                    if (orders == -1) {
                        System.out.println("No accounts have been created yet. Please enter another choice: \n");
                    } else {
                        searchCustomer();
                        searchOrder();
                        if (checkAsscOrder()) {
                            updateOrderMenu();
                            if (breakValue != 1) {
                                updateOrderInfo();
                            }
                        }
                        loop = false;

                    }
                    break;
                case 7:
                    if (orders == -1) {
                        System.out.println("No accounts have been created yet. Please enter another choice: \n");
                    } else {
                        searchCustomer();
                        searchOrder();
                        if (checkAsscOrder()) {
                            returns++;
                            getOrder(search - 1).setStatus("Returning");
                            returnDtb.add(new Return(returns, getOrder(search - 1).getSales(), currentCustomer, currentOrder));
                            System.out.println(getReturn(returns)); //add array exception
                        }
                        loop = false;
                    }
                    break;
                case 8:
                    if (returns == -1) {
                        System.out.println("No returns have been made yet. Please enter another choice:");
                    } else {
                        searchReturn();
                        loop = false;
                    }
                    break;
                case 9:
                    if (returns == -1) {
                        System.out.println("No returns have been made yet. Please enter another choice:");
                    } else {
                        searchCustomer();
                        searchReturn();
                        if (checkAsscReturn()) {
                            updateReturnDate();
                        }
                        loop = false;
                    }
                    break;
                case 10:
                    if (account == -1) {
                        System.out.println("No accounts have been created yet. Please enter another choice: \n");
                    } else {
                        searchCustomer();
                        inComTest.helpCenterMainMenu();
                        if (!inComTest.backToOperator()) {
                            inquiryOrComplaintForm();
                        }
                        loop = false;
                    }
                    break;
                case 11:
                    loop = false;
                    startProgram = false;
                    break;
                default:
                    System.out.println("You've entered an unavailable choice. Please enter again: \n");
                    break;
            }
        }

    }

    //fill in the customer info into ctmInfoDtb arraylist
    private void fillCtmDtb() {
        ctmInfoDtb.add(new Customer(account, ctmTest.getCustomerName(), ctmTest.getGender(), ctmTest.getDob(), ctmTest.getPhoneNo(), ctmTest.getEmail(), ctmTest.getMailingAddress()));
    }

    //fill in the customer info
    private void fillCustomerInfo() {
        account++;
        ctmTest.setCustomerName();
        ctmTest.setGender();
        ctmTest.setDob();
        ctmTest.setPhoneNo();
        ctmTest.setEmail();
        ctmTest.setMailingAddress();
        fillCtmDtb();
        System.out.println("Successfully registered.\n");
        System.out.println(getCustomer(account));
    }

    //search customer
    private void searchCustomer() {
        id = search - 1;
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("Please enter a valid customer ID by entering the number without 'C': ");
                search = handleExceptionsInt();
                System.out.println(getCustomer(search - 1));
                currentCustomer = search;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid input. This customer does not exist.");
                continue;
            }
            loop = false;
        }
    }

    //update customer info
    private void updateCustomerMenu() {
        System.out.println("Which part of information would you like to update?");
        System.out.println("Enter 1 = Customer name");
        System.out.println("Enter 2 = Gender");
        System.out.println("Enter 3 = Date of birth");
        System.out.println("Enter 4 = Email address");
        System.out.println("Enter 5 = Mailing address");
        System.out.println("Enter 6 = All of the above");

        updateCustomer = handleExceptionsInt();
    }

    //return update customer info choice
    private int getUpdateCustomer() {
        return updateCustomer;
    }

    //updating customer info
    private void updateCustomerInfo() {
        boolean loop = true;
        while (loop) {
            switch (getUpdateCustomer()) {
                case 1:
                    getCustomer(id+1).setCustomerName();
                    setConfirmChangeCustomer();
                    loop = false;
                    break;
                case 2:
                    getCustomer(id+1).setGender();
                    setConfirmChangeCustomer();
                    loop = false;
                    break;
                case 3:
                    getCustomer(id+1).setDob();
                    setConfirmChangeCustomer();
                    loop = false;
                    break;
                case 4:
                    getCustomer(id+1).setEmail();
                    setConfirmChangeCustomer();
                    loop = false;
                    break;
                case 5:
                    getCustomer(id+1).setMailingAddress();
                    setConfirmChangeCustomer();
                    loop = false;
                    break;
                case 6:
                    getCustomer(id+1).setCustomerName();
                    getCustomer(id+1).setGender();
                    getCustomer(id+1).setDob();
                    getCustomer(id+1).setPhoneNo();
                    getCustomer(id+1).setEmail();
                    getCustomer(id+1).setMailingAddress();
                    setConfirmChangeCustomer();
                    loop = false;
                    break;
                default:
                    System.out.println("Error 104: Invalid input. You've entered an unavailable choice. Please enter again: ");
                    updateCustomer = handleExceptionsInt();
                    break;
            }
        }
    }

    // update order choice
    private void updateOrderMenu() {
        boolean loop = true;
        breakValue = 0;
        while (loop) {
            getOrder(id).getSales();
            if (getOrder(id).validateStatus()) {
                System.out.println("The parcel(s) is being/returning/has been shipped. You're only allowed to change the status of the order.\n");
                System.out.println("Enter 1 = Change the status of the order");
                System.out.println("Enter 2 = Return to main menu");
                updateOrder = handleExceptionsInt();
                switch (updateOrder) {
                    case 1:
                        getOrder(id).setStatus();
                        setConfirmChangeCtmOrder();
                        breakValue = 1;
                        loop = false;
                        break;
                    case 2:
                        breakValue = 1;
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid input. You've entered an unavailable choice. Please enter again: ");
                        break;
                }

            } else if (getOrder(id).validateCanceledStatus()) {
                System.out.println("This order has been canceled. Please order again if you wish to.\n");
                breakValue = 1;
                loop = false;
            }
            else {
                System.out.println("Delivery Date: " + getOrder(id).getDeliveryDate());
                System.out.println("\nWhich part of information would you like to update?");
                System.out.println("Enter 1 = Quantity of Purchase");
                System.out.println("Enter 2 = Delivery Date");
                System.out.println("Enter 3 = Status");
                System.out.println("Enter 4 = All of the above");
                updateOrder = handleExceptionsInt();
            }
            loop = false;
        }

    }

    //return update order choice
    private int getUpdateOrder() {
        return updateOrder;
    }
    //start updating orders
    private void updateOrderInfo() {
        boolean loop = true;
        while (loop) {
            switch (getUpdateOrder()) {
                case 1:
                    getOrder(id).setQuantity();
                    setConfirmChangeCtmOrder();
                    loop = false;
                    break;
                case 2:
                    getOrder(id).setDeliveryDate();
                    setConfirmChangeCtmOrder();
                    loop = false;
                    break;
                case 3:
                    getOrder(id).setStatus();
                    setConfirmChangeCtmOrder();
                    loop = false;
                    break;
                case 4:
                    getOrder(id).setQuantity();
                    getOrder(id).setDeliveryDate();
                    getOrder(id).setStatus();
                    setConfirmChangeCtmOrder();
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid input. You've entered an unavailable choice. Please enter again: ");
                    updateOrder = handleExceptionsInt();
                    break;
            }
        }
    }
    //confirmation for changing orders
    private void setConfirmChangeCtmOrder() {
        boolean loop = true;

        System.out.println("Are you sure to make this change(s)?");
        System.out.println("Enter 1 = Yes");
        System.out.println("Enter 2 = No. I would like to change again");
        System.out.println("Enter 3 = No. I would NOT like to change anymore (You will be redirected to main menu)");

        while (loop) {
            confirmChange = handleExceptionsInt();

            switch (confirmChange) {
                case 1:
                    System.out.println("Updated successfully. New record below:\n");
                    System.out.println(getOrder(id));

                    getOrder(id).updateChanges();
                    loop = false;
                    break;
                case 2:
                    getOrder(id).revertChanges();
                    System.out.println(getOrder(id));
                    updateOrderMenu();
                    updateOrderInfo();
                    loop = false;
                    break;
                case 3:
                    getOrder(id).revertChanges();
                    loop = false;
                    System.out.println("Successfully redirected.");
                    break;
                default:
                    System.out.print("Invalid input. Please enter again: ");
                    break;
            }
        }
    }

    //confirmation for changing customer info
    private void setConfirmChangeCustomer() {
        boolean loop = true;

        System.out.println("Are you sure to make this change(s)?");
        System.out.println("Enter 1 = Yes");
        System.out.println("Enter 2 = No. I would like to change again");
        System.out.println("Enter 3 = No. I would NOT like to change anymore (You will be redirected to main menu)");

        while (loop) {
            confirmChange = handleExceptionsInt();

            switch (confirmChange) {
                case 1:
                    System.out.println("Updated successfully. New record below:\n");
                    System.out.println(getCustomer(id+1));
                    getCustomer(id+1).updateCtmChanges();
                    loop = false;
                    break;
                case 2:
                    getCustomer(id+1).revertCtmChanges();
                    System.out.println(getCustomer(id+1));
                    updateCustomerMenu();
                    updateCustomerInfo();
                    loop = false;
                    break;
                case 3:
                    getCustomer(id+1).revertCtmChanges();
                    loop = false;
                    System.out.println("Successfully redirected.");
                    break;
                default:
                    System.out.print("Error 106: Invalid input. Please enter again: ");
                    break;
            }
        }
    }

    //getting the index x's object of customer arraylist
    private Customer getCustomer(int x) {
        return ctmInfoDtb.get(x); //add array exception
    }

    //getting the index x's object of order arraylist
    private Order getOrder(int x) {
        return ordDtb.get(x); //add array exception
    }

    //search order function
    private void searchOrder() {
        id = search - 1;
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("Please enter a valid order ID by entering the number without 'ORD': ");
                search = handleExceptionsInt();
                System.out.println(getOrder(search - 1));
                currentOrder = search;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid input. This order does not exist.");
                continue;
            }
            loop = false;
        }
    }

    //check customer's association with orders
    private boolean checkAsscOrder() {
        if (currentCustomer != getOrder(search - 1).getAssc()) {
            System.out.println("\nTHIS ORDER DOES NOT BELONG TO THE CUSTOMER.\n");
            return false;
        } else {
            return true;
        }
    }
    //check customer's association with returns
    private boolean checkAsscReturn() {
        if (currentCustomer != getReturn(search - 1).getAssc()) {
            System.out.println("This return does not belongs to the customer.");
            return false;
        } else {
            return true;
        }
    }
    //search returns
    private void searchReturn() {
        id = search - 1;
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("Please enter a valid Return ID by entering the number without 'R': ");
                search = handleExceptionsInt();
                System.out.println(getReturn(search - 1));
                getReturn(search - 1).setSalesInfo(String.format("%s", getOrder(getReturn(search - 1).getOrderId()))); //update info for return before print
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid input. This return does not exist.");
                continue;
            }
            loop = false;
        }
    }
    //update pickup date for returns
    private void updateReturnDate() {
        getReturn(search - 1).setPickupDate();
    }
    //return pickup date
    private Return getReturn(int x) {
        return returnDtb.get(x); //add array exception
    }


    //inquiry/complaint form
    private void inquiryOrComplaintForm() {
        input.nextLine();
        System.out.printf("\nCategory: %s\n", inComTest.getInComCategory());
        System.out.println("Enter your subject: ");
        subject = input.nextLine();
        System.out.println("Enter your message: ");
        message = input.nextLine();

        if (inComTest.getInComCategory().equals("Order Related")) {
            System.out.println("Enter order ID: ");
            orderID = input.nextLine();
        } else {
            orderID = "None";
        }
        sendFormEmail();
    }

    //printing the inquiry/complaint form
    public String toInComString() {
        return String.format("Name: %s\n" + "Category: %s\n" + "OrderID: %s\n" + "Subject: %s\n" + "Email: %s\n" + "Message: %s\n", getCustomer(search - 1).getCustomerName(), inComTest.getInComCategory(), orderID, subject, getCustomer(search - 1).getEmail(), message);
    }

    //send the inquiry/complaint forms through email
    private void sendFormEmail() {
		/*boolean loop = true;
		final String username = "pokeemailserver@gmail.com";
		final String password = "pokeisthebest123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		while (loop) {
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("pokeemailserver@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mryik@live.cn"));
				message.setSubject("Inquiry/Complaint Email");
				message.setText(toInComString());
				System.out.println("Sending email...");

				Transport.send(message);

				System.out.println("Email sent.\n");
				loop = false;
			} catch(MessagingException e) {
				throw new RuntimeException(e);
			} catch(IndexOutOfBoundsException e) {
				System.out.println("The email does not exist!");
				loop = false;
			}
		}*/
    }

    //handle any input mismatch exceptions
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

} //end class Operator