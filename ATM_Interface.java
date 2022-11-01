import java.util.Scanner;
import java.io.*;

class BankAccount {
    String name, userName, password, accountNo, transactionHistory = " ";
    float balance = 100000f;
    int transactions = 0;

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n Enter your name : ");
        this.name = sc.nextLine();
        System.out.print("\n Enter your Username : ");
        this.userName = sc.nextLine();
        System.out.print("\n Enter your password : ");
        this.password = sc.nextLine();
        System.out.print("\n Enter your Account Number : ");
        this.accountNo = sc.nextLine();
        System.out.println("\n Registration completed successfully ! please login ");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.print("\nEnter your Username : ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!isLogin) {
                    System.out.print("\nEnter your password : ");
                    String Password = sc.nextLine();
                    if (Password.equals(password)) {
                        System.out.print("\nLogin successfully!");
                        isLogin = true;
                    } else
                        System.out.println("\nIncorrect password ");
                }
            } else
                System.out.println("\nUsername not found");
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\nWithdrawl is completed");
                String str = amount + " Rs Withdrawed\n";
                transactionHistory = transactionHistory.concat(str);
            } else
                System.out.println("\nInsufficient balance ");
        } catch (Exception e) {
        }
    }

    public void deposit() {

        System.out.print("\nEnter amount to deposit : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (balance <= 100000f) {
                transactions++;
                balance += amount;
                System.out.println("\nSuccessfully deposited ");
                String str = amount + " Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else
                System.out.println("\nSorry.... Limit is 100000.00");
        } catch (Exception e) {
        }
    }

    public void transfer() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Receipent's name : ");
        String receipent = sc.nextLine();
        System.out.print("Enter amount to transfer :  ");
        float amount = sc.nextFloat();
        try {
            if (balance >= 100000f) {
                if (amount <= 50000f) {
                    transactions++;
                    balance = balance - amount;
                    System.out.println("\n" + amount + " Successfully Transfered to " + receipent);
                    String str = amount + " Rs Transfered to " + receipent + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else
                    System.out.println("\nSorry.... Limit is 50000.00");
            } else
                System.out.println("\nInsufficient balance ");
        } catch (Exception e) {
        }
    }

    public void TransactionHistory() {
        if (transactions == 0)
            System.out.println("\nEmpty");
        else
            System.out.println("\n" + transactionHistory);
    }
}

class ATM_Interface {
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (flag && (input > limit || input < 1)) {
                    System.out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value ");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n**WELCOME TO SBI ATM INTERFACE **\n");
        System.out.println("1.Register \n2.Exit");
        System.out.print("Enter your choice : ");
        int choice = takeIntegerInput(2);
        if (choice == 1) {
            BankAccount b = new BankAccount();
            b.register();
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.print("Enter your choice : ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n\n**WELCOME BACK " + b.name + " **\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println(
                                    "\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Transaction History \n5.Exit \n");
                            System.out.print("Enter your choice : ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.TransactionHistory();
                                    break;
                                case 5:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else
                    System.exit(0);
            }
        } else
            System.exit(0);
    }
}