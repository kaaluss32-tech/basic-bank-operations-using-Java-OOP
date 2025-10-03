import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base Class
class Account {
    protected String accountNumber;
    protected double balance;
    protected List<String> transactions = new ArrayList<>();

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        transactions.add("Account created with balance: " + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount);
            System.out.println("✅ Deposited " + amount);
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
            System.out.println("💸 Withdrawn " + amount);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    public void showBalance() {
        System.out.println("💰 Current Balance: " + balance);
    }

    public void showTransactions() {
        System.out.println("\n📜 Transaction History:");
        for (String t : transactions) {
            System.out.println("- " + t);
        }
    }
}

// Subclass - Demonstrating Inheritance and Overriding
class SavingsAccount extends Account {
    private double interestRate = 0.05;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // Overriding deposit to include interest
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        double interest = amount * interestRate;
        balance += interest;
        transactions.add("Interest added: " + interest);
        System.out.println("💹 Interest of " + interest + " added.");
    }
}

// Main Class
public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account = new SavingsAccount("ACC123", 1000);

        int choice;
        do {
            System.out.println("\n🏦 Bank Account Menu");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Show Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                }
                case 3 -> account.showBalance();
                case 4 -> account.showTransactions();
                case 5 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}

