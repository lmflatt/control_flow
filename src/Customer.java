/**
 * Created by lee on 8/31/16.
 */
import java.lang.String;

public class Customer {

    public static String pinNumber;
    public static String name;
    public static double balance;

    public Customer() {
    }

    public Customer(String pinNumber, double balance) {
        this.pinNumber = pinNumber;
        this.balance = balance;
    }

    public static Customer newCustomer() {

        System.out.println("Before we begin, Enter [1] to generate standard customer \n or [2] to create a unique customer");
        int generatorKey = Integer.parseInt(Main.scanner.nextLine());
        Customer customer = null;

        if (generatorKey == 1) {
            customer = makeGenericCustomer();
        } else if (generatorKey == 2) {
            customer = makeCustomer();
        } else {
            System.out.println("Please Enter 1 or 2");
            newCustomer();
        }

        return customer;
    }



    public static Customer makeGenericCustomer() {
        Customer customer = new Customer("1234", 100);
        System.out.println("Your PIN is " + customer.pinNumber + " and your balance starts at $" + customer.balance);
        return customer;
    }




    public static Customer makeCustomer() {
        Customer customer = new Customer();
        validatePin(customer);
        validateBalance(customer);
        System.out.println("Your PIN is " + customer.pinNumber + " and your balance starts at $" + customer.balance);

        return customer;
    }


    public static void validatePin(Customer customer) {
        int u = 1;

        do {
            try {
                System.out.println("Please enter a 4-digit PIN number");
                String pinEntry = Main.scanner.nextLine();
                customer.pinNumber = pinEntry;
                u = pinEntry.length();
            } catch (Exception e) {
                System.out.println("Your PIN was not 4 digits long.");            }
        } while (u != 4);

    }

    public static void validateBalance(Customer customer) {
        double v = -1;

        do {
            try {
                System.out.println("Please enter a balance amount");
                double balanceEntry = Double.parseDouble(Main.scanner.nextLine());
                customer.balance = balanceEntry;
                v = balanceEntry;
            }
            catch (Exception e) {
                System.out.println("Please enter a number without letters or symbols.");
            }
        } while (v <= 0);

    }

    public void enterPin() {
        boolean p = false;
        System.out.println();
        System.out.println("Welcome to Java Bank!");


        do {
            System.out.println("Please enter your PIN:");
            String pinAttempt = Main.scanner.nextLine().toString();

            if (pinAttempt.equalsIgnoreCase(pinNumber)) {
                p = false;
            }
            else {
                System.out.println("PIN is not correct. Please try again");
                p = true;
            }
        } while(p);

        chooseTransaction();
    }

    public void chooseTransaction() {
        boolean u = true;

        while (u) {
            System.out.println("Enter [1] to Check Balance, [2] to Make a Withdrawal, or [3] to Cancel.");
            int userInput = Integer.parseInt(Main.scanner.nextLine());

            if (userInput == 1) {
                checkBalance();
                u = false;
            } else if (userInput == 2) {
                withdrawMoney();
                u = false;
            } else if (userInput == 3) {
                enterPin();
                u = false;
            } else {
                System.out.println();
                System.out.println("You must enter [1], [2], or [3]!");
            }
        }
    }

    public void checkBalance() {
        System.out.println();
        System.out.println("Your balance is currently $" + balance + ".");

        returnScreen();
    }

    public void withdrawMoney() {
        System.out.println();
        System.out.println("Please Enter the amount you would like to withdraw.");

        boolean t = true;

        while(t) {
            String stringAmt = Main.scanner.nextLine();

            if(stringAmt.charAt(0) == '$') {
                stringAmt = stringAmt.substring(1);
            }

            try {
                double numberAmt = Double.parseDouble(stringAmt);

                if (balance >= numberAmt) {
                    balance -= numberAmt;
                    System.out.println();
                    System.out.println("Success. Your remaining balance is $" + balance + ".");
                    t = false;
                } else {
                    System.out.println();
                    System.out.println("Withdrawal exceeds balance of $" + balance + ". Please enter another amount.");
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println("Please enter a valid number.");
            }
        }

        returnScreen();
    }

    public void returnScreen() {
        boolean b = true;

        while (b) {
            System.out.println();
            System.out.println("Enter [1] for another Transaction or [2] to Exit.");
            int userInput = Integer.parseInt(Main.scanner.nextLine());

            if (userInput == 1) {
                chooseTransaction();
                b = false;
            } else if (userInput == 2) {
                enterPin();
                b = false;
            } else {
                System.out.println();
                System.out.println("You must enter [1] or [2]!");
            }
        }
    }


}
