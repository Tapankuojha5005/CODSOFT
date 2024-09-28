import java.util.InputMismatchException;
import java.util.Scanner;

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("...ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            System.out.print("Choose from the options (1-4): ");
            
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); 
                continue;
            }

            switch (choice) {
                case 1: 
                    System.out.print("Enter the amount that you want to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (bankAccount.withdraw(withdrawAmount)) {
                        System.out.printf("Withdrew $%.2f.%n", withdrawAmount);
                    } else {
                        System.out.println("Withdrawal failed: Insufficient funds.");
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    if (bankAccount.deposit(depositAmount)) {
                        System.out.printf("Deposited $%.2f.%n", depositAmount);
                    } else {
                        System.out.println("Deposit failed: Amount must be greater than zero.");
                    }
                    break;

                case 3:
                    System.out.printf("Current balance: $%.2f%n", bankAccount.checkBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the initial balance for your account: ");
        double initialBalance;
        while (true) {
            try {
                initialBalance = scanner.nextDouble();
                if (initialBalance >= 0) break;
                System.out.println("Initial balance must be non-negative.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // clear the invalid input
            }
        }
        
        BankAccount account = new BankAccount(initialBalance);
        ATM atm = new ATM(account);
        atm.run();
    }
}
