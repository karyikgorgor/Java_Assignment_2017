//Chan Kar Yik 16031635 (Leader)
//Daniel Yap  
//Zen Sebastian
//Hafiz

/*Team leader's phone number: 0123892183
  Team leader's email address: 16031635@imail.sunway.edu.my	OR mryik@live.com */

import java.util.Scanner;
//regular expression imports for validating email, name, and dib
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//start customer class
public class Customer {
    private final String customerID;
    private String customerName;
    private String gender;
    private String dob;
    private String phoneNo;
    private String email;
    private String mailingAddress;
    private String temp[]; //to store last data that was entered

    private Scanner input = new Scanner(System.in);

    //constructor
    public Customer(int x, String customerName, String gender, String dob, String phoneNo, String email, String mailingAddress) {
        customerID = setCustomerID(x);
        this.customerName = customerName;
        this.gender = gender;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.email = email;
        this.mailingAddress = mailingAddress;
        updateCtmChanges();//initialise the first input into temp
    }

    //return to last changes
    public void revertCtmChanges(){
        customerName = temp[0];
        gender = temp[1];
        dob = temp[2];
        phoneNo = temp[3];
        email = temp[4];
        mailingAddress = temp[5];
    }

    //update last changes
    public void updateCtmChanges(){
        temp = new String[]{customerName, gender, dob, phoneNo, email, mailingAddress};
    }

    //generate customerID
    private String setCustomerID(int x) {
        String append;
        append = String.format("%d", (x + 1));
        int length;

        length = append.length();

        while (length < 0) {
            length = append.length();
            append = String.format("0%s", append);
        }
        return append;
    }

    //getting customer ID
    private String getCustomerID() {
        return String.format("C%s", customerID);
    }


    //enter customer name
    public void setCustomerName() {
        temp[0] = customerName;
        System.out.println("Enter customer's FULL name:");
        customerName = input.nextLine();

        while (!nameValidation(customerName)) {
            System.out.println("Invalid name. Please try again: ");
            customerName = input.nextLine();
        }
    }
    //return customer name
    public String getCustomerName() {
        return customerName;
    }
    // validate the format of customer name
    private boolean nameValidation(String txt) {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    // enter customer gender
    public void setGender() {
        temp[1] = gender;
        System.out.println("Enter customer's gender (Enter 1 for male; Enter 2 for female): ");
        String genderChoice = input.nextLine();

        switchLoop: while (!genderChoice.equals("1") || !genderChoice.equals("2")) {
            switch (genderChoice) {
                case "1":
                    gender = "Male";
                    break switchLoop;
                case "2":
                    gender = "Female";
                    break switchLoop;
                default:
                    System.out.println("Invalid input. Enter 1 for male; Enter 2 for female: ");
                    genderChoice = input.nextLine();
            }
        }

    }
    // return customer gender
    public String getGender() {
        return gender;
    }

    //enter customer dob
    public void setDob() {
        temp[2] = dob;
        System.out.println("Enter customer's date of birth with dd/MM/yyyy format (Example: 01/01/2001):");
        dob = input.nextLine();

        while (!dobValidation(dob)) {
            System.out.println("Invalid input. Possible reasons:");
            System.out.println("1) You didn't enter slash(es) between dd, MM, or yyyy");
            System.out.println("2) Invalid day(01-31), month(01-12) or year(1900-2017)");
            System.out.println("3) String were entered instead of number");
            System.out.println("4) No 0(s) were entered in front of the day (01-09) or the month (01-09)");
            System.out.println("Please try again with dd/MM/yyyy format (Example: 01/01/2001):");
            dob = input.nextLine();
        }

    }
    // return customer dob
    public String getDob() {
        return dob;
    }

    // validate the format of customer dob
    private boolean dobValidation(String dob) {
        String regex = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$";
        Pattern a = Pattern.compile(regex);
        Matcher b = a.matcher(dob);
        return b.matches();
    }

    // enter customer phone number
    public void setPhoneNo() {
        temp[3] = phoneNo;
        System.out.println("Enter customer's phone number (Only Malaysian mobile phone is allowed, e.g 0123456789):");
        phoneNo = input.nextLine();

        while (!phoneFormatValid(phoneNo)) {
            System.out.println("Invalid phone number. Please try again (Example: 0123456789): ");
            phoneNo = input.nextLine();
        }
    }
    // return customer phone number
    public String getPhoneNo() {
        return phoneNo;
    }
    // validate the format of phone number
    private boolean phoneFormatValid(String phoneNo) {
        if (phoneNo.matches("\\d{10}") && phoneNo.startsWith("0")) {
            return true;
        } else {
            return false;
        }
    }
    // enter email
    public void setEmail() {
        temp[4] = email;
        System.out.println("Enter customer's email address: ");
        email = input.nextLine();

        while (!emailFormatValid(email)) {
            System.out.println("Invalid email. Please try again: ");
            email = input.nextLine();
        }

    }
    // return email
    public String getEmail() {
        return email;
    }
    //validate the format of email
    private boolean emailFormatValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    // enter mailing address
    public void setMailingAddress() {
        temp[5] = mailingAddress;
        System.out.println("Enter customer's mailing address: ");
        mailingAddress = input.nextLine();
    }
    //return mailing address
    public String getMailingAddress() {
        return mailingAddress;
    }

    //print the info
    @Override
    public String toString() {
        return String.format("CUSTOMER INFORMATION\n-------------------\nCustomer ID: %s\nName: %s\nGender: %s\nDate of birth: %s\n" +
                        "Telephone: %s\nEmail: %s\nAddress: %s\n", getCustomerID(),
                getCustomerName(), getGender(), getDob(),
                getPhoneNo(), getEmail(), getMailingAddress());
    }
} //end class Customer