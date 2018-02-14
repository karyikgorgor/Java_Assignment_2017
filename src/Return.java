//Chan Kar Yik 16031635 (Leader)
//Daniel Yap  
//Zen Sebastian
//Hafiz

/*Team leader's phone number: 0123892183
  Team leader's email address: 16031635@imail.sunway.edu.my	OR mryik@live.com */

//imports for date and time
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

//start class return
public class Return {
    private String salesInfo;
    private final String returnId;
    private final int customerId; //(optional to be used)for checking if (customer)/ (who owns the order) requested this return
    private final int orderId; //update sales info in return
    private final String reason;
    private LocalDateTime pickupDate;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();

    private Scanner input = new Scanner(System. in );

    //constructor
    public Return(int id, String sales, int asscCust, int asscOrder) {
        returnId = String.format("R%d", id + 1);
        salesInfo = sales;
        customerId = asscCust;
        orderId = asscOrder - 1;
        reason = setReason();
        pickupDate = now;
        setPickupDate();
    }

    //return order ID
    public int getOrderId() {
        return orderId;
    }

    //update salesInfo before print search results
    public void setSalesInfo(String sales) {
        salesInfo = sales;
    }

    //reasons for returning the order
    private String setReason() {
        System.out.println("Please state your reason for returning the order:");
        return input.nextLine();
    }

    //set the pickup date for returning the order
    public void setPickupDate() {
        int days;
        boolean loop = true;
        LocalDateTime temp = pickupDate;
        int confirm = 2;

        do {
            System.out.printf("\nCurrent pick-up Date: %s\n" + "How many days would you like to add?\n", getPickupDate());
            days = input.nextInt();
            if (days < 0) {
                System.out.println("\nWe have not develop the technology to go back into the past!\n");
            } else {
                temp = pickupDate.plusDays(days);
                loop = false;
            }
        } while ( loop );
        do {
            loop = true;
            try {
                loop = false;
                System.out.printf("Set pick-up to %s ?\nEnter 1: Yes\nEnter 2: No\n", dtf.format(temp));
                confirm = input.nextInt();
                if (confirm != 1 && confirm != 2) {
                    System.out.println("Invalid input. Please Try again");
                    loop = true;
                }
            } catch(InputMismatchException e) {
                System.out.println("Not an integer. Please enter again: ");
                input.next();
                loop = true;
                continue;
            }
        } while ( loop );

        if (confirm == 1) {
            pickupDate = temp;
            System.out.printf("Pick-up Date is set to %s\n\n", getPickupDate());
        }
    }

    //return pickup date
    private String getPickupDate() {
        return dtf.format(pickupDate);
    }

    //return customerID
    public int getAssc() {
        return customerId;
    }

    //print info
    @Override
    public String toString() {
        return String.format("\n\nRETURN INFORMATION\n" + "--------------------\n" + "Return Id: %s\n" + "Pick-up Date: %s\n\n" + "REASON\n" + "-------------------\n" + "%s\n\n%s\n", returnId, pickupDate, reason, salesInfo);
    }

} //end class Return